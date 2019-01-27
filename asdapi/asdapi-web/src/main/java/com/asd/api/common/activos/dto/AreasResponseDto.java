/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.common.activos.dto;

import com.asd.api.persistencia.entidades.Area;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Dto con la informacion de areas
 *
 * @author andres.quiroga@ingeneo.com.co
 * @version 1.0
 */
@XmlRootElement
public class AreasResponseDto {
    
    @XmlElementWrapper(name="areas")
    @XmlElement(name="area")
    List<Area> areas;
    
    ResultDto result;
 
    public List<Area> getAreas() {
        return areas;
    }
 
    public void setActivo(List<Area> areas) {
        this.areas = areas;
    }
 
    public ResultDto getResult() {
        return result;
    }
 
    public void setResult(ResultDto result) {
        this.result = result;
    }
}
