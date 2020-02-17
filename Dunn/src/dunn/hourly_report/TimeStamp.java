package dunn.hourly_report;

public class TimeStamp {
	private int year;
	private int month;
	private int day;
	private int hour;

	public TimeStamp(int year, int month, int day, int hour) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
	}

	public int year() {
		return this.year;
	}

	public int month() {
		return this.month;
	}

	public int day() {
		return this.day;
	}

	public int hour() {
		return this.hour;
	}

	public boolean numericallyEquals(TimeStamp timeStamp) {
		if (this.year == timeStamp.year() && this.month == timeStamp.month() && this.day == timeStamp.day()
				&& this.hour == timeStamp.hour()) {
			return true;
		}
		return false;
	}
	
	public boolean isEndOfYear() {
		if(month == 12 && day == 31 && hour == 24) {
			return true;
		} else {
			return false;
		}
	}

	public void print() {
		System.out.println(String.valueOf(this.year) + " " + this.month + " " + this.day + " " + this.hour);
	}
}
