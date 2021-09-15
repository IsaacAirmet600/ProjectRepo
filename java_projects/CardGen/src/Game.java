import java.util.ArrayList;
import java.util.Random;
public class Game {
    Card card;
    Card compCard;
    int maxNum;
    ArrayList<Integer> userList;
    ArrayList<Integer> compList;
    Game(Card card, Card compCard, int maxNum){
        this.card = card;
        this.compCard = compCard;
        this.maxNum = maxNum;
        userList = this.card.getNumbers();
        compList = this.card.getNumbers();
        gameExplanation();
    }
    public void gameExplanation(){
        System.out.println("The following is a brief explanation of the game and it's rules:\n");
        System.out.println("For this game, random numbers will be generated with respect to the ranges of your card." +
        " If the number matches your card, the value on your card will disappear. Get a full empty row/column before the " +
        "computer and you win!");
        runGame();
    }
    public void runGame(){
        boolean noWin = true;
        int roundCount = 1;
        while(noWin){
            System.out.println("Round " + roundCount);
            int number = generateNum();
            for(Integer value: userList){
                if (value == number){
                    userList.add(userList.indexOf(value), -1);
                }
            }
            for(Integer value: compList){
                if (value == number){
                    compList.add(compList.indexOf(value), -1);
                }
            }
            noWin = checkWin();

        }
    }
    private int generateNum(){
        Random randInt = new Random();
        return randInt.nextInt(maxNum);
    }
    private void printCards(){
        System.out.println("Computer's card");
        card.print(null);
        System.out.println();
        System.out.println("User's card");
        compCard.print(null);
    }
    private boolean checkWin(){
        int cardSize = userList.size();
        int cardWidth = (int)Math.sqrt(cardSize);
        int userCorrect = 0;
        int compCorrect = 0;
        for(Integer value: userList){
            if (value == -1){
                userCorrect += 1;
                if(userList.indexOf(value) % cardWidth == 0){
                    if(userCorrect == 4){
                        System.out.println("You won!!");
                        return true;
                    }
                    else userCorrect = 0;
                }
            }
        }
        for(Integer value: compList){
            if (value == -1){
                compCorrect += 1;
                if(compList.indexOf(value) % cardWidth == 0){
                    if(compCorrect == 4){
                        System.out.println("You won!!");
                        return true;
                    }
                    else compCorrect = 0;
                }
            }
        }
        return false;
    }
}
