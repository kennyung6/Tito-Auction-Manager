/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import com.sun.xml.internal.bind.v2.TODO;
import dao.ItemDao;
import entity.Item;
import helpers.HoverButton;
import util.ComponentUtils;
import util.DateTimeUtils;
import util.Logy;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;
import java.util.Timer;


public final class ItemPanel extends JPanel {

    private JPanel centerPanel;
    private JPanel gridView;
    private String itemStartTime;
    private String itemEndTime;
    private long currentDateTime;
    private long itemTimeout;
    private int hour;
    private int minute;
    private int second;
    long interval;


    public ItemPanel() {
        initComponents();
        getItems();
       // countDownTimer();
        //initCountDown();
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
                itemStartTime = item.getStartTime();
                itemEndTime = item.getEndTime();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initCountDown() {

        long specific;

        DateTimeUtils obj = new DateTimeUtils();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-M-dd HH:mm:ss");

        try {

            Date date1 = simpleDateFormat.parse(itemStartTime);
            Date date2 = simpleDateFormat.parse(itemEndTime);

            interval = obj.printDifference(date1, date2);
            specific = obj.printSpecific(interval);

            System.out.println(interval);
            System.out.println(specific);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        Timer timer = new Timer();
        timer.schedule(new myTask() {
            @Override
            public void run() {
                    interval--;
                    hour = (int) (interval / 3600);
                    minute = (int) ((interval - 3600 * hour) / 60);
                    second = (int) (interval - 3600 * hour - 60 * minute);

                    System.out.println("Interval:" + interval);
                    System.out.println("Hour: " + hour);
                    System.out.println("Minute: " + minute);
                    System.out.println("Second: " + second);



            }
        }, 0, 1000);
    }


    private JComponent getGridItem(Item item) {

        final HoverButton btnImg = new HoverButton("");

        // Base Panel for Grid buttons
        btnImg.setLayout(new BorderLayout());


        // Image View
        ImageIcon icon = new ImageIcon(item.getImage());
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(140, 110, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
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

    public void countDownTimer() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dt = null;
        try {
            dt = sdf.parse(itemEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert dt != null;
        long epochDateTime = dt.getTime();

        System.out.println("Mysql DateTime: " + dt);

        // current date in UTC, no matter what the JVM default timezone is
        LocalDateTime dtNowUtc = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println("Current DateTime: " + dtNowUtc);

        // set time to midnight and get the epochMilli
        currentDateTime = dtNowUtc.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
        System.out.println("Current date Time(Mill): " + dtNowUtc.atZone(ZoneOffset.UTC).toInstant().toEpochMilli());

        itemTimeout = epochDateTime - currentDateTime;
        System.out.println("Item Timeout: " + itemTimeout);


        Timer timer = new Timer();
        timer.schedule(new myTask(){
            @Override
            public void run(){
                if(itemTimeout > 0){
                    itemTimeout--;
                    hour = (int) (itemTimeout/3600);
                    minute = (int) ((itemTimeout - 3600*hour)/60);
                    second = (int) (itemTimeout - 3600*hour - 60*minute);

                    System.out.println("Hour: " + hour);
                    System.out.println("Minute: " + minute);
                    System.out.println("Second: " + second);


                    epochToDate(itemTimeout);

                }

            }
        }, 0, 1000);
    }

    private void epochToDate(long time) {
        Date date = new Date(time);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formattedDate = format.format(date);

        System.out.println("Date From Epoch: " + formattedDate);

    }



    private class myTask extends TimerTask {

        @Override
        public void run() {
        }
    }
}
