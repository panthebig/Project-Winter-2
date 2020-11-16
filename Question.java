import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Question extends Game{

    public Question(){

    }

    public boolean QuestionsAndAnswer(String X){
        String Correct; // The correct answer of the question
        String Answer; // The Player's input answer
        String[] CompleteQestion;

        CompleteQestion=TXT(X);
        System.out.println("Question : \n"+CompleteQestion[1]);
        System.out.println("Answers : \n"+CompleteQestion[2]+" "+CompleteQestion[3]+" "+CompleteQestion[4]+" "+CompleteQestion[5]);

        Scanner inp = new Scanner(System.in);
        Answer = inp.nextLine();

        if(Answer.equals(CompleteQestion[6])){         //error giati thelei initialization h Correct
            System.out.println("CORRECT!!!!!!\n");
            return true;
        }
        else{
            System.out.println("WRONG!!!!!\n");
            return false;
        }
    }

    private String[] TXT(String X){
        String[] category = { "Food" , "Technology" ,"Science"};
        ArrayList<String[]> outStream = new ArrayList<String[]>();

        String inputCategory = X;
        File text = new File("Question.txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int categoryIndex = 0;
        String[] temp;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("--------------------------")){
                categoryIndex++;
                continue;
            }
            if(inputCategory.contains(category[categoryIndex])){
                temp = line.split("\t",7);
                outStream.add(temp);
                //System.out.println(temp[0]+temp[1]+temp[2]+temp[3]+temp[4]+temp[5]+temp[6]);
            }

        }
        int randomN = ThreadLocalRandom.current().nextInt(0, outStream.size()); //[0,outStream)

        return outStream.get(randomN);
    }


}
