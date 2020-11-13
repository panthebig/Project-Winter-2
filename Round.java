public class  Round extends Game{

    private  int I,Sum,rand;
    private boolean X;
    public static String[] Cat = {"","Animals","Sports","Politics","Food"};

    public Round(){

    }


    public int RoundStart(int i) { //Trexei o guros tupou i
        for(I=0;I<4;I++) { //To Game kanei 4 erwthseis gurou i kai tuxaias katigorias
            rand=GetRandomInt();

            if (i == 1) {
                System.out.println("The Round is -CORRECT ANSWER-\n");
                System.out.println("And The Category you will be playing this Question is :-\n"+Cat[rand]);
                X = RoundCorrectAnswer();
                if (X) {
                    Sum = Sum + 1000;
                }

            } else if (i == 2) {
                System.out.println("The Round is -BETTING-\n");
                System.out.println("And The Category you will be playing this Question is :-\n"+Cat[rand]);
                X = RoundBetting();
                if (X) {
                    Sum = Sum + 1000;
                }
            }

        }
        return Sum;
    }

    public boolean RoundCorrectAnswer(){
        //Question CQ = new Question();
        //return CQ.QuestionsAndAnswer();
        return true;
    }

    public boolean RoundBetting(){
        //Question CQ = new Question();
        //return  CQ.QuestionsAndAnswer();
        return true;
    }


}
