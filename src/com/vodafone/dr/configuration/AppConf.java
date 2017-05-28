/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.configuration;

import com.vodafone.dr.entities.DR;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 *
 * @author Admin
 */
public class AppConf {
    
    //<editor-fold defaultstate="collapsed" desc="OSS Details">
    private static String OSS_IP;
    private static String OSS_USER;
    private static String OSS_PASS;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Distaster Details">
    private static TreeMap<String,DR> DRDetails = new TreeMap<String,DR>();
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="App Conf">
    private static String workingDirectory;
    private static TreeMap<String,Integer> BSCs = new TreeMap<String,Integer>();
    private static String mydate = (java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())).replaceAll(":", "");
//</editor-fold>
    public static void configureOSS(String path){
        try {
            RandomAccessFile raf = new RandomAccessFile(new File(path), "r");
            String line = null;
            while((line=raf.readLine())!=null){
                if(line.contains("IP")){
                    OSS_IP = line.split("\\|")[1];
                }
                if(line.contains("USER")){
                    OSS_USER = line.split("\\|")[1];
                }
                if(line.contains("PASS")){
                    OSS_PASS = line.split("\\|")[1];
                }
                if(line.contains("WORKING_DIRECTORY")){
                     workingDirectory = line.split("\\|")[1];
                }
                if(line.contains("BSC")){
                     BSCs.put(line.split("\\|")[1],Integer.MIN_VALUE);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppConf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppConf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void configureDR(String path){
        try {
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File(path)));
            Sheet sheet = null;
            DR dr = null;
            int siteIdx = 0;
            int sourceMtxIdx = 0;
            int sourceHubIdx = 0;
            int targetMtxIdx = 0;
            int targetBscIdx = 0;
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                sheet = workbook.getSheetAt(i);
                for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                    if(j==1){
                    for (int k = 0; k < sheet.getRow(j).getLastCellNum(); k++) {
                            if(sheet.getRow(j).getCell(k).getStringCellValue().toLowerCase().contains("site")){
                                siteIdx = k;
                            }
                            else if(sheet.getRow(j).getCell(k).getStringCellValue().toLowerCase().contains("sourec mtx")){
                               sourceMtxIdx = k;
                            }
                            else if(sheet.getRow(j).getCell(k).getStringCellValue().toLowerCase().contains("source hub")){
                               sourceHubIdx = k;
                            }
                            else if(sheet.getRow(j).getCell(k).getStringCellValue().toLowerCase().contains("target mtx")){
                               targetMtxIdx = k;
                            }
                            else if(sheet.getRow(j).getCell(k).getStringCellValue().toLowerCase().contains("target bsc")){
                               targetBscIdx = k;
                            }
                    }
                    }else{
                    dr = new DR();
                    try{
                    sheet.getRow(j).getCell(siteIdx).setCellType(CellType.STRING);
                    }catch(Exception e){
                        System.out.println("Error in Site ");
                    }
                    try{
                    sheet.getRow(j).getCell(sourceHubIdx).setCellType(CellType.STRING);
                    }catch(Exception e){
                        System.out.println("Error in Source HUB");
                    }
                    try{
                    sheet.getRow(j).getCell(sourceMtxIdx).setCellType(CellType.STRING);
                    }catch(Exception e){
                        System.out.println("error in Source MTX");
                    }
                    try{
                    sheet.getRow(j).getCell(targetBscIdx).setCellType(CellType.STRING);
                    }catch(Exception e){
                        System.out.println("error in targe BSC");
                        System.out.println("Sheet: "+sheet.getSheetName());
                    }
                    try{
                    sheet.getRow(j).getCell(targetMtxIdx).setCellType(CellType.STRING);
                    }catch(Exception e){
                        System.out.println("error in target MTX");
                    }
                    dr.setSiteName(sheet.getRow(j).getCell(siteIdx).getStringCellValue().trim());
                    dr.setSourceHUB(sheet.getRow(j).getCell(sourceHubIdx).getStringCellValue().trim());
                    dr.setSourceMTX(sheet.getRow(j).getCell(sourceMtxIdx).getStringCellValue().trim());
                    dr.setTargetBSC(sheet.getRow(j).getCell(targetBscIdx).getStringCellValue().trim());
                    dr.setTargetMTX(sheet.getRow(j).getCell(targetMtxIdx).getStringCellValue().trim());
                    DRDetails.put(dr.getSiteName(), dr);
                    
                    }
                }
                System.out.println("Sheet "+sheet.getSheetName()+" Completed");
            }
        } catch (IOException ex) {
            Logger.getLogger(AppConf.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static String getOSS_IP() {
        return OSS_IP;
    }

    public static void setOSS_IP(String OSS_IP) {
        AppConf.OSS_IP = OSS_IP;
    }

    public static String getOSS_USER() {
        return OSS_USER;
    }

    public static void setOSS_USER(String OSS_USER) {
        AppConf.OSS_USER = OSS_USER;
    }

    public static String getOSS_PASS() {
        return OSS_PASS;
    }

    public static void setOSS_PASS(String OSS_PASS) {
        AppConf.OSS_PASS = OSS_PASS;
    }

    public static TreeMap<String, DR> getDRDetails() {
        return DRDetails;
    }

    public static void setDRDetails(TreeMap<String, DR> DRDetails) {
        AppConf.DRDetails = DRDetails;
    }

    public static String getWorkingDirectory() {
        return workingDirectory;
    }

    public static void setWorkingDirectory(String workingDirectory) {
        AppConf.workingDirectory = workingDirectory;
    }
    
    
    public static TreeMap<String, Integer> getBSCs() {
        return BSCs;
    }

    public static String getMydate() {
        return mydate;
    }

    
    
    
}
