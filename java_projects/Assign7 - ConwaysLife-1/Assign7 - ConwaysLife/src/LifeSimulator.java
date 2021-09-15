// Isaac Airmet Assn7 LifeSimulator
// Create class
public class LifeSimulator {
    // Data fields
    boolean[][] original;
    int sizeX;
    int sizeY;
    // Constructor
    public LifeSimulator(int sizeX, int sizeY){
        // Set sizes
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        // Multidimensional array (original). Fill by default as false
        original = new boolean[sizeX][sizeY];
        for (int i = 0; i < sizeX; i ++){
            for (int j = 0; j < sizeY; j ++){
                original[i][j] = false;
            }
        }
    }
    // Getter method for sizeX
    public int getSizeX(){
        return original.length;
    }
    // Getter method for sizeY
    public int getSizeY(){
        return original[0].length;
    }
    // Getter method for getCell
    public boolean getCell(int x, int y){
        return original[x][y];
    }
    // Method to insert patterns
    public void insertPattern(Pattern pattern, int startX, int startY){
        // For the size of the pattern...
        for (int i = 0; i < pattern.getSizeX(); i ++){
            for (int j = 0; j < pattern.getSizeY(); j ++){
                // Get the current position needing filed, and set position value if accessible
                int posX = startX + i;
                int posY = startY + j;
                if (posX < sizeX && posY < sizeY && posY >=0 && posX >= 0){
                    original[posX][posY] = pattern.getCell(i, j);
                }
            }
        }
    }
    // Update method
    public void update(){
        // Create new array based on original array
        boolean[][] updated = new boolean[sizeX][sizeY];
        for (int i = 0; i < sizeX; i ++){
            for (int j = 0; j < sizeY; j ++){
                // Check how many neighbors there are
                int aliveCount = checkNeighbors(i, j);
                // Make position true or false based results
                if (aliveCount >= 2 && aliveCount <= 3 && original[i][j]){
                    updated[i][j] = true;
                } else {
                    updated[i][j] = aliveCount == 3 && !original[i][j];
                }
            }
        }
        // Set original array to the reference of the updated one
        original = updated;
    }
    // Method to check for neighbors
    private int checkNeighbors(int x, int y){
        // Create a counter
        int count = 0;
        // For each position, check if point inside boundary and if cell is alive or dead
        if (y - 1 >= 0 && x - 1 >= 0 && original[x - 1][y - 1]){
            count ++;
        }
        if (y - 1 >= 0 && original[x][y-1]){
            count ++;
        }
        if (y - 1 >= 0 && x + 1 < sizeX && original[x + 1][y - 1]){
            count ++;
        }
        if (x - 1 >= 0 && original[x - 1][y]){
            count ++;
        }
        if (x + 1 < sizeX && original[x + 1][y]){
            count ++;
        }
        if (y + 1 < sizeY && x - 1 >= 0 && original[x - 1][y + 1]){
            count ++;
        }
        if (y + 1 < sizeY && original[x][y + 1]){
            count ++;
        }
        if (y + 1 < sizeY && x + 1 < sizeX && original[x + 1][y + 1]){
            count ++;
        }
        // Return number of neighbors
        return count;
    }
}
