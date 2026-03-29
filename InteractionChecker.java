package com.medical.checker;

import com.medical.database.DrugDAO;
import com.medical.database.InteractionDAO;
import com.medical.exceptions.DangerousInteractionException;
import com.medical.exceptions.DrugNotFoundException;
import com.medical.exceptions.InvalidDosageException;
import com.medical.models.Drug;
import com.medical.models.Prescription;
import java.util.ArrayList;
import java.util.HashMap;

public class InteractionChecker {

    private DrugDAO drugDAO;
    private InteractionDAO interactionDAO;
    private ArrayList<String> warningMessages;

    public InteractionChecker() {
        this.drugDAO = new DrugDAO();
        this.interactionDAO = new InteractionDAO();
        this.warningMessages = new ArrayList<>();
    }

    public ArrayList<String[]> checkPrescription(Prescription prescription)
            throws DrugNotFoundException, InvalidDosageException, DangerousInteractionException {

        ArrayList<String[]> results = new ArrayList<>();
        ArrayList<Drug> drugs = prescription.getDrugs();
        ArrayList<Double> dosages = prescription.getDosages();
        warningMessages.clear();

        for (int i = 0; i < drugs.size(); i++) {
            Drug drug = drugs.get(i);
            double dosage = dosages.get(i);

            Drug dbDrug = drugDAO.getDrugByName(drug.getName());
            if (dbDrug == null) {
                throw new DrugNotFoundException(drug.getName());
            }

            if (dosage > dbDrug.getMaxDosage()) {
                throw new InvalidDosageException(drug.getName(), dosage, dbDrug.getMaxDosage());
            }
        }

        for (int i = 0; i < drugs.size(); i++) {
            for (int j = i + 1; j < drugs.size(); j++) {
                Drug d1 = drugDAO.getDrugByName(drugs.get(i).getName());
                Drug d2 = drugDAO.getDrugByName(drugs.get(j).getName());

                if (d1 != null && d2 != null) {
                    String[] interaction = interactionDAO.checkInteraction(d1.getDrugId(), d2.getDrugId());
                    if (interaction != null) {
                        String[] result = new String[5];
                        result[0] = d1.getName();
                        result[1] = d2.getName();
                        result[2] = interaction[0];
                        result[3] = interaction[1];
                        result[4] = interaction[2];
                        results.add(result);
                        warningMessages.add(d1.getName() + " + " + d2.getName() + ": " + interaction[0]);

                        if (interaction[0].equals("SEVERE") || interaction[0].equals("LIFE-THREATENING")) {
                            throw new DangerousInteractionException(d1.getName(), d2.getName(), interaction[0], interaction[2]);
                        }
                    }
                }
            }
        }
        return results;
    }

    public HashMap<String, String> checkAllergyConflicts(Prescription prescription) {
        HashMap<String, String> conflicts = new HashMap<>();
        ArrayList<Drug> drugs = prescription.getDrugs();
        String allergies = prescription.getPatient().getAllergies();

        if (allergies == null || allergies.equalsIgnoreCase("None")) {
            return conflicts;
        }

        String[] allergyList = allergies.split(",");
        for (Drug drug : drugs) {
            for (String allergy : allergyList) {
                if (allergy.trim().equalsIgnoreCase(drug.getName().trim())) {
                    conflicts.put(drug.getName(), "Patient is allergic to " + drug.getName());
                }
            }
        }
        return conflicts;
    }

    public ArrayList<String> getWarningMessages() {
        return warningMessages;
    }
}
