/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grocery.list;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.DefaultListModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Wonarche
 */
public class GroceryList extends JFrame {
    
     private JTextField itemField;
     private JTextField listField;
    private JList grocerylist;
    private DefaultListModel<String> grocerylistModel;
    
    public GroceryList() {
        initComponents();
    }
    
     private void initComponents() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }

        setTitle("Future Value Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        itemField = new JTextField();
        listField = new JTextField();
        
        grocerylist = new JList();
        grocerylistModel = new DefaultListModel<>();        
        JScrollPane futureValuePane = new JScrollPane(grocerylist);
        
        Dimension dim = new Dimension(150, 20);
        itemField.setPreferredSize(dim);
        itemField.setMinimumSize(dim);
        listField.setPreferredSize(dim);
        listField.setMinimumSize(dim);
        

        Dimension dim2 = new Dimension(150, 80);
        futureValuePane.setPreferredSize(dim2);
        futureValuePane.setMinimumSize(dim2);

        JButton addButton = new JButton("Add");
        JButton clearButton = new JButton("Clear");
        JButton removeButton = new JButton("Remove");

        addButton.addActionListener(e -> addButtonClicked());
        clearButton.addActionListener(e -> clearButtonClicked());
        removeButton.addActionListener(e -> removeButtonClicked());

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(clearButton);
        buttonPanel.add(removeButton);
        
        

        // main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(new JLabel("Item:"), getConstraints(0, 0));
        panel.add(itemField, getConstraints(1, 0));
         panel.add(addButton);
        panel.add(new JLabel("List:"), getConstraints(0, 1));
        panel.add(futureValuePane, getConstraints(1, 1));       

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }

    // helper method for getting a GridBagConstraints object
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }

    private void addButtonClicked() {
        SwingValidator sv = new SwingValidator(this);
if (sv.isPresent(itemField, "Item")){
    
}
        String[] groceries = {itemField.getText() };
        grocerylistModel = new DefaultListModel<>();
        for (String item : groceries) {
            grocerylistModel.addElement(item);
        }
         grocerylist.setModel(grocerylistModel);

}
    private void clearButtonClicked() {
    int[] selectedIndices = grocerylist.getSelectedIndices();
        for (int index : selectedIndices) {
            grocerylistModel.clear();
            
           
//        System.exit(0);
        }
    }
    
      private void removeButtonClicked() {
        int[] selectedIndices = grocerylist.getSelectedIndices();
        for (int index : selectedIndices) {
            grocerylistModel.removeElementAt(index);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(() -> {
            JFrame frame = new GroceryList();
        });
    }
    
  
    }
    
    class SwingValidator {    
    private final Component parentComponent;
    
    public SwingValidator(Component parent) {
        this.parentComponent = parent;
    }
    
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(parentComponent, message, 
                "Invalid Entry", JOptionPane.ERROR_MESSAGE);
    }

    public boolean isPresent(JTextComponent c, String fieldName) {
        if (c.getText().isEmpty()) {
            showErrorDialog(fieldName + " is a required field.");
            c.requestFocusInWindow();
            return false;
        } else {
            return true;
        }
    }

    public boolean isInteger(JTextComponent c, String fieldName) {
        try {
            Integer.parseInt(c.getText());
            return true;
        } catch (NumberFormatException e) {
            showErrorDialog(fieldName + " must be an integer.");
            c.requestFocusInWindow();
            return false;
        }
    }

    public boolean isDouble(JTextComponent c, String fieldName) {
        try {
            Double.parseDouble(c.getText());
            return true;
        } catch (NumberFormatException e) {
            showErrorDialog(fieldName + " must be a valid number.");
            c.requestFocusInWindow();
            return false;
        }
    }
}