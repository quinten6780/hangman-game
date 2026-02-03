import java.util.Random;
import java.util.Scanner;


class hangman {


    final Scanner scanner = new Scanner(System.in);
   // final List<String> word = List.a();


  
    String user_name;
    String ran_word;

    int game_difficulty;
    int i_ran_word = 0;
    boolean word_guessed = false;
    
    void game_main() {      // all constructors and main walktrough of game
        
 
        
       //game_config();
        gen_word();
        //guess_word();
       
                                        
    }

    void game_config(){    // welcome the player and gets game config     Later: custom mode
        System.out.println("\nWelcome to Hangman\n");
        System.out.print("Your name: "); user_name = scanner.next();
        System.out.println("Please choose your difficulty:\nA Easy mode\nB Medium mode\nC Hard mode");
        System.out.print("Choose your difficulty: "); game_difficulty = scanner.next().charAt(0);

        
        
    
        

    }

    void gen_word() {                // generate a random word for the usser to guess            later: gui
        Random random = new Random();
        i_ran_word = random.nextInt(30)+i_ran_word;
       try {
            ran_word = textfiles.textfiles_main(i_ran_word);
        } catch (Exception e) {
            System.err.println("ln 61");
       }

        char[] letters = "cake".toCharArray();

       
        
       System.out.println(letters);

        char[] resultaat = new char[ran_word.length()];

        for (int i = 0; i < ran_word.length(); i++) {
        resultaat[i] = '_';
}
        System.out.println(resultaat);
                

    while (word_guessed == false) {
        System.out.print("Your guess: "); char user_input = scanner.next().charAt(0);
        for (int i = 0; i < ran_word.length(); i++){
            if (letters[i] == user_input) {
                resultaat[i] = user_input;
                
                System.out.println(resultaat);
            }
            

        }
    }

    System.out.println("\n");
   

    }

    void guess_word() {                   // makes the usser guess the word   
          

        
    
    while (word_guessed == false) {
        System.out.println("\n------");
    }
        


    }

    void report_highscore() {                // report back the stats and score    later: highscore system
           




    }



}
