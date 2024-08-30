<%-- 
    Document   : ReporteTotalVentaEventosZonas
    Created on : 7 ene. 2024, 16:52:30
    Author     : nelki
--%>

<%@page import="com.ues.edu.conexion.Conexion"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>

<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.view.JasperViewer" %>
<%@ page import="javax.servlet.ServletResponse" %>




<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte total de ventas de eventos por zona</title>
    </head>
    <body>
        <%
            System.setProperty("java.awt.headless", "false");
            Conexion conexion = new Conexion();
            File reporfile = new File(application.getRealPath("reportes/TotalVentaEventosZonas.jasper"));
            Map parameter = new HashMap();
            byte[] bytes = JasperRunManager.runReportToPdf(reporfile.getPath(), parameter, conexion.getConexion());
            response.setContentType("application.pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outpouststream = response.getOutputStream();
            outpouststream.write(bytes, 0, bytes.length);
            outpouststream.flush();
            outpouststream.close();
        %>
        

    
    </body>
</html>
