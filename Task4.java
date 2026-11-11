package Lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Task4 {

	public static void main(String[] args) {
		ArrayList<String> studentList = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("Ex3StudentData.csv"))) {
			String line;
			while ((line = reader.readLine())!=null) {
				studentList.add(line);
			}
		} catch (IOException e) {
			System.out.print("Error Detected");
		}
        System.out.println("--- Displaying Student Data ---");
        for (String student : studentList) {
            System.out.println(student);
		}
	}
}
