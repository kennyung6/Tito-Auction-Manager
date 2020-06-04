/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import dao.ItemDao;
import entity.Item;
import helpers.HoverButton;
import util.ComponentUtils;
import util.Logy;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


public final class ItemPanel extends JPanel {

    private JPanel centerPanel;
    private JPanel gridView;


    public ItemPanel() {
        initComponents();
        getItems();
        Logy.d("Item panel initialized");
    }


    private void initComponents() {

        // Center Panel
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        setBackground(Color.WHITE);

        gridView = new JPanel(new GridLayout(5, 5, 10, 20));
        gridView.setPreferredSize(new Dimension(690, 820));
        gridView.setBackground(Color.WHITE);


        // Add Grid to the Center Panel
        centerPanel.add(gridView);

        // Add center panel to Base Panel
        add(centerPanel);

    }


    private void getItems() {
        ItemDao itemDao = new ItemDao();
        List<Item> items;
        try {
            items = itemDao.getItems();
            for (Item item : items) {
                gridView.add(getGridItem(item));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private JComponent getGridItem(Item item) {

        final HoverButton btnImg = new HoverButton("");

        // Base Panel for Grid buttons
        btnImg.setLayout(new BorderLayout());


        // Image View
        ImageIcon icon = new ImageIcon(item.getImage());
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(140, 110,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        JLabel imgLabel = new JLabel(icon);


        // Inner Text Label
        JLabel itemTxt = new JLabel();
        itemTxt.setHorizontalAlignment(SwingConstants.CENTER);
        itemTxt.setPreferredSize(new Dimension(10, 20));
        itemTxt.setOpaque(true);
        itemTxt.setBackground(new Color(215, 219, 226));
        itemTxt.setText(item.getItemName());


        // Add child components to Grid Buttons
        btnImg.add(imgLabel, BorderLayout.CENTER);
        btnImg.add(itemTxt, BorderLayout.SOUTH);


        // Add Panel to the center panel in Dashboard
        btnImg.addActionListener(e -> {
            DetailPanel detailPanel = new DetailPanel(item.getItemId());
            ComponentUtils.addToPanel(centerPanel, detailPanel);
        });


        return btnImg;

    }


}
