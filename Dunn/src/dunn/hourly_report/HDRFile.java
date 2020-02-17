package dunn.hourly_report;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HDRFile {
	
	private ArrayList<HDRFileEntry> loadEntries;
	private ArrayList<HDRFileEntry> systemEntries;
	
	
	public HDRFile(String filePath) {
		loadEntries = new ArrayList<HDRFileEntry>();
		systemEntries = new ArrayList<HDRFileEntry>();
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(filePath, new String[0]));
			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			buffer.order(ByteOrder.LITTLE_ENDIAN);
			buffer.rewind();
			
			consumeInts(buffer, 6);
			
			for(int i=0;i<219;i++) {
				HDRFileEntry entry = new HDRFileEntry(buffer);
				loadEntries.add(entry);
//				System.out.println("ENTRY: " + entry.name());
//				System.out.println("I1: " + entry.i1());
//				System.out.println("I2: " + entry.i2());
//				System.out.println("INDEX: " + entry.index());
//				System.out.println("I3: " + entry.i3());
//				System.out.println("FORMAT: " + entry.format());
//				System.out.println("UNITS: " + entry.units());
//				System.out.println();
			}
			
			consumeInts(buffer, 2);
			
			for(int i=0;i<968;i++) {
				HDRFileEntry entry = new HDRFileEntry(buffer);
				systemEntries.add(entry);
//				System.out.println("ENTRY: " + entry.name());
//				System.out.println("I1: " + entry.i1());
//				System.out.println("I2: " + entry.i2());
//				System.out.println("INDEX: " + entry.index());
//				System.out.println("I3: " + entry.i3());
//				System.out.println("FORMAT: " + entry.format());
//				System.out.println("UNITS: " + entry.units());
//				System.out.println();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void consumeInts(ByteBuffer buffer, int num) {
		for(int i=0;i<num;i++) {
			buffer.getInt();
		}
	}
	
	public HDRFileEntry getEntry(int blockCode, int varIndex) {
		if(blockCode - 2000 > 0) {
			int sysBlockCode = blockCode - 2000;
			for(HDRFileEntry entry:systemEntries) {
				if(entry.i2() == sysBlockCode && entry.index() == varIndex) {
					return entry;
				}
			}
		} else {
			int loadBlockCode = blockCode - 1000;
			for(HDRFileEntry entry:loadEntries) {
				if(entry.i2() == loadBlockCode && entry.index() == varIndex) {
					return entry;
				}
			}
		}
		return null;
	}

}
