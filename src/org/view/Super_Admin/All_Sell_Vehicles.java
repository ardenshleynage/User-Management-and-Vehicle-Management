/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.view.Super_Admin;

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
import org.mod.Vehicles_mod;
import org.view.Home.Home;

/**
 *
 * @author an
 */
public class All_Sell_Vehicles extends javax.swing.JFrame {

    private String welcomeMessage;
    private static Parking_ctrl controller;
    private String username;
    private Vehicles_mod veh;

    /**
     * Creates new form All_Sell_Vehicles
     *
     * @param welcomeMessage
     */
    public All_Sell_Vehicles(String welcomeMessage) {
        String filePath = ConfigConstants.DB_CONFIG_FILE_PATH;  // Chemin relatif vers le fichier de configuration
        Properties properties = ConfigUtil.loadProperties(filePath);

        String host = properties.getProperty("db.host");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        // Initialiser le contrôleur avec les valeurs chargées
        controller = new Parking_ctrl(host, user, password);
        this.welcomeMessage = welcomeMessage;
        initComponents();
        populateTable();
        ClickVehicles();
        if (welcomeMessage != null && !welcomeMessage.isEmpty()) {
            this.welcomeMessage = welcomeMessage;
        } else {
            // Récupérer dynamiquement le nom complet si welcomeMessage est nul ou vide
            String fullName = fetchFullName(); // Appel à une méthode pour récupérer le nom complet
            if (fullName != null && !fullName.isEmpty()) {
                this.welcomeMessage = "Bienvenue, " + fullName;
            } else {
                this.welcomeMessage = "Bienvenue, Nom inconnu";
            }
        }
        updateAdminName();
    }

    public void setWelcomeMessage(String newMessage) {
        this.welcomeMessage = newMessage; // Met à jour le welcomeMessage
        updateAdminName(); // Met à jour l'affichage
    }

    private String fetchFullName() {
        String fullName = controller.fetchAdminFullName(username); // Exemple avec ID ou une autre source
        return fullName; // Renvoie le nom complet récupéré
    }

    public void updateAdminName() {
        // Vérifie si welcomeMessage est null ou vide, sinon met à jour le texte de jLabel1
        if (welcomeMessage == null || welcomeMessage.isEmpty()) {
            // Si welcomeMessage est nul ou vide
            jLabel1.setText("Bienvenue, Nom inconnu"); // Affiche un message par défaut
        } else {
            // Si welcomeMessage a une valeur
            jLabel1.setText(welcomeMessage); // Mettez à jour l'étiquette avec le message
        }

    }

