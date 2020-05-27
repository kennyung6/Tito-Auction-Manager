package dbhelper;

import java.sql.Timestamp;

public class User {

    private int id;
    private String name;

    private int bid;
    private byte[] Image;

    public User(){}

    public User(int Id, String name, int bid, byte[] image){

        this.id = Id;
        this.name = name;
        this.bid = bid;
        this.Image = image;

    }


    public void setImage(byte[] image) {
        Image = image;
    }

    public int getId(){
        return id;
    }

    public void setId(int Id){
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage(){
        return Image;
    }



}