package com.medical.models;

public class Drug {

    private int drugId;
    private String name;
    private String category;
    private double maxDosage;
    private String description;

    public Drug() {
    }

    public Drug(int drugId, String name, String category, double maxDosage, String description) {
        this.drugId = drugId;
        this.name = name;
        this.category = category;
        this.maxDosage = maxDosage;
        this.description = description;
    }

    public Drug(String name, String category, double maxDosage) {
        this.name = name;
        this.category = category;
        this.maxDosage = maxDosage;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getMaxDosage() {
        return maxDosage;
    }

    public void setMaxDosage(double maxDosage) {
        this.maxDosage = maxDosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormattedDosage() {
        return String.valueOf(maxDosage) + " mg";
    }

    public boolean isHighRiskDrug() {
        String[] highRisk = {"Warfarin", "Morphine", "Digoxin", "Lithium", "Methotrexate"};
        for (String h : highRisk) {
            if (name.equalsIgnoreCase(h)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(name);
        sb.append(" [");
        sb.append(category);
        sb.append("] - Max: ");
        sb.append(maxDosage);
        sb.append(" mg");
        return sb.toString();
    }
}