package State;

import Entities.Creatures.Player;
import java.awt.Graphics;
import tilegame.Game;
import tilegame.gfx.Assets;

public class GameState extends State {
    private Player player;
    public GameState(Game game){
        super(game);
        player = new Player(game, 100, 100);
    }
    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
       player.render(g);
    }
    
    
}
