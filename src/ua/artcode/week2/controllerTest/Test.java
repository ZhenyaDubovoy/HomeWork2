package ua.artcode.week2.controllerTest;

import ua.artcode.week2.controller.MainControllerImpl;
import ua.artcode.week2.db.AppDB;
import ua.artcode.week2.model.Card;
import ua.artcode.week2.model.CardGroup;

/**
 * Created by Евгений Дубовой on 13.09.2017.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Test creating group " + createGroupTest());
        System.out.println("Test adding card to group " + addingCardTest());
        System.out.println("Test removing card from concrete group and from all groups " + removingCardTest());
        System.out.println("Test getting all groups " + testGetGroups());
        System.out.println("Test getting all cards of group" + testGetCardsOfGroup());

    }

    public static boolean createGroupTest(){
        AppDB appDB = new AppDB();
        MainControllerImpl controller = new MainControllerImpl(appDB);

        CardGroup group = new CardGroup(1);

        boolean res = controller.createGroup(group);

        return res && appDB.getCardGroups().get(0) == group;
    }

    public static boolean addingCardTest() {
        AppDB appDB = new AppDB();
        MainControllerImpl controller = new MainControllerImpl(appDB);

        controller.createGroup(new CardGroup(1));

        Card card = new Card(1, "Sport", "Some action with concrete rules, connected with physical activity");

        boolean res = controller.addCard(card, 1);

        return res && appDB.getCardGroups().get(0).getCards().get(0) == card;

    }

    public static boolean removingCardTest(){
        AppDB appDB = new AppDB();
        MainControllerImpl controller = new MainControllerImpl(appDB);

        controller.createGroup(new CardGroup(1));
        controller.createGroup(new CardGroup(2));

        Card card1 = new Card(1,"word1", "description1");
        Card card2 = new Card(2,"word2", "description2");

        controller.addCard(card1, 1);
        controller.addCard(card2, 1);
        controller.addCard(card1, 2);
        controller.addCard(card2, 2);

        controller.removeCard(2, 1);
        boolean res1 = !controller.getAppDB().getCardGroups().get(0).getCards().contains(card2);

        controller.addCard(card2, 1);

        controller.removeCard(1);

        return res1 && !controller.getAppDB().getCardGroups().get(0).getCards().contains(card1) && !controller.getAppDB().getCardGroups().get(1).getCards().contains(card1);

    }

    public static boolean testGetGroups(){
        AppDB appDB = new AppDB();
        MainControllerImpl controller = new MainControllerImpl(appDB);

        controller.createGroup(new CardGroup(1));
        controller.createGroup(new CardGroup(2));

        CardGroup groups [];
        groups = controller.getAllGroups();

        if (groups[0].getId() == 1 && groups[1].getId()==2 && groups.length == 2){
            return true;
        }
        else return false;
    }

    public static boolean testGetCardsOfGroup(){
        AppDB appDB = new AppDB();
        MainControllerImpl controller = new MainControllerImpl(appDB);

        controller.createGroup(new CardGroup(1));

        Card card1 = new Card(1,"word1", "description1");
        Card card2 = new Card(2,"word2", "description2");

        controller.addCard(card1, 1);
        controller.addCard(card2, 1);

        Card[] cards = controller.getCards(1);

        return cards[0].getId() == 1 && cards[1].getId() == 2 && cards.length == 2;
    }
}
