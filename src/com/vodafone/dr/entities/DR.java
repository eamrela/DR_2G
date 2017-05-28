/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.entities;

/**
 *
 * @author Admin
 */
public class DR {
    
    private String siteName;
    private String sourceHUB;
    private String sourceMTX;
    private String targetMTX;
    private String targetBSC;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        if(siteName.matches("[aA-zZ]+\\d+")){
            siteName = siteName.replaceAll("\\D+", "");
        }
        this.siteName = siteName;
    }

    public String getSourceHUB() {
        return sourceHUB;
    }

    public void setSourceHUB(String sourceHUB) {
        this.sourceHUB = sourceHUB;
    }

    public String getSourceMTX() {
        return sourceMTX;
    }

    public void setSourceMTX(String sourceMTX) {
        this.sourceMTX = sourceMTX;
    }

    public String getTargetMTX() {
        return targetMTX;
    }

    public void setTargetMTX(String targetMTX) {
        this.targetMTX = targetMTX;
    }

    public String getTargetBSC() {
        return targetBSC;
    }

    public void setTargetBSC(String targetBSC) {
        this.targetBSC = targetBSC;
    }
    
    
}
