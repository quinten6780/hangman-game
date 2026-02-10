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
     public static String textfiles_main(int i_ran_word) throws IOException{
        final List <String> line = Files.readAllLines(Path.of("all-words.txt"));
        //final FileWriter writer = new FileWriter("guesses.txt");
       // final List <String> end = Files.readAllLines(Path.of("guesses.txt"));
        String ran_word = line.get(i_ran_word);
   return ran_word;     

    }
}



    

