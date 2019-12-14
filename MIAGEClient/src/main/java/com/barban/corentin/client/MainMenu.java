/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.client;

import DTO.FormateurDTO;
import DTO.FormationDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author jdetr
 */
public class MainMenu extends javax.swing.JFrame {

    private List<FormationDTO> listeFormations;
    
    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        chargerCatalogue();
    }

    private void chargerCatalogue() {
        try {
            URL url = new URL("http://localhost:8085/MIAGETechnicoCommercial-web/webresources/formationsCatalogue");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Gson gson = new Gson();
            this.listeFormations = new ArrayList<>();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                java.lang.reflect.Type typeMyType = new TypeToken<ArrayList<FormationDTO>>() {
                }.getType();
                while ((output = br.readLine()) != null) {
                    this.listeFormations = gson.fromJson(output, typeMyType);
                }
                DefaultTableModel tm = (DefaultTableModel) tableFormations.getModel();
                tm.setNumRows(listeFormations.size());
                for (int i=0; i < this.listeFormations.size(); i++) {
                    tm.setValueAt(this.listeFormations.get(i).getCode(), i, 0);
                    tm.setValueAt(this.listeFormations.get(i).getIntitule(), i, 1);
                    tm.setValueAt(this.listeFormations.get(i).getNiveau(), i, 2);
                    tm.setValueAt(this.listeFormations.get(i).getTypeduree(), i, 3);
                    tm.setValueAt(this.listeFormations.get(i).getTarifforfaitaire(), i, 4);
                }
                tableFormations.setModel(tm);
            }
            conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteFormationFromCatalogue() {
        try {
            String toDelete = this.tableFormations.getValueAt(this.tableFormations.getSelectedRow(), 0).toString();
            URL url = new URL("http://localhost:8085/MIAGETechnicoCommercial-web/webresources/formationsCatalogue/" + toDelete );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            } else {
                chargerCatalogue();
            }
            conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableFormations = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        BTN_demandeF = new javax.swing.JButton();
        BTN_addF = new javax.swing.JButton();
        BTN_supprF = new javax.swing.JButton();
        BTN_sallesF = new javax.swing.JButton();
        BTN_formateursF = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableFormations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Intitulé", "Niveau", "Durée", "Tarif forfaitaire"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableFormations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFormationsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableFormations);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Catalogue des formations disponibles");

        BTN_demandeF.setText("Demander une formation");
        BTN_demandeF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_demandeFActionPerformed(evt);
            }
        });

        BTN_addF.setText("Ajouter une formation au catalogue");
        BTN_addF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_addFActionPerformed(evt);
            }
        });

        BTN_supprF.setText("Supprimer la formation sélectionnée");
        BTN_supprF.setEnabled(false);
        BTN_supprF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_supprFActionPerformed(evt);
            }
        });

        BTN_sallesF.setText("Gérer les salles adéquates de la formation");
        BTN_sallesF.setEnabled(false);
        BTN_sallesF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_sallesFActionPerformed(evt);
            }
        });

        BTN_formateursF.setText("Gérer les formateurs compétents pour la formation");
        BTN_formateursF.setEnabled(false);
        BTN_formateursF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_formateursFActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Actions commerciales :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Actions technico-commerciales :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(364, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTN_addF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_supprF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_formateursF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_sallesF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_demandeF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_addF)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_supprF)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_sallesF)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_formateursF)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_demandeF)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_demandeFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_demandeFActionPerformed
        CréerDemande create = new CréerDemande(listeFormations);
        create.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_demandeFActionPerformed

    private void tableFormationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFormationsMouseClicked
        if (tableFormations.getSelectedRow() > -1) {
            this.BTN_formateursF.setEnabled(true);
            this.BTN_sallesF.setEnabled(true);
            this.BTN_supprF.setEnabled(true);
        }
    }//GEN-LAST:event_tableFormationsMouseClicked

    private void BTN_supprFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_supprFActionPerformed
        this.deleteFormationFromCatalogue();
    }//GEN-LAST:event_BTN_supprFActionPerformed

    private void BTN_addFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_addFActionPerformed
        AjoutFormation af = new AjoutFormation();
        af.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_addFActionPerformed

    private void BTN_sallesFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_sallesFActionPerformed
        GérerRessourcesFormation grf = new GérerRessourcesFormation(true, this.tableFormations.getValueAt(this.tableFormations.getSelectedRow(), 0).toString());
        grf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_sallesFActionPerformed

    private void BTN_formateursFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_formateursFActionPerformed
        GérerRessourcesFormation grf = new GérerRessourcesFormation(false, this.tableFormations.getValueAt(this.tableFormations.getSelectedRow(), 0).toString());
        grf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_formateursFActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_addF;
    private javax.swing.JButton BTN_demandeF;
    private javax.swing.JButton BTN_formateursF;
    private javax.swing.JButton BTN_sallesF;
    private javax.swing.JButton BTN_supprF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableFormations;
    // End of variables declaration//GEN-END:variables
}