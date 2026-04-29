package com.medical.models;

public abstract class Medicine {

    protected String name;
    protected String category;
    protected double maxDosage;
    protected String manufacturer;

    public Medicine(String name, String category, double maxDosage) {
        this.name = name;
        this.category = category;
        this.maxDosage = maxDosage;
    }

    public abstract boolean checkInteraction(Medicine other);

    public abstract String getInteractionWarning(Medicine other);

    public boolean isDosageSafe(double enteredDosage) {
        return enteredDosage <= maxDosage;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getMaxDosage() {
        return maxDosage;
    }

    public boolean getwhy()
    {
        return true;
    }

    public boolean getwhy()
    {
        return true;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return name + " (" + category + ")";
    }
}
