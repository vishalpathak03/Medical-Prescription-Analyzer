package com.medical.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prescription {

    private int prescriptionId;
    private Patient patient;
    private String doctorName;
    private LocalDate date;
    private ArrayList<Drug> drugs;
    private ArrayList<Double> dosages;
    private ArrayList<Integer> durations;

    public Prescription() {
        this.drugs = new ArrayList<>();
        this.dosages = new ArrayList<>();
        this.durations = new ArrayList<>();
        this.date = LocalDate.now();
    }

    public Prescription(Patient patient, String doctorName) {
        this.patient = patient;
        this.doctorName = doctorName;
        this.date = LocalDate.now();
        this.drugs = new ArrayList<>();
        this.dosages = new ArrayList<>();
        this.durations = new ArrayList<>();
    }

    public void addDrug(Drug drug, double dosage, int durationDays) {
        drugs.add(drug);
        dosages.add(dosage);
        durations.add(durationDays);
    }


    public int getMax()
    {
        return 555;
    }
    public void removeDrug(int index) {
        if (index >= 0 && index < drugs.size()) {
            drugs.remove(index);
            dosages.remove(index);
            durations.remove(index);
        }
    }

    public int getTotalDrugs() {
        return drugs.size();
    }

    public String getSummary() {
        StringBuffer sb = new StringBuffer();
        sb.append("Prescription Summary\n");
        sb.append("Patient: ").append(patient.getName()).append("\n");
        sb.append("Doctor: ").append(doctorName).append("\n");
        sb.append("Date: ").append(date.toString()).append("\n");
        sb.append("Drugs Prescribed:\n");
        for (int i = 0; i < drugs.size(); i++) {
            sb.append("  ").append(i + 1).append(". ");
            sb.append(drugs.get(i).getName());
            sb.append(" - Dosage: ").append(dosages.get(i)).append(" mg");
            sb.append(" - Duration: ").append(durations.get(i)).append(" days\n");
        }
        return sb.toString();
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public ArrayList<Double> getDosages() {
        return dosages;
    }

    public ArrayList<Integer> getDurations() {
        return durations;
    }

    @Override
    public String toString() {
        return "Prescription[" + prescriptionId + "] - " + patient.getName() + " by Dr." + doctorName;
    }
}
