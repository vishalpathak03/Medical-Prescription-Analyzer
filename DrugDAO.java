package com.medical.database;

import com.medical.models.Drug;
import java.sql.*;
import java.util.ArrayList;

public class DrugDAO {

    public Drug getDrugByName(String name) {
        Drug drug = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM drugs WHERE name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                drug = new Drug(
                    rs.getInt("drug_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("max_dosage"),
                    rs.getString("description")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching drug: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return drug;
    }

    public boolean getwhy()
    {
        return true;
    }

    public Drug getDrugById(int id) {
        Drug drug = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM drugs WHERE drug_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                drug = new Drug(
                    rs.getInt("drug_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("max_dosage"),
                    rs.getString("description")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching drug by id: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return drug;
    }

    public ArrayList<Drug> getAllDrugs() {
        ArrayList<Drug> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DatabaseConnection.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM drugs ORDER BY name");
            while (rs.next()) {
                list.add(new Drug(
                    rs.getInt("drug_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("max_dosage"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all drugs: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return list;
    }

    public ArrayList<Drug> getDrugsByCategory(String category) {
        ArrayList<Drug> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM drugs WHERE category = ?");
            ps.setString(1, category);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Drug(
                    rs.getInt("drug_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("max_dosage"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching drugs by category: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return list;
    }
}
