package com.medical.database;

import java.sql.*;
import java.util.ArrayList;

public class InteractionDAO {

    public String[] checkInteraction(int drug1Id, int drug2Id) {
        String[] result = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement(
                "SELECT severity, description, alternative FROM interactions " +
                "WHERE (drug1_id = ? AND drug2_id = ?) OR (drug1_id = ? AND drug2_id = ?)"
            );
            ps.setInt(1, drug1Id);
            ps.setInt(2, drug2Id);
            ps.setInt(3, drug2Id);
            ps.setInt(4, drug1Id);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new String[3];
                result[0] = rs.getString("severity");
                result[1] = rs.getString("description");
                result[2] = rs.getString("alternative");
            }
        } catch (SQLException e) {
            System.out.println("Error checking interaction: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return result;
    }

    public ArrayList<String[]> getAllInteractionsForDrug(int drugId) {
        ArrayList<String[]> interactions = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement(
                "SELECT d1.name as drug1, d2.name as drug2, i.severity, i.description, i.alternative " +
                "FROM interactions i " +
                "JOIN drugs d1 ON i.drug1_id = d1.drug_id " +
                "JOIN drugs d2 ON i.drug2_id = d2.drug_id " +
                "WHERE i.drug1_id = ? OR i.drug2_id = ?"
            );
            ps.setInt(1, drugId);
            ps.setInt(2, drugId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] row = new String[5];
                row[0] = rs.getString("drug1");
                row[1] = rs.getString("drug2");
                row[2] = rs.getString("severity");
                row[3] = rs.getString("description");
                row[4] = rs.getString("alternative");
                interactions.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching interactions: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return interactions;
    }
}