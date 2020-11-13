import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    int AmountOfPlayers;
    int i;
    int randomNum;
    int RoundPoints;

    public Game(){
        this.AmountOfPlayers=0;
    }

    public void GameStart(){
        System.out.println("How Many Players?\n");
        Scanner inp = new Scanner(System.in);
        AmountOfPlayers = inp.nextInt();

        if(AmountOfPlayers==1){
            Player Player1 = new Player();



            for(i=0;i<1;i++){
                Round aRound =new Round();
                RoundPoints=aRound.RoundStart(GetRandomInt());
                System.out.println("Points:"+RoundPoints);
            }
        }
        else {
            Player Player1 = new Player();
            Player Player2 = new Player();
        }

    }

    public int GetRandomInt(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
        return randomNum;
    }


}
