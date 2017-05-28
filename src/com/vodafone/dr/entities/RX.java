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
public class RX {
 
    private String rxd;
    private String rx;
    private String alternative_rx;
    private String band;

    public String getRxd() {
        return rxd;
    }

    public void setRxd(String rxd) {
        this.rxd = rxd;
    }

    public String getRx() {
        return rx;
    }

    public void setRx(String rx) {
        this.rx = rx;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getAlternative_rx() {
        return alternative_rx;
    }

    public void setAlternative_rx(String alternative_rx) {
        
        this.alternative_rx = "RXORX-"+alternative_rx+"-"+rx.split("-")[2];
    }
    
    
    
    public String printout(){
        if(alternative_rx==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternative_rx);
        }
    }
    
    private String getPrintOut(){
        return "RXMOI:MO="+(rx!=null?rx.trim():"")+",RXD="+(rxd!=null?rxd.trim():"")+",BAND="+(band!=null?band.trim():"")+";\n"
                + "RXMOI:MO=RXOTS-"+(rx!=null?rx.replaceAll("RXORX-", "").trim():"")+"-0&&-7;";
    }
    
    private String getPrintOut(String newTG){
        return "RXMOI:MO="+(newTG!=null?newTG.trim():"")+",RXD="+(rxd!=null?rxd.trim():"")+",BAND="+(band!=null?band.trim():"")+";\n"
                + "RXMOI:MO=RXOTS-"+(newTG!=null?newTG.replaceAll("RXORX-", "").trim():"")+"-0&&-7;";
    }
    
    
}
