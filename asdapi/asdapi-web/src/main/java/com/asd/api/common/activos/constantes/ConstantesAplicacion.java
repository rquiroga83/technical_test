/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.common.activos.constantes;

import java.util.logging.Logger;

/**
 * Clase que contine contantes generales del programa
 *
 * @author rquiroga83@gmail.com
 * @version 1.0
 */
public class ConstantesAplicacion {
    
    private ConstantesAplicacion() { 
    } 
 
    private static final Logger LOGGER = Logger.getLogger(ConstantesAplicacion.class.getName()); 
 
 
    /** 
     * Constantes de aplicacion 
     */ 
    public static final int ERROR_CODE = -1; 
    public static final int SUCESS_CODE = 0; 
    public static final int ID_TIMER_CNFIG_PROPERTY = 6; 
    public static final int ID_TITULO_INIT_PARAM = 3; 
    public static final int ID_CONTENT_INIT_PARAM = 4; 
    public static final int ID_LOGOUT_TIME = 5; 

}
