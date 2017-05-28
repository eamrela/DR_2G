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
public class TX {
    
    private String tx;
    private String alternative_tx;
    private String maxpw;
    private String band;
    private String cell;

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public String getMaxpw() {
        return maxpw;
    }

    public void setMaxpw(String maxpw) {
        this.maxpw = maxpw;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAlternative_tx() {
        return alternative_tx;
    }

    public void setAlternative_tx(String alternative_tx) {
        this.alternative_tx = "RXOTX-"+alternative_tx+"-"+tx.split("-")[2];;
        
    }
    
    public String printout(){
        if(alternative_tx==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternative_tx);
        }
    }
    
    private String getPrintOut(){
        return  "RXMOI:MO="+(tx!=null?tx.trim():"")+",BAND="+(band!=null?band.trim():"")+",MPWR="+(maxpw!=null?maxpw.trim():"")+";\n" +
                "RXMOC:MO="+(tx!=null?tx.trim():"")+",CELL="+(cell!=null?cell.trim():"")+";  ";
    }
    
    private String getPrintOut(String newTG){
        return  "RXMOI:MO="+(newTG!=null?newTG.trim():"")+",BAND="+(band!=null?band.trim():"")+",MPWR="+(maxpw!=null?maxpw.trim():"")+";\n" +
                "RXMOC:MO="+(newTG!=null?newTG.trim():"")+",CELL="+(cell!=null?cell.trim():"")+";  ";
    }
}
