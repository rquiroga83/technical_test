/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.ejb.activos;
 
import com.asd.api.common.activos.dto.ActivosResponseDto;
import javax.ejb.Local;
 
 
/**
 * interface local de bean de sesion que brinda servicios sobre los activos
 * @author rquiroga83@gmail.com
 * @version 1.0
 */
@Local
public interface ActivosBeanLocal {
    
 /**
     * Funcion que extrae listado completo de activos
     * @return ActivosResponseDto
     */
    public ActivosResponseDto obtenerActivos();  
}