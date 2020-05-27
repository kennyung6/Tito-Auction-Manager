import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.List;

import dbhelper.AuctionItem;
import dbhelper.DbQuery;
import dbhelper.User;
import net.miginfocom.swing.MigLayout;
import util.DropShadowPanel;
import util.HoverButton;
import util.LevelBar;
import util.SelectedImageFilter;

public class ItemDetail extends JFrame{

    JPanel imgPanel;
    DropShadowPanel imgMetaData;
    JPanel topPanel;
    JPanel contentPanel;

    JLabel timeLeftLabel;
    JLabel timeLeft;

    JLabel bidHistoryLabel;
    JLabel bidHistory;

    JLabel startingBidLabel;
    JLabel startingBid;

    JLabel maxBid;
    JFormattedTextField bidTextField;
    JButton placeBidButton;

    JLabel lowBidLabel;

    private static int itemID;
    private static String itemName;
    private static Timestamp timestamp;
    private static int lowBid;
    private static int bidHis;
    private static byte[] imgPath;

    String userName;
    int userID;
    int userBid;



    public ItemDetail(int itemID, String itemName, Timestamp timestamp, int lowBid, int bidHistory, byte[] imgPath) {
        ItemDetail.itemID = itemID;
        ItemDetail.itemName = itemName;
        ItemDetail.timestamp = timestamp;
        ItemDetail.lowBid = lowBid;
        ItemDetail.bidHis = bidHistory;
        ItemDetail.imgPath = imgPath;

    }


    JPanel itemDetailPanel() {
        JPanel p = new JPanel();
        GroupLayout layout = new GroupLayout(p);
        p.setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).
                addGroup(layout.createSequentialGroup().
                        addComponent(imageMetaData(), 330 , 330 , 330).
                        addComponent(contentPanel(),450,450,450)));

        layout.setVerticalGroup(layout.createSequentialGroup().
                addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(imageMetaData(), 270,270,270).
                                addComponent(contentPanel(), 500, 500,500)));


        return p;
    }

    JPanel contentPanel() {
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
        itemNameLbl.setText(itemName);
        itemNameLbl.setFont(new Font("Verdana",Font.BOLD, 16));
        itemNameLbl.setOpaque(true);
        itemNameLbl.setBackground(Color.WHITE);
        itemNameLbl.setBorder(new EmptyBorder(60,0,0,0));



        // Create rating Bar
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/resources/31g.png"));
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
        imgPanel.add(CreatePanel());

    }

    JPanel CreatePanel() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        timeLeftLabel = new JLabel("Time Left");
        bidHistoryLabel = new JLabel("Bid History");
        startingBidLabel = new JLabel("Starting Bid");
        maxBid = new JLabel("Your max bid");

        timeLeft = new JLabel();
        bidHistory = new JLabel();
        startingBid = new JLabel();
        bidTextField = new JFormattedTextField();

        // Format Date

        String timeout = new SimpleDateFormat(("dd MMM yy HH:mm"))
                .format(timestamp.getTime());

        // Will print in UTC
        timeLeft.setText("(" + timeout + " UTC" + ")");
        bidHistory.setText(bidHis + " Bids");

        startingBid.setText("\u00A2" + lowBid);
        placeBidButton = new JButton("Place Bid");

        lowBidLabel = new JLabel("Enter " + lowBid + " or more");



        placeBidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userBid = bidTextField.getText().trim();
                // Casting
                int userBidNum = Integer.parseInt(userBid);
                if (userBidNum > lowBid) {

                    bidHistory.setText(bidHis + " Bids");
                    updateUserMaxBid(userBidNum);
                    setUserBid();


                } else {
                    System.out.println("Your BID is low");
                }
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
                        .addComponent(startingBidLabel)
                        .addComponent(maxBid))
                .addGroup(layout.createParallelGroup()
                        .addComponent(timeLeft)
                        .addComponent(bidHistory)
                        .addComponent(startingBid)
                        .addComponent(bidTextField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(placeBidButton)));
        hGroup.addComponent(lowBidLabel);

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
                .addComponent(startingBidLabel)
                .addComponent(startingBid));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(maxBid)
                .addComponent(bidTextField)
                .addComponent(placeBidButton));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(lowBidLabel));

        layout.setVerticalGroup(vGroup);


        return panel;
    }

    void updateUserMaxBid(int userBidNum) {
        int i = 0;

        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:8889/titodb",
                    "root", "root");

            PreparedStatement st =  connection
                    .prepareStatement("UPDATE tbl_user SET bid = ?, WHERE id = ?");

            st.setString(1, String.valueOf(userBidNum));
            i  = st.executeUpdate();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    void setUserBid () {
        DbQuery mq = new DbQuery();
        ArrayList<User> list = mq.BindUser();

        for (int i = 0; i < list.size(); i++) {

            bidHis = list.get(i).getBid();
        }
        bidHistory.setText(String.valueOf(bidHis));

    }

    DropShadowPanel imageMetaData() {
        if (imgMetaData == null) {
            imgMetaData = new DropShadowPanel(4);

            initUserProfile(imgMetaData);

        }

        return imgMetaData;
    }

    public void initUserProfile (JPanel container) {

        JPanel userView = new JPanel();
        userView.setBackground(new Color(240, 241,248));
        // Image View
        ImageIcon icon = new ImageIcon(imgPath);
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(330, 260,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        JLabel imgLabel = new JLabel(icon);

        // add to UserView
        userView.add(imgLabel);


        container.add(userView);

    }

    private static ImageIcon makeStarImageIcon(ImageProducer ip, float rf, float gf, float bf) {
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(ip, new SelectedImageFilter(rf, gf, bf))));
    }

}
