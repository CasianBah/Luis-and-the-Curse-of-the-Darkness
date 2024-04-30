package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Input.*;
import PaooGame.State.*;
import PaooGame.Tiles.Tile;
import PaooGame.Graphics.Assets;

import java.sql.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
public class Game implements Runnable {
    private GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;
    private Graphics g;
    private Camera camera;
    private PlayState playState;
    // private State menuState;
    //private State settingsState;
    //private State aboutState;
    private KeyManager keyManager;

    private RefLinks refLink;

    public Game(String title, int width, int height) {
        wnd = new GameWindow(title, width, height);
        runState = false;
        keyManager = new KeyManager();

    }

    private void InitGame() {

        wnd.BuildGameWindow();
        wnd.GetWndFrame().addKeyListener(keyManager);
        Assets.Init();
        refLink = new RefLinks(this);
        camera = new Camera(refLink, 0, 0, GetWidth(), GetHeight());
        playState = new PlayState(refLink, camera);
        //menuState = new MenuState(reflink);
        //settingsState = new SettingsState(refLink);
        //aboutState      = new AboutState(refLink);
        State.SetState(playState);

    }

    public void run() {

        InitGame();
        String url = "jdbc:postgresql://localhost:5432/Recorduri";
        String username = "postgres";
        String password = "cas02";
        String insertQuery = "INSERT INTO records (secunde) VALUES (?)";
        Connection connection = null;
        PreparedStatement statement = null;

        long oldTime = System.nanoTime();
        long curentTime;
        long initTime = System.currentTimeMillis();


        final int framesPerSecond = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/


        while (runState == true) {
            if (playState.mapNumber == 4) break;
            curentTime = System.nanoTime();
            if ((curentTime - oldTime) > timeFrame) {
                Update();
                Draw();
                oldTime = curentTime;
            }
        }


        long endGameTime = System.currentTimeMillis();

        float elapsedTimeInSeconds = (endGameTime - initTime) / 1000F;

        System.out.println("Felicitari! Ati terminat jocul in "+elapsedTimeInSeconds+" secunde!");

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.prepareStatement(insertQuery);
            statement.setFloat(1, elapsedTimeInSeconds);
            statement.executeUpdate();
        } catch (SQLException e) {
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e1) {
                    System.out.println("Error occurred while closing statement: " + e1.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e2) {
                    System.out.println("Error occurred while closing connection: " + e2.getMessage());
                }
            }
        }
        StopGame();
    }





    public synchronized void StartGame()
    {
        if(runState == false)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        else
        {

            return;
        }
    }

    public synchronized void StopGame()
    {
        if(runState == true)
        {
            runState = false;
            try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {

            return;
        }
    }


    private void Update()
    {

        keyManager.Update();
        wnd.GetWndFrame().requestFocus();

       if(State.GetState() != null)
       {
              State.GetState().Update();
       }

    }

    private void Draw()
    {

        bs = wnd.GetCanvas().getBufferStrategy();

        if(bs == null)
        {
            try
            {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        if(State.GetState() != null)
        {

            State.GetState().Draw(g);
        }

        bs.show();
        g.dispose();
    }
    public int GetWidth()
    {
        return wnd.GetWndWidth();
    }
    public int GetHeight()
    {
        return wnd.GetWndHeight();
    }
    public KeyManager GetKeyManager()
    {
        return keyManager;
    }
}



