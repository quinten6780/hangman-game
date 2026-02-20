import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.*;
import javax.swing.*;






class gui extends JFrame implements ActionListener {

    private final CountDownLatch configLatch = new CountDownLatch(3);
    private final AtomicReference<CountDownLatch> configLatch2 = new AtomicReference<>(new CountDownLatch(1));

    private hangman game;


    JTextField textfield = new JTextField(10);
    int enter_amount;
    String user_name;
    String end_language;
    char language;
    char game_difficulty;
    String end_difficulty;
    char user_guess;
   // String ran_word;



    

    

    

    JLabel s_label2 = new JLabel("Your name:", SwingConstants.CENTER);

    JLabel m_label4_1 = new JLabel("_ _ _ _ _", SwingConstants.CENTER);


    void gui_main() {
        
        frame_1();
        frame_2();
        frame_0();
        
    }

    void frame_1() {
        JPanel s_panel1 = new JPanel(new BorderLayout());
        s_panel1.setBackground(Color.BLUE);
        s_panel1.setPreferredSize(new Dimension(350,100));
        JLabel s_label1 = new JLabel("Welcome to Hangman", SwingConstants.CENTER);
        s_label1.setSize(100,30);
        s_label1.setVerticalAlignment(SwingConstants.CENTER);
        s_label1.setForeground(Color.white);
        s_label1.setFont(new Font("Open Sans", Font.PLAIN,25));
        s_panel1.add(s_label1, BorderLayout.CENTER);
        this.add(s_panel1, BorderLayout.NORTH);



        JPanel s_panel2 = new JPanel(new BorderLayout());
        s_panel2.setBackground(Color.BLUE);
        s_panel2.setPreferredSize(new Dimension(350,400));
        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
        s_label2.setForeground(Color.WHITE);
        s_label2.setVerticalAlignment(SwingConstants.CENTER);
        s_panel2.add(s_label2, BorderLayout.SOUTH);
        this.add(s_panel2, BorderLayout.CENTER);            // HEEREEREREEEE    making panels 2-3 right size


        JPanel s_panel3 = new JPanel(new BorderLayout(0,0));
        s_panel3.setBackground(Color.CYAN);
        s_panel3.setPreferredSize(new Dimension(350,100));

        JPanel s_panel3_2 = new JPanel();
        s_panel3_2.setSize(350,300);
        s_panel3_2.setBackground(Color.BLUE);

        s_panel3_2.add(textfield);
        textfield.addActionListener(this);
        s_panel3.add(s_panel3_2, BorderLayout.CENTER);
        this.add(s_panel3, BorderLayout.SOUTH);

    }

    void frame_2() {

        

        JPanel m_panel1 = new JPanel(new BorderLayout());
        m_panel1.setBackground(Color.BLUE);
        m_panel1.setPreferredSize(new Dimension(350,400));
        this.add(m_panel1, BorderLayout.NORTH);




        JPanel m_panel2 = new JPanel(new BorderLayout());
        m_panel2.setPreferredSize(new Dimension(350,150));
        m_panel2.setBackground(Color.gray);
        this.add(m_panel2, BorderLayout.CENTER);


        JPanel m_panel4 = new JPanel(new BorderLayout(0,0));
        JPanel m_panel4_1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        m_panel4_1.setBackground(Color.PINK);
        m_label4_1.setFont(new Font("Open Sans", Font.PLAIN,25));
        m_panel4_1.add(m_label4_1);
        
        m_panel4_1.setPreferredSize(new Dimension(350,50));
        
        JPanel m_panel4_2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        m_panel4_2.setPreferredSize(new Dimension(350,50));
        textfield.setColumns(12);
        m_panel4_2.add(textfield);
        
        m_panel4.setPreferredSize(new Dimension(350,100));
        m_panel4.setBackground(Color.orange);
        m_panel4.add(m_panel4_1, BorderLayout.NORTH);
        m_panel4.add(m_panel4_2, BorderLayout.SOUTH);
        this.add(m_panel4, BorderLayout.SOUTH);
    }

    void frame_0() {
        this.setTitle("Hangman");
        this.pack();
        this.setMinimumSize(new Dimension(350,400));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout(0,0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == textfield) {
        //clear_frame();
    

        enter_amount++;
        switch (enter_amount) {
            case 1 -> {
                user_name = textfield.getText();
                textfield.setText("");
                System.out.println("CONFIG: username: "+user_name);
                s_label2.setText("Welcome "+user_name);
                javax.swing.Timer clear_timer = new javax.swing.Timer(2000, ev -> s_label2.setText("<html>Choose your language: <br>    A English <br>  B Dutch</html>"));
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
                        s_label2.setText(end_language+" mode selected");
                }
                    case 'b','B' -> {
                        end_language = ("Dutch");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setText(end_language+" mode selected");
                }
                    default -> {
                        end_language = ("English ");
                        s_label2.setFont(new Font("Open Sans", Font.BOLD,20));
                        s_label2.setForeground(Color.red);
                        s_label2.setText("<html>That's not a valid choice <br>"+end_language+" mode selected</html>");
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
                        s_label2.setText(end_difficulty+" mode selected");
                }
                    case 'b','B' -> {
                        end_difficulty = ("Medium");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setText(end_difficulty+" mode selected");
                }
                    case 'c','C' -> {
                        end_difficulty = ("Hard");
                        s_label2.setFont(new Font("Open Sans", Font.PLAIN,25));
                        s_label2.setText(end_difficulty+" mode selected");
                }
                    default -> {
                        end_language = ("English ");
                        s_label2.setFont(new Font("Open Sans", Font.BOLD,20));
                        s_label2.setForeground(Color.red);
                        s_label2.setText("<html>That's not a valid choice <br>"+end_difficulty+" mode selected</html>");
                }
                }
                textfield.setText("");
                System.out.println("CONFIG: game_difficulty: "+game_difficulty);
                Timer clear_timer = new javax.swing.Timer(2000, ev -> frame_2());
                clear_timer.setRepeats(false);
                clear_timer.start();
                configLatch.countDown();
            }
            case 4 -> {
                String input = textfield.getText().trim();
                user_guess = input.charAt(0);
                textfield.setText("");
                game = new hangman(this);

                
                game.game_config();
                game.gen_word();
                
                new Thread(() -> game.guess_word(user_guess)).start();

             //   configLatch2.get().countDown();
                
//                game.guess_word(user_guess);
                configLatch2.set(new CountDownLatch(1));
                
            }
            case 5 -> {
                
                String input = textfield.getText().trim();
                user_guess = input.charAt(0);
                textfield.setText("");
                configLatch2.get().countDown();
                enter_amount--;


                //hangman game = new hangman(this);
                
                
                
             //   game.guess_word(user_guess);
                
                configLatch2.set(new CountDownLatch(1));
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

    public void clear_frame() {
        SwingUtilities.invokeLater(()->{
            getContentPane().removeAll();
            frame_2();
            revalidate();
            repaint();
        });
    }
    
}
