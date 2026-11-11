package Lab4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Random;
public class Task1 {

	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter("LuckyNumber.csv");
			for (int i=0; i<500; i++) {
				Random r = new Random();				
				int number = r.nextInt(0, 1000);
				fw.write(number+"\n");
			}
	
			fw.close();
			
		} catch (IOException e) {
			System.out.print("Error Detected");
		}
		try (BufferedReader reader = new BufferedReader(new FileReader("LuckyNumber.csv"))) {
			String line;
			while ((line = reader.readLine())!=null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.print("Error Detected");
		}
}

	}


