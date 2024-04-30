package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private RefLinks refLink;
    private int width;
    private int height;
    private int [][] tiles;

    public Map(RefLinks refLink,String mapNumber)
    {
        this.refLink = refLink;
        LoadWorld(mapNumber);
    }

    public void Update()
    {

    }

    public void Draw(Graphics g)
    {
        for(int y=0;y<50;y++)
        {
            for (int x=0;x<50;x++)
                GetTile(x,y).Draw(g,(int)x*Tile.TILE_HEIGHT, (int)y*Tile.TILE_WIDTH);
        }
    }

    public Tile GetTile(int x, int y)
    {
        if(x<0 || y<0 || x>=width ||y>= height)
            return Tile.grassTile;
        Tile t = Tile.tiles[tiles[x][y]];
        return t;
    }

    private void LoadWorld(String mapNumber)
    {
        try {
            Scanner scanner = new Scanner(new File("res/maps/"+mapNumber));
            String line;
            ArrayList<String[]> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] parts = line.split(" ");
                lines.add(parts);
            }
            width = lines.get(0).length;
            height = lines.size();
            tiles = new int[width][height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    tiles[x][y] = Integer.parseInt(lines.get(y)[x]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
