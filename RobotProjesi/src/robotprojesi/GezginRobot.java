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
public abstract class GezginRobot extends Robot{
    static float gezinmeHizi;
    boolean engelDurumu;
    static float hizKontrol;
    
    
    
    
    
    public float getGezinmeHizi(){
        return gezinmeHizi;
    }
    
    public void setGezinmeHizi(int gezinmeHizi){
        this.gezinmeHizi = gezinmeHizi;
    }
   
    
}
