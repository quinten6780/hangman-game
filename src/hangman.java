import java.util.Scanner;



class hangman {

     


    final Scanner scanner = new Scanner(System.in);

  
    
    String user_name;
    
    void game_main() {                  // all constructors and main walktrough of game
        game_config();
        
        System.out.println(user_name);
                                        
    }

    void game_config(){             // welcome the player and gets game config     later: custom mode
        System.out.println("\nWelcome to Hangman\n");

        if (App.config == true) { user_name = "\nconfig_name"; }
        else {System.out.print("Your name: "); user_name = scanner.next();}

        if (App.config == true) { System.out.println("CONFIG user_name =   "+user_name); }

        System.out.println("Please choose your difficulty:\nA Easy mode\nB Medium mode\nC Hard mode");

    
        

    }

    void gen_word() {                // generate a random word for the usser to guess            later: gui
           



    }

    void guess_word() {                   // makes the usser guess the word   
          



    }

    void report_highscore() {                // report back the stats and score    later: highscore system
           




    }



}
