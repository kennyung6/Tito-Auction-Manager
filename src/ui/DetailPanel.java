package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import dao.ItemDao;
import database.ConnectionFactory;
import database.DbUtil;
import entity.Item;
import net.miginfocom.swing.MigLayout;
import helpers.DropShadowPanel;
import helpers.LevelBar;
import helpers.SelectedImageFilter;
import widget.Alert;

public  class DetailPanel extends JPanel{

    JPanel imgPanel;
    DropShadowPanel itemImage;
    JPanel topPanel;
    static JPanel contentPanel;

    JLabel timeLeftLabel;
    JLabel timeLeft;

    JLabel bidHistoryLabel;
    JLabel bidHistory;

    JLabel startBidLabel;
    JLabel startBid;

    JLabel startingBidLabel;
    JLabel lowBid;



    JLabel maxBid;
    JFormattedTextField bidTextField;
    JButton placeBidButton;
    JButton optOut;

    JLabel lowBidLabel;

    private int itemID;
    private static Item detailItem;

    private String flag = "start";
    private String flag2 = "noReset";
    private Timer timer;
    private int hour;
    private int minute;
    private int second;
    private int totalSeconds;
    private int totalSecondsCopy;
    private String originTime;

    private SimpleDateFormat sdf;
    private String inputData1;
    private String inputData2;

    int userBidNum;
    String userBid;

    public DetailPanel(int clickedItem) {
        this.itemID = clickedItem;
        initItemData(clickedItem);
        // Base Panel Background
        setBackground(Color.WHITE);
        add(detailLayout());
        revalidate();
        repaint();
    }

