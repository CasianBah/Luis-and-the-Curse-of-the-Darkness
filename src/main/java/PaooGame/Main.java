package PaooGame;
public class Main {
    public static void main(String[] args) {
        Game game = new Game("Luis si Blestemul Intunericului",960,480);
        System.out.println("Controls are W A S D");
        System.out.println("Press key '1' to attack");
        game.StartGame();
    }
}