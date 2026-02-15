import java.util.Random;
import java.util.Scanner;

class hangman {

    final Scanner scanner = new Scanner(System.in);
    final String green = "\u001B[32m";
    final String reset = "\u001B[0m";
    final String red = "\u001B[31m";
    final String cyan = "\u001B[36m";

    String user_name;
    String ran_word;
    String end_difficulty;
    String highscore_placement;
    String end_language;

    char game_difficulty;
    int user_times_guessed;
    int letter_counter;
    int leaderboard_type = 0;
    int fouten = 0;
    char i_ran_word = 'a';
    char language = 'a';
    boolean on_leaderbord = false;
    int r_woorden;


    int woorden_easy_eng = 30;
    int woorden_medium_eng = 30;
    int woorden_hard_eng = 30;
    int woorden_easy_dutch = 30;
    int woorden_medium_dutch = 30;
    int woorden_hard_dutch = 30;

    int i_difficulty;

    textfiles files = new textfiles();
    
    void game_main() throws Exception{      // all constructors and main walktrough of game
         game_config();
         gen_word();
         guess_word();
         report_highscore();

    }

    void game_config(){    // welcome the player and gets game config     Later: custom mode       language
        System.out.println("\nWelcome to Hangman");
        System.out.print("Choose your name: "); user_name = scanner.next();
        System.out.println("\nA Easy mode\nB Medium mode\nC Hard mode");
        System.out.print("Please choose your difficulty: "); game_difficulty = scanner.next().charAt(0);
        System.out.println("\n");
        System.out.println("A English \nB Dutch");        
        System.out.print("Please choose the language of the word: "); language = scanner.next().charAt(0);
        switch (language) {
            case 'a': System.out.println(green+"English mode selected"+reset);
                    end_language = "English";
                    switch (game_difficulty) {
                case 'a': System.out.println(green+"Easy mode selected"+reset);
                    i_difficulty = woorden_easy_eng;
                    leaderboard_type = 0;
                    end_difficulty = "easy";
                    r_woorden = 0; 
                    break;
                case 'b': System.out.println(green+"Medium mode selected"+reset);
                    i_difficulty = woorden_medium_eng;
                    leaderboard_type = 4;
                    end_difficulty = "medium";
                    r_woorden = woorden_easy_eng;
                    break;
                case 'c': System.out.println(green+"Hard mode selected"+reset);
                    i_difficulty = woorden_hard_eng;
                    leaderboard_type = 8;
                    end_difficulty = "hard";
                    r_woorden = woorden_easy_eng + woorden_medium_eng;
                    break;
                default: System.out.println(red+"Thats not a valid choice. \nEasy mode selected"+reset);
                    i_difficulty = woorden_easy_eng;
                    game_difficulty = 'a';
                    leaderboard_type = 0;
                    end_difficulty = "easy";
                    r_woorden = 0;
                    break;
            }
                break;
            case 'b': System.out.println(green+"Dutch mode selected"+reset);
                    end_language = "Dutch";
                    switch (game_difficulty) {
                case 'a': System.out.println(green+"Easy mode selected"+reset);
                    i_difficulty = woorden_easy_eng;
                    leaderboard_type = 0;
                    end_difficulty = "easy";
                    r_woorden = woorden_easy_eng + woorden_medium_eng + woorden_hard_eng + 1; 
                    break;
                case 'b': System.out.println(green+"Medium mode selected"+reset);
                    i_difficulty = woorden_medium_eng;
                    leaderboard_type = 4;
                    end_difficulty = "medium";
                    r_woorden = woorden_easy_eng + woorden_medium_eng + woorden_hard_eng +1+ woorden_easy_dutch;
                    break;
                case 'c': System.out.println(green+"Hard mode selected"+reset);
                    leaderboard_type = 8;
                    end_difficulty = "hard";
                    r_woorden = woorden_easy_eng + woorden_medium_eng + woorden_hard_eng +1+ woorden_easy_dutch + woorden_medium_dutch;
                    break;
                default: System.out.println(red+"Thats not a valid choice. \nEasy mode selected"+reset);
                    game_difficulty = 'a';
                    leaderboard_type = 0;
                    end_difficulty = "easy";
                    r_woorden = woorden_easy_eng + woorden_medium_eng + woorden_hard_eng +1;
                    break;
            }
                break;
            default: System.out.println(red+"Thats not a valid choice. \nEnglish mode selected"+reset);
            language = 'a';
            end_language = "English";
                switch (game_difficulty) {
                case 'a': System.out.println(green+"Easy mode selected"+reset);
                    i_difficulty = woorden_easy_eng;
                    leaderboard_type = 0;
                    end_difficulty = "easy";
                    r_woorden = 0; 
                    break;
                case 'b': System.out.println(green+"Medium mode selected"+reset);
                    i_difficulty = woorden_medium_eng;
                    leaderboard_type = 4;
                    end_difficulty = "medium";
                    r_woorden = woorden_easy_eng;
                    break;
                case 'c': System.out.println(green+"Hard mode selected"+reset);
                    i_difficulty = woorden_hard_eng;
                    leaderboard_type = 8;
                    end_difficulty = "hard";
                    r_woorden = woorden_easy_eng + woorden_medium_eng;
                    break;
                default: System.out.println(red+"Thats not a valid choice. \nEasy mode selected"+reset);
                    i_difficulty = woorden_easy_eng;
                    game_difficulty = 'a';
                    leaderboard_type = 0;
                    end_difficulty = "easy";
                    r_woorden = 0;
                    break;
            }
                break;
        }
    }

