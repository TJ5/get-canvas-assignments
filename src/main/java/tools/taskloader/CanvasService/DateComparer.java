package tools.taskloader.CanvasService;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.assignment.Assignment;

import java.util.Date;
public class DateComparer {
	public static Boolean compareCourseDate(Course x, Date now) {
		
		
			try {
				if (x.getEnrollmentTerm().getEndAt().before(now)) {
					return false;
				}
			} catch (NullPointerException e) {
				return true;
			}
		

		return true;
	}
	
	public static Boolean compareAssignmentDate(Assignment x, Date now) {
		
		try {
			if(x.getDueAt().before(now)) {
				return false;
			}
		} catch (NullPointerException e) {
			return true;
		}
		
		return true;
	}
}
