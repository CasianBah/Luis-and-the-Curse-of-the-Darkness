package PaooGame.Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage spriteSheet;
    private static final int tileWidth=16;
    private static final int tileHeight = 16;
    private static final int heroWidth = 32;
    private static final int heroHeight =32;
    public SpriteSheet(BufferedImage buffImg)
    {
        spriteSheet = buffImg;
    }

    public  BufferedImage crop(int x, int y)
    {
        return spriteSheet.getSubimage(x*tileWidth,y*tileHeight,tileWidth,tileHeight);
    }

    public BufferedImage cropCharacter(int x, int y)
    {
        return spriteSheet.getSubimage(x*heroWidth,y*heroHeight,heroWidth,heroHeight);
    }
    public BufferedImage cropBoss(int x, int y)
    {
        return spriteSheet.getSubimage(x*64,y*64,64,64);
    }
}
