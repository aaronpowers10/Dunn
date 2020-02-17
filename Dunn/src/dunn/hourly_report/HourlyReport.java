package dunn.hourly_report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HourlyReport {
	private ArrayList<ReportBlock> blocks;
	private String name;

	public HourlyReport(String name) {
		this.name = name;
		this.blocks = new ArrayList<ReportBlock>();
	}

	public String name() {
		return this.name;
	}

	public void addBlock(ReportBlock block) {
		this.blocks.add(block);
	}

	public int numBlocks() {
		return this.blocks.size();
	}

	public ReportBlock getBlock(int index) {
		return blocks.get(index);
	}
	
	public ReportBlock getBlock(String name) {
		for(ReportBlock block: blocks) {
			if(block.name().equals(name)) {
				return block;
			}
		}
		return null;
	}
	
	public ReportBlock getBlockOfType(int type) {
		for(ReportBlock block: blocks) {
			if(block.code() == type) {
				return block;
			}
		}
		return null;
	}
	
	public int numVariables() {
		int numVars = 0;
		for(ReportBlock block: blocks) {
			numVars += block.numVariables();
		}
		return numVars;
	}
	
	public void write(String fileName) {
		try {
			FileWriter out = generateOutputWriter(fileName,".csv");
			out.write("Year,Month,Day,Hour,");
			for(ReportBlock block:blocks) {
				block.writeHeader1(out);
			}
			out.write("\n");
			out.write(" , , , ,");
			for(ReportBlock block:blocks) {
				block.writeHeader2(out);
			}
			out.write("\n");
			for(int i=0;i<8760;i++) {
				TimeStamp ts = blocks.get(0).getTimeStamp(i);
				out.write(ts.year() + "," + ts.month() + "," + ts.day() + "," + ts.hour() + ",");
				for(ReportBlock block:blocks) {
					block.write(out, i);
				}
				out.write("\n");
			}
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private FileWriter generateOutputWriter(String baseFileName,String extension) throws IOException{
		FileWriter out = null;
		try {
			out = new FileWriter(new File(baseFileName + extension));
		} catch (FileNotFoundException e) {
			boolean fileCreated = false;
			int fileIndex = 1;
			while (!fileCreated) {
				try {
					out = new FileWriter(new File(baseFileName +"_"+ fileIndex + extension));
					fileCreated = true;
				} catch (FileNotFoundException e2) {
					fileIndex++;
				}
			}
		}
		return out;
	}
}
