package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

/**
 * Created by Daniel on 12/1/2017.
 */

public class Bale {
    private int id;
    private String user;
    private String date;
    private String type;
    private double weight;

    public Bale(int id, String user, String date, String type, double weight) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.type = type;
        this.weight = weight;
    }

    public Bale(String user, String date, String type, double weight) {
        this.user = user;
        this.date = date;
        this.type = type;
        this.weight = weight;
    }

    public Bale() {
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Bale{" +
                "user='" + user + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", weight=" + weight +
                '}';
    }
}
