package dunn.hourly_report;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Tester {

	
	public void test1(String filePath) {
		//CECHR01.BIN
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(filePath, new String[0]));
			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			buffer.order(ByteOrder.LITTLE_ENDIAN);
			buffer.rewind();
			
			consumeInts(buffer,1); // header start
			consumeChars(buffer,236); // DOE2 version, date, time, BDL
			consumeInts(buffer,1); // 1
			consumeChars(buffer,20); // Weather
			consumeInts(buffer,1); // header end
			
			consumeInts(buffer,1); // header start
			consumeInts(buffer,1); // 1
			consumeInts(buffer,1); // header end
			
			consumeInts(buffer,1); // header start
			consumeInts(buffer,1); // 1
			consumeInts(buffer,1); // header end
			
			consumeInts(buffer,1); // header start
			consumeInts(buffer,1); // 1114
			consumeInts(buffer,1); // 4			
			consumeChars(buffer,32); // GLOBAL DRY BULBTEMP
			consumeInts(buffer,1); // 10
			consumeChars(buffer,12); // Format ,1X,F9.1
			consumeInts(buffer,2);  // 8, 0
			consumeChars(buffer,20); // F                 (4)
			consumeInts(buffer,1); // header end
			
			consumeInts(buffer,1); // header start
			consumeInts(buffer, 1); // 8808
			consumeInts(buffer,1); // header end
			//consumeBytes(buffer,236);
			
			//consumeInts(buffer, 6);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void test2(String filePath) {
		//CECHR02.BIN
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(filePath, new String[0]));
			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			buffer.order(ByteOrder.LITTLE_ENDIAN);
			buffer.rewind();
			
			consumeInts(buffer,1); // header start
			consumeChars(buffer,236); // DOE2 version, date, time, BDL
			consumeInts(buffer,1); // 1
			consumeChars(buffer,20); // Weather
			consumeInts(buffer,1); // header end
			
			consumeInts(buffer,1); // header start
			consumeInts(buffer,1); // 1
			consumeInts(buffer,1); // header end
			
			consumeInts(buffer,1); // header start
			consumeInts(buffer,1); // 1
			consumeInts(buffer,1); // header end
			
			consumeInts(buffer,1); // header start
			consumeInts(buffer,1); // 1114
			consumeInts(buffer,1); // 4			
			consumeChars(buffer,32); // GLOBAL DRY BULBTEMP
			consumeInts(buffer,1); // 10
			consumeChars(buffer,12); // Format ,1X,F9.1
			consumeInts(buffer,2);  // 8, 0
			consumeChars(buffer,20); // F                 (4)
			consumeInts(buffer,1); // header end
			
			consumeInts(buffer,1); // header start
			consumeInts(buffer, 1); // 8808
			consumeInts(buffer,1); // header end
			//consumeBytes(buffer,236);
			
			//consumeInts(buffer, 6);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void consumeChars(ByteBuffer buffer, int num) {
		ByteBuffer textBuffer = ByteBuffer.allocate(num);
		textBuffer.order(ByteOrder.LITTLE_ENDIAN);
		for (int i = 0; i < num; i++) {
			textBuffer.put(buffer.get());
		}
		try {
			System.out.println("S: " + new String(textBuffer.array(), "ASCII").trim());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void consumeBytes(ByteBuffer buffer, int num) {
		for(int i=0;i<num;i++) {
			System.out.println("B: " + buffer.get());
		}
	}
	
	private void consumeInts(ByteBuffer buffer, int num) {
		for(int i=0;i<num;i++) {
			System.out.println("I: " + buffer.getInt());
		}
	}

	public static void main(String[] args) {
		Tester t = new Tester();
		t.test2("CECHR02.BIN");
	}
}
