package com.medical.fileio;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportWriter {

    private String outputDirectory;

    public ReportWriter(String outputDirectory) {
        this.outputDirectory = outputDirectory;
        File dir = new File(outputDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void writeReport(String reportContent, String patientName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = outputDirectory + File.separator + patientName.replace(" ", "_") + "_" + timestamp + ".txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("========================================");
            writer.newLine();
            writer.write("   MEDICAL PRESCRIPTION ANALYZER");
            writer.newLine();
            writer.write("   Interaction Report");
            writer.newLine();
            writer.write("========================================");
            writer.newLine();
            writer.write("Generated: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            writer.newLine();
            writer.write("========================================");
            writer.newLine();
            writer.newLine();
            writer.write(reportContent);
            writer.newLine();
            writer.write("========================================");
            writer.newLine();
            writer.write("END OF REPORT");
            writer.newLine();
            writer.write("========================================");
            writer.close();
            System.out.println("Report saved: " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to save report: " + e.getMessage());
        }
    }

    public void appendToLog(String logMessage) {
        String logFile = outputDirectory + File.separator + "system_log.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            writer.write("[" + LocalDateTime.now().format(formatter) + "] " + logMessage);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Log write failed: " + e.getMessage());
        }
    }

    public String readReport(String fileName) {
        StringBuffer content = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            return "Report file not found: " + fileName;
        } catch (IOException e) {
            return "Error reading report: " + e.getMessage();
        }
        return content.toString();
    }
}