package com.pakpan.pro;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {


        enum QuestionType{
            Food,
            Geography,
            Ath
        }

    public static void main(String[] args) {

        String[] category = { "Food" , "Geograph" ,"athe"};
        ArrayList<String[]> outStream = new ArrayList<String[]>();
        Scanner input = new Scanner(System.in);
        String inputCategory = input.nextLine();
        System.out.println("Give Category");
        File text = new File("Food.txt");

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
                System.out.println(temp[0]+temp[1]+temp[2]+temp[3]+temp[4]+temp[5]+temp[6]);
            }

        }

        //System.out.println(outStream.get(1)[4]);
        //return outStream;
    }

}
