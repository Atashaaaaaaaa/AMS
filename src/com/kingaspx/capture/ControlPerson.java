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

public class ControlPerson {

    ConectaBanco conecta = new ConectaBanco();

    public void insert(ModelPerson mod) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        try {
            conecta.connect();
            PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO person (id, first_name, last_name, student_id, class_section, register_date) VALUES (?, ?, ?, ?, ?, ?)");
            pst.setInt(1, mod.getId());
            pst.setString(2, mod.getFirst_name());
            pst.setString(3, mod.getLast_name());
            pst.setString(4, mod.getStudent_id());
            pst.setString(5, mod.getClass_section());
            pst.setString(6, date);
            pst.executeUpdate();
            conecta.disconnect();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void update(ModelPerson mod, int id) {
        conecta.connect();
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE person SET first_name= ?, last_name= ?, student_id= ?, class_section= ? WHERE id=?");
            pst.setString(1, mod.getFirst_name());
            pst.setString(2, mod.getLast_name());
            pst.setString(3, mod.getStudent_id());
            pst.setString(4, mod.getClass_section());
            pst.setInt(5, id);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        conecta.disconnect();
    }

    public void delete(int id) {
        conecta.connect();
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM person WHERE id= '" + id + "'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error!");
        } finally {
            conecta.disconnect();
        }
    }

    public void createTable(String SQL, JTable table) {
        String id = null;

        conecta.connect();
        ArrayList data = new ArrayList();
        String[] columns = new String[]{"", "ID", "Name", "Student Id", "Section"};
        conecta.executaSQL(SQL);
        try {
            conecta.rs.first();
            do {
                data.add(new Object[]{
                    "",
                    conecta.rs.getString("id"),
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
        table.getColumnModel().getColumn(0).setCellRenderer(new ControlPerson.ImageRenderer());
//        table.getColumnModel().getColumn(1).setMaxWidth(0);
//        table.getColumnModel().getColumn(1).setMinWidth(0);
//        table.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
//        table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
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

    public void editar(ModelPerson mod, int id) {
        conecta.connect();
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE apto SET apto= ? WHERE id=? ");
            pst.setString(1, mod.getFirst_name());
            pst.setInt(2, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Changed Successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error when changing apartment. Try Again!");
        } finally {
            conecta.disconnect();
        }
    }

}
