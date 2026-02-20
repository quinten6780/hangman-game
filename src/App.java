

public class App {
    public static void main(String[] args) throws Exception {

        gui userInterface = new gui();
        hangman game = new hangman(userInterface);
        game.game_main();

    }
}
