package com.kingaspx.util;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {

    private String path;

    private ArrayList lines = null;
    private String[] columns = null;

    public ModelTable(ArrayList lin, String[] col) {
        setLines(lin);
        setColumns(col);
    }

    public ModelTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList getLines() {
        return lines;
    }

    public void setLines(ArrayList data) {
        lines = data;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public String getColumnName(int numCol) {
        return columns[numCol];
    }

    @Override
    public Object getValueAt(int numLin, int numCol) {
        Object[] lines = (Object[]) getLines().get(numLin);
        return lines[numCol];
    }

}
