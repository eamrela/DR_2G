/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.main;

import com.vodafone.dr.Parser;
import com.vodafone.dr.collector.BSCDataCollector;
import com.vodafone.dr.configuration.AppConf;
import com.vodafone.dr.creator.ScriptCreator;
import com.vodafone.dr.entities.BSC;
import com.vodafone.dr.entities.RSITE;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JTextArea;

/**
 *
 * @author Amr
 */
public class Main {
    
        private static TreeMap<String, RSITE> SITES = new TreeMap<String, RSITE>();
        private static TreeMap<String, TreeMap<String,Boolean>> TG_STATUS = new TreeMap<String, TreeMap<String, Boolean>>();
    
        public static void loopOnPrintouts(String printoutDir){
        File mainFolder = new File(printoutDir);
        File files[];
        files = mainFolder.listFiles();
        BSC bsc = null;
        for (File file : files) {
            bsc = Parser.parseBSC(file);
            SITES.putAll(bsc.getRSITEs());
            TG_STATUS.put(bsc.getBSC(), bsc.getTGStatus());
        }
    }
    
    
    public static boolean process(String ossConf,String drConf) {
        
        //<editor-fold defaultstate="collapsed" desc="Configure Application">
        System.out.println("Configuring OSS-RC Account");
        AppConf.configureOSS(ossConf);
        System.out.println("Finished Configuring OSS-RC Account");
        System.out.println("Configuring Disaster Recovery Plan");
        AppConf.configureDR(drConf);
        System.out.println("Finished Configuring Disaster Recovery Plan: "+AppConf.getDRDetails().size());
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Create Directories">
        String workingDir = AppConf.getWorkingDirectory()+"\\DR_"+AppConf.getMydate();
        String printoutsDir = AppConf.getWorkingDirectory()+"\\DR_"+AppConf.getMydate()+"\\Printouts";
        String recoveryDir = AppConf.getWorkingDirectory()+"\\DR_"+AppConf.getMydate()+"\\Recovery";
        new File(workingDir).mkdirs();
        new File(printoutsDir).mkdirs();
        new File(recoveryDir).mkdirs();
        File tgFile = new File(workingDir+"\\TG_SITE.csv");
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Collect Data">
        ExecutorService executor;
        executor= Executors.newCachedThreadPool();
        for (Map.Entry<String, Integer> bsc : AppConf.getBSCs().entrySet()) {
            executor.submit(new BSCDataCollector().init(bsc.getKey(),printoutsDir,null));
        }
        executor.shutdown();
        while(!executor.isTerminated()){}
        System.out.println("Executer finished");
//</editor-fold>

        loopOnPrintouts(printoutsDir);
        ScriptCreator.createRecoveryScripts(recoveryDir, SITES,TG_STATUS,tgFile);
        return true;

    }
    
    public static boolean processGUI(String ossIP,String ossUser,String ossPASS,
            String drConf, String workDir,TreeMap<String,Integer> BSCs,JTextArea output) {
        
        //<editor-fold defaultstate="collapsed" desc="Configure Application">
        output.append("Configuring OSS-RC Account\n");
        AppConf.setOSS_IP(ossIP);
        AppConf.setOSS_USER(ossUser);
        AppConf.setOSS_PASS(ossPASS);
        output.append("Finished Configuring OSS-RC Account\n");
        output.append("Configuring Disaster Recovery Plan\n");
        AppConf.configureDR(drConf);
        AppConf.setWorkingDirectory(workDir);
        output.append("Finished Configuring Disaster Recovery Plan: "+AppConf.getDRDetails().size());
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Create Directories">
        String workingDir = AppConf.getWorkingDirectory()+"\\DR_"+AppConf.getMydate();
        String printoutsDir = AppConf.getWorkingDirectory()+"\\DR_"+AppConf.getMydate()+"\\Printouts";
        String recoveryDir = AppConf.getWorkingDirectory()+"\\DR_"+AppConf.getMydate()+"\\Recovery";
        new File(workingDir).mkdirs();
        new File(printoutsDir).mkdirs();
        new File(recoveryDir).mkdirs();
        File tgFile = new File(workingDir+"\\TG_SITE.csv");
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Collect Data">
        ExecutorService executor;
        executor= Executors.newCachedThreadPool();
        for (Map.Entry<String, Integer> bsc : BSCs.entrySet()) {
            executor.submit(new BSCDataCollector().init(bsc.getKey(),printoutsDir,output));
        }
        executor.shutdown();
        while(!executor.isTerminated()){}
        output.append("Executer finished");
//</editor-fold>

        loopOnPrintouts(printoutsDir);
        ScriptCreator.createRecoveryScripts(recoveryDir, SITES,TG_STATUS,tgFile);
        return true;
    }
    
    
}
