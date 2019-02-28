/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotprojesi;

import javax.swing.JOptionPane;
import static robotprojesi.Problem1.baslangic;
import static robotprojesi.Problem1.sonKonum;

/**
 *
 * @author Engin Uysal
 */
public class Problem2 extends ManipulatorRobot{
    
    
    static float zaman = 0;
    Object[] options = {"Evet", "Hayır"};
    float azamiYukKapasitesi;
    float yukMiktari;
    String koordinat;
    static final int[] baslangic = new int[2];
    static int[] kol;
    String yon;
    int yonParca1;
    String yonParca2;
    float tasimaHizi;
    double uzaklik;
    boolean tastiMi;
    int birKere = 0;
    //Pencere pencere = new Pencere();
    
    String robotTipi;
    SeriRobot seriRobot;
    ParalelRobot paralelRobot;
    
    
    
    //boş costructor
    Problem2(){
       
    }
    
    Problem2(String robotTipi){
        this.robotTipi = robotTipi;
        
        
        switch(robotTipi){
            
            case "seri":
                seriRobot = new SeriRobot();
//                seriRobot.setTasimaHizi(Float.parseFloat(JOptionPane.showInputDialog("Taşıma hızını giriniz").trim()));
                tasimaHizi = Float.parseFloat(JOptionPane.showInputDialog("Taşıma hızını giriniz").trim());
                seriRobot.setAzamiYukKapasitesi(Float.parseFloat(JOptionPane.showInputDialog("Azami yük kapasitesini giriniz").trim()));
                seriRobot.setYukMiktari(Float.parseFloat(JOptionPane.showInputDialog("Yük miktarını giriniz").trim()));
                
                while(seriRobot.getYukMiktari()> seriRobot.getAzamiYukKapasitesi()){
                    JOptionPane.showMessageDialog(null, "Yük miktarı, azami yük kapasitesini geçemez!");
                    seriRobot.setYukMiktari(Float.parseFloat(JOptionPane.showInputDialog("Yük miktarını giriniz").trim()));
                }
                
                seriRobot.setKolUzunlugu(Float.parseFloat(JOptionPane.showInputDialog("Kol uzunluğunu giriniz").trim()));
                break;  
                
            case "paralel":
                paralelRobot = new ParalelRobot();
                
//                paralelRobot.setTasimaHizi(Float.parseFloat(JOptionPane.showInputDialog("Taşıma hızını giriniz")));
                tasimaHizi = Float.parseFloat(JOptionPane.showInputDialog("Taşıma hızını giriniz").trim());
                paralelRobot.setAzamiYukKapasitesi(Float.parseFloat(JOptionPane.showInputDialog("Azami yük kapasitesini giriniz")));
                paralelRobot.setYukMiktari(Float.parseFloat(JOptionPane.showInputDialog("Yük miktarını giriniz")));
                
                while(paralelRobot.getYukMiktari()> paralelRobot.getAzamiYukKapasitesi()){
                    JOptionPane.showMessageDialog(null, "Yük miktarı, azami yük kapasitesini geçemez!");
                    paralelRobot.setYukMiktari(Float.parseFloat(JOptionPane.showInputDialog("Yük miktarını giriniz")));
                }
                paralelRobot.setKolUzunlugu(Float.parseFloat(JOptionPane.showInputDialog("Kol uzunluğunu giriniz")));
                
                break; 
        }
            Islemler();
            
        } // constructor sonu
        
        
        
        
        
