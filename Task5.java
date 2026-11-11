package Lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}

public class Task5 {
    private static final String FILE_PATH = "Ex3StudentData.csv";

    public static void main(String[] eloquence) {
        ArrayList<Student> studentList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;   
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String id = data[0].trim();
                    String name = data[1].trim();
                    studentList.add(new Student(id, name));
                }
            }
            System.out.println("Data loaded successfully. Total students: " + studentList.size());
        } catch (IOException e) {
            System.out.println("Error reading file (or file does not exist yet). Starting fresh.");
        }

        // 2. Add 3 more students from your team
        studentList.add(new Student("101", "Alex Smith"));
        studentList.add(new Student("102", "Chloe Jones"));
        studentList.add(new Student("103", "Benjamin Miller"));
        System.out.println("Added 3 team members.");

        // 3. Sort data based on the name (A-Z)
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareToIgnoreCase(s2.getName());
            }
        });
        System.out.println("Sorted students by name (A-Z).");

        // 4. Write the updated data back to StudentData.csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : studentList) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Successfully saved sorted data back to " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
