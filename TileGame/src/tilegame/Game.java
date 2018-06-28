
package tilegame;

import State.GameState;
import State.MenuState;
import State.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import tilegame.gfx.Assets;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;
import tilegame.input.KeyManager;
//TODO: what is implements?
public class Game implements Runnable{
    private Display display;
    public int width, height;
    public String title;
    private boolean running = false; //run game loop while true
    //creating a thread - is like a miniprogram (this helps with efficiency
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    //State
    private State gameState;
    private State menuState;
   //Input
    private KeyManager keyManager;
    
    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();

    }  
    private void init(){ //initializes the graphics
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();
        gameState = new GameState(this);
        menuState = new MenuState(this);
        
        State.setState(gameState);
    }
    

    private void tick(){ //update variables
        keyManager.tick();
       if(State.getState() != null)
           State.getState().tick();
    }
    
    private void render (){ //update render - everything to the screen
       //render == draw to the screen
       //in order to draw stuff into the screen we need the canvas
       //TODO: what's a buffer strategy? a way for a computer to draw things to 
       //the screeam. how it should draw things to the screen
       //Buffer == is like a 'hidden' computer screen within your computer
       //this is use to smooth the transaction between frames
       bs = display.getCanvas().getBufferStrategy();
       if(bs == null){
           display.getCanvas().createBufferStrategy(3);//we are going to use 3 buffers
           return;
       }
       g = bs.getDrawGraphics(); //is like a paint brush
       //clear the screen
       g.clearRect(0, 0, width, height);
       //Draw here!
       if(State.getState() != null)
           State.getState().render(g);       
       //end drawing!
       //load images

       bs.show();
       g.dispose();
    }
    
    public void run(){
        init();
        int fps = 60; ;//frames per seconds - 10 Game loop timer
        //time in nano seconds, 1 billion nano seconds  == 1 second
        double timePerTick = 1000000000 / fps; 
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        //the Game Loop
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if(delta >= 1) {
                tick(); //update variables
                render(); //draw on screen
                delta--;
            }
        }
        stop();
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    }
    
    //creating a thread
    public synchronized void start(){
        if(running == true)
            return;
        running = true;
        thread = new Thread(this);
        thread.start(); //this calles the run() method
    }
    public synchronized void stop(){ //close the thread
        if(running == false)
            return;
        running = false;
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
}
