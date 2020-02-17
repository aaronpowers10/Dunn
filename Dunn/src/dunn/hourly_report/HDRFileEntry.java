package dunn.hourly_report;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class HDRFileEntry {
	
	private String format;
	private String name;
	private int units;
	private int i1;
	private int i2;
	private int index;
	private int i3;
	private ReportDataType dataType;
	
	public HDRFileEntry(ByteBuffer buffer) {
		i1 = buffer.getInt();
		i2 = buffer.getInt();
		index = buffer.getInt();
		i3 = buffer.getInt(); // A format tag?
		format = getString(buffer,10);
		name = getString(buffer,18);
		units = buffer.getInt();
		
		if(format.contains("I") || format.contains("i")) {
			dataType = ReportDataType.INT;
		} else if (format.contains("A") || format.contains("a")) {
			dataType = ReportDataType.ALPHA;
		} else {
			dataType = ReportDataType.FLOAT;
		}
		
	}
	
	public ReportDataType dataType() {
		return dataType;
	}
	
	public String format() {
		return format;
	}
	
	public String name() {
		return name;
	}
	
	public int i1() {
		return i1;
	}
	
	public int i2() {
		return i2;
	}
	
	public int index() {
		return index;
	}
	
	public int i3() {
		return i3;
	}
	
	public int units() {
		return units;
	}
	
	private String getString(ByteBuffer buffer, int numChars) {
		ByteBuffer textBuffer = ByteBuffer.allocate(numChars);
		textBuffer.order(ByteOrder.LITTLE_ENDIAN);
		for (int i = 0; i < numChars; i++) {
			textBuffer.put(buffer.get());
		}
		try {
			return (new String(textBuffer.array(), "ASCII")).trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

}
