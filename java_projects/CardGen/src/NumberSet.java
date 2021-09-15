import java.util.ArrayList;
import java.util.Collections;

public class NumberSet {
    int maxVal;
    int size;
    ArrayList<Integer> numList = new ArrayList<>();
    ArrayList<Integer> tempList = new ArrayList<>();
    int indexCounter;
    NumberSet(int size, int maxVal){
        this.maxVal = maxVal;
        this.size = size * size;
        this.indexCounter = size * size;
        buildList();
    }
    private void buildList(){
        int count = 1;
        for(int i = 0; i < maxVal; i++){
            tempList.add(count);
            count += 1;
        }
        randomize();
        for(int i = 0; i <= size; i++){
            numList.add(tempList.get(i));
        }
        checkFree();
    }
    private void checkFree(){
        if(size % 2 != 0){
            int index = (numList.size() - 1) / 2;
            numList.add(index, 99999);
        }
    }
    public int getSize(){
        return size;
    }
    public int get(int index){
        return numList.get(index);
    }
    public void randomize(){
        Collections.shuffle(tempList);
    }
    public int getNext(){
        if(indexCounter < 1){
            indexCounter = size;
        }
        indexCounter -= 1;
        return numList.get(indexCounter);
    }
    public ArrayList<Integer> getArray(){
        return numList;
    }
}
