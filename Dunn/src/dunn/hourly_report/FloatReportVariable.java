package dunn.hourly_report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FloatReportVariable implements ReportVariable{
	private int index;
	private ArrayList<FloatDataPoint> values;
	private String name;
	private double annualSum;
	private ArrayList<Double> monthSum;

	public FloatReportVariable(String name, int index) {
		this.name = name;
		this.index = index;
		this.values = new ArrayList<FloatDataPoint>();
		monthSum = new ArrayList<Double>();
		for(int i=0;i<12;i++) {
			monthSum.add(0.0);
		}
	}

	@Override
	public int index() {
		return this.index;
	}

	public int numValues() {
		return this.values.size();
	}
	
	@Override
	public void addDataPoint(TimeStamp timeStamp, int valInt) {
		FloatDataPoint value = new FloatDataPoint(timeStamp,IntToDouble.convert(valInt));
		annualSum += value.value();
		if(value.month() == 1) {
			monthSum.set(0,  monthSum.get(0) +value.value());
		} else if(value.month() == 2) {
			monthSum.set(1,  monthSum.get(1) +value.value());
		}else if(value.month() == 3) {
			monthSum.set(2,  monthSum.get(2) +value.value());
		}else if(value.month() == 4) {
			monthSum.set(3,  monthSum.get(3) +value.value());
		}else if(value.month() == 5) {
			monthSum.set(4,  monthSum.get(4) +value.value());
		}else if(value.month() == 6) {
			monthSum.set(5,  monthSum.get(5) +value.value());
		}else if(value.month() == 7) {
			monthSum.set(6,  monthSum.get(6) +value.value());
		}else if(value.month() == 8) {
			monthSum.set(7,  monthSum.get(7) +value.value());
		}else if(value.month() == 9) {
			monthSum.set(8,  monthSum.get(8) +value.value());
		}else if(value.month() == 10) {
			monthSum.set(9,  monthSum.get(9) +value.value());
		}else if(value.month() == 11) {
			monthSum.set(10,  monthSum.get(10) +value.value());
		}else if(value.month() == 12) {
			monthSum.set(11,  monthSum.get(11) +value.value());
		}
		this.values.add(value);
	}

	public double getValue(int index) {
		return ((FloatDataPoint) this.values.get(index)).value();
	}

	public double getValue(TimeStamp timeStamp) {
		for (FloatDataPoint value : this.values) {
			if (value.occuredAt(timeStamp)) {
				return value.value();
			}
		}
		return -9.999999999999E12D;
	}

	public int month(int index) {
		return ((FloatDataPoint) this.values.get(index)).month();
	}
	
	public String name() {
		return name;
	}
	
	@Override
	public void write(FileWriter out, int index) throws IOException {
		out.write(Double.toString(values.get(index).value()) +",");
	}
	
	@Override
	public TimeStamp getTimeStamp(int index) {
		return values.get(index).timeStamp();
	}
	
	public double annualSum() {
		return annualSum;
	}
	
	public double monthSum(int index) {
		return monthSum.get(index);
	}


}
