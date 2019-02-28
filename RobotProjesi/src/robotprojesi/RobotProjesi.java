
package robotprojesi;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RobotProjesi {

    
    public static void main(String[] args) {
//        Object[] options = {"Yes, please", "No way"};
        float azamiYukKapasitesi;
        float yukMiktari;
        int motorSayisi;
        
        String robotTipi;
        int robotSayisi;
        int robotSirasi;
        String[] robotAdlari ={"tekerlekli", "paletli", "spider", "seri", "paralel", "hibrit"}; 
        
        // robot sayısını al
        robotSayisi = Integer.parseInt(JOptionPane.showInputDialog("Kaç tane robot gireceksiniz").trim());
        
        String[] robotTipleri = new String[robotSayisi];
        // robot tiplerini diziye at
        for(int i = 0; i < robotSayisi; i++){
            robotTipleri[i] = JOptionPane.showInputDialog((i+1) + ". robotun tipini giriniz").trim();
            while(!dogrula(robotTipleri[i], robotAdlari)){
                JOptionPane.showMessageDialog(null, "Yanlış robot adı girdiniz");
                robotTipleri[i] = JOptionPane.showInputDialog((i+1) + ". robotun tipini giriniz").trim();
            }
            System.out.println("Robot " + (i+1) + ": " + robotTipleri[i]);
                 
        }
        
        while(true){
        // kaçıncı robotun hareket edeceğini kullanıcıya sor
        if(robotSayisi > 1)
        robotSirasi = Integer.parseInt(JOptionPane.showInputDialog("Hangi sıradaki robot hareket ettirilecek").trim());
        else
        robotSirasi = 1;
        
        
        
        System.out.println(robotTipleri[robotSirasi-1]);
        
        
        switch(robotTipleri[robotSirasi-1].toLowerCase()){ // iki tane aynı robot oluşturursa ne olacak ?
            
            case "tekerlekli":
                Problem1 p1 = new Problem1("tekerlekli");
                break;
                
            case "paletli":                
                Problem1 p1_ = new Problem1("paletli");
                break;
            
            case "spider":
                Problem1 p1__ = new Problem1("spider");
                break; 
            
            
            case "seri":
                Problem2 p2 = new Problem2("seri");
                break;  
                
            case "paralel":
                Problem2 p2_ = new Problem2("paralel");
                break; 
                
            case "hibrit":
                Problem3 p3 = new Problem3("hibrit");
                break;
        
            default:
                JOptionPane.showMessageDialog(null, "Yanlış isim girdiniz");
         
        }
      }
        
        
    } // main sonu
    
    public static String ilk(String metin){
        String ilkKelime = "";
        if(metin.isEmpty())
            return "";
        if(metin.contains(" ")) {
        int boslukIndex = metin.indexOf(' ');
        ilkKelime = metin.substring(0, boslukIndex);
        } else {
            ilkKelime = metin;
        }
        
        return ilkKelime;
    }
    
    public static String son(String metin){
        String sonKelime = "";
        if(metin.isEmpty())
            return "";
        if(metin.contains(" ")) {
        int boslukIndex = metin.indexOf(' ');
        sonKelime = metin.substring(boslukIndex+1);
        } else {
            sonKelime = metin;
        }
        return sonKelime;
    }
    
    public static boolean dogrula(String isim, String[] dizi){
        for(String eleman : dizi){
            if(isim.equalsIgnoreCase(eleman))
                return true;
        }
        return false;
    }
    
    public static boolean hizKontroluGezgin(){
        if(!(PaletliRobot.getMax() < TekerlekliRobot.getMin() 
                && SpiderRobot.getMax() < TekerlekliRobot.getMin())){
            return false;
        }
        
        if(!(SpiderRobot.getMax() < PaletliRobot.getMin())){
            return false;
        }
        
        if(!(TekerlekliRobot.getMin() > SpiderRobot.getMax()
                && PaletliRobot.getMin() > SpiderRobot.getMax())){
            return false;
        }
        
        if((TekerlekliRobot.getMax() > PaletliRobot.getMax()
                && SpiderRobot.getMax() < PaletliRobot.getMin())){
            return false;
        }
        return true;
    }

    
    
}
