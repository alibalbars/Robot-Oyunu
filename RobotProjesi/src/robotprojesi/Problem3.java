/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotprojesi;

import javax.swing.JOptionPane;
import static robotprojesi.Problem1.baslangic;
import static robotprojesi.Problem1.engelSayisi;
import static robotprojesi.Problem1.engeller;
//import static robotprojesi.Problem1.sonKonum;
import static robotprojesi.RobotProjesi.dogrula;

/**
 *
 * @author Engin Uysal
 */
public class Problem3 {
       
    
    static float zaman = 0; 
    int motorSayisi;
    Object[] options = {"Evet", "Hayır"};
    static int[][] engeller = new int[20][20];
    int engelSayisi;
    int[][] engelDizisi;
    String koordinat;
    int x, y;
    int n;
    float azamiYukKapasitesi;
    static int[] baslangic = new int[2];
    static int[] kol = baslangic;
    static int[] sonKonum;
    static int[] sabitKonum = new int[2];
    String hareketliKisim;
    String hareketsizKisim;
    String[] hareketliRobotlar = {"tekerlekli", "paletli", "spider"};
    String[] hareketsizRobotlar = {"seri", "paralel"};
    
    float gezinmeHizi;
    float yukMiktari;
    float tasimaHizi;
    float kolUzunlugu;
    
    float oturmaSuresi;
    float kolaGecmeSuresi;
    
    String robotTipi;
    String yon;
    int yonParca1;
    String yonParca2;
    
    boolean engelVarmi;
    static boolean oturduMu = false;
    boolean birKere;
    TekerlekliRobot tekerlekliRobot;
    PaletliRobot paletliRobot;
    SpiderRobot spiderRobot;
    
    GezginManipulatorRobot gezginManipulatorRobot;
    double uzaklik;
    
    Problem3(){
        
    }
   
    
    
