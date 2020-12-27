import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;



public class Question {

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

        int categoryStartIndex=0;
        int categoryEndIndex=0;
        Boolean notFound = true;
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
        System.out.println("Start:"+categoryStartIndex +"   "+categoryEndIndex+"\n");
        String[] CompleteQestion = Game.getString(randomQuestionIndex);
        String[] KeyBind = {"q","w","e","r"};
        ArrayShuffle(CompleteQestion);

        GUI.updateQuestion(CompleteQestion);
        //GUI.updateAnswers(CompleteQestion);

        //System.out.println("Question : \n"+CompleteQestion[1]);
        //System.out.println("Answers : \n"+"press q for:"+CompleteQestion[2]+",  "+"press w for:"+CompleteQestion[3]+",  "+"press e for:"+CompleteQestion[4]+",  "+"press r for:"+CompleteQestion[5]);



        //Scanner inp = new Scanner(System.in);
        //Answer = inp.nextLine();
        Answer = Character.toString(GUI.getChar());
        System.out.println(Answer);

        if(Answer.equals("q")){
            Answer = CompleteQestion[2];
        }
        else if(Answer.equals("w")){
            Answer = CompleteQestion[3];
        }
        else if(Answer.equals("e")){
            Answer = CompleteQestion[4];
        }
        else if(Answer.equals("r")){
            Answer = CompleteQestion[5];
        }



        if(Answer.equals(CompleteQestion[6])){
            System.out.println("CORRECT!!!!!!\n");
            Game.removeString(randomQuestionIndex);
            return true;
        }
        else{
            System.out.println("WRONG!!!!!\n");
            Game.removeString(randomQuestionIndex);
            return false;
        }

    }


}
