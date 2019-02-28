/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotprojesi;

/**
 *
 * @author Engin Uysal
 */
public abstract class ManipulatorRobot extends Robot{
    static float tasimaHizi;
    static float kolUzunlugu;
    
    static float azamiYukKapasitesi;
    static float yukMiktari;
    
    ManipulatorRobot(){
        super();     
    }

    public float getYukMiktari() {
        return yukMiktari;
    }

    public void setYukMiktari(float yukMiktari) {
        this.yukMiktari = yukMiktari;
    }

    public float getAzamiYukKapasitesi() {
        return azamiYukKapasitesi;
    }

    public void setAzamiYukKapasitesi(float azamiYukKapasitesi) {
        this.azamiYukKapasitesi = azamiYukKapasitesi;
    }
     
    public float getTasimaHizi(){
        return tasimaHizi;
    }
    
    public void setTasimaHizi(float tasimaHizi){
        this.tasimaHizi = tasimaHizi;
    }

    public float getKolUzunlugu() {
        return this.kolUzunlugu;
    }

    public void setKolUzunlugu(float kolUzunlugu) {
        this.kolUzunlugu = kolUzunlugu;
    }
    
    
    
}
