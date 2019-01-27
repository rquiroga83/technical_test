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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public void estableceRespuesta(int statusCode){
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

}
