/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.rest.activos.servicios;

import com.asd.api.anotations.Secured;
import com.asd.api.common.activos.constantes.ConstantesAplicacion;
import com.asd.api.common.activos.dto.ActivosResponseDto;
import com.asd.api.common.activos.dto.AreasResponseDto;
import com.asd.api.common.activos.dto.PersonasResponseDto;
import com.asd.api.common.activos.dto.ResultDto;
import com.asd.api.ejb.activos.ActivosBeanLocal;
import com.asd.api.persistencia.entidades.ActivoFijo;
import com.asd.api.persistencia.entidades.EstadoActual;
import com.asd.api.persistencia.entidades.Tipo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.slf4j.LoggerFactory; 

/**
 * Clase que implementa front de servicios web
 *
 * @author rquiroga83@gmail.com
 * @version 1.0
 */
@Path("servicioActivos")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ServicioActivos {

    //private static final Logger LOGGER = Logger.getLogger(ServicioActivos.class.getName()); 
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ServicioActivos.class); 

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
            LOGGER.error( "Error al establecer repuesta {0}", e);
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
    @Operation(summary = "Obtiene todos los activos",
    tags = {"activos"},
    description = "Retorna la totalidad de activos de la base de datos",
    responses = {
            @ApiResponse(description = "Lista de activos", content = @Content(
                    schema = @Schema(implementation = ActivosResponseDto.class)
            )),
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos faltantes"),
            @ApiResponse(responseCode = "404", description = "Busqueda sin resultados"),
            @ApiResponse(responseCode = "500", description = "Errores en el backend")
    })
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
    @Operation(summary = "Retorna activos por nuero de serial",
    tags = {"activos"},
    description = "Retorna activos por nuero de serial",
    responses = {
            @ApiResponse(description = "Lista de activos", content = @Content(
                    schema = @Schema(implementation = ActivosResponseDto.class)
            )),
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos faltantes"),
            @ApiResponse(responseCode = "404", description = "Busqueda sin resultados"),
            @ApiResponse(responseCode = "500", description = "Errores en el backend")
    })
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
    @Operation(summary = "Retorna activos por tipo",
    tags = {"activos"},
    description = "Retorna activos por tipo",
    responses = {
            @ApiResponse(description = "Lista de activos", content = @Content(
                    schema = @Schema(implementation = ActivosResponseDto.class)
            )),
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos faltantes"),
            @ApiResponse(responseCode = "404", description = "Busqueda sin resultados"),
            @ApiResponse(responseCode = "500", description = "Errores en el backend")
    })
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
    @Operation(summary = "Retorna activos por fecha de compra",
    tags = {"activos"},
    description = "Retorna activos por fecha de compra",
    responses = {
            @ApiResponse(description = "Lista de activos", content = @Content(
                    schema = @Schema(implementation = ActivosResponseDto.class)
            )),
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos faltantes"),
            @ApiResponse(responseCode = "404", description = "Busqueda sin resultados"),
            @ApiResponse(responseCode = "500", description = "Errores en el backend")
    })
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
    @Operation(summary = "Crea activos",
    tags = {"activos"},
    description = "Crea activos",
    responses = {
            @ApiResponse(description = "Resultado de la operacion", content = @Content(
                    schema = @Schema(implementation = ActivosResponseDto.class)
            )),
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos faltantes"),
            @ApiResponse(responseCode = "404", description = "Busqueda sin resultados"),
            @ApiResponse(responseCode = "500", description = "Errores en el backend")
    })
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

            if (dFechaBaja.before(dFechaCompra)) {
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
        } catch (ConstraintViolationException ev) {
            LOGGER.error("Error en parametros enviados parametros imcompletos " + ev); 
            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Error en parametros enviados parametros imcompletos " + ev.getMessage()));
            estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
            return activosResponseDto;

        } catch (Exception e) {
            LOGGER.error("Error general el almacenamiento del activo " + e); 
            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Error general el almacenamiento del activo " + e.getMessage()));
            estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
            return activosResponseDto;
        }
    }

    /**
     * Servicio REST encargado de la creacion de activos fijos
     *
     * @param id
     * @param numero_interno_inventario
     * @param fechaBaja
     * @param servletResponse
     * @return
     */
    @POST
    @Secured
    @Path("/actualizarActivoFijo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Actuliza el serial y fecha de baja",
    tags = {"activos"},
    description = "Actuliza el serial y fecha de baja",
    responses = {
            @ApiResponse(description = "Resultado de la operacion", content = @Content(
                    schema = @Schema(implementation = ActivosResponseDto.class)
            )),
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos faltantes"),
            @ApiResponse(responseCode = "404", description = "Busqueda sin resultados"),
            @ApiResponse(responseCode = "500", description = "Errores en el backend")
    })
    public ActivosResponseDto actualizarActivoFijo(@FormParam("id") Integer id,
            @FormParam("numero_interno_inventario") String numero_interno_inventario,
            @FormParam("fecha_baja") String fechaBaja,
            @Context HttpServletResponse servletResponse) {

        establecerCabeceras();
        try {
            Date dFechaBaja = new java.sql.Date(df.parse(fechaBaja).getTime());

            if (id == null) {
                ActivosResponseDto activosResponseDto = new ActivosResponseDto();
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "El Id no puede ser nulo"));
                estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
                return activosResponseDto;
            }

            ActivoFijo activoFijo = new ActivoFijo();
            activoFijo.setId(id);
            activoFijo.setNumeroInternoInventario(numero_interno_inventario);
            activoFijo.setFechaBaja(dFechaBaja);

            if (!activosBean.actualizarActivoFijo(activoFijo)) {
                ActivosResponseDto activosResponseDto = new ActivosResponseDto();
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "El activo a actualizar no existe"));
                estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
                return activosResponseDto;
            }

            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Datos actualizados correctamente"));
            return activosResponseDto;
        } catch (final ParseException ex) {
            // Si se presenta error en la conversion de la fecha 
            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Formato de fecha incorrecto! intente de nuevo con el formato yyyy-MM-dd"));
            estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
            return activosResponseDto;
        } catch (EJBException ev) {
            LOGGER.error("Error en parametros enviados parametros imcompletos " + ev); 
            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Error en parametros enviados parametros imcompletos " + ev.getMessage()));
            estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
            return activosResponseDto;
        } catch (Exception e) {
            LOGGER.error( "Error general el almacenamiento del activo " + e); 
            ActivosResponseDto activosResponseDto = new ActivosResponseDto();
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.MISSING_DATA_CODE, "Error general el almacenamiento del activo " + e.getMessage()));
            estableceRespuesta(ConstantesAplicacion.MISSING_DATA_CODE);
            return activosResponseDto;
        }
    }

    
    
    /**
     * servicio REST que extrae listado completo de areas
     *
     * @return ActivosResponseDto
     */
    @Path("/obtenerAreas")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene todas las areas",
    tags = {"areas"},
    description = "Obtiene todas las areas",
    responses = {
            @ApiResponse(description = "Listado de areas", content = @Content(
                    schema = @Schema(implementation = AreasResponseDto.class)
            )),
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos faltantes"),
            @ApiResponse(responseCode = "404", description = "Busqueda sin resultados"),
            @ApiResponse(responseCode = "500", description = "Errores en el backend")
    })
    public AreasResponseDto obtenerAreas() {

        establecerCabeceras();
        AreasResponseDto areasResponseDto = activosBean.obtenerAreas();
        estableceRespuesta(areasResponseDto.getResult().getResultCode());

        return areasResponseDto;
    }
    
    
    /**
     * servicio REST que extrae listado completo de personas
     *
     * @return ActivosResponseDto
     */
    @Path("/obtenerPersonas")
    @GET
    @Secured
    @Produces({MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene todas las personas",
    tags = {"areas"},
    description = "Obtiene todas las personas",
    responses = {
            @ApiResponse(description = "Listado de areas", content = @Content(
                    schema = @Schema(implementation = PersonasResponseDto.class)
            )),
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos faltantes"),
            @ApiResponse(responseCode = "404", description = "Busqueda sin resultados"),
            @ApiResponse(responseCode = "500", description = "Errores en el backend")
    })
    public PersonasResponseDto obtenerPersonas() {

        establecerCabeceras();
        PersonasResponseDto personasResponseDto = activosBean.obtenerPersonas();
        estableceRespuesta(personasResponseDto.getResult().getResultCode());

        return personasResponseDto;
    }
    
}
