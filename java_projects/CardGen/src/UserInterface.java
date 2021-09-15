import java.util.Scanner;
public class UserInterface {
    int size;
    int maxNum;
    int numCards;
    Deck currentDeck;
    int highBound;
    int lowBound;
    boolean homeKeepGoing = true;
    boolean menuKeepGoing = true;
    UserInterface(){
        size = 0;
        maxNum = 0;
        numCards = 0;

    }

    public void run(){
        System.out.println("Welcome to the Bingo Deck Generator!");
        Menu menu = new Menu("Main");
        menu.addOption("C", "Create a new deck");
        while(homeKeepGoing){
            String command = menu.show();
            if(command.equals("C")){
                createDeck();
            }
            else if(command.equals("X")){
                homeKeepGoing = false;
            }
        }

    }
    private void createDeck(){
        size = getNumberInput("Enter card size", 3, 15);
        lowBound = 2 * size * size;
        highBound = 2 * lowBound;
        maxNum = getNumberInput("Enter max number", lowBound, highBound);
        numCards = getNumberInput("Enter number of cards", 3, 10000);
        currentDeck = new Deck(size, numCards, maxNum);
        deckMenu();
    }
    private void deckMenu(){
        Menu menu = new Menu("Deck");
        menu.addOption("P", "Print a card to the screen");
        menu.addOption("D", "Display the whole deck to the screen");
        menu.addOption("S", "Save the whole deck to a file");
        menu.addOption("G", "Play the game");

        while (menuKeepGoing){
            String command = menu.show();
            if(command.equals("P")){
                printCard();
            }
            else if(command.equals("D")){
                System.out.println();
                currentDeck.print(null, -1);
            }
            else if(command.equals("S")){
                saveDeck();
            }
            else if(command.equals("X")){
                menuKeepGoing = false;
            }
            else if(command.equals("G")){
                menuKeepGoing = false;
                System.out.println("Lets play the game!");
                System.out.println("Please select your card after reviewing the options.");
                currentDeck.print(null, -1);
                int cardNum = getNumberInput("Select your card", 1, numCards);
                System.out.println("You have selected card " + cardNum + ".");
                Card computerCard = compCard(cardNum);
                new Game(currentDeck.getCard(cardNum), computerCard, maxNum);
            }
        }
    }
    private Card compCard(int cardNum){
        if(cardNum == 1){
            return currentDeck.getCard(numCards);
        } else{
            return currentDeck.getCard(cardNum - 1);
        }
    }
    private void printCard(){
        int cardToPrint = getNumberInput("ID of card to print", 1, currentDeck.getCardCount());
        System.out.println();
        currentDeck.print(null, cardToPrint);
    }
    private void saveDeck(){
        String fileName = getStringInput("Enter output file name");
        currentDeck.print(fileName, -1);
        System.out.println("Done!");
    }
    private int getNumberInput(String phrase, int min, int max){
        boolean repeat = true;
        while(repeat){
            System.out.println("\n" + phrase + "[" + min + " - " + max + "]");
            Scanner response = new Scanner(System.in);
            try {
                int result = response.nextInt();
                if(result >= min && result <= max){
                    return result;
                }
                else{
                    throw new Exception();
                }
            }
            catch (Exception e){
                System.out.println("Please enter a number in the range [" + min + " - " + max + "]");
            }

        }
        return -1;
    }
    private String getStringInput(String phrase){
        boolean repeat = true;
        while(repeat){
            System.out.println("\n" + phrase);
            Scanner response = new Scanner(System.in);
            try{
                String result = response.next();
                result = result.trim();
                if(!result.isEmpty()){
                    return result;
                }
                else{
                    throw new Exception();
                }
            }
            catch (Exception e){
                System.out.println("Please enter a non-blank string");
            }
        }
        return "Void";
    }
}
