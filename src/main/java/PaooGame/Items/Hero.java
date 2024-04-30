package PaooGame.Items;


import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hero extends Character{
    private BufferedImage image;
    private boolean AttackFlag = false;
    private ArrayList<ProjectileHero> projectilesHero;
    private long lastShotTime;
    private final long shotCooldown = 500;
    private boolean DeathFlag = false;
    public Hero(RefLinks refLink, float x, float y)
    {
        super(refLink,x,y, Character.DEFAULT_CREATURE_WIDTH,Character.DEFAULT_CREATURE_HEIGHT);
        image = Assets.heroRight[0];
        life = 3000;
        
        normalBounds.x = 24;
        normalBounds.y = 36;
        normalBounds.width = 16;
        normalBounds.height = 32;

        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;

        projectilesHero = new ArrayList<>();
        lastShotTime = System.currentTimeMillis();
    }

    @Override
    public void Update()
    {
        GetInput();
        CheckCollision();
        Attack();
        Move();
        Death();
        Animation();
        UpdateProjectiles();
    }

    private void GetInput()
    {
        xMove = 0;
        yMove = 0;

        if(refLink.GetKeyManager().up)
        {
            yMove = -speed;
        }

        if(refLink.GetKeyManager().down)
        {
            yMove = speed;
        }

        if(refLink.GetKeyManager().left)
        {
            xMove = -speed;
        }

        if(refLink.GetKeyManager().right)
        {

            xMove = speed;
        }
        if(DeathFlag)
        {
            xMove=0;
            yMove=0;
        }
    }

    private boolean deathAnimationPlayed = false;
    private void Animation()
    {
        spriteCounter++;

        if(spriteCounter > 2)
        {
            spriteNum++;
            if (spriteNum > 9)
                spriteNum = 0;

            spriteCounter = 0;

            if (DeathFlag) {
                if(!deathAnimationPlayed) {
                    if (lastSprite == 1)
                        image = Assets.heroDeathRight[spriteNum];
                    else image = Assets.heroDeathLeft[spriteNum];

                    if (spriteNum == 9) {
                        image = Assets.heroDeathRight[9];
                    }
                    if(spriteNum==9) deathAnimationPlayed =true;
                }

            }
            else if(AttackFlag){
                if(lastSprite == 1)
                    image = Assets.heroAtackRight[spriteNum];
                else image = Assets.heroAtackLeft[spriteNum];
                if(spriteNum == 9)
                    AttackFlag = false;
            }
            else {
                if (refLink.GetKeyManager().left) {
                    lastSprite = 0;
                    image = Assets.heroLeft[spriteNum];
                } else if (refLink.GetKeyManager().right) {
                    lastSprite = 1;
                    image = Assets.heroRight[spriteNum];
                } else if (refLink.GetKeyManager().up) {
                    if (lastSprite == 1)
                        image = Assets.heroRight[spriteNum];
                    else image = Assets.heroLeft[spriteNum];
                } else if (refLink.GetKeyManager().down) {
                    if (lastSprite == 1)
                        image = Assets.heroRight[spriteNum];
                    else image = Assets.heroLeft[spriteNum];
                }
                else {
                    if (lastSprite == 1)
                        image = Assets.heroIdleRight[spriteNum];
                    else image = Assets.heroIdleLeft[spriteNum];
                }
            }

        }

    }

    private void Attack()
    {
        if(refLink.GetKeyManager().ability[1]) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastShotTime >=shotCooldown){
                AttackFlag = true;
                shootProjectile();
                lastShotTime = currentTime;
            }
            xMove=0;
            yMove=0;
        }
    }

    private void UpdateProjectiles()
    {
        for (int i = 0; i < projectilesHero.size(); i++) {
            ProjectileHero p = projectilesHero.get(i);
            p.Update();
            if (p.ShouldRemove()) {
                projectilesHero.remove(i);
                i--;
            }
        }
    }

    private void shootProjectile()
    {
        if (projectilesHero.isEmpty() || projectilesHero.get(projectilesHero.size() - 1).GetX() > 30) {
            int direction = (lastSprite==1) ? 1 : 0;
            ProjectileHero p = new ProjectileHero(refLink, this, direction);
            projectilesHero.add(p);
        }
    }

    public void Death()
    {
        if(life<=0)
            DeathFlag = true;
       /* if(life > 0) System.out.println("viata :"+life);
        else System.out.println("MORT");*/
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);

        for(ProjectileHero p : projectilesHero)
            p.Draw(g);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);

    }

    public float GetX(){return x;}
    public float GetY(){return y;}
    public void SetX(float x){this.x=x;}
    public void SetY(float y){this.y=y;}
}
