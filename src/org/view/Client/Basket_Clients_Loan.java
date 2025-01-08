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
import org.view.Home.Home;

/**
 *
 * @author an
 */
public class Basket_Clients_Loan extends javax.swing.JFrame {

    private static Parking_ctrl controller;
    private String welcomeMessage;
    private String clientId;
    private String LastName;
    private String FirstName;
    private String username;
    private String Email;
    private String Status;

    /**
     * Creates new form Basket_Clients_Loan
     *
     * @param welcomeMessage
     * @param clientId
     * @param username
     * @param FirstName
     * @param LastName
     * @param Email
     * @param Status
     */
    public Basket_Clients_Loan(String welcomeMessage, String clientId, String LastName, String FirstName, String username, String Email, String Status) {
        initComponents();
        this.welcomeMessage = welcomeMessage;
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
        ClickTransactions();
        System.out.println("Id du client : " + clientId);
    }

    private void ClickTransactions() {
        jTable2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable2.getSelectedRow();
                if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(e)) {
                    try {

                        List<Basket_mod> basket = controller.getClientsLoanBasket(Integer.parseInt(clientId));
                        Basket_mod bas = basket.get(selectedRow);

                        if (bas != null) {
                            String number = String.valueOf(bas.getId());
                            String number_clients = String.valueOf(bas.getId_clients());
                            String last_name = bas.getL_name();
                            String first_name = bas.getF_name();
                            String username = bas.getUsername();
                            String email = bas.getEmail();
                            String number_vehicles = String.valueOf(bas.getId_vehicles());
                            String brand = bas.getBrand();
                            String model = bas.getModel();
                            String plate = bas.getPlate();
                            String year = String.valueOf(bas.getYear());
                            String imagePath = bas.getImage(); // Chemin absolu de l'image
                            String price = String.valueOf(bas.getPrice());
                            String loan_price = String.valueOf(bas.getLoan_price());
                            String date = bas.getDate();
                            int status_clients = bas.getStatus_clients();
                            int status_vehicles = bas.getStatus_vehicles();
                            int status = bas.getStatus();

                            String statut = (status == 0) ? "Supprimer" : (status == 1) ? "Vente" : (status == 2) ? "Location" : "Inconnu";
                            String statut_clients = (status_clients == 0) ? "Supprimer" : (status_clients == 1) ? "Autoriser" : (status_clients == 2) ? "Bloquer" : "Inconnu";
                            String statut_vehicles = (status_vehicles == 0) ? "Supprimer" : (status_vehicles == 1) ? "À vendre" : (status_vehicles == 2) ? "À louer" : "Inconnu";

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
                            textPanel.add(new JLabel("Date : " + date));

                            panel.add(textPanel, BorderLayout.CENTER);
                            panel.add(imageLabel, BorderLayout.EAST);

                            // Définir les options dynamiques
                            int option = JOptionPane.showOptionDialog(Basket_Clients_Loan.this,
                                    panel,
                                    "Détails de la tansactions",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null,
                                    new Object[]{"Supprimer", "Annuler"},
                                    "Retour"
                            );

                            // Actions basées sur l'option sélectionnée
                            switch (option) {
                                case 0: // "Supprimer"
                                    DeleteTransactionsQuestion(bas);
                                    break;
                                case 1: // "À louer" ou "À vendre"

                                    break;

                            }
                        } else {
                            JOptionPane.showMessageDialog(Basket_Clients_Loan.this, "Erreur : la transaction sélectionné n'a pas pu être trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Basket_Clients_Loan.this, "Erreur lors de la récupération des données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public void populateTable() {

        boolean NoSellBasket = controller.hasNoLoanOrders(Integer.parseInt(clientId));
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        if (NoSellBasket) {
            String emptyMessage = "Panier de location vide.";
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
            List<Basket_mod> BasketList = controller.getClientsLoanBasket(Integer.parseInt(clientId));

            int rowNumber = 1;

            for (Basket_mod bas : BasketList) {

                String statut = bas.getStatus() == 0 ? "Annuler" : bas.getStatus() == 1 ? "Vente" : bas.getStatus() == 2 ? "loacation" : "Inconnu";
                String statut_clients = bas.getStatus_clients() == 0 ? "Supprimer" : bas.getStatus_clients() == 1 ? "Autoriser" : bas.getStatus_clients() == 2 ? "Bloquer" : "Inconnu";
                String statut_vehicles = bas.getStatus_vehicles() == 0 ? "Supprimer" : bas.getStatus_vehicles() == 1 ? "A vendre" : bas.getStatus_vehicles() == 2 ? "A louer" : "Inconnu";

                Object[] rowData = {
                    rowNumber++,
                    bas.getId(),
                    bas.getId_clients(),
                    bas.getL_name(),
                    bas.getF_name(),
                    bas.getUsername(),
                    bas.getEmail(),
                    bas.getId_vehicles(),
                    bas.getBrand(),
                    bas.getModel(),
                    bas.getPlate(),
                    bas.getYear(),
                    bas.getImage(),
                    bas.getPrice() + " $",
                    bas.getLoan_price(),
                    bas.getDate(),
                    statut_clients,
                    statut_vehicles,
                    statut,};

                model.addRow(rowData);
            }

            jTable2.getColumnModel().getColumn(1).setMinWidth(0);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(1).setWidth(0);

            jTable2.getColumnModel().getColumn(2).setMinWidth(0);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(2).setWidth(0);

            jTable2.getColumnModel().getColumn(3).setMinWidth(0);
            jTable2.getColumnModel().getColumn(3).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(3).setWidth(0);

            jTable2.getColumnModel().getColumn(4).setMinWidth(0);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(4).setWidth(0);

            jTable2.getColumnModel().getColumn(5).setMinWidth(0);
            jTable2.getColumnModel().getColumn(5).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(5).setWidth(0);

            jTable2.getColumnModel().getColumn(6).setMinWidth(0);
            jTable2.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(6).setWidth(0);

            jTable2.getColumnModel().getColumn(7).setMinWidth(0);
            jTable2.getColumnModel().getColumn(7).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(7).setWidth(0);

            jTable2.getColumnModel().getColumn(12).setMinWidth(0);
            jTable2.getColumnModel().getColumn(12).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(12).setWidth(0);

            jTable2.getColumnModel().getColumn(14).setMinWidth(0);
            jTable2.getColumnModel().getColumn(14).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(14).setWidth(0);

            jTable2.getColumnModel().getColumn(16).setMinWidth(0);
            jTable2.getColumnModel().getColumn(16).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(16).setWidth(0);

            jTable2.getColumnModel().getColumn(17).setMinWidth(0);
            jTable2.getColumnModel().getColumn(17).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(17).setWidth(0);

            jTable2.getColumnModel().getColumn(18).setMinWidth(0);
            jTable2.getColumnModel().getColumn(18).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(18).setWidth(0);

        }

    }

    private void DeleteTransactionsQuestion(Basket_mod bas) {

        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement supprimez cette tansaction ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Non"
        );

        if (option == JOptionPane.YES_OPTION) {
            handleDeleteTransactions(bas);
        } else if (option == JOptionPane.NO_OPTION) {

            return;
        } else {

            JOptionPane.showMessageDialog(null,
                    "Erreur : choix non reconnu.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void handleDeleteTransactions(Basket_mod bas) {
        boolean success = controller.SureDeleteTransactions(bas);

        if (success) {
            JOptionPane.showMessageDialog(this, "La transaction a été supprimés.", "Information", JOptionPane.INFORMATION_MESSAGE);
            populateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion de la transaction.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        setTitle("Mes locations");
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
                Basket_Clients_Buy AllPurchaseClientFrame = new Basket_Clients_Buy(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                AllPurchaseClientFrame.setLocationRelativeTo(null);
                AllPurchaseClientFrame.setVisible(true);
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
                Basket_Clients_Loan AllLoanClientFrame = new Basket_Clients_Loan(welcomeMessage, clientId, LastName, FirstName, username, Email, Status);
                AllLoanClientFrame.setLocationRelativeTo(null);
                AllLoanClientFrame.setVisible(true);

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
                "#", "id", "id_clients", "Nom", "Prénom", "Nom d'utilisateur", "E-mail", "id_vehicles", "Marque", "Modèle", "Plaque", "Annèe", "Image", "Prix (vendre)", "Prix (Louer)", "Date transaction", "Status Client", "Status Vehicules", "Status transaction"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
                        .addGap(169, 169, 169)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(391, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(626, Short.MAX_VALUE))
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
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
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
                jLabel2.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
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
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
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
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel7.setForeground(new java.awt.Color(0, 0, 0));
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
                .addGap(158, 158, 158)
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
            java.util.logging.Logger.getLogger(Basket_Clients_Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Basket_Clients_Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Basket_Clients_Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Basket_Clients_Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        String finalMessage = welcomeMessage;
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Basket_Clients_Loan(finalMessage, "", "", "", "", "", "").setVisible(true);
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
