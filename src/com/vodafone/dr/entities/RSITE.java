/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.entities;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Admin
 */
public class RSITE {
    
    private String sourceBSC;
    private String sourceMTX; // config
    private String sourceHUB; // config
    private String targetMTX; // config
    private String targetBSC; // config
    private String rsite_name;
    private String alternativeTG;
    private TreeMap<String,TG> TGs;
    private TreeMap<String,CELL> CELLs;
    private TreeMap<String,CF> CFs;
    private TreeMap<String,MCTR> MCTRs;
    private TreeMap<String,PSTU> PSTUs;
    private TreeMap<String,RX> RXs;
    private TreeMap<String,SuperChannel> SCs;
    private TreeMap<String,TF> TFs;
    private TreeMap<String,TRX> TRXs;
    private TreeMap<String,TX> TXs;
    private TreeMap<String,ODP> ODPs;
    private TreeMap<String,OCON> OCONs;

    public RSITE(String name,String bsc){
        this.rsite_name = name;
        this.sourceBSC = bsc;
        TGs = new TreeMap<String,TG>();
        CELLs = new TreeMap<String,CELL>();
        CFs = new TreeMap<String,CF>();
        MCTRs = new TreeMap<String,MCTR>();
        PSTUs = new TreeMap<String,PSTU>();
        RXs = new TreeMap<String,RX>();
        SCs = new TreeMap<String,SuperChannel>();
        TFs = new TreeMap<String,TF>();
        TRXs = new TreeMap<String,TRX>();
        TXs = new TreeMap<String,TX>();
        ODPs = new TreeMap<String, ODP>();
        OCONs = new TreeMap<String, OCON>();
    }

    public TreeMap<String, OCON> getOCONs() {
        return OCONs;
    }

    public TreeMap<String, ODP> getODPs() {
        return ODPs;
    }

    public void setODPs(TreeMap<String, ODP> ODPs) {
        this.ODPs = ODPs;
    }

    public void setOCONs(TreeMap<String, OCON> OCONs) {
        this.OCONs = OCONs;
    }
    
    
    public String getSourceBSC() {
        return sourceBSC;
    }

    public void setSourceBSC(String sourceBSC) {
        this.sourceBSC = sourceBSC;
    }

    public String getSourceMTX() {
        return sourceMTX;
    }

    public void setSourceMTX(String sourceMTX) {
        this.sourceMTX = sourceMTX;
    }

    public String getSourceHUB() {
        return sourceHUB;
    }

    public void setSourceHUB(String sourceHUB) {
        this.sourceHUB = sourceHUB;
    }

    public String getTargetMTX() {
        return targetMTX;
    }

    public void setTargetMTX(String targetMTX) {
        this.targetMTX = targetMTX;
    }

    public String getTargetBSC() {
        return targetBSC;
    }

    public void setTargetBSC(String targetBSC) {
        this.targetBSC = targetBSC;
    }

    public String getRsite_name() {
        return rsite_name;
    }

    public void setRsite_name(String rsite_name) {
        this.rsite_name = rsite_name;
    }

    public TreeMap<String, TG> getTGs() {
        return TGs;
    }

    public void setTGs(TreeMap<String, TG> TGs) {
        this.TGs = TGs;
    }

    public TreeMap<String, CELL> getCELLs() {
        return CELLs;
    }

    public void setCELLs(TreeMap<String, CELL> CELLs) {
        this.CELLs = CELLs;
    }

    public TreeMap<String, CF> getCFs() {
        return CFs;
    }

    public void setCFs(TreeMap<String, CF> CFs) {
        this.CFs = CFs;
    }

    public TreeMap<String, MCTR> getMCTRs() {
        return MCTRs;
    }

    public void setMCTRs(TreeMap<String, MCTR> MCTRs) {
        this.MCTRs = MCTRs;
    }

    public TreeMap<String, PSTU> getPSTUs() {
        return PSTUs;
    }

    public void setPSTUs(TreeMap<String, PSTU> PSTUs) {
        this.PSTUs = PSTUs;
    }

    public TreeMap<String, RX> getRXs() {
        return RXs;
    }

    public void setRXs(TreeMap<String, RX> RXs) {
        this.RXs = RXs;
    }

    public TreeMap<String, SuperChannel> getSCs() {
        return SCs;
    }

