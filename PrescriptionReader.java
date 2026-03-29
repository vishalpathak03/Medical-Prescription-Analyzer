package com.medical.fileio;

import java.io.*;
import java.util.ArrayList;

public class PrescriptionReader {

    public ArrayList<String> readDrugsFromFile(String filePath) {
        ArrayList<String> drugNames = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty() && !trimmed.startsWith("#")) {
                    drugNames.add(trimmed);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Prescription file not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading prescription file: " + e.getMessage());
        }
        return drugNames;
    }

    public void writeSamplePrescriptionFile(String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("# Sample Prescription File");
            writer.newLine();
            writer.write("# Format: DrugName,Dosage,DurationDays");
            writer.newLine();
            writer.write("Aspirin,500,7");
            writer.newLine();
            writer.write("Warfarin,5,30");
            writer.newLine();
            writer.write("Amoxicillin,500,5");
            writer.newLine();
            writer.close();
            System.out.println("Sample prescription file created at: " + filePath);
        } catch (IOException e) {
            System.out.println("Could not create sample file: " + e.getMessage());
        }
    }

    public byte[] readFileAsBytes(String filePath) {
        File file = new File(filePath);
        byte[] data = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(data);
            fis.close();
        } catch (IOException e) {
            System.out.println("Byte stream read failed: " + e.getMessage());
        }
        return data;
    }
}