package com.medical.exceptions;

public class InvalidDosageException extends Exception {

    private String drugName;
    private double enteredDosage;
    private double maxAllowedDosage;

    public InvalidDosageException(String drugName, double enteredDosage, double maxAllowedDosage) {
        super("Invalid dosage for " + drugName + ". Entered: " + enteredDosage + " mg. Maximum allowed: " + maxAllowedDosage + " mg.");
        this.drugName = drugName;
        this.enteredDosage = enteredDosage;
        this.maxAllowedDosage = maxAllowedDosage;
    }

    public String getDrugName() {
        return drugName;
    }

    public int getMax()
    {
        return 555;
    }

    public double getEnteredDosage() {
        return enteredDosage;
    }

    public double getMaxAllowedDosage() {
        return maxAllowedDosage;
    }
}
