import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.*;
import javax.swing.*;
import javax.swing.Timer;



class gui extends JFrame implements ActionListener {

    private final CountDownLatch configLatch = new CountDownLatch(3);
    private final AtomicReference<CountDownLatch> configLatch2 = new AtomicReference<>(new CountDownLatch(1));

    private hangman game;

    char ch;

    JTextField textfield = new JTextField(10);
    int enter_amount;
    String user_name;
    String end_language;
    char language;
    char game_difficulty;
    String end_difficulty;
    char user_guess;


    JLabel s_label2 = new JLabel("<html>Your name:<br><br> </html>", SwingConstants.CENTER);
    JLabel m_label4_1 = new JLabel("_ _ _ _ _", SwingConstants.CENTER);
    JLabel m_label1_1 = new JLabel("", SwingConstants.CENTER);
    JLabel m_label1_2 = new JLabel("", SwingConstants.CENTER);

    JLabel m_label2_1 = new JLabel("", SwingConstants.CENTER);

    JLabel e_label1_1 = new JLabel("", SwingConstants.CENTER);
    JLabel e_label1_2 = new JLabel("", SwingConstants.CENTER);
    JLabel e_label2_1 = new JLabel("Stats", SwingConstants.CENTER);
    JLabel e_label2_2 = new JLabel("Stats", SwingConstants.CENTER);



    void gui_main() {
        
        frame_1();
        frame_0();
        waitForConfig();
        set_frame_2();
        update_keyboard(new java.util.HashSet<>(), new java.util.HashSet<>());
    }

    void frame_1() {
        JPanel s_panel1 = new JPanel(new BorderLayout());
        s_panel1.setBackground(Color.BLUE);
        s_panel1.setPreferredSize(new Dimension(350,125));
        JLabel s_label1 = new JLabel("Welcome to Hangman", SwingConstants.CENTER);
        s_label1.setSize(100,30);
        s_label1.setVerticalAlignment(SwingConstants.CENTER);
        s_label1.setForeground(Color.white);
        s_label1.setFont(new Font("Open Sans", Font.PLAIN,27));
        s_panel1.add(s_label1, BorderLayout.CENTER);
        this.add(s_panel1, BorderLayout.NORTH);
        


        JPanel s_panel2 = new JPanel(new BorderLayout());
        s_panel2.setBackground(Color.BLUE);
        s_panel2.setPreferredSize(new Dimension(350,400));
        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
        s_label2.setForeground(Color.WHITE);
        s_label2.setVerticalAlignment(SwingConstants.CENTER);
        s_panel2.add(s_label2, BorderLayout.SOUTH);
        this.add(s_panel2, BorderLayout.CENTER);

        JPanel s_panel3 = new JPanel(new BorderLayout(0,0));
        s_panel3.setBackground(Color.CYAN);
        s_panel3.setPreferredSize(new Dimension(350,150));

        JPanel s_panel3_2 = new JPanel();
        s_panel3_2.setSize(350,500);
        s_panel3_2.setBackground(Color.BLUE);

        s_panel3_2.add(textfield);
        textfield.addActionListener(this);
        s_panel3.add(s_panel3_2, BorderLayout.CENTER);
        this.add(s_panel3, BorderLayout.SOUTH);

    }

    void frame_2() {

        JPanel m_panel1 = new JPanel(new BorderLayout());
        m_panel1.setBackground(Color.yellow);
        m_panel1.setPreferredSize(new Dimension(350,400));
        m_panel1.add(m_label1_1, BorderLayout.CENTER);
        m_label1_2.setFont(new Font("Arial", Font.PLAIN, 18));
        m_label1_2.setForeground(Color.RED);
        m_panel1.add(m_label1_2, BorderLayout.SOUTH);
        this.add(m_panel1, BorderLayout.NORTH);


        JPanel m_panel2 = new JPanel(new BorderLayout());
        m_panel2.setPreferredSize(new Dimension(350,150));
        m_panel2.setBackground(Color.orange);
        m_label2_1.setFont(new Font("Arial", Font.PLAIN, 16));
     
        m_panel2.add(m_label2_1, BorderLayout.CENTER);
        this.add(m_panel2, BorderLayout.CENTER);


        JPanel m_panel4 = new JPanel(new BorderLayout(0,0));
        JPanel m_panel4_1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        m_panel4_1.setBackground(Color.white);
        m_label4_1.setFont(new Font("Open Sans", Font.PLAIN,25));
        m_panel4_1.add(m_label4_1);
        
        m_panel4_1.setPreferredSize(new Dimension(350,50));
        
        JPanel m_panel4_2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        m_panel4_2.setPreferredSize(new Dimension(350,50));
        textfield.setColumns(12);
        m_panel4_2.add(textfield);
        
        m_panel4.setPreferredSize(new Dimension(350,100));
        m_panel4.setBackground(Color.red);
        m_panel4.add(m_panel4_1, BorderLayout.NORTH);
        m_panel4.add(m_panel4_2, BorderLayout.SOUTH);
        this.add(m_panel4, BorderLayout.SOUTH);

        game = new hangman(this);               
        game.game_config();
        game.gen_word();
        set_grid();
        
        set_hangman();

        SwingUtilities.invokeLater(() -> textfield.requestFocusInWindow());
        
    }

