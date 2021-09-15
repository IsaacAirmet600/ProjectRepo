// Isaac Airmet Assn7 Blinker
// Create blinker class (subclass of Pattern)
public class PatternBlinker extends Pattern {
    // Create the pattern
    boolean[][] pattern = {
            {false, false, false},
            {true, true, true},
            {false, false, false},
    };
    // Methods to return dimensions and cell state
    @Override
    protected int getSizeX(){
        return 3;
    }
    @Override
    public int getSizeY(){
        return 3;
    }
    @Override
    public boolean getCell(int col, int row){
        return pattern[row][col];
        }
    }

