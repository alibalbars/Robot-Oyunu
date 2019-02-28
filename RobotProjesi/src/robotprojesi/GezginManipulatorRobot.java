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
public class GezginManipulatorRobot extends Robot{
    float gezinmeHizi;
    float yukKapasitesi;
    float azamiYukKapasitesi;
    float kolUzunlugu;
    float tasimaHizi;
    boolean engelDurumu;
    
    
    GezginManipulatorRobot(){
        super();  
    }

    public float getGezinmeHizi() {
        return gezinmeHizi;
    }

    public void setGezinmeHizi(float gezinmeHizi) {
        this.gezinmeHizi = gezinmeHizi;
    }

    public float getYukKapasitesi() {
        return yukKapasitesi;
    }

    public void setYukKapasitesi(float yukKapasitesi) {
        this.yukKapasitesi = yukKapasitesi;
    }

    public float getKolUzunlugu() {
        return kolUzunlugu;
    }

    public void setKolUzunlugu(float kolUzunlugu) {
        this.kolUzunlugu = kolUzunlugu;
    }

    public float getTasimaHizi() {
        return tasimaHizi;
    }

    public void setTasimaHizi(float tasimaHizi) {
        this.tasimaHizi = tasimaHizi;
    }
    
    
    
}
