package PaooGame;

import PaooGame.Input.KeyManager;
import PaooGame.Items.Enemy;
import PaooGame.Maps.Map;
import PaooGame.Items.Hero;
import PaooGame.State.*;

import java.util.ArrayList;

public class RefLinks {
    private Game game;
    private Hero hero;
    private Map map;
    private PlayState playState;
    public ArrayList<Enemy> enemies;

    public RefLinks(Game game){this.game=game;}
    public KeyManager GetKeyManager(){return game.GetKeyManager();}
    public int GetWidth(){return game.GetWidth();}
    public int GetHeight(){return game.GetHeight();}
    public Game GetGame(){return game;}
    public void SetGame(Game game){this.game=game;}
    public Map GetMap(){return map;}
    public Hero GetHero(){return hero;}
    public void SetEnemy(ArrayList<Enemy> enemies){this.enemies =enemies;}
    public void SetHero(Hero hero){this.hero = hero;}
    public void SetMap(Map map){this.map = map;}
}
