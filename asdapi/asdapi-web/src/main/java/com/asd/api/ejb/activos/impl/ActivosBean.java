/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.ejb.activos.impl;

import com.asd.api.common.activos.constantes.ConstantesAplicacion;
import com.asd.api.common.activos.dto.ActivosResponseDto;
import com.asd.api.common.activos.dto.AreasResponseDto;
import com.asd.api.common.activos.dto.PersonasResponseDto;
import com.asd.api.common.activos.dto.ResultDto;
import com.asd.api.persistencia.constantes.JpaConstantes;
import com.asd.api.ejb.activos.ActivosBeanLocal;
import com.asd.api.persistencia.entidades.ActivoFijo;
import com.asd.api.persistencia.entidades.Area;
import com.asd.api.persistencia.entidades.EstadoActual;
import com.asd.api.persistencia.entidades.Persona;
import com.asd.api.persistencia.entidades.Tipo;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
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

    /**
     * Implementacion de funcion que extrae listado completo de activos
     *
     * @return ActivosResponseDto
     */
    @Override
    public ActivosResponseDto obtenerActivos() {

        ActivosResponseDto activosResponseDto = new ActivosResponseDto();

        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de activos");
            Query q = em.createNamedQuery("ActivoFijo.findAll", ActivoFijo.class);

            List<ActivoFijo> activosList = q.getResultList();

            LOGGER.log(Level.INFO, "Registros consultados: {0}", activosList.size());

            if (activosList.size() > 0) {
                /* Se llena Dto de respuesta */
                activosResponseDto.setActivo(activosList);
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            } else {
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
     *
     * @param serial
     * @return ActivosResponseDto
     */
    @Override
    public ActivosResponseDto obtenerActivosSerial(String serial) {
        ActivosResponseDto activosResponseDto = new ActivosResponseDto();

        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de activos por serial");
            Query q = em.createNamedQuery("ActivoFijo.findBySerial", ActivoFijo.class)
                    .setParameter("serial", serial);

            List<ActivoFijo> activosList = q.getResultList();

            LOGGER.log(Level.INFO, "Registros consultados: {0}", activosList.size());

            if (activosList.size() > 0) {
                /* Se llena Dto de respuesta */
                activosResponseDto.setActivo(activosList);
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            } else {
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
     *
     * @param tipo
     * @return ActivosResponseDto
     */
    @Override
    public ActivosResponseDto obtenerActivosIdTipo(Integer tipo) {
        ActivosResponseDto activosResponseDto = new ActivosResponseDto();

        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de activos por tipo");
            Query q = em.createNamedQuery("ActivoFijo.findByTipo", ActivoFijo.class)
                    .setParameter("idTipo", tipo);

            List<ActivoFijo> activosList = q.getResultList();

            LOGGER.log(Level.INFO, "Registros consultados: {0}", activosList.size());

            if (activosList.size() > 0) {
                /* Se llena Dto de respuesta */
                activosResponseDto.setActivo(activosList);
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            } else {
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
     *
     * @param fechaCompra
     * @return ActivosResponseDto
     */
    @Override
    public ActivosResponseDto obtenerActivosFechaCompra(Date fechaCompra) {
        ActivosResponseDto activosResponseDto = new ActivosResponseDto();

        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de activos por fecha de compra");
            Query q = em.createNamedQuery("ActivoFijo.findByFechaCompra", ActivoFijo.class)
                    .setParameter("fechaCompra", fechaCompra);

            List<ActivoFijo> activosList = q.getResultList();

            LOGGER.log(Level.INFO, "Registros consultados: {0}", activosList.size());

            if (activosList.size() > 0) {
                /* Se llena Dto de respuesta */
                activosResponseDto.setActivo(activosList);
                activosResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            } else {
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
     * Implementacion de funcion que crea un registro en la tabla de activos
     * fijos
     *
     * @param activoFijo
     */
    @Override
    public void crearActivoFijo(ActivoFijo activoFijo) {
        em.persist(activoFijo);
    }

    /**
     * Implementacion de funcion que busca tipo de activo por Id
     *
     * @param id
     * @return
     */
    @Override
    public Tipo getTipoActivoById(Integer id) {
        try {
            Query q = em.createNamedQuery("Tipo.findById", Tipo.class)
                    .setParameter("id", id);

            Tipo tipo = (Tipo) q.getSingleResult();

            return tipo;
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "Tipo no encontrado {0}", e);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error general en la consulta de tipo {0}", e);
        }

        return null;
    }

    /**
     * Implementacion de funcion que busca tipo de activo por Id
     *
     * @param id
     * @return
     */
    @Override
    public EstadoActual getEstadoActualActivoById(Integer id) {
        try {
            Query q = em.createNamedQuery("EstadoActual.findById", EstadoActual.class)
                    .setParameter("id", id);

            EstadoActual estadoActual = (EstadoActual) q.getSingleResult();

            return estadoActual;
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "Estado no encontrado {0}", e);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error general en la consulta del estado {0}", e);
        }

        return null;
    }
    
    /** 
     * Implementacion de funcion que actualiza un registro en la tabla de activos fijos 
     * @param activoFijo 
     * @return  
     */ 
    @Override 
    public boolean actualizarActivoFijo(ActivoFijo activoFijo) { 
        ActivoFijo m_activoFijo = em.find(ActivoFijo.class, activoFijo.getId()); 
         
        if(m_activoFijo == null){ 
            return false; 
        } 
         
        m_activoFijo.setFechaBaja(activoFijo.getFechaBaja()); 
        m_activoFijo.setNumeroInternoInventario(activoFijo.getNumeroInternoInventario()); 
         
        em.merge(m_activoFijo); 
         
        return true; 
    } 
    
    
    /**
     * Implementacion de funcion que extrae listado completo de areas
     *
     * @return ActivosResponseDto
     */
    @Override
    public AreasResponseDto obtenerAreas() {

        AreasResponseDto areasResponseDto = new AreasResponseDto();

        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de areas");
            Query q = em.createNamedQuery("Area.findAll", Area.class);

            List<Area> areasList = q.getResultList();

            LOGGER.log(Level.INFO, "Registros consultados: {0}", areasList.size());

            if (areasList.size() > 0) {
                /* Se llena Dto de respuesta */
                areasResponseDto.setActivo(areasList);
                areasResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            } else {
                areasResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));
            }
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "No existen activos {0}", e);
            areasResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error general en la consulta del activo {0}", e);
            areasResponseDto.setResult(new ResultDto(ConstantesAplicacion.ERROR_CODE, "Error general en la consulta del activo "));
        }

        return areasResponseDto;
    }
    
    
    /**
     * Implementacion de funcion que extrae listado completo de personas
     *
     * @return ActivosResponseDto
     */
    @Override
    public PersonasResponseDto obtenerPersonas() {

        PersonasResponseDto personasResponseDto = new PersonasResponseDto();

        try {
            /* Se realiza la consulta de todos los activos */
            LOGGER.log(Level.INFO, "Se realiza consulta de personas");
            Query q = em.createNamedQuery("Persona.findAll", Persona.class);

            List<Persona> personasList = q.getResultList();

            LOGGER.log(Level.INFO, "Registros consultados: {0}", personasList.size());

            if (personasList.size() > 0) {
                /* Se llena Dto de respuesta */
                personasResponseDto.setActivo(personasList);
                personasResponseDto.setResult(new ResultDto(ConstantesAplicacion.SUCESS_CODE, "Consulta realizada correctamente"));
            } else {
                personasResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));
            }
        } catch (NoResultException e) {
            LOGGER.log(Level.INFO, "No existen activos {0}", e);
            personasResponseDto.setResult(new ResultDto(ConstantesAplicacion.NO_RESULT_CODE, "No se generaron resultados"));

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error general en la consulta del activo {0}", e);
            personasResponseDto.setResult(new ResultDto(ConstantesAplicacion.ERROR_CODE, "Error general en la consulta del activo "));
        }

        return personasResponseDto;
    }

    
}