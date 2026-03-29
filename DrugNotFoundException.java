package com.medical.exceptions;

public class DrugNotFoundException extends Exception {

    private String searchedDrug;

    public DrugNotFoundException(String searchedDrug) {
        super("Drug not found in database: " + searchedDrug + ". Please verify the drug name and try again.");
        this.searchedDrug = searchedDrug;
    }

    public String getSearchedDrug() {
        return searchedDrug;
    }
}