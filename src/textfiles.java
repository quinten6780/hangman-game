import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;

class textfiles{
    
    Scanner scanner = new Scanner(System.in);
    boolean on_leaderbord = false;
    
    

    public static String textfiles_main(int i_ran_word) throws IOException{
        final List <String> line = Files.readAllLines(Path.of("all-words.txt"));
        String ran_word = line.get(i_ran_word);
   return ran_word; 
    }


     String write_highscore(int leaderboard_type, int user_times_guessed) throws IOException{

        final FileWriter writer = new FileWriter("highscores.txt", true);
        final List <String> end = Files.readAllLines(Path.of("highscores.txt"));

        String highscore_placement = "you did not reach the leaderbord";
        

        
        int score1 = Integer.parseInt(end.get(leaderboard_type));
        int score2 = Integer.parseInt(end.get(leaderboard_type+1));
        int score3 = Integer.parseInt(end.get(leaderboard_type+2));


        if (user_times_guessed <= score1) {
            end.set(leaderboard_type, String.valueOf(user_times_guessed));
            highscore_placement = "1st";
            on_leaderbord = true;
        }
        else if (user_times_guessed <= score2) {
            end.set(leaderboard_type+1, String.valueOf(user_times_guessed));
            highscore_placement = "2th";
        }
        else if (user_times_guessed <= score3) {
            end.set(leaderboard_type+2, String.valueOf(user_times_guessed));
            highscore_placement = "3rd";
        }

        FileWriter nwriter = new FileWriter("highscores.txt");
        nwriter.close();
        for (String line : end) {
            writer.write(line+"\n");
        }
        writer.close();
        return highscore_placement;
    }

    public boolean user_on_leaderboard() {
        return on_leaderbord;
    }
}



    

