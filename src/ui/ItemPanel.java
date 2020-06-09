/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import dao.ItemDao;
import entity.Item;
import helpers.DropShadowPanel;
import helpers.HoverButton;
import util.ComponentUtils;
import util.Logy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.List;


public final class ItemPanel extends JPanel {
    DropShadowPanel topPanel;
    int expiredItem;
    private JPanel centerPanel;
    private DynamicGridLayout gridLayout;
    private JPanel gridPanel;

    public ItemPanel() {
        initComponents();
        getItems();

        Logy.d("Item panel initialized");
    }

    private void initComponents() {

        // Center Panel
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BorderLayout());
        setBackground(Color.WHITE);


        gridLayout = new DynamicGridLayout();
        gridPanel = new JPanel(new GridLayout(5, 5, 10, 20));
        gridPanel.setBackground(Color.white);

        gridLayout.getUI().add(gridPanel);


        // Image View
        String path = "/images/topimg.png";
        ImageIcon icon = new ImageIcon(Dashboard.class.getResource(path));
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(820, 145, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        JLabel imgLabel = new JLabel(icon);


        // Top Panel
        topPanel = new DropShadowPanel(4);
        topPanel.setBackground(Color.PINK);

        // Add Image to Top Panel
        topPanel.add(imgLabel);

        // Extra Layout Settings
        centerPanel.add(topPanel, BorderLayout.PAGE_START);
        //  insert Empty border to provide top space
        gridPanel.setBorder(new EmptyBorder(40, 40, 0, 40));
        centerPanel.add(gridPanel, BorderLayout.CENTER);

        // Add center panel to Base Panel
        add(centerPanel);

    }

    private void getItems() {
        ItemDao itemDao = new ItemDao();
        List<Item> items;

        try {
            items = itemDao.getItems();
            int limit = 0;
            for (Item item : items) {

                if (shouldAddItem(item)) {

                    if (limit <= 15) {
                        // do something
                        gridPanel.add(getGridItem(item));
                    } else {
                        // do something else
                    }
                    limit++;

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean shouldAddItem(Item item) {

        // Current Time
        long now = Instant.now().toEpochMilli();
        long endTime = dateToEpoch(item.getEndTime());


        if (endTime <= now) {
            expiredItem = item.getItemId();
            Logy.d("Expired Item: " + expiredItem);
        }

        return endTime > now;
    }

    private Long dateToEpoch(String timestamp) {
        Long millis = null;
        try {
            millis = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestamp).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millis;
    }

    private JComponent getGridItem(Item item) {

        final HoverButton btnImg = new HoverButton("");

        // Base Panel for Grid buttons
        btnImg.setLayout(new BorderLayout());

        JPanel gridItemPanel = new JPanel(new BorderLayout());

        // Image View
        ImageIcon icon = new ImageIcon(item.getImage());
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(160, 120, Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        JLabel imgLabel = new JLabel(icon);


        // Inner Text Label
        JLabel itemTxt = new JLabel();
        itemTxt.setHorizontalAlignment(SwingConstants.CENTER);
        itemTxt.setOpaque(true);
        itemTxt.setFont(new Font("", Font.PLAIN, 14));
        itemTxt.setBackground(new Color(215, 219, 226));
        itemTxt.setText(item.getItemName());

        gridItemPanel.add(imgLabel, BorderLayout.CENTER);
        gridItemPanel.add(itemTxt, BorderLayout.SOUTH);

        // Add child components to Grid Buttons
        btnImg.add(gridItemPanel);


        // Add Panel to the center panel in Dashboard
        btnImg.addActionListener(e -> {
            // DetailPanel detailPanel = new DetailPanel(item.getItemId());
            DetailPanel detailPanel = new DetailPanel(item.getItemId());
            ComponentUtils.addToPanel(centerPanel, detailPanel);
        });


        return btnImg;

    }

}
