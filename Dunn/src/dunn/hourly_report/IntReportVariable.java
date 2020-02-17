package dunn.hourly_report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IntReportVariable implements ReportVariable{
	
	private int index;
	private String name;
	private ArrayList<IntDataPoint> values;
	
	public IntReportVariable(String name, int index) {
		this.name = name;
		this.index = index;
		this.values = new ArrayList<IntDataPoint>();
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public void write(FileWriter out, int index) throws IOException {
		out.write(Integer.toString(values.get(index).value()) +",");
		
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public TimeStamp getTimeStamp(int index) {
		return values.get(index).timeStamp();
	}

	@Override
	public void addDataPoint(TimeStamp timeStamp, int valInt) {
		values.add(new IntDataPoint(timeStamp,valInt));
		
	}

}