    Problem3(String robotTipi){
        this.robotTipi = robotTipi;
        
        hareketliKisim = JOptionPane.showInputDialog("Hibrit robot için hareketli kısmı").trim();
        while(!dogrula(hareketliKisim, hareketliRobotlar)){
                JOptionPane.showMessageDialog(null, "Yanlış robot adı girdiniz");
                hareketliKisim = JOptionPane.showInputDialog("Hibrit robot için hareketli kısmı").trim();
             }
        System.out.println("Hareketli: " + hareketliKisim);
        
        hareketsizKisim = JOptionPane.showInputDialog("Hibrit robot için hareketsiz kısmı").trim();
        while(!dogrula(hareketsizKisim, hareketsizRobotlar)){
                JOptionPane.showMessageDialog(null, "Yanlış robot adı girdiniz");
                hareketsizKisim = JOptionPane.showInputDialog("Hibrit robot için hareketsiz kısmı").trim();
             }
        System.out.println("Hareketsiz: " + hareketsizKisim);
        
        //spider değilse motor sayısını al
       if(!hareketliKisim.equalsIgnoreCase("spider"))
       motorSayisi = Integer.parseInt(JOptionPane.showInputDialog("Motor sayısını giriniz"));
       System.out.println("Motor Sayısı" + motorSayisi);
       
       // gezinme hızını al
       gezinmeHizi = Float.parseFloat(JOptionPane.showInputDialog("Gezinme hızını giriniz"));
       
       
       if(hareketliKisim.equals("tekerlekli")){
           TekerlekliRobot.motorSayisi = motorSayisi;
           TekerlekliRobot.setMax(gezinmeHizi);
           TekerlekliRobot.setMin(gezinmeHizi);
       }
           
       if(hareketliKisim.equals("paletli")){
           PaletliRobot.motorSayisi = motorSayisi;
           PaletliRobot.setMax(gezinmeHizi);
           PaletliRobot.setMin(gezinmeHizi);
       }
       
       if(hareketliKisim.equals("spider")){
           SpiderRobot.setMax(gezinmeHizi);
           SpiderRobot.setMin(gezinmeHizi);
       }
          
       
       
        
    
       // taşıma hızını al
       tasimaHizi = Float.parseFloat(JOptionPane.showInputDialog("Taşıma hızını giriniz"));
       
       // kol uzunluğunu al
       kolUzunlugu = Float.parseFloat(JOptionPane.showInputDialog("Kol uzunluğunu giriniz"));
        System.out.println("Kol uzunluğu: " + kolUzunlugu);
        
       // oturma süresini al
       oturmaSuresi = Float.parseFloat(JOptionPane.showInputDialog("Oturma süresini giriniz"));
       
       // kola geçme süresi
       kolaGecmeSuresi= Float.parseFloat(JOptionPane.showInputDialog("Kola geçme süresini giriniz"));
       
 
        azamiYukKapasitesi = Float.parseFloat(JOptionPane.showInputDialog("Azami yük kapasitesini giriniz").trim());
        yukMiktari = Float.parseFloat(JOptionPane.showInputDialog("Yük miktarını giriniz").trim());
        while(yukMiktari > azamiYukKapasitesi){
            JOptionPane.showMessageDialog(null, "Yük miktarı azami yük kapasitesinden büyük olamaz");
            yukMiktari = Float.parseFloat(JOptionPane.showInputDialog("Yük miktarını giriniz").trim());
        }

        
        n = JOptionPane.showOptionDialog(null, "Engel girecek misiniz?", "Input", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        //Hayır
        if(n == 1){
            
            engelSayisi = 1;
            engelDizisi = new int[engelSayisi][2];
            
          //Evet
        } else if(n == 0){
            engelSayisi = Integer.parseInt(JOptionPane.showInputDialog("Kaç engel gireceksiniz").trim());
            engelDizisi = new int[engelSayisi][2];
                
            for(int i = 0; i < engelSayisi; i++){
                koordinat = JOptionPane.showInputDialog("Kaçıncı satır kaçıncı sütun").trim();
                x = Integer.parseInt(RobotProjesi.ilk(koordinat));
                y = Integer.parseInt(RobotProjesi.son(koordinat));
                System.out.println("x: " + x + " " + "y: " + y);
                engeller[x-1][y-1] = -1;
            
            }
            // engelDizisi'ni doldur
            int k = 0;
            for(int i = 0; i<20; i++){
                for(int j = 0; j<20; j++){
                    if(engeller[i][j] == -1){
                        engelDizisi[k][0] = i; // bunlar kullanıcının girdiğinden 1 eksik
                        engelDizisi[k][1] = j;
                        k++;
                    }
                    
                }
            
            }
        
            // engel dizisi yazdir
            System.out.println("Engel dizisi: ");
            for(int i = 0; i<engelSayisi; i++){
                for(int j = 0; j<2; j++){
                    System.out.print(engelDizisi[i][j]);
                }
                System.out.println();
            }
            
        }
        
       // başlangıç konumunu al diziye at
       koordinat = JOptionPane.showInputDialog("Başlangıç konumunu giriniz [satır-sütun]");
       baslangic[0] = Integer.parseInt(RobotProjesi.ilk(koordinat))-1;
       baslangic[1] = Integer.parseInt(RobotProjesi.son(koordinat))-1;
       
       sonKonum = baslangic;
       
       
       
        
       Pencere pencere = new Pencere(3);
       Islemler();
    
    } // contructor sonu
    
    public void Islemler(){
        // hareket ettirme
        while(true){
//            System.out.println("Başlangıç: " + (baslangic[0]+1) +" "+ (baslangic[1]+1)); 
            
           yon = JOptionPane.showInputDialog("Hareket yönü giriniz [Durdurmak için -1]");
           if(yon.equals("-1") && !oturduMu){
               JOptionPane.showMessageDialog(null, "Robot oturdu");
               oturduMu = true;
               sabitKonum = sonKonum.clone();
               System.out.println("Sabit konum: " + (sabitKonum[0]+1) + " " + (sabitKonum[1]+1));
               zaman += oturmaSuresi + kolaGecmeSuresi;
           }
           else if(yon.equals("-1") && oturduMu){
               JOptionPane.showMessageDialog(null, "Sonuç:\nGeçen süre: " + zaman + " sn");
               Pencere.ekraniGosterme();
           }
           else {
               yonParca1 = Integer.parseInt(RobotProjesi.ilk(yon));
               yonParca2 = RobotProjesi.son(yon);
               System.out.println("Son: " + (sonKonum[0]+1) +" "+ (sonKonum[1]+1));
               // son konumu yenile ve taşma kontrolü yap
               switch(yonParca2){
                   case "ileri":
                       if((sonKonum[0] + yonParca1) > 19){
                           JOptionPane.showMessageDialog(null,"Robot haritadan taştı!");
                           
                           continue;
                       }
                       else{
                           
                           for(int i=0; i < yonParca1; i++){ // ilerleme sayısı kadar
                               if(oturduMu){
                                   uzaklik = Math.sqrt(Math.pow(sabitKonum[0]-(sonKonum[0]+1), 2)+
                                       Math.pow(sabitKonum[1]-sonKonum[1], 2))*10;
                                   System.out.println("Uzaklık: " + uzaklik);
                                   
                                       
                               }
                               else{
                                   uzaklik = 0;
                               }
                               
                               if(uzaklik>kolUzunlugu){
                                   JOptionPane.showMessageDialog(null, "Kol oraya yetişmiyor");
                                   continue;
                               }
                               else{
                               for(int j=0; j < engelSayisi; j++){ // tüm engelleri kontrol et
/* engele denk geliyorsa*/     if((sonKonum[0]+1 == engelDizisi[j][0] && sonKonum[1] == engelDizisi[j][1])){
                                     //JOptionPane.showMessageDialog(null, "Engele takıldı");
//                                     System.out.println("Robot tipi: " + robotTipi);
                                       engelVarmi = true;
                                       
 /* engele denk gelmiyorsa*/       }
                               } // engel kontrolu bitisi
                               
                               if(engelVarmi){
                                   if(hareketliKisim.equals("tekerlekli")){
                                       zaman += TekerlekliRobot.getEngelGecmeSuresi();
                                       sonKonum[0]++;
                                   }
                                   
                                   if(hareketliKisim.equals("paletli")){
                                       zaman += PaletliRobot.getEngelGecmeSuresi();
                                       sonKonum[0]++;
                                   }
                                   
                                   if(hareketliKisim.equals("spider")){
                                       JOptionPane.showMessageDialog(null, "Sonuç:\nGeçen süre: " + zaman + " sn");
                                   }
                                   
                               }
                               
                               else if (!engelVarmi){
                                   if(hareketliKisim.equals("tekerlekli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]++;
                                   }
                                   
                                   if(hareketliKisim.equals("paletli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]++;
                                   }
                                   
                                   if(hareketliKisim.equals("spider")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]++;
                                   }
                               }
                               
                               engelVarmi = false;
                            } // else sonu
                               
                          }           
                       }
                       break;
                       
                   case "geri":
                       if((sonKonum[0] - yonParca1) < 0){
                           JOptionPane.showMessageDialog(null,"Robot haritadan taştı!");
                           //sonKonum[0] = 0;
                           continue;
                       }
                       else{
                           
                           for(int i=0; i < yonParca1; i++){ // ilerleme sayısı kadar
                               
                               if(oturduMu){
                                   uzaklik = Math.sqrt(Math.pow(sabitKonum[0]-(sonKonum[0]-1), 2)+
                                       Math.pow(sabitKonum[1]-sonKonum[1], 2))*10;
                               System.out.println("Uzaklık: " + uzaklik);
                               }
                               else{
                                   uzaklik = 0;
                               }
                               
                               if(uzaklik>kolUzunlugu){
                                   JOptionPane.showMessageDialog(null, "Kol oraya yetişmiyor");
                                   continue;
                               }
                               
                               for(int j=0; j < engelSayisi; j++){ // tüm engelleri kontrol et
/* engele denk geliyorsa*/     if((sonKonum[0]-1 == engelDizisi[j][0] && sonKonum[1] == engelDizisi[j][1])){
                                     //JOptionPane.showMessageDialog(null, "Engele takıldı");
//                                     System.out.println("Robot tipi: " + robotTipi);
                                       engelVarmi = true;
                                       
 /* engele denk gelmiyorsa*/       }
                               } // engel kontrolu bitisi
                               
                               if(engelVarmi){
                                   if(hareketliKisim.equals("tekerlekli")){
                                       zaman += TekerlekliRobot.getEngelGecmeSuresi();
                                       sonKonum[0]--;
                                   }
                                   
                                   if(hareketliKisim.equals("paletli")){
                                       zaman += PaletliRobot.getEngelGecmeSuresi();
                                       sonKonum[0]--;
                                   }
                                   
                                   if(hareketliKisim.equals("spider")){
                                       JOptionPane.showMessageDialog(null, "Sonuç:\nGeçen süre: " + zaman + " sn");
                                   }
                               }
                               
                               else if (!engelVarmi){
                                   if(hareketliKisim.equals("tekerlekli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]--;
                                   }
                                   
                                   if(hareketliKisim.equals("paletli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]--;
                                   }
                                   
                                   if(hareketliKisim.equals("spider")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]--;
                                   }
                               }
                               
                               engelVarmi = false;
                            }

                       }
                       break;
                       
                   case "sol":
                       if((sonKonum[1] - yonParca1) < 0){
                           JOptionPane.showMessageDialog(null,"Robot haritadan taştı!");
                           //sonKonum[1] = 0;
                           continue;
                       }
                       else{
                           
                           for(int i=0; i < yonParca1; i++){ // ilerleme sayısı kadar
                               
                               if(oturduMu){
                                   uzaklik = Math.sqrt(Math.pow(sabitKonum[1]-(sonKonum[1]-1), 2)+
                                       Math.pow(sabitKonum[0]-sonKonum[0], 2))*10;
                               System.out.println("Uzaklık: " + uzaklik);
                               }
                               else{
                                   uzaklik = 0;
                               }
                               
                               if(uzaklik>kolUzunlugu){
                                   JOptionPane.showMessageDialog(null, "Kol oraya yetişmiyor");
                                   continue;
                               }
                               
                               for(int j=0; j < engelSayisi; j++){ // tüm engelleri kontrol et
/* engele denk geliyorsa*/     if((sonKonum[1]-1 == engelDizisi[j][1] && sonKonum[0] == engelDizisi[j][0])){
                                     //JOptionPane.showMessageDialog(null, "Engele takıldı");
//                                     System.out.println("Robot tipi: " + robotTipi);
                                       engelVarmi = true;
                                       
 /* engele denk gelmiyorsa*/       }
                               } // engel kontrolu bitisi
                               
                               if(engelVarmi){
                                   if(hareketliKisim.equals("tekerlekli")){
                                       zaman += TekerlekliRobot.getEngelGecmeSuresi();
                                       sonKonum[1]--;
                                   }
                                   
                                   if(hareketliKisim.equals("paletli")){
                                       zaman += PaletliRobot.getEngelGecmeSuresi();
                                       sonKonum[1]--;
                                   }
                                   
                                   if(robotTipi.equals("spider")){
                                       JOptionPane.showMessageDialog(null, "Sonuç:\nGeçen süre: " + zaman + " sn");
                                   }
                               }
                               
                               else if (!engelVarmi){
                                   if(hareketliKisim.equals("tekerlekli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]--;
                                   }
                                   
                                   if(hareketliKisim.equals("paletli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]--;
                                   }
                                   
                                   if(hareketliKisim.equals("spider")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]--;
                                   }
                               }
                               
                               engelVarmi = false;
                            }

                       }
                       break;
                       
                   case "sağ":
                       if((sonKonum[1] + yonParca1) > 19){
                           JOptionPane.showMessageDialog(null,"Robot haritadan taştı!");
                           //sonKonum[1] = 19;
                           
                           continue;
                       }
                       else{
                           
                           for(int i=0; i < yonParca1; i++){ // ilerleme sayısı kadar
                               
                               if(oturduMu){
                                   uzaklik = Math.sqrt(Math.pow(sabitKonum[1]-(sonKonum[1]+1), 2)+
                                       Math.pow(sabitKonum[0]-sonKonum[0], 2))*10;
                               System.out.println("Uzaklık: " + uzaklik);
                               }
                               else{
                                   uzaklik = 0;
                               }
                               
                               if(uzaklik>kolUzunlugu){
                                   JOptionPane.showMessageDialog(null, "Kol oraya yetişmiyor");
                                   continue;
                               }
                               
                               for(int j=0; j < engelSayisi; j++){ // tüm engelleri kontrol et
/* engele denk geliyorsa*/     if((sonKonum[1]+1 == engelDizisi[j][1] && sonKonum[0] == engelDizisi[j][0])){
                                     //JOptionPane.showMessageDialog(null, "Engele takıldı");
//                                     System.out.println("Robot tipi: " + robotTipi);
                                       engelVarmi = true;
                                       
 /* engele denk gelmiyorsa*/       }
                               } // engel kontrolu bitisi
                               
                               if(engelVarmi){
                                   if(hareketliKisim.equals("tekerlekli")){
                                       zaman += TekerlekliRobot.getEngelGecmeSuresi();
                                       sonKonum[1]++;
                                   }
                                   
                                   if(hareketliKisim.equals("paletli")){
                                       zaman += PaletliRobot.getEngelGecmeSuresi();
                                       sonKonum[1]++;
                                   }
                                   
                                   if(hareketliKisim.equals("spider")){
                                       JOptionPane.showMessageDialog(null, "Sonuç:\nGeçen süre: " + zaman + " sn");
                                   }
                               }
                               
                               else if (!engelVarmi){
                                   if(hareketliKisim.equals("tekerlekli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]++;
                                   }
                                   
                                   if(hareketliKisim.equals("paletli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]++;
                                   }
                                   
                                   if(hareketliKisim.equals("spider")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]++;
                                   }
                               }
                               
                               engelVarmi = false;
                            }

                       }
                       break;
                       
                   default:System.out.println("Yanlış yön girdiniz");
               }
               
           }
           System.out.println("Zaman: " + zaman);
           Pencere.ekraniGosterme();
           Pencere.ekraniGoster();
           
       } // while sonu
        
    } // işlemler sonu
    
    public int[][] getEngeller(){
        return engeller;
    }
    
    public int[][] getEngelDizisi() {
        return engelDizisi;
    }
 
}