    private void ClickVehicles() {
        jTable2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable2.getSelectedRow();
                if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(e)) {
                    try {
                        List<Vehicles_mod> vehicules = controller.getAllSellVehicles();
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
                            textPanel.add(new JLabel("Prix (vente) : " + price + " $"));
                            textPanel.add(new JLabel("Prix (location) : " + loan_price + " $"));
                            textPanel.add(new JLabel("Statut : " + statut));

                            panel.add(textPanel, BorderLayout.CENTER);
                            panel.add(imageLabel, BorderLayout.EAST);

                            int option = JOptionPane.showOptionDialog(
                                    All_Sell_Vehicles.this,
                                    panel,
                                    "Détails du véhicule",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null,
                                    new Object[]{"Supprimer", "À louer", "Modifier", "Annuler"},
                                    "Annuler"
                            );

                            // Actions basées sur l'option sélectionnée
                            switch (option) {
                                case 0: // "Supprimer"
                                    DeleteVehiclesQuestion(veh);
                                    break;

                                case 1: // "À louer" ou "À vendre"

                                    controller.LoanVehicles(veh);
                                    JOptionPane.showMessageDialog(All_Sell_Vehicles.this, "Véhicule loué avec succès !");
                                    populateTable();
                                    break;

                                case 2: // "Modifier"
                                    dispose();
                                    Update_Vehicles UpdateVehiclesFrame = new Update_Vehicles(brand, model, plate, year, number, imagePath, price, loan_price, welcomeMessage);
                                    UpdateVehiclesFrame.setLocationRelativeTo(null);
                                    UpdateVehiclesFrame.setVisible(true);
                                    break;

                                case 3: // "Annuler"
                                    break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(All_Sell_Vehicles.this, "Erreur : le véhicule sélectionné n'a pas pu être trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(All_Sell_Vehicles.this, "Erreur lors de la récupération des données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    
    

    public void populateTable() {
        boolean NoSellVehicles = controller.NoSellVehicles();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        if (NoSellVehicles) {
            String emptyMessage = "Il n'y a pas de véhicules á vendre.";
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
            List<Vehicles_mod> vehiclesList = controller.getAllSellVehicles();  // Fetch all people from the controller

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

        }

        // Adjust the table's total height based on the number of rows
    }

    private void DeleteVehiclesQuestion(Vehicles_mod veh) {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement supprimez le véhicule ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Non"
        );

        // Handle user selection
        if (option == JOptionPane.YES_OPTION) {
            handleDeletePeoples(veh);
        } else if (option == JOptionPane.NO_OPTION) {

            return;
        } else {
            // Handle any unexpected option, though in this case it's unlikely
            JOptionPane.showMessageDialog(null,
                    "Erreur : choix non reconnu.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void handleDeletePeoples(Vehicles_mod veh) {
        boolean success = controller.SureDeleteVehicles(veh);

        if (success) {
            JOptionPane.showMessageDialog(this, "Le véhicile a été supprimés.", "Information", JOptionPane.INFORMATION_MESSAGE);
            populateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion du véhicule.", "Erreur", JOptionPane.ERROR_MESSAGE);
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(138, 285));

        jLabel2.setBackground(new java.awt.Color(255, 51, 51));
        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/dash-user(1).png"))); // NOI18N
        ImageIcon normalIcon8 = new ImageIcon(getClass().getResource("/org/images/dash-user(1).png"));
        ImageIcon hoverIcon8 = new ImageIcon(getClass().getResource("/org/images/dash-user(2).png"));
        jLabel2.setText("Clients");
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Super_Int AllUsersFrame = new Super_Int(welcomeMessage);
                AllUsersFrame.setVisible(true);
                AllUsersFrame.pack();
                AllUsersFrame.setLocationRelativeTo(null);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel2.setBackground(new java.awt.Color(255, 255, 255));
                jLabel2.setForeground(new java.awt.Color(0, 0, 0));
                jLabel2.setIcon(hoverIcon8);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel2.setBackground(new java.awt.Color(255, 51, 51));
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                jLabel2.setIcon(normalIcon8);

            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/car(4).png"))); // NOI18N
        ImageIcon normalIcon9 = new ImageIcon(getClass().getResource("/org/images/car(4).png"));
        ImageIcon hoverIcon9 = new ImageIcon(getClass().getResource("/org/images/car(5).png"));
        jLabel3.setText("Véhicules");
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Cars_Int CarsFrame = new Cars_Int(welcomeMessage);
                CarsFrame.setVisible(true);
                CarsFrame.pack();
                CarsFrame.setLocationRelativeTo(null);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel3.setOpaque(true);
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel3.setBackground(new java.awt.Color(255, 51, 51));
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
                jLabel3.setIcon(hoverIcon9);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel3.setBackground(new java.awt.Color(255, 255, 255));
                jLabel3.setForeground(new java.awt.Color(0, 0, 0));
                jLabel3.setIcon(normalIcon9);

            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/logo (1).png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/transaction.png"))); // NOI18N
        ImageIcon normalIcon20 = new ImageIcon(getClass().getResource("/org/images/transaction.png"));
        ImageIcon hoverIcon20 = new ImageIcon(getClass().getResource("/org/images/transaction(1).png"));
        jLabel6.setText("Transactions");
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                All_Sell_Transactions AllTransactionsFrame = new All_Sell_Transactions(welcomeMessage);
                AllTransactionsFrame.setVisible(true);
                AllTransactionsFrame.pack();
                AllTransactionsFrame.setLocationRelativeTo(null);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel6.setOpaque(true);
                jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel6.setBackground(new java.awt.Color(255, 255, 255));
                jLabel6.setForeground(new java.awt.Color(0, 0, 0));
                jLabel6.setIcon(hoverIcon20);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel6.setBackground(new java.awt.Color(255, 51, 51));
                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                jLabel6.setIcon(normalIcon20);

            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/logout(1).png"))); // NOI18N
        ImageIcon normalIcon21 = new ImageIcon(getClass().getResource("/org/images/logout(1).png"));
        ImageIcon hoverIcon21 = new ImageIcon(getClass().getResource("/org/images/logout(2).png"));
        jLabel9.setText("Déconnexion");
        jLabel9.setOpaque(true);
        jLabel9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Home homeFrame = new Home();
                homeFrame.setVisible(true);
                homeFrame.setLocationRelativeTo(null);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel9.setOpaque(true);
                jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel9.setBackground(new java.awt.Color(255, 255, 255));
                jLabel9.setForeground(new java.awt.Color(0, 0, 0));
                jLabel9.setIcon(hoverIcon21);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel9.setBackground(new java.awt.Color(255, 51, 51));
                jLabel9.setForeground(new java.awt.Color(255, 255, 255));
                jLabel9.setIcon(normalIcon21);

            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable2.setBackground(new java.awt.Color(0, 0, 0));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "id", "Marque", "Modèle", "Plaque", "Annèe", "Image", "Prix (vendre)", "Prix (Louer)", "Status"
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

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/all(1)_sub.png"))); // NOI18N
        ImageIcon normalIcon3 = new ImageIcon(getClass().getResource("/org/images/all(1)_sub.png"));
        ImageIcon hoverIcon3 = new ImageIcon(getClass().getResource("/org/images/all(2)_sub.png"));
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                All_Vehicles AllVehiclesFrame = new All_Vehicles(welcomeMessage);
                AllVehiclesFrame.setLocationRelativeTo(null);
                AllVehiclesFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel5.setBackground(new java.awt.Color(255, 51, 51));
                jLabel5.setIcon(hoverIcon3);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel5.setBackground(new java.awt.Color(255, 255, 255));
                jLabel5.setIcon(normalIcon3);

            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
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
                All_Sell_Vehicles AllSellVehiclesFrame = new All_Sell_Vehicles(welcomeMessage);
                AllSellVehiclesFrame.setLocationRelativeTo(null);
                AllSellVehiclesFrame.setVisible(true);

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
                jLabel11.setBackground(new java.awt.Color(255, 51, 51));
                jLabel11.setIcon(normalIcon4);

            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
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
                All_Loan_Vehicles AllLoanVehiclesFrame = new All_Loan_Vehicles(welcomeMessage);
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
                jLabel12.setBackground(new java.awt.Color(255, 255, 255));
                jLabel12.setIcon(normalIcon5);

            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/trash_sub.png"))); // NOI18N
        ImageIcon normalIcon6 = new ImageIcon(getClass().getResource("/org/images/trash_sub.png"));
        ImageIcon hoverIcon6 = new ImageIcon(getClass().getResource("/org/images/trash(1)_sub.png"));
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jLabel13.setOpaque(true);
        jLabel13.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                All_Trash_Vehicles AllTrashVehiclesFrame = new All_Trash_Vehicles(welcomeMessage);
                AllTrashVehiclesFrame.setLocationRelativeTo(null);
                AllTrashVehiclesFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel13.setBackground(new java.awt.Color(255, 51, 51));
                jLabel13.setIcon(hoverIcon6);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel13.setBackground(new java.awt.Color(255, 255, 255));
                jLabel13.setIcon(normalIcon6);

            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText(welcomeMessage);

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/images/car(2).png"))); // NOI18N
        ImageIcon normalIcon = new ImageIcon(getClass().getResource("/org/images/car(2).png"));
        ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/org/images/car(3).png"));
        jLabel8.setText("Ajoutez un véhicule");
        jLabel8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Add_Vehicles AddVehiclesFrame = new Add_Vehicles(welcomeMessage);
                AddVehiclesFrame.setLocationRelativeTo(null);
                AddVehiclesFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel8.setForeground(new java.awt.Color(255, 255, 255));
                jLabel8.setIcon(hoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel8.setForeground(new java.awt.Color(0, 0, 0));
                jLabel8.setIcon(normalIcon);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(390, 390, 390)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(206, 206, 206))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 780, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        String username = "admin"; // Remplacez par la logique pour obtenir le nom d'utilisateur
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
            java.util.logging.Logger.getLogger(All_Sell_Vehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(All_Sell_Vehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(All_Sell_Vehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(All_Sell_Vehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        String finalMessage = welcomeMessage;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new All_Sell_Vehicles(finalMessage).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
