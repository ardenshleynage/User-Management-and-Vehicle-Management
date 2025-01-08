/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.view.Client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.connect.ConfigConstants;
import org.connect.ConfigUtil;
import org.ctrl.Parking_ctrl;
import org.mod.Basket_mod;
import org.mod.Vehicles_mod;
import org.view.Home.Home;

/**
 *
 * @author an
 */
public class List_LoanVehicles extends javax.swing.JFrame {

    private static Parking_ctrl controller;
    private String welcomeMessage;
    private String clientId;
    private String LastName;
    private String FirstName;
    private String username;
    private String Email;
    private String Status;

    /**
     * Creates new form List_LoanVehicles
     *
     * @param welcomeMessage
     * @param clientId
     * @param FirstName
     * @param LastName
     * @param username
     * @param Email
     * @param Status
     */
    public List_LoanVehicles(String welcomeMessage, String clientId, String LastName, String FirstName, String username, String Email, String Status) {
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
        populateTable();
        ClickVehicles();
    }

    private void ClickVehicles() {
        jTable2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable2.getSelectedRow();
                if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(e)) {
                    try {
                        List<Vehicles_mod> vehicules = controller.getAllLoanVehicles();
                        Vehicles_mod veh = vehicules.get(selectedRow);

                        if (veh != null) {
                            String number = String.valueOf(veh.getId());
                            String brand = veh.getBrand();
                            String model = veh.getModel();
                            String plate = veh.getPlate();
                            String year = String.valueOf(veh.getYear());
                            String imagePath = veh.getImage(); // Chemin absolu de l'image
                            String price = String.valueOf(veh.getPrice());
                            String loan_price = String.valueOf(veh.getLoan_price());
                            int status = veh.getStatus();

                            String statut = (status == 1) ? "À vendre" : "À louer";

                            // Redimensionner et charger l'image
                            JLabel imageLabel = new JLabel();
                            try {
                                ImageIcon originalImageIcon = new ImageIcon(imagePath);
                                Image originalImage = originalImageIcon.getImage();

                                // Fixe les dimensions pour l'image
                                int fixedWidth = 200;
                                int fixedHeight = 200;

                                // Redimensionner l'image
                                Image scaledImage = originalImage.getScaledInstance(fixedWidth, fixedHeight, Image.SCALE_SMOOTH);
                                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                                imageLabel.setIcon(scaledImageIcon);
                            } catch (Exception imgEx) {
                                imageLabel.setText("Image non disponible");
                            }

                            // Créer un panneau pour afficher les informations
                            JPanel panel = new JPanel(new BorderLayout(10, 10));
                            JPanel textPanel = new JPanel(new GridLayout(0, 1));

                            textPanel.add(new JLabel("Marque : " + brand));
                            textPanel.add(new JLabel("Modèle : " + model));
                            textPanel.add(new JLabel("Plaque : " + plate));
                            textPanel.add(new JLabel("Année : " + year));
                            textPanel.add(new JLabel("Prix : " + price + " $"));

                            panel.add(textPanel, BorderLayout.CENTER);
                            panel.add(imageLabel, BorderLayout.EAST);

                            int option = JOptionPane.showOptionDialog(
                                    List_LoanVehicles.this,
                                    panel,
                                    "Détails du véhicule",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null,
                                    new Object[]{"Louer", "Annuler"},
                                    "Annuler"
                            );

                            // Actions basées sur l'option sélectionnée
                            switch (option) {
                                case 0: // "Supprimer"
                                    Integer id_clients;
                                    Integer is_active;
                                    Long priceVeh;
                                    Long LoanpriceVeh;
                                    Integer yearVeh;
                                    id_clients = Integer.valueOf(clientId.trim());
                                    priceVeh = Long.valueOf(price);
                                    LoanpriceVeh = Long.valueOf(loan_price);
                                    yearVeh = Integer.valueOf(year);
                                    is_active = Integer.valueOf(Status.trim());

                                    Basket_mod bas = new Basket_mod(
                                            0,
                                            id_clients,
                                            LastName,
                                            FirstName,
                                            username,
                                            Email,
                                            veh.getId(),
                                            veh.getBrand(),
                                            veh.getModel(),
                                            veh.getPlate(),
                                            yearVeh,
                                            veh.getImage(),
                                            priceVeh,
                                            LoanpriceVeh,
                                            java.time.LocalDateTime.now().toString(),
                                            is_active,
                                            status,
                                            2
                                    );

                                    
                                    String checkResult = controller.checkLoanBasket(bas);
                                    if (checkResult.contains("dépasse la limite")) {
                                        JOptionPane.showMessageDialog(
                                                List_LoanVehicles.this,
                                                "Vous avez dépassez la limite autorisée pour la location d'un véhicule (1), pour plus d'informations veuillez contacter le service á la clientèle.",
                                                "Limite atteinte",
                                                JOptionPane.WARNING_MESSAGE
                                        );
                                    } else {
                                        
                                        boolean added = controller.addBasket(bas);
                                        if (added) {
                                            JOptionPane.showMessageDialog(
                                                    List_LoanVehicles.this,
                                                    "Véhicule loué avec succès !"
                                            );
                                        } else {
                                            JOptionPane.showMessageDialog(
                                                    List_LoanVehicles.this,
                                                    "Erreur lors de la location du véhicule.",
                                                    "Erreur",
                                                    JOptionPane.ERROR_MESSAGE
                                            );
                                        }
                                    }
                                    break;

                                case 1: // "À louer" ou "À vendre"
                                    break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(List_LoanVehicles.this, "Erreur : le véhicule sélectionné n'a pas pu être trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(List_LoanVehicles.this, "Erreur lors de la récupération des données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public void populateTable() {
        boolean NoSellVehicles = controller.NoLoanVehicles();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        if (NoSellVehicles) {
            String emptyMessage = "Il n'y a pas de véhicules á loué.";
            model.setColumnCount(1);// Réduire à une seule colonne
            model.setColumnIdentifiers(new String[]{""}); // Supprimer les en-têtes
            model.addRow(new Object[]{emptyMessage}); // Ajouter une ligne avec le message

            // Ajuster l'affichage du tableau
            jTable2.setModel(model);
            jTable2.setEnabled(false);// Ajoutez une ligne avec le message
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            jTable2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        } else {
            List<Vehicles_mod> vehiclesList = controller.getAllLoanVehicles();  // Fetch all people from the controller

            // Clear the existing rows
            int rowNumber = 1;

            for (Vehicles_mod veh : vehiclesList) {

                String statut = veh.getStatus() == 0 ? "Supprimer" : veh.getStatus() == 1 ? "Á vendre" : veh.getStatus() == 2 ? "Á louer" : "Inconnu";
                Object[] rowData = {
                    rowNumber++,
                    veh.getId(),
                    veh.getBrand(),
                    veh.getModel(),
                    veh.getPlate(),
                    veh.getYear(),
                    veh.getImage(),
                    veh.getPrice() + " $",
                    veh.getLoan_price() + " $",
                    statut,};

                model.addRow(rowData);
            }
            jTable2.getColumnModel().getColumn(1).setMinWidth(0);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(1).setWidth(0);

            jTable2.getColumnModel().getColumn(6).setMinWidth(0);
            jTable2.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(6).setWidth(0);

            jTable2.getColumnModel().getColumn(8).setMinWidth(0);
            jTable2.getColumnModel().getColumn(8).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(8).setWidth(0);

            jTable2.getColumnModel().getColumn(9).setMinWidth(0);
            jTable2.getColumnModel().getColumn(9).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(9).setWidth(0);

        }

        // Adjust the table's total height based on the number of rows
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
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Liste des Véhicules á louer");
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/shopping-cart_sub.png"))); // NOI18N
        ImageIcon normalIcon4 = new ImageIcon(getClass().getResource("/org/images/shopping-cart_sub.png"));
        ImageIcon hoverIcon4 = new ImageIcon(getClass().getResource("/org/images/shopping-cart(1)_sub.png"));
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jLabel11.setOpaque(true);
        jLabel11.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                List_SellVehicles AllSellsVehiclesFrame = new List_SellVehicles(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                AllSellsVehiclesFrame.setLocationRelativeTo(null);
                AllSellsVehiclesFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel11.setBackground(new java.awt.Color(255, 51, 51));
                jLabel11.setIcon(hoverIcon4);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel11.setBackground(new java.awt.Color(255, 255, 255));
                jLabel11.setIcon(normalIcon4);

            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_swing/Parking_Project/src/org/images/signing_sub.png"))); // NOI18N
        ImageIcon normalIcon5 = new ImageIcon(getClass().getResource("/org/images/signing_sub.png"));
        ImageIcon hoverIcon5 = new ImageIcon(getClass().getResource("/org/images/signing(1)_sub.png"));
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jLabel12.setOpaque(true);
        jLabel12.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                List_LoanVehicles AllLoanVehiclesFrame = new List_LoanVehicles(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                AllLoanVehiclesFrame.setLocationRelativeTo(null);
                AllLoanVehiclesFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel12.setBackground(new java.awt.Color(255, 51, 51));
                jLabel12.setIcon(hoverIcon5);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel12.setBackground(new java.awt.Color(255, 51, 51));
                jLabel12.setIcon(normalIcon5);

            }
        });

        jTable2.setBackground(new java.awt.Color(0, 0, 0));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "id", "Marque", "Modèle", "Plaque", "Annèe", "Image", "Prix", "Prix (Louer)", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(40);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(402, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(610, Short.MAX_VALUE))
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

        jLabel7.setFont(new java.awt.Font("FreeSerif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Transactions");
        jLabel7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Transactions TransactionsFrame = new Transactions(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                TransactionsFrame.setLocationRelativeTo(null);
                TransactionsFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel7.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
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
                .addGap(157, 157, 157)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
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
                    .addComponent(jLabel7))
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
            java.util.logging.Logger.getLogger(List_LoanVehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(List_LoanVehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(List_LoanVehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(List_LoanVehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        String finalMessage = welcomeMessage;
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new List_LoanVehicles(finalMessage, "", "", "", "", "", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
