/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotprojesi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Engin Uysal
 */
public class Pencere extends JPanel{
    static JFrame frame;
    boolean engelDurumu;
    Problem1 problem1;
    Problem2 problem2;
    Problem3 problem3;
    int[][] engeller;
    int sayac;
    int[][] engelDizisi;
    int[] baslangic;
    int[] sonKonum;
    int[] kol;
    int[] sabitKonum;
    int problem;
    Graphics g;
    
    Pencere(){
        
    }
    
    Pencere(int problem){
                this.problem = problem;
                switch(problem){
                    case 1:
                        problem1 = new Problem1();
                        engeller = Problem1.engeller;
                        baslangic = Problem1.baslangic;
                        break;
                    case 2:
                        problem2 = new Problem2();
                        baslangic = Problem2.baslangic;
                        kol = Problem2.kol; // içine değer verilmezse
                        break;
                    case 3:
                        problem3 = new Problem3();
                        baslangic = Problem3.baslangic;
                        kol = Problem3.kol;
                        sonKonum = Problem3.sonKonum;
                        break;
                }      
        
        
        frame = new JFrame();
        frame.setTitle("Mozart Of Chess");
        frame.setVisible(true);
        
        setPreferredSize(new Dimension(600, 600));
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        
        
    }
    
    
    @Override
    public void paint(Graphics g){
        this.g = g;
        // 20x20 ızgara çiz
        int h = getHeight()/20;
        int w = getWidth()/20;
        g.setColor(Color.GRAY);
        
        // dış çerçeveyi çiz
        for(int i = 0; i < 6; i++)
        g.drawRect(i, i, getWidth()-2*i, getHeight()-2*i);
        
        // ızgaraları çiz
        g.setColor(Color.BLACK);
        for(int i = 0; i < 19; i++){
            g.drawLine(w, 0, w, getHeight());
            g.drawLine(0, h, getWidth(), h);
            w = w+getWidth()/20;
            h = h+getHeight()/20;
        }
           
        
       if(problem == 1){
           // engel çiz
           engelCiz(1);
       }
       
       if(problem == 3){
           // engel çiz
           engelCiz(3);
       }
       
       
       //başlangıç konumu çizimi
        if(problem == 1){
        g.setColor(Color.YELLOW);
        for(int i = 0; i < getWidth()/20; i++){
            g.drawLine(baslangic[1]*getWidth()/20 + i, baslangic[0]*getHeight()/20,
                    baslangic[1]*getWidth()/20 + i, baslangic[0]*getHeight()/20+getHeight()/20);
            }
        }
        
 
        // kol konum çizimi
        if(problem == 2){
           g.setColor(Color.ORANGE);
        for(int i = 0; i < getWidth()/20; i++){
            g.drawLine(kol[1]*getWidth()/20 + i, kol[0]*getHeight()/20,
                    kol[1]*getWidth()/20 + i, kol[0]*getHeight()/20+getHeight()/20);
            
           baslangicCiz();
        }
        }
        
        //oturmadıysa
        if((problem == 3) && !Problem3.oturduMu){
            engelCiz(3);
            //robotun kolunu çiz
            g.setColor(Color.ORANGE);
            for(int i = 0; i < getWidth()/20; i++){
                g.drawLine(sonKonum[1]*getWidth()/20 + i, sonKonum[0]*getHeight()/20,
                sonKonum[1]*getWidth()/20 + i, sonKonum[0]*getHeight()/20+getHeight()/20);
            } 

           //robotun X'ini(kendisini) çiz
           g.setColor(Color.BLUE);
           g.drawLine(sonKonum[1]*getWidth()/20, sonKonum[0]*getHeight()/20, (sonKonum[1]+1)*getWidth()/20, (sonKonum[0]+1)*getHeight()/20);
           g.drawLine((sonKonum[1]+1)*getWidth()/20, sonKonum[0]*getHeight()/20, sonKonum[1]*getWidth()/20, (sonKonum[0]+1)*getHeight()/20);
           
           sabitKonum = sonKonum.clone();
        }
        
        // oturduysa
        if((problem == 3) && Problem3.oturduMu){
            
            //robotun kolunu çiz
            g.setColor(Color.ORANGE);
            for(int i = 0; i < getWidth()/20; i++){
                g.drawLine(sonKonum[1]*getWidth()/20 + i, sonKonum[0]*getHeight()/20,
                sonKonum[1]*getWidth()/20 + i, sonKonum[0]*getHeight()/20+getHeight()/20);
            }
            
           //robotun X'ini(kendisini) çiz
           
           g.setColor(Color.BLUE);
           g.drawLine(sabitKonum[1]*getWidth()/20, sabitKonum[0]*getHeight()/20, (sabitKonum[1]+1)*getWidth()/20, (sabitKonum[0]+1)*getHeight()/20);
           g.drawLine((sabitKonum[1]+1)*getWidth()/20, sabitKonum[0]*getHeight()/20, sabitKonum[1]*getWidth()/20, (sabitKonum[0]+1)*getHeight()/20);
        }
        
       
        
}// paint sonu
   
    public static void ekraniGoster(){
        frame.setVisible(true);
    }
    
    public static void ekraniGosterme(){
        frame.setVisible(false);
    }
    
    public void baslangicCiz(){
        g.setColor(Color.BLUE);
        
        g.drawLine(baslangic[1]*getWidth()/20, baslangic[0]*getHeight()/20, (baslangic[1]+1)*getWidth()/20, (baslangic[0]+1)*getHeight()/20);
        g.drawLine((baslangic[1]+1)*getWidth()/20, baslangic[0]*getHeight()/20, baslangic[1]*getWidth()/20, (baslangic[0]+1)*getHeight()/20);
        
        g.setColor(Color.YELLOW);
    }
    
    public void engelCiz(int problem){
        if(problem == 1)
            engeller = Problem1.engeller;
        else if(problem == 3)
            engeller = Problem3.engeller;
        
        g.setColor(Color.RED);
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(engeller[i][j] == -1){
                   g.drawLine(j*getWidth()/20, i*getHeight()/20, (j+1)*getWidth()/20, (i+1)*getHeight()/20);
                   g.drawLine((j+1)*getWidth()/20, i*getHeight()/20, j*getWidth()/20, (i+1)*getHeight()/20);
                }
            }
        }
    }
   
    
} // class sonu
