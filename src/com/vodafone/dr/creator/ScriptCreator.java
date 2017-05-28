/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.creator;

import com.vodafone.dr.entities.RSITE;
import com.vodafone.dr.entities.TG;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amr
 */
public class ScriptCreator {
    
    public static void createRecoveryScripts(String destination,TreeMap<String, RSITE> RSITES
                                        , TreeMap<String, TreeMap<String,Boolean>> TG_STATUS, File tgSite){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(tgSite);
            pw.println("SITE,BSC,TARGET_BSC,TG,TARGET_TG,CHANGED");
            TreeMap<String, RSITE> ADJUSTED = new TreeMap<String, RSITE>();
            System.out.println("Adjusting Sites");
            for (Map.Entry<String, RSITE> site : RSITES.entrySet()) {
                if(site.getValue().getTargetBSC()!=null){
                    site.getValue().adjustSite(TG_STATUS,pw);
                    ADJUSTED.put(site.getKey(), site.getValue());
                }
            }   writeFiles(destination, ADJUSTED);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScriptCreator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
   
    
    private static void writeFiles(String destination,TreeMap<String, RSITE> RSITES){
        for (Map.Entry<String, RSITE> site : RSITES.entrySet()) {
            if(site.getValue().getTargetBSC()!=null){
            new File(destination+"\\"+site.getValue().getSourceMTX()).mkdirs();
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
			File file = new File(destination+"\\"+site.getValue().getSourceMTX()+"\\"
                        +site.getValue().getTargetMTX()+"_"+site.getValue().getTargetBSC()+".recovery");
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
                        
                       
                        bw.write("\n"+site.getValue().getSiteCreationScript());    
                       
            
            } catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
            }   
        }
    }
}
