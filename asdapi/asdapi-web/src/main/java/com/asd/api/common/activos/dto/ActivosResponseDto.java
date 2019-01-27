/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.common.activos.dto;

import com.asd.api.persistencia.entidades.ActivoFijo;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Dto con la informacion del activo
 *
 * @author andres.quiroga@ingeneo.com.co
 * @version 1.0
 */

@XmlRootElement
public class ActivosResponseDto {
    
    @XmlElementWrapper(name="activosFijos")
    @XmlElement(name="activoFijo")
    List<ActivoFijo> activosFijos;
    
    ResultDto result;
 
    public List<ActivoFijo> getActivosFijos() {
        return activosFijos;
    }
 
    public void setActivo(List<ActivoFijo> activos) {
        this.activosFijos = activos;
    }
 
    public ResultDto getResult() {
        return result;
    }
 
    public void setResult(ResultDto result) {
        this.result = result;
    }
    
    
}
