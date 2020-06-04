/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.util.Calendar;
import javax.swing.JTable;

/**
 *
 * @author Suzn
 */
public class Utils {

    public static int generateRandomID() {
        return (int) (Math.random() * 90000) + 10000;
    }

    public static Date calanderToSqlDate(Calendar calendar) {
        return new Date(calendar.getTimeInMillis());
    }

    public static Calendar sqlToCalandarDate(Date expiryDate) {
        Calendar c = new java.util.GregorianCalendar();
        c.setTime(expiryDate);
        return c;
    }

    public static Calendar millisToCalendar(long expiryDate) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(expiryDate);
        return c;
    }

    public static Date millisToSql(long expiryDate) {
        return new Date(expiryDate);
    }

    public static boolean isTableRowSelected(final JTable table) {
        return table.getSelectedRow() > -1;
    }

    public static int getIdFromTable(JTable table, int row) {
        return (int) table.getModel().getValueAt(row, 0);
    }

    public static boolean isAfterCurrentDate(Date date) {
        return date.after(new Date(new java.util.Date().getTime()));
    }

    public static boolean isBeforeCurrentDate(Date date) {
        return date.before(new Date(new java.util.Date().getTime()));
    }

    public static boolean isBeforeCurrentDate(Calendar date) {
        return date.before(Calendar.getInstance());
    }

    public static boolean isBeforeAndEqualCurrentDate(Date date) {
        return isBeforeCurrentDate(date) || ((new java.util.Date(date.getTime()).compareTo(new java.util.Date())) == 0);
    }

}
