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
    public static final int ERROR_CODE = 500;
    public static final int SUCESS_CODE = 200;
    public static final int NO_RESULT_CODE = 404;
    public static final int MISSING_DATA_CODE = 400;
 
}
