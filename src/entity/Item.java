package entity;

public class Item {


    public void setItemStatus(int itemStatus) {
        this.itemStatus = itemStatus;
    }

    private int itemStatus;

    // Item Bean

    private int itemId;
    private String itemName;


    private String description;
    private String startTime;
    private String endTime;
    private int startBid;
    private int highestBid;
    private int bidHistory;
    private byte[] image;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    public int getStartBid() {
        return startBid;
    }

    public void setStartBid(int startBid) {
        this.startBid = startBid;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
