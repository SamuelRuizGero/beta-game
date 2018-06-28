/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilegame;

/**
 *
 * @author zamy0
 */
public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("The super mega game", 640, 360);
        game.start();
    }
}
