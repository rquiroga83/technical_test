/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.common.activos.dto;

import com.asd.api.persistencia.entidades.Persona;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Dto con la informacion de personas
 *
 * @author andres.quiroga@ingeneo.com.co
 * @version 1.0
 */
@XmlRootElement
public class PersonasResponseDto {
    
    @XmlElementWrapper(name="personas")
    @XmlElement(name="persona")
    List<Persona> personas;
    
    ResultDto result;
 
    public List<Persona> getPersonas() {
        return personas;
    }
 
    public void setActivo(List<Persona> personas) {
        this.personas = personas;
    }
 
    public ResultDto getResult() {
        return result;
    }
 
    public void setResult(ResultDto result) {
        this.result = result;
    }
    
}
