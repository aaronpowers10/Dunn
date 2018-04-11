/*
 *
 *  Copyright (C) 2018 Aaron Powers
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package dunn.input;

import java.io.IOException;
import java.util.ArrayList;

import booker.building_data.BuildingProject;
import otis.lexical.CannotParseException;
import otis.lexical.EndOfSequenceException;
import otis.lexical.InputSequence;
import otis.lexical.OptionalParser;
import otis.lexical.UpdateListener;

public class DOE2ProjectParser {
	
	private ArrayList<UpdateListener> updateListeners;
	
	public DOE2ProjectParser(){
		updateListeners = new ArrayList<UpdateListener>();
	}
	
	public DOE2ProjectParser(UpdateListener updateListener){
		updateListeners = new ArrayList<UpdateListener>();
		updateListeners.add(updateListener);
	}
	
	public DOE2ProjectParser(ArrayList<UpdateListener> updateListeners){
		this.updateListeners = updateListeners;
	}

	public BuildingProject parse(String fileName){
		InputSequence in = new InputSequence(fileName,80);
		
		BuildingProject project = new BuildingProject();
		
		OptionalParser optionalDelimiter = new OptionalParser(new DelimiterParser());
		optionalDelimiter.parse(in);

		DOE2ObjectParser objectParser = new DOE2ObjectParser(updateListeners);

		boolean continueParsing = true;
		while (continueParsing) {
			try {
				project.add(objectParser.parseObject(in));
				optionalDelimiter.parse(in);
			} catch (EndOfSequenceException e1) {
				continueParsing = false;
			} catch (CannotParseException e2) {
				continueParsing = false;
			}

		}
		updateListeners("Finished reading input file. ");
		try{
			in.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		return project;
	}
	
	private void updateListeners(String message) {
		for (int i = 0; i < updateListeners.size(); i++) {
			updateListeners.get(i).update(message);
		}
	}
}
