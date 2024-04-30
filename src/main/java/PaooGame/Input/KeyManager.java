package PaooGame.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    private boolean[] keys; /*!< Vector de flaguri pentru toate tastele. Tastele vor fi regasite dupa cod [0 - 255]*/
    public boolean up;      /*!< Flag pentru tasta "sus" apasata.*/
    public boolean down;    /*!< Flag pentru tasta "jos" apasata.*/
    public boolean left;    /*!< Flag pentru tasta "stanga" apasata.*/
    public boolean right;
    public boolean[] ability = new boolean[10];
    public KeyManager()
    {
        ///Constructie vector de flaguri aferente tastelor.
        keys = new boolean[256];
    }

    public void Update()
    {
        up    = keys[KeyEvent.VK_W];
        down  = keys[KeyEvent.VK_S];
        left  = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];

        ability[1] = keys[KeyEvent.VK_1];
        ability[2] = keys[KeyEvent.VK_2];
        ability[3] = keys[KeyEvent.VK_3];
        ability[4] = keys[KeyEvent.VK_4];
        ability[5] = keys[KeyEvent.VK_5];
        ability[6] = keys[KeyEvent.VK_6];
        ability[7] = keys[KeyEvent.VK_7];
        ability[8] = keys[KeyEvent.VK_8];
        ability[9] = keys[KeyEvent.VK_1];
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