    public void Islemler(){
       // başlangıç dizisini dolsur
       koordinat = JOptionPane.showInputDialog("Başlangıç konumunu giriniz [satır-sütun]");
       baslangic[0] = Integer.parseInt(RobotProjesi.ilk(koordinat))-1;
       baslangic[1] = Integer.parseInt(RobotProjesi.son(koordinat))-1;
       System.out.println("basSatir: " + (baslangic[0]+1) + " basSütun: " +  (baslangic[1]+1));
       
       kol = baslangic.clone();
       Pencere pencere = new Pencere(2);
       
       // kolu hareket ettirme
       while(true){
           yon = JOptionPane.showInputDialog("Hareket yönü giriniz [Girilmeyecekse -1]");
           if(yon.equals("-1")){
               JOptionPane.showMessageDialog(null, "Sonuç:\nGeçen süre: " + zaman + " sn");
               Pencere.ekraniGosterme();
               break;
           } else {
               yonParca1 = Integer.parseInt(RobotProjesi.ilk(yon));
               yonParca2 = RobotProjesi.son(yon);
               
               // son konumu yenile ve taşma kontrolü yap
               switch(yonParca2){
                   case "ileri":
                       if((kol[0] + yonParca1) > 19){ // buralara kol uzunluğu kontrolünü de ekliyecez          
                               JOptionPane.showMessageDialog(null,"Robot haritadan taştı!");                         
//                         continue;
                       }
                       else{
                           
                           for(int i=0; i < yonParca1; i++){ // ilerleme sayısı kadar
                               uzaklik = Math.sqrt(Math.pow(baslangic[0]-(kol[0]+1), 2)+
                                       Math.pow(baslangic[1]-kol[1], 2))*10;
                               if((uzaklik>seriRobot.kolUzunlugu && robotTipi.equals("seri")) ||
                                       uzaklik>paralelRobot.kolUzunlugu && robotTipi.equals("paralel")){
                                   JOptionPane.showMessageDialog(null, "Kol oraya yetişmiyor");
                               }
                               
                               else{
                                   zaman += 10/tasimaHizi;
//                                       System.out.println("Yön parça1: " + yonParca1);
                                       System.out.println("Taşıma hızı: " + tasimaHizi);
                                       System.out.println("Zamana eklenen: " + (10)/tasimaHizi);
                                       System.out.println("Toplam zaman: " + zaman);
//                                    System.out.println("Baslangic: " + baslangic[0] + " " + baslangic[1]);
//                                    System.out.println("Kol: " + kol[0] + " " + kol[1]);
                                      kol[0]++;
                                }
                            }
                        }
                            break;
                            
                   case "geri": 
                       
                       if((kol[0] - yonParca1) < 0){ // buralara kol uzunluğu kontrolünü de ekliyecez
                           JOptionPane.showMessageDialog(null,"Robot haritadan taştı!");
                           
                           continue;
                       }
                       else{
                           
                           for(int i=0; i < yonParca1; i++){ // ilerleme sayısı kadar
                               uzaklik = Math.sqrt(Math.pow(baslangic[0]-(kol[0]-1), 2)+
                                       Math.pow(baslangic[1]-kol[1], 2))*10;
                               if((uzaklik>seriRobot.kolUzunlugu && robotTipi.equals("seri")) ||
                                       uzaklik>paralelRobot.kolUzunlugu && robotTipi.equals("paralel")){
                                   JOptionPane.showMessageDialog(null, "Kol oraya yetişmiyor!");
                               }
                               
                               else{
                                   zaman += 10/tasimaHizi;
                                   System.out.println("Taşıma hızı: " + tasimaHizi);
                                   System.out.println("Zamana eklenen: " + (10)/tasimaHizi);
                                   System.out.println("Toplam zaman: " + zaman);
//                                   System.out.println("Baslangic: " + baslangic[0] + " " + baslangic[1]);
//                                   System.out.println("Kol: " + kol[0] + " " + kol[1]);
                                   kol[0]--;
                               }


                               }
                            }
                            
                       break;
                       
                   case "sol":
                       
                       if((kol[1] - yonParca1) < 0){ // buralara kol uzunluğu kontrolünü de ekliyecez
                           JOptionPane.showMessageDialog(null,"Robot haritadan taştı!");
                           
                           continue;
                       }
                       else{
                           
                           for(int i=0; i < yonParca1; i++){ // ilerleme sayısı kadar
                               uzaklik = Math.sqrt(Math.pow(baslangic[1]-(kol[1]-1), 2)+
                                       Math.pow(baslangic[0]-kol[0], 2))*10;
                               if((uzaklik>seriRobot.kolUzunlugu && robotTipi.equals("seri")) ||
                                       uzaklik>paralelRobot.kolUzunlugu && robotTipi.equals("paralel")){
                                   JOptionPane.showMessageDialog(null, "Kol oraya yetişmiyor!");
                               }
                               
                               else{
                                   zaman += 10/tasimaHizi;
                                   System.out.println("Taşıma hızı: " + tasimaHizi);
                                   System.out.println("Zamana eklenen: " + (10)/tasimaHizi);
                                   System.out.println("Toplam zaman: " + zaman);
//                                   System.out.println("Baslangic: " + baslangic[0] + " " + baslangic[1]);
//                                   System.out.println("Kol: " + kol[0] + " " + kol[1]);
                                   kol[1]--;
                               }


                               }
                            }
                       break;
                       
                   case "sağ":
                       
                       if((kol[1] + yonParca1) > 19){ // buralara kol uzunluğu kontrolünü de ekliyecez
                           JOptionPane.showMessageDialog(null,"Robot haritadan taştı!");
                           
                           continue;
                       }
                       else{
                           
                           for(int i=0; i < yonParca1; i++){ // ilerleme sayısı kadar
                               uzaklik = Math.sqrt(Math.pow(baslangic[1]-(kol[1]+1), 2)+
                                       Math.pow(baslangic[0]-kol[0], 2))*10;
                               if((uzaklik>seriRobot.kolUzunlugu && robotTipi.equals("seri")) ||
                                       uzaklik>paralelRobot.kolUzunlugu && robotTipi.equals("paralel")){
                                   JOptionPane.showMessageDialog(null, "Kol oraya yetişmiyor!");
                               }
                                                                                                               
                               else{
                                   zaman += 10/tasimaHizi;
                                   System.out.println("Taşıma hızı: " + tasimaHizi);
                                   System.out.println("Zamana eklenen: " + (10)/tasimaHizi);
                                   System.out.println("Toplam zaman: " + zaman);
//                                   System.out.println("Baslangic: " + baslangic[0] + " " + baslangic[1]);
//                                   System.out.println("Kol: " + kol[0] + " " + kol[1]);
                                   kol[1]++;
                               }


                               }
                            }
                       break;
                       
                        }
        
               } // switch sonu
               Pencere.ekraniGosterme();
               
               Pencere.ekraniGoster();
               
            } // while sonu
        }
    }
    
