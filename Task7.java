package Lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task7 {

    static class Candidate {
        String rawLine;
        String gender;
        String group;

        public Candidate(String rawLine, String gender, String group) {
            this.rawLine = rawLine;
            this.gender = gender.trim().toLowerCase();
            this.group = group.trim();
        }
    }

    public static void main(String[] args) {
        String csvFilePath = "CandidatePerformanceTest.csv";
        List<Candidate> candidates = loadCsvData(csvFilePath);

        if (candidates.isEmpty()) {
            System.out.println("No data loaded. Please check your file path.");
            return;
        }

        Map<String, List<Candidate>> groupedData = new HashMap<>();
        for (Candidate c : candidates) {
            groupedData.putIfAbsent(c.group, new ArrayList<>());
            groupedData.get(c.group).add(c);
        }

        String csvHeader = "ID,gender,group,Education,test preparation course,Math,English,Computer,Total,Average";

        for (Map.Entry<String, List<Candidate>> entry : groupedData.entrySet()) {
            String groupName = entry.getKey();
            List<Candidate> groupList = entry.getValue();

            String outputFileName = groupName.replaceAll("\\s+", "") + ".csv";
            writeGroupFile(outputFileName, csvHeader, groupList, groupName);
        }

        System.out.println("Exercise 7 completed successfully! Group files generated.");
    }

    public static List<Candidate> loadCsvData(String filePath) {
        List<Candidate> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (tokens.length >= 8) {
                    try {
                        String gender = tokens[1].replace("\"", "").trim();
                        String group = tokens[2].replace("\"", "").trim();

                        Integer.parseInt(tokens[5].replace("\"", "").trim());
                        Integer.parseInt(tokens[6].replace("\"", "").trim());
                        Integer.parseInt(tokens[7].replace("\"", "").trim());

                        list.add(new Candidate(line, gender, group));
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public static void writeGroupFile(String fileName, String header, List<Candidate> groupList, String groupName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(header);
            bw.newLine();

            int maleCount = 0;
            int totalStudents = groupList.size();

            for (Candidate c : groupList) {
                bw.write(c.rawLine);
                bw.newLine();
                if (c.gender.equals("male")) {
                    maleCount++;
                }
            }


            double pctMale = totalStudents > 0 ? ((double) maleCount / totalStudents) * 100 : 0.0;
            double pctFemale = totalStudents > 0 ? 100.0 - pctMale : 0.0;

            bw.newLine();
            bw.write("--- SUMMARY FOR " + groupName.toUpperCase() + " ---");
            bw.newLine();
            bw.write(String.format("Total Students,%d", totalStudents));
            bw.newLine();
            bw.write(String.format("Percentage Male,%.2f%%", pctMale));
            bw.newLine();
            bw.write(String.format("Percentage Female,%.2f%%", pctFemale));
            bw.newLine();

            System.out.println("Successfully generated: " + fileName + " (Total: " + totalStudents + ")");

        } catch (IOException e) {
            System.out.println("Error writing to file " + fileName + ": " + e.getMessage());
        }
    }
}