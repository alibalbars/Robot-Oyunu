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
public class PaletliRobot extends GezginRobot{
    private int paletSayisi;
    static float max;
    static float min;

    public static float getMax() {
        return max;
    }

    public static void setMax(float max) {
        if(max > PaletliRobot.max)
        PaletliRobot.max = max;
    }

    public static float getMin() {
        return min;
    }

    public static void setMin(float min) {
        if(min < PaletliRobot.min)
        PaletliRobot.min = min;
    }

    PaletliRobot(int motorSayisi){
        engelDurumu = true;
        this.motorSayisi = motorSayisi;
    }
    

    @Override
    public float getGezinmeHizi() {
        return super.getGezinmeHizi();
    }
    
    public static float getEngelGecmeSuresi() {
        
        return motorSayisi*3f;
    }
    
    
    
}
