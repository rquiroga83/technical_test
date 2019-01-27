/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.ejb.activos;
 
import com.asd.api.common.activos.dto.ActivosResponseDto;
import java.util.Date;
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
    
        /**
     * Funcion que obtiene un activo por setial
     * @param serial
     * @return ActivosResponseDto
     */
    public ActivosResponseDto obtenerActivosSerial(String serial);
 
    
    /**
     * Funcion que obtiene un activos por tipo
     * @param tipo
     * @return ActivosResponseDto
     */
    public ActivosResponseDto obtenerActivosIdTipo(Integer tipo);
 
    
    /**
     * Funcion que obtiene un activos por fecha de compra
     * @param tipo
     * @return ActivosResponseDto
     */
    public ActivosResponseDto obtenerActivosFechaCompra(Date tipo);
    
}