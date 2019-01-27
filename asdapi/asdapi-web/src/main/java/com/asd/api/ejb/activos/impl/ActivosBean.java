/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.ejb.activos.impl;
 
import com.asd.api.common.activos.constantes.ConstantesAplicacion;
import com.asd.api.common.activos.dto.ActivosResponseDto;
import com.asd.api.common.activos.dto.ResultDto;
import com.asd.api.persistencia.constantes.JpaConstantes;
import com.asd.api.ejb.activos.ActivosBeanLocal;
import com.asd.api.persistencia.entidades.ActivoFijo;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
 
    
     /**
     * Implementacion de funcion que extrae listado completo de activos
     * @return ActivosResponseDto
     */
    @Override
    public ActivosResponseDto obtenerActivos(){
        
        ActivosResponseDto activosResponseDto = new ActivosResponseDto();
        
        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de activos");
            Query q = em.createNamedQuery("ActivoFijo.findAll", ActivoFijo.class);
            
            List<ActivoFijo> activosList = q.getResultList();
            
            LOGGER.log(Level.INFO, "Registros consultados: {0}", activosList.size());
            
            if(activosList.size() > 0) {
                /* Se llena Dto de respuesta */
                activosResponseDto.setActivo(activosList);
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            }
            else {
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));
            }
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "No existen activos {0}", e);
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));
 
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error general en la consulta del activo {0}", e);
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.ERROR_CODE, "Error general en la consulta del activo "));
        }
        
        return activosResponseDto;
    }
    
    
    
    /**
     * Implementacion de funcion que obtiene un activo por serial
     * @param serial
     * @return ActivosResponseDto
     */
    @Override
    public ActivosResponseDto obtenerActivosSerial(String serial){
        ActivosResponseDto activosResponseDto = new ActivosResponseDto();
        
        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de activos por serial");
            Query q = em.createNamedQuery("ActivoFijo.findBySerial", ActivoFijo.class)
                    .setParameter("serial", serial);
            
            List<ActivoFijo> activosList = q.getResultList();
            
            LOGGER.log(Level.INFO, "Registros consultados: {0}", activosList.size());
            
            if(activosList.size() > 0) {
                /* Se llena Dto de respuesta */
                activosResponseDto.setActivo(activosList);
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            }
            else {
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));
            }
            
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "No existen activos {0}", e);
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.ERROR_CODE, "No se generaron resultados"));
 
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error general en la consulta del activo {0}", e);
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.ERROR_CODE, "Error general en la consulta del activo "));
        }
        
        return activosResponseDto;
    }
    
    
    /**
     * Implementacion de funcion que obtiene un activos por tipo
     * @param tipo
     * @return ActivosResponseDto
     */
    @Override
    public ActivosResponseDto obtenerActivosIdTipo(Integer tipo){
        ActivosResponseDto activosResponseDto = new ActivosResponseDto();
        
        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de activos por tipo");
            Query q = em.createNamedQuery("ActivoFijo.findByTipo", ActivoFijo.class)
                    .setParameter("idTipo", tipo);
            
            List<ActivoFijo> activosList = q.getResultList();
            
            LOGGER.log(Level.INFO, "Registros consultados: {0}", activosList.size());
            
            if(activosList.size() > 0) {
                /* Se llena Dto de respuesta */
                activosResponseDto.setActivo(activosList);
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            }
            else {
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));
            }
            
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "No existen activos {0}", e);
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));
 
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error general en la consulta del activo {0}", e);
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.ERROR_CODE, "Error general en la consulta del activo " + e.getMessage()));
        }
        
        return activosResponseDto;
    } 
    
    
    /**
     * Implementacion de funcion que obtiene un activos por fecha de compra
     * @param fechaCompra
     * @return ActivosResponseDto
     */
    @Override
    public ActivosResponseDto obtenerActivosFechaCompra(Date fechaCompra){
        ActivosResponseDto activosResponseDto = new ActivosResponseDto();
        
        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de activos por fecha de compra");
            Query q = em.createNamedQuery("ActivoFijo.findByFechaCompra", ActivoFijo.class)
                    .setParameter("fechaCompra", fechaCompra);
            
            List<ActivoFijo> activosList = q.getResultList();
            
            LOGGER.log(Level.INFO, "Registros consultados: {0}", activosList.size());
            
            if(activosList.size() > 0) {
                /* Se llena Dto de respuesta */
                activosResponseDto.setActivo(activosList);
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            }
            else {
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));
            }
            
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "No existen activos {0}", e);
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));
 
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error general en la consulta del activo {0}", e);
            activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.ERROR_CODE, "Error general en la consulta del activo "));
        }
        
        return activosResponseDto;
    }  
 
    
}