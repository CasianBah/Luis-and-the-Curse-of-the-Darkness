package PaooGame.Items;

import PaooGame.RefLinks;

public class EnemyFactory{
    public static Enemy createEnemy(RefLinks refLink, int x, int y,int tier){
        return new Enemy(refLink, x,y,tier);
    }
}
