package ua.artcode.week2.model;

/**
 * @author Serhii Bilobrov
 * @since 1.7
 */
public class Card {

    private int id;
    private String front;
    private String back;

    public Card() {
    }

    public Card(int id, String front, String back) {
        this.id = id;
        this.front = front;
        this.back = back;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this){
            return true;
        }
        if (obj.getClass()!= Card.class){
            return false;
        }
        Card otherCard = (Card) obj;
        if (otherCard.id == id && otherCard.front == front && otherCard.back == back){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", front='" + front + '\'' +
                ", back='" + back + '\'' +
                '}';
    }
}
