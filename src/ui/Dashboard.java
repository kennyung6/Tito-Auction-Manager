package ui;

import helpers.DropShadowPanel;
import util.ComponentUtils;
import util.Logy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BorderFactory.createMatteBorder;

/**
 * Primary GUI window for the client application.
 */
public class Dashboard extends JFrame {
    
    JPanel rootPanel;
    JPanel sidePanel;
    JPanel contentPanel;
    DropShadowPanel topPanel;
    JPanel centerPanel;
    JScrollPane scroll;
    JPanel gridView;
    JPanel itemDetailPanel;
    JLabel backLabel;

    private HomePanel homePanel;
    private ItemPanel auctionPanel;
    private JButton homeMenuButton;
    private final Border defaultDashboardMenuBorder = createEmptyBorder(0, 5, 0, 0);
    private final Border selectedDashboardMenuBorder = createMatteBorder(0, 5, 0, 0,
            new Color(255, 162, 0));

    private JButton auctionMenuButton;
    private JButton logoutButton;




    public Dashboard() {
        super("Tito Auction Board");

        initLayout();
        initFrame();
        ComponentUtils.addToPanel(this.centerPanel, getHomePanel());
        Logy.d("Dashboard panel initialized");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
             new Dashboard().setVisible(true);
        });

    }

    private void initFrame() {
        setSize(1080, 720);
        // this method display the JFrame to center position of a screen
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initLayout() {

        rootPanel = new JPanel();
        GroupLayout layout = new GroupLayout(rootPanel);
        rootPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER).
                addGroup(layout.createSequentialGroup().
                        addComponent(sidePanel(), 200, 200, 200).
                        addComponent(contentPanel())));

        layout.setVerticalGroup(layout.createSequentialGroup().
                addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(sidePanel()).
                                addComponent(contentPanel())));

        add(rootPanel);

    }

    private JPanel sidePanel() {
        if (sidePanel == null) {
            sidePanel = new JPanel();
            sidePanel.setLayout(new BorderLayout());
            sidePanel.setPreferredSize(new Dimension(60, 520));
            sidePanel.setBackground(new Color(51, 65, 130));


            JPanel userView = new JPanel();
            userView.setBackground(new Color(51, 65, 130));
            // Home Button
            String path = "/images/userprof.png";
            ImageIcon icon = new ImageIcon(Dashboard.class.getResource(path));
            Image image = icon.getImage(); // transform it
            Image newImg = image.getScaledInstance(120, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            icon = new ImageIcon(newImg);  // transform it back
            JLabel imgLabel = new JLabel(icon);
            imgLabel.setBorder(new EmptyBorder(20, 0, 0, 0));//top,left,bottom,right


            // add to UserView
            userView.add(imgLabel);


            // Name Label
            JLabel nameLabel = new JLabel();
            nameLabel.setText("Kenny Dabiri");
            nameLabel.setForeground(Color.white);
            nameLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
            nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
            userView.add(nameLabel);


            // Back button image
            String btnPath = "/images/ic_home.png";
            ImageIcon btnIcon = new ImageIcon(Dashboard.class.getResource(btnPath));
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
                        gridView.setVisible(true);
                        backLabel.setVisible(false);

                    }

                }
            });

            // Titus Watermark
            String wmPath = "/images/titusalpha.png";
            ImageIcon wmIcon = new ImageIcon(Dashboard.class.getResource(wmPath));
            Image wmImage = wmIcon.getImage(); // transform it
            Image wmImg = wmImage.getScaledInstance(180, 250, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            wmIcon = new ImageIcon(wmImg);  // transform it back
            JLabel wmImgLabel = new JLabel(wmIcon);
            wmImgLabel.setBorder(new EmptyBorder(50, 0, 0, 0));//top,left,bottom,right

            wmImgLabel.setLayout(new BorderLayout());
            backLabel.setHorizontalAlignment(JLabel.CENTER);
            backLabel.setVerticalAlignment(JLabel.CENTER);

            wmImgLabel.add(backLabel);

            // show watermark

            JPanel sideButtonPanel = new JPanel(new GridLayout(3,1));
            sideButtonPanel.setBackground(new Color(51, 65, 130));





            homeMenuButton = new JButton();
            homeMenuButton.setBackground(new Color(51, 65, 130));
            homeMenuButton.setFont(new Font("Tahoma", Font.BOLD, 14)); 
            homeMenuButton.setForeground(new Color(255, 255, 255));
            homeMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/home.png"))); 
            homeMenuButton.setText("Home");
            homeMenuButton.setBorder(createMatteBorder(0, 5, 0, 0, new Color(243, 187, 0)));


            homeMenuButton.setContentAreaFilled(false);
            homeMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
            homeMenuButton.setVerticalAlignment(SwingConstants.CENTER);
            homeMenuButton.setIconTextGap(10);
            homeMenuButton.setOpaque(true);
            homeMenuButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); 
            homeMenuButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home1x.png"))); 
            homeMenuButton.addActionListener(evt -> {
                homeMenuButton.setBorder(selectedDashboardMenuBorder);
                auctionMenuButton.setBorder(defaultDashboardMenuBorder);
                auctionMenuButton.setBackground(new Color(51, 65, 130));

                homeMenuButtonActionPerformed(evt);
            });


            /*homeMenuButton.addActionListener(new DetailPanel.Button1Handler());
*/

            auctionMenuButton = new JButton();
            auctionMenuButton.setBackground(new Color(51, 65, 130));
            auctionMenuButton.setFont(new Font("Tahoma", 1, 14));
            auctionMenuButton.setForeground(new Color(255, 255, 255));
            auctionMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/auction.png"))); 
            auctionMenuButton.setText("Auction");
            auctionMenuButton.setBorder(createEmptyBorder(1, 5, 1, 1));
            auctionMenuButton.setContentAreaFilled(false);
            auctionMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
            auctionMenuButton.setIconTextGap(10);



            auctionMenuButton.setPressedIcon(new ImageIcon(getClass().getResource("/images/auction.png"))); 
            auctionMenuButton.setSelectedIcon(new ImageIcon(getClass().getResource("/images/auction1x.png"))); 
            auctionMenuButton.addActionListener(evt -> {
                auctionMenuButton.setBorder(selectedDashboardMenuBorder);

                homeMenuButton.setBackground(new Color(51, 65, 130));
                homeMenuButton.setBorder(defaultDashboardMenuBorder);
                auctionMenuButtonActionPerformed(evt);
            });



            logoutButton = new JButton();
            logoutButton.setBackground(new Color(51, 65, 130));
            logoutButton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14)); 
            logoutButton.setForeground(new java.awt.Color(255, 255, 255));
            logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); 
            logoutButton.setText("Logout");
            logoutButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
            logoutButton.setContentAreaFilled(false);
            logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
            logoutButton.setIconTextGap(10);
            logoutButton.setOpaque(true);
            logoutButton.setPressedIcon(new ImageIcon(getClass().getResource("/images/logout.png")));
            logoutButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout1x.png"))); 
            logoutButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    logoutButtonActionPerformed(evt);
                }
            });


            sideButtonPanel.add(homeMenuButton);
            sideButtonPanel.add(auctionMenuButton);
            sideButtonPanel.add(logoutButton);


            sidePanel.add(userView, BorderLayout.NORTH);
            sidePanel.add(sideButtonPanel, BorderLayout.CENTER);
           // sidePanel.add(logoutButton);

            // hide on Dashboard
            backLabel.setVisible(false);

        }

        return sidePanel;
    }

    public JScrollPane contentPanel() {
        if (scroll == null) {

            // Split into Two Panels
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.setBackground(Color.WHITE);

            // Make ContentPanel Scrollable
            scroll = new JScrollPane(contentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            // remove border from scroll pane
            scroll.setBorder(createEmptyBorder());
            scroll.setViewportBorder(createEmptyBorder());

            // remove scrollbar
            scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            scroll.getViewport().setBorder(null);

            // Top Panel
            topPanel = new DropShadowPanel(4);
            topPanel.setBackground(Color.PINK);

            // Center Panel
            centerPanel = new JPanel();
            centerPanel.setLayout(new CardLayout());
            centerPanel.setBackground(Color.WHITE);

            // Inner View for Center Panel
            gridView = new JPanel(new GridLayout(5, 5, 10, 20));
            gridView.setPreferredSize(new Dimension(650, 820));
            gridView.setBackground(Color.WHITE);


            // Image View
            String path = "/images/topimg.png";
            ImageIcon icon = new ImageIcon(Dashboard.class.getResource(path));
            Image image = icon.getImage(); // transform it
            Image newImg = image.getScaledInstance(790, 137, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            icon = new ImageIcon(newImg);  // transform it back
            JLabel imgLabel = new JLabel(icon);


            // Add Image to Top Panel
            topPanel.add(imgLabel);

            // Add Grid to Bottom Container
            centerPanel.add(gridView);

            // Extra Layout Settings
            contentPanel.add(topPanel, BorderLayout.PAGE_START);
            //  insert Empty border to provide top space
            centerPanel.setBorder(new EmptyBorder(30,0,0,0));

            contentPanel.add(centerPanel, BorderLayout.CENTER);


        }

        return scroll;
    }

    private HomePanel getHomePanel() {
        if (homePanel == null) {
            Logy.d("Creating home panel instance");
            homePanel = new HomePanel();
        }
        return homePanel;
    }

    private ItemPanel getItemPanel() {
        //if (auctionPanel == null) {
            Logy.d("Creating auction panel instance");
            return new ItemPanel();
       // }

    }

    private void homeMenuButtonActionPerformed(ActionEvent evt) {
        Logy.d("Home menu clicked");
        ComponentUtils.addToPanel(this.centerPanel, getHomePanel());
    }

    private void auctionMenuButtonActionPerformed(ActionEvent evt) {
        Logy.d("Auction menu clicked");
        ComponentUtils.addToPanel(this.centerPanel, getItemPanel());
    }

    private void logoutButtonActionPerformed(ActionEvent evt) {
        Logy.d("Logout button clicked");
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        this.dispose();
    }

}