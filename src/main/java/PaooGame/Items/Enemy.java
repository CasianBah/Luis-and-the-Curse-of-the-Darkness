package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Character{
    private BufferedImage image;
    private boolean movingRight = true;
    private float leftLimit = this.x+100;
    private float rightLimit = this.x+400;
    private boolean AttackFlag =false;
    private boolean DeathFlag = false;
    private boolean AttackHit = false;
    public boolean removeEnemy = false;
    private long lastHitTime;
    private long HitCooldown = 500;
    private static float damage = 4;

    private int tier;
    public Enemy(RefLinks refLink, float x, float y, int tier)
    {
        super(refLink,x,y, Character.DEFAULT_CREATURE_WIDTH,Character.DEFAULT_CREATURE_HEIGHT);
        this.tier = tier;

        if(tier==1) image = Assets.enemy1Right[0];
        else {
            image = Assets.enemyBossR[0];
            width=128;
            height=128;
            life = 20;
            damage = 10;
            HitCooldown = 1000;
            SetSpeed(1.8f);
        }

        normalBounds.x = 24;
        normalBounds.y = 36;
        normalBounds.width = 16;
        normalBounds.height = 32;

        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
    }

    @Override
    public void Update()
    {
        AttackState();
        Attack();
        NPCMove();
        Death();
        Animation();
    }

    public void AttackState()
    {
        int attackRangeX = 400;
        int attackRangeY = 100;

        if(Math.abs(refLink.GetHero().GetX() - this.x) <= attackRangeX && Math.abs(refLink.GetHero().GetY() - this.y) <= attackRangeY && refLink.GetHero().life>0) {
            this.AttackFlag = true;
        } else {
            this.AttackFlag = false;
        }

    }

    public void NPCMove()
    {
        int limita=0;
        if (DeathFlag) {
            xMove = 0;
            yMove = 0;
            if (spriteNum == 8)
                removeEnemy = true;
        }
        else if(this.AttackFlag){
            this.SetSpeed(2.2f);

            if(this.x<refLink.GetHero().GetX()) limita = -50;
            else if(this.x>refLink.GetHero().GetX()) limita = 50;

            if(this.x<refLink.GetHero().GetX()+75 && xMove<0) AttackHit = true;
            else if(this.x>refLink.GetHero().GetX()-75 && xMove>0) AttackHit = true;
            else AttackHit = false;

            if(refLink.GetHero().GetX()+limita <= this.x)
                xMove = -speed;
            else if(refLink.GetHero().GetX()+limita >= this.x)
                xMove = speed;
            if(refLink.GetHero().GetY() > this.y)
                yMove = speed;
            else if(refLink.GetHero().GetY() < this.y)
                yMove = -speed;
        }
        else {
            this.SetSpeed(1.5f);
            if (movingRight) {
                xMove = speed;
                if (x >= rightLimit) {
                    movingRight = false;
                    xMove = -speed;
                }
            } else {
                xMove = -speed;
                if (x <= leftLimit) {
                    movingRight = true;
                    xMove = speed;
                }
            }
        }

        CheckCollision();
        Move();
    }

    public void Death() {
        if(life <= 0)
            DeathFlag = true;
    }

    public void Attack()
    {
        if(AttackHit)
        {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastHitTime >= HitCooldown){
                refLink.GetHero().life-=damage;
                lastHitTime = currentTime;
            }
        }
    }
    private void Animation()
    {
        spriteCounter++;

        if(spriteCounter > 2 && tier==1)
        {
            spriteNum++;
            if (spriteNum > 9)
                spriteNum = 0;
            spriteCounter = 0;

            if(DeathFlag){
                if(lastSprite == 1)
                    image = Assets.enemy1DeathR[spriteNum];
                else image = Assets.enemy1DeathL[spriteNum];
            }else if(AttackHit){
                if(lastSprite == 1)
                    image = Assets.enemy1AttackR[spriteNum];
                else image = Assets.enemy1AttackL[spriteNum];

            }
            else {
                    if (xMove < 0) {
                        lastSprite = 0;
                        image = Assets.enemy1Left[spriteNum];
                    } else if (xMove > 0) {
                        lastSprite = 1;
                        image = Assets.enemy1Right[spriteNum];
                    } else if (yMove < 0) {
                        if (lastSprite == 1)
                            image = Assets.enemy1Right[spriteNum];
                        else image = Assets.enemy1Left[spriteNum];
                    } else if (yMove > 0) {
                        if (lastSprite == 1)
                            image = Assets.enemy1Right[spriteNum];
                        else image = Assets.enemy1Left[spriteNum];
                    }
                }
        }
        else if(spriteCounter > 8 && tier==2)
        {
            spriteNum++;
            if (spriteNum > 8)
                spriteNum = 0;
            spriteCounter = 0;

            if(DeathFlag){
                if(lastSprite == 1)
                    image = Assets.enemyBossDeathR[spriteNum];
                else image = Assets.enemyBossDeathL[spriteNum];
            }else if(AttackHit){
                if(lastSprite == 1)
                    image = Assets.enemyBossAttackR[spriteNum];
                else image = Assets.enemyBossAttackL[spriteNum];
               /*if(spriteNum == 4)
                    spriteNum = 0;*/
            }
            else {
                if (xMove < 0) {
                    lastSprite = 0;
                    image = Assets.enemyBossL[spriteNum];
                } else if (xMove > 0) {
                    lastSprite = 1;
                    image = Assets.enemyBossR[spriteNum];
                } else if (yMove < 0) {
                    if (lastSprite == 1)
                        image = Assets.enemyBossR[spriteNum];
                    else image = Assets.enemyBossL[spriteNum];
                } else if (yMove > 0) {
                    if (lastSprite == 1)
                        image = Assets.enemyBossR[spriteNum];
                    else image = Assets.enemyBossL[spriteNum];
                }
            }
        }
    }



    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
       // g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}
