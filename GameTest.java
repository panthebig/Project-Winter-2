import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import static org.junit.Assert.assertEquals;


public class GameTest {

    public boolean QuestionTESTING(int i,String testChar,String[] CompleteQestion,int k){

                String Answer;

                    Answer = testChar;

                    if (k == 0) {
                        if (Answer.equals("q")) {
                            Answer = CompleteQestion[2];
                        } else if (Answer.equals("w")) {
                            Answer = CompleteQestion[3];
                        } else if (Answer.equals("e")) {
                            Answer = CompleteQestion[4];
                        } else if (Answer.equals("r")) {
                            Answer = CompleteQestion[5];
                        }
                    } else if (k == 1) {
                        if (Answer.equals("u")) {
                            Answer = CompleteQestion[2];
                        } else if (Answer.equals("i")) {
                            Answer = CompleteQestion[3];
                        } else if (Answer.equals("o")) {
                            Answer = CompleteQestion[4];
                        } else if (Answer.equals("p")) {
                            Answer = CompleteQestion[5];
                        }
                    }


                    if (Answer.equals(CompleteQestion[6])) {
                        return true;
                    } else {
                        return false;
                    }


    }


    @Test
    public void testCorrectAnswerPoints(){
        //i einai o tupos gurou
        int i=1;
        int Points=0;
        String[] Array = {"Films","How many films have Al Pacino and Robert De Niro starred in together?","10","6","2","4","4"};
        String[] KeySimulation = {"q","w","e","r"};
        boolean testCorrection;

        assertEquals(QuestionTESTING(i,"r",Array,0),true);
        assertEquals(QuestionTESTING(i,"a",Array,0),false);

        for(int j=0;j<4;j++) {
            testCorrection = QuestionTESTING(i, KeySimulation[j], Array, 0);
            if (testCorrection) {
                Points += 1000;
            }
        }
        assertEquals(Points,1000);
        assertEquals(Points>1000,false);
    }

    @Test
    public void testBettingPoints(){
        //i einai o tupos gurou
        int i=1;
        int Points=0;
        String[] Array = {"Films","How many films have Al Pacino and Robert De Niro starred in together?","10","6","2","4","4"};
        String[] KeySimulation = {"q","w","e","r"};
        boolean testCorrection;

        assertEquals(QuestionTESTING(i,"r",Array,0),true);
        assertEquals(QuestionTESTING(i,"a",Array,0),false);
        int Bet=500;
        for(int j=0;j<4;j++) {
            testCorrection = QuestionTESTING(i, KeySimulation[j], Array, 0);
            if (testCorrection) {
                Points += Bet;
            }
            else{
                Points -= Bet;
            }
        }
        assertEquals(Points,-1000);
        assertEquals(Points!=-1000,false);
    }

    @Test
    public void testBetting2Points(){
        //i einai o tupos gurou
        int i=1;
        int Points=0;
        String[] Array = {"Films","How many films have Al Pacino and Robert De Niro starred in together?","10","6","2","4","4"};
        String[] KeySimulation = {"r","r","e","r"};
        boolean testCorrection;

        assertEquals(QuestionTESTING(i,"r",Array,0),true);
        assertEquals(QuestionTESTING(i,"a",Array,0),false);
        int Bet=750;
        for(int j=0;j<4;j++) {
            testCorrection = QuestionTESTING(i, KeySimulation[j], Array, 0);
            if (testCorrection) {
                Points += Bet;
            }
            else{
                Points -= Bet;
            }
        }
        assertEquals(Points,1500);
        assertEquals(Points!=1500,false);
    }

    @Test
    public void testCorrectAnswerPoints2Players(){
        //i einai o tupos gurou
        int i=1;
        int Points=0;
        int Points2=0;
        String[] Array = {"Films","How many films have Al Pacino and Robert De Niro starred in together?","10","6","2","4","4"};
        String[] KeySimulation = {"r","r","e","r"};
        String[] KeySimulation2 = {"u","p","o","p"};
        boolean testCorrection;
        boolean testCorrection2;

        assertEquals(QuestionTESTING(i,"r",Array,0),true);
        assertEquals(QuestionTESTING(i,"q",Array,0),false);

        assertEquals(QuestionTESTING(i,"p",Array,1),true); //GIA deutero paikti
        assertEquals(QuestionTESTING(i,"u",Array,1),false);

        int Bet=750;
        for(int j=0;j<4;j++) {
            testCorrection = QuestionTESTING(i, KeySimulation[j], Array, 0);
            testCorrection2 = QuestionTESTING(i, KeySimulation2[j], Array, 1);
            if (testCorrection) {
                Points += Bet;
            }
            else{
                Points -= Bet;
            }

            if (testCorrection2) {
                Points2 += Bet;
            }
            else{
                Points2 -= Bet;
            }

        }
        assertEquals(Points,1500);
        assertEquals(Points!=1500,false);

        assertEquals(Points2,0);
        assertEquals(Points2!=0,false);
    }

}