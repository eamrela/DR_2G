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
public class ODP {
 
    private String odp;
    private String dpi;
    private String alternative_odp;

    public ODP(String odp,String dpi){
        this.dpi = dpi;
        this.odp = odp;
    }
    public String getOdp() {
        return odp;
    }

    public void setOdp(String odp) {
        this.odp = odp;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getAlternative_odp() {
        return alternative_odp;
    }

    public void setAlternative_odp(String alternative_odp) {
        this.alternative_odp = "RXODP-"+alternative_odp+"-"+odp.split("-")[2];
        if(alternative_odp.trim().matches("\\d+")){
            dpi = "RXODPI-"+(Integer.parseInt(alternative_odp)*2);
        }
    }
    
     public String printout(){
        if(alternative_odp==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternative_odp);
        }
    }
    
    private String getPrintOut(){
        return "RXMOI:MO="+(odp!=null?odp.trim():"")+",DEV="+(dpi!=null?dpi.trim():"")+";";
    }
    
    private String getPrintOut(String newTG){
        return "RXMOI:MO="+(newTG!=null?newTG.trim():"")+",DEV="+(dpi!=null?dpi.trim():"")+";";
    }
    
}
