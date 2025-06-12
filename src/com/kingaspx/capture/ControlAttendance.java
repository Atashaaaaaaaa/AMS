package com.kingaspx.capture;

import com.kingaspx.util.ConectaBanco;
import com.kingaspx.util.ModelTable;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ControlAttendance {

    ConectaBanco conecta = new ConectaBanco();

    public void insert(ModelAttendance mod) {
        try {
            System.out.println("Saving attendance");
            conecta.connect();
            PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO attendance (person_id, time_entry) VALUES (?, ?)");
            pst.setInt(1, mod.gePerson_id());            
            pst.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

            pst.executeUpdate();
            conecta.disconnect();
            System.out.println("Saving attendance..done");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void createTable(String SQL, JTable table) {
        String id = null;

        conecta.connect();
        ArrayList data = new ArrayList();
        String[] columns = new String[]{"", "ID", "Time In", "Name", "Student Id", "Section"};
        conecta.executaSQL(SQL);
        try {
            conecta.rs.first();
            do {
                data.add(new Object[]{
                    "",
                    conecta.rs.getString("person_id"),
                    conecta.rs.getString("time_entry"),
                    conecta.rs.getString("first_name") + " " + conecta.rs.getString("last_name"),
                    conecta.rs.getString("student_id"),
                    conecta.rs.getString("class_section")});
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            
        } finally {
            conecta.disconnect();
        }

        ModelTable modelo = new ModelTable(data, columns);
        table.setModel((TableModel) modelo);
        table.getColumnModel().getColumn(0).setCellRenderer(new ControlAttendance.ImageRenderer());     
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);       
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    class ImageRenderer implements TableCellRenderer {

        public JLabel lbl = new JLabel();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            try {
                Object text = table.getValueAt(row, 1);
                File image = new File("C:\\photos\\person." + text + ".1.jpg");
                String path = image.getAbsolutePath();
                ImageIcon i = new ImageIcon(new ImageIcon(String.valueOf(path)).getImage().getScaledInstance(lbl.getWidth() + 50, lbl.getHeight() + 50, Image.SCALE_SMOOTH));
                lbl.setIcon(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lbl;
        }
    }

}
