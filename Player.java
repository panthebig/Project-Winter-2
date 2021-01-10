public class Player {
    public int Score;
    private int QueastionsAnsweredCorectly;
    private int HighScore;

    /***
     * A simple constructor.
     * Sets every variable to 0.
     */
    public Player(){
        this.Score=0;
        this.QueastionsAnsweredCorectly=0;
        this.HighScore=0;
    }

    /***
     * Given an Array i ,updates the respective variables
     * @param i Represents an Array with the score of each round and the questions answered ,of correctly by each player
     */
    public void ScoreCount(int[] i){
        this.Score=this.Score+i[0];
        if(this.Score>this.HighScore){
            this.HighScore=this.Score;
        }
        this.QueastionsAnsweredCorectly=this.QueastionsAnsweredCorectly+i[1];
    }

    public void PrintScore(){
        System.out.println("Total Score : " +this.Score);
    }


}