import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import static java.lang.Thread.sleep;


public class GUI extends JFrame{


    public static JFrame frame;
    private JPanel ImagePanel;
    private JPanel TopGamePanel;
    private static JPanel CenterGamePanel;
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
    private static JLabel Image;

    public static char character;
    public static boolean flag = true;
    public static int CurrentRoundType;
    public static int GUIBetting;
    private static int NumberOfPlayers;

    private static Timer timer;
    public static int theTime1=0;
    public static int theTime2=0;
    private static JLabel imageLabel;

    /***
     * This is the Graphical User Interface
     * GUI makes sure to create each needed component and place it in the correct position before the actual game starts
     * Later down the Game it is updated (when needed) by separate functions and methods
     */
    public GUI(){

        frame = new JFrame("BuzzQuizWorld!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1500,450);
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
        CenterGamePanel.setLayout(new GridLayout(8,2));//TODO this ofr testing
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

        TheAnswersLabel1 = new JLabel("");
        AnsewrsPanel1.add(TheAnswersLabel1);

        AnsewrsPanel2 = new JPanel();
        AnsewrsPanel2.setLayout(new FlowLayout(FlowLayout.LEADING));
        AnsewrsPanel2.setBackground(Color.lightGray);
        CenterGamePanel.add(AnsewrsPanel2);

        TheAnswersLabel2 = new JLabel("");
        AnsewrsPanel2.add(TheAnswersLabel2);

        AnsewrsPanel3 = new JPanel();
        AnsewrsPanel3.setLayout(new FlowLayout(FlowLayout.LEADING));
        AnsewrsPanel3.setBackground(Color.lightGray);
        CenterGamePanel.add(AnsewrsPanel3);

        TheAnswersLabel3 = new JLabel("");
        AnsewrsPanel3.add(TheAnswersLabel3);

        AnsewrsPanel4 = new JPanel();
        AnsewrsPanel4.setLayout(new FlowLayout(FlowLayout.LEADING));
        AnsewrsPanel4.setBackground(Color.lightGray);
        CenterGamePanel.add(AnsewrsPanel4);

        TheAnswersLabel4 = new JLabel("");
        AnsewrsPanel4.add(TheAnswersLabel4);

        imageLabel = new JLabel();


    }

    private void AmountOfPlayersButton(){

    }

    /***
     * startGUI is basically responsible for making the entire GUI visible to the player(s) and starting the actual game
     * by creating a Game object and calling the GameStart() method which as the name suggests, starts the game.
     */
    void startGUI(){
        frame.setVisible(true);
        Game NewGame = new Game();
        NewGame.GameStart();

    }

    /***
     * updateRound is responsible for updating the GUI labels that inform the player what type of round he will be playing
     * for the next 4 questions
     * @param RoundTypes an array with all the available Round Types
     * @param CurrentRound an int which shows the current round
     */
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
        else if(CurrentRoundType==3){//Stopwatch
            TheUtilityLabel.setText("It's Thermometer round!");

        }
        else if(CurrentRoundType==4){//Stopwatch
            TheUtilityLabel.setText("It's Fast Answer round!");

        }

    }

    /***
     * updateBet is used (ONLY in the case the current round type is Betting) to show the player a pop-up window from
     * which he is able to choose one of the four available bets.
     * !! The player cannot continue playing unless he/she chooses a bet !!
     */
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

    /***
     * updateQuestion updates the respective labels of GUI that have to do with the Category of the current Question,
     * the actual Question and the four possible answers the player(s) has/have to choose from.
     * The corresponding keys for each answer are also updated on the left-hand side of them
     * @param Questions
     */
    public static void updateQuestion(String[] Questions){
        TheCategoryLabel.setText("Category : " + Questions[0]);
        TheQuestionLabel.setText("Question : " + Questions[1]);
        if(Round.k==0) {
            TheAnswersLabel1.setText("Q : " + Questions[2]);
            TheAnswersLabel2.setText("W : " + Questions[3]);
            TheAnswersLabel3.setText("E : " + Questions[4]);
            TheAnswersLabel4.setText("R : " + Questions[5]);
        }
        else if (Round.k==1){
            TheAnswersLabel1.setText("Q / U : " + Questions[2]);
            TheAnswersLabel2.setText("W / I : " + Questions[3]);
            TheAnswersLabel3.setText("E / O : " + Questions[4]);
            TheAnswersLabel4.setText("R / P : " + Questions[5]);
        }


    }