    public void setSCs(TreeMap<String, SuperChannel> SCs) {
        this.SCs = SCs;
    }

    public TreeMap<String, TF> getTFs() {
        return TFs;
    }

    public void setTFs(TreeMap<String, TF> TFs) {
        this.TFs = TFs;
    }

    public TreeMap<String, TRX> getTRXs() {
        return TRXs;
    }

    public void setTRXs(TreeMap<String, TRX> TRXs) {
        this.TRXs = TRXs;
    }

    public TreeMap<String, TX> getTXs() {
        return TXs;
    }

    public void setTXs(TreeMap<String, TX> TXs) {
        this.TXs = TXs;
    }
    
    public String getSiteCreationScript(){
  String script ="!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
        script +="!! SITE: "+rsite_name+"\n";
        script +="!! SOURCE_BSC: "+sourceBSC+"\n";
        script +="!! SOURCE_HUB: "+sourceHUB+"\n";
        script +="!! SOURCE_MTX: "+sourceMTX+"\n";
        script +="!! TARGET_BSC: "+targetBSC+"\n";
        script +="!! TARGET_MTX: "+targetMTX+"\n";
        script +="!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
        // CREATE PSTU
        for (Map.Entry<String, PSTU> entry : PSTUs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        // CREATE SCGR
        for (Map.Entry<String, SuperChannel> entry : SCs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        //CREATE TG
        for (Map.Entry<String, TG> entry : TGs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        //CREATE OCON
        for (Map.Entry<String, OCON> entry : OCONs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        //CREATE ODP
        for (Map.Entry<String, ODP> entry : ODPs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        //CREATE CF
        for (Map.Entry<String, CF> entry : CFs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        //CREATE TF
        for (Map.Entry<String, TF> entry : TFs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        //CELLS ATTACHMENT
        for (Map.Entry<String, CELL> entry : CELLs.entrySet()) {
            script +=entry.getValue().attachPrintout()+"\n";
        }
        //CREATE TRX
        for (Map.Entry<String, TRX> entry : TRXs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        // CREATE TX
        for (Map.Entry<String, TX> entry : TXs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        // CREATE RX
        for (Map.Entry<String, RX> entry : RXs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        // CREATE MCTR
        for (Map.Entry<String, MCTR> entry : MCTRs.entrySet()) {
            script +=entry.getValue().printout()+"\n";
        }
        // TRX TO SC AND MCTR ATTACHMENT
        for (Map.Entry<String, TRX> entry : TRXs.entrySet()) {
            script +=entry.getValue().attachPrintout()+"\n";
        }
        // CELLS AND ACTIVATION
        for (Map.Entry<String, TG> entry : TGs.entrySet()) {
            script +=entry.getValue().activatePrintout()+"\n";
        }
        //remove duplicates
        TreeMap<String, CELL> CELLs_Adjusted = new TreeMap<String, CELL>();
        for (Map.Entry<String, CELL> entry : CELLs.entrySet()) {
            CELLs_Adjusted.put(entry.getKey().split("~")[1], entry.getValue());
        }
        for (Map.Entry<String, CELL> entry : CELLs_Adjusted.entrySet()) {
            if(!entry.getKey().contains("~")){
            script +=entry.getValue().activate()+"\n";
            }
        }
        script+="!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n";
        return script;
    }
    
    public void adjustSite(TreeMap<String, TreeMap<String,Boolean>> TG_STATUS,PrintWriter tgSite){
        String alternative = null;
        //Adjust TG
        for (Map.Entry<String, TG> entry : TGs.entrySet()) {
            //SITE,BSC,TARGET_BSC,TG,TARGET_TG,CHANGED
           if(TG_STATUS.containsKey(targetBSC)){
                if(TG_STATUS.get(targetBSC).containsKey(entry.getValue().getTg().replaceAll("RXOTG-", "").trim())){
                    if(TG_STATUS.get(targetBSC).get(entry.getValue().getTg().replaceAll("RXOTG-", "").trim())){
                        
                        alternative = getFreeTG(targetBSC, TG_STATUS);
                        
                        entry.getValue().setAlternative_tg(alternative);
                        String original = entry.getValue().getTg().replaceAll("RXOTG-", "");
                        
                        tgSite.println(rsite_name+","+sourceBSC+","+targetBSC+",RXOTG-"+original+",RXOTG-"+alternative+",YES");
                        
                        adjustCELL(original, alternative);
                        adjustCF(original, alternative);
                        adjustMCTR(original, alternative);
                        adjustPSTU(original, alternative);
                        adjustRX(original, alternative);
                        adjustSCGR(original, alternative);
                        adjustTF(original, alternative);
                        adjustTRX(original, alternative);
                        adjustTX(original, alternative);
                        adjustODP(original, alternative);
                        adjustOCON(original, alternative);
                    }
                }else{
                    String original = entry.getValue().getTg().replaceAll("RXOTG-", "");
                    tgSite.println(rsite_name+","+sourceBSC+","+targetBSC+",RXOTG-"+original+",RXOTG-"+original+",NO");
                }
            } 
        }
    }
    
    public void adjustCELL(String original,String alternative){
        for (Map.Entry<String, CELL> entry : CELLs.entrySet()) {
            if(entry.getValue().getTg().contains(original)){
                entry.getValue().setAlternative_tg(alternative);
            }
        }
    }
    public void adjustCF(String original,String alternative){
        for (Map.Entry<String, CF> entry : CFs.entrySet()) {
            if(entry.getValue().getCf().contains(original)){
                entry.getValue().setAlternativeCf(alternative);
            }
        }
    }
    public void adjustMCTR(String original,String alternative){
        for (Map.Entry<String, MCTR> entry : MCTRs.entrySet()) {
            if(entry.getValue().getMctr().contains(original)){
                entry.getValue().setAlternative_mctr(alternative);
            }
        }
    }
    public void adjustPSTU(String original,String alternative){
       for (Map.Entry<String, PSTU> entry : PSTUs.entrySet()) {
            if(entry.getValue().getPstu().contains(original)){
                entry.getValue().setAlternative_pstu(alternative);
            }
        } 
    }
    public void adjustRX(String original,String alternative){
        for (Map.Entry<String, RX> entry : RXs.entrySet()) {
            if(entry.getValue().getRx().contains(original)){
                entry.getValue().setAlternative_rx(alternative);
            }
        }
    }
    public void adjustSCGR(String original,String alternative){
        for (Map.Entry<String, SuperChannel> entry : SCs.entrySet()) {
            if(entry.getValue().getPstu().contains(original)){
                entry.getValue().setAlternative_pstu(alternative);
            }
        }
    }
    public void adjustTF(String original,String alternative){
        for (Map.Entry<String, TF> entry : TFs.entrySet()) {
            if(entry.getValue().getTf().contains(original)){
                entry.getValue().setAlternative_tf(alternative);
            }
        }
    }
    public void adjustTRX(String original,String alternative){
        for (Map.Entry<String, TRX> entry : TRXs.entrySet()) {
            if(entry.getValue().getTrx().contains(original)){
                entry.getValue().setAlternative_trx(alternative);
            }
        }
    }
    public void adjustTX(String original,String alternative){
        for (Map.Entry<String, TX> entry : TXs.entrySet()) {
            if(entry.getValue().getTx().contains(original)){
                entry.getValue().setAlternative_tx(alternative);
            }
        }
    }
    public void adjustODP(String original,String alternative){
        for (Map.Entry<String, ODP> entry : ODPs.entrySet()) {
            if(entry.getValue().getOdp().contains(original)){
                entry.getValue().setAlternative_odp(alternative);
            }
        }
    }
    public void adjustOCON(String original,String alternative){
        for (Map.Entry<String, OCON> entry : OCONs.entrySet()) {
            if(entry.getValue().getOcon().contains(original)){
                entry.getValue().setAlternative_ocon(alternative);
            }
        }
    }
    
    public void setAlternativeTG(String alternativeTG) {
        this.alternativeTG = alternativeTG;
    }

    private static String getFreeTG(String BSC,TreeMap<String,TreeMap<String,Boolean>> TG_STATUS){
        TreeMap<String,Boolean> BSC_TGs = TG_STATUS.get(BSC);
        String newTG = null;
        for (Map.Entry<String, Boolean> entry : BSC_TGs.entrySet()) {
            if(!entry.getValue()){
                newTG = entry.getKey();
                entry.setValue(Boolean.TRUE);
                return newTG;
            }
        }
        return "NO FREE TG";
    }
    
}
