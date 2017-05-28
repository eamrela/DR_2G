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
public class OCON {
    
    private String ocon;
    private String dcp;
    private String alternative_ocon;

    public String getOcon() {
        return ocon;
    }

    public void setOcon(String ocon) {
        this.ocon = ocon;
    }

    public String getDcp() {
        return dcp;
    }

    public void setDcp(String dcp) {
        if(this.dcp==null){
        this.dcp = dcp;    
        }else{
        this.dcp += ","+dcp;    
        }
    }
    
    public void finalizeDcp(){
        if(dcp!=null){
            String[] tmp = dcp.split(",");
            if(tmp.length>2){
                dcp = tmp[0]+"&&"+tmp[tmp.length-1];
            }
        }
    }

    public String getAlternative_ocon() {
        return alternative_ocon;
    }

    public void setAlternative_ocon(String alternative_ocon) {
        this.alternative_ocon = "RXOCON-"+alternative_ocon;
    }
    
     public String printout(){
        if(alternative_ocon==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternative_ocon);
        }
    }
    
    
    private String getPrintOut(){
        return "RXMOI:MO="+(ocon!=null?ocon.trim():"")+",DCP="+(dcp!=null?dcp.trim():"")+";";
    }
    
    private String getPrintOut(String newTG){
        return "RXMOI:MO="+(newTG!=null?newTG.trim():"")+",DCP="+(dcp!=null?dcp.trim():"")+";";
    }
    
    
}
