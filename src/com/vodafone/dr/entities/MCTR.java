/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.entities;

/**
 *
 * @author Amr
 */
public class MCTR {
    private String mctr;
    private String alternative_mctr;
    private String maxtrx;
    private String txpwr;
    private String pao;
    private String mixedmode;
    private String ipm;
    private String maxpwr;

    public String getMctr() {
        return mctr;
    }

    public void setMctr(String mctr) {
        this.mctr = mctr;
    }

    public String getMaxtrx() {
        return maxtrx;
    }

    public void setMaxtrx(String maxtrx) {
        this.maxtrx = maxtrx;
    }

    public String getTxpwr() {
        return txpwr;
    }

    public void setTxpwr(String txpwr) {
        this.txpwr = txpwr;
    }

    public String getPao() {
        return pao;
    }

    public void setPao(String pao) {
        this.pao = pao;
    }

    public String getMixedmode() {
        return mixedmode;
    }

    public void setMixedmode(String mixedmode) {
        this.mixedmode = mixedmode;
    }

    public String getIpm() {
        return ipm;
    }

    public void setIpm(String ipm) {
        this.ipm = ipm;
    }

    public String getMaxpwr() {
        return maxpwr;
    }

    public void setMaxpwr(String maxpwr) {
        this.maxpwr = maxpwr;
    }

    public String getAlternative_mctr() {
        return alternative_mctr;
    }

    public void setAlternative_mctr(String alternative_mctr) {
        this.alternative_mctr = "RXOMCTR-"+alternative_mctr+"-"+mctr.split("-")[2];
    }
    
    public String printout(){
        if(alternative_mctr==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternative_mctr);
        }
    }
    
    private String getPrintOut(){
        return "RXMOI:MO="+(mctr!=null?mctr.trim():"")+";\n" +
               "RXMOC:MO="+(mctr!=null?mctr.trim():"")+", MAXTRX="+(maxtrx!=null?maxtrx.trim():"")
                +", TXMPWR= "+(txpwr!=null?txpwr.trim():"")+", PAO="+(pao!=null?pao.trim():"")
                +",MIXEDMODE="+(mixedmode!=null?mixedmode.trim():"")+", IPM="+(ipm!=null?ipm.trim():"")+" ,MAXPWR="+(maxpwr!=null?maxpwr.trim():"")+";";
    }
    
    private  String getPrintOut(String newTG){
        
        return "RXMOI:MO="+(newTG!=null?newTG.trim():"")+";\n" +
               "RXMOC:MO="+(newTG!=null?newTG.trim():"")+", MAXTRX="+(maxtrx!=null?maxtrx.trim():"")
                +", TXMPWR= "+(txpwr!=null?txpwr.trim():"")+", PAO="+(pao!=null?pao.trim():"")
                +",MIXEDMODE="+(mixedmode!=null?mixedmode.trim():"")+", IPM="+(ipm!=null?ipm.trim():"")
                +" ,MAXPWR="+(maxpwr!=null?maxpwr.trim():"")+";";
    }
    
}
