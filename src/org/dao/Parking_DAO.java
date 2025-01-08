/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.dao;

/**
 *
 * @author an
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.connect.DBconnect;
import org.mod.Users_mod;
import org.mod.Vehicles_mod;
import org.mod.Basket_mod;

public class Parking_DAO {

    private final DBconnect dbConnect;
    private PreparedStatement statement;
    private ResultSet rs;

    public Parking_DAO(DBconnect dbConnect) {
        this.dbConnect = dbConnect;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean isTableEmpty() {
        String query = "SELECT COUNT(*) FROM users"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table users : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean NoClients() {
        String query = "SELECT COUNT(*) FROM users WHERE role = 2 AND is_active IN (1, 2)"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table users : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    public boolean NoAllowClients() {
        String query = "SELECT COUNT(*) FROM users WHERE role = 2 AND is_active = 1"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table users : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    public boolean NoBlockClients() {
        String query = "SELECT COUNT(*) FROM users WHERE role = 2 AND is_active = 2"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table users : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    public boolean EmptyTrashClients() {
        String query = "SELECT COUNT(*) FROM users WHERE role = 2 AND is_active = 0"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table users : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean UsersExist(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try {
            dbConnect.connect();

            try (PreparedStatement localStatement = dbConnect.getConnection().prepareStatement(query)) {
                localStatement.setString(1, username);
                try (ResultSet localRs = localStatement.executeQuery()) {
                    if (localRs.next()) {
                        return localRs.getLong(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'existence de personnes : " + e.getMessage());
        } finally {
            try {
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion à la base de données : " + e.getMessage());
            }
        }
        return false;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public void addSuperAdmin(Users_mod users) throws SQLException {
        String sql = "INSERT INTO users (l_name, f_name, username, email, password, role, last_login, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, users.getL_name());
        statement.setString(2, users.getF_name());
        statement.setString(3, users.getUsername());
        statement.setString(4, users.getEmail());
        statement.setString(5, users.getPassword());
        statement.setInt(6, users.getRole());
        statement.setString(7, users.getLast_login());
        statement.setInt(8, users.getIs_active());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Super administrateur ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout du super administrateur.");
        }
    }

    public void addClients(Users_mod users) throws SQLException {
        String sql = "INSERT INTO users (l_name, f_name, username, email, password, role, last_login, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, users.getL_name());
        statement.setString(2, users.getF_name());
        statement.setString(3, users.getUsername());
        statement.setString(4, users.getEmail());
        statement.setString(5, users.getPassword());
        statement.setInt(6, users.getRole());
        statement.setString(7, users.getLast_login());
        statement.setInt(8, users.getIs_active());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Client ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout du client.");
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateLastNameClients(Users_mod modln) throws SQLException {
        String sql = "UPDATE users SET l_name = ? WHERE id = ?";
        dbConnect.connect();
        int id = modln.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, modln.getL_name());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateFirstNameClients(Users_mod modfn) throws SQLException {
        String sql = "UPDATE users SET f_name = ? WHERE id = ?";
        dbConnect.connect();
        int id = modfn.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, modfn.getF_name());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateUserNameClients(Users_mod moduname) throws SQLException {
        String sql = "UPDATE users SET username = ? WHERE id = ?";
        dbConnect.connect();
        int id = moduname.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, moduname.getUsername());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateUserEmailClients(Users_mod modmail) throws SQLException {
        String sql = "UPDATE users SET email = ? WHERE id = ?";
        dbConnect.connect();
        int id = modmail.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, modmail.getEmail());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }

    public List<Users_mod> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users WHERE role = 2 AND is_active IN (0, 1, 2)";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getAllTrashUsers() throws SQLException {
        String sql = "SELECT * FROM users WHERE role IN (2, 3) AND is_active = 0";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getAllActiveUsers() throws SQLException {
        String sql = "SELECT * FROM users WHERE role IN (2, 3) AND is_active = 1";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getBlockUsers() throws SQLException {
        String sql = "SELECT * FROM users WHERE role IN (2, 3) AND is_active = 2 ";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getAllAdmins() throws SQLException {
        String sql = "SELECT * FROM users WHERE role = 2 AND is_active IN (0, 1, 2)";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getTrashAdmins() throws SQLException {
        String sql = "SELECT * FROM users WHERE role = 2 AND is_active = 0";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getAllActiveAdmins() throws SQLException {
        String sql = "SELECT * FROM users WHERE role = 2 AND is_active = 1";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getBlockAdmins() throws SQLException {
        String sql = "SELECT * FROM users WHERE role = 2 AND is_active = 2";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getAllClients() throws SQLException {
        String sql = "SELECT * FROM users WHERE role = 2 AND is_active IN (1, 2)";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getTrashClient() throws SQLException {
        String sql = "SELECT * FROM users WHERE role = 2 AND is_active = 0";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getActiveClient() throws SQLException {
        String sql = "SELECT * FROM users WHERE role = 2 AND is_active = 1";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Users_mod> getBlockClient() throws SQLException {
        String sql = "SELECT * FROM users WHERE role = 2 AND is_active = 2";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Users_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String l_name = rs.getString("l_name");
            String f_name = rs.getString("f_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String last_login = rs.getString("last_login");
            int is_active = rs.getInt("is_active");

            Users_mod list = new Users_mod(id, l_name, f_name, username, email, password, role, last_login, is_active);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public boolean SureDeletePeoples(Users_mod emp) throws SQLException {
        if (emp == null) {
            throw new IllegalArgumentException("L'objet Users_mod ne peut pas être null");
        }

        String sql = "UPDATE users SET is_active = ? WHERE id = ?";
        dbConnect.connect();

        int id = emp.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean SureErasePeoples(Users_mod emp) throws SQLException {
        if (emp == null) {
            throw new IllegalArgumentException("L'objet Users_mod ne peut pas être null");
        }

        String sql = "DELETE FROM users WHERE id = ?";
        dbConnect.connect();

        int id = emp.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean SureRestoreClients(Users_mod emp) throws SQLException {
        if (emp == null) {
            throw new IllegalArgumentException("L'objet Users_mod ne peut pas être null");
        }

        String sql = "UPDATE users SET is_active = ? WHERE id = ?";
        dbConnect.connect();

        int id = emp.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean BlockClients(Users_mod emp) throws SQLException {
        if (emp == null) {
            throw new IllegalArgumentException("L'objet Users_mod ne peut pas être null");
        }

        String sql = "UPDATE users SET is_active = ? WHERE id = ?";
        dbConnect.connect();

        int id = emp.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 2);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean AllowClients(Users_mod emp) throws SQLException {
        if (emp == null) {
            throw new IllegalArgumentException("L'objet Users_mod ne peut pas être null");
        }

        String sql = "UPDATE users SET is_active = ? WHERE id = ?";
        dbConnect.connect();

        int id = emp.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean loginSuperAdmin(String username, String password) {
        String sqlSelect = "SELECT * FROM users WHERE BINARY username = ? AND password = ? AND role = 1";
        String sqlUpdateLastLogin = "UPDATE users SET last_login = ? WHERE BINARY username = ?";

        try {
            dbConnect.connect();

            statement = dbConnect.getConnection().prepareStatement(sqlSelect);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Connexion réussie.");

                PreparedStatement updateStatement = dbConnect.getConnection().prepareStatement(sqlUpdateLastLogin);
                updateStatement.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
                updateStatement.setString(2, username);
                updateStatement.executeUpdate();
                updateStatement.close();

                return true;
            } else {
                System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                dbConnect.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean loginAdmins(String username, String password) {
        String sql = "SELECT * FROM users WHERE BINARY username = ? AND password = ? AND role = 2";
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Connexion réussie.");
                return true;
            } else {
                System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                dbConnect.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean loginClients(String username, String password) {
        String sqlSelect = "SELECT * FROM users WHERE BINARY username = ? AND password = ? AND role = 2 AND is_active = 1";
        String sqlUpdateLastLogin = "UPDATE users SET last_login = ? WHERE BINARY username = ?";

        try {
            dbConnect.connect();

            statement = dbConnect.getConnection().prepareStatement(sqlSelect);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Connexion réussie.");

                PreparedStatement updateStatement = dbConnect.getConnection().prepareStatement(sqlUpdateLastLogin);
                updateStatement.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
                updateStatement.setString(2, username);
                updateStatement.executeUpdate();
                updateStatement.close();

                return true;
            } else {
                System.out.println("Nom d'utilisateur ou mot de passe incorrect, si le problème continue appelé le service clientel");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                dbConnect.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public Users_mod getUserByUsername(String username) {
        String query = "SELECT id, l_name, f_name, username, email, password, role, last_login, is_active "
                + "FROM users WHERE username = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Connexion à la base
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            statement.setString(1, username.trim()); // Paramètre username
            resultSet = statement.executeQuery();

            // Si un utilisateur est trouvé
            if (resultSet.next()) {
                return new Users_mod(
                        resultSet.getInt("id"),
                        resultSet.getString("l_name"),
                        resultSet.getString("f_name"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("role"),
                        resultSet.getString("last_login"),
                        resultSet.getInt("is_active")
                );
            } else {
                System.err.println("Aucun utilisateur trouvé pour ce nom d'utilisateur.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null; // Si aucun utilisateur trouvé
    }

    public String getAdminFullName(String username) {
        String query = "SELECT f_name, l_name FROM users WHERE username = ?";
        String fullName = "Nom inconnu";
        try {
            dbConnect.connect();
            try (PreparedStatement statement = dbConnect.getConnection().prepareStatement(query)) {
                statement.setString(1, username.trim());
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        String fName = rs.getString("f_name");
                        String lName = rs.getString("l_name");
                        fullName = fName + " " + lName;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du nom de l'administrateur : " + e.getMessage());
        } finally {
            try {
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion à la base de données : " + e.getMessage());
            }
        }
        return fullName;
    }

    public String getClientsFullName(String username) {
        String query = "SELECT f_name, l_name FROM users WHERE username = ?";
        String fullName = "Nom inconnu";
        try {
            dbConnect.connect();
            try (PreparedStatement statement = dbConnect.getConnection().prepareStatement(query)) {
                statement.setString(1, username.trim());
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        String fName = rs.getString("f_name");
                        String lName = rs.getString("l_name");
                        fullName = fName + " " + lName;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du nom du client : " + e.getMessage());
        } finally {
            try {
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion à la base de données : " + e.getMessage());
            }
        }
        return fullName;
    }

    public boolean resetPassword(String clientId, String hashedPassword) {
        String query = "UPDATE users SET password = ? WHERE id = ?";
        boolean isUpdated = false;

        try {
            dbConnect.connect(); // Connecter à la base de données

            try (PreparedStatement statement = dbConnect.getConnection().prepareStatement(query)) {
                statement.setString(1, hashedPassword); // Hachage du mot de passe
                statement.setString(2, clientId.trim()); // ID du client

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    isUpdated = true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
        } finally {
            try {
                dbConnect.disconnect(); // Déconnecter après l'opération
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion à la base de données : " + e.getMessage());
            }
        }

        return isUpdated;
    }

//      Vehicles
    public void addVehicles(Vehicles_mod veh) throws SQLException {
        String sql = "INSERT INTO vehicles (brand, model, plate, year, image, price, loan_price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        dbConnect.connect();
        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, veh.getBrand());
        statement.setString(2, veh.getModel());
        statement.setString(3, veh.getPlate());
        statement.setInt(4, veh.getYear());
        statement.setString(5, veh.getImage());
        statement.setLong(6, veh.getPrice());
        statement.setLong(7, veh.getLoan_price());
        statement.setInt(8, veh.getStatus());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Véhicule ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout du véhicule.");
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean VehiclesExist(String plate) {
        String query = "SELECT COUNT(*) FROM vehicles WHERE plate = ?";
        try {
            dbConnect.connect();

            try (PreparedStatement localStatement = dbConnect.getConnection().prepareStatement(query)) {
                localStatement.setString(1, plate);
                try (ResultSet localRs = localStatement.executeQuery()) {
                    if (localRs.next()) {
                        return localRs.getLong(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'existence du véhicule : " + e.getMessage());
        } finally {
            try {
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion à la base de données : " + e.getMessage());
            }
        }
        return false;
    }

    public List<Vehicles_mod> getAllVehicles() throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE status IN (1, 2)";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Vehicles_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String plate = rs.getString("plate");
            int year = rs.getInt("year");
            String image = rs.getString("image");
            Long price = rs.getLong("price");
            Long loan_price = rs.getLong("loan_price");
            int status = rs.getInt("status");

            Vehicles_mod list = new Vehicles_mod(id, brand, model, plate, year, image, price, loan_price, status);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Vehicles_mod> getAllSellVehicles() throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE status = 1";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Vehicles_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String plate = rs.getString("plate");
            int year = rs.getInt("year");
            String image = rs.getString("image");
            Long price = rs.getLong("price");
            Long loan_price = rs.getLong("loan_price");
            int status = rs.getInt("status");

            Vehicles_mod list = new Vehicles_mod(id, brand, model, plate, year, image, price, loan_price, status);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Vehicles_mod> getAllLoanVehicles() throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE status = 2";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Vehicles_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String plate = rs.getString("plate");
            int year = rs.getInt("year");
            String image = rs.getString("image");
            Long price = rs.getLong("price");
            Long loan_price = rs.getLong("loan_price");
            int status = rs.getInt("status");

            Vehicles_mod list = new Vehicles_mod(id, brand, model, plate, year, image, price, loan_price, status);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    public List<Vehicles_mod> getAllTrashVehicles() throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE status = 0";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Vehicles_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String plate = rs.getString("plate");
            int year = rs.getInt("year");
            String image = rs.getString("image");
            Long price = rs.getLong("price");
            Long loan_price = rs.getLong("loan_price");
            int status = rs.getInt("status");

            Vehicles_mod list = new Vehicles_mod(id, brand, model, plate, year, image, price, loan_price, status);
            lists.add(list);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean NoVehicles() {
        String query = "SELECT COUNT(*) FROM vehicles WHERE status IN (1, 2)"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table vehicles : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean NoSellVehicles() {
        String query = "SELECT COUNT(*) FROM vehicles WHERE status = 1"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table vehicles : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean NoLoanVehicles() {
        String query = "SELECT COUNT(*) FROM vehicles WHERE status = 2"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table vehicles : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    public boolean EmptyTrashVehicles() {
        String query = "SELECT COUNT(*) FROM vehicles WHERE status = 0"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table users : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    public boolean SureDeleteVehicles(Vehicles_mod veh) throws SQLException {
        if (veh == null) {
            throw new IllegalArgumentException("L'objet Vehicles_mod ne peut pas être null");
        }

        String sql = "UPDATE vehicles SET status = ? WHERE id = ?";
        dbConnect.connect();

        int id = veh.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean SureEraseVehicles(Vehicles_mod veh) throws SQLException {
        if (veh == null) {
            throw new IllegalArgumentException("L'objet Vehicles_mod ne peut pas être null");
        }

        String sql = "DELETE FROM vehicles WHERE id = ?";
        dbConnect.connect();

        int id = veh.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean LoanVehicles(Vehicles_mod veh) throws SQLException {
        if (veh == null) {
            throw new IllegalArgumentException("L'objet Vehicles_mod ne peut pas être null");
        }

        String sql = "UPDATE vehicles SET status = ? WHERE id = ?";
        dbConnect.connect();

        int id = veh.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 2);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean SellVehicles(Vehicles_mod veh) throws SQLException {
        if (veh == null) {
            throw new IllegalArgumentException("L'objet Vehicles_mod ne peut pas être null");
        }

        String sql = "UPDATE vehicles SET status = ? WHERE id = ?";
        dbConnect.connect();

        int id = veh.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean SureRestoreVehicles(Vehicles_mod veh) throws SQLException {
        if (veh == null) {
            throw new IllegalArgumentException("L'objet Vehicles_mod ne peut pas être null");
        }

        String sql = "UPDATE vehicles SET status = ? WHERE id = ?";
        dbConnect.connect();

        int id = veh.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean updateImageVehicles(int vehicleId, String imagePath) throws SQLException {
        String sql = "UPDATE vehicles SET image = ? WHERE id = ?";
        dbConnect.connect();
        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, imagePath); // Définit le chemin de l'image
        statement.setInt(2, vehicleId);    // Définit l'ID du véhicule

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated; // Retourne true si la mise à jour a réussi
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateBrandVehicles(Vehicles_mod modbrand) throws SQLException {
        String sql = "UPDATE vehicles SET brand = ? WHERE id = ?";
        dbConnect.connect();
        int id = modbrand.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, modbrand.getBrand());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateModelVehicles(Vehicles_mod modmodel) throws SQLException {
        String sql = "UPDATE vehicles SET model = ? WHERE id = ?";
        dbConnect.connect();
        int id = modmodel.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, modmodel.getModel());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updatePlateVehicles(Vehicles_mod modplate) throws SQLException {
        String sql = "UPDATE vehicles SET plate = ? WHERE id = ?";
        dbConnect.connect();
        int id = modplate.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setString(1, modplate.getPlate());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateYearVehicles(Vehicles_mod modyear) throws SQLException {
        String sql = "UPDATE vehicles SET year = ? WHERE id = ?";
        dbConnect.connect();
        int id = modyear.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, modyear.getYear());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updatePriceVehicles(Vehicles_mod modprice) throws SQLException {
        String sql = "UPDATE vehicles SET price = ? WHERE id = ?";
        dbConnect.connect();
        int id = modprice.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setLong(1, modprice.getPrice());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public long getPriceLoanById(int vehicleId) throws Exception {
        String query = "SELECT loan_price FROM vehicles WHERE id = ?";
        try {
            dbConnect.connect();
            // Préparation de la requête
            statement = dbConnect.getConnection().prepareStatement(query);
            statement.setInt(1, vehicleId);

            // Exécution de la requête et traitement du résultat
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getLong("loan_price");
            } else {
                throw new Exception("Véhicule introuvable avec l'ID: " + vehicleId);
            }
        } finally {
            // Fermeture des ressources
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            dbConnect.disconnect();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public long getPriceById(int vehicleId) throws Exception {
        String query = "SELECT price FROM vehicles WHERE id = ?";
        try {
            dbConnect.connect();
            // Préparation de la requête
            statement = dbConnect.getConnection().prepareStatement(query);
            statement.setInt(1, vehicleId);

            // Exécution de la requête et traitement du résultat
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getLong("price");
            } else {
                throw new Exception("Véhicule introuvable avec l'ID: " + vehicleId);
            }
        } finally {
            // Fermeture des ressources
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            dbConnect.disconnect();
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateLoanVehicles(Vehicles_mod modloan) throws SQLException {
        String sql = "UPDATE vehicles SET loan_price = ? WHERE id = ?";
        dbConnect.connect();
        int id = modloan.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setLong(1, modloan.getLoan_price());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnect.disconnect();
        return rowUpdated;
    }
//Basket

    public boolean addToBasket(Basket_mod bas) throws SQLException {
        String sql = "INSERT INTO basket (id_clients, id_vehicles, date, status) VALUES (?, ?, ?, ?)";

        dbConnect.connect();
        try (PreparedStatement statement = dbConnect.getConnection().prepareStatement(sql)) {
            statement.setInt(1, bas.getId_clients());
            statement.setInt(2, bas.getId_vehicles());
            statement.setString(3, bas.getDate());
            statement.setInt(4, bas.getStatus());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            dbConnect.disconnect();
        }
    }

    public Users_mod getClientsInfoByUsername(String username) throws SQLException {
        String query = "SELECT id, l_name, f_name, username, email, is_active FROM users WHERE username = ?";
        dbConnect.connect();
        try (PreparedStatement stmt = dbConnect.getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idClients = rs.getInt("id");
                    String lName = rs.getString("l_name");
                    String fName = rs.getString("f_name");
                    String email = rs.getString("email");
                    int status = rs.getInt("is_active");

                    return new Users_mod(idClients, lName, fName, username, email, null, status, null, 1);
                }
            }
        }
        dbConnect.disconnect();
        return null;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean NoSellBasket() {
        String query = "SELECT COUNT(*) FROM basket WHERE status = 1"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table basket : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; // Par défaut, retourne false en cas d'erreur
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean NoLoanBasket() {
        String query = "SELECT COUNT(*) FROM basket WHERE status = 2"; // Compte le nombre d'enregistrements dans la table users
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; // Condition respectée
                }
            }
            // Si la table est vide, retourne true
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table basket : " + e.getMessage());
        } finally {
            // Ferme les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; 
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean NoTrashBasket() {
        String query = "SELECT COUNT(*) FROM basket WHERE status = 0";
        try {
            dbConnect.connect();
            statement = dbConnect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; 
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table basket : " + e.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false; 
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Basket_mod> getAllSellBasket() throws SQLException {

        String sql = "SELECT "
                + "basket.id AS basket_id, "
                + "basket.id_clients AS client_id, "
                + "users.l_name AS last_name, "
                + "users.f_name AS first_name, "
                + "users.username AS username, "
                + "users.email AS email, "
                + "users.is_active AS is_active, "
                + "basket.id_vehicles AS vehicle_id, "
                + "vehicles.brand AS brand, "
                + "vehicles.model AS model, "
                + "vehicles.plate AS plate, "
                + "vehicles.year AS year, "
                + "vehicles.image AS image, "
                + "vehicles.price AS price, "
                + "vehicles.loan_price AS loan_price, "
                + "vehicles.status AS vehicle_status, "
                + "basket.date AS transaction_date, "
                + "basket.status AS status "
                + "FROM basket "
                + "INNER JOIN users ON basket.id_clients = users.id "
                + "INNER JOIN vehicles ON basket.id_vehicles = vehicles.id "
                + "WHERE basket.status = 1"; 
        dbConnect.connect();
        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Basket_mod> lists = new ArrayList<>();

        while (rs.next()) {

            int id = rs.getInt("basket_id");
            int clients_id = rs.getInt("client_id");
            String last_name = rs.getString("last_name");
            String first_name = rs.getString("first_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            int vehicles_id = rs.getInt("vehicle_id");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String plate = rs.getString("plate");
            int year = rs.getInt("year");
            String image = rs.getString("image");
            Long price = rs.getLong("price");
            Long loan_price = rs.getLong("loan_price");
            String date = rs.getString("transaction_date");
            int status_clients = rs.getInt("is_active");
            int status_vehicles = rs.getInt("vehicle_status");
            int status = rs.getInt("status");

            Basket_mod list = new Basket_mod(id, clients_id, last_name, first_name, username, email, vehicles_id, brand, model, plate, year, image, price, loan_price, date, status_clients, status_vehicles, status);

            lists.add(list);
        }
        rs.close();
        statement.close();
        dbConnect.disconnect();
        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Basket_mod> getAllLoanBasket() throws SQLException {

        String sql = "SELECT "
                + "basket.id AS basket_id, "
                + "basket.id_clients AS client_id, "
                + "users.l_name AS last_name, "
                + "users.f_name AS first_name, "
                + "users.username AS username, "
                + "users.email AS email, "
                + "users.is_active AS is_active, "
                + "basket.id_vehicles AS vehicle_id, "
                + "vehicles.brand AS brand, "
                + "vehicles.model AS model, "
                + "vehicles.plate AS plate, "
                + "vehicles.year AS year, "
                + "vehicles.image AS image, "
                + "vehicles.price AS price, "
                + "vehicles.loan_price AS loan_price, "
                + "vehicles.status AS vehicle_status, "
                + "basket.date AS transaction_date, "
                + "basket.status AS status "
                + "FROM basket "
                + "INNER JOIN users ON basket.id_clients = users.id "
                + "INNER JOIN vehicles ON basket.id_vehicles = vehicles.id "
                + "WHERE basket.status = 2";
        dbConnect.connect();
        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Basket_mod> lists = new ArrayList<>();

        while (rs.next()) {

            int id = rs.getInt("basket_id");
            int clients_id = rs.getInt("client_id");
            String last_name = rs.getString("last_name");
            String first_name = rs.getString("first_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            int vehicles_id = rs.getInt("vehicle_id");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String plate = rs.getString("plate");
            int year = rs.getInt("year");
            String image = rs.getString("image");
            Long price = rs.getLong("price");
            Long loan_price = rs.getLong("loan_price");
            String date = rs.getString("transaction_date");
            int status_clients = rs.getInt("is_active");
            int status_vehicles = rs.getInt("vehicle_status");
            int status = rs.getInt("status");

            Basket_mod list = new Basket_mod(id, clients_id, last_name, first_name, username, email, vehicles_id, brand, model, plate, year, image, price, loan_price, date, status_clients, status_vehicles, status);

            lists.add(list);
        }
        rs.close();
        statement.close();
        dbConnect.disconnect();
        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Basket_mod> getAllTrashBasket() throws SQLException {

        String sql = "SELECT "
                + "basket.id AS basket_id, "
                + "basket.id_clients AS client_id, "
                + "users.l_name AS last_name, "
                + "users.f_name AS first_name, "
                + "users.username AS username, "
                + "users.email AS email, "
                + "users.is_active AS is_active, "
                + "basket.id_vehicles AS vehicle_id, "
                + "vehicles.brand AS brand, "
                + "vehicles.model AS model, "
                + "vehicles.plate AS plate, "
                + "vehicles.year AS year, "
                + "vehicles.image AS image, "
                + "vehicles.price AS price, "
                + "vehicles.loan_price AS loan_price, "
                + "vehicles.status AS vehicle_status, "
                + "basket.date AS transaction_date, "
                + "basket.status AS status "
                + "FROM basket "
                + "INNER JOIN users ON basket.id_clients = users.id "
                + "INNER JOIN vehicles ON basket.id_vehicles = vehicles.id "
                + "WHERE basket.status = 0";
        dbConnect.connect();
        statement = dbConnect.getConnection().prepareStatement(sql);
        rs = statement.executeQuery();

        List<Basket_mod> lists = new ArrayList<>();

        while (rs.next()) {

            int id = rs.getInt("basket_id");
            int clients_id = rs.getInt("client_id");
            String last_name = rs.getString("last_name");
            String first_name = rs.getString("first_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            int vehicles_id = rs.getInt("vehicle_id");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String plate = rs.getString("plate");
            int year = rs.getInt("year");
            String image = rs.getString("image");
            Long price = rs.getLong("price");
            Long loan_price = rs.getLong("loan_price");
            String date = rs.getString("transaction_date");
            int status_clients = rs.getInt("is_active");
            int status_vehicles = rs.getInt("vehicle_status");
            int status = rs.getInt("status");

            Basket_mod list = new Basket_mod(id, clients_id, last_name, first_name, username, email, vehicles_id, brand, model, plate, year, image, price, loan_price, date, status_clients, status_vehicles, status);

            lists.add(list);
        }
        rs.close();
        statement.close();
        dbConnect.disconnect();
        return lists;
    }

    public List<Basket_mod> getClientsPurchaseBasket(int clientId) throws SQLException {
        String sql = "SELECT "
                + "basket.id AS basket_id, "
                + "basket.id_clients AS client_id, "
                + "users.l_name AS last_name, "
                + "users.f_name AS first_name, "
                + "users.username AS username, "
                + "users.email AS email, "
                + "users.is_active AS is_active, "
                + "basket.id_vehicles AS vehicle_id, "
                + "vehicles.brand AS brand, "
                + "vehicles.model AS model, "
                + "vehicles.plate AS plate, "
                + "vehicles.year AS year, "
                + "vehicles.image AS image, "
                + "vehicles.price AS price, "
                + "vehicles.loan_price AS loan_price, "
                + "vehicles.status AS vehicle_status, "
                + "basket.date AS transaction_date, "
                + "basket.status AS status "
                + "FROM basket "
                + "INNER JOIN users ON basket.id_clients = users.id "
                + "INNER JOIN vehicles ON basket.id_vehicles = vehicles.id "
                + "WHERE users.is_active = 1 AND vehicles.status = 1 AND basket.status = 1 "
                + "AND basket.id_clients = ?";

        dbConnect.connect();
        PreparedStatement statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, clientId);
        ResultSet rs = statement.executeQuery();

        List<Basket_mod> lists = new ArrayList<>();

        while (rs.next()) {
            Basket_mod basket = new Basket_mod(
                    rs.getInt("basket_id"),
                    rs.getInt("client_id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getInt("vehicle_id"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("plate"),
                    rs.getInt("year"),
                    rs.getString("image"),
                    rs.getLong("price"),
                    rs.getLong("loan_price"),
                    rs.getString("transaction_date"),
                    rs.getInt("is_active"),
                    rs.getInt("vehicle_status"),
                    rs.getInt("status")
            );

            lists.add(basket);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();
        return lists;
    }

    public boolean SureDeleteTransactions(Basket_mod bas) throws SQLException {
        if (bas == null) {
            throw new IllegalArgumentException("L'objet Basket_mod ne peut pas être null");
        }

        String sql = "UPDATE basket SET status = ? WHERE id = ?";
        dbConnect.connect();

        int id = bas.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean LoanTransactions(Basket_mod bas) throws SQLException {
        if (bas == null) {
            throw new IllegalArgumentException("L'objet Basket_mod ne peut pas être null");
        }

        String sql = "UPDATE basket SET status = ? WHERE id = ?";
        dbConnect.connect();

        int id = bas.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 2);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean SellTransactions(Basket_mod bas) throws SQLException {
        if (bas == null) {
            throw new IllegalArgumentException("L'objet Basket_mod ne peut pas être null");
        }

        String sql = "UPDATE basket SET status = ? WHERE id = ?";
        dbConnect.connect();

        int id = bas.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean SureEraseTransactions(Basket_mod bas) throws SQLException {
        if (bas == null) {
            throw new IllegalArgumentException("L'objet Basket_mod ne peut pas être null");
        }

        String sql = "DELETE FROM basket WHERE id = ?";
        dbConnect.connect();

        int id = bas.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    public boolean SureRestoreTransactions(Basket_mod bas) throws SQLException {
        if (bas == null) {
            throw new IllegalArgumentException("L'objet Basket_mod ne peut pas être null");
        }

        String sql = "UPDATE basket SET status = ? WHERE id = ?";
        dbConnect.connect();

        int id = bas.getId();

        statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        dbConnect.disconnect();
        return rowDeleted;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean ExceedLoanBasket(Basket_mod basket) {
        String query = "SELECT COUNT(*) FROM basket WHERE id_clients = ? AND id_vehicles = ? AND status = 2";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            
            dbConnect.connect();

            statement = dbConnect.getConnection().prepareStatement(query);
            statement.setInt(1, basket.getId_clients());
            statement.setInt(2, basket.getId_vehicles());

            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);

                return count == 1;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table basket : " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean ExceedSellBasket(Basket_mod basket) {
        String query = "SELECT COUNT(*) FROM basket WHERE id_clients = ? AND id_vehicles = ? AND status = 1";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {

            dbConnect.connect();

            statement = dbConnect.getConnection().prepareStatement(query);
            statement.setInt(1, basket.getId_clients());
            statement.setInt(2, basket.getId_vehicles());

            rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 1;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la table basket : " + e.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                dbConnect.disconnect();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }

        return false;
    }

    public boolean hasNoOrders(int clientId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM basket "
                + "INNER JOIN users ON basket.id_clients = users.id "
                + "INNER JOIN vehicles ON basket.id_vehicles = vehicles.id "
                + "WHERE users.is_active = 1 AND vehicles.status = 1 AND basket.status = 1 "
                + "AND basket.id_clients = ?";

        dbConnect.connect();
        PreparedStatement statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, clientId);

        ResultSet rs = statement.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return count == 0;
    }

    public boolean hasNoLoanOrders(int clientId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM basket "
                + "INNER JOIN users ON basket.id_clients = users.id "
                + "INNER JOIN vehicles ON basket.id_vehicles = vehicles.id "
                + "WHERE users.is_active = 1 AND vehicles.status = 2 AND basket.status = 2 "
                + "AND basket.id_clients = ?";

        dbConnect.connect();
        PreparedStatement statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, clientId);

        ResultSet rs = statement.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        statement.close();
        dbConnect.disconnect();

        return count == 0;
    }

    public List<Basket_mod> getClientsLoanBasket(int clientId) throws SQLException {
        String sql = "SELECT "
                + "basket.id AS basket_id, "
                + "basket.id_clients AS client_id, "
                + "users.l_name AS last_name, "
                + "users.f_name AS first_name, "
                + "users.username AS username, "
                + "users.email AS email, "
                + "users.is_active AS is_active, "
                + "basket.id_vehicles AS vehicle_id, "
                + "vehicles.brand AS brand, "
                + "vehicles.model AS model, "
                + "vehicles.plate AS plate, "
                + "vehicles.year AS year, "
                + "vehicles.image AS image, "
                + "vehicles.price AS price, "
                + "vehicles.loan_price AS loan_price, "
                + "vehicles.status AS vehicle_status, "
                + "basket.date AS transaction_date, "
                + "basket.status AS status "
                + "FROM basket "
                + "INNER JOIN users ON basket.id_clients = users.id "
                + "INNER JOIN vehicles ON basket.id_vehicles = vehicles.id "
                + "WHERE users.is_active = 1 AND vehicles.status = 2 AND basket.status = 2 "
                + "AND basket.id_clients = ?";

        dbConnect.connect();
        PreparedStatement statement = dbConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, clientId);
        ResultSet rs = statement.executeQuery();

        List<Basket_mod> lists = new ArrayList<>();

        while (rs.next()) {
            Basket_mod basket = new Basket_mod(
                    rs.getInt("basket_id"),
                    rs.getInt("client_id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getInt("vehicle_id"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("plate"),
                    rs.getInt("year"),
                    rs.getString("image"),
                    rs.getLong("price"),
                    rs.getLong("loan_price"),
                    rs.getString("transaction_date"),
                    rs.getInt("is_active"),
                    rs.getInt("vehicle_status"),
                    rs.getInt("status")
            );

            lists.add(basket);
        }

        rs.close();
        statement.close();
        dbConnect.disconnect();
        return lists;
    }

    public int addClientAndGetId(Users_mod users) throws SQLException {
        String sql = "INSERT INTO users (l_name, f_name, username, email, password, role, last_login, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        dbConnect.connect();

        statement = dbConnect.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, users.getL_name());
        statement.setString(2, users.getF_name());
        statement.setString(3, users.getUsername());
        statement.setString(4, users.getEmail());
        statement.setString(5, users.getPassword());
        statement.setInt(6, users.getRole());
        statement.setString(7, users.getLast_login());
        statement.setInt(8, users.getIs_active());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

}
