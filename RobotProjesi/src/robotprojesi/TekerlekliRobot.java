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
public class TekerlekliRobot extends GezginRobot{
    private int tekerlekSayisi;
    static float max;
    static float min;

    public static float getMax() {
        return max;
    }

    public static void setMax(float max) {
        if(max > TekerlekliRobot.max)
        TekerlekliRobot.max = max;
    }

    public static float getMin() {
        return min;
    }

    public static void setMin(float min) {
        if(min < TekerlekliRobot.min)
        TekerlekliRobot.min = min;
    }
    
    TekerlekliRobot(int motorSayisi){
        engelDurumu = true;
        this.motorSayisi = motorSayisi;
        
    }

    
    public static float getEngelGecmeSuresi() {
        //return super.engelGecmeSuresiBul(); //To change body of generated methods, choose Tools | Templates.
        return motorSayisi*0.5f;
    }

    

    public int getTekerlekSayisi() {
        return tekerlekSayisi;
    }

    public void setTekerlekSayisi(int tekerlekSayisi) {
        this.tekerlekSayisi = tekerlekSayisi;
    }
    
 
}
