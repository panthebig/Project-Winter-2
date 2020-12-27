import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class  Round{

    private  int I,rand;
    private boolean X;

    public static String[] Cat = {"Food","Science","Music","Technology","Films"};


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
                Stats[l]=0; //Stats[0]=Score1 Stats[1]=AmountOfRoundsWon
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


        for(I=0;I<4;I++) { //To Game kanei 4 erwthseis gurou i kai tuxaias katigorias

            rand=ThreadLocalRandom.current().nextInt(0, 5);  //[0,4]


            if (i == 1) {
                System.out.println("And The Category you will be playing this Question is :-\n"+Cat[rand]);
                X = RoundAnswer();
                if (X) {
                    Stats[0] = Stats[0] + 1000;
                    Stats[1]++;
                }
                else{
                    Stats[1]--;
                }

            } else if (i == 2) {
                System.out.println("And The Category you will be playing this Question is :-\n"+Cat[rand]);

                System.out.println("How much Do You want to bet?\n");

                Scanner input = new Scanner(System.in);
                int am = input.nextInt();

                X = RoundAnswer();
                if (X) {
                    Stats[0] = Stats[0] + am;
                    Stats[1]++;
                }
                else{
                    Stats[0] = Stats[0] - am;
                    Stats[1]--;
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



}