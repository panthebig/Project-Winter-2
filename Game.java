import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    int AmountOfPlayers;
    int i;
    int randomNum;
    int RoundPoints[];

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
        System.out.println("How Many Players?\n");
        Scanner inp = new Scanner(System.in);
        AmountOfPlayers = inp.nextInt();

        if(AmountOfPlayers==1){
            Player Player1 = new Player();

            System.out.println("To choose the correct answer use keys 'q w e r'\n");

            for(i=0;i<1;i++){
                Round aRound =new Round();
                RoundPoints=aRound.RoundStart(GetRandomInt(),1);
                Player1.ScoreCount(RoundPoints);
                Player1.PrintScore();
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


}
