package tools.taskloader.CanvasService;
import java.util.Date;
import java.util.Comparator;
import edu.ksu.canvas.model.Course;
public class CompareCourseList implements Comparator {


	public int compare(Object x, Object y) {
		Course a = (Course) x;
		Course b = (Course) y;
		Date insurance = new Date();
		Date aDate = new Date();
		Date bDate = new Date();
		try {
			if (a.getEnrollmentTerm().getEndAt() != null) {
				aDate = a.getEnrollmentTerm().getEndAt();
			}
			
			if(b.getEnrollmentTerm().getEndAt() != null) {
				bDate = b.getEnrollmentTerm().getEndAt();
			}
		}
		catch (NullPointerException e) {
			
		}
		if(aDate.after(bDate)) {
			return 1;
		}
		else if (aDate.before(bDate)) {
			return -1;
		}
		
		return 0;
	}
}
