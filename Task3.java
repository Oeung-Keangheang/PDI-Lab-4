package Lab4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            FileWriter fw = new FileWriter("Ex3StudentData.csv");
            fw.write("Name,ID,Gender");
            
            System.out.println("Input 3 Student data");
            System.out.println("---------------------------");
            
            for (int i = 1; i <= 3; i++) {
                System.out.println("Student " + i + " data");
                System.out.print("Name: ");
                String name = sc.nextLine();
                
                System.out.print("ID: ");
                String id = sc.nextLine();
                
                System.out.print("Gender: ");
                String gender = sc.nextLine();
                System.out.println("---------------------------");
                fw.write(System.lineSeparator() + name + "," + id + "," + gender);
            }
            fw.close();
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.print("Error Detected: " + e.getMessage());
        } finally {
            sc.close(); 
        }
    }
}
