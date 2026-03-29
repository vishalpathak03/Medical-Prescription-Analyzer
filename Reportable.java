package com.medical.interfaces;

public interface Reportable {

    String generateReport();

    void saveReport(String filePath);

    default void printReport() {
        System.out.println(generateReport());
    }
}