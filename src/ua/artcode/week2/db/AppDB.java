package ua.artcode.week2.db;

import ua.artcode.week2.model.Card;
import ua.artcode.week2.model.CardGroup;

import java.util.ArrayList;

/**
 * @author Serhii Bilobrov
 * @since 1.7
 */
// dao pattern?
public class AppDB implements GroupDao {

    private static int count = 0;

    private ArrayList<CardGroup> cardGroups;

    public AppDB() {
        cardGroups = new ArrayList<>();
    }

    public ArrayList<CardGroup> getCardGroups() {
        return cardGroups;
    }

    public void setCardGroups(ArrayList<CardGroup> cardGroups) {
        this.cardGroups = cardGroups;
    }

    @Override
    public CardGroup create(CardGroup cardGroup) {
        //cardGroup.setId(generateId());
        cardGroups.add(cardGroup);
        return cardGroup;
    }

    private int generateId() {
        return count++;
    }

    @Override
    public CardGroup findOne(int id) {

        // or for-each
        for (int i = 0; i < cardGroups.size(); i++) {
            CardGroup current = cardGroups.get(i);
            if(current.getId() == id){
                return current;
            }
        }

        return null;
    }

    @Override
    public CardGroup update(CardGroup newCardGroup, int id) {
        for (int i = 0; i < cardGroups.size(); i++) {
            CardGroup current = cardGroups.get(i);
            if(current.getId() == id){
                return cardGroups.set(i, newCardGroup);
            }
        }
        return null;
    }

    @Override
    public CardGroup delete(int id) {
        for (int i = 0; i < cardGroups.size(); i++) {
            CardGroup current = cardGroups.get(i);
            if(current.getId() == id){
                return cardGroups.remove(i);
            }
        }
        return null;
    }
}
