import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class  Round{

    private  int I,rand;
    private boolean X;
    public static String[] Rounds = {"Correct Answer","Betting","Stopwatch","Thermometer"};
    public static String[] Cat = {"Food","Science","Music","Technology","Films"};
    public static int k;


    public Round(){

    }

    /***
     * RoundStart is responsible for starting each round ,deciding a random category for each question
     * And calling the respective function with the question category as parameter.
     * @param i This parameter represents the type of round the player(s) has/have to play for the next 4 questions
     * @param AmountOfPlayers depending on this parameter the method decides which if case to follow
     * @return Returns an integer array which keeps track of the score and questions answered correctly by the player(s)
     *         During the round
     */
    public int[] RoundStart(int i,int AmountOfPlayers) { //Trexei o guros tupou i

        int[] Stats;
        if(AmountOfPlayers==1){
            Stats = new int[2];
            for(int l=0;l<2;l++){
                Stats[l]=0; //Stats[0]=Score1 Stats[1]=AmountOfCorrectQuestions
            }
        }else{
            Stats = new int[4];
        }

        if(i==1){
            System.out.println("The Round is -CORRECT ANSWER-\n");
        }
        else if(i==2){
            System.out.println("The Round is -BETTING-\n");
        }
        else if(i==3){
            System.out.println("The Round is -Stopwatch-\n");
        }

        GUI.updateRound(Rounds,i);

        if(i==1 || i==2 || i==3) {//Correct Answer or Betting or StopWatch


            for (I = 0; I < 4; I++) { //To Game kanei 4 erwthseis gurou i kai tuxaias katigorias

                rand = ThreadLocalRandom.current().nextInt(0, 5);  //[0,4]

                for (k = 0; k < AmountOfPlayers; k++) {

                    if (k == 0) {
                        System.out.println("Player 1 : ");
                    } else if (k == 1) {
                        System.out.println("Player 2 : ");
                    }

                    if (i == 1) {
                        System.out.println("And The Category you will be playing this Question is :-\n" + Cat[rand]);
                        X = RoundAnswer();
                        if (X) {
                            Stats[2 * k] = Stats[2 * k] + 1000;
                            Stats[1 + 2 * k]++;
                        }


                    } else if (i == 2) {
                        System.out.println("And The Category you will be playing this Question is :-\n" + Cat[rand]);

                        int am = GetBet();

                        X = RoundAnswer();
                        if (X) {
                            Stats[2 * k] = Stats[2 * k] + am;
                            Stats[1 + 2 * k]++;
                        } else {
                            Stats[2 * k] = Stats[2 * k] - am;
                        }
                    } else if (i == 3) {

                        GUI.StopWatch();
                        X = RoundAnswer();
                        GUI.StopTimer();

                        if (X) {
                            Stats[2 * k] = (int) (Stats[2 * k] + GUI.theTime * 0.2);
                            Stats[1 + 2 * k]++;
                        }

                    }
                }

            }
        }
        else{//Thermometer or Fast Answer
            if(i==4) {//Thermometer

                int counter = 0;
                int[] ThermometerStats = new int[2];
                ThermometerStats[0]=0;
                ThermometerStats[1]=0;
                while ((ThermometerStats[0] < 5 && ThermometerStats[1] < 5) && counter < 10) {//Runs for 10 questions each so to make sure the programm doesnt run out of questions early on.
                    for (k = 0; k < 2; k++) {

                        rand = ThreadLocalRandom.current().nextInt(0, 5);  //[0,4]

                        if (k == 0) {
                            System.out.println("Player 1 : ");
                        } else if (k == 1) {
                            System.out.println("Player 2 : ");
                        }

                        X = RoundAnswer();
                        if (X) {
                            ThermometerStats[k]++;
                            Stats[1 + 2 * k]++;
                        }

                        if(ThermometerStats[0] == 5 || ThermometerStats[1] == 5){
                            break;
                        }
                    }

                    counter++;
                }
                if (ThermometerStats[0] == 5) {
                    Stats[0] = Stats[0] + 5000;
                } else if (ThermometerStats[1] == 5) {
                    Stats[2] = Stats[2] + 5000;
                }

            }
            else{//Fast Answer

            }

        }

        return Stats;
    }

    /***
     * The following Function creates a Question object and calls the respective method
     * @return returns a boolean value weather the player(s) answered correctly or not
     */
    public boolean RoundAnswer(){
        Question CQ = new Question();
        return CQ.QuestionsAndAnswer(Cat[rand]);

    }

    public int GetBet(){
        System.out.println("How much Do You want to bet?\n");

        GUI.updateBet();
        int am = 0;
        boolean flag = true;
        while (flag) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            am = GUI.TheAmoundofBetting();
            if (am != 0) {
                flag = false;
            }
        }
        System.out.println("YOUR BET : " + am);
        return am;
    }


}