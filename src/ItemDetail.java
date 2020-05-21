import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.util.Arrays;
import java.util.List;

import net.miginfocom.swing.MigLayout;
import util.DropShadowPanel;
import util.LevelBar;
import util.SelectedImageFilter;

public class ItemDetail extends JFrame{

    JPanel imgPanel;
    DropShadowPanel imgMetaData;
    JPanel pinkPanel;
    JPanel contentPanel;
    JPanel gridPanel;

    private JLabel labelShowLevel;
    private JLabel textShowLevel;

    private JLabel labelAddEntity;
    private JLabel textAddEntity;

    private JLabel labelRemoveEntity;
    private JLabel textRemoveEntity;

    private JLabel labelSearchEntity;
    private JFormattedTextField textSearchEntity;
    private JButton buttonSearchEntity;

    private JLabel totalLevels;

    public static void main (String [] args){
        EventQueue.invokeLater(ItemDetail::new);
    }


    public ItemDetail() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(Color.white);

        initLayout();

        pack();
        setVisible(true);
    }

    void initLayout() {
        JPanel p = new JPanel();
        GroupLayout layout = new GroupLayout(p);
        p.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).
                addGroup(layout.createSequentialGroup().
                        addComponent(imageMetaData(), 400 , 400 , 400).
                        addComponent(contentPanel(),470,470,470)));

        layout.setVerticalGroup(layout.createSequentialGroup().
                addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(imageMetaData(), 400,400,400).
                                addComponent(contentPanel(), 400, 400,400)));

        add(p);
    }

    JPanel contentPanel() {
        if (contentPanel == null) {

            initTopPanel();
            initGridPanel();

            // Split into Two Panels
            contentPanel = new JPanel();
            contentPanel.setLayout(new MigLayout());
            contentPanel.setBackground(Color.WHITE);



            contentPanel.add(pinkPanel, "cell 0 0");
            //  insert invisible components to provide top space
            contentPanel.add(Box.createRigidArea(new Dimension(0, 80)));
            contentPanel.add(imgPanel, "cell 0 1");


        }

        return contentPanel;
    }

    public void initTopPanel () {
        // Top Panel
        pinkPanel = new JPanel(new MigLayout());
        pinkPanel.setBackground(Color.WHITE);



        // Item Name
        JLabel itemName = new JLabel();
        itemName.setText("Golden Watch");
        itemName.setFont(new Font("Verdana",Font.BOLD, 16));
        itemName.setOpaque(true);
        itemName.setBackground(Color.WHITE);
        itemName.setBorder(new EmptyBorder(20,0,0,0));



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
        descText.setText("Golden watch was discovered during world war two when soldiers \nwhen agitated by the drones");
        descText.setBorder(new EmptyBorder(20,0,0,0));
        descText.setEditable(false);


        pinkPanel.add(itemName,  "cell 0 0");
        pinkPanel.add(levelBar, "cell 0 1");
        pinkPanel.add(availText, "cell 0 2");
        pinkPanel.add(descText, "cell 0 3");


    }

    private static ImageIcon makeStarImageIcon(ImageProducer ip, float rf, float gf, float bf) {
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(ip, new SelectedImageFilter(rf, gf, bf))));
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

        labelShowLevel = new JLabel("Time Left");
        labelAddEntity = new JLabel("Bid History");
        labelRemoveEntity = new JLabel("Starting Bid");
        labelSearchEntity = new JLabel("Your max bid");

        textShowLevel = new JLabel();
        textAddEntity = new JLabel();
        textRemoveEntity = new JLabel();
        textSearchEntity = new JFormattedTextField();

        textShowLevel.setText("(24 Jun 14 09:45 UTC");
        textAddEntity.setText("<html><font color='blue'>0 Bid</font></html>");
        textRemoveEntity.setText("$200");
        buttonSearchEntity = new JButton("Place Bid");

        totalLevels = new JLabel("Enter $200 or more");



        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.linkSize(SwingConstants.HORIZONTAL, buttonSearchEntity);

        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER); // Will align the labels the way you wanted

        hGroup.addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelShowLevel)
                        .addComponent(labelAddEntity)
                        .addComponent(labelRemoveEntity)
                        .addComponent(labelSearchEntity))
                .addGroup(layout.createParallelGroup()
                        .addComponent(textShowLevel)
                        .addComponent(textAddEntity)
                        .addComponent(textRemoveEntity)
                        .addComponent(textSearchEntity))
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonSearchEntity)));
        hGroup.addComponent(totalLevels);

        layout.setHorizontalGroup(hGroup);

        // Vertical
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(labelShowLevel)
                .addComponent(textShowLevel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(labelAddEntity)
                .addComponent(textAddEntity));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(labelRemoveEntity)
                .addComponent(textRemoveEntity));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(labelSearchEntity)
                .addComponent(textSearchEntity)
                .addComponent(buttonSearchEntity));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(totalLevels));

        layout.setVerticalGroup(vGroup);
//@formatter:on

        return panel;
    }

    DropShadowPanel imageMetaData() {
        if (imgMetaData == null) {
            imgMetaData = new DropShadowPanel(4);
            imgMetaData.setPreferredSize(new Dimension(60, 520));

            initUserProfile(imgMetaData);

        }

        return imgMetaData;
    }

    public void initUserProfile (JPanel container) {

        JPanel userView = new JPanel();
        userView.setBackground(new Color(240, 241,248));
        // Image View
        String path = "/resources/img_gold_pocket.png";
        ImageIcon icon = new ImageIcon(AuctionBoard.class.getResource(path));
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(400, 350,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        JLabel imgLabel = new JLabel(icon);

        // add to UserView
        userView.add(imgLabel);


        container.add(userView);

    }



}
