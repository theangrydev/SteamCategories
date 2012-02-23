/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slugsource.steamcategories.gui;

import com.slugsource.steamcategories.lib.SteamCategories;
import com.slugsource.vdf.lib.InvalidFileException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Nathan Fearnley
 */
public class SteamCategoriesGui extends javax.swing.JFrame
{

    private SteamCategories cats;
    private File file = new File("F:\\Programming\\SteamCategories\\sharedconfig.vdf");
    private String steamId = "nfearnley";

    /**
     * Creates new form SteamCategories
     */
    public SteamCategoriesGui()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        appsList = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        categoriesList = new javax.swing.JList();
        setCategoryButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        closeMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        quitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        appsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(appsList);

        jSplitPane1.setLeftComponent(jScrollPane3);

        categoriesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        categoriesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        categoriesList.setToolTipText("");
        jScrollPane4.setViewportView(categoriesList);

        jSplitPane1.setRightComponent(jScrollPane4);

        setCategoryButton.setText("Set Category");

        fileMenu.setText("File");

        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        closeMenuItem.setText("Close");
        fileMenu.add(closeMenuItem);
        fileMenu.add(jSeparator1);

        quitMenuItem.setText("Quit");
        fileMenu.add(quitMenuItem);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 283, Short.MAX_VALUE)
                        .addComponent(setCategoryButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setCategoryButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_openMenuItemActionPerformed
    {//GEN-HEADEREND:event_openMenuItemActionPerformed
        // TODO add your handling code here:
        openFile();
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void openFile()
    {
        cats = new SteamCategories(file, steamId);
        try
        {
            cats.readApps();
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Could not load apps.");
            cats = null;
            return;
        }
        try
        {
            cats.readCategories();
        } catch (InvalidFileException | IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Could not load categories.");
            cats = null;
            return;
        }
        Set<String> apps = cats.getAppIdList();
        Set<String> categories = cats.getCategoryList();
        appsList.setListData(apps.toArray());
        categoriesList.setListData(categories.toArray());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(SteamCategoriesGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(SteamCategoriesGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(SteamCategoriesGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(SteamCategoriesGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                new SteamCategoriesGui().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList appsList;
    private javax.swing.JList categoriesList;
    private javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton setCategoryButton;
    // End of variables declaration//GEN-END:variables
}
