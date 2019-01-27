/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.ejb.i18n;

import javax.ejb.Local;

/**
 * Inteface local  de servicio de internacionalizacion
 * @author rquiroga83@gmail.com
 * @version 1.0
 */
@Local
public interface I18nLocal {

    /**
     * Funcion que obtiene mensajes de internacionalizacion
     * @param key
     * @return 
     */
    public String getMessage(String key);
    
}
