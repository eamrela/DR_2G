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
public class TRX {
    
    private String trx;
    private String alternative_trx;
    private String tei;
    private String sig;
    private String dcp1;
    private String dcp2;
    private String cell;
    private String mctri;
    private String sc;

    public String getMctri() {
        return mctri;
    }

    public String getSc() {
        return sc;
    }

    public void setMctri(String mctri) {
        this.mctri = mctri;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }
    
    

    public String getTrx() {
        return trx;
    }

    public void setTrx(String trx) {
        this.trx = trx;
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

    public String getDcp1() {
        return dcp1;
    }

    public void setDcp1(String dcp1) {
        this.dcp1 = dcp1;
    }

    public String getDcp2() {
        return dcp2;
    }

    public void setDcp2(String dcp2) {
        this.dcp2 = dcp2;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAlternative_trx() {
        return alternative_trx;
    }

    public void setAlternative_trx(String alternative_trx) {
        
        this.alternative_trx = "RXOTRX-"+alternative_trx+"-"+trx.split("-")[2];;
    }
    
    
    public String printout(){
        if(alternative_trx==null){
            return getPrintOut();
        }else{
            return getPrintOut(alternative_trx);
        }
    }
    
    private String getPrintOut(){
        return "RXMOI:MO="+(trx!=null?trx.trim():"")+",TEI="+(tei!=null?tei.trim():"")+",SIG="
                +(sig!=null?sig.trim():"")+",DCP1="+(dcp1!=null?dcp1.trim():"")+",DCP2="+(dcp2!=null?dcp2.trim():"")+";\n" +
               "RXMOC:MO="+(trx!=null?trx.trim():"")+",CELL="+(cell!=null?cell.trim():"")+";";
    }
    
    private String getPrintOut(String newTG){
        return "RXMOI:MO="+(newTG!=null?newTG.trim():"")+",TEI="+(tei!=null?tei.trim():"")+",SIG="
                +(sig!=null?sig.trim():"")+",DCP1="+(dcp1!=null?dcp1.trim():"")+",DCP2="+(dcp2!=null?dcp2.trim():"")+";\n" +
               "RXMOC:MO="+(newTG!=null?newTG.trim():"")+",CELL="+(cell!=null?cell.trim():"")+";";
    }
    
    public String attachPrintout(){
        if(alternative_trx==null){
            return attach();
        }else{
            return attach(alternative_trx);
        }
    }
    
    private String attach(){
        return "RXMOC:MO="+(trx!=null?trx.trim():"")+",SC="+(sc!=null?sc.trim():"")+";\n" +
                "RXMOC:MO="+(trx!=null?trx.trim():"")+",MCTRI="+(mctri!=null?mctri.trim():"")+";";
    }
    
    private String attach(String newTG){
        return "RXMOC:MO="+(newTG!=null?newTG.trim():"")+",SC="+(sc!=null?sc.trim():"")+";\n" +
                "RXMOC:MO="+(newTG!=null?newTG.trim():"")+",MCTRI="+(mctri!=null?mctri.trim():"")+";";
    }
}
