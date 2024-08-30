package com.ues.edu.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static com.sun.tools.javac.jvm.Gen.one;
import com.ues.edu.entities.Boletos;
import com.ues.edu.entities.Carrito;
import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.entities.ReporteBoletosEstablec;
import com.ues.edu.entities.Usuarios;
import com.ues.edu.entities.Zona_Establec;
import com.ues.edu.model.dao.BoletoDao;
import com.ues.edu.model.dao.BoletosEstablecimientoDao;
import com.ues.edu.model.dao.EventoProgramadoDao;
import com.ues.edu.model.dao.Evento_ProDao;
import jakarta.jms.JMSException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

//@WebServlet("/boletos")
///@WebServlet("/even_pro")
@WebServlet(name = "ControllerBoletosEstablecimiento", urlPatterns = {"/ControllerBoletosEstablecimiento"})
public class ControllerBoletosEstablecimiento extends HttpServlet {

    private BoletosEstablecimientoDao bolDao;
    private Evento_ProDao evtDao;
    private Evento_Programado evenPro;

    private BoletoDao boletoDao;
    private EventoProgramadoDao eventoP;
    //PARA TOMAR EL EVENTO SELECCIONADO JUNTO CON LA ZONA
    Evento_Programado ep = new Evento_Programado();

    ArrayList<Carrito> listaCarrito = new ArrayList<>();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;

    //VARIABLES PARA  OBJETOS
    int idZE;
    int idEP;
    Carrito car;

    @Override
    public void init() throws ServletException {
        super.init();
        // Aquí puedes inicializar tu DAO si es necesario
        bolDao = new BoletosEstablecimientoDao(); // Reemplaza "BoletoDao" con el nombre de tu clase DAO de Boletos
        boletoDao = new BoletoDao(); // Reemplaza "BoletoDao" con el nombre de tu clase DAO de Boletos
        evtDao = new Evento_ProDao(); // Reemplaza "BoletoDao" con el nombre de tu clase DAO de Boletos
        eventoP = new EventoProgramadoDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(Evento_Programado.class.getName()).log(Level.SEVERE, null, ex);
        }

        //doGet se encarga de obtener la lista de boletos y enviarla a la página JSP
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.bolDao = new BoletosEstablecimientoDao();
        this.evenPro = new Evento_Programado();

