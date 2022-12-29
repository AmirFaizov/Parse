package ru.kpfu.itis.entity;

import lombok.Builder;

@Builder
public class Toy {
    private Integer id;
    private String toyName;
    private String discription;
    private Integer price;
    private Integer userID;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Toy(Integer id, String toyName, String discription, Integer price, Integer userID) {
        this.id = id;
        this.toyName = toyName;
        this.discription = discription;
        this.price = price;
        this.userID = userID;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Toy(Integer id, Integer price, String toyName) {
        this.id = id;
        this.price = price;
        this.toyName = toyName;
    }
    public Toy(Integer price, String toyName, String discription) {
        this.price = price;
        this.toyName = toyName;
        this.discription = discription;
    }
    public Toy(Integer price, String toyName) {
        this.price = price;
        this.toyName = toyName;
    }
    public Toy(Integer id, Integer price, String toyName, String discription) {
        this.id=id;
        this.price = price;
        this.toyName = toyName;
        this.discription = discription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Toy toy = (Toy) o;

        if (!id.equals(toy.id)) return false;
        if (!price.equals(toy.price)) return false;
        return toyName.equals(toy.toyName);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + toyName.hashCode();
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }
}
