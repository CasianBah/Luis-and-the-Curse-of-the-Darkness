package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
public class PathTile extends Tile{
    public PathTile(int id)
    {
        super(Assets.path[id-1],id);
    }
}
