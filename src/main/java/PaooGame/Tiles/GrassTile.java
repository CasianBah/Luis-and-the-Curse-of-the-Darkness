package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
public class GrassTile extends Tile{
    public GrassTile(int id)
    {
        super(Assets.grass,id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
