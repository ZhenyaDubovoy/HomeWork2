package ua.artcode.week2.controller;

import ua.artcode.week2.db.AppDB;
import ua.artcode.week2.model.Card;
import ua.artcode.week2.model.CardGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Serhii Bilobrov
 * @since 1.7
 */
public class MainControllerImpl implements MainController {

    private AppDB appDB;

    public AppDB getAppDB() {
        return appDB;
    }

    public MainControllerImpl(AppDB appDB) {
        this.appDB = appDB;
    }

    @Override
    public boolean createGroup(CardGroup group) {
        return appDB.create(group) != null;
    }

    @Override
    public boolean addCard(Card card, int groupId) {
        CardGroup one = appDB.findOne(groupId);

        if(one == null){
            System.out.println("Group was not found");
            return false;
        }

        return one.getCards().add(card);
    }

    @Override
    public Card removeCard(int cardId) {
        Card result = null;
        int indexCardToRemove=-1;
        //
        for (CardGroup group: appDB.getCardGroups()) {
            for (Card card: group.getCards()) {
                if (card.getId()==cardId) {
                    indexCardToRemove = group.getCards().indexOf(card);
                }
            }
            if(indexCardToRemove!=-1) {
                result = group.getCards().remove(indexCardToRemove);
            }
        }

        return result; //returning null, if not deleted, or deleted card
    }

    @Override
    public Card removeCard(int cardId, int groupId) {
        CardGroup needed = null;

        for (CardGroup group: appDB.getCardGroups()) {
            if (group.getId()==groupId){
                needed = group;
            }
        }

        if(needed == null){
            System.out.println("Group not found");
            return null;
        }

        for (Card card: needed.getCards()) {
            if (card.getId()==cardId){
                needed.getCards().remove(card);
                return card;
            }
        }
        System.out.println("card not found in group");
        return null;
    }

    @Override
    public CardGroup[] getAllGroups() {
        CardGroup[] result = new CardGroup[appDB.getCardGroups().size()];

        for (int i = 0; i < appDB.getCardGroups().size(); i++) {
            result[i] = appDB.getCardGroups().get(i);
        }

        return result;
    }

    @Override
    public Card[] getCards(int groupId) {
        CardGroup targetGroup = null;

        for (CardGroup group: appDB.getCardGroups()) {
            if (group.getId() == groupId){
                targetGroup = group;
            }
        } //finding group by id

        if (targetGroup == null){
            System.out.println("Group not found");
            return null;
        }

        Card[] result = new Card[targetGroup.getCards().size()];

        for (int i = 0; i < targetGroup.getCards().size(); i++) {
            result[i] = targetGroup.getCards().get(i);
        }

        return result;

    }
}
