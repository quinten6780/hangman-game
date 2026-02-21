import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class textfiles{
    
    int letter_counter;
    String highscore_placement;
    String leaderboard;
    

    public static String get_word(int r_woorden) throws IOException{
        final List <String> line = Files.readAllLines(Path.of("all-words.txt"));
        String ran_word = line.get(r_woorden);
    return ran_word; 
    }


    String write_highscore(int leaderboard_type, int fouten, String user_name) throws IOException{

        
        try (FileWriter writer = new FileWriter("highscores.txt", true)) {
            final List <String> end = Files.readAllLines(Path.of("highscores.txt"));
            highscore_placement = " ";
            int score1 = Integer.parseInt(end.get(leaderboard_type));
            int score2 = Integer.parseInt(end.get(leaderboard_type+1));
            int score3 = Integer.parseInt(end.get(leaderboard_type+2));
            if (fouten == 10) {
                letter_counter = -5;
            } else if (fouten <= score1) {
                System.out.println(score2);
                System.out.println(score1);
                end.set(leaderboard_type+2, String.valueOf(score2));
                end.set(leaderboard_type+1, String.valueOf(score1));
                end.set(leaderboard_type, String.valueOf(fouten));
                end.set(leaderboard_type+14, end.get(leaderboard_type+13));
                end.set(leaderboard_type+13, end.get(leaderboard_type+12));
                end.set(leaderboard_type+12, user_name);
                letter_counter = -1;
            }
            else if (fouten <= score2) {
                end.set(leaderboard_type+2, String.valueOf(score2));
                end.set(leaderboard_type+1, String.valueOf(fouten));
                end.set(leaderboard_type+14, end.get(leaderboard_type+13));
                end.set(leaderboard_type+13, user_name);
                letter_counter = -2;
            }
            else if (fouten <= score3) {
                end.set(leaderboard_type+2, String.valueOf(fouten));
                end.set(leaderboard_type+14, user_name);
                letter_counter = -3;
            }
            else letter_counter = -4;
            FileWriter nwriter = new FileWriter("highscores.txt");
            nwriter.close();
            for (String line : end) {
                writer.write(line+"\n");
            }
            leaderboard = "<html>"+
                        "1st  "+end.get(leaderboard_type+12)+" with "+end.get(leaderboard_type)+" guesses"+
                        "<br>2nd  "+end.get(leaderboard_type+13)+" with "+end.get(leaderboard_type+1)+" guesses"+
                        "<br>3rd  "+end.get(leaderboard_type+14)+" with "+end.get(leaderboard_type+2)+" guesses"+
                        "<br><br></html>";
        }
        

        return highscore_placement;
    }

    String get_leaderboard() {
        System.out.println("lb:  "+leaderboard);
        return leaderboard;
    }

    public int user_on_leaderboard() {
        return letter_counter;
    }
}



    

