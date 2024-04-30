package PaooGame.State;

import PaooGame.Camera;
import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;

import java.awt.*;
import java.util.ArrayList;

import static PaooGame.Items.EnemyFactory.createEnemy;

public class PlayState extends State{
    private Map map;
    private Hero hero;
    private ArrayList<Enemy> enemies;
    private Camera camera;


    public int mapNumber=1;
    boolean breakGame = false;
    public PlayState(RefLinks refLink, Camera camera)
    {
        super(refLink);
        this.camera = camera;
        map = new Map(refLink, "map01.txt");
        refLink.SetMap(map);
        hero = new Hero(refLink, 600, 250);
        enemies = new ArrayList<>();
        enemies.add(createEnemy(refLink, 1100, 210, 1));
        enemies.add(createEnemy(refLink, 1300, 300, 1));
        enemies.add(createEnemy(refLink, 500, 500, 1));
        enemies.add(createEnemy(refLink, 1290, 540, 1));
        enemies.add(createEnemy(refLink, 850, 490, 1));
        enemies.add(createEnemy(refLink, 500, 740, 1));
        enemies.add(createEnemy(refLink, 740, 710, 1));
        enemies.add(createEnemy(refLink, 1300, 740, 2));
        refLink.SetHero(hero);
        refLink.SetEnemy(enemies);
    }

    @Override
    public void Update()
    {
        LevelUpdate();
        map.Update();
        hero.Update();
        for(int i=0;i<enemies.size();i++){
            Enemy enemy = enemies.get(i);
            enemy.Update();
            if(enemy.removeEnemy)
                enemies.remove(enemy);
        }

        camera.Update(hero);

    }

    @Override
    public void Draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(-camera.getX(),-camera.getY());

        map.Draw(g);
        hero.Draw(g);
        for(Enemy enemy : enemies){
            enemy.Draw(g);
        }

        g2.translate(camera.getX(), camera.getY());
    }

    public void LevelUpdate()
    {
        if(enemies.isEmpty())
        {
            mapNumber++;
            if(mapNumber==2) {
                map = new Map(refLink, "map02.txt");
                refLink.SetMap(map);
                hero.SetX(75);
                hero.SetY(1203);
                enemies.add(createEnemy(refLink, 647, 972, 1));
                enemies.add(createEnemy(refLink, 793, 891, 1));
                enemies.add(createEnemy(refLink, 1172, 1011, 1));
                enemies.add(createEnemy(refLink, 1525, 908, 1));
                enemies.add(createEnemy(refLink, 500, 1203, 1));
                enemies.add(createEnemy(refLink, 661, 1556, 2));
                enemies.add(createEnemy(refLink, 1350, 1556, 2));
                enemies.add(createEnemy(refLink, 1991, 1040, 2));
                enemies.add(createEnemy(refLink, 1976, 1260, 1));
                enemies.add(createEnemy(refLink, 1902, 1332, 1));
            }
            if(mapNumber==3){
                map = new Map(refLink, "map03.txt");
                refLink.SetMap(map);
                hero.SetX(75);
                hero.SetY(1203);
                enemies.add(createEnemy(refLink, 1037, 1075, 1));
                enemies.add(createEnemy(refLink, 1037, 1123, 2));
                enemies.add(createEnemy(refLink, 1037, 1123, 1));
                enemies.add(createEnemy(refLink, 984, 1123, 1));

            }
        }
    }

}
