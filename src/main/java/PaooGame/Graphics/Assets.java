package PaooGame.Graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage[] heroIdleRight = new BufferedImage[10];
    public static BufferedImage[] heroIdleLeft = new BufferedImage[10];
    public static BufferedImage[] heroLeft = new BufferedImage[10];
    public static BufferedImage[] heroRight = new BufferedImage[10];
    public static BufferedImage[] heroAtackRight = new BufferedImage[10];
    public static BufferedImage[] heroAtackLeft = new BufferedImage[10];
    public static BufferedImage[] heroDeathRight = new BufferedImage[10];
    public static BufferedImage[] heroDeathLeft = new BufferedImage[10];
    public static BufferedImage[] enemy1Right = new BufferedImage[10];
    public static BufferedImage[] enemy1Left = new BufferedImage[10];
    public static BufferedImage[] enemy1DeathR = new BufferedImage[10];
    public static BufferedImage[] enemy1DeathL = new BufferedImage[10];
    public static BufferedImage[] enemy1AttackR = new BufferedImage[10];
    public static BufferedImage[] enemy1AttackL = new BufferedImage[10];
    public static BufferedImage[] enemyBossR = new BufferedImage[10];
    public static BufferedImage[] enemyBossL = new BufferedImage[10];
    public static BufferedImage[] enemyBossAttackR = new BufferedImage[10];
    public static BufferedImage[] enemyBossAttackL = new BufferedImage[10];
    public static BufferedImage[] enemyBossDeathR = new BufferedImage[10];
    public static BufferedImage[] enemyBossDeathL = new BufferedImage[10];
    public static BufferedImage[] path = new BufferedImage[20];
    public static BufferedImage grass;
    public static BufferedImage tree;
    public static BufferedImage[] projectile = new BufferedImage[2];
    public static void Init()
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/tileset.png"));
        SpriteSheet luisR = new SpriteSheet(ImageLoader.LoadImage("/textures/YoungWizard.png"));
        SpriteSheet luisL = new SpriteSheet(ImageLoader.LoadImage("/textures/YoungWizard2.png"));
        SpriteSheet enemyR = new SpriteSheet(ImageLoader.LoadImage("/textures/Enemy.png"));
        SpriteSheet enemyL = new SpriteSheet(ImageLoader.LoadImage("/textures/Enemy2.png"));
        SpriteSheet projR = new SpriteSheet(ImageLoader.LoadImage("/textures/ProjectileRight.png"));
        SpriteSheet projL = new SpriteSheet(ImageLoader.LoadImage("/textures/ProjectileLeft.png"));
        SpriteSheet bossR = new SpriteSheet(ImageLoader.LoadImage("/textures/statueSpritesheet.png"));
        SpriteSheet bossL = new SpriteSheet(ImageLoader.LoadImage("/textures/statueSpritesheet2.png"));

        for(int i=0;i<10;i++)
        {
            heroDeathRight[i] = luisR.cropCharacter(i,4);
            heroDeathLeft[i] = luisL.cropCharacter(9-i,4);
            heroAtackRight[i] = luisR.cropCharacter(i,3);
            heroAtackLeft[i] = luisL.cropCharacter(9-i,3);
            heroIdleRight[i] = luisR.cropCharacter(i,0);
            heroIdleLeft[i] = luisL.cropCharacter(9-i,0);
            heroRight[i] = luisR.cropCharacter(i,2);
            heroLeft[i] = luisL.cropCharacter(9-i,2);
            enemy1Right[i] = enemyR.cropCharacter(i,2);
            enemy1Left[i] = enemyL.cropCharacter(9-i,2);
            enemy1DeathR[i] = enemyR.cropCharacter(i,4);
            enemy1DeathL[i] = enemyL.cropCharacter(9-i,4);
            enemy1AttackR[i] = enemyR.cropCharacter(i,3);
            enemy1AttackL[i] = enemyL.cropCharacter(9-i,3);
        }

        for(int i=0;i<8;i++)
        {
            enemyBossR[i] = bossR.cropBoss(i,1);
            enemyBossL[i] = bossL.cropBoss(7-i,1);
        }
        for(int i=0;i<5;i++)
        {
            enemyBossAttackR[i] = bossR.cropBoss(i,2);
            enemyBossAttackL[i] = bossL.cropBoss(4-i,2);
        }
        for(int i=0;i<6;i++)
        {
            enemyBossDeathR[i] = bossR.cropBoss(i,3);
            enemyBossDeathL[i] = bossL.cropBoss(5-i,3);
        }

        grass = sheet.crop(9,0);
        path[0] = sheet.crop(0,7);
        path[1] = sheet.crop(8,7);
        path[2] = sheet.crop(12,7);
        path[3] = sheet.crop(7,7);
        path[4] = sheet.crop(16,7);
        path[5] = sheet.crop(29,8);
        path[6] = sheet.crop(21,8);
        path[7] = sheet.crop(27,8);
        path[8] = sheet.crop(19,8);
        path[9] = sheet.crop(22,8);
        path[10] = sheet.crop(0,9);
        path[11] = sheet.crop(28,8);
        path[12] = sheet.crop(20,8);
        path[13] = sheet.crop(19,8);
        path[14] = sheet.crop(27,8);
       // path[15] = sheet.crop()

        projectile[0] = projL.crop(0,0);
        projectile[1] = projR.crop(0,0);
    }

}
