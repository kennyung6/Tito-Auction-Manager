package dbhelper;

import java.awt.*;
import java.sql.Timestamp;

public class AuctionItem {

    private int id;
    private String name;
    private Timestamp timeout;
    private int lowBid;
    private int bidHistory;
    private byte[] Image;

    public AuctionItem(){}

    public AuctionItem(int Id, String name, Timestamp timeout, int lowBid, int bidHistory, byte[] image){

        this.id = Id;
        this.name = name;
        this.timeout = timeout;
        this.lowBid = lowBid;
        this.bidHistory = bidHistory;
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

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTimeout() {
        return timeout;
    }

    public void setTimeout(Timestamp timeout) {
        this.timeout = timeout;
    }

    public int getLowBid() {
        return lowBid;
    }

    public void setLowBid(int lowBid) {
        this.lowBid = lowBid;
    }

    public int getBidHistory() {
        return bidHistory;
    }

    public void setBidHistory(int bidHistory) {
        this.bidHistory = bidHistory;
    }


    public byte[] getImage(){
        return Image;
    }



}