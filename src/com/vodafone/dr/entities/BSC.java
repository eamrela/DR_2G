/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.entities;

import com.vodafone.dr.configuration.AppConf;
import java.util.TreeMap;

/**
 *
 * @author Amr
 */
public class BSC {
    
    private String BSC;
    private TreeMap<String,RSITE> RSITEs;
    private TreeMap<String,String> TG_RSITE;
    private TreeMap<String,Boolean> TGStatus;

    public BSC(String name){
        BSC = name;
        RSITEs = new TreeMap<String,RSITE>();
        TG_RSITE = new TreeMap<String,String>();
        initializeTGs();
    }
    
    public String getBSC() {
        return BSC;
    }

    public void setBSC(String BSC) {
        this.BSC = BSC;
    }

    public void addRSITE(RSITE site){
        if(!RSITEs.containsKey(site.getRsite_name())){
            if(AppConf.getDRDetails().containsKey(site.getRsite_name())){
                DR dr = AppConf.getDRDetails().get(site.getRsite_name());
                site.setSourceHUB(dr.getSourceHUB());
                site.setSourceMTX(dr.getSourceMTX());
                site.setTargetBSC(dr.getTargetBSC());
                site.setTargetMTX(dr.getTargetMTX());
            }
           RSITEs.put(site.getRsite_name(), site);
        }
    }
    public void addTG(TG tg){
        
	addRSITE(new RSITE(tg.getRsite().trim(),BSC));
        
        if(tg.getTg()!=null && tg.getRsite()!=null){
        TG_RSITE.put(tg.getTg().replaceAll("RXOTG-", "").trim(), tg.getRsite().trim());
        RSITEs.get(tg.getRsite().trim()).getTGs().put(tg.getTg(), tg);
        if(TGStatus.containsKey(tg.getTg().replaceAll("RXOTG-", "").trim())){
            TGStatus.put(tg.getTg().replaceAll("RXOTG-", "").trim(), Boolean.TRUE);
        }
        }
    }
    public void addTRX(TRX trx) {
        if(trx.getTrx()!=null){
        if(TG_RSITE.containsKey(trx.getTrx().split("-")[1].trim())){
            RSITEs.get(TG_RSITE.get(trx.getTrx().split("-")[1].trim()))
                    .getTRXs().put(trx.getTrx().trim(), trx);
        }
        }
    }
    public void addTF(TF tf) {
        if(tf.getTf()!=null){
        if(TG_RSITE.containsKey(tf.getTf().replaceAll("RXOTF-", "").trim())){
            RSITEs.get(TG_RSITE.get(tf.getTf().replaceAll("RXOTF-", "").trim()))
                    .getTFs().put(tf.getTf().replaceAll("RXOTF-", "").trim(), tf);
        }
        }
    }
    public void addCF(CF cf) {
        if(cf.getCf()!=null){
        if(TG_RSITE.containsKey(cf.getCf().replaceAll("RXOCF-", "").trim())){
            RSITEs.get(TG_RSITE.get(cf.getCf().replaceAll("RXOCF-", "").trim()))
                    .getCFs().put(cf.getCf().replaceAll("RXOCF-", "").trim(), cf);
        }
        }
    }
    public void addRX(RX rx) {
        if(rx.getRx()!=null){
        if(TG_RSITE.containsKey(rx.getRx().split("-")[1].trim())){
            RSITEs.get(TG_RSITE.get(rx.getRx().split("-")[1].trim()))
                    .getRXs().put(rx.getRx(), rx);
        }
        }
    }
    public void addTX(TX tx) {
        if(tx.getTx()!=null){
        if(TG_RSITE.containsKey(tx.getTx().split("-")[1].trim())){
            RSITEs.get(TG_RSITE.get(tx.getTx().split("-")[1].trim()))
                    .getTXs().put(tx.getTx().trim(), tx);
        }
        }
    }
    public void addMCTR(MCTR mctr) {
        if(mctr.getMctr()!=null){
        if(TG_RSITE.containsKey(mctr.getMctr().split("-")[1].trim())){
            RSITEs.get(TG_RSITE.get(mctr.getMctr().split("-")[1].trim()))
                    .getMCTRs().put(mctr.getMctr().trim(), mctr);
        }
        }
    }
    public void addPSTU(PSTU pstu) {
        if(pstu.getPstu()!=null){
        if(TG_RSITE.containsKey(pstu.getPstu().trim())){
            RSITEs.get(TG_RSITE.get(pstu.getPstu().trim()))
                    .getPSTUs().put(pstu.getPstu().trim(), pstu);
        }
        }
    }
    public void addSCGR(SuperChannel value) {
        if(value.getPstu()!=null && value.getScgr()!=null){
        if(TG_RSITE.containsKey(value.getPstu().trim())){
            RSITEs.get(TG_RSITE.get(value.getPstu().trim()))
                    .getSCs().put(value.getScgr().trim(), value);
        }
        }
    }
    public void addCELL(CELL value) {
        if(value.getTg()!=null){
        if(TG_RSITE.containsKey(value.getTg().replaceAll("RXOTG-", "").trim())){
            RSITEs.get(TG_RSITE.get(value.getTg().replaceAll("RXOTG-", "").trim()))
                    .getCELLs().put(value.getTg().replaceAll("RXOTG-", "")+"~"+value.getCell().trim(), value);
        }
        }
    }
    public void addODP(ODP value) {
        if(value.getOdp()!=null){
        if(TG_RSITE.containsKey(value.getOdp().split("-")[1].trim())){
            RSITEs.get(TG_RSITE.get(value.getOdp().split("-")[1].trim()))
                    .getODPs().put(value.getOdp().trim(), value);
        }
        }
    }
    public void addOCON(OCON value) {
        if(value.getOcon()!=null){
        if(TG_RSITE.containsKey(value.getOcon().replaceAll("RXOCON-", "").trim())){
            RSITEs.get(TG_RSITE.get(value.getOcon().replaceAll("RXOCON-", "").trim()))
                    .getOCONs().put(value.getOcon().replaceAll("RXOCON-", "")+"~"+value.getOcon().trim(), value);
        }
        }
    }
    
    public void addTGStatus(String TG){
        if(TGStatus.containsKey(TG.replaceAll("RXOTG-", "").trim())){
            TGStatus.put(TG.replaceAll("RXOTG-", "").trim(), Boolean.TRUE);
        }
    }
    
    public TreeMap<String, RSITE> getRSITEs() {
        return RSITEs;
    }

    private void initializeTGs(){
        TGStatus = new TreeMap<String, Boolean>();
        for (int i = 1; i < 650; i++) {
            TGStatus.put(i+"", Boolean.FALSE);
        }
    }

    public TreeMap<String, Boolean> getTGStatus() {
        return TGStatus;
    }
    
   
}