    /***
     * getChar is a keyListener which depending on the amount of players or which player can play, accepts each time a
     * different set of keys.
     * @return character, which is the actual key that has just been pressed.
     */
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
                    if(Round.k==0) {
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
                    else if(Round.k==1){
                        switch (e.getKeyChar()) {
                            case 'u':
                                character = 'u';
                                //System.out.println("Player 1 - Answer A");
                                flag = false;
                                break;
                            case 'i':
                                character = 'i';
                                //System.out.println("Player 1 - Answer B");
                                flag = false;
                                break;
                            case 'o':
                                character = 'o';
                                //System.out.println("Player 1 - Answer C");
                                flag = false;
                                break;
                            case 'p':
                                character = 'p';
                                //System.out.println("Player 1 - Answer D");
                                flag = false;
                                break;
                        }
                    }
                    else if(Round.k==3){//It is impossible for k to become 3 in the entire program so k=3 is used to mark the fastest key pressed
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
                            case 'u':
                                character = 'u';
                                //System.out.println("Player 1 - Answer A");
                                flag = false;
                                break;
                            case 'i':
                                character = 'i';
                                //System.out.println("Player 1 - Answer B");
                                flag = false;
                                break;
                            case 'o':
                                character = 'o';
                                //System.out.println("Player 1 - Answer C");
                                flag = false;
                                break;
                            case 'p':
                                character = 'p';
                                //System.out.println("Player 1 - Answer D");
                                flag = false;
                                break;
                        }
                    }
                }

            });

        }
        //System.out.println(character);
        return character;
    }

    /***
     * Simple getter for the NumberOfPlayers variable
     * @return NumberOfPlayers
     */
    public static int numberOfPlayers(){
        return NumberOfPlayers;
    }

    /***
     * Simple getter for the TheAmoundofBetting variable
     * @return GUIBetting
     */
    public static int TheAmoundofBetting(){
        return GUIBetting;
    }

    /***
     * StopWatch implements a sort of countdown starting from 5 seconds or 5000 mSec
     * On each countdown the current remaining time is saved on both theTime1 and theTime2 which represent the remaining
     * time for player1 and player2 respectively.
     */
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
                theTime1=count;
                theTime2=count;
            }

        });
        timer.start();


    }

    /***
     * Stops the timer.
     * In the case of one Player the timer stops after he/she answers the question.
     * In the case of two Players the timer stops after both the players have answered the question.
     * After the timer has been stopped a pop-up message appears that lets players get ready for the next question.
     */
    public static void StopTimer(){
        timer.stop();
        JOptionPane.showMessageDialog(frame,
                "The question has been answered!\n Ready for the next one?",
                "Inane warning",
                JOptionPane.WARNING_MESSAGE);
    }

    /***
     * loadImage is responsible for loading the correct image on the right-hand side of the main frame
     * @param imageName the actual name of the image without the format (.jpg).
     */
    public static void loadImage(String imageName){
        String imagePath = "Images/"+imageName+".jpg";
        /*Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.createImage(imagePath);
        Image.setIcon((Icon) img);*/

        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            //Graphics g =new Graphics
            //Graphics.drawImage();

            //*
            ImageIcon icon = new ImageIcon(img);
            imageLabel.setIcon(icon);
            //JPanel ImagePanel = new JPanel();
            //ImagePanel.add(imageLabel);
            frame.add(imageLabel,BorderLayout.LINE_END);
            frame.repaint();

            //*/

            //JPanel panel = (JPanel)frame.getContentPane();



            //JOptionPane.showMessageDialog(null, label);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * As the name suggests unloadImage makes sure to take away the image after the corresponding question has been answered
     */
    public static void unloadImage(){
        frame.remove(imageLabel);
    }

    /***
     * EndGame is called when the Entire game has ended and updates some labels displaying the messages shown below.
     */
    public static void EndGame(){
        TheRoundTypeLabel.setText("");
        TheCategoryLabel.setText("The Game Has ended. Thank you for playing!");
        TheQuestionLabel.setText("If you would like to play again please rerun the program");
        TheUtilityLabel.setText("All of your statistics have been saved.");
        TheAnswersLabel1.setText("");
        TheAnswersLabel2.setText("");
        TheAnswersLabel3.setText("");
        TheAnswersLabel4.setText("This game is a product of PakPanTeam!");
    }
}