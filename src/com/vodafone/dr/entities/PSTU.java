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
public class PSTU {
    
    private String pstu;
    private String alternative_pstu;
    private String lbg;
    private String sapi;

    public String getPstu() {
        return pstu;
    }

    public void setPstu(String pstu) {
        this.pstu = pstu;
    }

    public String getLbg() {
        return lbg;
    }

    public void setLbg(String lbg) {
        if(this.lbg==null){
        this.lbg = lbg;    
        }else{
        this.lbg += "&"+lbg;    
        }
    }

    public String getSapi() {
        return sapi;
    }

    public void setSapi(String sapi) {
        if(this.sapi==null){
        this.sapi = sapi;    
        }else{
        this.sapi += "&"+sapi;    
        }
        
    }

    public String getAlternative_pstu() {
        return alternative_pstu;
    }

    public void setAlternative_pstu(String alternative_pstu) {
        this.alternative_pstu = alternative_pstu;
    }
    
    public String printout(){
        if(alternative_pstu==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternative_pstu);
        }
    }
    private  String getPrintOut(){
        return "RRPTI:PSTU="+(pstu!=null?pstu.trim():"")+", LBG="+(lbg!=null?lbg.trim():"")+", SAPI="+(sapi!=null?sapi.trim():"")+";";
    }
    
    private String getPrintOut(String newTG){
        return "RRPTI:PSTU="+(newTG!=null?newTG.trim():"")+", LBG="+(lbg!=null?lbg.trim():"")+", SAPI="+(sapi!=null?sapi.trim():"")+";";
    }
    
}