    private void initItemData(int itemID) {
        String query = "SELECT * FROM tbl_items WHERE it_id=" + itemID;
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Item item = new Item();
				/*Retrieve one auction item and store it in auction object*/
                item.setItemId(rs.getInt("it_id"));
                item.setItemName(rs.getString("it_name"));
                item.setTimestamp(rs.getString("it_timeout"));
                item.setLowBid(rs.getInt("it_lowbid"));
                item.setStartBid(rs.getInt("it_startbid"));
                item.setBidHistory(rs.getInt("it_bidhistory"));
                item.setImage(rs.getBytes("it_image"));
                this.detailItem = item;


            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
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

    public void initTopPanel () {
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
        ImageIcon star = makeStarImageIcon(ip, 1f, 1f, 0f);
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
        descText.setText("Golden watch was discovered during world war\ntwo when soldiers when agitated by the drones");
        descText.setBorder(new EmptyBorder(20,0,0,0));
        descText.setEditable(false);


        topPanel.add(itemNameLbl,  "cell 0 0");
        topPanel.add(levelBar, "cell 0 1");
        topPanel.add(availText, "cell 0 2");
        topPanel.add(descText, "cell 0 3");


    }

    public void initGridPanel () {

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

    JPanel rightView() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        timeLeftLabel = new JLabel("Time Left");
        bidHistoryLabel = new JLabel("Bid History");
        startBidLabel = new JLabel("Starting Bid");
        startingBidLabel = new JLabel("Current High Bid");
        maxBid = new JLabel("Your max bid");

        timeLeft = new JLabel();
        bidHistory = new JLabel();
        startBid = new JLabel();
        lowBid = new JLabel();
        bidTextField = new JFormattedTextField();
        optOut = new JButton();

        optOut.setBackground(Color.RED);
        optOut.setText("Opt Out");



        // Will print in UTC
        timeLeft.setText("(" + parseTime(detailItem.getTimestamp()) + " UTC" + ")");
        bidHistory.setText(detailItem.getBidHistory() + " Bids");
        startBid.setText("\u00A2" + detailItem.getStartBid());

        lowBid.setText("\u00A2" + detailItem.getLowBid());
        placeBidButton = new JButton("Place Bid");

        lowBidLabel = new JLabel("Enter " + detailItem.getLowBid() + " or more");



        placeBidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userBid = bidTextField.getText().trim();

                // Increment BidHistory onBidPlaced Success
                int addBidHis = detailItem.getBidHistory();

                // Add a new bid history (+1) everytime a bid placement is successful
                addBidHis++;

                // Cast user input to an integer
                int userBidNum = Integer.parseInt(userBid);
                if (userBidNum > detailItem.getStartBid()) {

                    // Set new Bid history
                    bidHistory.setText(addBidHis + " Bids");
                    lowBid.setText(String.valueOf(userBidNum));


                    // Update data on Database
                    updateUserMaxBid(userBidNum, addBidHis);

                    // Refresh Data
                    initItemData(detailItem.getItemId());


                    System.out.println("BidHistoryIncrement: " + addBidHis);
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
        optOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optOutBid();
            }
        });



        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.linkSize(SwingConstants.HORIZONTAL, placeBidButton);

        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER); // Will align the labels the way you wanted

        hGroup.addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(timeLeftLabel)
                        .addComponent(bidHistoryLabel)
                        .addComponent(startBidLabel)
                        .addComponent(startingBidLabel)
                        .addComponent(maxBid))
                .addGroup(layout.createParallelGroup()
                        .addComponent(timeLeft)
                        .addComponent(bidHistory)
                        .addComponent(startBid)
                        .addComponent(lowBid)
                        .addComponent(bidTextField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(placeBidButton)));
        hGroup.addComponent(optOut);

        layout.setHorizontalGroup(hGroup);

        // Vertical
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(timeLeftLabel)
                .addComponent(timeLeft));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(bidHistoryLabel)
                .addComponent(bidHistory));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(startBidLabel)
                .addComponent(startBid));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(startingBidLabel)
                .addComponent(lowBid));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(maxBid)
                .addComponent(bidTextField)
                .addComponent(placeBidButton));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(optOut));

        layout.setVerticalGroup(vGroup);


        return panel;
    }


    public String parseTime(String date){
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

    public void updateUserMaxBid(int userBidNum, int bidHis) {
        ItemDao itemDao = new ItemDao();
        Item item = new Item();
        item.setLowBid(userBidNum);
        item.setBidHistory(bidHis);
        item.setItemId(detailItem.getItemId());

        itemDao.update(item);


    }

    public void optOutBid() {
        ItemDao itemDao = new ItemDao();
        Item item = new Item();
        item.setLowBid(0);
        item.setBidHistory(0);
        item.setItemId(detailItem.getItemId());
        itemDao.update(item);

        // Refresh View
        lowBid.setText("0");

    }

    public void countDownTimer() {
        sdf = new SimpleDateFormat("HH:mm:ss");


        try {
            Date startTime;
            Date basicTime = sdf.parse("00:00:00");


                startTime = sdf.parse(parseTime(detailItem.getTimestamp()));
                totalSeconds = (int) ((startTime.getTime()-basicTime.getTime())/1000);
                totalSecondsCopy = totalSeconds;

            originTime = inputData1;
            timer = new Timer();
            timer.schedule(new myTask(){
                @Override
                public void run(){
                    if(totalSeconds > 0){
                            totalSeconds--;
                            hour = totalSeconds/3600;
                            minute = (totalSeconds - 3600*hour)/60;
                            second = totalSeconds - 3600*hour - 60*minute;
                        }

                }
            }, 0, 1000);
        }catch(ParseException e){
            e.printStackTrace();
        }
    }


   /* //Use general internal classes to monitor events generated by each event source, such as (button1, button2)
    public static class Button1Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(contentPanel,"Button 1 Be clicked");
            DetailPanel detailPanel = new DetailPanel(detailItem.getItemId());
            detailPanel.setVisible(false);

        }
    }*/

    private static ImageIcon makeStarImageIcon(ImageProducer ip, float rf, float gf, float bf) {
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(ip, new SelectedImageFilter(rf, gf, bf))));
    }

    class myTask extends TimerTask{

        @Override
        public void run() {
        }
    }
}
