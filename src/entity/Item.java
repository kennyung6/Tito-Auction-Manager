package entity;

public class Item {


    private String itemName;
    private String timestamp;
    private int lowBid;
    private int bidHistory;
    private byte[] image;


    private int startBid;


    private int itemId;

    public Item() {

    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getStartBid() {
        return startBid;
    }

    public void setStartBid(int startBid) {
        this.startBid = startBid;
    }

    // String



}
