package dunn.hourly_report;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HourlyReader {

	public HourlyReport read(String reportFilePath, String hdrFilePath) {
		HDRFile hdrFile = new HDRFile(hdrFilePath);
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(reportFilePath, new String[0]));
			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			buffer.order(ByteOrder.LITTLE_ENDIAN);
			buffer.rewind();

			// File info record
			ignore(buffer, 1); // First record start header
			ignore(buffer, 2); // num reports, IPRO
			this.ignoreString(buffer, 7 * 4); // Date, time, and BDL
			this.ignore(buffer, 1); // 1
			this.ignoreString(buffer, 52 * 4); // DOE2 Version
			this.ignore(buffer, 91); // Bunch of -88888
			this.ignoreString(buffer, 5 * 4); // Weather location
			this.ignore(buffer, 1); // First record end header

			// First report record
			this.ignore(buffer, 1); // Second record Start Header
			HourlyReport report = new HourlyReport(getString(buffer, 8));
			ignore(buffer, 1); // 1 --> Report Index?
			ignoreFloats(buffer, 36); // Schedule?
			ignore(buffer, 1); // 173
			int numBlocks = buffer.getInt();
			ignore(buffer, 1); // Second record end header

			// All blocks records
			for (int i = 0; i < numBlocks; i++) {
				ignore(buffer, 1); // Block record start header
				String blockName = getString(buffer, 8);
				int blockCode = buffer.getInt();
				ReportBlock block = new ReportBlock(blockName, blockCode);
				int numVars = buffer.getInt();
				for (int j = 0; j < numVars; j++) {
					int varIndex = buffer.getInt();
					HDRFileEntry varEntry = hdrFile.getEntry(block.code(), varIndex);
					String varName = varEntry.name();
					if(varEntry.dataType() == ReportDataType.INT) {
						IntReportVariable  variable = new IntReportVariable(varName, varIndex);
						block.addVariable(variable);
					} else if (varEntry.dataType() == ReportDataType.FLOAT) {
						FloatReportVariable  variable = new FloatReportVariable(varName, varIndex);
						block.addVariable(variable);
					}
					
				}
				ignore(buffer, 1); // Block record end header
				report.addBlock(block);
			}

			// Hourly Data records	
			boolean yearStarted = false;
			while (buffer.hasRemaining()) {				
				ignore(buffer, 1); // Hour record start header
				int year = buffer.getInt();
				int month = buffer.getInt();
				int day = buffer.getInt();
				int hour = buffer.getInt();
				TimeStamp timeStamp = new TimeStamp(year, month, day, hour);
				int numVars = buffer.getInt();
				
				if(month == 1 && day == 1 && hour == 1) {
					yearStarted = true;
				}

				if (numVars == report.numVariables() && yearStarted) {

					for (int j = 0; j < report.numBlocks(); j++) {
						ReportBlock block = report.getBlock(j);
						for (int k = 0; k < block.numVariables(); k++) {
							ReportVariable variable = block.getVariable(k);
							int valInt = buffer.getInt();
							if (hour < 25) {
								variable.addDataPoint(timeStamp, valInt);
							}

						}
					}
				} else {
					for (int j = 0; j < numVars; j++) {
						ignore(buffer, 1);
					}
				}
				ignore(buffer, 1); // Hour record end header

			}

			return report;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void ignore(ByteBuffer buffer, int num) {
		for (int i = 0; i < num; i++) {
			 buffer.getInt();
		}
	}


	private void ignoreFloats(ByteBuffer buffer, int num) {
		for (int i = 0; i < num; i++) {
			buffer.getFloat();
		}
	}

	private void ignoreString(ByteBuffer buffer, int numChars) {
		for (int i = 0; i < numChars; i++) {
			buffer.get();
		}
	}

	private String getString(ByteBuffer buffer, int num) {
		ByteBuffer textBuffer = ByteBuffer.allocate(4 * num);
		textBuffer.order(ByteOrder.LITTLE_ENDIAN);
		for (int i = 0; i < num; i++) {
			textBuffer.putInt(buffer.getInt());
		}
		try {
			return (new String(textBuffer.array(), "ASCII")).trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) throws IOException {
		HourlyReader reader = new HourlyReader();
		HourlyReport report = reader.read(System.getProperty("user.dir") + "\\" + "DOEHRREP.SIN",
				System.getProperty("user.dir") + "\\" + "HDRFIL.BIN");
		report.write("TestOut.csv");
	}
}
