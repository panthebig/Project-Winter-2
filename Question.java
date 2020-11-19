import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Question {

    public Question(){

    }

    public boolean QuestionsAndAnswer(){

        String Correct; // The correct answer of the question
        String Answer; // The Player's input answer
        System.out.println(Game.getSize());
        System.out.println(Game.outStream.toString());

        int randomQuestionIndex = ThreadLocalRandom.current().nextInt(0,Game.getSize());
        String[] CompleteQestion = Game.getString(randomQuestionIndex);

        System.out.println("And The Category you will be playing this Question is :-\n"+CompleteQestion[0]);
        System.out.println(" Question : \n"+CompleteQestion[1]);
        System.out.println("Answers : \n"+CompleteQestion[2]+" "+CompleteQestion[3]+" "+CompleteQestion[4]+" "+CompleteQestion[5]);

        Scanner inp = new Scanner(System.in);
        Answer = inp.nextLine();

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
