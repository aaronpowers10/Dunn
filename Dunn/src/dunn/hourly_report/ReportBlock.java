package dunn.hourly_report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportBlock {
	private String name;
	private ArrayList<ReportVariable> variables;
	private int code;

	public ReportBlock(String name, int code) {
		this.name = name;
		this.variables = new ArrayList<ReportVariable>();
		this.code = code;
	}

	public String name() {
		return this.name;
	}

	public void addVariable(ReportVariable variable) {
		this.variables.add(variable);
	}

	public int numVariables() {
		return this.variables.size();
	}

	public ReportVariable getVariable(int index) {
		return variables.get(index);
	}
	
	public ReportVariable getByDOE2Index(int index) {
		for(ReportVariable variable: variables) {
			if(variable.index() == index) {
				return variable;
			}
		}
		return null;
	}
	
	public int code() {
		return code;
	}
	
	public void write(FileWriter out, int index) throws IOException{
		for(ReportVariable variable: variables) {
			variable.write(out, index);
		}
	}
	
	public void writeHeader1(FileWriter out) throws IOException{
		out.write(name + ",");
		for(int i=1;i<variables.size();i++) {
			out.write(" ,");
		}
	}
	
	public void writeHeader2(FileWriter out) throws IOException{
		for(ReportVariable variable: variables) {
			out.write(variable.name() + ",");
		}
	}
	
	public TimeStamp getTimeStamp(int index) {
		return variables.get(0).getTimeStamp(index);
	}
	
	
}
