package Classes;


public class Player {
    private Stack Deck;
    private Hand PlayerHand;
    private String Name;
    

    Player(String Name, Stack Deck){
        this.Name = Name;
        this.Deck = Deck;

    }

    public void CreateHand(){
        // creates hand with 4 cards
        for(int i = 0; i < 4; i++){
            PlayerHand.addCard(Deck.randomCard());
        }

    }
    public void showHand(){
        PlayerHand.ShowList();
    }
}
