/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadtest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author zhangshuting
 */
public class DownloadTest extends JFrame {
    public static void main(String[] args) throws MalformedURLException, IOException {
        new DownloadTest();
    }
    public DownloadTest() throws MalformedURLException, IOException{
        super("Download");
        JPanel Panel = new JPanel();
        JTextArea ta= new JTextArea(20,20);
        Panel.add(ta);
        JButton bt = new JButton("WEATHER"); 
        Panel.add(bt);
        DefaultListModel listModel = new DefaultListModel();
        JList list = new JList(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        Panel.add(listScrollPane);
        add(Panel);
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        bt.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                ta.append("www.cwb.gov.tw"+"\n");
                try {
                    java.net.URL url = new java.net.URL("www.cwb.gov.tw");
                    Scanner input = new Scanner(url.openStream());
                    while(input.hasNext()){
                        ta.append(input.nextLine()+"\n");
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(DownloadTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DownloadTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        javax.swing.Timer t = new javax.swing.Timer(600000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    java.net.URL url = new java.net.URL("www.cna.com.tw");
                    Scanner input = new Scanner(url.openStream());
                    while(input.hasNext()){
                        listModel.addElement(input.nextLine()+"\n");
                    }   } catch (MalformedURLException ex) {
                    Logger.getLogger(DownloadTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DownloadTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        });    
    }
}
