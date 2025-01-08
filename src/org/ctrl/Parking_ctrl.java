/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ctrl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.connect.DBconnect;
import org.mod.Users_mod;
import org.dao.Parking_DAO;
import org.mod.Basket_mod;
import org.mod.Vehicles_mod;

/**
 *
 * @author an
 */
public class Parking_ctrl {

    private final Parking_DAO pDAO;

    public Parking_ctrl(String host, String user, String password) {
        DBconnect dbConnect = new DBconnect(host, user, password);
        this.pDAO = new Parking_DAO(dbConnect);
    }

    public boolean isTableEmpty() {
        return pDAO.isTableEmpty();
    }

    public boolean NoClients() {
        return pDAO.NoClients();
    }

    public boolean NoAllowClients() {
        return pDAO.NoAllowClients();
    }

    public boolean NoBlockClients() {
        return pDAO.NoBlockClients();
    }

    public boolean EmptyTrashClients() {
        return pDAO.EmptyTrashClients();
    }

    public boolean UsersExist(String username) {
        return pDAO.UsersExist(username);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void addSuperAdmin(Users_mod users) {
        try {
            pDAO.addSuperAdmin(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void addClients(Users_mod users) {
        try {
            pDAO.addClients(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public int addClientAndGetId(Users_mod users) {
    try {
        // Appel de la méthode DAO pour ajouter le client et récupérer l'ID généré
        return pDAO.addClientAndGetId(users); // Retourner l'ID généré
    } catch (SQLException e) {
        // Gestion des erreurs : affichage du message d'erreur
        System.err.println("Erreur lors de l'ajout du client : " + e.getMessage());
        e.printStackTrace();
    }
    return -1; // Retourner -1 en cas d'erreur
}


    @SuppressWarnings("CallToPrintStackTrace")
    public void updateLastNameClients(Users_mod modln) {
        try {
            pDAO.updateLastNameClients(modln);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateFirstNameClients(Users_mod modfn) {
        try {
            pDAO.updateFirstNameClients(modfn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateUserNameClients(Users_mod moduname) {
        try {
            pDAO.updateUserNameClients(moduname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateUserEmailClients(Users_mod modmail) {
        try {
            pDAO.updateUserEmailClients(modmail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Users_mod> getAllUsers() {
        List<Users_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Users_mod> getAllTrashUsers() {
        List<Users_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllTrashUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Users_mod> getAllActiveUsers() {
        List<Users_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllActiveUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Users_mod> getBlockUsers() {
        List<Users_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getBlockUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Users_mod> getAllClients() {
        List<Users_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllClients();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Users_mod> getTrashClient() {
        List<Users_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getTrashClient();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Users_mod> getActiveClient() {
        List<Users_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getActiveClient();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Users_mod> getBlockClient() {
        List<Users_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getBlockClient();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean logSuperAdmin(Users_mod users) {
        try {
            return pDAO.loginSuperAdmin(users.getUsername(), users.getPassword().trim());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean logAdmins(Users_mod users) {
        try {
            return pDAO.loginAdmins(users.getUsername(), users.getPassword().trim());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean logClients(Users_mod users) {
        try {
            return pDAO.loginClients(users.getUsername(), users.getPassword().trim());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeletePeoples(Users_mod emp) {
        try {
            return pDAO.SureDeletePeoples(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureErasePeoples(Users_mod emp) {
        try {
            return pDAO.SureErasePeoples(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockClients(Users_mod emp) {
        try {
            return pDAO.BlockClients(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllowClients(Users_mod emp) {
        try {
            return pDAO.AllowClients(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureRestoreClients(Users_mod emp) {
        try {
            return pDAO.SureRestoreClients(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public String getWelcomeMessage(String username) {
        // Récupérer l'utilisateur via le DAO
        Users_mod user = pDAO.getUserByUsername(username);

        // Si l'utilisateur existe
        if (user != null) {
            return "Bienvenue, " + user.getF_name() + " " + user.getL_name() + "!";
        } else {
            return "Utilisateur introuvable. Veuillez vérifier vos informations.";
        }
    }

    public String fetchAdminFullName(String username) {
        String fullName = pDAO.getAdminFullName(username);
        if ("Nom inconnu".equals(fullName)) {
            System.out.println("Aucun administrateur trouvé pour l'username : " + username);
        } else {
            return fullName;
        }
        return fullName;
    }

    public String fetchClientsFullName(String username) {
        String fullName = pDAO.getClientsFullName(username);
        if ("Nom inconnu".equals(fullName)) {
            System.out.println("Aucun client trouvé pour l'username : " + username);
        } else {
            return fullName;
        }
        return fullName;
    }

    public boolean resetPassword(String clientId, String hashedPassword) {
        return pDAO.resetPassword(clientId, hashedPassword);
    }

//    Vehicles
    @SuppressWarnings("CallToPrintStackTrace")
    public void addVehicles(Vehicles_mod veh) {
        try {
            pDAO.addVehicles(veh);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean VehiclesExist(String plate) {
        return pDAO.VehiclesExist(plate);

    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Vehicles_mod> getAllVehicles() {
        List<Vehicles_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllVehicles();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Vehicles_mod> getAllSellVehicles() {
        List<Vehicles_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllSellVehicles();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Vehicles_mod> getAllLoanVehicles() {
        List<Vehicles_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllLoanVehicles();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Vehicles_mod> getAllTrashVehicles() {
        List<Vehicles_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllTrashVehicles();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    public boolean NoVehicles() {
        return pDAO.NoVehicles();
    }

    public boolean NoSellVehicles() {
        return pDAO.NoSellVehicles();
    }

    public boolean NoLoanVehicles() {
        return pDAO.NoLoanVehicles();
    }

    public boolean EmptyTrashVehicles() {
        return pDAO.EmptyTrashVehicles();
    }

    public boolean NoSellBasket() {
        return pDAO.NoSellBasket();
    }

    public boolean NoTrashBasket() {
        return pDAO.NoTrashBasket();
    }

    public boolean NoLoanBasket() {
        return pDAO.NoLoanBasket();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteVehicles(Vehicles_mod veh) {
        try {
            return pDAO.SureDeleteVehicles(veh);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureEraseVehicles(Vehicles_mod veh) {
        try {
            return pDAO.SureEraseVehicles(veh);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean LoanVehicles(Vehicles_mod veh) {
        try {
            return pDAO.LoanVehicles(veh);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SellVehicles(Vehicles_mod veh) {
        try {
            return pDAO.SellVehicles(veh);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureRestoreVehicles(Vehicles_mod veh) {
        try {
            return pDAO.SureRestoreVehicles(veh);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateImageVehicles(int vehicleId, String imagePath) {
        try {
            return pDAO.updateImageVehicles(vehicleId, imagePath); // Appel au DAO pour mettre à jour l'image
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // En cas d'erreur, retourner false
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateBrandVehicles(Vehicles_mod modbrand) {
        try {
            pDAO.updateBrandVehicles(modbrand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateModelVehicles(Vehicles_mod modmodel) {
        try {
            pDAO.updateModelVehicles(modmodel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updatePlateVehicles(Vehicles_mod modplate) {
        try {
            pDAO.updatePlateVehicles(modplate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateYearVehicles(Vehicles_mod modyear) {
        try {
            pDAO.updateYearVehicles(modyear);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updatePriceVehicles(Vehicles_mod modprice) {
        try {
            pDAO.updatePriceVehicles(modprice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateLoanVehicles(Vehicles_mod modloan) {
        try {
            pDAO.updateLoanVehicles(modloan);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public long getPriceLoanById(int vehicleId) throws Exception {
        try {
            return pDAO.getPriceLoanById(vehicleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Retourne une valeur d'erreur en cas de problème
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public long getPriceById(int vehicleId) throws Exception {
        try {
            return pDAO.getPriceById(vehicleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Retourne une valeur d'erreur en cas de problème
        }
    }

//    Basket
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean addBasket(Basket_mod bas) {
        try {
            return pDAO.addToBasket(bas);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public Users_mod getClientsInfoByUsername(String username) {
        try {
            return pDAO.getClientsInfoByUsername(username);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des informations utilisateur : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Basket_mod> getAllSellBasket() {
        List<Basket_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllSellBasket();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Basket_mod> getAllLoanBasket() {
        List<Basket_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllLoanBasket();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Basket_mod> getAllTrashBasket() {
        List<Basket_mod> lists = new ArrayList<>();
        try {

            lists = pDAO.getAllTrashBasket();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteTransactions(Basket_mod bas) {
        try {
            return pDAO.SureDeleteTransactions(bas);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean LoanTransactions(Basket_mod bas) {
        try {
            return pDAO.LoanTransactions(bas);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SellTransactions(Basket_mod bas) {
        try {
            return pDAO.SellTransactions(bas);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureEraseTransactions(Basket_mod bas) {
        try {
            return pDAO.SureEraseTransactions(bas);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureRestoreTransactions(Basket_mod bas) {
        try {
            return pDAO.SureRestoreTransactions(bas);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String checkLoanBasket(Basket_mod basket) {
        boolean exceedsLimit = pDAO.ExceedLoanBasket(basket);
        if (exceedsLimit) {
            return "Le panier dépasse la limite autorisée pour le client ID: " + basket.getId_clients();
        } else {
            return "Le panier est dans les limites autorisées pour le client ID: " + basket.getId_clients();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean hasNoOrders(int clientId) {
        boolean hasNoOrders = false;
        try {
            hasNoOrders = pDAO.hasNoOrders(clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasNoOrders;
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean hasNoLoanOrders(int clientId) {
        boolean hasNoOrders = false;
        try {
            hasNoOrders = pDAO.hasNoLoanOrders(clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasNoOrders;
    }

    public String checkSellBasket(Basket_mod basket) {
        boolean exceedsLimit = pDAO.ExceedSellBasket(basket);
        if (exceedsLimit) {
            return "Le panier dépasse la limite autorisée pour le client ID: " + basket.getId_clients();
        } else {
            return "Le panier est dans les limites autorisées pour le client ID: " + basket.getId_clients();
        }
    }

    public List<Basket_mod> getClientsPurchaseBasket(int clientId) {
        List<Basket_mod> lists = new ArrayList<>();

        try {
            lists = pDAO.getClientsPurchaseBasket(clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }
    
    public List<Basket_mod> getClientsLoanBasket(int clientId) {
        List<Basket_mod> lists = new ArrayList<>();

        try {
            lists = pDAO.getClientsLoanBasket(clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }
    
}
