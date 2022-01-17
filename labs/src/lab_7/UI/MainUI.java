package lab_7.UI;

import lab_7.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainUI
{
    private DBConnection dbConnection;
    private java.sql.Statement stmt;

    private JPanel rootPanel;
    private JTable workTable;
    private JButton addButton;
    private JButton delButton;
    private JButton saveButton;
    private JButton refreshButton;
    private JScrollPane scroll;
    private JLabel result;

    private DefaultTableModel model;
    private Object[][] data = new Object[][]{{}};
    private ResultSet resultSet;

    private ButtonListener buttonListener;

    private final int colsCount = 6;

    public JPanel getRootPanel() { return rootPanel; }
    private void createUIComponents()
    {
        // TODO: place custom component creation code here
    }

    public MainUI()
    {
        startConnection();

        buttonListener = new ButtonListener();

        addButton.addActionListener(buttonListener);
        addButton.setActionCommand("ADD");

        delButton.addActionListener(buttonListener);
        delButton.setActionCommand("DEL");

        saveButton.addActionListener(buttonListener);
        saveButton.setActionCommand("SAVE");

        refreshButton.addActionListener(buttonListener);
        refreshButton.setActionCommand("REFRESH");

        createTable();
    }

    private void createTable()
    {
        workTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Фамилия", "Имя", "Отчество", "Дата рождения", "Должность", "Специализация"}
        ));

        model = (DefaultTableModel) workTable.getModel();
        updateTable();
    }

    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String command = event.getActionCommand();
            if (command.equals("ADD")) { model.addRow(new Object[]{}); return; }
            if (command.equals("DEL"))
                if (workTable.getRowCount() > 0) { model.removeRow(workTable.getRowCount() - 1); return; }
            if (command.equals("REFRESH")) { updateTable(); return; }
            if (command.equals("SAVE")) { saveTable(); return; }
        }
    }

    private void startConnection()
    {
        try
        {
            dbConnection = new DBConnection();
            dbConnection.init();
            Connection conn = dbConnection.getConnection();
            stmt = conn.createStatement();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    private ResultSet getResultFromDB()
    {
        ResultSet rs = null;
        try
        {
            rs = stmt.executeQuery("SELECT * from clinic");
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
        return rs;
    }

        private void updateTable()
        {
            resultSet = getResultFromDB();
            List<String[]> rows = new ArrayList<>();
            String[] tmp;
            try
            {
                while (resultSet.next())
                {
                    tmp = new String[colsCount];
                    for (int i = 1; i <= colsCount; ++i)
                        tmp[i - 1] = resultSet.getString(i);
                    rows.add(tmp);
                }
            }
            catch (SQLException throwables) { throwables.printStackTrace(); }

            removeAllRows();
            for (String[] row : rows) model.addRow(row);
        }

    private void removeAllRows()
    {
        int size = model.getRowCount();
        for (int i = size - 1; i >= 0; --i) model.removeRow(i);
    }

    private void saveTable() {
        List<String[]> rows = new ArrayList<>();
        String[] tmp;
        int rowsCount = model.getRowCount();

        for (int i = 0; i < rowsCount; ++i) {
            tmp = new String[colsCount];
            for (int j = 0; j < colsCount; ++j) tmp[j] = (String) model.getValueAt(i, j);
            rows.add(tmp);
        }

        try
        {
            stmt.executeUpdate("DELETE FROM clinic");
            for (int i = 0; i < rowsCount; ++i) {
                String command = "";
                tmp = rows.get(i);
                command = "INSERT INTO clinic VALUES ("
                        + quotate(tmp[0]) + ", "
                        + quotate(tmp[1]) + ", "
                        + quotate(tmp[2]) + ", "
                        + quotate(tmp[3]) + ", "
                        + quotate(tmp[4]) + ", "
                        + quotate(tmp[5]) + ")";
                int code = stmt.executeUpdate(command);
            }
        }
        catch (SQLException throwables) { throwables.printStackTrace(); result.setText(throwables.getMessage()); return; }

        result.setText("");
    }

    private String quotate(String content) { return "'" + content + "'"; }
}