// Isaac Airmet Assn7 Acorn
// Create acorn class (subclass of Pattern)
public class PatternAcorn extends Pattern {
    // Create the pattern
    boolean[][] pattern = {
            {false, true, false, false, false, false, false},
            {false, false, false, true, false, false, false},
            {true, true, false, false, true, true, true},
    };
    // Methods to return dimensions and cell state
    @Override
    public int getSizeX(){
        return 7;
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