    void frame_3() {
        JPanel e_panel1 = new JPanel(new BorderLayout());
        e_panel1.setBackground(Color.darkGray);
        e_panel1.setPreferredSize(new Dimension(350, 400));
        e_label1_1.setFont(new Font("Arial", Font.PLAIN, 23));
        e_label1_1.setForeground(Color.white);
        e_label1_2.setFont(new Font("Arial", Font.PLAIN, 25));
        e_label1_2.setForeground(Color.white);
        e_panel1.add(e_label1_1, BorderLayout.CENTER);
        e_panel1.add(e_label1_2, BorderLayout.SOUTH);
        this.add(e_panel1, BorderLayout.NORTH);


        JPanel e_panel2 = new JPanel(new BorderLayout());
        e_panel2.setBackground(Color.green);
        e_panel2.setPreferredSize(new Dimension(350, 300));

        e_label2_1.setFont(new Font("Arial", Font.PLAIN, 25));
        set_end_message();
        e_label2_2.setFont(new Font("Arial", Font.PLAIN, 22));
        e_panel2.add(e_label2_1, BorderLayout.NORTH); // "STATS"
        e_panel2.add(e_label2_2, BorderLayout.CENTER); // all the stats
        this.add(e_panel2, BorderLayout.CENTER);
    }

