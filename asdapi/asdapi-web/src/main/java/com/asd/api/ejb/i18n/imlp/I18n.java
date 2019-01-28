/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.ejb.i18n.imlp;

import com.asd.api.common.activos.constantes.ConstantesAplicacion;
import com.asd.api.ejb.i18n.I18nLocal;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.Stateless;

/**
 * Implementacion de servicio de internacionalizacion
 * @author rquiroga83@gmail.com
 * @version 1.0
 */
@Stateless
public class I18n implements I18nLocal {
    
    @Override
    public String getMessage(String key){

        Locale currentLocale = new Locale(ConstantesAplicacion.LANGUAJE, ConstantesAplicacion.COUNTRY);
        ResourceBundle messages = ResourceBundle.getBundle("com.asd.api.common.messages.Msg", currentLocale);
        return messages.getString(key);
    }
    
}
