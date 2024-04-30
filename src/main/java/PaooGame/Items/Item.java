package PaooGame.Items;

import java.awt.*;

import PaooGame.RefLinks;
public abstract class Item {
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected Rectangle bounds;
    protected Rectangle normalBounds;
    protected Rectangle attackBounds;

    protected RefLinks refLink;
    public int lastSprite = 1;
    public Item(RefLinks refLink, float x, float y, int width, int height)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.refLink = refLink;

        normalBounds = new Rectangle(0,0,width,height);
        attackBounds = new Rectangle(0,0,width,height);
        bounds = normalBounds;
    }

    public abstract void Update();
    public abstract void Draw(Graphics g);

    public float GetX(){return x;}
    public float GetY(){return y;}

}
