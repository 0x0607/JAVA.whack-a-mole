package windows;
import java.util.Random;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class start{
    static int buttonNumber=50;
    static int score=0;
    static int highLightButton=0;
    static int delayTime=1000;
    static boolean debugMode=true;
    public start() {
        int yLayer=0;
        int i=0;
        JFrame j_Frame=new JFrame();
        j_Frame.setSize(500,300);
        j_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j_Frame.getContentPane().setLayout(new GridBagLayout());;
        JLabel j_Score = new JLabel("Score = "+String.valueOf(score));
        j_Score.setForeground(Color.CYAN);
        j_Score.setBackground(Color.BLACK);
        j_Score.setOpaque(true);
        i=0;
        while(i<buttonNumber){
            JButton j_button = new JButton(""+i);
            if(i%10==0) yLayer++;//每十個換一行
            GridBagConstraints objGridBag = new GridBagConstraints();
            objGridBag.gridx=i%10;
            objGridBag.gridy=yLayer;
            objGridBag.gridwidth=1;
            objGridBag.gridheight=1;
            objGridBag.weightx=1;
            objGridBag.weighty=1;
            objGridBag.fill=GridBagConstraints.BOTH;
            objGridBag.anchor=GridBagConstraints.WEST;
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
            j_Frame.add(j_button,objGridBag);
        
            Thread j_Thread = new Thread(new Runnable() {
                public void run(){
                    try {
                        while(true){
                            Thread.sleep(delayTime/10);
                            if(j_button.getText().equals(String.valueOf(highLightButton))) j_button.setBackground(Color.MAGENTA);
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
        //跟上面的objGridBag不衝突
        GridBagConstraints objGridBag = new GridBagConstraints();
        objGridBag.gridx=0;
        objGridBag.gridy=6;
        objGridBag.gridwidth=10;
        objGridBag.gridheight=1;
        objGridBag.weightx=1;
        objGridBag.weighty=1;
        objGridBag.fill=GridBagConstraints.BOTH;
        objGridBag.anchor=GridBagConstraints.WEST;
        j_Frame.add(j_Score,objGridBag);
        j_Frame.setVisible(true);
    }
    public static void main(String[] args) {
        new start();   
    }
}