import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    private JFrame frame;
    private JPanel TopGamePanel;
    private JPanel CenterGamePanel;
    private JPanel QuestionPanel;
    private JButton StartButton;
    private JButton HighScores;
    private JLabel TheQuestionLabel;

    private JTextField Question;
    private int NumberOfPlayers;

    public GUI(){

        frame = new JFrame("BuzzQuizWorld!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(false);
        frame.setLayout(new BorderLayout());

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
        CenterGamePanel.setLayout(new BorderLayout());
        CenterGamePanel.setBackground(Color.GRAY);
        frame.add(CenterGamePanel,BorderLayout.CENTER);

        QuestionPanel = new JPanel();
        QuestionPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        QuestionPanel.setBackground(Color.lightGray);
        CenterGamePanel.add(QuestionPanel,BorderLayout.PAGE_START);

        TheQuestionLabel = new JLabel("Question : ");
        QuestionPanel.add(TheQuestionLabel);













    }

    private void AmountOfPlayersButton(){

    }

    void startGUI(){
        frame.setVisible(true);
    }

}
