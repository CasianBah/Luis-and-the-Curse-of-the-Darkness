package PaooGame;


import PaooGame.Items.Hero;
import PaooGame.Tiles.Tile;
import java.awt.*;

public class Camera {
    private RefLinks refLink;
    private float x;
    private float y;
    private int width;
    private int height;

    public Camera(RefLinks refLink, float x, float y, int width, int height)
    {
        this.refLink = refLink;
        this.x = x ;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void Update(Hero hero){
        float newX = hero.GetX() - getWidth() / 2 + Tile.TILE_HEIGHT;
        float newY = hero.GetY() - getHeight() / 2 + Tile.TILE_HEIGHT;

        newX = Math.max(0, Math.min(newX, 50*Tile.TILE_WIDTH - getWidth()));
        newY = Math.max(0, Math.min(newY, 50*Tile.TILE_HEIGHT - getHeight()));

        x = newX;
        y = newY;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

}
