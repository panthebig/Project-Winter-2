import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GUI extends JFrame{


    private JFrame frame;
    private JPanel TopGamePanel;
    private JPanel CenterGamePanel;
    private JPanel QuestionPanel;
    private JPanel CategoryPanel;
    private JPanel AnsewrsPanel0;
    private JPanel AnsewrsPanel1;
    private JPanel AnsewrsPanel2;
    private JPanel AnsewrsPanel3;
    private JPanel AnsewrsPanel4;
    private JButton StartButton;
    private JButton HighScores;
    private static JLabel TheQuestionLabel;
    private static JLabel TheCategoryLabel;
    private static JLabel TheAnswersLabel1;
    private static JLabel TheAnswersLabel2;
    private static JLabel TheAnswersLabel3;
    private static JLabel TheAnswersLabel4;

    private JTextField Question;
    private int NumberOfPlayers;

    public GUI(){

        frame = new JFrame("BuzzQuizWorld!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(false);
        frame.setLayout(new BorderLayout());
        frame.setFocusable(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyChar()) {
                    case 'q':
                        System.out.println("Player 1 - Answer A");
                        break;
                    case 'w':
                        System.out.println("Player 1 - Answer B");
                        break;
                    case 'e':
                        System.out.println("Player 1 - Answer C");
                        break;
                    case 'r':
                        System.out.println("Player 1 - Answer D");
                        break;
                }
            }
        });

        TopGamePanel = new JPanel();
        TopGamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        TopGamePanel.setBackground(Color.orange);
        //TopGamePanel.setLayout(new BorderLayout());
        frame.add(TopGamePanel, BorderLayout.PAGE_START);


        StartButton = new JButton("Start");
        StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] players = {"One", "Two"};

                int n = JOptionPane.showOptionDialog(null,
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
                else {
                    System.out.println(players[n] + " players will be playing");
                    NumberOfPlayers=2;
                }//can easily add more players!
            }
        });
        TopGamePanel.add(StartButton);

        HighScores = new JButton("Get The High Scores");
        HighScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        TopGamePanel.add(HighScores);

        CenterGamePanel = new JPanel();
        CenterGamePanel.setLayout(new GridLayout(7,1));
        CenterGamePanel.setBackground(Color.DARK_GRAY);
        frame.add(CenterGamePanel,BorderLayout.CENTER);

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

        AnsewrsPanel0 = new JPanel();
        AnsewrsPanel0.setBackground(Color.lightGray);
        CenterGamePanel.add(AnsewrsPanel0);

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
    }



    public static void updateQuestion(String[] Questions){
        TheCategoryLabel.setText("Category : " + Questions[0]);
        TheQuestionLabel.setText("Question : " + Questions[1]);
        TheAnswersLabel1.setText("Q : " + Questions[2]);
        TheAnswersLabel2.setText("W : " + Questions[3]);
        TheAnswersLabel3.setText("E : " + Questions[4]);
        TheAnswersLabel4.setText("R : " + Questions[5]);


    }
/*
    public static void updateAnswers(String[] Questions){
        System.out.println(Questions[1]);
        label1.setText(Questions[1]);
    }
*/

}