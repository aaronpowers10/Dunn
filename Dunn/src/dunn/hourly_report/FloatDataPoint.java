package dunn.hourly_report;


public class FloatDataPoint {
	private double value;

	private TimeStamp timeStamp;

	public FloatDataPoint(TimeStamp timeStamp, double value) {
		this.timeStamp = timeStamp;
		this.value = value;
	}

	public TimeStamp timeStamp() {
		return this.timeStamp;
	}

	public double value() {
		return this.value;
	}

	public boolean occuredAt(TimeStamp timeStamp) {
		return timeStamp.numericallyEquals(this.timeStamp);
	}

	public int month() {
		return this.timeStamp.month();
	}
}
