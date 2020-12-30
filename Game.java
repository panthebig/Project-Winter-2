import java.io.FileWriter;
import java.io.IOException;
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
        highscoresFile(0 , 0);

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

        TXT();

        if(AmountOfPlayers==1){
            Player Player1 = new Player();

            System.out.println("To choose the correct answer use keys 'q w e r'\n");

            for(i=0;i<4;i++){

                Round aRound =new Round();
                RoundPoints=aRound.RoundStart(GetRandomInt(),1);
                Player1.ScoreCount(RoundPoints);
                Player1.PrintScore();
            }
            highscoresFile(Player1.Score , 0);
        }
        else {
            Player Player1 = new Player();
            Player Player2 = new Player();
            int[] RoundPointsPlayer1 = new int[2];
            int[] RoundPointsPlayer2 = new int[2];

            for(i=0;i<1;i++) {
                Round aRound = new Round();
                RoundPoints = aRound.RoundStart(1, 2);
                RoundPointsPlayer1[0] = RoundPoints[0];
                RoundPointsPlayer1[1] = RoundPoints[1];
                RoundPointsPlayer2[0] = RoundPoints[2];
                RoundPointsPlayer2[1] = RoundPoints[3];

                Player1.ScoreCount(RoundPointsPlayer1);
                Player2.ScoreCount(RoundPointsPlayer2);


                Player1.PrintScore();
                Player2.PrintScore();
            }
            highscoresFile(Player1.Score , Player2.Score);

        }

    }

    /***
     * Calculates a random integer given the lower and upper bounds of the number
     * @return randomNum which is the random number
     */
    public int GetRandomInt(){
        if(AmountOfPlayers==1){
            int randomNum = ThreadLocalRandom.current().nextInt(1, 4); //[1,3]
            return randomNum;
        }
        else{
            int randomNum = ThreadLocalRandom.current().nextInt(1, 5); //[1,4] Edo telika prepei na ginei [1,5] gt oi 2 paiktes mporoun na ta paiskoun ola
            return randomNum;
        }
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
        scanner.close();

    }

    public int[] highscoresFile(int p1 , int p2){
        int ar[] = new int[3];
        try {
            File myObj = new File("Highscore.txt");

            if (myObj.createNewFile()) {
                try {
                    FileWriter myWriter = new FileWriter("Highscore.txt");
                    myWriter.write("Solo highscore is: 0\n" +
                            "Player 1 Wins: 0\n" +
                            "Player 2 Wins: 0");
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
                Scanner myReader = new Scanner(myObj);
                String text ="";
                String currentline;
                String[] line;
                int i=0;
                while ( myReader.hasNextLine()){
                    currentline = myReader.nextLine(); // line[3] has the number
                    text += currentline + "\n";
                    line = currentline.split(" ");
                    ar[i] = Integer.parseInt(line[3]);
                    i++;

                }
                if(AmountOfPlayers == 1){

                    if(p1 > ar[0]){
                        text = text.replaceAll("Solo highscore is: " + String.valueOf(ar[0]) , "Solo highscore is: " + String.valueOf(p1) );

                        FileWriter myWriter = new FileWriter("Highscore.txt");
                        myWriter.write( text);
                        myWriter.close();
                    }
                }else if(AmountOfPlayers == 2)
                    if(p1>p2){
                        text = text.replaceAll("Player 1 Wins: " + String.valueOf(ar[1]) , "Player 1 Wins: " + String.valueOf(ar[1] + 1));

                        FileWriter myWriter = new FileWriter("Highscore.txt");
                        myWriter.write( text);
                        myWriter.close();
                    }else if(p2>p1){
                        text = text.replaceAll("Player 2 Wins: " + String.valueOf(ar[2]) , "Player 2 Wins: " + String.valueOf(ar[2] + 1));

                        FileWriter myWriter = new FileWriter("Highscore.txt");
                        myWriter.write( text);
                        myWriter.close();
                    }

                myReader.close();
                // Read compare and write
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return ar;

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