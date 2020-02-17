package dunn.hourly_report;

public class AlphaDataPoint {
	private String value;

	private TimeStamp timeStamp;

	public AlphaDataPoint(TimeStamp timeStamp, String value) {
		this.timeStamp = timeStamp;
		this.value = value;
	}

	public TimeStamp timeStamp() {
		return this.timeStamp;
	}

	public String value() {
		return this.value;
	}

	public boolean occuredAt(TimeStamp timeStamp) {
		return timeStamp.numericallyEquals(this.timeStamp);
	}

	public int month() {
		return this.timeStamp.month();
	}
}