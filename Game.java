import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game {
    int AmountOfPlayers;
    int i;
    int RoundPoints[];
    static ArrayList<String[]> outStream = new ArrayList<String[]>();


    public Game(){
        this.AmountOfPlayers=0;
    }

    /***
     * Gets the amount of players that will be playing the game via keyboard input
     * Depending on the amount of players ,creates the player's/players' object(s) and allows the first round to begin
     * GameStart is also responsible for calling the equivalent method for counting the score
     * And keeping track of the number of questions answered correctly by each player!
     */
    public void GameStart(){

        boolean flag = true;

        System.out.println("How Many Players?\n");
        //Scanner inp = new Scanner(System.in);
        //AmountOfPlayers = inp.nextInt();
        while (flag){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AmountOfPlayers = GUI.numberOfPlayers();
            if(AmountOfPlayers != 0){
                flag = false;
            }
        }

        System.out.println(AmountOfPlayers);
        TXT();

        if(AmountOfPlayers==1){
            Player Player1 = new Player();

            System.out.println("To choose the correct answer use keys 'q w e r'\n");

            for(i=0;i<4;i++){

                Round aRound =new Round();
                RoundPoints=aRound.RoundStart(GetRandomInt(),1);
                Player1.ScoreCount(RoundPoints);
                Player1.PrintScore();
                System.out.println(i);
            }
        }
        else {
            Player Player1 = new Player();
            Player Player2 = new Player();
        }

    }

    /***
     * Calculates a random integer given the lower and upper bounds of the number
     * @return randomNum which is the random number
     */
    public int GetRandomInt(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 3); //[1,2]
        return randomNum;
    }


    /***
     * Opens the file with all the questions,the category of each question,the possible asnwers and the correct asnwer
     * And puts them in an Arraylist
     */
    public void TXT(){

        File text = new File("Question.txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] temp;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            temp = line.split("\t",7);
            outStream.add(temp);
            //System.out.println(outStream.size());
            //System.out.println(temp[0]+temp[1]+temp[2]+temp[3]+temp[4]+temp[5]+temp[6]
        }

    }

    public static void removeString(int num){
        outStream.remove(num);
    }
    public static String[] getString(int num){
        return outStream.get(num);
    }
    public static int getSize(){
        return outStream.size();
    }

}