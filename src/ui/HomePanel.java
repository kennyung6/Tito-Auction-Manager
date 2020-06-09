/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import util.Logy;

import javax.swing.*;
import java.awt.*;


public class HomePanel extends JPanel {


    private JPanel footerPanel;
    private JLabel gifImgLabel;
    private JLabel imgLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel parentPanel;
    private JPanel topTextPanel;


    public HomePanel() {
        initComponents();
        Logy.d("Home panel initialized");
    }


    private void initComponents() {

        parentPanel = new JPanel();
        footerPanel = new JPanel();
        imgLabel = new JLabel();
        topTextPanel = new JPanel();
        jLabel1 = new JLabel();
        jPanel1 = new JPanel();
        gifImgLabel = new JLabel();
        jLabel2 = new JLabel();

        int width = 20;
        int height = 20;


        parentPanel = new JPanel();
        footerPanel = new JPanel();
        imgLabel = new JLabel();
        topTextPanel = new JPanel();
        jLabel1 = new JLabel();
        jPanel1 = new JPanel();
        gifImgLabel = new JLabel();
        jLabel2 = new JLabel();


        setBackground(new Color(246, 249, 255));

        parentPanel.setBackground(new Color(246, 249, 255));

        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon footerImgLbl = new ImageIcon(getClass().getResource("/images/footerImg.png"));
        footerImgLbl.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        imgLabel.setIcon(footerImgLbl);

        footerPanel.setBackground(new Color(246, 249, 255));

        GroupLayout footerPanelLayout = new GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
                footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup()
                                .addComponent(imgLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        footerPanelLayout.setVerticalGroup(
                footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(imgLabel, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
        );

        topTextPanel.setBackground(new Color(246, 249, 255));

        jLabel1.setBackground(new Color(255, 255, 255));
        jLabel1.setFont(new Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Bid.!! Buy..!! And have a Blast..!!");
        jLabel1.setRequestFocusEnabled(false);
        jLabel1.setVerifyInputWhenFocusTarget(false);

        GroupLayout topTextPanelLayout = new GroupLayout(topTextPanel);
        topTextPanel.setLayout(topTextPanelLayout);
        topTextPanelLayout.setHorizontalGroup(
                topTextPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topTextPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                                .addContainerGap())
        );
        topTextPanelLayout.setVerticalGroup(
                topTextPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topTextPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                .addContainerGap())
        );

        gifImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gifImgLabel.setBackground(new Color(246, 249, 255));

        ImageIcon gifImgLbl = new ImageIcon(getClass().getResource("/images/auction_pc.gif"));
        footerImgLbl.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        gifImgLabel.setIcon(gifImgLbl);


        jPanel1.setBackground(new Color(246, 249, 255));
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(gifImgLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(gifImgLabel, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );

        jLabel2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 22)); // NOI18N
        jLabel2.setForeground(new Color(51, 65, 130));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Tito Auction Room");

        GroupLayout parentPanelLayout = new GroupLayout(parentPanel);
        parentPanel.setLayout(parentPanelLayout);
        parentPanelLayout.setHorizontalGroup(
                parentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(parentPanelLayout.createSequentialGroup()
                                .addGroup(parentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(parentPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(footerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(parentPanelLayout.createSequentialGroup()
                                                .addGroup(parentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(parentPanelLayout.createSequentialGroup()
                                                                .addGap(220, 220, 220)
                                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(parentPanelLayout.createSequentialGroup()
                                                                .addGap(123, 123, 123)
                                                                .addComponent(topTextPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 122, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(parentPanelLayout.createSequentialGroup()
                                .addGap(309, 309, 309)
                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        parentPanelLayout.setVerticalGroup(
                parentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(parentPanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(42, 42, 42)
                                .addComponent(topTextPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(44, 44, 44)
                                .addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 255, 255));
        GroupLayout layout = new GroupLayout(centerPanel);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(parentPanel, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(parentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );


        add(centerPanel, BorderLayout.CENTER);

    }


}
