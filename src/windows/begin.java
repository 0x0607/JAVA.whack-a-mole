package windows;
import java.util.Random;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class begin{
    static int buttonNumber=50;
    static int score=0;
    static int highLightButton=0;
    static int delayTime=1000;
    static boolean debugMode=true;
    public begin() {
        int i=0;
        JFrame j_Frame=new JFrame();
        j_Frame.setSize(500,300);
        j_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j_Frame.getContentPane().setLayout(new FlowLayout());;
        JLabel j_Score = new JLabel("Score = "+String.valueOf(score));
        i=0;
        while(i<=buttonNumber){
            JButton j_button = new JButton(""+i);
            j_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(j_button.getText().equals(String.valueOf(highLightButton))){
                        score++;
                        j_Score.setText("Score = "+String.valueOf(score));
                        j_Frame.setTitle("Score = "+String.valueOf(score));
                        highLightButton=new Random().nextInt(buttonNumber);
                        if(debugMode) System.out.println(score);
                    }
                }
            });
            j_Frame.add(j_button);
        
            Thread j_Thread = new Thread(new Runnable() {
                public void run(){
                    try {
                        while(true){
                            Thread.sleep(delayTime/10);
                            if(j_button.getText().equals(String.valueOf(highLightButton))) j_button.setBackground(Color.yellow);
                            else j_button.setBackground(Color.gray);
                        }
                    } catch (InterruptedException e) {}
                }
            });
            j_Thread.start();
        i++;}
        Thread f_Thread = new Thread(new Runnable() {
            public void run(){
                try{
                    while(true){
                        Thread.sleep(delayTime);
                        highLightButton=new Random().nextInt(buttonNumber);
                    }
                }
                catch (InterruptedException e){e.printStackTrace();}
            }
        });
        f_Thread.start();
        j_Frame.add(j_Score);
        j_Frame.setVisible(true);
    }
    public static void main(String[] args) {
        new begin();   
    }
}