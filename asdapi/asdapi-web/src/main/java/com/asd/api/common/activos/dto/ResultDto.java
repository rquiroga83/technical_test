/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.common.activos.dto;

/**
 * Dto que contiene la informacion del resultado del llamado del servicio
 * @author rquiroga83@gmail.com
 * @version 1.0
 */
public class ResultDto {
    
    int resultCode;
    String resultMsg;

    public ResultDto() {
    }
    
    public ResultDto(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
    
    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
    
    
}
