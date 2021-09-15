// Isaac Airmet Assn7 Block
// Create block class (subclass of Pattern)
public class PatternBlock extends Pattern{
    // Create the pattern
    private boolean[][] pattern = {
            {true, true},
            {true, true},
    };
    // Methods to return dimensions and cell state
    @Override
    public int getSizeX(){
        return 2;
    }
    @Override
    public int getSizeY(){
        return 2;
    }
    @Override
    public boolean getCell(int col, int row){
        return pattern[row][col];
    }
}
