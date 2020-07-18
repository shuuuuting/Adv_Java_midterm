/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author zhangshuting
 */
public class Dictionary extends JFrame {
    
    public static void main(String[] args) throws FileNotFoundException {
        new Dictionary();
    }
    
    public Dictionary() throws FileNotFoundException {
        super("Dictionary");
        Scanner scanner = new Scanner(new FileReader("Dictionary.txt"));

        HashMap<String,String> map = new HashMap<String,String>();

        while (scanner.hasNextLine()) {
            String[] columns = scanner.nextLine().split("-");
            map.put(columns[0],columns[1]);
        }
        TreeMap<String, String> treeMap = new TreeMap<>(map);
        Set<Entry<String, String>> mappings = treeMap.entrySet();
        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JPanel(),new JPanel());
        splitpane.setDividerLocation(150);
        JPanel Panel = new JPanel();
        JTextField txt = new JTextField(40);
        Panel.add(txt);
        JButton bt = new JButton("Search"); 
        Panel.add(bt);
        JTextArea ta = new JTextArea(50, 48); 
        JScrollPane scroll = new JScrollPane (ta);
        Panel.add(scroll);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        add(Panel, BorderLayout.NORTH);
        txt.setText("Please enter any word you want to know. Enter * to see all words.");
        txt.selectAll();
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        bt.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                if(txt.getText().length() == 0){
                    JOptionPane.showMessageDialog(null,"no data");
                }
                else if(txt.getText().equals("*")){
                    for(Entry<String, String> mapping : mappings){
                        ta.append(mapping.getKey() + " -- " + mapping.getValue()+"\n");
                    }
                }
                else if(map.get((txt.getText()))==null){
                    ta.setText("No descriptions about this!");
                }
                else{
                    ta.setText(map.get((txt.getText())));
                }
            } 
	}); 
    } 
}