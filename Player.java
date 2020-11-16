public class Player {
    public int Score;
    private int RoundsWon;
    private int HighScore;

    public Player(){
        this.Score=0;
        this.RoundsWon=0;
        this.HighScore=0;
    }
    public void ScoreCount(int[] i){
        this.Score=this.Score+i[0];
        if(this.Score>this.HighScore){
            this.HighScore=this.Score;
        }
        this.RoundsWon=this.RoundsWon+i[1];
    }
    public void PrintScore(){
        System.out.println("Total Score : " +this.Score);
    }

}
