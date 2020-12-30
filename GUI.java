import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;


public class GUI extends JFrame{


    public static JFrame frame;
    private JPanel TopGamePanel;
    private JPanel CenterGamePanel;
    private JPanel RoundTypePanel;
    private JPanel QuestionPanel;
    private JPanel CategoryPanel;
    private JPanel BetPanel;
    private JPanel AnsewrsPanel1;
    private JPanel AnsewrsPanel2;
    private JPanel AnsewrsPanel3;
    private JPanel AnsewrsPanel4;
    private JButton StartButton;
    private JButton HighScores;
    private static JLabel TheRoundTypeLabel;
    private static JLabel TheQuestionLabel;
    private static JLabel TheCategoryLabel;
    private static JLabel TheUtilityLabel;
    private static JLabel TheAnswersLabel1;
    private static JLabel TheAnswersLabel2;
    private static JLabel TheAnswersLabel3;
    private static JLabel TheAnswersLabel4;

    public static char character;
    public static boolean flag = true;
    public static int CurrentRoundType;
    public static int GUIBetting;
    private static int NumberOfPlayers;

    private static Timer timer;
    public static int theTime;

    public GUI(){

        frame = new JFrame("BuzzQuizWorld!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(false);
        frame.setLayout(new BorderLayout());
        frame.setFocusable(true);


        TopGamePanel = new JPanel();
        TopGamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        TopGamePanel.setBackground(Color.orange);
        frame.add(TopGamePanel, BorderLayout.PAGE_START);


        StartButton = new JButton("Start");
        StartButton.setFocusable(false);
        StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] players = {"One", "Two"};

                int n = JOptionPane.showOptionDialog(frame,
                        "How many players will be playing?",
                        "Amount of players",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        players,
                        players[0]);

                if(n==0){
                    System.out.println(players[n] + " player will be playing");
                    NumberOfPlayers=1;

                }
                else if (n==1){
                    System.out.println(players[n] + " players will be playing");
                    NumberOfPlayers=2;
                }//can easily add more players!
            }

        });
        TopGamePanel.add(StartButton);


        HighScores = new JButton("Get The High Scores");
        HighScores.setFocusable(false);
        HighScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner myReader = null;
                try {
                    myReader = new Scanner(new File("Highscore.txt"));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                String currentline;
                String[] line;
                String[] temp = new String[3];
                int i=0;
                while ( myReader.hasNextLine()){
                    currentline = myReader.nextLine(); // line[3] has the number
                    line = currentline.split(" ");
                    temp[i] = line[3];
                    i++;

                }
                myReader.close();
                JOptionPane.showMessageDialog(frame, "Solo highscore is: " + temp[0] + "\n" +
                        "Player 1 Wins: " + temp[1] + "\n"+
                        "Player 2 Wins: " + temp[2]);
            }
        });
        TopGamePanel.add(HighScores);

        CenterGamePanel = new JPanel();
        CenterGamePanel.setLayout(new GridLayout(8,1));
        CenterGamePanel.setBackground(Color.DARK_GRAY);
        frame.add(CenterGamePanel,BorderLayout.CENTER);

        RoundTypePanel = new JPanel();
        RoundTypePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        RoundTypePanel.setBackground(Color.GRAY);
        CenterGamePanel.add(RoundTypePanel);

        TheRoundTypeLabel = new JLabel("Round Type : ");
        RoundTypePanel.add(TheRoundTypeLabel);

        CategoryPanel = new JPanel();
        CategoryPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        CategoryPanel.setBackground(Color.GRAY);
        CenterGamePanel.add(CategoryPanel);

        TheCategoryLabel = new JLabel("Category : ");
        CategoryPanel.add(TheCategoryLabel);

        QuestionPanel = new JPanel();
        QuestionPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        QuestionPanel.setBackground(Color.lightGray);
        CenterGamePanel.add(QuestionPanel);

        TheQuestionLabel = new JLabel("Question : ");
        QuestionPanel.add(TheQuestionLabel);

        BetPanel = new JPanel();
        BetPanel.setBackground(Color.lightGray);
        CenterGamePanel.add(BetPanel);

        TheUtilityLabel = new JLabel();
        BetPanel.add(TheUtilityLabel);

        AnsewrsPanel1 = new JPanel();
        AnsewrsPanel1.setLayout(new FlowLayout(FlowLayout.LEADING));
        AnsewrsPanel1.setBackground(Color.lightGray);
        CenterGamePanel.add(AnsewrsPanel1);

        TheAnswersLabel1 = new JLabel("TEST1");
        AnsewrsPanel1.add(TheAnswersLabel1);

        AnsewrsPanel2 = new JPanel();
        AnsewrsPanel2.setLayout(new FlowLayout(FlowLayout.LEADING));
        AnsewrsPanel2.setBackground(Color.lightGray);
        CenterGamePanel.add(AnsewrsPanel2);

        TheAnswersLabel2 = new JLabel("TEST2");
        AnsewrsPanel2.add(TheAnswersLabel2);

        AnsewrsPanel3 = new JPanel();
        AnsewrsPanel3.setLayout(new FlowLayout(FlowLayout.LEADING));
        AnsewrsPanel3.setBackground(Color.lightGray);
        CenterGamePanel.add(AnsewrsPanel3);

        TheAnswersLabel3 = new JLabel("TEST3");
        AnsewrsPanel3.add(TheAnswersLabel3);

        AnsewrsPanel4 = new JPanel();
        AnsewrsPanel4.setLayout(new FlowLayout(FlowLayout.LEADING));
        AnsewrsPanel4.setBackground(Color.lightGray);
        CenterGamePanel.add(AnsewrsPanel4);

        TheAnswersLabel4 = new JLabel("TEST4");
        AnsewrsPanel4.add(TheAnswersLabel4);




    }

    private void AmountOfPlayersButton(){

    }

    void startGUI(){
        frame.setVisible(true);
        Game NewGame = new Game();
        NewGame.GameStart();

    }

    public static void updateRound(String[] RoundTypes,int CurrentRound){
        CurrentRound=CurrentRound-1;
        CurrentRoundType=CurrentRound;
        TheRoundTypeLabel.setText("Round Type : " + RoundTypes[CurrentRound]);

        if(CurrentRoundType==0){//Correct Answer
            TheUtilityLabel.setText("Choose the correct answer!");
        }
        else if(CurrentRoundType==1){
            TheUtilityLabel.setText("Time to Bet!");
        }
        else if(CurrentRoundType==2){//Stopwatch
            TheUtilityLabel.setText("It's Stopwatch round so be FAST!");

        }

    }

    public static void updateBet(){
        if(CurrentRoundType==1){//THIS MEANS THE ROUND IS BETTING
            Object[] TheBet = {"250", "500", "750", "1000"};

            int n = JOptionPane.showOptionDialog(frame,
                    "How much do you want to Bet?",
                    "Betting Amount",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    TheBet,
                    TheBet[0]);

            if(n==0){
                GUIBetting=250;
            }
            else if(n==1){
                GUIBetting=500;
            }
            else if(n==2){
                GUIBetting=750;
            }
            else{
                GUIBetting=1000;
            }
        }

        if(CurrentRoundType==1){
            TheUtilityLabel.setText("The Betting is : " + GUIBetting);
        }

    }

    public static void updateQuestion(String[] Questions){
        TheCategoryLabel.setText("Category : " + Questions[0]);
        TheQuestionLabel.setText("Question : " + Questions[1]);
        TheAnswersLabel1.setText("Q : " + Questions[2]);
        TheAnswersLabel2.setText("W : " + Questions[3]);
        TheAnswersLabel3.setText("E : " + Questions[4]);
        TheAnswersLabel4.setText("R : " + Questions[5]);


    }

    public static char getChar() {

        flag = true;
        while(flag){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);
                    switch (e.getKeyChar()) {
                        case 'q':
                            character = 'q';
                            //System.out.println("Player 1 - Answer A");
                            flag = false;
                            break;
                        case 'w':
                            character = 'w';
                            //System.out.println("Player 1 - Answer B");
                            flag = false;
                            break;
                        case 'e':
                            character = 'e';
                            //System.out.println("Player 1 - Answer C");
                            flag = false;
                            break;
                        case 'r':
                            character = 'r';
                            //System.out.println("Player 1 - Answer D");
                            flag = false;
                            break;
                    }
                }

            });

        }
        //System.out.println(character);
        return character;
    }

    public static int numberOfPlayers(){
        return NumberOfPlayers;
    }

    public static int TheAmoundofBetting(){
        return GUIBetting;
    }

    public  static  void StopWatch(){
        TheUtilityLabel.setText("Starting time : 5 Seconds");

        timer = new Timer(100, new ActionListener() {
            private int count = 5000;


            @Override
            public void actionPerformed(ActionEvent e) {
                if (count <= 0) {
                    ((Timer) e.getSource()).stop();
                } else {
                    count -= 100;
                }
                TheUtilityLabel.setText(Integer.toString(count));
                theTime=count;
            }

        });
        timer.start();


    }

    //public static void StartTimer(){
    //timer.start();
    // }
    public static void StopTimer(){
        timer.stop();
        JOptionPane.showMessageDialog(frame,
                "The question has been answered!\n Ready for the next one?",
                "Inane warning",
                JOptionPane.WARNING_MESSAGE);
    }


}