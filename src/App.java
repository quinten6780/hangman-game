

public class App {
    public static boolean config_messages = true;    // show config messages
    public static boolean config_user_name = true;   // auto fill user_name with config_name
    public static boolean config_game_difficulty = true;   // auto fill game_difficulty with a

 


    public static void main(String[] args) throws Exception {
   

      

      int i_ran_word = 0;


        textfiles files = new textfiles();
        hangman game = new hangman();

      //String n = files.textfiles_main(i_ran_word);
        game.game_main();

    }
}
