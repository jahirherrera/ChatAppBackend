package com.example.Chatapp.DTO;

import com.example.Chatapp.model.Star;

public class StarDTO {

    private int amount;
    private String usernameFrom;
    private String usernameTo;

    public StarDTO() {
    }

    public StarDTO(int amount, String usernameFrom, String usernameTo) {
        this.amount = amount;
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
    }

    public StarDTO(Star star){
        this.amount = star.getAmount();
        this.usernameFrom = star.getFromUser().getUsername();
        this.usernameTo = star.getToUser().getUsername();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    @Override
    public String toString() {
        return "StarDTO{" +
                "amount=" + amount +
                ", usernameFrom='" + usernameFrom + '\'' +
                ", usernameTo='" + usernameTo + '\'' +
                '}';
    }
}
