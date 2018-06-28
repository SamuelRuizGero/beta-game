/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilegame.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author zamy0
 */
public class SpriteSheet {
    private BufferedImage sheet; //load sprite image
    public SpriteSheet(BufferedImage sheet){ //constructor
        this.sheet = sheet; 
    }
    
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
