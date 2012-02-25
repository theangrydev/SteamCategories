/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slugsource.steamcategories.gui;

import com.slugsource.steamcategories.lib.SteamCategories;
import com.slugsource.vdf.lib.InvalidFileException;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author Nathan Fearnley
 */
public class SteamCategoriesGui extends javax.swing.JFrame
{

    private SteamCategories cats;

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
        removeCategoryButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        closeMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        quitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 287));

        jSplitPane1.setDividerLocation(350);

        jScrollPane3.setViewportView(appsList);

        jSplitPane1.setLeftComponent(jScrollPane3);

        categoriesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(categoriesList);

        jSplitPane1.setRightComponent(jScrollPane4);

        setCategoryButton.setText("Set Category");
        setCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setCategoryButtonActionPerformed(evt);
            }
        });

        removeCategoryButton.setText("Remove Category");
        removeCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCategoryButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
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
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removeCategoryButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(setCategoryButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setCategoryButton)
                    .addComponent(removeCategoryButton))
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
        //String steamId = "nfearnley";
        String steamId = JOptionPane.showInputDialog(this, "Enter your Steam ID:");
        if (steamId == null)
        {
            return;
        }
        if (steamId.equals(""))
        {
            return;
        }

        // File file = new File("F:\\Programming\\SteamCategories\\sharedconfig.vdf");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(getSteamDirectory());
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter()
        {

            String filename = "sharedconfig.vdf";

            @Override
            public boolean accept(File f)
            {
                return f.getName().equals(filename);
            }

            @Override
            public String getDescription()
            {
                return filename;
            }
        });
        int result = fileChooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        File file = fileChooser.getSelectedFile();

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
        ListModel appListModel = cats.getAppListModel();
        ListModel catListModel = cats.getCategoryListModel();
        appsList.setModel(appListModel);
        categoriesList.setModel(catListModel);
    }

    private File getSteamDirectory()
    {
        File programFiles = new File(System.getenv("ProgramFiles"));
        if (!programFiles.isDirectory())
        {
            return new File(".");
        }

        File steam = new File(programFiles, "Steam");
        if (!steam.isDirectory())
        {
            return programFiles;
        }

        File userdata = new File(steam, "userdata");
        if (!userdata.isDirectory())
        {
            return steam;
        }

        File[] userids = userdata.listFiles();
        if (userids.length == 0)
        {
            return userdata;
        }
        File userid = userids[0];
        if (!userid.isDirectory())
        {
            return userdata;
        }

        File seven = new File(userid, "7");
        if (!seven.isDirectory())
        {
            return userid;
        }

        File remote = new File(seven, "remote");
        if (!remote.isDirectory())
        {
            return seven;
        }

        return remote;
    }

    private void setCategoryButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_setCategoryButtonActionPerformed
    {//GEN-HEADEREND:event_setCategoryButtonActionPerformed
        setCategory();
    }//GEN-LAST:event_setCategoryButtonActionPerformed

    private void setCategory()
    {
        if (categoriesList.getSelectedIndex() == -1)
        {
            removeCategory();
            return;
        }

        for (int x : appsList.getSelectedIndices())
        {
            String appId = cats.getAppId(x);
            int catIndex = categoriesList.getSelectedIndex();
            String category = cats.getCategory(catIndex);
            cats.setCategory(appId, category);
        }
        appsList.repaint();
    }

    private void removeCategoryButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_removeCategoryButtonActionPerformed
    {//GEN-HEADEREND:event_removeCategoryButtonActionPerformed
        removeCategory();
    }//GEN-LAST:event_removeCategoryButtonActionPerformed

    private void removeCategory()
    {
        for (int x : appsList.getSelectedIndices())
        {
            String appId = cats.getAppId(x);
            cats.setCategory(appId, null);
        }
        appsList.repaint();
    }

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveMenuItemActionPerformed
    {//GEN-HEADEREND:event_saveMenuItemActionPerformed
        saveFile();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveFile()
    {
        try
        {
            cats.writeCategories();
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Could not save categories.");
        }
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
    private javax.swing.JButton removeCategoryButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton setCategoryButton;
    // End of variables declaration//GEN-END:variables
}
