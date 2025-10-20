package com.example.Chatapp.model;


import com.example.Chatapp.DTO.StarDTO;
import jakarta.persistence.*;

@Entity
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount; //1-5

    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser; //Jahir

    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser; //viper

    public Star() {
    }

    public Star(int amount, User fromUser, User toUser) {
        this.amount = amount;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public Star(int id, int amount, User toUser, User fromUser) {
        this.id = id;
        this.amount = amount;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

}
