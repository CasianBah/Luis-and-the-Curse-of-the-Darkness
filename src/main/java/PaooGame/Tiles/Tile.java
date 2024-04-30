package PaooGame.Tiles;



import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {
    private static final int NO_TILES =32;
    public static Tile[] tiles =new Tile[NO_TILES];
    public static Tile grassTile = new GrassTile(0);
    public static Tile pathTile = new PathTile(1);
    public static Tile pathUpTile= new PathTile(2);
    public static Tile pathDownTile= new PathTile(3);
    public static Tile pathLeftTile= new PathTile(4);
    public static Tile pathRight= new PathTile(5);
    public static Tile pathCorner1= new PathTile(6);
    public static Tile pathCorner2= new PathTile(7);
    public static Tile pathCorner3 = new PathTile(8);
    public static Tile pathCorner4 = new PathTile(9);
    public static Tile pathCorner5 = new PathTile(10);
    public static Tile pathCorner6 = new PathTile(11);
    public static Tile pathCorner7 = new PathTile(12);
    public static Tile pathCorner8 = new PathTile(13);
    public static Tile pathCorner9 = new PathTile(14);
    public static Tile pathCorner10 = new PathTile(15);

    public static final int TILE_WIDTH = 48;
    public static final int TILE_HEIGHT = 48;
    protected BufferedImage img;
    protected final int id;

    public Tile(BufferedImage image, int idd)
    {
        img= image;
        id=idd;

        tiles[id]=this;
    }

    public void Update()
    {

    }

    public void Draw(Graphics g, int x, int y)
    {g.drawImage(img,x,y,TILE_WIDTH,TILE_HEIGHT,null);}

    public boolean IsSolid(){return false;}
    public int GetId(){return id;}

}
