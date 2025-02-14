/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.view.Client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;
import javax.swing.ImageIcon;
import org.connect.ConfigConstants;
import org.connect.ConfigUtil;
import org.ctrl.Parking_ctrl;
import org.view.Home.Home;

/**
 *
 * @author an
 */
public class Vehicles extends javax.swing.JFrame {

    private static Parking_ctrl controller;
    private String welcomeMessage;
    private String clientId;
    private String LastName;
    private String FirstName;
    private String username;
    private String Email;
    private String Status;

    /**
     * Creates new form Vehicles
     *
     * @param welcomeMessage
     * @param clientId
     * @param LastName
     * @param FirstName
     * @param username
     * @param Email
     * @param Status
     */
    public Vehicles(String welcomeMessage, String clientId, String LastName, String FirstName, String username, String Email, String Status) {
        initComponents();
        String filePath = ConfigConstants.DB_CONFIG_FILE_PATH;  // Chemin relatif vers le fichier de configuration
        Properties properties = ConfigUtil.loadProperties(filePath);

        String host = properties.getProperty("db.host");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        // Initialiser le contrôleur avec les valeurs chargées
        controller = new Parking_ctrl(host, user, password);
        this.welcomeMessage = welcomeMessage;
        this.clientId = clientId;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.username = username;
        this.Email = Email;
        this.Status = Status;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Véhicules");
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/shopping-cart.png"))); // NOI18N
        ImageIcon normalIcon2 = new ImageIcon(getClass().getResource("/org/images/shopping-cart.png"));
        ImageIcon hoverIcon2 = new ImageIcon(getClass().getResource("/org/images/shopping-cart(1).png"));
        jLabel7.setText("Á Vendre");
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setOpaque(true);
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel7.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                List_SellVehicles AllSellsVehiclesFrame = new List_SellVehicles(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                AllSellsVehiclesFrame.setLocationRelativeTo(null);
                AllSellsVehiclesFrame.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel7.setBackground(new java.awt.Color(255, 51, 51));
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
                jLabel7.setIcon(hoverIcon2);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel7.setForeground(new java.awt.Color(0, 0, 0));
                jLabel7.setBackground(new java.awt.Color(255, 255, 255));
                jLabel7.setIcon(normalIcon2);

            }
        });

        jLabel8.setFont(new java.awt.Font("FreeSerif", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Quels options choisisez-vous ?");
        jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/signing.png"))); // NOI18N
        ImageIcon normalIcon3 = new ImageIcon(getClass().getResource("/org/images/signing.png"));
        ImageIcon hoverIcon3 = new ImageIcon(getClass().getResource("/org/images/signing(1).png"));
        jLabel9.setText("Á Louer");
        jLabel9.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setOpaque(true);
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel9.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                List_LoanVehicles AllLoanVehiclesFrame = new List_LoanVehicles(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                AllLoanVehiclesFrame.setVisible(true);
                AllLoanVehiclesFrame.setLocationRelativeTo(null);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel9.setBackground(new java.awt.Color(255, 51, 51));
                jLabel9.setForeground(new java.awt.Color(255, 255, 255));
                jLabel9.setIcon(hoverIcon3);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel9.setForeground(new java.awt.Color(0, 0, 0));
                jLabel9.setBackground(new java.awt.Color(255, 255, 255));
                jLabel9.setIcon(normalIcon3);

            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)))
                .addContainerGap(419, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel8)
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addContainerGap(639, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("FreeSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/clientlogo2.png"))); // NOI18N
        jLabel1.setText("VehicleHub");
        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Clients_Int ClientsFrame = new Clients_Int(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                ClientsFrame.setLocationRelativeTo(null);
                ClientsFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel1.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel2.setFont(new java.awt.Font("FreeSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Véhicules");
        jLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Vehicles VehiclesFrame = new Vehicles(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                VehiclesFrame.setLocationRelativeTo(null);
                VehiclesFrame.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel2.setForeground(new java.awt.Color(0, 0, 0));
            }
        });

        jLabel3.setFont(new java.awt.Font("FreeSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contact");
        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Contact ContactFrame = new Contact(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                ContactFrame.setLocationRelativeTo(null);
                ContactFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel3.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel4.setFont(new java.awt.Font("FreeSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("À propos");
        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                About AboutFrame = new About(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                AboutFrame.setLocationRelativeTo(null);
                AboutFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel4.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel5.setFont(new java.awt.Font("FreeSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Home");
        jLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Clients_Int ClientsFrame = new Clients_Int(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                ClientsFrame.setLocationRelativeTo(null);
                ClientsFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel5.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel6.setFont(new java.awt.Font("FreeSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Log out");
        jLabel6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Home homeFrame = new Home();
                homeFrame.setVisible(true);
                homeFrame.setLocationRelativeTo(null);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel6.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel10.setFont(new java.awt.Font("FreeSerif", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Transactions");
        jLabel10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Transactions TransactionsFrame = new Transactions(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                TransactionsFrame.setLocationRelativeTo(null);
                TransactionsFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel10.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel10.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(163, 163, 163)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 557, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        String welcomeMessage = "";
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
            java.util.logging.Logger.getLogger(Vehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        String finalMessage = welcomeMessage;
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vehicles(finalMessage, "", "", "", "", "", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
