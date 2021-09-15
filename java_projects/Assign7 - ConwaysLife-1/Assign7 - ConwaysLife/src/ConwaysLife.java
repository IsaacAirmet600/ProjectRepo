// Isaac Airmet Assn7 ConwaysLife
// Reference for Lanterna 3: https://github.com/mabe02/lanterna/blob/master/docs/contents.md
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

// Create class
public class ConwaysLife {
    // Create main method
    public static void main(String[] args) {
        // Try....
        try {
            // General setup
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();

            TerminalSize size = screen.getTerminalSize();
            // Create instance of LifeSimulator and insert patterns
            LifeSimulator simulation = new LifeSimulator(size.getColumns(), size.getRows());
            simulation.insertPattern(new PatternBlinker(), 5, 10);
            simulation.insertPattern(new PatternGlider(), 12, 17);
            simulation.insertPattern(new PatternAcorn(), 70, 12);
            simulation.insertPattern(new PatternAcorn(), 30, 12);
            simulation.insertPattern(new PatternBlock(), 5, 17);
            simulation.insertPattern(new PatternGlider(), 0, 4);

            screen.startScreen();
            screen.setCursorPosition(null);

            // Run through the animations
            for (int i = 0; i < 145; i ++) {
                render(simulation, screen, graphics);   // Render the current state of the simulation
                Thread.yield();                         // Let the JVM have some time to update other things
                Thread.sleep(100);                // Sleep for a bit to make for a nicer paced animation
                simulation.update();                    // Tell the simulation to update
            }

            screen.stopScreen();
            // Catch block
        } catch (Exception ex) {
            System.out.println("Something bad happened: " + ex.getMessage());
        }
    }
    // Render method
    public static void render(LifeSimulator simulation, Screen screen, TextGraphics graphics){
        screen.clear();
        // Set X based on true or false for each position
        for (int i = 0; i < simulation.getSizeX(); i ++){
            for (int j = 0; j < simulation.getSizeY(); j ++){
                if (simulation.getCell(i, j)){
                    graphics.setCharacter(i, j, 'X');
                }
            }
        }
        // Try and catch blocks for refresh
        try{
            screen.refresh();
        } catch(Exception ex){
        }
    }
}
