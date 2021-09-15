// Isaac Airmet Assn7 Glider
// Create glider class (subclass of Pattern)
public class PatternGlider extends Pattern {
    // Create the pattern
    boolean[][] pattern = {
            {true, false, false},
            {false, true, true},
            {true, true, false},
    };
    // Methods to return dimensions and cell state
    @Override
    public int getSizeX(){
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
