import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

class textfiles{
    
    Scanner scanner = new Scanner(System.in);
    boolean on_leaderbord = false;

    int woorden_difficulty;
    int r_language;
    

    public static String get_word(int r_woorden) throws IOException{
        final List <String> line = Files.readAllLines(Path.of("all-words.txt"));
        String ran_word = line.get(r_woorden);
    return ran_word; 
    }


    String write_highscore(int leaderboard_type, int user_times_guessed) throws IOException{

        final FileWriter writer = new FileWriter("highscores.txt", true);
        final List <String> end = Files.readAllLines(Path.of("highscores.txt"));

        String highscore_placement = " ";    

        
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
            on_leaderbord = true;
        }
        else if (user_times_guessed <= score3) {
            end.set(leaderboard_type+2, String.valueOf(user_times_guessed));
            highscore_placement = "3rd";
            on_leaderbord = true;
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



    

