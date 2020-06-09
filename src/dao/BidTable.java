package dao;

import entity.Bid;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class BidTable extends AbstractTableModel {

    private List<Bid> bids;

    public BidTable(int itemID) {

        BidDao bidDao = new BidDao();

        try {
            bids = bidDao.getItemMaxBids(itemID);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }


    @Override
    public int getRowCount() {
        return bids.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        String name = null;
        switch (column) {
            case 0:
                name = "ID";
                break;
            case 1:
                name = "BID";
                break;
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class clazz = String.class;
        switch (columnIndex) {
            case 1:
                clazz = Integer.class;
                break;
        }
        return clazz;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Bid Bid = bids.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = Bid.getBidId();
                break;
            case 1:
                value = Bid.getMaxBid();
                break;
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Bid bid = bids.get(rowIndex);

        switch (columnIndex) {
            case 0:
                if (aValue instanceof Integer) {


                    bid.setBidId((Integer) aValue);
                }
                break;
            case 1:
                if (aValue instanceof Integer) {
                    bid.setMaxBid((Integer) aValue);
                }
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void add() {
        bids.add(new Bid());
        fireTableRowsInserted(bids.size() - 1, bids.size() - 1);
    }


}
