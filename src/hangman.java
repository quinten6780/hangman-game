import java.util.Random;
import java.util.Scanner;


class hangman {


    final Scanner scanner = new Scanner(System.in);

    final String green = "\u001B[32m";
    final String reset = "\u001B[0m";
    final String red = "\u001B[31m";

    String user_name;
    String ran_word;
    String end_difficulty;
    String highscore_placement;

    char game_difficulty;
    int user_times_guessed;
    int leaderboard_type = 0;
    int fouten = 0;
    int i_ran_word = 0;
    boolean word_guessed = false;
    boolean on_leaderbord = false;

    textfiles files = new textfiles();
    
    void game_main() throws Exception{      // all constructors and main walktrough of game

                                                                    //textfiles files = new textfiles();
                                                            // files.write_highscore();
        game_config();
        gen_word();
        guess_word();
        report_highscore();

    }

    public void print_hangman (int fouten) {
  

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
    else System.out.print(red+"    wrong guesses: 10/10, \n               You failed!"+reset);

System.out.println("\n");
    }

    void game_config(){    // welcome the player and gets game config     Later: custom mode
        System.out.println("\nWelcome to Hangman\n");
        System.out.print("Your name: "); user_name = scanner.next();
        System.out.println("Please choose your difficulty:\nA Easy mode\nB Medium mode\nC Hard mode");
        System.out.print("Choose your difficulty: "); game_difficulty = scanner.next().charAt(0);
        System.out.println("\n");
        switch (game_difficulty) {
            case 'a': System.out.println(green+"Easy mode selected"+reset);
                i_ran_word = 0;
                leaderboard_type = 0;
                end_difficulty = "easy";
                break;
            case 'b': System.out.println(green+"Easy mode selected"+reset);
                i_ran_word = 30;
                leaderboard_type = 4;
                end_difficulty = "medium";
                break;
            case 'c': System.out.println(green+"Easy mode selected"+reset);
                i_ran_word = 60;
                leaderboard_type = 8;
                end_difficulty = "hard";
                break;
            default: System.out.println(red+"Thats not a valid choice. \nEasy mode selected"+reset);
                i_ran_word = 0;
                leaderboard_type = 0;
                end_difficulty = "easy";
                break;
        }
    }

    void gen_word() {                // generate a random word for the usser to guess            later: gui
        Random random = new Random();
        i_ran_word = random.nextInt(30)+i_ran_word;
       try {
            ran_word = textfiles.textfiles_main(i_ran_word);
        } catch (Exception e) {
            System.err.println("ln 61");
       }
    }

    void guess_word() {                   // makes the usser guess the word   

        ran_word = "a";

        int letter_counter = ran_word.length();
        char[] letters = ran_word.toCharArray();
        char[] result = ran_word.toCharArray();

    for (int s = 0; s < ran_word.length(); s++){
        result[s] = '_';
        System.out.print(result[s]+" ");
    }
         System.out.println("    config:   "+ran_word);
    while (letter_counter != 0) {
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
    if (result[i] != '_') {
        System.out.print(green+result[i]+reset+" ");
    }
    else System.out.print(result[i]+" ");
        }
        if (b == 0) {
            fouten++;
        }
        print_hangman(fouten);
    }
}

    void report_highscore() throws Exception {                // report back the stats and score    later: highscore system
        highscore_placement = files.write_highscore(leaderboard_type, user_times_guessed);
        on_leaderbord = files.user_on_leaderboard();


        if (on_leaderbord == true) {
            //System.out.println(green+"Well done "+user_name+" you reached the leaderbord!\nYour in "+highscore_placement+" place in "+end_difficulty+" difficulty."+reset);
            System.out.println(green+"Goodjob, "+user_name+"! \nThe word was: "+ran_word+". \nFinal score: "+user_times_guessed+" guesses. \nDifficulty: "+end_difficulty + ".\nYour position on the "+end_difficulty+" leaderboard: "+highscore_placement+reset);
        }
        else;


    


    }

}