        if (request.getParameter("botonEnviarEstablecimiento") != null) {
            try {
                String establecimientoSelec = request.getParameter("botonEnviarEstablecimiento");
                List<ReporteBoletosEstablec> listaReporte = this.bolDao.getBoletosComprados(establecimientoSelec);
                request.setAttribute("listaReporteBoletos", listaReporte);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerBoletosEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("consultas/Boletos_Comprado_Establecimiento.jsp").forward(request, response);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, JMSException {
        String accion = request.getParameter("accion");
        System.out.println(accion);
        switch (accion) {
            case "AgregarCarrito":
                //PARA CONOCER LA POSICION DEL PRODUCTO
                int pos = 0;
                cantidad = 1;
                //AQUI TOMO EL EVENTO Y LA ZONA QUE FUE SELECCIONADA
                idZE = Integer.parseInt(request.getParameter("idZE"));
                idEP = Integer.parseInt(request.getParameter("idEP"));
                ep = evtDao.getEventoPorId(idZE, idEP);
                if (listaCarrito.size() > 0) {
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if (idZE == listaCarrito.get(i).getIdZonaEstable() && idEP == listaCarrito.get(i).getIdEventoProgra()) {
                            pos = i;
                        }
                    }
                    //ACTUALIZAMOS EL REGISTRO EXISTENTE
                    if (idZE == listaCarrito.get(pos).getIdZonaEstable() && idEP == listaCarrito.get(pos).getIdEventoProgra()) {
                        cantidad = listaCarrito.get(pos).getCantidad() + cantidad;//SI AGREGO UN NUEVO PRODUCTO, PARA NO DUPLICAR SOLO LE SUMO UNO MAS AL QUE YA HABIA REGISTRADO
                        double subTotal = listaCarrito.get(pos).getPrecio() * cantidad;
                        //AHORA ACTUALIZO EL SUB TOTAL Y LA CANTIDAD DEL PRODUCTO AL CUAL LE ESTOY AGREGANDO OTRO PRODUCTO
                        listaCarrito.get(pos).setCantidad(cantidad);
                        listaCarrito.get(pos).setSubTotal(subTotal);
                    } else {
                        //SI NO ENCONTRO NINGUN REGISTRO LO AGREGAMOS CON NORMALIDAD
                        item = item + 1;
                        car = new Carrito();
                        car.setItem(item);
                        car.setIdZonaEstable(ep.getEstablecimientos().getZonas().get(0).getIdZonaEstablecimiento());
                        car.setIdEventoProgra(ep.getId_evento_progra());
                        car.setnEvento(ep.getId_evento().getnEvento());
                        car.setnZona(ep.getEstablecimientos().getZonas().get(0).getIdZona().getNombreZonas());
                        car.setDescripcion(ep.getId_evento().getDescripcion());
                        car.setEstablecimiento(ep.getEstablecimientos().getN_establecimiento());
                        car.setDireccion(ep.getEstablecimientos().getDireccion());
                        car.setBoletosDisponibles(ep.getEstablecimientos().getZonas().get(0).getCapacidadZona());
                        car.setPrecio(ep.getEstablecimientos().getZonas().get(0).getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(cantidad * ep.getEstablecimientos().getZonas().get(0).getPrecio());
                        listaCarrito.add(car);
                    }
                } else {//SI NO HAY PRODUCTOS DEL MISMO TIPO AGREGADO LO AGREGARA NORMAL
                    item = item + 1;
                    car = new Carrito();
                    car.setItem(item);
                    car.setIdZonaEstable(ep.getEstablecimientos().getZonas().get(0).getIdZonaEstablecimiento());
                    car.setIdEventoProgra(ep.getId_evento_progra());
                    car.setnEvento(ep.getId_evento().getnEvento());
                    car.setnZona(ep.getEstablecimientos().getZonas().get(0).getIdZona().getNombreZonas());
                    car.setDescripcion(ep.getId_evento().getDescripcion());
                    car.setEstablecimiento(ep.getEstablecimientos().getN_establecimiento());
                    car.setDireccion(ep.getEstablecimientos().getDireccion());
                    car.setBoletosDisponibles(ep.getEstablecimientos().getZonas().get(0).getCapacidadZona());
                    car.setPrecio(ep.getEstablecimientos().getZonas().get(0).getPrecio());
                    car.setCantidad(cantidad);
                    car.setSubTotal(cantidad * ep.getEstablecimientos().getZonas().get(0).getPrecio());
                    listaCarrito.add(car);
                }

                request.setAttribute("contador", listaCarrito.size());
                request.getRequestDispatcher("ControllerBoletosEstablecimiento?accion=home").forward(request, response);
                break;

            case "Delete":
                //TOMAMOS LOS ID QUE VIENEN DESDE EL ARCHIVO JAVASCRIPT
                idZE = Integer.parseInt(request.getParameter("idZE"));
                System.out.println("El id de la zona establecimiento: " + idZE);
                idEP = Integer.parseInt(request.getParameter("idEP"));
                System.out.println("El id del evento programado " + idEP);
                //RECORREMOS LA LISTA PARA IR COMPARANDO HASTA ENCONTRAR EL PRODUCTO QUE QUEREMOS ELIMINAR DEL CARRITO
                for (int i = 0; i < listaCarrito.size(); i++) {
                    //SI LOS DOS ID SON IGUALES QUIERE DECIR QUE YA ENCONTRAMOS EL BOLETO QUE QUEREMOS ELIMINAR
                    if (listaCarrito.get(i).getIdZonaEstable() == idZE && listaCarrito.get(i).getIdEventoProgra() == idEP) {
                        //Y SIMPLEMENTE ELIMINAMOS
                        listaCarrito.remove(i);
                    }
                }
                break;
            case "Carrito":
                totalPagar = 0.0;
                request.setAttribute("carrito", listaCarrito);
                //SACAMOS EL TOTAL A PAGAR PARA LUEGO SOLO MANDARLO A IMPRIMIR
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("VentaDeBoletos/carrito.jsp").forward(request, response);
                break;

            case "Comprar"://ESTA OPCION NOS MANDA DIRECTAMENTE A LA COMPRA SIN TENER QUE PASAR POR EL CARRITO
                totalPagar = 0;
                //AQUI TOMO EL EVENTO Y LA ZONA QUE FUE SELECCIONADA
                idZE = Integer.parseInt(request.getParameter("idZE"));
                idEP = Integer.parseInt(request.getParameter("idEP"));
                ep = evtDao.getEventoPorId(idZE, idEP);
                item = item + 1;
                car = new Carrito();
                car.setItem(item);
                car.setIdZonaEstable(ep.getEstablecimientos().getZonas().get(0).getIdZonaEstablecimiento());
                car.setIdEventoProgra(ep.getId_evento_progra());
                car.setnEvento(ep.getId_evento().getnEvento());
                car.setnZona(ep.getEstablecimientos().getZonas().get(0).getIdZona().getNombreZonas());
                car.setDescripcion(ep.getId_evento().getDescripcion());
                car.setEstablecimiento(ep.getEstablecimientos().getN_establecimiento());
                car.setDireccion(ep.getEstablecimientos().getDireccion());
                car.setBoletosDisponibles(ep.getEstablecimientos().getZonas().get(0).getCapacidadZona());
                car.setPrecio(ep.getEstablecimientos().getZonas().get(0).getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad * ep.getEstablecimientos().getZonas().get(0).getPrecio());
                listaCarrito.add(car);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("contador", listaCarrito.size());

                request.getRequestDispatcher("VentaDeBoletos/carrito.jsp").forward(request, response);
                break;
            case "ActualizarCantidad":
                //TOMAMOS LOS VALORES PARA HACER EL CAMBIO Y QUE PODAMOS CAMBIAR LA CANTIDAD DE PRODUCTOS A COMPRAR Y QUE NOS VAYA HACIENDO EL CALCULO AL MISMO TIEMPO
                idZE = Integer.parseInt(request.getParameter("idZE"));
                idEP = Integer.parseInt(request.getParameter("idEP"));
                int cant = Integer.parseInt(request.getParameter("Cantidad"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    //VERIFICAMOS QUE LOS DATOS QUE ESTAMOS EDITANDO SEAN CORRECTOS 
                    if (listaCarrito.get(i).getIdZonaEstable() == idZE && listaCarrito.get(i).getIdEventoProgra() == idEP) {
                        listaCarrito.get(i).setCantidad(cant);
                        double subT = listaCarrito.get(i).getPrecio() * cant;
                        listaCarrito.get(i).setSubTotal(subT);
                    }
                }
                break;
            case "GenerarCompra":
                //AQUI SI ME ENCHIBOLE
                boolean compra = false;
                String tarjetaCredito = request.getParameter("tarjeta");
                String dui = request.getParameter("idUsuario");
                System.out.println("DUI: " + dui);
                System.out.println("TARJETA DE CREDITO: " + tarjetaCredito);
                for (Carrito x : listaCarrito) {
                    Boletos bol = new Boletos();
                    bol.setFecha_compra(new java.util.Date());
                    bol.setCantidad_boletos(x.getCantidad());

                    bol.setNum_tarjeta(tarjetaCredito);
                    Zona_Establec zoe = new Zona_Establec(x.getIdZonaEstable());
                    bol.setId_zona_establec(zoe);
                    Evento_Programado evpro = new Evento_Programado(x.getIdEventoProgra());
                    bol.setId_evento_programado(evpro);
                    Usuarios usu = new Usuarios();
                    usu.setDui(dui);
                    bol.setDui(usu);
                    if (boletoDao.comprarBoletos(bol) == true) {
                        System.out.println("GENERO LA COMPRA");
                    }

                }
                listaCarrito = new ArrayList<>();
                compra = true;
                request.getRequestDispatcher("VentaDeBoletos/CompraDeBoletos.jsp").forward(request, response);

                break;
            /*case "generarBoleto":
                this.generarBoleto(request, response);
                break;*/
            case "home":
                doPost(request, response, true);
                break;
            default:
                doPost(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response, boolean bandera) throws ServletException, IOException {
        this.evtDao = new Evento_ProDao();
        this.evenPro = new Evento_Programado();

        try {
            List<Evento_Programado> listaEventos = this.evtDao.getEventosProximos();
            request.setAttribute("listaEventosProgramados", listaEventos);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerEvento_Programado.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("VentaDeBoletos/CompraDeBoletos.jsp").forward(request, response);

    }

    /*

    private void listarEventoProgramado(HttpServletRequest request, HttpServletResponse response) throws SQLException, JMSException {
        try {
            request.setAttribute("listaEventosPro", evtDao.getEventoProgramado());
            request.getRequestDispatcher("Evt_Fecha_Especific.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Evento_Programado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/

    private void generarBoleto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        try {
            InputStream boletos = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("reportes/Boletos.jasper");
            if (boletos != null) {
                String jsonBoletos = request.getParameter("generarComprayy"); //OJO
                Gson gson = new Gson();
                List<Carrito> reportesBoletos = new ArrayList<>();
                List<Carrito> reportesBoletos2 = gson.fromJson(jsonBoletos, new TypeToken<List<Carrito>>() {
                }.getType());

                // Remover la línea que añade un elemento vacío antes de agregar los datos reales
                // reportesBoletos.add(new Carrito());
                reportesBoletos.addAll(reportesBoletos2);

                // Cambiar esta línea para cargar el informe desde el InputStream directamente
                JasperReport report = (JasperReport) JRLoader.loadObject(boletos);

                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(reportesBoletos.toArray());

                Map<String, Object> parameters = new HashMap();
                parameters.put("ds", ds);
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=Boletos.pdf");
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);

                out.flush();
                out.close();
            } else {
                response.setContentType("text/plain");
                out.println("no se pudo generar el reporte");
                out.println("esto puede deberse a que la lista de datos no fue recibida o el archivo plantilla del reporte no se ha encontrado");
                out.println("contacte a soporte");
            }
        } catch (Exception e) {
            response.setContentType("text/plain");
            out.print("ocurrió un error al intentar generar el reporte:" + e.getMessage());
            e.printStackTrace();
        }
    }

}
