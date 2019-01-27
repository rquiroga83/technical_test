/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.rest.activos.servicios;

import com.asd.api.common.activos.constantes.ConstantesAplicacion;
import com.asd.api.common.activos.dto.ActivosResponseDto;
import com.asd.api.common.activos.dto.ResultDto;
import com.asd.api.ejb.activos.ActivosBeanLocal;
import com.asd.api.persistencia.entidades.ActivoFijo;
import com.asd.api.persistencia.entidades.EstadoActual;
import com.asd.api.persistencia.entidades.Tipo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa front de servicios web
 *
 * @author rquiroga83@gmail.com
 * @version 1.0
 */
@Path("servicioActivos")
@RequestScoped
public class ServicioActivos {

    private static final Logger LOGGER = Logger.getLogger(ServicioActivos.class.getName());

    @Context
    private HttpServletResponse httpServletResponse;

    /**
     * Inyeccion de EJB ActivosBeanLocal para ser utilizado en la capa de
     * servicios
     */
    @EJB
    private ActivosBeanLocal activosBean;

    /**
     * Variable utilizada para formato de fechas
     */
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Crea una neva instancia de ActivosService
     */
    public ServicioActivos() {
    }

    /**
     * Funcion que establece los headers del servicio.
     *
     *
     */
    public void establecerCabeceras() {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
    }

    /**
     * Funcion que establece los codigos de error
     * estableceRespuesta(activosResponse.getResult().getResultCode());
     */
    public void estableceRespuesta(int statusCode) {
        httpServletResponse.setStatus(statusCode);
        try {
            httpServletResponse.flushBuffer();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al establecer repuesta {0}", e);
        }
    }

    /**
     * servicio REST que extrae listado completo de activos
     *
     * @return ActivosResponseDto
     */
    @Path("/obtenerActivosFijos")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ActivosResponseDto obtenerActivos() {

        establecerCabeceras();
        ActivosResponseDto activosResponse = activosBean.obtenerActivos();
        estableceRespuesta(activosResponse.getResult().getResultCode());

        return activosResponse;
    }

    /**
     * servicio REST que busca activos por serial
     *
     * @param serial
     * @return ActivosResponseDto
     */
    @Path("/obtenerActivosFijosSerial")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ActivosResponseDto obtenerActivosSerial(@QueryParam("serial") String serial) {

        establecerCabeceras();
        ActivosResponseDto activosResponse = activosBean.obtenerActivosSerial(serial);
        estableceRespuesta(activosResponse.getResult().getResultCode());

        return activosResponse;
    }

    /**
     * servicio REST que busca activos por id de tipo de activo
     *
     * @param idTipo
     * @return ActivosResponseDto
     */
    @Path("/obtenerActivosFijosIdTipo")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ActivosResponseDto obtenerActivosIdTipo(@QueryParam("idTipo") Integer idTipo) {

        establecerCabeceras();
        ActivosResponseDto activosResponse = activosBean.obtenerActivosIdTipo(idTipo);
        estableceRespuesta(activosResponse.getResult().getResultCode());

        return activosResponse;
    }

