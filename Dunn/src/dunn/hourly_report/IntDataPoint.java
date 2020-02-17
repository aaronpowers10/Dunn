package dunn.hourly_report;

public class IntDataPoint {
	private int value;

	private TimeStamp timeStamp;

	public IntDataPoint(TimeStamp timeStamp, int value) {
		this.timeStamp = timeStamp;
		this.value = value;
	}

	public TimeStamp timeStamp() {
		return this.timeStamp;
	}

	public int value() {
		return this.value;
	}

	public boolean occuredAt(TimeStamp timeStamp) {
		return timeStamp.numericallyEquals(this.timeStamp);
	}

	public int month() {
		return this.timeStamp.month();
	}
}