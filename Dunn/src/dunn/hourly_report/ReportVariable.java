package dunn.hourly_report;

import java.io.FileWriter;
import java.io.IOException;

public interface ReportVariable {

	public int index();
	public void write(FileWriter out, int index) throws IOException;
	public String name();
	public TimeStamp getTimeStamp(int index);
	public void addDataPoint(TimeStamp timeStamp, int valInt);
}
