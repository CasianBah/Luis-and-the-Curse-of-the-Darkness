package PaooGame.Items;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

public abstract class Character extends Item{
    public static final int DEFAULT_LIFE =10;
    public static final float DEFAULT_SPEED = 2.4f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    protected int life;

    protected float speed;
    protected float xMove;
    protected float yMove;

    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
        super(refLink,x,y,width,height);

        life=DEFAULT_LIFE;
        speed=DEFAULT_SPEED;
        xMove=0;
        yMove=0;

    }

    public void Move()
    {
        MoveX();
        MoveY();
    }
    public void CheckCollision() {
        int nextX, nextY;

        if (xMove != 0) {
            nextY = (int) (y + bounds.y) / Tile.TILE_HEIGHT;
            if (xMove > 0) {
                nextX = (int) (x + bounds.x + bounds.width + xMove) / Tile.TILE_HEIGHT;
                if (refLink.GetMap().GetTile(nextX, nextY).IsSolid())
                    xMove = 0;

            } else {
                nextX = (int) (x + bounds.x + xMove) / Tile.TILE_HEIGHT;
                if (refLink.GetMap().GetTile(nextX, nextY).IsSolid())
                    xMove = 0;
            }
        }

        if (yMove != 0) {
            nextX = (int) (x + bounds.x) / Tile.TILE_HEIGHT;
            if (yMove < 0) {
                nextY = (int) (y + bounds.y + yMove) / Tile.TILE_HEIGHT;
                if (refLink.GetMap().GetTile(nextX, nextY).IsSolid())
                    yMove = 0;
            } else {
                nextY = (int) (y + bounds.y + bounds.height + yMove) / Tile.TILE_HEIGHT;
                if (refLink.GetMap().GetTile(nextX, nextY).IsSolid())
                    yMove = 0;
            }
        }
    }
    public void MoveX(){x+=xMove;}
    public void MoveY()
    {
        y+=yMove;
    }
    public void SetSpeed(float speed){this.speed=speed;}

}
