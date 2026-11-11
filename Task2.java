package Lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Task2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println("Input the Number of files you want to create: ");
		int n = sc.nextInt();
		sc.nextLine();
		ArrayList<Integer> numberList = new ArrayList<>();
		 File folder = new File("Ex2LuckyNumber");
	        if (!folder.exists()) {
	            folder.mkdir();
	        }
		for (int i=1; i<=n; i++) {
		try {
			FileWriter fw = new FileWriter("file"+i+".csv");
				Random r = new Random();				
				int number = r.nextInt(0, 100);
				fw.write(number+"\n");
				fw.close();
		}
			 catch (IOException e) {
			System.out.print("Error Detected");
		}
		try (BufferedReader reader = new BufferedReader(new FileReader("file"+i+".csv"))) {
			String line;
			while ((line = reader.readLine())!=null) {
				int number = Integer.parseInt(line.trim());
				numberList.add(number);
			}
		} catch (IOException e) {
			System.out.print("Error Detected");
		}
		}
		int max = numberList.get(0);
		int min = numberList.get(0);
		for (int i = 0; i<numberList.size(); i++) {
			int currentNumber = numberList.get(i);
			if (currentNumber > max) {
				max = currentNumber;
			}
			if (currentNumber < min) {
				min = currentNumber;
			}
		}
		System.out.println("Max: "+max+"\nMin: "+min);
		sc.close();
}
}
