/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr;

import com.vodafone.dr.entities.BSC;
import com.vodafone.dr.entities.CELL;
import com.vodafone.dr.entities.CF;
import com.vodafone.dr.entities.MCTR;
import com.vodafone.dr.entities.OCON;
import com.vodafone.dr.entities.ODP;
import com.vodafone.dr.entities.PSTU;
import com.vodafone.dr.entities.RX;
import com.vodafone.dr.entities.SuperChannel;
import com.vodafone.dr.entities.TF;
import com.vodafone.dr.entities.TG;
import com.vodafone.dr.entities.TRX;
import com.vodafone.dr.entities.TX;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amr
 */
public class Parser {
    
    public static BSC parseBSC(File bsc_printout){
        
            String bsc_name = bsc_printout.getName().split("\\.")[0];
            BSC bsc = new BSC(bsc_name);
            getTGs(bsc, bsc_printout); 
            getSCGR(bsc, bsc_printout);
            getCFs(bsc, bsc_printout);
            getTFs(bsc, bsc_printout);
            getTRXs(bsc, bsc_printout);
            getTXs(bsc, bsc_printout);
            getRXs(bsc, bsc_printout);
            getPSTUs(bsc, bsc_printout);
            getMCTRs(bsc, bsc_printout);
            getCELLs(bsc, bsc_printout);
            getODPs(bsc, bsc_printout);
            getOCONs(bsc, bsc_printout);
            getTGStatus(bsc,bsc_printout);
            
        return bsc;
    }
    
