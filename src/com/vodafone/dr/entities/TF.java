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
public class TF {
    
    private String tf;
    private String alternative_tf;
    private String tfmode;
    private String syncsrc;
    private String fsoffset;

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public String getTfmode() {
        return tfmode;
    }

    public void setTfmode(String tfmode) {
        this.tfmode = tfmode;
    }

    public String getFsoffset() {
        return fsoffset;
    }

    public void setFsoffset(String fsoffset) {
        this.fsoffset = fsoffset;
    }

    
    public String getSyncsrc() {
        return syncsrc;
    }

    public void setSyncsrc(String syncsrc) {
        this.syncsrc = syncsrc;
    }

    public String getAlternative_tf() {
        return alternative_tf;
    }

    public void setAlternative_tf(String alternative_tf) {
        this.alternative_tf = "RXOTF-"+alternative_tf;
    }
    
    public String printout(){
        if(alternative_tf==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternative_tf);
        }
    }
    
    
    private String getPrintOut(){
        return "RXMOI:MO="+(tf!=null?tf.trim():"")+", TFMODE="+(tfmode!=null?tfmode.trim():"")+",SYNCSRC="+(syncsrc!=null?syncsrc.trim():"")
                +(fsoffset!=null?(fsoffset.equals("OFF")?"":",FSOFFSET="+fsoffset):"")+";";
    }
    
    private String getPrintOut(String newTG){
        return "RXMOI:MO="+(newTG!=null?newTG.trim():"")+", TFMODE="+(tfmode!=null?tfmode.trim():"")+",SYNCSRC="+(syncsrc!=null?syncsrc.trim():"")
                +(fsoffset!=null?(fsoffset.equals("OFF")?"":",FSOFFSET="+fsoffset):"")+";";
    }
    
}
