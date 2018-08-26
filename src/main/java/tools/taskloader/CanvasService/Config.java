package tools.taskloader.CanvasService;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Config {
	private Date today;
	private SimpleDateFormat todaySDF = new SimpleDateFormat("dd-MM-yyyy");
	
	public void setToday(String now) throws java.text.ParseException {
		this.today = this.todaySDF.parse(now);
			
	
	}
	public Date getToday() {
		return this.today;
	}
	
	
}
