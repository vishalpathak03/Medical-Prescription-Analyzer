package com.medical.interfaces;

public interface Checkable {

    boolean checkSafety();

    String getWarnings();

    boolean isDosageSafe(double dosage);

    default String getSafetyStatus() {
        if (checkSafety()) {
            return "SAFE";
        } else {
            return "UNSAFE  " + getWarnings();
        }
    }
}
