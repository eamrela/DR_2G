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
public class TG {
    
    private String rsite;
    private String comb;
    private String traco;
    private String swver;
    private String tg;
    private String alternative_tg;
    private String confact;
    private String fhop;
    private String abisalloc;
    private String tmode;
    private String scgr;
    private String packalg;
    private String jbsdl;
    private String jbpta;
    private String pal;

    public String getRsite() {
        if(rsite.matches("[aA-zZ]+\\d+")){
            rsite = rsite.replaceAll("\\D+", "");
        }
        return rsite;
    }

    public void setRsite(String rsite) {
        this.rsite = rsite;
    }

    public String getComb() {
        return comb;
    }

    public void setComb(String comb) {
        this.comb = comb;
    }

    public String getTraco() {
        return traco;
    }

    public void setTraco(String traco) {
        this.traco = traco;
    }

    public String getSwver() {
        return swver;
    }

    public void setSwver(String swver) {
        this.swver = swver;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public String getConfact() {
        return confact;
    }

    public void setConfact(String confact) {
        this.confact = confact;
    }

    public String getFhop() {
        return fhop;
    }

    public void setFhop(String fhop) {
        this.fhop = fhop;
    }

    public String getAbisalloc() {
        return abisalloc;
    }

    public void setAbisalloc(String abisalloc) {
        this.abisalloc = abisalloc;
    }

    public String getTmode() {
        return tmode;
    }

    public void setTmode(String tmode) {
        this.tmode = tmode;
    }

    public String getScgr() {
        return scgr;
    }

    public void setScgr(String scgr) {
        this.scgr = scgr;
    }

    public String getJbpta() {
        return jbpta;
    }

    public String getJbsdl() {
        return jbsdl;
    }

    public String getPackalg() {
        return packalg;
    }

    public void setJbpta(String jbpta) {
        this.jbpta = jbpta;
    }

    public void setJbsdl(String jbsdl) {
        this.jbsdl = jbsdl;
    }

    public void setPackalg(String packalg) {
        this.packalg = packalg;
    }

    public String getPal() {
        return pal;
    }

    public void setPal(String pal) {
        this.pal = pal;
    }

    
    
    public String getAlternative_tg() {
        return alternative_tg;
    }

    public void setAlternative_tg(String alternative_tg) {
        
        this.alternative_tg = "RXOTG-"+alternative_tg;
    }
    
    
    public String printout(){
        if(alternative_tg==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternative_tg);
        }
    }
    
    public String activatePrintout(){
        if(alternative_tg==null){
            return activate();
        }else{
            return activate(alternative_tg);
        }
    }
    
    private String getPrintOut(){
        return "RXMOI:MO="+(tg!=null?tg.trim():"")+",RSITE="+(rsite!=null?rsite.trim():"")+",COMB="
                +(comb!=null?comb.trim():"")+",TRACO="+(traco!=null?traco.trim():"")+",SWVER="+(swver!=null?swver.trim():"")+";\n" +
               "RXMOC:MO="+(tg!=null?tg.trim():"")+",CONFACT="+(confact!=null?confact.trim():"")+",FHOP="+(fhop!=null?fhop.trim():"")+";\n" +
               "RXMOC:MO="+(tg!=null?tg.trim():"")+",ABISALLOC="+(abisalloc!=null?abisalloc.trim():"")+";\n"+
               "RXMOC:MO="+(tg!=null?tg.trim():"")+",TMODE=SCM,SCGR="+(scgr!=null?scgr.trim():"")+";\n"+
               "RXMOC:MO="+(tg!=null?tg.trim():"")+",PACKALG="+(packalg!=null?packalg.trim():"")+";\n"+
                "RXMOC:MO="+(tg!=null?tg.trim():"")+",JBSDL="+(jbsdl!=null?jbsdl.trim():"")+",PAL="+(pal!=null?pal.trim():"")
                +",PACKALG="+(packalg!=null?packalg.trim():"")+",JBPTA="+(jbpta!=null?jbpta.trim():"")+";";
    }
    
    private String getPrintOut(String newTG){
        return "RXMOI:MO="+(newTG!=null?newTG.trim():"")+",RSITE="+(rsite!=null?rsite.trim():"")+",COMB="
                +(comb!=null?comb.trim():"")+",TRACO="+(traco!=null?traco.trim():"")+",SWVER="+(swver!=null?swver.trim():"")+";\n" +
               "RXMOC:MO="+(newTG!=null?newTG.trim():"")+",CONFACT="+(confact!=null?confact.trim():"")+",FHOP="+(fhop!=null?fhop.trim():"")+";\n" +
               "RXMOC:MO="+(newTG!=null?newTG.trim():"")+",ABISALLOC="+(abisalloc!=null?abisalloc.trim():"")+";\n"+
                "RXMOC:MO="+(newTG!=null?newTG.trim():"")+",TMODE=SCM,SCGR="+(newTG!=null?newTG.replaceAll("RXOTG-","").trim():"")+";\n"+
                "RXMOC:MO="+(newTG!=null?newTG.trim():"")+",PACKALG="+(packalg!=null?packalg.trim():"")+";\n"+
                "RXMOC:MO="+(newTG!=null?newTG.trim():"")+",JBSDL="+(jbsdl!=null?jbsdl.trim():"")+",PAL="+(pal!=null?pal.trim():"")
                +",PACKALG="+(packalg!=null?packalg.trim():"")+",JBPTA="+(jbpta!=null?jbpta.trim():"")+";";
    }
    
    private String activate(){
        return "RXESI:MO="+(tg!=null?tg.trim():"")+",SUBORD;\n" +
                "RXBLE:MO="+(tg!=null?tg.trim():"")+",SUBORD;";
    }
   
    private String activate(String newTG){
        return "RXESI:MO="+(newTG!=null?newTG.trim():"")+",SUBORD;\n" +
                "RXBLE:MO="+(newTG!=null?newTG.trim():"")+",SUBORD;";
    }
}
