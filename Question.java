import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;



public class Question {
    public static int FastestAnswer;
    public static String[] Speed = new String[4];

    public Question(){

    }

    private void ArrayShuffle(String[] Arr){
        Random rando = ThreadLocalRandom.current();
        for (int i = Arr.length - 2; i > 2; i--)
        {
            int index = ThreadLocalRandom.current().nextInt(2,6);
            // Simple swap
            String a = Arr[index];
            Arr[index] = Arr[i];
            Arr[i] = a;
        }
    }


    /***
     * Picks a random question ,binds each input choice/key with the respective possible answer
     * And checks weather or not the player(s) answered correctly
     * @param cat represents the category from which the random question will be chosen
     * @return Returns a boolean statement given weather or not the player answered correctly or not
     */
    public boolean QuestionsAndAnswer(String cat){

        String Correct; // The correct answer of the question
        String Answer; // The Player's input answer
        String Answer2;

        int categoryStartIndex=0;
        int categoryEndIndex=0;
        boolean notFound = true;
        for(int i=0;i<Game.getSize();i++){

            if(cat.equals(Game.getString(i)[0]) && notFound){
                categoryStartIndex=i;
                notFound = false;
            }

            if(cat .equals(Game.getString(i)[0])){
                categoryEndIndex=i;
            }

        }


        int randomQuestionIndex = ThreadLocalRandom.current().nextInt(categoryStartIndex,categoryEndIndex);

        //System.out.println("Start:"+categoryStartIndex +"   "+categoryEndIndex+"\n");
        String[] CompleteQestion = Game.getString(randomQuestionIndex);
        String[] KeyBind = {"q","w","e","r"};
        if(cat == "Geography"){
            System.out.println("random index " +randomQuestionIndex + " start idnex " + categoryStartIndex + " cat end " + categoryEndIndex);
            GUI.loadImage(CompleteQestion[6]);
        }

        ArrayShuffle(CompleteQestion);

        GUI.updateQuestion(CompleteQestion);


        //System.out.println("Question : \n"+CompleteQestion[1]);
        //System.out.println("Answers : \n"+"press q for:"+CompleteQestion[2]+",  "+"press w for:"+CompleteQestion[3]+",  "+"press e for:"+CompleteQestion[4]+",  "+"press r for:"+CompleteQestion[5]);


        if(Round.TheCurrentRoundType!=5) {

            Answer = Character.toString(GUI.getChar());

            if(cat == "Geography"){
                GUI.unloadImage();
            }
            //System.out.println(Answer);

            if (Round.k == 0) {
                if (Answer.equals("q")) {
                    Answer = CompleteQestion[2];
                } else if (Answer.equals("w")) {
                    Answer = CompleteQestion[3];
                } else if (Answer.equals("e")) {
                    Answer = CompleteQestion[4];
                } else if (Answer.equals("r")) {
                    Answer = CompleteQestion[5];
                }
            } else if (Round.k == 1) {
                if (Answer.equals("u")) {
                    Answer = CompleteQestion[2];
                } else if (Answer.equals("i")) {
                    Answer = CompleteQestion[3];
                } else if (Answer.equals("o")) {
                    Answer = CompleteQestion[4];
                } else if (Answer.equals("p")) {
                    Answer = CompleteQestion[5];
                }
            }


            if (Answer.equals(CompleteQestion[6])) {
                System.out.println("CORRECT!!!!!!\n");
                Game.removeString(randomQuestionIndex);
                return true;
            } else {
                System.out.println("WRONG!!!!!\n");
                Game.removeString(randomQuestionIndex);
                return false;
            }
        }
        else{
            Round.k=3;
            Answer = Character.toString(GUI.getChar());

            if (Answer.equals("q")) {
                Answer = CompleteQestion[2];
                Speed[0]="Player1";
            } else if (Answer.equals("w")) {
                Answer = CompleteQestion[3];
                Speed[0]="Player1";
            } else if (Answer.equals("e")) {
                Answer = CompleteQestion[4];
                Speed[0]="Player1";
            } else if (Answer.equals("r")) {
                Answer = CompleteQestion[5];
                Speed[0]="Player1";
            }
            else if (Answer.equals("u")) {
                Answer = CompleteQestion[2];
                Speed[0]="Player2";
            } else if (Answer.equals("i")) {
                Answer = CompleteQestion[3];
                Speed[0]="Player2";
            } else if (Answer.equals("o")) {
                Answer = CompleteQestion[4];
                Speed[0]="Player2";
            } else if (Answer.equals("p")) {
                Answer = CompleteQestion[5];
                Speed[0]="Player2";
            }

            if (Answer.equals(CompleteQestion[6])) {
                System.out.println("The Fastest player Answered Correctly\n");
                Speed[1]="Correct";
            } else {
                System.out.println("The Fastest player Answered WRONGLY\n");
                Speed[1]="Wrong";
            }


            if(Speed[0].equals("Player1")){//if Player1 has the fastest answer then only the Player2 can answer
                Round.k=1;
                Answer2 = Character.toString(GUI.getChar());

                if (Answer2.equals("u")) {
                    Answer2 = CompleteQestion[2];
                } else if (Answer2.equals("i")) {
                    Answer2 = CompleteQestion[3];
                } else if (Answer2.equals("o")) {
                    Answer2 = CompleteQestion[4];
                } else if (Answer2.equals("p")) {
                    Answer2 = CompleteQestion[5];
                }

                Speed[2] = "Player2";

            }
            else {//if Player2 has the fastest answer then only the Player1 can answer
                Round.k=0;
                Answer2 = Character.toString(GUI.getChar());

                if (Round.k == 0) {
                    if (Answer2.equals("q")) {
                        Answer2 = CompleteQestion[2];
                    } else if (Answer2.equals("w")) {
                        Answer2 = CompleteQestion[3];
                    } else if (Answer2.equals("e")) {
                        Answer2 = CompleteQestion[4];
                    } else if (Answer2.equals("r")) {
                        Answer2 = CompleteQestion[5];
                    }
                }

                Speed[2] = "Player1";
            }

            if(cat == "Geography"){
                GUI.unloadImage();
            }


            if (Answer2.equals(CompleteQestion[6])) {
                System.out.println("Second Fastest player Answered Correctly\n");
                Game.removeString(randomQuestionIndex);
                Speed[3]="Correct";
                return true;
            } else {
                System.out.println("Second Fastest player Answered WRONGLY\n");
                Game.removeString(randomQuestionIndex);
                Speed[3]="Wrong";
                return false;
            }
        }

    }

}