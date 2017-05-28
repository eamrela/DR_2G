/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.collector;

import com.jscape.inet.ssh.Ssh;
import com.jscape.inet.ssh.SshConnectedEvent;
import com.jscape.inet.ssh.SshDataReceivedEvent;
import com.jscape.inet.ssh.SshDisconnectedEvent;
import com.jscape.inet.ssh.SshException;
import com.jscape.inet.ssh.SshListener;
import com.jscape.inet.ssh.SshScript;
import com.jscape.inet.ssh.SshTask;
import com.jscape.inet.ssh.util.SshParameters;
import com.vodafone.dr.configuration.AppConf;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Amr
 */
public class BSCDataCollector implements Runnable, SshListener {
    
    private String BSCName;
    private String Destination;
    private JTextArea output;
    private final String PSTUDATA = "RRPTP:PSTU=ALL;";
    private final String SCGRDATA = "RRSCP:SCGR=ALL;";
    private final String SCGRDATA_2 = "RRSGP:SCGR=ALL;";
    private final String TGDATA = "RXMOP:MOTY=RXOTG;";
    private final String CFDATA = "RXMOP:MOTY=RXOCF;";
    private final String ISDATA = "RXMOP:MOTY=RXOIS;";
    private final String TFDATA = "RXMOP:MOTY=RXOTF;";
    private final String TRXDATA = "RXMOP:MOTY=RXOTRX;";
    private final String TXDATA = "RXMOP:MOTY=RXOTX;";
    private final String RXDATA = "RXMOP:MOTY=RXORX;";
    private final String MCTRDATA = "RXMOP:MOTY=RXOMCTR;";
    private final String CELLDATA = "RXTCP:MOTY=RXOTG;";
    private final String CONDATA = "RXMOP:MOTY=RXOCON;";
    private final String ODPDATA = "RXMOP:MOTY=RXODP;";
    private final String TGCHECK = "RXMSP:MOTY=RXOTG;";
    private final String OSS_SHELL = ">";
    private final String BSC_SHELL = "<";
    private StringBuilder builder;

    public String collectPrintout(){
            builder = new StringBuilder();
        try {
            Ssh ssh = null;
            SshParameters sshParams = new SshParameters(AppConf.getOSS_IP(),AppConf.getOSS_USER(),AppConf.getOSS_PASS());
            ssh = new Ssh(sshParams);
            ssh.setEcho(true);
            ssh.addSshListener(this);
            SshScript script = new SshScript(ssh);
            
            SshTask connectToBSC = new SshTask(OSS_SHELL,"eaw "+BSCName,BSC_SHELL);
            script.addTask(connectToBSC);
            //pstu
            SshTask PSTU = new SshTask(BSC_SHELL,PSTUDATA,BSC_SHELL);
            script.addTask(PSTU);
            //scgr
            SshTask SCGR_1 = new SshTask(BSC_SHELL,SCGRDATA,BSC_SHELL);
            script.addTask(SCGR_1);
            SshTask SCGR_2 = new SshTask(BSC_SHELL,SCGRDATA_2,BSC_SHELL);
            script.addTask(SCGR_2);
            //tg
            SshTask TG = new SshTask(BSC_SHELL,TGDATA,BSC_SHELL);
            script.addTask(TG);
            //cf
            SshTask CF = new SshTask(BSC_SHELL,CFDATA,BSC_SHELL);
            script.addTask(CF);
            //is
            SshTask IS = new SshTask(BSC_SHELL,ISDATA,BSC_SHELL);
            script.addTask(IS);
            //tf
            SshTask TF = new SshTask(BSC_SHELL,TFDATA,BSC_SHELL);
            script.addTask(TF);
            //trx
            SshTask TRX = new SshTask(BSC_SHELL,TRXDATA,BSC_SHELL);
            script.addTask(TRX);
            //tx
            SshTask TX = new SshTask(BSC_SHELL,TXDATA,BSC_SHELL);
            script.addTask(TX);
            //rx
            SshTask RX = new SshTask(BSC_SHELL,RXDATA,BSC_SHELL);
            script.addTask(RX);
            //mctr
            SshTask MCTR = new SshTask(BSC_SHELL,MCTRDATA,BSC_SHELL);
            script.addTask(MCTR);
            //cell
            SshTask CELL = new SshTask(BSC_SHELL ,CELLDATA,BSC_SHELL);
            script.addTask(CELL);
            //CON
            SshTask CON = new SshTask(BSC_SHELL ,CONDATA,BSC_SHELL);
            script.addTask(CON);
            //ODP
            SshTask ODP = new SshTask(BSC_SHELL ,ODPDATA,BSC_SHELL);
            script.addTask(ODP);
            //TG-CHECK
            SshTask TG_CHECK = new SshTask(BSC_SHELL ,TGCHECK,BSC_SHELL);
            script.addTask(TG_CHECK);
            
            ssh.connect();
            while(!script.isComplete()) {
                Thread.sleep(100);
              }
            
        } catch (SshException ex) {
            Logger.getLogger(BSCDataCollector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BSCDataCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return builder.toString();
    }
    
    @Override
    public void run() {
        WriteTheFile(collectPrintout(), Destination+"\\"+BSCName+".log", true);
////
//        try {
//            copyFileUsingChannel(new File("C:\\tmp\\DR\\"+BSCName+".log"),new File(Destination+"\\"+BSCName+".log"));
//        } catch (IOException ex) {
//            Logger.getLogger(BSCDataCollector.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public void connected(SshConnectedEvent sce) {
        System.out.println("Connected to "+BSCName);
        if(output!=null){
            output.append("Connected to "+BSCName);
        }
    }

    @Override
    public void disconnected(SshDisconnectedEvent sde) {
        System.out.println("Disconnected from "+BSCName);
        if(output!=null){
            output.append("Disconnected from "+BSCName);
        }
    }

    @Override
    public void dataReceived(SshDataReceivedEvent sdre) {
//        System.out.print(sdre.getData());
        builder.append(sdre.getData());
    }
    
    public BSCDataCollector init(String bscName,String destination,JTextArea output){
        this.BSCName = bscName;
        this.Destination = destination;
        return this;
    }
    
    public void WriteTheFile(String FileContent,String Destination,boolean Append){
        
      File file = new File(Destination);

		boolean b = false;


		if (!file.exists()) {
            try {
                
                b = file.createNewFile();
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, Append)));
                
                
                out.println(FileContent);
                out.flush();
                  
                
                
                out.close();
            
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		}else{
            PrintWriter out = null;
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter(file, Append)));
               
                
                out.println(FileContent);
                
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                out.close();
            }
                    
                } 
                
                System.out.println("File Created!");
                if(output!=null){
                    output.append("File Created!");
                }
          
        
    }
    
    private static void copyFileUsingChannel(File source, File dest) throws IOException {
    FileChannel sourceChannel = null;
    FileChannel destChannel = null;
    try {
        sourceChannel = new FileInputStream(source).getChannel();
        destChannel = new FileOutputStream(dest).getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
       }finally{
           sourceChannel.close();
           destChannel.close();
   }
}
}
