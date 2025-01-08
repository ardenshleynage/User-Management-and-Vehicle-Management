/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 *
 * @author an
 */
public class ConfigUtil {

    /**
     * Charge les propriétés à partir du chemin spécifié.
     *
     * @param filePath Chemin du fichier de configuration.
     * @return Un objet Properties contenant les clés et valeurs du fichier.
     */
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input); // Charger le contenu du fichier dans l'objet Properties
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier de configuration : " + e.getMessage());
        }
        return properties;
    }
}
