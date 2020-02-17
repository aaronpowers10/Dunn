package dunn.hourly_report;

import java.nio.ByteBuffer;

public class IntToFloat {
	public static float convert(int integer) {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(integer);
		buffer.rewind();
		return buffer.getFloat();
	}
}
