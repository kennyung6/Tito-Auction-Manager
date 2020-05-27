import dbhelper.DbQuery;
import dbhelper.AuctionItem;
import dbhelper.User;
import util.DropShadowPanel;
import util.HoverButton;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

/**
 * Primary GUI window for the client application.
 */
public class AuctionBoard extends JFrame {
    JPanel imgPanel;
    JPanel imgMetaData;
    DropShadowPanel pinkPanel;
    JPanel contentPanel;
    JScrollPane scroll;
    JPanel gridPanel;
    JPanel itemDetailPanel;
    JLabel backLabel;
    int bidHistory;



    public AuctionBoard() {
        super("Tito Auction Board");
        initLayout();


    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            AuctionBoard irc = new AuctionBoard();
            irc.setVisible(true);
        });

    }

    void initLayout() {
        setSize(1000, 750);
        // this method display the JFrame to center position of a screen
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);

              /*  LoginPage loginPage = new LoginPage();
                loginPage.dispose();*/

/*
                if (isVisible()) {
                    System.out.println("Frame is visible");
                    LoginPage loginPage = new LoginPage();
                    loginPage.dispose();
                    loginPage.setVisible(false);
                } else {
                    System.out.println("frame is invisible");
                }*/



            }
        });

        JPanel p = new JPanel();
        GroupLayout layout = new GroupLayout(p);
        p.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER).
                addGroup(layout.createSequentialGroup().
                        addComponent(imageMetaData(), 200, 200, 200).
                        addComponent(contentPanel())));

        layout.setVerticalGroup(layout.createSequentialGroup().
                addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(imageMetaData()).
                                addComponent(contentPanel())));

        add(p);
    }

    JScrollPane contentPanel() {
        if (scroll == null) {

            initTopPanel();
            initGridPanel();

            // Split into Two Panels
            contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setBackground(Color.WHITE);

            // Make ContentPanel Scrollable
            scroll = new JScrollPane(contentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


            // remove border from scroll pane
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.setViewportBorder(BorderFactory.createEmptyBorder());

            // remove scrollbar
            scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            scroll.getViewport().setBorder(null);


            contentPanel.add(pinkPanel);
            //  insert invisible components to provide top space
            contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));
            contentPanel.add(imgPanel);
            //  insert invisible components to provide bottom space
            contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        }

        return scroll;
    }

    public void initTopPanel() {
        // Top Panel
        pinkPanel = new DropShadowPanel(4);
        pinkPanel.setBackground(Color.PINK);


        // Image View
        String path = "/resources/topimg.png";
        ImageIcon icon = new ImageIcon(AuctionBoard.class.getResource(path));
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(770, 137, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        JLabel imgLabel = new JLabel(icon);
        //imgLabel.setBorder(new EmptyBorder(50,0,0,0));//top,left,bottom,right

        // add to UserView
        pinkPanel.add(imgLabel);


    }

    public void initGridPanel() {

        // 4x4 GridLayout Panel
        imgPanel = new JPanel();
        imgPanel.setBackground(Color.WHITE);
        initGridLayout(imgPanel);

    }

    public void initGridLayout(JPanel container) {
        //setLayout(null);

        DbQuery mq = new DbQuery();
        ArrayList<AuctionItem> list = mq.BindItem();

        gridPanel = new JPanel(new GridLayout(5, 5, 10, 20));
        gridPanel.setPreferredSize(new Dimension(650, 820));
        gridPanel.setBackground(Color.WHITE);



        for (int i = 0; i < list.size(); i++) {


            if (list.get(i).getImage() != null || list.get(i).getName() != null) {

                // Image
                ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getImage()).getImage()
                        .getScaledInstance(150, 110, Image.SCALE_SMOOTH));

                // Name
                String itemName = list.get(i).getName();

                // Add  Button
                HoverButton btnImg = new HoverButton("");
                btnImg.setLayout(new BorderLayout());
                int finalIndex = i;

                // Create Image Label
                JLabel imgLabel = new JLabel();
                imgLabel.setIcon(image);


                btnImg.addActionListener(e -> {

                    // Log the value of item position
                    String text = String.format("Button: %02d", finalIndex);
                    System.out.println(text);

                   // Get Item Data from Server
                    int itemID = list.get(finalIndex).getId();
                    String Name = list.get(finalIndex).getName();
                    Timestamp timeLeft = list.get(finalIndex).getTimeout();
                    int lowBid = list.get(finalIndex).getLowBid();
                    bidHistory = list.get(finalIndex).getBidHistory();
                    byte[] imgPath = list.get(finalIndex).getImage();


                    // Transfer item data to the ItemDetail Frame
                    ItemDetail itemDetail = new ItemDetail(itemID,Name,timeLeft,lowBid,bidHistory,imgPath);
                    itemDetailPanel = itemDetail.itemDetailPanel();
                    itemDetailPanel.setPreferredSize(new Dimension(700, 600));
                    itemDetailPanel.setBackground(Color.WHITE);

                    container.add(itemDetailPanel);
                    container.revalidate();

                    // show Home
                    backLabel.setVisible(true);

                    gridPanel.setVisible(false);


                });


                // Add button and image to single card instance
                // Inner Text Label
                JLabel textLabel = new JLabel();
                textLabel.setText(itemName);
                textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                textLabel.setPreferredSize(new Dimension(10, 20));
                textLabel.setOpaque(true);
                textLabel.setBackground(new Color(215, 219, 226));


                btnImg.add(imgLabel, BorderLayout.CENTER);
                btnImg.add(textLabel, BorderLayout.SOUTH);

                JPanel btnPanel = new JPanel();
                btnPanel.add(btnImg);

                gridPanel.add(btnImg);

            }

        }

        // Add Grid to Bottom Container
        container.add(gridPanel);

    }




    JPanel imageMetaData() {
        if (imgMetaData == null) {
            imgMetaData = new JPanel();
            imgMetaData.setLayout(new GridLayout(2, 1));
            imgMetaData.setPreferredSize(new Dimension(60, 520));
            imgMetaData.setBackground(new Color(51, 65, 130));


            initUserProfile(imgMetaData);
            initHomeBtn(imgMetaData);

        }

        return imgMetaData;
    }

    public void initUserProfile(JPanel container) {

        JPanel userView = new JPanel();
        userView.setBackground(new Color(51, 65, 130));
        // Home Button
        String path = "/resources/userprof.png";
        ImageIcon icon = new ImageIcon(AuctionBoard.class.getResource(path));
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(120, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        JLabel imgLabel = new JLabel(icon);
        imgLabel.setBorder(new EmptyBorder(50, 0, 0, 0));//top,left,bottom,right


        // add to UserView
        userView.add(imgLabel);


        // Name Label
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Kenny Dabiri");
        nameLabel.setForeground(Color.white);
        nameLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));

        userView.add(nameLabel);

        container.add(userView);

    }

    public void initHomeBtn(JPanel container) {


        // Back button image
        String btnPath = "/resources/ic_home.png";
        ImageIcon btnIcon = new ImageIcon(AuctionBoard.class.getResource(btnPath));
        Image btnImg = btnIcon.getImage(); // transform it
        Image newBtnImg = btnImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        btnIcon = new ImageIcon(newBtnImg);  // transform it back

        // Back Button
        backLabel = new JLabel(btnIcon);
        backLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                // you can open a new frame here as
                // i have assumed you have declared "frame" as instance variable
                if (itemDetailPanel != null && itemDetailPanel.isVisible()) {
                    itemDetailPanel.setVisible(false);
                    gridPanel.setVisible(true);
                    backLabel.setVisible(false);

                }

            }
        });

        // hide on Dashboard
        backLabel.setVisible(false);



        // Titus Watermark
        String wmPath = "/resources/titusalpha.png";
        ImageIcon wmIcon = new ImageIcon(AuctionBoard.class.getResource(wmPath));
        Image wmImage = wmIcon.getImage(); // transform it
        Image wmImg = wmImage.getScaledInstance(150, 250, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        wmIcon = new ImageIcon(wmImg);  // transform it back
        JLabel wmImgLabel = new JLabel(wmIcon);
        wmImgLabel.setBorder(new EmptyBorder(50, 0, 0, 0));//top,left,bottom,right

        wmImgLabel.setLayout(new BorderLayout());
        backLabel.setHorizontalAlignment(JLabel.CENTER);
        backLabel.setVerticalAlignment(JLabel.CENTER);

        wmImgLabel.add(backLabel);

        // show watermark
        container.add(wmImgLabel);


    }



}