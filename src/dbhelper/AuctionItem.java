package dbhelper;

public class AuctionItem {

    private int id;


    private String imgName;
    private int qte;
    private String price;
    private int catId;
    private byte[] Image;

    public AuctionItem(){}

    public AuctionItem(int Id, String name, byte[] image){

        this.id = Id;
        this.imgName = name;
        this.Image = image;

    }


    public int getID(){
        return id;
    }

    public void setID(int ID){
        this.id = ID;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String name) {
        this.imgName = name;
    }


    public byte[] getMyImage(){
        return Image;
    }
}