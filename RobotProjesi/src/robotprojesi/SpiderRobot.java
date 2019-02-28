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
public class SpiderRobot extends GezginRobot{
    private int bacakSayisi;
    static float max;
    static float min;

    public static float getMax() {
        return max;
    }

    public static void setMax(float max) {
        if(max > SpiderRobot.max)
        SpiderRobot.max = max;
    }

    public static float getMin() {
        return min;
    }

    public static void setMin(float min) {
        if(min < SpiderRobot.min)
        SpiderRobot.min = min;
    }
    
    SpiderRobot(){ // engellerden gecemedigi için motor sayısını almadık
        engelDurumu = false;
    }
    
    
    
}
