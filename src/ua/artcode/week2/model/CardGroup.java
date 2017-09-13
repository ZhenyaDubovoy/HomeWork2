package ua.artcode.week2.model;


import java.util.ArrayList;

/**
 * @author Serhii Bilobrov
 * @since 1.7
 */
public class CardGroup {

    private int id;
    private ArrayList<Card> cards;

    public CardGroup(int id) {
        this.id = id;
        cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
