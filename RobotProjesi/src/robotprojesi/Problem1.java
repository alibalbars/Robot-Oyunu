/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotprojesi;

import javax.swing.JOptionPane;

/**
 *
 * @author Engin Uysal
 */
public class Problem1 { // gezinme hizi degiskenini almak için
    
    float zaman = 0;
    int motorSayisi;
    Object[] options = {"Evet", "Hayır"};
    static int[][] engeller = new int[20][20];
    static int engelSayisi;
    static int[][] engelDizisi;
    String koordinat;
    int x, y;
    int n;
    String robotTipi;
    static int[] baslangic = new int[2];
    static int[] sonKonum = baslangic;
    String yon;
    int yonParca1;
    String yonParca2;
    boolean gecisIzni = true;
    boolean engelVarmi = false;
    float gezinmeHizi = 0;
    
    TekerlekliRobot tekerlekliRobot;
    PaletliRobot paletliRobot;
    SpiderRobot spiderRobot;
    
    //boş costructor
    Problem1(){
        
    }
    
    Problem1(String robotTipi){
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){
                engeller[i][j] = 0;
            }
        }
        
        this.robotTipi = robotTipi;
        
        
        switch(robotTipi){
            
            case "tekerlekli":
                motorSayisi = Integer.parseInt(JOptionPane.showInputDialog("Motor sayısını giriniz").trim());
                tekerlekliRobot = new TekerlekliRobot(motorSayisi);
                tekerlekliRobot.setGezinmeHizi(Integer.parseInt(JOptionPane.showInputDialog("Gezinme Hızını giriniz").trim()));
                gezinmeHizi = tekerlekliRobot.getGezinmeHizi();
//                TekerlekliRobot.setMax(tekerlekliRobot.getGezinmeHizi());
//                TekerlekliRobot.setMin(tekerlekliRobot.getGezinmeHizi());
//                
//                while(!RobotProjesi.hizKontroluGezgin()){
//                    JOptionPane.showMessageDialog(null, "Hız kontrol uyumsuzluğu");
//                    tekerlekliRobot.setGezinmeHizi(Integer.parseInt(JOptionPane.showInputDialog("Gezinme Hızını giriniz").trim()));
//                    TekerlekliRobot.setMax(tekerlekliRobot.getGezinmeHizi());
//                    TekerlekliRobot.setMin(tekerlekliRobot.getGezinmeHizi());
//                }
                
//                gezinmeHizi = Integer.parseInt(JOptionPane.showInputDialog("Gezinme Hızını giriniz").trim());
                
                break;
                
            case "paletli":
                motorSayisi = Integer.parseInt(JOptionPane.showInputDialog("Motor sayısını giriniz").trim());
                paletliRobot = new PaletliRobot(motorSayisi);
                paletliRobot.setGezinmeHizi(Integer.parseInt(JOptionPane.showInputDialog("Gezinme Hızını giriniz").trim()));
                gezinmeHizi = paletliRobot.getGezinmeHizi();
//                PaletliRobot.setMax(paletliRobot.getGezinmeHizi());
//                PaletliRobot.setMin(paletliRobot.getGezinmeHizi());
//                while(!RobotProjesi.hizKontroluGezgin()){
//                    JOptionPane.showMessageDialog(null, "Hız kontrol uyumsuzluğu");
//                    paletliRobot.setGezinmeHizi(Integer.parseInt(JOptionPane.showInputDialog("Gezinme Hızını giriniz").trim()));
//                    PaletliRobot.setMax(tekerlekliRobot.getGezinmeHizi());
//                    PaletliRobot.setMin(tekerlekliRobot.getGezinmeHizi());
//                }
//                gezinmeHizi = Integer.parseInt(JOptionPane.showInputDialog("Gezinme Hızını giriniz").trim());
                break;
            
            case "spider":
                spiderRobot = new SpiderRobot();
                spiderRobot.setGezinmeHizi(Integer.parseInt(JOptionPane.showInputDialog("Gezinme Hızını giriniz").trim()));
                gezinmeHizi = spiderRobot.getGezinmeHizi();
//                SpiderRobot.setMax(spiderRobot.getGezinmeHizi());
//                SpiderRobot.setMin(spiderRobot.getGezinmeHizi());
//                while(!RobotProjesi.hizKontroluGezgin()){
//                    JOptionPane.showMessageDialog(null, "Hız kontrol uyumsuzluğu");
//                    spiderRobot.setGezinmeHizi(Integer.parseInt(JOptionPane.showInputDialog("Gezinme Hızını giriniz").trim()));
//                    SpiderRobot.setMax(tekerlekliRobot.getGezinmeHizi());
//                    SpiderRobot.setMin(tekerlekliRobot.getGezinmeHizi());
//                }
//                gezinmeHizi = Integer.parseInt(JOptionPane.showInputDialog("Gezinme Hızını giriniz").trim());
                break;
        }
 
        System.out.println("Gezinme hızı: " + gezinmeHizi); // kontrol amaçlı
        //System.out.println("Engel geçme süresi: " + tekerlekliRobot.getEngelGecmeSuresi());
        
        
        
        
        
        n = JOptionPane.showOptionDialog(null, "Engel girecek misiniz?", "Giriş", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        //Hayır
        if(n == 1){
            engelSayisi = 1; // bunu yazmassak engel girilmeyen durumda zamanı hesaplamıyor
            engelDizisi = new int[engelSayisi][2];
            
            
          //Evet
        } else if(n == 0){
            
            engelSayisi = Integer.parseInt(JOptionPane.showInputDialog("Kaç engel gireceksiniz").trim());
            engelDizisi = new int[engelSayisi][2];
             // engeller dizisi doldur
            for(int i = 0; i < engelSayisi; i++){
                koordinat = JOptionPane.showInputDialog("Engel konumu giriniz [satır-sütun]").trim();
                x = Integer.parseInt(RobotProjesi.ilk(koordinat));
                y = Integer.parseInt(RobotProjesi.son(koordinat));
                System.out.println("x: " + x + " " + "y: " + y);
                engeller[x-1][y-1] = -1;
            
            }
            
            //matrisi çiz
            for(int i = 0; i<20; i++){
                for(int j = 0; j<20; j++){
                    System.out.print(engeller[i][j]);
                }
                System.out.println();
            }
           
        } // engel alımı bitişi
        
        Islemler();
//        Pencere pencere = new Pencere(1);
        
        
    } // contructor sonu
    
    public void Islemler(){
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
        
        // engel dizisini yazdır
        System.out.println("Engel dizisi: ");
        for(int i = 0; i<k; i++){
                for(int j = 0; j<2; j++){
                    System.out.print(engelDizisi[i][j] + " ");
                }
                System.out.println();
            }
        
        // engel sayısını yazdır
        System.out.println("Engel sayısı: " + engelSayisi);

       // başlangıç konumunu al diziye at
       koordinat = JOptionPane.showInputDialog("Başlangıç konumunu giriniz [satır-sütun]");
       baslangic[0] = Integer.parseInt(RobotProjesi.ilk(koordinat))-1;
       baslangic[1] = Integer.parseInt(RobotProjesi.son(koordinat))-1;
       System.out.println("basSatir: " + (baslangic[0]+1) + " basSütun: " +  (baslangic[1]+1));
       
        //engel dizisini yazdır
        System.out.println("Engel dizisi:");
       for(int i=0; i<engelDizisi.length; i++){
           for(int j=0; j<2; j++){
               System.out.print(engelDizisi[i][j] + " ");
           }
           System.out.println();
       }
        System.out.println();
        
       // şu halde ekranı göster
       Pencere pencere = new Pencere(1);
        
        // hareket ettirme
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
                       if((sonKonum[0] + yonParca1) > 19){
                           JOptionPane.showMessageDialog(null,"Robot haritadan taştı!");
                           //sonKonum[0] = 19;
                           
                           //en son 90 ileri gidince son konumu sınıra dayamayı yapamadım.

                           continue;
                       }
                       else{
                           
                           for(int i=0; i < yonParca1; i++){ // ilerleme sayısı kadar
                               for(int j=0; j < engelSayisi; j++){ // tüm engelleri kontrol et
/* engele denk geliyorsa*/     if((sonKonum[0]+1 == engelDizisi[j][0] && sonKonum[1] == engelDizisi[j][1])){
                                     //JOptionPane.showMessageDialog(null, "Engele takıldı");
//                                     System.out.println("Robot tipi: " + robotTipi);
                                       engelVarmi = true;
                                       
 /* engele denk gelmiyorsa*/       }
                               } // engel kontrolu bitisi
                               
                               if(engelVarmi){
                                   if(robotTipi.equals("tekerlekli")){
                                       zaman += tekerlekliRobot.getEngelGecmeSuresi();
                                       sonKonum[0]++;
                                   }
                                   
                                   if(robotTipi.equals("paletli")){
                                       zaman += paletliRobot.getEngelGecmeSuresi();
                                       sonKonum[0]++;
                                   }
                                   
                                   if(robotTipi.equals("spider")){
                                       
                                   }
                               }
                               
                               else if (!engelVarmi){
                                   if(robotTipi.equals("tekerlekli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]++;
                                   }
                                   
                                   if(robotTipi.equals("paletli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]++;
                                   }
                                   
                                   if(robotTipi.equals("spider")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]++;
                                   }
                               }
                               
                               engelVarmi = false;
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
                               for(int j=0; j < engelSayisi; j++){ // tüm engelleri kontrol et
/* engele denk geliyorsa*/     if((sonKonum[0]-1 == engelDizisi[j][0] && sonKonum[1] == engelDizisi[j][1])){
                                     //JOptionPane.showMessageDialog(null, "Engele takıldı");
//                                     System.out.println("Robot tipi: " + robotTipi);
                                       engelVarmi = true;
                                       
 /* engele denk gelmiyorsa*/       }
                               } // engel kontrolu bitisi
                               
                               if(engelVarmi){
                                   if(robotTipi.equals("tekerlekli")){
                                       zaman += tekerlekliRobot.getEngelGecmeSuresi();
                                       sonKonum[0]--;
                                   }
                                   
                                   if(robotTipi.equals("paletli")){
                                       zaman += paletliRobot.getEngelGecmeSuresi();
                                       sonKonum[0]--;
                                   }
                                   
                                   if(robotTipi.equals("spider")){
                                       
                                   }
                               }
                               
                               else if (!engelVarmi){
                                   if(robotTipi.equals("tekerlekli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]--;
                                   }
                                   
                                   if(robotTipi.equals("paletli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[0]--;
                                   }
                                   
                                   if(robotTipi.equals("spider")){
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
                               for(int j=0; j < engelSayisi; j++){ // tüm engelleri kontrol et
/* engele denk geliyorsa*/     if((sonKonum[1]-1 == engelDizisi[j][1] && sonKonum[0] == engelDizisi[j][0])){
                                     //JOptionPane.showMessageDialog(null, "Engele takıldı");
//                                     System.out.println("Robot tipi: " + robotTipi);
                                       engelVarmi = true;
                                       
 /* engele denk gelmiyorsa*/       }
                               } // engel kontrolu bitisi
                               
                               if(engelVarmi){
                                   if(robotTipi.equals("tekerlekli")){
                                       zaman += tekerlekliRobot.getEngelGecmeSuresi();
                                       sonKonum[1]--;
                                   }
                                   
                                   if(robotTipi.equals("paletli")){
                                       zaman += paletliRobot.getEngelGecmeSuresi();
                                       sonKonum[1]--;
                                   }
                                   
                                   if(robotTipi.equals("spider")){
                                       
                                   }
                               }
                               
                               else if (!engelVarmi){
                                   if(robotTipi.equals("tekerlekli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]--;
                                   }
                                   
                                   if(robotTipi.equals("paletli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]--;
                                   }
                                   
                                   if(robotTipi.equals("spider")){
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
                               for(int j=0; j < engelSayisi; j++){ // tüm engelleri kontrol et
/* engele denk geliyorsa*/     if((sonKonum[1]+1 == engelDizisi[j][1] && sonKonum[0] == engelDizisi[j][0])){
                                     //JOptionPane.showMessageDialog(null, "Engele takıldı");
//                                     System.out.println("Robot tipi: " + robotTipi);
                                       engelVarmi = true;
                                       
 /* engele denk gelmiyorsa*/       }
                               } // engel kontrolu bitisi
                               
                               if(engelVarmi){
                                   if(robotTipi.equals("tekerlekli")){
                                       zaman += tekerlekliRobot.getEngelGecmeSuresi();
                                       sonKonum[1]++;
                                   }
                                   
                                   if(robotTipi.equals("paletli")){
                                       zaman += paletliRobot.getEngelGecmeSuresi();
                                       sonKonum[1]++;
                                   }
                                   
                                   if(robotTipi.equals("spider")){
                                       
                                   }
                               }
                               
                               else if (!engelVarmi){
                                   if(robotTipi.equals("tekerlekli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]++;
                                   }
                                   
                                   if(robotTipi.equals("paletli")){
                                       zaman += 10/gezinmeHizi;
                                       sonKonum[1]++;
                                   }
                                   
                                   if(robotTipi.equals("spider")){
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
    
    public int[] getBaslangic() {
        return baslangic;
    }
    
}
