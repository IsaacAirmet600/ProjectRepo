import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Card {
    int idNum;
    int size;
    NumberSet numberSet;
    Card(int idNum, int size, NumberSet numberSet){
        this.idNum = idNum;
        this.size = size;
        this.numberSet = numberSet;
    }
    public int getId(){
        return idNum;
    }
    public int getSize(){
        return size;
    }
    // FIX FILE
    public void print(String file){
        String border = "+-----";
        if(file == null){
            System.out.println("Card #" + getId());
            for(int row = 0; row < getSize(); row++){
                for(int i = 0; i < getSize(); i++){
                    System.out.print(border);
                }
                System.out.println("+");
                for(int value = 0; value < getSize(); value++){
                    System.out.print("|");
                    String number = spacer(numberSet.getNext());
                    System.out.print(number);
                }
                System.out.println("|");
            }
            for(int x = 0; x < getSize(); x++){
                System.out.print(border);
            }
            System.out.print("+\n");

        } else {
            File output = new File(file);
            try(FileWriter writer = new FileWriter(output)){
                writer.write("Card #" + getId());
                for(int row = 0; row < getSize(); row++){
                    for(int i = 0; i < getSize(); i++){
                        writer.append(border);
                    }
                    writer.write("+");
                    for(int value = 0; value < getSize(); value++){
                        writer.append("|");
                        String number = spacer(numberSet.getNext());
                        writer.append(number);
                    }
                    writer.write("|");
                }
                for(int x = 0; x < getSize(); x++){
                    writer.append(border);
                }
                writer.write("+");

            }
            catch (IOException e){
                System.out.println("An Error Occurred Writing to the File: " + file);
            }
        }
    }
    public String spacer(int num){
        if(num == 99999){
            return "FREE!";
        }
        String value = num + "";
        switch (value.length()){
            case 1: {
                return "  " + value + "  ";
            }
            case 2: {
                return " " + value + "  ";
            }
            case 3: {
                return " " + value + " ";
            }
            case 4: {
                return value + " ";
            }
            default: {
                return value;
            }
        }
    }
    public ArrayList<Integer> getNumbers(){
        return numberSet.getArray();
    }
}
