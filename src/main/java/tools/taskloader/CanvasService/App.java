package tools.taskloader.CanvasService;

import java.io.IOException;
import java.text.ParseException;

import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;
import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions.Include;
import edu.ksu.canvas.CanvasApiFactory;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.ListCourseAssignmentsOptions;
import edu.ksu.canvas.interfaces.AssignmentReader;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.model.Course;
import java.util.*;
import edu.ksu.canvas.model.assignment.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Config config = new Config();
    	try {
			config.setToday("01-01-2018");
		} catch (ParseException e2) {
			
			e2.printStackTrace();
		}
    	
    	//basic stuff
    	String canvasBaseUrl = "https://overlake.instructure.com";
    	OauthToken oauthToken = new NonRefreshableOauthToken("");
    	CanvasApiFactory apiFactory = new CanvasApiFactory(canvasBaseUrl);
    	//Course Reader
    	ListCurrentUserCoursesOptions courseOptions = new ListCurrentUserCoursesOptions();
    	Include[] incTerm = {Include.TERM};
    	courseOptions = courseOptions.includes(Arrays.asList(incTerm));
    	CourseReader course = apiFactory.getReader(CourseReader.class, oauthToken);
    	//Assignment Reader
    	
    	AssignmentReader assignment = apiFactory.getReader(AssignmentReader.class, oauthToken);
    	
    	//Get Courselist and assignments
    	List<Course> courseList = new ArrayList<Course>();
    	List<Assignment> assignmentList = new ArrayList<Assignment>();
    	String courseId;
    	Integer returnedId;
    	
    	String fileName = "C:\\Users\\tejes\\Documents\\CanvasOutput.txt";
    	Logger.createFile(fileName);
		
    	Date now = new Date();
    	
    	
    	try {
			BufferedWriter  writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.append("\r\n-------" + now + "-------");
			writer.close();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} 
		
    	try {
    		
    		courseList = (course.listCurrentUserCourses(courseOptions));
    		courseList.sort(new CompareCourseList());
    		for (int i = 0; i < courseList.size(); i++) {
    			returnedId = courseList.get(i).getId();
    			courseId = returnedId.toString();
    			
    			ListCourseAssignmentsOptions assignmentOptions = new ListCourseAssignmentsOptions(courseId);
    			
    			if (courseList.get(i).getName() != null) {	
        			assignmentList = assignment.listCourseAssignments(assignmentOptions);
        			System.out.println(courseList.get(i).getName());
	    			if (DateComparer.compareCourseDate(courseList.get(i), config.getToday()) == true) {	
				    		for (int x = 0; x < assignmentList.size(); x++) {
				    			if (DateComparer.compareAssignmentDate(assignmentList.get(x), config.getToday()) == true) {
				    				
				    				Logger.writeToFile(courseList.get(i), assignmentList.get(x), fileName);
				    				System.out.println("Outputted assignment to text file");
				    			}
				    				
				    		}
			    			
			    			
			    				
			    			
			    		}
    			}
    		}
    	    
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	System.out.println("All Done!");
    }
}
