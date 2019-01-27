/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.ejb.activos.impl;
 
import com.asd.api.persistencia.constantes.JpaConstantes;
import com.asd.api.ejb.activos.ActivosBeanLocal;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
/**
 * Implementacion bean de sesion que brinda servicios sobre los activos
 * @author rquiroga83@gmail.com
 * @version 1.0
 */
@Stateless
public class ActivosBean implements ActivosBeanLocal  {
    
    private static final Logger LOGGER = Logger.getLogger(ActivosBean.class.getName());
 
    @PersistenceContext(unitName = JpaConstantes.NOMBRE_UNIDAD_PERSISTENCIA)
    private EntityManager em;
 
    
    
 
    
}