/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.ejb.activos;
 
import com.asd.api.common.activos.dto.ActivosResponseDto;
import com.asd.api.common.activos.dto.AreasResponseDto;
import com.asd.api.common.activos.dto.PersonasResponseDto;
import com.asd.api.persistencia.entidades.ActivoFijo; 
import com.asd.api.persistencia.entidades.EstadoActual; 
import com.asd.api.persistencia.entidades.Tipo; 
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
    
    
    /** 
     * Funcion que crea un registro en la tabla de activos fijos 
     * @param activoFijo  
     */ 
    public void crearActivoFijo(ActivoFijo activoFijo); 
 
     
    /** 
     * Funcion que busca tipo de activo por Id 
     * @param id 
     * @return  
     */ 
    public Tipo getTipoActivoById(Integer id); 
 
     
    /** 
     * Funcion que busca tipo de activo por Id 
     * @param id 
     * @return  
     */ 
    public EstadoActual getEstadoActualActivoById(Integer id); 
    
    /** 
     * Funcion que actualiza un registro en la tabla de activos fijos 
     * @param activoFijo 
     */ 
    public boolean actualizarActivoFijo(ActivoFijo activoFijo); 
    
    /**
     * Funcion que extrae listado completo de areas
     * @return ActivosResponseDto
     */
    public AreasResponseDto obtenerAreas();
    
    
    /**
     * Funcion que extrae listado completo de areas
     * @return ActivosResponseDto
     */
    public PersonasResponseDto obtenerPersonas();
    
}