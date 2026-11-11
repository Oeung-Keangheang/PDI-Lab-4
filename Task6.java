package Lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task6 {

    static class Candidate {
        String gender;
        String group;
        int math;
        int english;
        int computer;

        public Candidate(String gender, String group, int math, int english, int computer) {
            this.gender = gender.trim().toLowerCase();
            this.group = group.trim();
            this.math = math;
            this.english = english;
            this.computer = computer;
        }

        public double getAverageScore() {
            return (math + english + computer) / 3.0;
        }
    }

    public static void main(String[] args) {
        String csvFilePath = "CandidatePerformanceTest.csv";
        List<Candidate> candidates = loadCsvData(csvFilePath);

        if (candidates.isEmpty()) {
            System.out.println("No data loaded. Check file path or console errors above.");
            return;
        }

        answerQuestion1(candidates);
        answerQuestion2(candidates);
        answerQuestion3(candidates);
        answerQuestion4(candidates);
    }

    public static List<Candidate> loadCsvData(String filePath) {
        List<Candidate> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Ensure row has at least 8 elements (ID, gender, group, education, test prep, math, english, computer)
                if (tokens.length >= 8) {
                    try {
                        String gender = tokens[1].replace("\"", "").trim();
                        String group = tokens[2].replace("\"", "").trim();
                        int math = Integer.parseInt(tokens[5].replace("\"", "").trim());
                        int english = Integer.parseInt(tokens[6].replace("\"", "").trim());
                        int computer = Integer.parseInt(tokens[7].replace("\"", "").trim());

                        list.add(new Candidate(gender, group, math, english, computer));
                    } catch (NumberFormatException e) {
                        // Skip header lines or malformed rows quietly
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("CRITICAL ERROR: Could not find or read the file at: " + filePath);
            e.printStackTrace();
        }
        return list;
    }
    // Q1: Total candidates & overall gender percentages
    public static void answerQuestion1(List<Candidate> list) {
        System.out.println("--- Question 1 ---");
        int total = list.size();
        int maleCount = 0;
        for (Candidate c : list) {
            if (c.gender.equals("male")) maleCount++;
        }
        double pctMale = ((double) maleCount / total) * 100;
        double pctFemale = 100.0 - pctMale;

        System.out.println("Total candidates: " + total);
        System.out.printf("Percentage of male candidates: %.2f%%\n", pctMale);
        System.out.printf("Percentage of female candidates: %.2f%%\n\n", pctFemale);
    }
    // Q2: Average score >= 90 & gender percentages
    public static void answerQuestion2(List<Candidate> list) {
        System.out.println("--- Question 2 ---");
        int subTotal = 0;
        int maleCount = 0;
        for (Candidate c : list) {
            if (c.getAverageScore() >= 90.0) {
                subTotal++;
                if (c.gender.equals("male")) maleCount++;
            }
        }
        System.out.println("Candidates with average score >= 90: " + subTotal);
        if (subTotal > 0) {
            double pctMale = ((double) maleCount / subTotal) * 100;
            double pctFemale = 100.0 - pctMale;
            System.out.printf("Percentage of male candidates: %.2f%%\n", pctMale);
            System.out.printf("Percentage of female candidates: %.2f%%\n\n", pctFemale);
        } else {
            System.out.println("Percentage of male/female candidates: 0.00%\n");
        }
    }

    // Q3: Score for EACH subject >= 85
    public static void answerQuestion3(List<Candidate> list) {
        System.out.println("--- Question 3 ---");
        int matches = 0;
        for (Candidate c : list) {
            if (c.math >= 85 && c.english >= 85 && c.computer >= 85) {
                matches++;
            }
        }
        System.out.println("Candidates with a score of at least 85 in each subject: " + matches + "\n");
    }

    // Q4: Students in each group & gender breakdowns
    public static void answerQuestion4(List<Candidate> list) {
        System.out.println("--- Question 4 ---");
        Map<String, List<Candidate>> groups = new HashMap<>();

        for (Candidate c : list) {
            groups.putIfAbsent(c.group, new ArrayList<>());
            groups.get(c.group).add(c);
        }

        for (Map.Entry<String, List<Candidate>> entry : groups.entrySet()) {
            List<Candidate> groupList = entry.getValue();
            int grpTotal = groupList.size();
            int maleCount = 0;
            for (Candidate c : groupList) {
                if (c.gender.equals("male")) maleCount++;
            }
            double pctMale = ((double) maleCount / grpTotal) * 100;
            double pctFemale = 100.0 - pctMale;

            System.out.println(entry.getKey() + ":");
            System.out.println("  Total students: " + grpTotal);
            System.out.printf("  Male candidates: %.2f%%\n", pctMale);
            System.out.printf("  Female candidates: %.2f%%\n\n", pctFemale);
        }
    }
}