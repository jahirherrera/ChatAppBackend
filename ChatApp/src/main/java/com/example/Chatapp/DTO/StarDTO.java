package com.example.Chatapp.DTO;

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


}
