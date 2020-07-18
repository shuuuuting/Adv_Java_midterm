/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm;

/**
 *
 * @author zhangshuting
 */
import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Calendar;
import java.util.Date;
import static javafx.scene.paint.Color.color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;
public class Alarm extends JFrame{
    float alpha = 1.0f;
    private Color color = Color.BLACK;
    public static void main(String[] args){
        new Alarm();
        
    }
    public Alarm() {
        super("clock");
        
        JPanel mypanel = new JPanel();
        mypanel.setLayout(new FlowLayout());
        JLabel time=new JLabel();
        time.setFont(new Font("Arial", Font.PLAIN, 100)); 
        mypanel.add(time);
        add(mypanel);
        mypanel.setOpaque(false);
        setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        setSize(600,200);
        setLocation(800, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        javax.swing.Timer t = new javax.swing.Timer(100, new ActionListener(){
            public void actionPerformed(ActionEvent e){
            Calendar current = Calendar.getInstance();
            int hh = current.get(Calendar.HOUR_OF_DAY);
            int mm = current.get(Calendar.MINUTE);
            int ss = current.get(Calendar.SECOND);
            time.setText("" + hh + ":" + mm + ":" + ss);
            }
        });
        t.start();
        MouseInputAdapter handler = new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                String input=JOptionPane.showInputDialog("Please enter alarm time.(24hr-format)");
                String tokens[] = input.split("\\:");
                int h=Integer.parseInt(tokens[0]);
                int m=Integer.parseInt(tokens[1]);
                int s=Integer.parseInt(tokens[2]);
                javax.swing.Timer t = new javax.swing.Timer(100, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        Calendar current = Calendar.getInstance();
                        int hh = current.get(Calendar.HOUR_OF_DAY);
                        int mm = current.get(Calendar.MINUTE);
                        int ss = current.get(Calendar.SECOND);
                        if(hh==h && mm==m && ss==s){
                                javax.swing.Timer t = new javax.swing.Timer(100, new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    alpha -= 0.01;
                                    color=new Color(1.0f, 1.0f, 0.0f, alpha);
                                    time.setForeground(color);
                                }
                                });
                                t.start();
                        }
                    }
                });
                t.start();
            }        
        };
        addMouseListener(handler);
    }
}         
