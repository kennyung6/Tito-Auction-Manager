package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageProducer;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

import dao.ItemDao;
import entity.Item;
import helpers.StarImageIcon;
import net.miginfocom.swing.MigLayout;
import helpers.DropShadowPanel;
import helpers.LevelBar;
import widget.Alert;

public  class DetailPanel extends JPanel{

    // Panels
    private JPanel imgPanel;
    private DropShadowPanel itemImage;
    private JPanel topPanel;
    private JPanel contentPanel;

    // Display Labels
    public JLabel endTimeLabel;
    public JLabel startBidLabel;
    private JLabel highBidLabel;
    public JLabel bidHisLabel;
    public JLabel maxBidLabel;

    // Value Labels
    public JLabel startTime;
    public JLabel endTime;
    public JLabel startBid;
    public JLabel highBid;
    public JLabel bidHis;
    private JFormattedTextField bidTextField;
    public JButton placeBidBtn;
    public JButton optOutBtn;


    private Item detailItem;
    private int itemID;



    public DetailPanel(int clickedItem) {
        this.itemID = clickedItem;
        getItemDetails();



        // Base Panel Background
        setBackground(Color.WHITE);
        add(detailLayout());
        revalidate();
        repaint();
    }

    JPanel detailLayout() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(p);
        p.setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).
                addGroup(layout.createSequentialGroup().
                        addComponent(itemImage(), 360 , 360 , 360).
                        addComponent(basePanel(),450,450,450)));

        layout.setVerticalGroup(layout.createSequentialGroup().
                addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(itemImage(), 290,290,290).
                                addComponent(basePanel(), 500, 500,500)));


        return p;
    }

    JPanel basePanel() {
        if (contentPanel == null) {

            initTopPanel();
            initGridPanel();

            // Split into Two Panels
            contentPanel = new JPanel();
            contentPanel.setLayout(new MigLayout());
            contentPanel.setBackground(Color.WHITE);

            contentPanel.add(topPanel, "cell 0 0");
            contentPanel.add(imgPanel, "cell 0 1");
        }
        return contentPanel;
    }

    private void initTopPanel () {
        // Top Panel
        topPanel = new JPanel(new MigLayout());
        topPanel.setBackground(Color.WHITE);

        // Item Name
        JLabel itemNameLbl = new JLabel();
        itemNameLbl.setText(detailItem.getItemName());
        System.out.println(detailItem.getItemName());
        itemNameLbl.setFont(new Font("Verdana",Font.BOLD, 16));
        itemNameLbl.setOpaque(true);
        itemNameLbl.setBackground(Color.WHITE);
        itemNameLbl.setBorder(new EmptyBorder(60,0,0,0));


        // Create rating Bar
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/images/31g.png"));
        ImageProducer ip = defaultIcon.getImage().getSource();
        List<ImageIcon> list;
        StarImageIcon starImageIcon = new StarImageIcon();
        ImageIcon star = starImageIcon.makeStarImageIcon(ip, 1f, 1f, 0f);
        list = Arrays.asList(star, star, star, star, star);
        LevelBar levelBar = new LevelBar(defaultIcon,list,1);
        levelBar.setBackground(Color.white);
        levelBar.setBorder(new EmptyBorder(5,0,0,450));


        // Availability Text
        JLabel availText = new JLabel();
        availText.setText("<html>Availability: <font color='blue'>In stock</font></html>");
        availText.setBorder(new EmptyBorder(5,0,0,0));

        // Description Text
        JTextArea descText = new JTextArea();
        descText.setLineWrap(true);
        descText.setColumns(34);
        descText.setEditable(false);
        descText.setText(detailItem.getDescription());
        descText.setBorder(new EmptyBorder(20,0,0,0));

        topPanel.add(itemNameLbl,  "cell 0 0");
        topPanel.add(levelBar, "cell 0 1");
        topPanel.add(availText, "cell 0 2");
        topPanel.add(descText, "cell 0 3");


    }

    private void initGridPanel () {
        // 4x4 GridLayout Panel
        imgPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        imgPanel.setBackground(Color.WHITE);
        imgPanel.add(rightView());

    }

    private DropShadowPanel itemImage() {
        if (itemImage == null) {
            itemImage = new DropShadowPanel(4);
            itemImage.setBackground(Color.WHITE);

            JPanel userView = new JPanel();
            userView.setBackground(new Color(240, 241,248));
            // Image View
            ImageIcon icon = new ImageIcon(detailItem.getImage());
            Image image = icon.getImage(); // transform it
            Image newImg = image.getScaledInstance(350, 280,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            icon = new ImageIcon(newImg);  // transform it back
            JLabel imgLabel = new JLabel(icon);

            // add to UserView
            userView.add(imgLabel);
            itemImage.add(userView);
        }

        return itemImage;
    }

    private JPanel rightView() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        // Labels
        endTimeLabel = new JLabel("Time Left");
        endTime = new JLabel();
        bidHisLabel = new JLabel("Bid History");
        bidHis = new JLabel();
        startBidLabel = new JLabel("Starting Bid");
        startBid = new JLabel();
        highBidLabel = new JLabel("Current High Bid");
        highBid = new JLabel();
        maxBidLabel = new JLabel("Your max bid");
        bidTextField = new JFormattedTextField();

        // Buttons
        placeBidBtn = new JButton("Place Bid");
        optOutBtn = new JButton();
        optOutBtn.setBackground(Color.RED);
        optOutBtn.setText("Opt Out");


        // Will print in UTC
        endTime.setText("(" + parseTime(detailItem.getEndTime()) + " UTC" + ")");
        bidHis.setText(detailItem.getBidHistory() + " Bids");
        startBid.setText("\u00A2" + detailItem.getStartBid());
        highBid.setText("\u00A2" + detailItem.getHighestBid());


        placeBidBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userBid = bidTextField.getText().trim();

                // Increment bidHis onBidPlaced Success
                int addBidHis = detailItem.getBidHistory();

                // Add a new bid history (+1) everytime a bid placement is successful
                addBidHis++;

                // Cast user input to an integer
                int userBidNum = Integer.parseInt(userBid);
                if (userBidNum > detailItem.getStartBid() && userBidNum > detailItem.getHighestBid()) {

                    // Set new Bid history
                    bidHis.setText(addBidHis + " Bids");
                    highBid.setText(String.valueOf(userBidNum));


                    // Update data on Database
                    updateUserMaxBid(userBidNum, addBidHis);

                    // Refresh Data
                    getItemDetails();


                    System.out.println("bidHisIncrement: " + addBidHis);
                    System.out.println("UserBid No: " + userBidNum);
                    System.out.println("Item ID : " + detailItem.getItemId());


                    Alert.showInformation(contentPanel, "Bid Placed Successfully");

                } else {
                    System.out.println("Your BID is low");
                    Alert.showError(contentPanel, "Bid is lower. Enter an higher amount");
                }
            }
        });

        // Delete Item
        optOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optOutBtnBid();
            }
        });



        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.linkSize(SwingConstants.HORIZONTAL, placeBidBtn);

        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER); // Will align the labels the way you wanted

        hGroup.addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(endTimeLabel)
                        .addComponent(bidHisLabel)
                        .addComponent(startBidLabel)
                        .addComponent(highBidLabel)
                        .addComponent(maxBidLabel))
                .addGroup(layout.createParallelGroup()
                        .addComponent(endTime)
                        .addComponent(bidHis)
                        .addComponent(startBid)
                        .addComponent(highBid)
                        .addComponent(bidTextField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(placeBidBtn)));
        hGroup.addComponent(optOutBtn);

        layout.setHorizontalGroup(hGroup);

        // Vertical
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(endTimeLabel)
                .addComponent(endTime));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(bidHisLabel)
                .addComponent(bidHis));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(startBidLabel)
                .addComponent(startBid));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(highBidLabel)
                .addComponent(highBid));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(maxBidLabel)
                .addComponent(bidTextField)
                .addComponent(placeBidBtn));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(optOutBtn));


        layout.setVerticalGroup(vGroup);


        return panel;
    }

    private String parseTime(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        try {
            Date date1 = format.parse(date.replace("T"," "));
            String d= new SimpleDateFormat("yyyy/dd/MM HH:mm:ss").format(date1);
            return d;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private void updateUserMaxBid(int userBidNum, int bidHis) {
        ItemDao itemDao = new ItemDao();
        Item item = new Item();
        item.setHighestBid(userBidNum);
        item.setBidHistory(bidHis);
        item.setItemId(detailItem.getItemId());

        itemDao.updateItemDetails(item);
    }

    private void optOutBtnBid() {
        ItemDao itemDao = new ItemDao();
        Item item = new Item();
        item.setHighestBid(0);
        item.setBidHistory(0);
        item.setItemId(detailItem.getItemId());
        itemDao.updateItemDetails(item);

        // Refresh View
        highBidLabel.setText("0");

    }

    private void getItemDetails() {
        ItemDao itemDao = new ItemDao();
        Item item = new Item();
        try {
            item = itemDao.getItemDetails(itemID);
            System.out.println("Description: " + item.getDescription());
            System.out.println("Name: " + item.getItemName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        detailItem = item;
    }

    static class myTask extends TimerTask{

        @Override
        public void run() {
        }
    }
}
