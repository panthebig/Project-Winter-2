import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class  Round{

    private  int I,rand;
    private boolean X;
    public static String[] Rounds = {"Correct Answer","Betting","Stopwatch","Thermometer","Fast Answer"};
    public static String[] Cat = {"Food","Science","Music","Technology","Films","Geography"};//TODO
    public static int k;
    public static int TheCurrentRoundType;


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

        TheCurrentRoundType=i;

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

                rand = ThreadLocalRandom.current().nextInt(0, 6);  //TODO [0,5...]

                k=AmountOfPlayers-1;



                    if (i == 1) {
                        //System.out.println("And The Category you will be playing this Question is :-\n" + Cat[rand]);
                        X = RoundAnswer();
                        if(k==0){
                            if (Question.Speed[1].equals("Correct")) {
                                Stats[2 * k] = Stats[2 * k] + 1000;
                                Stats[1 + 2 * k]++;
                            }
                        }
                        else{
                            if(Question.Speed[0].equals("Player1")) {//Player1 answered First and Player2 answered Second
                                if (Question.Speed[1].equals("Correct")) {//Player1 answered Correctly
                                    Stats[0] = Stats[0] + 1000;
                                    Stats[1]++;
                                }

                                if(Question.Speed[3].equals("Correct")){//Player2 answered Correctly
                                    Stats[2] = Stats[2] + 1000;
                                    Stats[3]++;
                                }
                            }
                            else if(Question.Speed[0].equals("Player2")) {//Player1 answered First and Player2 answered Second
                                if (Question.Speed[1].equals("Correct")) {//Player1 answered Correctly
                                    Stats[2] = Stats[2] + 1000;
                                    Stats[3]++;
                                }

                                if(Question.Speed[3].equals("Correct")){//Player2 answered Correctly
                                    Stats[0] = Stats[0] + 1000;
                                    Stats[1]++;
                                }
                            }
                        }

                    } else if (i == 2) {

                        int[] am = new int[2];
                        for(int j=0;j<AmountOfPlayers;j++) {
                            am[j] = GetBet();//Prwta o protos vasei bet
                        }

                        X = RoundAnswer();
                        if(k==0){
                            if (Question.Speed[1].equals("Correct")) {
                                Stats[2 * k] = Stats[2 * k] + am[0];
                                Stats[1 + 2 * k]++;
                            } else {
                                Stats[2 * k] = Stats[2 * k] - am[0];
                            }
                        }
                        else {
                            if(Question.Speed[0].equals("Player1")) {//Player1 answered First and Player2 answered Second
                                if (Question.Speed[1].equals("Correct")) {//Player1 answered Correctly
                                    Stats[0] = Stats[0] + am[0];
                                    Stats[1]++;
                                }
                                else {//Player1 answered Wrongly
                                    Stats[0] = Stats[0] - am[0];
                                }

                                if(Question.Speed[3].equals("Correct")){//Player2 answered Correctly
                                    Stats[2] = Stats[2] + am[1];
                                    Stats[3]++;
                                }
                                else{//Player2 answered Wrongly
                                    Stats[2] = Stats[2] - am[1];
                                }
                            }
                            else if(Question.Speed[0].equals("Player2")) {//Player2 answered First and Player1 answered Second
                                if (Question.Speed[1].equals("Correct")) {//Player2 answered Correctly
                                    Stats[2] = Stats[2] + am[1];
                                    Stats[3]++;
                                }
                                else{//Player2 answered Wrongly
                                    Stats[2] = Stats[2] - am[1];
                                }

                                if(Question.Speed[3].equals("Correct")){//Player1 answered Correctly
                                    Stats[0] = Stats[0] + am[0];
                                    Stats[1]++;
                                }
                                else {//Player1 answered Wrongly
                                    Stats[0] = Stats[0] - am[0];
                                }
                            }
                        }

                    } else if (i == 3) {

                        GUI.StopWatch();
                        X = RoundAnswer();
                        GUI.StopTimer();
                        if(k==0){
                            //System.out.println(GUI.theTime1);
                            if (X) {
                                Stats[2 * k] = (int) (Stats[2 * k] + GUI.theTime1 * 0.2);
                                Stats[1 + 2 * k]++;
                            }
                        }
                        else{
                            //System.out.println(Question.Timer1+"\n");
                            //System.out.println(Question.Timer2+"\n");
                            if(Question.Speed[0].equals("Player1")) {//Player1 answered First and Player2 answered Second
                                if (Question.Speed[1].equals("Correct")) {//Player1 answered Correctly
                                    Stats[0] = (int) (Stats[0] + Question.Timer1 * 0.2);
                                    Stats[1]++;
                                }

                                if(Question.Speed[3].equals("Correct")){//Player2 answered Correctly
                                    Stats[2] = (int) (Stats[2] + Question.Timer2 * 0.2);
                                    Stats[3]++;
                                }
                            }
                            else if(Question.Speed[0].equals("Player2")) {//Player2 answered First and Player1 answered Second
                                if (Question.Speed[1].equals("Correct")) {//Player2 answered Correctly
                                    Stats[2] = (int) (Stats[2] + Question.Timer2 * 0.2);
                                    Stats[3]++;
                                }

                                if(Question.Speed[3].equals("Correct")){//Player1 answered Correctly
                                    Stats[0] = (int) (Stats[0] + Question.Timer1 * 0.2);
                                    Stats[1]++;
                                }
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
                        k=AmountOfPlayers-1;

                        rand = ThreadLocalRandom.current().nextInt(0, 6);  //[0,5]

                        X = RoundAnswer();
                        if(Question.Speed[0].equals("Player1")){
                            if (Question.Speed[1].equals("Correct")) {//Player1 answered Correctly
                                ThermometerStats[0]++;
                                Stats[1]++;
                            }

                            if (Question.Speed[3].equals("Correct")) {//Player2 answered Correctly
                                ThermometerStats[1]++;
                                Stats[3]++;
                            }
                        }
                        else{//Player2 Answered First
                            if (Question.Speed[1].equals("Correct")) {//Player2 answered Correctly
                                ThermometerStats[1]++;
                                Stats[3]++;
                            }

                            if (Question.Speed[3].equals("Correct")) {//Player1 answered Correctly
                                ThermometerStats[0]++;
                                Stats[1]++;
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
                for(I=0;I<4;I++){
                    rand = ThreadLocalRandom.current().nextInt(0, 5);  //[0,4]
                    k=1;

                    X = RoundAnswer();
                    System.out.println("---- "+k+" ----");
                    if(Question.Speed[0].equals("Player1")){//Player1 answered First and Player2 answered Second
                        if(Question.Speed[1].equals("Correct")){//Player1 answered Correctly
                            Stats[0] = Stats[0] + 1000;
                            Stats[1]++;
                        }



                        if(Question.Speed[3].equals("Correct")){//Player2 answered Correctly
                            if(Question.Speed[1].equals("Wrong")){
                                Stats[2] = Stats[2] + 1000;
                                Stats[3]++;
                            }
                            else {
                                Stats[2] = Stats[2] + 500;
                                Stats[3]++;
                            }
                        }

                    }
                    else if(Question.Speed[0].equals("Player2")){//Player2 answered First and Player1 answered Second
                        if(Question.Speed[1].equals("Correct")){//Player2 answered Correctly
                            Stats[2] = Stats[2] + 1000;
                            Stats[3]++;
                        }

                        if(Question.Speed[3].equals("Correct")){//Player1 answered Correctly
                            if(Question.Speed[1].equals("Wrong")){
                                Stats[0] = Stats[0] + 1000;
                                Stats[1]++;
                            }
                            else {
                                Stats[0] = Stats[0] + 500;
                                Stats[1]++;
                            }
                        }
                    }
                }
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
        System.out.println("How much Do You want to bet?");

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