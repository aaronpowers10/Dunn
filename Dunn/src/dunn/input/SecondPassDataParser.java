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

import java.util.ArrayList;

import booker.building_data.BookerObject;
import booker.building_data.BookerProject;
import dunn.doe2.DOE2Factory;
import otis.lexical.UpdateListener;

public class SecondPassDataParser {
	
private ArrayList<UpdateListener> updateListeners;
	
	public SecondPassDataParser(){
		updateListeners = new ArrayList<UpdateListener>();
	}
	
	public SecondPassDataParser(UpdateListener updateListener){
		updateListeners = new ArrayList<UpdateListener>();
		updateListeners.add(updateListener);
	}
	
	public SecondPassDataParser(ArrayList<UpdateListener> updateListeners){
		this.updateListeners = updateListeners;
	}

	public BookerProject parse(BookerProject firstPassData){
		BookerProject project = new BookerProject();
		
		for(int i=0;i<firstPassData.size();i++){
			BookerObject firstPassObject = firstPassData.get(i);
			project.add(DOE2Factory.createObject(firstPassObject.name(),firstPassObject.type()));
		}
		
		for(int i=0;i<project.size();i++){
			FieldLoader.loadFields(firstPassData.get(i), project.get(i), project);
		}

		updateListeners("Finished reading input file. ");

		return project;
	}
	
	private void updateListeners(String message) {
		for (int i = 0; i < updateListeners.size(); i++) {
			updateListeners.get(i).update(message);
		}
	}

}
