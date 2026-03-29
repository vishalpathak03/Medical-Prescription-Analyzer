package com.medical.collections;

import com.medical.database.DrugDAO;
import com.medical.models.Drug;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DrugInventory {

    private HashMap<String, Drug> drugMap;
    private ArrayList<Drug> drugList;
    private HashSet<String> highRiskDrugs;
    private LinkedList<String> recentSearches;
    private ArrayDeque<String> alertQueue;

    public DrugInventory() {
        drugMap = new HashMap<>();
        drugList = new ArrayList<>();
        highRiskDrugs = new HashSet<>();
        recentSearches = new LinkedList<>();
        alertQueue = new ArrayDeque<>();
        initializeHighRiskSet();
    }

    private void initializeHighRiskSet() {
        highRiskDrugs.add("Warfarin");
        highRiskDrugs.add("Morphine");
        highRiskDrugs.add("Digoxin");
        highRiskDrugs.add("Lithium");
        highRiskDrugs.add("Methotrexate");
        highRiskDrugs.add("Diazepam");
        highRiskDrugs.add("Phenytoin");
    }

    public void loadFromDatabase() {
        DrugDAO dao = new DrugDAO();
        ArrayList<Drug> allDrugs = dao.getAllDrugs();
        for (Drug drug : allDrugs) {
            drugMap.put(drug.getName().toLowerCase(), drug);
            drugList.add(drug);
        }
        System.out.println("Loaded " + drugList.size() + " drugs into inventory.");
    }

    public Drug searchDrug(String name) {
        String searchKey = name.toLowerCase();
        if (recentSearches.size() >= 10) {
            recentSearches.removeFirst();
        }
        recentSearches.addLast(name);
        return drugMap.get(searchKey);
    }

    public boolean isHighRisk(String drugName) {
        return highRiskDrugs.contains(drugName);
    }

    public ArrayList<Drug> getDrugsByCategory(String category) {
        ArrayList<Drug> result = new ArrayList<>();
        Iterator<Drug> iterator = drugList.iterator();
        while (iterator.hasNext()) {
            Drug drug = iterator.next();
            if (drug.getCategory().equalsIgnoreCase(category)) {
                result.add(drug);
            }
        }
        return result;
    }

    public void addAlert(String alertMessage) {
        alertQueue.addLast(alertMessage);
    }

    public String getNextAlert() {
        return alertQueue.pollFirst();
    }

    public boolean hasAlerts() {
        return !alertQueue.isEmpty();
    }

    public List<String> getRecentSearches() {
        return recentSearches;
    }

    public Set<String> getHighRiskDrugs() {
        return highRiskDrugs;
    }

    public HashMap<String, Drug> getDrugMap() {
        return drugMap;
    }

    public ArrayList<Drug> getDrugList() {
        return drugList;
    }

    public int getTotalDrugs() {
        return drugList.size();
    }

    public String[] getDrugNamesArray() {
        String[] names = new String[drugList.size()];
        for (int i = 0; i < drugList.size(); i++) {
            names[i] = drugList.get(i).getName();
        }
        return names;
    }
}
