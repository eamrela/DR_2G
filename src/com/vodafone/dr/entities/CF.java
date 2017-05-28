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
public class CF {
    
    private String cf;
    private String alternativeCf;
    
    private String tei;
    private String sig;

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getTei() {
        return tei;
    }

    public void setTei(String tei) {
        this.tei = tei;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }
    
    public String printout(){
        if(alternativeCf==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternativeCf);
        }
    }
    private String getPrintOut(){
        return "RXMOI:MO="+(cf!=null?cf.trim():"")+",SIG="+(sig!=null?sig.trim():"")+",TEI="+(tei!=null?tei.trim():"")+";\n"
                + "RXMOI:MO=RXOIS-"+(cf!=null?cf.replaceAll("RXOCF-", "").trim():"")+";";
    }
    
    private String getPrintOut(String newTG){
        return "RXMOI:MO="+(newTG!=null?newTG.trim():"")+",SIG="+(sig!=null?sig.trim():"")+",TEI="+(tei!=null?tei.trim():"")+";\n"
                + "RXMOI:MO=RXOIS-"+(newTG!=null?newTG.replaceAll("RXOCF-", "").trim():"")+";";
    }

    public String getAlternativeCf() {
        return alternativeCf;
    }

    public void setAlternativeCf(String alternativeCf) {
        this.alternativeCf = "RXOCF-"+alternativeCf;
    }
    
    
}