    void gen_word() {                // generate a random word for the usser to guess            later: gui
        Random random = new Random();
        r_woorden = random.nextInt(i_difficulty) + r_woorden;
       try {
            ran_word = textfiles.get_word(r_woorden);
        } catch (Exception e) {
            System.err.println("ln 61");
       }
    }

    void guess_word() {                   // makes the usser guess the word   
        letter_counter = ran_word.length();
        char[] letters = ran_word.toCharArray();
        char[] result = ran_word.toCharArray();

    for (int s = 0; s < ran_word.length(); s++){
        result[s] = '_';
        System.out.print(result[s]+" ");
    }
//System.out.println("    config:   "+ran_word);
//System.out.println("lettercounter   "+letter_counter);

     while (letter_counter > 0 ) {
        
        System.out.print("\nYour guess: "); char user_guess = scanner.next().charAt(0);
            user_times_guessed++;
            
        int b = 0;   
            for (int i = 0; i < ran_word.length(); i++){
                b = b + 0;
                if (result[i] == '_') {
                    if (letters[i] == user_guess){
                        result[i] = user_guess;
                        letter_counter--;
                        b++;
                }
            else result[i] = '_'; 
        }
    }    
    if (b == 0) {
            fouten++;
        }
    print_hangman(fouten);
    for (int w = 0; w < ran_word.length(); w++) {
        if (result[w] != '_') {
        System.out.print(green+result[w]+reset+" ");
    }
    else System.out.print(result[w]+" ");
    }

}
    }

    void print_hangman (int fouten) {

        System.out.println("\n\n");
    switch (fouten) {
        case 0: System.out.print("\n|\n|\n|\n|\n|\n|\n|\n");
            break;
        case 1: System.out.print("\n|\n|\n|\n|\n|\n|\n|\n|========= ");
            break;
        case 2: System.out.print("\n|\n|\n|   |\n|   |\n|   |\n|   |\n|   |\n|========= ");
            break;
        case 3: System.out.print("\n|\n|    ____\n|   |\n|   |\n|   |\n|   |\n|   |\n|========= ");
            break;
        case 4: System.out.print("\n|\n|    ____\n|   |    |\n|   |\n|   |\n|   |\n|   |\n|========= ");
            break;
        case 5: System.out.print("\n|\n|    ____\n|   |    |\n|   |    0\n|   |\n|   |\n|   |\n|========= ");
            break;
        case 6: System.out.print("\n|\n|    ____\n|   |    |\n|   |    0\n|   |    |\n|   |\n|   |\n|========= ");
            break;
        case 7: System.out.print("\n|\n|    ____\n|   |    |\n|   |    0\n|   |   /|\n|   |\n|   |\n|========= ");
            break;
        case 8: System.out.print("\n|\n|    ____\n|   |    |\n|   |    0\n|   |   /|\\  \n|   |\n|   |\n|========= ");
            break;
        case 9: System.out.print("\n|\n|    ____\n|   |    |\n|   |    0\n|   |   /|\\  \n|   |   /\n|   |\n|========= ");
            break;
        case 10: System.out.print("\n|\n|    ____\n|   |    |\n|   |    0\n|   |   /|\\  \n|   |   / \\ \n|   |\n|========= ");
            break;
    }
    if (fouten <= 9) {
        System.out.print(red+"    wrong guesses: "+fouten+"/10"+reset);
    } 
    else { System.out.print(red+"    wrong guesses: 10/10, \n               You failed!"+reset);
    letter_counter = -1;
    }
System.out.println("\n");
    }

    void report_highscore() throws Exception {                // report back the stats and score    later: highscore system
        highscore_placement = files.write_highscore(leaderboard_type, user_times_guessed);
        on_leaderbord = files.user_on_leaderboard();
        
        
        if (letter_counter == 0) {
            if (on_leaderbord == true) {
                System.out.println(green+"\n\n\nGoodjob, "+user_name+"! The word was: "+ran_word+".");   
                System.out.println("Your position on the "+end_difficulty+" leaderboard: "+highscore_placement+reset);
            }
            else System.out.println(red+"\n\n\nSorry you did not make the "+end_difficulty+" leaderboard"+reset);    
        }
        else System.out.println(red+"\n\n\nNoob! \nYou didn't manage to guess the word"+reset);
    

        System.out.println(cyan+"\nStats:");
        System.out.println("Language word: "+end_language);
        System.out.println("Guesses: "+user_times_guessed);
        System.out.println("Difficulty: "+end_difficulty);
        System.out.println(reset);


    }
}
