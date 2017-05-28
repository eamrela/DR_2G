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
public class CELL {
    
    private String cell;
    private String tg;
    private String alternative_tg;
    private String chgr;

    public CELL(String TG, String cell, String chgr) {
        this.cell = cell;
        this.tg = TG;
        this.chgr = chgr;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public String getChgr() {
        return chgr;
    }

    public void setChgr(String chgr) {
        this.chgr = chgr;
    }

    public String getAlternative_tg() {
        return alternative_tg;
    }

    public void setAlternative_tg(String alternative_tg) {
        this.alternative_tg = "RXOTG-"+alternative_tg;
    }
    
    
    public String attachPrintout(){
        if(alternative_tg==null){
            return attach();
        }else{
            return attach(alternative_tg);
        }
    }
    
    private String attach(){
        return "RXTCI:MO="+(tg!=null?tg.trim():"")+",CELL="+(cell!=null?cell.trim():"")+",CHGR="+(chgr!=null?chgr.trim():"")+";";
    }
    
    private String attach(String newTG){
        return "RXTCI:MO="+(newTG!=null?newTG.trim():"")+",CELL="+(cell!=null?cell.trim():"")+",CHGR="+(chgr!=null?chgr.trim():"")+";";
    }
    
    public String activate(){
        return "RLSTC:CELL="+(cell!=null?cell.trim():"")+",STATE=ACTIVE;";
    }
    
}
