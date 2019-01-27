/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.rest.activos.servicios;
 
import com.asd.api.ejb.activos.ActivosBeanLocal;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
 
/**
 * Clase que implementa front de servicios web
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
     * Inyeccion de EJB ActivosBeanLocal para ser utilizado en la capa de servicios
     */
    @EJB
    private ActivosBeanLocal activosBean;
    
    
    /**
     * Crea una neva instancia de ActivosService
     */
    public ServicioActivos() {
    }
    
    
    /**
     * Funcion que establece los headers del servicio.
     *
     * @since 25/04/2017
     *
     */
    public void establecerCabeceras() {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
    }    
    
    
 
    
}