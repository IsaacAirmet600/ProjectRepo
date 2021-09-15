import java.util.ArrayList;

public class Deck {
    int cardCount;
    int cardSize;
    int numberMax;
    ArrayList<Card> cards = new ArrayList<>();
    Deck(int cardSize, int cardCount, int numberMax){
        this.cardCount = cardCount;
        this.cardSize = cardSize;
        this.numberMax = numberMax;
        createCards();
    }
    private void createCards(){
        int idNum = 1;
        for(int card = 0; card < cardCount; card ++){
            cards.add(new Card(idNum, cardSize, new NumberSet(cardSize, numberMax)));
            idNum += 1;
        }
    }
    public int getCardCount(){
        return cardCount;
    }
    public Card getCard(int n){
        Card card = null;
        n -= 1;
        if(0 <= n && n < getCardCount()){
            card = cards.get(n);
        }
        return card;
    }
    // FIX FILES
    public void print(String file, int idx){
        if(idx == -1){
            for(int i = 1; i <= cardCount; i++){
                getCard(i).print(file);
            }
        }
        else{
            getCard(idx).print(file);
        }
    }
}
