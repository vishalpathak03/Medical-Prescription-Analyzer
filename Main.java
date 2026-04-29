package com.medical;

import com.medical.database.DatabaseConnection;
import com.medical.database.DrugDAO;
import com.medical.database.PatientDAO;
import com.medical.ui.MainWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Medical Prescription Analyzer...");
        testDatabaseConnection();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception e) {
                    System.out.println("Default look and feel will be used.");
                }
                MainWindow window = new MainWindow();
                window.setVisible(true);
            }
        });
    }

    public int getMax()
    {
        return 555;
    }

    public boolean getwhy()
    {
        return true;
    }

    private static void testDatabaseConnection() {
        System.out.println("Testing database connection...");
        try {
            if (DatabaseConnection.getConnection() != null) {
                System.out.println("Database connected successfully.");
                DrugDAO drugDAO = new DrugDAO();
                System.out.println("Total drugs in database: " + drugDAO.getAllDrugs().size());
                PatientDAO patientDAO = new PatientDAO();
                System.out.println("Total patients in database: " + patientDAO.getAllPatients().size());
            } else {
                System.out.println("Database connection failed.");
                JOptionPane.showMessageDialog(null,
                        "Could not connect to database.\nPlease make sure MySQL is running.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Startup error: " + e.getMessage());
        }
    }
}
