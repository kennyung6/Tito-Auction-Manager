/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.BidDao;
import dao.ItemDao;
import dao.BidTable;
import database.SerializeIO;
import entity.Bid;
import entity.Item;
import entity.User;
import helpers.*;
import util.DateTimeUtils;
import util.Logy;
import widget.Alert;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageProducer;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class DetailPanel extends JPanel {

    private static final String filepath = "/Users/kennyung6/Desktop/tamfile.txt";
    helpers.AnimatedPDialog.ProgressDialog dialog;
    // Variables declaration
    private JPanel parentPanel;
    private JButton bidButton;
    private JPanel bidTable;
    private JPanel contentPanel;
    private JTextArea desTextField;
    private JLabel endTimeLabel;
    private JLabel endValue;
    private JTable highBidTable;
    private JPanel itemImage;
    private JLabel itemNameLabel;
    private JButton optOutButton;
    private JLabel jLabel1;
    private JLabel highBidTableLabel;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JSeparator jSeparator1;
    private JFormattedTextField maxBidField;
    private JPanel ratingPanel;
    private JButton refreshButton;
    private JLabel startBidLabel;
    private JLabel maxBidLabel;
    private JLabel highBidLabel;
    private JLabel startBidValue;
    private JLabel highBidValue;
    private JLabel startTimeLabel;
    private JLabel startValue;
    private JPanel xContentOne;
    // End of variables declaration
    private JPanel xContentTwo;
    private Item detailItem;
    private int itemID;
    private ItemDao dao;
    private int optOutItemID;
    private int maxBidID;
    private int maxBid;
    private DefaultTableModel model;
    private int currentUserBid;
    private BidTable tableModel;
    private long interval;
    private String itemEndTime;
    private String itemEndDate;

    /**
     * Creates new form ItemDetail
     */
    public DetailPanel(int clickedItem) {
        this.itemID = clickedItem;

        try {
            this.dao = new ItemDao();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        getItemDetails();
        initComponents();
        showMaxBidsTable();

        // Base Panel Background
        setBackground(new Color(255, 255, 255));
        revalidate();
        repaint();
    }

    private void initComponents() {

        itemImage = new JPanel();
        jLabel1 = new JLabel();
        bidTable = new JPanel();
        jScrollPane2 = new JScrollPane();
        highBidTable = new JTable();
        highBidTableLabel = new JLabel();
        contentPanel = new JPanel();
        itemNameLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        desTextField = new JTextArea();
        ratingPanel = new JPanel();
        jSeparator1 = new JSeparator();
        jLabel3 = new JLabel();
        startTimeLabel = new JLabel();
        startValue = new JLabel();
        endTimeLabel = new JLabel();
        endValue = new JLabel();
        startBidLabel = new JLabel();
        startBidValue = new JLabel();
        jPanel1 = new JPanel();
        maxBidField = new JFormattedTextField();
        maxBidLabel = new JLabel();
        bidButton = new JButton();
        highBidLabel = new JLabel();
        highBidValue = new JLabel();
        xContentOne = new JPanel();
        xContentTwo = new JPanel();
        refreshButton = new JButton();
        optOutButton = new JButton();


        leftViewComponents();
        rightViewComponents();
        add(parentPanel());

    }

    private JPanel parentPanel() {

        parentPanel = new JPanel();
        parentPanel.setBackground(new Color(255, 255, 255));
        GroupLayout layout = new GroupLayout(parentPanel);
        parentPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(itemImage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(xContentOne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGap(6, 6, 6)
                                                                        .addComponent(bidTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(xContentTwo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(186, 186, 186)
                                        .addComponent(optOutButton)))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(234, 234, 234)
                                        .addComponent(refreshButton)
                                        .addContainerGap(30, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(itemImage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(xContentOne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(xContentTwo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(bidTable, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                                                .addGap(7, 7, 7)
                                                .addComponent(optOutButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        )
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
                                                .addGap(80, 80, 80)
                                                .addComponent(refreshButton, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                        )))
        );

        return parentPanel;
    }

    private void leftViewComponents() {

        /*
          ITEM IMAGE VIEW
         */

        itemImage.setBackground(new Color(44, 57, 116, 117));
        itemImage.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));


        JPanel userView = new JPanel();
        userView.setBackground(new Color(13, 24, 120));
        // Image View
        ImageIcon icon = new ImageIcon(detailItem.getImage());
        System.out.println(Arrays.toString(detailItem.getImage()));
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(439, 398, Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        JLabel imgLabel = new JLabel(icon);

        // add to UserView
        userView.add(imgLabel);
        itemImage.add(imgLabel);



         /*
          ITEM IMAGE VIEW DECORATIONS
         */


        xContentOne.setBackground(new Color(51, 65, 130));

        GroupLayout xContentOneLayout = new GroupLayout(xContentOne);
        xContentOne.setLayout(xContentOneLayout);
        xContentOneLayout.setHorizontalGroup(
                xContentOneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 399, Short.MAX_VALUE)
        );
        xContentOneLayout.setVerticalGroup(
                xContentOneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 15, Short.MAX_VALUE)
        );

        xContentTwo.setBackground(new Color(82, 94, 153));

        GroupLayout xContentTwoLayout = new GroupLayout(xContentTwo);
        xContentTwo.setLayout(xContentTwoLayout);
        xContentTwoLayout.setHorizontalGroup(
                xContentTwoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 387, Short.MAX_VALUE)
        );
        xContentTwoLayout.setVerticalGroup(
                xContentTwoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 12, Short.MAX_VALUE)
        );



        /*
          MAXIMUM BIDS TABLE
         */


        bidTable.setBackground(new Color(204, 204, 204));

        jScrollPane2.setViewportView(highBidTable);

        highBidTableLabel.setFont(new Font("Lucida Grande", 1, 13)); // NOI18N
        highBidTableLabel.setForeground(new Color(0, 0, 0));
        highBidTableLabel.setBackground(new Color(184, 200, 217, 255));
        highBidTableLabel.setOpaque(true);
        highBidTableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        highBidTableLabel.setText("HIGHEST BIDS");
        highBidTableLabel.setAutoscrolls(true);
        highBidTableLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        // Centers Table texts
        DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer)
                highBidTable.getDefaultRenderer(String.class);
        stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Centers Table Headers
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)
                highBidTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Increase Cell texts font size
        Font font = highBidTable.getFont();
        font = font.deriveFont((float) (font.getSize2D() * 1.10));
        highBidTable.setFont(font);

        highBidTable.setRowHeight(30);
        highBidTable.getTableHeader().setFont(new Font("", Font.BOLD, 13));

        GroupLayout bidTableLayout = new GroupLayout(bidTable);
        bidTable.setLayout(bidTableLayout);
        bidTableLayout.setHorizontalGroup(
                bidTableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addComponent(highBidTableLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bidTableLayout.setVerticalGroup(
                bidTableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, bidTableLayout.createSequentialGroup()
                                .addComponent(highBidTableLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );


        optOutButton.setText("Opt Out");
        optOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Read object and get User id from file
                SerializeIO serializeIO = new SerializeIO();
                User user = (User) serializeIO.ReadObjectFromFile(filepath);
                int userId = user.getId();

                ItemDao itemDao = new ItemDao();
                itemDao.optOutBid(userId);

                showMaxBidsTable();
                resetItemMaxBid();
            }

        });

    }

    private void resetItemMaxBid() {
        BidDao bidDao = new BidDao();
        List<Bid> bids;
        int maxBID = 0;

        try {

            bids = bidDao.getItemMaxs(detailItem.getItemId());

            for (Bid bid : bids) {
                maxBID = bid.getMaxBid();

                System.out.println("MaxBid: " + maxBID);
            }

            highBidValue.setText("\u00A2" + maxBID);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private void rightViewComponents() {

        contentPanel.setBackground(new Color(243, 244, 246, 255));
        contentPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        itemNameLabel.setText(detailItem.getItemName());
        itemNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        itemNameLabel.setForeground(new Color(36, 49, 114));

        desTextField.setEditable(false);
        desTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        desTextField.setBackground(new Color(243, 244, 246, 255));
        desTextField.setColumns(20);
        desTextField.setForeground(new Color(0, 0, 0));
        desTextField.setLineWrap(true);
        desTextField.setRows(5);
        desTextField.setText(detailItem.getDescription());
        desTextField.setWrapStyleWord(true);
        desTextField.setBorder(null);
        desTextField.setFocusable(false);
        desTextField.setRequestFocusEnabled(false);

        ratingPanel.setBackground(new Color(243, 244, 246, 255));
        ratingPanel.add(displayRating());


        startTimeLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13)); // NOI18N
        startTimeLabel.setForeground(new Color(0, 0, 0));
        startTimeLabel.setText("BID Started :");

        startValue.setForeground(new Color(0, 0, 0));
        startValue.setText(new DateTimeUtils().getTime(detailItem.getStartTime()));

        endTimeLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13)); // NOI18N
        endTimeLabel.setForeground(new Color(0, 0, 0));
        endTimeLabel.setText("BID Ends : ");

        endValue.setForeground(new Color(0, 0, 0));
        endValue.setText(parseTime(new DateTimeUtils().getTime(detailItem.getEndTime())));

        startBidLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13)); // NOI18N
        startBidLabel.setForeground(new Color(0, 0, 0));
        startBidLabel.setText("Start BID : ");

        startBidValue.setForeground(new Color(0, 0, 0));
        startBidValue.setText("\u00A2" + detailItem.getStartBid());

        jPanel1.setBackground(new Color(215, 224, 234, 255));
        jPanel1.setBorder(BorderFactory.createEtchedBorder());

        maxBidField.setText("");
        maxBidField.setHorizontalAlignment(SwingConstants.CENTER);
        // Makes field restrict character input but numbers
        maxBidField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        maxBidField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                maxBidFieldActionPerformed(evt);
            }
        });

        maxBidLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        maxBidLabel.setForeground(new Color(0, 0, 0));
        maxBidLabel.setText("Enter Max Bid : ");

        bidButton.setText("Place Bid");
        bidButton.setBackground(new Color(10, 114, 22));
        bidButton.setForeground(Color.WHITE);
        bidButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        bidButton.setOpaque(true);
        bidButton.setBorderPainted(false);


        bidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bidButtonAction(e);

            }
        });


        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(maxBidLabel)
                                .addGap(20, 20, 20)
                                .addComponent(maxBidField, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(bidButton)
                                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(maxBidLabel)
                                        .addComponent(maxBidField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bidButton))
                                .addContainerGap(32, Short.MAX_VALUE))
        );

        highBidLabel.setFont(new Font("Lucida Grande", 1, 13)); // NOI18N
        highBidLabel.setForeground(new Color(0, 0, 0));
        highBidLabel.setText("Highest Bid :");

        highBidValue.setForeground(new Color(0, 0, 0));
        highBidValue.setText("\u00A2" + detailItem.getHighestBid());

        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addComponent(jLabel3))
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(desTextField, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                                .addComponent(itemNameLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addComponent(ratingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(startTimeLabel)
                                        .addComponent(startBidLabel))
                                .addGap(25, 25, 25)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(startValue)
                                        .addComponent(startBidValue))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                                .addComponent(endTimeLabel)
                                                .addGap(5, 5, 5))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                                .addComponent(highBidLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(endValue)
                                        .addComponent(highBidValue))
                                .addGap(25, 25, 25))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                .addContainerGap(0, Short.MAX_VALUE)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
        );
        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(itemNameLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ratingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(desTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(startTimeLabel)
                                        .addComponent(startValue)
                                        .addComponent(endTimeLabel)
                                        .addComponent(endValue))
                                .addGap(33, 33, 33)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(startBidLabel)
                                        .addComponent(startBidValue)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(highBidLabel)
                                                .addComponent(highBidValue)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
        );


        refreshButton.setText("REFRESH");
        refreshButton.setMaximumSize(new Dimension(20, 20));
        refreshButton.setPreferredSize(new Dimension(20, 20));
        refreshButton.setBackground(new Color(58, 69, 132));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("", Font.BOLD, 14));
        refreshButton.setOpaque(true);
        refreshButton.setBorderPainted(false);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                highBidTable.repaint();
            }
        });

    }

    private void updateUserMaxBid(int userBidNum) {
        ItemDao itemDao = new ItemDao();
        Item item = new Item();
        item.setHighestBid(userBidNum);
        item.setItemId(detailItem.getItemId());

        currentUserBid = userBidNum;

        itemDao.updateItemDetails(item);
        String message = "Sending your bid";
        dialog = new AnimatedPDialog.ProgressDialog(contentPanel, message, "/images/bidham.gif");
        dialog.setVisible(true);
    }

    private void insertUserMaxBid(int userMaxBid) {

        //Read object and get User id from file
        SerializeIO serializeIO = new SerializeIO();
        User user = (User) serializeIO.ReadObjectFromFile(filepath);
        String userId = String.valueOf(user.getId());


        Item item = new Item();
        item.setItemId(detailItem.getItemId());
        item.setHighestBid(userMaxBid);


        ItemDao itemDao = new ItemDao();
        //itemDao.insertMaxBid(userId, item);
        itemDao.insertOrUpdateMaxBid(userId, item);

        System.out.println("User ID: " + userId);
        System.out.println("Item ID: " + detailItem.getItemId());


    }

    private void getItemDetails() {
        ItemDao itemDao = new ItemDao();
        Item item = new Item();
        try {
            item = itemDao.getItemDetails(itemID);
            System.out.println("Description: " + item.getDescription());
            System.out.println("Name: " + item.getItemName());
            itemEndTime = item.getEndTime();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        detailItem = item;
    }

    private LevelBar displayRating() {
        // Create rating Bar
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/images/31g.png"));
        ImageProducer ip = defaultIcon.getImage().getSource();
        List<ImageIcon> list;
        StarImageIcon starImageIcon = new StarImageIcon();
        ImageIcon star = starImageIcon.makeStarImageIcon(ip, 1f, 1f, 0f);
        //star.getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH);
        list = Arrays.asList(star, star, star, star, star);
        LevelBar levelBar = new LevelBar(defaultIcon, list, 1);
        levelBar.setBackground(new Color(243, 244, 246, 255));
        levelBar.setLevel(2);


        return levelBar;
    }

    private String parseTime(String timestamp) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            date = sdf.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return timestamp;
    }

    private void maxBidFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void bidButtonAction(ActionEvent evt) {
        new BidWorker().execute();
    }

    private void insertUpdateBid() {
        String userBid = maxBidField.getText().trim();


        // Cast user input to an integer
        int userBidNum = Integer.parseInt(userBid);
        if (userBidNum > detailItem.getStartBid() && userBidNum > detailItem.getHighestBid()) {

            // Update data on Database
            updateUserMaxBid(userBidNum);

            // Insert user max bid on Bid Table
            insertUserMaxBid(userBidNum);

            // set Highest Bid
            highBidValue.setText("\u00A2" + userBidNum);


            // Log Values
            Logy.d("UserBid No: " + userBidNum);
            Logy.d("Item ID : " + detailItem.getItemId());

        } else {
            Logy.d("User Bid is Low");
            Alert.showError(contentPanel, "Bid is lower. Enter an higher amount");
            dialog.setVisible(false);
            dialog.dispose();
        }
    }

    private void showMaxBidsTable() {
        // Update Table
        tableModel = new BidTable(detailItem.getItemId());
        highBidTable.setModel(tableModel);
        highBidTable.repaint();
    }

    private class BidWorker extends SwingWorker<Integer, Integer> {

        protected Integer doInBackground() throws Exception {
            insertUpdateBid();

            // Do a time-consuming task.
            Thread.sleep(1000);
            return 42;
        }

        protected void done() {
            try {

                showMaxBidsTable();
                //JOptionPane.showMessageDialog(f, get());
                // Show Success Flags
                Alert.showInformation(contentPanel, "Bid Placed Successfully");
                dialog.setVisible(false);
                dialog.dispose();

                // Clear text view
                maxBidField.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