    void frame_0() {
        this.setTitle("Hangman");
        this.pack();
        this.setMinimumSize(new Dimension(350,700));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout(0,0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        SwingUtilities.invokeLater(() -> textfield.requestFocusInWindow());
        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                SwingUtilities.invokeLater(() -> textfield.requestFocusInWindow());
            }
        });
    }



    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == textfield) {
        enter_amount++;
        switch (enter_amount) {
            case 1 -> {
                user_name = textfield.getText();
                textfield.setText("");
                System.out.println("CONFIG: username: "+user_name);
                s_label2.setText("<html><pre>Welcome "+user_name+"<br><br></pre></html>");
                javax.swing.Timer clear_timer = new javax.swing.Timer(2000, ev -> s_label2.setText("<html>Choose your language: <center>A English <br>B Dutch<br><br></center></html>"));
                clear_timer.setRepeats(false);
                clear_timer.start();
                configLatch.countDown();
            }
            case 2 -> {
                String input = textfield.getText().trim();
                language = input.charAt(0);
                switch (language) {
                    case 'a','A' -> {
                        end_language = ("English");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setText("<html><pre>"+end_language+" mode selected<br><br></pre></html>");
                }
                    case 'b','B' -> {
                        end_language = ("Dutch");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setText("<html><pre>"+end_language+" mode selected<br><br></pre></html>");
                }
                    default -> {
                        end_language = ("English ");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setText("<html><center>That's not a valid choice <br>"+end_language+" mode selected<br><br></center></html>");
                        s_label2.setForeground(Color.white);
                }
                }
                textfield.setText("");
                System.out.println("CONFIG: language: "+language);
                Timer clear_timer = new javax.swing.Timer(2000, ev -> s_label2.setText("<html>A Easy mode<br>B Medium mode<br>C Hard mode</html>"));
                clear_timer.setRepeats(false);
                clear_timer.start();
                configLatch.countDown();
            }
            case 3 -> {
                String input = textfield.getText().trim();
                game_difficulty= input.charAt(0);
                switch (game_difficulty) {
                    case 'a','A' -> {
                        end_difficulty = ("Easy");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setText("<html><pre>"+end_difficulty+" mode selected<br><br></pre></html>");
                }
                    case 'b','B' -> {
                        end_difficulty = ("Medium");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setText("<html><pre>"+end_difficulty+" mode selected<br><br></pre></html>");
                }
                    case 'c','C' -> {
                        end_difficulty = ("Hard");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setText("<html><pre>"+end_difficulty+" mode selected<br><br></pre></html>");
                }
                    default -> {
                        end_difficulty = ("Easy ");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setForeground(Color.white);
                        s_label2.setText("<html><center>That's not a valid choice <br>"+end_difficulty+" mode selected<br><br</center></html>");
                }
                }
                textfield.setText("");
                SwingUtilities.invokeLater(() -> getRootPane().requestFocusInWindow());
                System.out.println("CONFIG: game_difficulty: "+game_difficulty);
                Timer clear_timer = new javax.swing.Timer(2000, ev -> configLatch.countDown());
                clear_timer.setRepeats(false);
                clear_timer.start();
            }
            case 4 -> {
                String input = textfield.getText().trim();
                if (input == null) {
                    input = "a";
                }
                user_guess = input.charAt(0);
                
                textfield.setText("");
                configLatch2.get().countDown();
                System.out.println(".(3)");
                new Thread(() -> game.guess_word(user_guess)).start();
                System.out.println(".(4)");
            }
            case 5 -> {
                String input = textfield.getText().trim();
                user_guess = input.charAt(0);
                textfield.setText("");
                configLatch2.get().countDown();
                SwingUtilities.invokeLater(() -> textfield.requestFocusInWindow());
                enter_amount--;   
            }
        }
    }
}



    char get_language() {
        return language;
    }
    char get_game_difficulty() {
        return game_difficulty;
    }
    char get_user_guess() {
        return user_guess;
    }
    String get_user_name() {
        return user_name;
    }

    void set_grid() {
        String ran_word = game.sss();
        String basic_lgrid = "";
        for(int n = 0; n < ran_word.length(); n++) {
            basic_lgrid = basic_lgrid+"_ ";
        }
        m_label4_1.setText(basic_lgrid);
    }
    void update_grid() {
        String new_lgrid = game.sss();
        m_label4_1.setText(new_lgrid); 
    }

    void update_keyboard(Set<Character> correct, Set<Character> incorrect) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><div style='text-align:center; font-family:Arial; font-size:17px;'>");
        
        String rows[] = {"Q W E R T Y U I O P", "A S D F G H J K L", "Z X C V B N M"};
        for (String row : rows) {
            for (int i = 0; i < row.length(); i++) {
                ch = row.charAt(i);
                String color = "#000000";
                if (correct.contains(Character.toLowerCase(ch)) || correct.contains(Character.toUpperCase(ch))) color = "#00ff00";
                if (incorrect.contains(Character.toLowerCase(ch)) || incorrect.contains(Character.toUpperCase(ch))) color = "#ff2727";
                sb.append("<span style='color:").append(color).append("; margin:0 6px;'>")
                .append(ch)
                .append("</span>");
        }
        sb.append("<br>");
    }
    sb.append("</div></html>");

    String html = sb.toString();
    SwingUtilities.invokeLater(() -> m_label2_1.setText(html));
}

    void set_hangman() {
        int fouten = game.get_fouten();
        switch (fouten) {
            case 0 -> {m_label1_1.setText("<html>|<br>|<br>|<br>|<br>|<br>|<br>+============</html>"); }     
            case 1 -> {m_label1_1.setText("<html><pre>|<br>|   <br>|   <br>|   <br>|   <br>|  /|\\<br>+============</pre></html>"); }
            case 2 -> {m_label1_1.setText("<html><pre>|<br>|   |<br>|   |<br>|   |<br>|   |<br>|  /|\\<br>+============</pre></html>"); }
            case 3 -> {m_label1_1.setText("<html><pre>|    ____<br>|   |/<br>|   |<br>|   |<br>|   |<br>|  /|\\<br>+============</pre></html>"); }
            case 4 -> {m_label1_1.setText("<html><pre>|    ____<br>|   |/  |<br>|   |<br>|   |<br>|   |<br>|  /|\\<br>+============</pre></html>"); }
            case 5 -> {m_label1_1.setText("<html><pre>|    ____<br>|   |/  |<br>|   |   0<br>|   |<br>|   |<br>|  /|\\<br>+============</pre></html>"); }
            case 6 -> {m_label1_1.setText("<html><pre>|    ____<br>|   |/  |<br>|   |   0<br>|   |   |<br>|   |<br>|  /|\\<br>+============</pre></html>"); }
            case 7 -> {m_label1_1.setText("<html><pre>|    ____<br>|   |/  |<br>|   |   0<br>|   |  /|<br>|   |<br>|  /|\\<br>+============</pre></html>"); }
            case 8 -> {m_label1_1.setText("<html><pre>|    ____<br>|   |/  |<br>|   |   0<br>|   |  /|\\<br>|   |<br>|  /|\\<br>+============</pre></html>"); }
            case 9 -> {m_label1_1.setText("<html><pre>|    ____<br>|   |/  |<br>|   |   0<br>|   |  /|\\<br>|   |  /<br>|  /|\\<br>+============</pre></html>"); }
            case 10 -> {m_label1_1.setText("<html><pre>|    ____<br>|   |/  |<br>|   |   0<br>|   |  /|\\<br>|   |  / \\<br>|  /|\\<br>+============</pre></html>"); }
            default ->  {}

        }
        m_label1_1.setFont(new Font("Monospaced", Font.PLAIN,25));
    }

    void set_wrong_counter(int fouten) {
        m_label1_2.setText("<html><pre>Wrong guesses "+fouten+"/10<br>   </pre></html>");
        if (fouten == 10) {
            javax.swing.Timer clear_timer = new javax.swing.Timer(2000, ev -> set_frame_3());
            SwingUtilities.invokeLater(() -> getRootPane().requestFocusInWindow());
            clear_timer.setRepeats(false);
            clear_timer.start();
        }
    }

    void aaa() {
        configLatch2.set(new CountDownLatch(1));
    }

    void set_end_message() {
        int letter_counter = game.get_letter_counter();
        System.out.println("conf lettercounter:  "+letter_counter);
        
        switch (letter_counter) {
            case -1 -> {e_label1_1.setText("<html><center><br>Goodjob "+user_name+"!<br><br> Your 1st on the leaderboard</center></html>");}// 1e plaats
            case -2 -> {e_label1_1.setText("<html><center><br>Goodjob "+user_name+"!<br><br> Your 2nd on the leaderboard</center></html>");}// 2e plaats
            case -3 -> {e_label1_1.setText("<html><center><br>Goodjob "+user_name+"!<br><br> Your 3rd on the leaderboard</center></html>");}// 3e plaats
            case -4 -> {e_label1_1.setText("<html><center><br>Sorry "+user_name+"!<br><br> Your not on the leaderboard</center></html>");}// niet op leaderboard maar woord geraden
            case -5 -> {e_label1_1.setText("<html><center><br>Noob!<br><br> You didn't guess the word</center></html>");}
        }
    }
    void set_leaderboard(String leaderboard) {
        e_label1_2.setText(leaderboard);
    }
    void set_stats_page(String ran_word, int user_times_guessed, int fouten) {
        e_label2_2.setText("<html>" +
                            "The Word: "+ran_word+ 
                            "<br>Language: "+end_language+
                            "<br>Difficulty: "+end_difficulty+
                            "<br>Times guessed: "+user_times_guessed+
                            "<br>Wrong guesses: "+fouten+

                            "<br><br><br></html>"
        );
    }

    public void waitForConfig() {
    try {
        configLatch.await();
    }   catch (InterruptedException ex) {
    Thread.currentThread().interrupt();
    }
    }
    public void waitForConfig3() {
    try {
        configLatch2.get().await();
    }   catch (InterruptedException ex) {
    Thread.currentThread().interrupt();
    }
    }

    public void set_frame_2() {
        SwingUtilities.invokeLater(()->{
            getContentPane().removeAll();
            frame_2();
            revalidate();
            repaint();
        });
    }
    public void set_frame_3() {
        SwingUtilities.invokeLater(()->{
            getContentPane().removeAll();
            frame_3();
            revalidate();
            repaint();
        });
    }
}
