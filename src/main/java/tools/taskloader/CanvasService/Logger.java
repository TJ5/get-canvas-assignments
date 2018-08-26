package tools.taskloader.CanvasService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import edu.ksu.canvas.model.assignment.*;
import java.io.File;
import edu.ksu.canvas.model.Course;

public class Logger {
	public static void createFile(String fileLocation) {
		File logFile = new File(fileLocation);
		try {
			logFile.createNewFile();
		} catch (IOException e) {
			System.out.println("Couldn't create output file!");
			e.printStackTrace();
		}
		
	}
	
	public static void writeToFile(Course course,Assignment assignment, String fileName) {
		
		try {
			BufferedWriter  writer = new BufferedWriter(new FileWriter(fileName, true)); 
			
			writer.append("\r\n" + course.getName() + " assignment: " + assignment.getName() + ", due on: " + assignment.getDueAt());
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Failed to write to file!");
			e.printStackTrace();
		}
	}
	
}
	