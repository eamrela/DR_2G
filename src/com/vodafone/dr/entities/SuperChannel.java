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
public class SuperChannel {
    
    private String scgr;
    private String mode;
    private String pstu;
    private String alternative_pstu;
    private String mbwdl;
    private String mbwul;
    private String ldel;
    private String ipov;
    private String jbsul;
    private String sc_dcp_numdev;

    public String getScgr() {
        return scgr;
    }

    public void setScgr(String scgr) {
        this.scgr = scgr;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPstu() {
        return pstu;
    }

    public void setPstu(String pstu) {
        this.pstu = pstu;
    }

    public String getMbwdl() {
        return mbwdl;
    }

    public void setMbwdl(String mbwdl) {
        this.mbwdl = mbwdl;
    }

    public String getMbwul() {
        return mbwul;
    }

    public void setMbwul(String mbwul) {
        this.mbwul = mbwul;
    }

    public String getLdel() {
        return ldel;
    }

    public void setLdel(String ldel) {
        this.ldel = ldel;
    }

    public String getIpov() {
        return ipov;
    }

    public void setIpov(String ipov) {
        this.ipov = ipov;
    }

    public String getJbsul() {
        return jbsul;
    }

    public void setJbsul(String jbsul) {
        this.jbsul = jbsul;
    }

    public String getSc_dcp_numdev() {
        return sc_dcp_numdev;
    }

    public void setSc_dcp_numdev(String sc_dcp_numdev) {
        this.sc_dcp_numdev = sc_dcp_numdev;
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
    
    private String getPrintOut(){
        return "RRSGI:SCGR="+(scgr!=null?scgr.trim():"")+",MODE="+(mode!=null?mode.trim():"")
                +",PSTU="+(pstu!=null?pstu.trim():"")+",MBWDL="+(mbwdl!=null?mbwdl.trim():"")
                +",MBWUL="+(mbwul!=null?mbwul.trim():"")+",LDEL="+(ldel!=null?ldel.trim():"")+",IPOV="+(ipov!=null?ipov.trim():"")+";\n" +
               "RRSGC:SCGR="+(scgr!=null?scgr.trim():"")+",JBSUL="+(jbsul!=null?jbsul.trim():"")+";\n"+
               "RRSCI:SCGR="+(scgr!=null?scgr.trim():"")+","+(sc_dcp_numdev!=null?sc_dcp_numdev.trim():"")+";";
    }
    
    private String getPrintOut(String newTG){
        return "RRSGI:SCGR="+(newTG!=null?newTG.trim():"")+",MODE="+(mode!=null?mode.trim():"")
                +",PSTU="+(newTG!=null?newTG.trim():"")+",MBWDL="+(mbwdl!=null?mbwdl.trim():"")
                +",MBWUL="+(mbwul!=null?mbwul.trim():"")+",LDEL="+(ldel!=null?ldel.trim():"")+",IPOV="+(ipov!=null?ipov.trim():"")+";\n" +
               "RRSGC:SCGR="+(newTG!=null?newTG.trim():"")+",JBSUL="+(jbsul!=null?jbsul.trim():"")+";\n"+
               "RRSCI:SCGR="+(newTG!=null?newTG.trim():"")+","+(sc_dcp_numdev!=null?sc_dcp_numdev.trim():"")+";";
    }
    
    
}