    /**
     * servicio REST que busca activos por fecha de compra
     *
     * @param fechaCompra
     * @return ActivosResponseDto
     */
    @Path("/obtenerActivosFijosFechaCompra")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ActivosResponseDto obtenerActivosFechaCompra(@QueryParam("fechaCompra") String fechaCompra) {

        establecerCabeceras();
        try {
            Date dFechaCompra = new java.sql.Date(df.parse(fechaCompra).getTime());
            ActivosResponseDto activosResponse = activosBean.obtenerActivosFechaCompra(dFechaCompra);
            estableceRespuesta(activosResponse.getResult().getResultCode());
            return activosResponse;
        } catch (final ParseException ex) {
            // Si se presenta error en la conversion de la fecha
            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.ERROR_CODE, "Formato de fecha incorrecto! intente de nuevo con el formato yyyy-MM-dd"));
            estableceRespuesta(ConstantesAplicacion.ERROR_CODE);
            return activosResponseDto;
        }
    }

    /**
     * Servicio REST encargado de la creacion de activos fijos
     *
     * @param nombre
     * @param descripcion
     * @param serial
     * @param numero_interno_inventario
     * @param peso
     * @param alto
     * @param ancho
     * @param largo
     * @param valor_compra
     * @param fechaCompra
     * @param fechaBaja
     * @param tipoId
     * @param estadoActualId
     * @param servletResponse
     * @return
     */
    @POST
    @Path("/crearActivoFijo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ActivosResponseDto crearActivoFijo(@FormParam("nombre") String nombre,
            @FormParam("descripcion") String descripcion,
            @FormParam("serial") String serial,
            @FormParam("numero_interno_inventario") String numero_interno_inventario,
            @FormParam("peso") Double peso,
            @FormParam("alto") Double alto,
            @FormParam("ancho") Double ancho,
            @FormParam("largo") Double largo,
            @FormParam("valor_compra") long valor_compra,
            @FormParam("fecha_compra") String fechaCompra,
            @FormParam("fecha_baja") String fechaBaja,
            @FormParam("tipo_id") Integer tipoId,
            @FormParam("estado_actual_id") Integer estadoActualId,
            @Context HttpServletResponse servletResponse) {

        establecerCabeceras();
        try {
            Date dFechaCompra = new java.sql.Date(df.parse(fechaCompra).getTime());
            Date dFechaBaja = new java.sql.Date(df.parse(fechaBaja).getTime());

            if(dFechaBaja.before(dFechaCompra)){
                ActivosResponseDto activosResponseDto = new ActivosResponseDto();
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "La fecha de baja no puede ser inferior a la fecha de compra"));
                estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
                return activosResponseDto;
            }
            
            if (tipoId == null) {
                ActivosResponseDto activosResponseDto = new ActivosResponseDto();
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Tipo no puede ser nulo"));
                estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
                return activosResponseDto;
            }

            if (estadoActualId == null) {
                ActivosResponseDto activosResponseDto = new ActivosResponseDto();
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Estado no puede ser nulo"));
                estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
                return activosResponseDto;
            }

            Tipo tipo = activosBean.getTipoActivoById(tipoId);
            EstadoActual estadoActual = activosBean.getEstadoActualActivoById(estadoActualId);

            if (tipo == null) {
                ActivosResponseDto activosResponseDto = new ActivosResponseDto();
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Tipo de activo no existe en la base de datos"));
                estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
                return activosResponseDto;
            } else if (estadoActual == null) {
                ActivosResponseDto activosResponseDto = new ActivosResponseDto();
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Estado de activo no existe en la base de datos"));
                estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
                return activosResponseDto;
            }

            ActivoFijo activoFijo = new ActivoFijo();
            activoFijo.setNombre(nombre);
            activoFijo.setDescripcion(descripcion);
            activoFijo.setNumeroInternoInventario(numero_interno_inventario);
            activoFijo.setSerial(serial);
            activoFijo.setPeso(peso);
            activoFijo.setAlto(alto);
            activoFijo.setAncho(ancho);
            activoFijo.setLargo(largo);
            activoFijo.setValorCompra(valor_compra);
            activoFijo.setFechaBaja(dFechaBaja);
            activoFijo.setFechaCompra(dFechaCompra);
            activoFijo.setTipoId(tipo);
            activoFijo.setEstadoActualId(estadoActual);

            activosBean.crearActivoFijo(activoFijo);

            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Datos actualizados correctamente"));
            return activosResponseDto;
        } catch (final ParseException ex) {
            // Si se presenta error en la conversion de la fecha 
            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Formato de fecha incorrecto! intente de nuevo con el formato yyyy-MM-dd"));
            estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
            return activosResponseDto;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error general el almacenamiento del activo {0}", e);
            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Error general el almacenamiento del activo " + e.getMessage()));
            estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
            return activosResponseDto;
        }
    }

}