    private static void getPSTUs(BSC bsc,File file){
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            PSTU pstu = null;
            boolean pstuStarted = false;
            boolean sapiStarted = false;
            while((line=raf.readLine())!=null){
             if(line.contains("PSTU                  STN                 IWDVER")){
                 pstuStarted=true;
                 sapiStarted = false;
                 if(pstu!=null){
                     bsc.addPSTU(pstu);
                 }
                 pstu = new PSTU();
             }
             else if(line.contains("SAPI  LBG  CONN")){
                 sapiStarted = true;
                 pstuStarted = false;
             }
             else if(pstuStarted){
                 line = removeExtraSpaces(line);
                 String[] vals = line.split(" ");
                 if(vals.length>=1){
                    pstu.setPstu(vals[0]);
                    pstuStarted = false;
                 }
             }
             else if(sapiStarted){
                line = removeExtraSpaces(line);
                 String[] vals = line.split(" ");
                 if(vals.length==3){
                    pstu.setSapi(vals[0]);
                    pstu.setLbg(vals[1]);
                 } 
             }
             if(line.contains("END")){
                 if(pstu!=null){
                     bsc.addPSTU(pstu);
                 }
                 break;
             }
             
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 

    private static void getSCGR(BSC bsc,File file){
            TreeMap<String,SuperChannel> scgrs = new TreeMap<String,SuperChannel>();
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            SuperChannel scgr = null;
            boolean scgr1Started = false;
            boolean scgr2Started = false;
            String ref1 = "SCGR  SC  DEV            DEV1           NUMDEV  DCP  STATE  REASON";
            while((line=raf.readLine())!=null){
                if(line.contains("SCGR  SC  DEV            DEV1           NUMDEV  DCP  STATE  REASON")){
                    scgr1Started = true;
                    scgr2Started = false;
                    if(scgr==null){
                        scgr = new SuperChannel();
                    }else{
                        scgrs.put(scgr.getScgr(), scgr);
                        scgr = new SuperChannel();
                    }
                }else if(line.contains("SCGR  MODE  PSTU                  MBWDL  MBWUL  JBSUL  LDEL  IPOV")){
                    scgr2Started = true;
                    scgr1Started = false;
                }else if(scgr1Started){
                    try{
                    scgr.setScgr(line.substring(ref1.indexOf("SCGR "), ref1.indexOf("SCGR ")+4).trim());
                    scgr.setSc_dcp_numdev("SC="+line.substring(ref1.indexOf("DEV")-4, ref1.indexOf("DEV")-2).trim()
                            +",DCP="+line.substring(ref1.indexOf("DCP"), ref1.indexOf("DCP")+3).trim()
                            +",NUMDEV="+line.substring(ref1.indexOf("NUMDEV"), ref1.indexOf("NUMDEV")+5).trim());
                    
                    }catch(StringIndexOutOfBoundsException e){
//                        System.out.println(line);
                    }
                }else if(scgr2Started){
                    if(line.contains("IPM")){
                        line = removeExtraSpaces(line);
                        String[] vals = line.split(" ");
                        if(vals.length==8){
                        if(scgrs.containsKey(vals[0].trim())){
                            scgr = scgrs.get(vals[0].trim());
                            scgr.setMode(vals[1].trim());
                            scgr.setPstu(vals[2].trim());
                            scgr.setMbwdl(vals[3].trim());
                            scgr.setMbwul(vals[4].trim());
                            scgr.setJbsul(vals[5].trim());
                            scgr.setLdel(vals[6].trim());
                            scgr.setIpov(vals[7].trim());
                            scgrs.put(scgr.getScgr(), scgr);
                        }
                        }
                    }
                }
                
                if(line.contains("END") && scgr1Started){
                    scgrs.put(scgr.getScgr(), scgr);
                    scgr = null;
                    scgr1Started = false;
                }
                if(line.contains("END") && scgr2Started){
                    scgr2Started = false;
                }
                
            }
        
            for (Map.Entry<String, SuperChannel> entry : scgrs.entrySet()) {
               if(entry.getValue().getMode()!=null)
                bsc.addSCGR(entry.getValue());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void getTGs(BSC bsc,File file){
        
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            TG tg = null;
            boolean tgStarted = false;
            while((line=raf.readLine())!=null){
                if(line.contains("MO                RSITE                               COMB  FHOP  MODEL")){
                    tgStarted = true;
                    tg = new TG();
                }else if(tgStarted){
                    line = removeExtraSpaces(line);
                    String[] vals = line.split(" ");
                    if(vals.length==5){
                        tg.setTg(vals[0]);
                        tg.setRsite(vals[1]);
                        tg.setComb(vals[2]);
                        tg.setFhop(vals[3]);
                     raf.readLine(); // empty line
                     raf.readLine(); // headers
                     line = raf.readLine();
                     if(line.contains("SCM")){
                       line = removeExtraSpaces(line);
                       vals = line.split(" ");
                       if(vals.length==3){
                       tg.setSwver(vals[0]);
                       tg.setTmode("SCM");
                       raf.readLine(); // empty line
                       raf.readLine(); // headers
                       line = raf.readLine();
                       line = removeExtraSpaces(line);
                       vals=line.split(" ");
                        if(vals.length==5){
                            tg.setConfact(vals[1]);
                            tg.setTraco(vals[2]);
                            tg.setAbisalloc(vals[3]);
                            tg.setScgr(vals[4]);
                            
                            // new code 
                            raf.readLine(); // empty line
                            raf.readLine(); // headers
                            raf.readLine(); // values
                            raf.readLine(); // empty line
                            raf.readLine(); // headers
                            line = raf.readLine();
                            line = removeExtraSpaces(line);
                            vals=line.split(" ");
                            if(vals.length==4){
                            tg.setJbsdl(vals[1]);
                            tg.setPal(vals[2]);
                            tg.setJbpta(vals[3]);
                            raf.readLine(); // empty line
                            raf.readLine(); // headers
                            line = raf.readLine();
                            line = removeExtraSpaces(line);
                            vals=line.split(" ");
                            if(vals.length>3){
                                if(vals[vals.length-1].matches("\\d")){
                                    tg.setPackalg(vals[vals.length-1]);
                                }
                            }
                            // part from old code
                            bsc.addTG(tg);
                            tgStarted = false;
                            tg = null;
                            }
                        }
                       }
                     }else{
                         tg = null;
                         tgStarted = false;
                     }
                    }
                }
            }
                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    private static void getCFs(BSC bsc,File file){
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            CF cf = null;
            boolean cfStarted = false;
            while((line=raf.readLine())!=null){
                if(line.contains("MO                TEI      SIG")){
                    cfStarted = true;
                    cf = new CF();
                }else if(cfStarted){
                    line = removeExtraSpaces(line);
                    String[] vals = line.split(" ");
                    if(vals.length==3){
                        cf.setCf(vals[0]);
                        cf.setTei(vals[1]);
                        cf.setSig(vals[2]);
                        bsc.addCF(cf);
                        cf = null;
                        cfStarted = false;
                    }
                }
            }
                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    private static void getTFs(BSC bsc,File file){
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            TF tf = null;
            boolean tfStarted = false;
            while((line=raf.readLine())!=null){
                if(line.contains("MO              TFMODE  SYNCSRC  TFCOMPNEG  TFCOMPPOS  FSOFFSET  TFAUTO")){
                    tfStarted = true;
                    tf = new TF();
                }else if(tfStarted){
                    line = removeExtraSpaces(line);
                    String[] vals = line.split(" ");
                    if(vals.length>=3){
                        tf.setTf(vals[0]);
                        tf.setTfmode(vals[1]);
                        tf.setSyncsrc(vals[2]);
                        if(line.contains("OFF")){
                            tf.setFsoffset("OFF");
                        }else{
                            tf.setFsoffset("0");
                        }
                        bsc.addTF(tf);
                        tf = null;
                        tfStarted = false;
                    }
                }
            }
                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    private static void getTRXs(BSC bsc,File file){
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            TRX trx = null;
            String ref1 = "MO                CELL      CHGR    TEI      SIG      MCTRI    DCP1  SC";
            while((line=raf.readLine())!=null){
                if(line.contains(ref1)){
                    line = raf.readLine();
                    try{
                    trx = new TRX();
                    trx.setTrx(line.substring(ref1.indexOf("MO "), ref1.indexOf("CELL ")).trim());
                    trx.setCell(line.substring(ref1.indexOf("CELL "), ref1.indexOf("CHGR ")).trim());
                    trx.setTei(line.substring(ref1.indexOf("TEI "), ref1.indexOf("SIG ")).trim());
                    trx.setSig(line.substring(ref1.indexOf("SIG "), ref1.indexOf("MCTRI ")).trim());
                    if(line.length()>ref1.indexOf("DCP1 ")+4){
                    trx.setDcp1(line.substring(ref1.indexOf("DCP1 "), ref1.indexOf("DCP1 ")+4).trim());
                    trx.setSc(line.substring(ref1.indexOf("DCP1 ")+4).trim());
                    }else{
                    trx.setDcp1(line.substring(ref1.indexOf("DCP1 ")).trim());   
                    }
                    trx.setMctri(line.substring(ref1.indexOf("MCTRI "), ref1.indexOf("DCP1 ")).trim());
                    
                    raf.readLine(); // empty line
                    raf.readLine(); // headers
                    line = raf.readLine();
                    String dcp2End = null;
                    line = removeExtraSpaces(line);
                    String[] vals = line.split(" ");
                    trx.setDcp2(vals[vals.length-1]);// DCP2 start
                    line = raf.readLine();
                    while(line.length()>2){
                    line = removeExtraSpaces(line);
                    String[] vals2 = line.split(" ");
                    dcp2End = vals2[vals2.length-1];
                    line = raf.readLine();
                    }
                    trx.setDcp2(trx.getDcp2()+"&&"+dcp2End);
                    bsc.addTRX(trx);
                    
                    }catch(StringIndexOutOfBoundsException e){
                        System.out.println(line);
                        e.printStackTrace();
                    }
                }
                
                
            }
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    
    private static void getTXs(BSC bsc,File file){
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            TX tx = null;
            while((line=raf.readLine())!=null){
                if(line.contains("MO                CELL      CHGR  BAND     ANT      MPWR")){
                    line = raf.readLine();
                    line = removeExtraSpaces(line);
                    String[] vals = line.split(" ");
                    if(vals.length>=5){
                        tx = new TX();
                        tx.setTx(vals[0]);
                        tx.setCell(vals[1]);
                        tx.setBand(vals[3]);
                        tx.setMaxpw(vals[vals.length-1]);
                        bsc.addTX(tx);
                    }
                }
            }
                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    private static void getRXs(BSC bsc,File file){
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            RX rx = null;
            while((line=raf.readLine())!=null){
                if(line.contains("MO                RXD       BAND     ANTA      ANTB      IMBVAMOS")){
                    line = raf.readLine();
                    line = removeExtraSpaces(line);
                    String[] vals = line.split(" ");
                    if(vals.length>=4){
                        rx = new RX();
                        rx.setRx(vals[0]);
                        rx.setRxd(vals[1]);
                        rx.setBand(vals[2]);
                        bsc.addRX(rx);
                    }
                }
            }
                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    private static void getMCTRs(BSC bsc,File file){
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            MCTR mctr = null;
            while((line=raf.readLine())!=null){
                if(line.contains("MO                MAXPWR   MAXTRX   PAO   TXMPWR   IPM  MIXEDMODE  TPO")){
                    line = raf.readLine();
                    line = removeExtraSpaces(line);
                    String[] vals = line.split(" ");
                    if(vals.length==8){
                        mctr = new MCTR();
                        mctr.setMctr(vals[0]);
                        mctr.setMaxpwr(vals[1]);
                        mctr.setMaxtrx(vals[2]);
                        mctr.setPao(vals[3]);
                        mctr.setTxpwr(vals[4]);
                        mctr.setIpm(vals[5]);
                        mctr.setMixedmode(vals[6]);
                        bsc.addMCTR(mctr);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void getCELLs(BSC bsc,File file){
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            boolean cellsStarted = false;
            String TG=null;
            String[] vals;
            while((line=raf.readLine())!=null){
                if(cellsStarted){
                    if(line.contains("END")){
                        cellsStarted=false;
                    }else{
                        line = removeExtraSpaces(line);
                        vals = line.split("\\s");
                        if(vals.length==3){
                            if(vals[0].length()>0){
                            TG = vals[0];
                            bsc.addCELL(new CELL(TG, vals[1], vals[2]));
                        }
                        }else if(vals.length==2){
                        bsc.addCELL(new CELL(TG, vals[0], vals[1]));
                        }
                    }
                }else{
                    if(line.contains("MO               CELL                CHGR")){
                        cellsStarted=true;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void getODPs(BSC bsc,File file){
        
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            boolean odpStarted = false;
            String[] vals;
            while((line=raf.readLine())!=null){
                if(odpStarted){
                    if(line.contains("END")){
                        odpStarted=false;
                    }else{
                        line = removeExtraSpaces(line);
                        vals = line.split("\\s");
                        if(vals.length==2){
                            if(vals[0].length()>0){
                                bsc.addODP(new ODP(vals[0], vals[1]));
                            }
                        }
                           
                        
                    }
                }else  if(line.contains("MO                DEV")){
                                odpStarted=true;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void getOCONs(BSC bsc,File file){
        
       try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            OCON ocon = null;
            boolean oconStarted = false;
            while((line=raf.readLine())!=null){
                if(line.contains("MO                DCP")){
                    if(ocon!=null){
                        ocon.finalizeDcp();
                        bsc.addOCON(ocon);
                        ocon = null;
                        oconStarted = false;
                    }
                    oconStarted = true;
                    ocon = new OCON();
                }else if(oconStarted){
                    line = removeExtraSpaces(line);
                    String[] vals = line.split(" ");
                    if(vals.length>8){
                        if(vals[0].contains("RXOCON")){
                        ocon.setOcon(vals[0]);
                        ocon.setDcp(vals[1]);
                        }
                    }else if(vals.length==8){
                        ocon.setDcp(vals[vals.length-1]);
                    }
                }else if(line.contains("END")){
                    if(ocon!=null){
                        ocon.finalizeDcp();
                        bsc.addOCON(ocon);
                        ocon = null;
                        oconStarted = false;
                        break;
                    }
                }
            }
                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
   
    private static void getTGStatus(BSC bsc,File file){
        
       try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line = null;
            String tg = null;
            boolean tgStarted = false;
            while((line=raf.readLine())!=null){
                if(line.contains("MO               STATE  BLSTATE    BLO   BLA   LMO    BTS   CONF")){
                    tgStarted = true;
                }else if(tgStarted){
                    line = removeExtraSpaces(line);
                    String[] vals = line.split(" ");
                    if(vals.length>0){
                       tg =  vals[0];
                       bsc.addTGStatus(tg);
                    }
                }else if(line.contains("END")){
                        tgStarted = false;
                        break;
                    }
                }
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private static String removeExtraSpaces(String str){
        str = str.replaceAll(" +", " ");
        return str.trim();
    }


    
}
