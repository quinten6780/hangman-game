import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class hangman {
    

    Set<Character> correctGuesses = ConcurrentHashMap.newKeySet();
    Set<Character> incorrectGuesses   = ConcurrentHashMap.newKeySet();

    String user_name;
    String end_difficulty;
    String highscore_placement;
    String end_language;
    String pp_pp = user_name+end_difficulty+highscore_placement+end_language;

    char game_difficulty;
    char language;
    int user_times_guessed;
    int letter_counter;
    int leaderboard_type = 0;
    int fouten = 0;
    int r_woorden;


    int woorden_easy_eng = 30;
    int woorden_medium_eng = 30;
    int woorden_hard_eng = 30;
    int woorden_easy_dutch = 30;
    int woorden_medium_dutch = 30;
    int woorden_hard_dutch = 30;
    int i_difficulty;


    textfiles files = new textfiles();
    gui gui;

    String ran_word;
    String char_result;
    
    void game_main() throws Exception {      // all constructors and main walktrough of game
        gui.gui_main();
        gui.waitForConfig();
    }

    void game_config(){    // welcome the player and gets game config     Later: custom mode       language
        language = gui.get_language();
        game_difficulty = gui.get_game_difficulty();
   
        switch (language) {
            case 'a','A'-> {
                System.out.println("English mode selected    "+pp_pp);
                end_language = "english";
                switch (game_difficulty) {
                    case 'a','A' -> {
                        System.out.println("Easy mode selected");
                        i_difficulty = woorden_easy_eng;
                        leaderboard_type = 0;
                        end_difficulty = "easy";
                        r_woorden = 0;
                }
                    case 'b','B' -> {
                        System.out.println("Medium mode selected");
                        i_difficulty = woorden_medium_eng;
                        leaderboard_type = 4;
                        end_difficulty = "medium";
                        r_woorden = woorden_easy_eng;
                }
                    case 'c','C' -> {
                        System.out.println("Hard mode selected");
                        i_difficulty = woorden_hard_eng;
                        leaderboard_type = 8;
                        end_difficulty = "hard";
                        r_woorden = woorden_easy_eng + woorden_medium_eng;
                }
                    default -> {
                        System.out.println("Thats not a valid choice. \nEasy mode selected");
                        i_difficulty = woorden_easy_eng;
                        game_difficulty = 'a';
                        leaderboard_type = 0;
                        end_difficulty = "easy";
                        r_woorden = 0;
                }
                }
            }
            case 'b','B' -> {
                System.out.println("Dutch mode selected");
                end_language = "dutch";
                switch (game_difficulty) {
                    case 'a','A' -> {
                        System.out.println("Easy mode selected");
                        i_difficulty = woorden_easy_eng;
                        leaderboard_type = 0;
                        end_difficulty = "easy";
                        r_woorden = woorden_easy_eng + woorden_medium_eng + woorden_hard_eng + 1;
                }
                    case 'b','B' -> {
                        System.out.println("Medium mode selected");
                        i_difficulty = woorden_medium_eng;
                        leaderboard_type = 4;
                        end_difficulty = "medium";
                        r_woorden = woorden_easy_eng + woorden_medium_eng + woorden_hard_eng +1+ woorden_easy_dutch;
                }
                    case 'c','C' -> {
                        System.out.println("Hard mode selected");
                        i_difficulty = woorden_hard_dutch;
                        leaderboard_type = 8;
                        end_difficulty = "hard";
                        r_woorden = woorden_easy_eng + woorden_medium_eng + woorden_hard_eng +1+ woorden_easy_dutch + woorden_medium_dutch;
                }
                    default -> {
                        System.out.println("Thats not a valid choice. \nEasy mode selected");
                        i_difficulty = woorden_easy_dutch;
                        game_difficulty = 'a';
                        leaderboard_type = 0;
                        end_difficulty = "easy";
                        r_woorden = woorden_easy_eng + woorden_medium_eng + woorden_hard_eng +1;
                }
                }
            }
            default -> {
                System.out.println("Thats not a valid choice. \nEnglish mode selected");
                language = 'a';
                end_language = "english";
                switch (game_difficulty) {
                    case 'a' -> {
                        System.out.println("Easy mode selected");
                        i_difficulty = woorden_easy_eng;
                        leaderboard_type = 0;
                        end_difficulty = "easy";
                        r_woorden = 0;
                }
                    case 'b' -> {
                        System.out.println("Medium mode selected");
                        i_difficulty = woorden_medium_eng;
                        leaderboard_type = 4;
                        end_difficulty = "medium";
                        r_woorden = woorden_easy_eng;
                }
                    case 'c' -> {
                        System.out.println("Hard mode selected");
                        i_difficulty = woorden_hard_eng;
                        leaderboard_type = 8;
                        end_difficulty = "hard";
                        r_woorden = woorden_easy_eng + woorden_medium_eng;
                }
                    default -> {
                        System.out.println("Thats not a valid choice. \nEasy mode selected");
                        i_difficulty = woorden_easy_eng;
                        game_difficulty = 'a';
                        leaderboard_type = 0;
                        end_difficulty = "easy";
                        r_woorden = 0;
                }
                }
            }
        }
    }

    void gen_word() {                // generate a random word for the usser to guess            later: gui
        Random random = new Random();
        r_woorden = random.nextInt(i_difficulty) + r_woorden;
       try {
             ran_word = textfiles.get_word(r_woorden);
        } catch (IOException e) {
            System.err.println("err: ln 182");
       }
       System.out.println("    config:   "+ran_word);
       char_result = ran_word;
       correctGuesses.clear();
       incorrectGuesses.clear();
    gui.update_keyboard(correctGuesses, incorrectGuesses);
    }

    void guess_word(char user_guess) {                   // makes the usser guess the word
           
        letter_counter = ran_word.length();
        char[] letters = ran_word.toCharArray();
        char[] result = ran_word.toCharArray();
        String guessed_letters = "";

    for (int s = 0; s < ran_word.length(); s++){
        result[s] = '_';
        System.out.print(result[s]+" ");
    }

    

     while (letter_counter > 0 ) {
        System.out.println("    your already guessed: "+guessed_letters);
        
        gui.waitForConfig3();
        user_guess = gui.get_user_guess();

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
            guessed_letters = guessed_letters + " " +user_guess+",";
            incorrectGuesses.add(Character.toLowerCase(user_guess));
        } else {
            correctGuesses.add(Character.toLowerCase(user_guess));
        }
        gui.update_keyboard(correctGuesses, incorrectGuesses);
    for (int w = 0; w < ran_word.length(); w++) {
        if (result[w] != '_') {
        System.out.print(result[w]+" ");
    }
    else System.out.print(result[w]+" ");
    }

    char_result = "";
    for (int n = 0; n < ran_word.length(); n++) {
        char_result = char_result+result[n]+" ";
    }
    
    gui.update_grid();
    gui.set_hangman();
    gui.aaa();
    gui.set_wrong_counter(fouten);
    if (fouten == 10) {
        try {
            report_highscore();
        } catch (Exception e) {
    }
    }
}
try {
            report_highscore();
        } catch (Exception e) {
    }
}
    String sss() {
        return char_result;
    }
    


    void report_highscore() throws Exception {                // report back the stats and score    later: highscore system
        user_name = gui.get_user_name();
        highscore_placement = files.write_highscore(leaderboard_type, fouten, user_name);
        letter_counter = files.user_on_leaderboard();
        String leaderboard = files.get_leaderboard();
        gui.set_leaderboard(leaderboard);
        gui.set_stats_page(ran_word, user_times_guessed, fouten);
        gui.set_frame_3();
        
    }

    int get_letter_counter() {
        return letter_counter;
    }
    public hangman(gui gui) {
        this.gui = gui;
    }
    int get_fouten() {
        return fouten;
    }
}