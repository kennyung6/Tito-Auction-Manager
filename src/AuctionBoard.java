import dbhelper.DbQuery;
import dbhelper.AuctionItem;
import util.DropShadowPanel;

import java.awt.*;
import java.awt.event.*;
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
        setSize(900, 700);
        // this method display the JFrame to center position of a screen
        setLocationRelativeTo(null);
        setResizable(false);

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
            scroll.setPreferredSize(new Dimension(600, 600));


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
        Image newImg = image.getScaledInstance(670, 137, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
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
        ArrayList<AuctionItem> list = mq.BindTable();

        gridPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        gridPanel.setPreferredSize(new Dimension(600, 600));
        gridPanel.setBackground(Color.WHITE);

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getMyImage() != null || list.get(i).getImgName() != null) {

                ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getMyImage()).getImage()
                        .getScaledInstance(130, 100, Image.SCALE_SMOOTH));

                String imgName = list.get(i).getImgName();
                itemCard(image, imgName);

            }


        }


        container.add(gridPanel);


    }

    private JPanel itemCard(ImageIcon image, String imgName) {
        DropShadowPanel cardPanel = new DropShadowPanel(3);
        cardPanel.setBackground(new Color(255, 255, 255));
        cardPanel.setBounds(0, 0, 450, 450);
        cardPanel.setLayout(new BorderLayout());

        // inner Image
        JLabel imgLabel = new JLabel(image, JLabel.CENTER);

        cardPanel.add(imgLabel);


        // Inner Label
        JLabel textLabel = new JLabel();
        textLabel.setText(imgName);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(10, 30));
        textLabel.setOpaque(true);
        textLabel.setBackground(new Color(215, 219, 226));

        cardPanel.add(textLabel, BorderLayout.PAGE_END);


        gridPanel.add(cardPanel);


        return cardPanel;
    }

    JPanel imageMetaData() {
        if (imgMetaData == null) {
            imgMetaData = new JPanel();
            imgMetaData.setLayout(new GridLayout(2, 1));
            imgMetaData.setPreferredSize(new Dimension(60, 520));
            imgMetaData.setBackground(new Color(51, 65, 130));


            initUserProfile(imgMetaData);
            initLogoutBtn(imgMetaData);

        }

        return imgMetaData;
    }

    public void initUserProfile(JPanel container) {

        JPanel userView = new JPanel();
        userView.setBackground(new Color(51, 65, 130));
        // Image View
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

    public void initLogoutBtn(JPanel container) {
        // Back button image
        String btnPath = "/resources/ic_back_btn.png";
        ImageIcon btnIcon = new ImageIcon(AuctionBoard.class.getResource(btnPath));
        Image btnImg = btnIcon.getImage(); // transform it
        Image newBtnImg = btnImg.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        btnIcon = new ImageIcon(newBtnImg);  // transform it back

        // Back Button
        JLabel backbtn = new JLabel(btnIcon);

        container.add(backbtn);

    }


}