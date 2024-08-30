$(document).ready(function () {
    //tr POR QUE DENTRO DE LA ETIQUETA tr esta el boton
    $("tr #btnDelete").click(function () {
        //CAPTURAREMOS EL VALOR DE LA FILA SELECCIONADA
        //RECORDEMOS QUE TOMAMOS LOS DOS ID PARA QUE EL REGISTRO A ELIMINAR SEA EL CORRECTO
        var idZE = $(this).parent().find("#idZonaE").val();
        var idEP = $(this).parent().find("#idEvenP").val();
        //ALERTA DE LIBRERIA SWEET ALERT
        //CREAMOS UN METODO QUE RECIBE DOS PARAMETROS
        //Y SI LE DAMOS A BORRA SE BORRARA, DE CASO CONTRARIO NO HARA NADA
        Swal.fire({
            title: "¿Desea eliminar este item?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Si, eliminar!"
        }).then((result) => {
            if (result.isConfirmed) {
                eliminar(idZE, idEP);
                Swal.fire({
                    title: "Eliminado!",
                    text: "El item ha sido eliminado.",
                    icon: "success"
                }).then((willDelete) => {
                    if (willDelete) {
                        location.href = "ControllerBoletosEstablecimiento?accion=Carrito";
                    }
                });
            }
        });

    });

        
        
        

    //METODO ELIMINAR
    function eliminar(idZE, idEP) {
        //GUARDAMOS LA DIRECCION AL CONTROLADOR
        //var url="../ControllerBoletos?accion=Delete";
        var url = "ControllerBoletosEstablecimiento?accion=Delete";
        //IMPLEMENTAMOS UN METODO AJAX, 
        //ENVIAMOS POR type: GET por que este metodo es el que nos redigire al processrequest Y NO EL METODO POST   
        $.ajax({
            type: 'GET',
            url: url,
            data: "idZE=" + idZE + "&idEP=" + idEP,
            success: function (data, textStatus, jqXHR) {
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
    
    
  



    $("td #Cantidad").click(function () {
        //CREAMOS VARIABLES PARA TOMAR LOS ID
        var idZE = $(this).parent().find("#idze").val();
        var idEP = $(this).parent().find("#idep").val();
        var cantidad = $(this).parent().find("#Cantidad").val();
        var url = "ControllerBoletosEstablecimiento?accion=ActualizarCantidad";
        //ENVIAMOS POR type: GET por que este metodo es el que nos redigire al processrequest Y NO EL METODO POST   
        $.ajax({
            type: 'GET',
            url: url,
            data: "idZE=" + idZE + "&idEP=" + idEP + "&Cantidad=" + cantidad,
            success: function (data, textStatus, jqXHR) {
                location.href = "ControllerBoletosEstablecimiento?accion=Carrito";
            }
        });
    });

    //VALIDACION DEL CAMPO TARJETA
    
    //VALIDACION PARA QUE NO ACEPTE LETRAS
    document.getElementById('tarjeta').addEventListener('keypress', function (event) {
    let input = event.target;
    let charCode = event.charCode;

    // Permitir solo números y limitar a 19 caracteres
    if ((charCode < 48 || charCode > 57) || input.value.replace(/\s/g, '').length >= 16) {
        event.preventDefault();
    }
});
//VALIDACION PARA QUE HAGA SECCIONES DE 4 NUMEROS COMO EN LAS TARJETAS REALES
document.getElementById('tarjeta').addEventListener('input', function (event) {
    let input = event.target;
    let value = input.value.replace(/\s/g, ''); // Eliminar espacios en blanco
    let formattedValue = '';

    // Limitar la longitud máxima a 19 caracteres
    value = value.substring(0, 16);

    for (let i = 0; i < value.length; i++) {
        if (i > 0 && i % 4 === 0) {
            formattedValue += ' '; // Agregar espacio cada 4 caracteres
        }
        formattedValue += value[i];
    }

    input.value = formattedValue;
});

//function validarFormulario() {
//    var tarjeta=document.getElementById('tarjeta').value;
//        
//        if (tarjeta==='' || tarjeta.length!==16) {
//            Swal.fire("Ingrese un numero de tarjeta!!!");
//            return false;
//        }
//        Swal.fire("Compra realizada con exito");
//        return true;
//}


});
function validarFormulario() {
    var tarjeta = document.getElementById('tarjeta').value;

    if (tarjeta==='' || tarjeta.length!==19) {
            Swal.fire("Ingrese un numero de tarjeta!!!");
            return false;
        }
        Swal.fire("Compra realizada con exito");
        return true;
}
function reporte(accion){
    $.get('../ControllerBoletosEstablecimiento?accion=listar',function(r){
        if (r) {
            $('#accion').val(accion);
            $('#generarCompra').val(JSON.stringify(r));
            $('#frmReporte').submit();
        }else{
            alert('El reporte no se pudo generar debido a un error: '+r);
        }
    });
}