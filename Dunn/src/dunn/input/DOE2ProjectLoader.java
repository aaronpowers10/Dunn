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

import booker.building_data.BookerProject;
import booker.io.ProjectLoader;
import booker.io.ProjectReadCompleteListener;
import otis.lexical.UpdateListener;

public class DOE2ProjectLoader implements ProjectLoader {
	
	private ArrayList<UpdateListener> updateListeners;
	private ArrayList<ProjectReadCompleteListener> readCompleteListeners;
	
	public DOE2ProjectLoader(){
		updateListeners = new ArrayList<UpdateListener>();
		readCompleteListeners = new ArrayList<ProjectReadCompleteListener>();
	}
	
	public DOE2ProjectLoader(UpdateListener listener){
		updateListeners = new ArrayList<UpdateListener>();
		readCompleteListeners = new ArrayList<ProjectReadCompleteListener>();
		updateListeners.add(listener);
	}

	@Override
	public void load(String fileName) {
		FirstPassDataParser firstPassParser = new FirstPassDataParser(updateListeners);
		SecondPassDataParser secondPassParser = new SecondPassDataParser(updateListeners);
		BookerProject project = secondPassParser.parse(firstPassParser.parse(fileName));
		for(int i=0;i<readCompleteListeners.size();i++){
			readCompleteListeners.get(i).projectReadComplete(project);
		}
	}

	@Override
	public void addProjectReadCompleteListener(ProjectReadCompleteListener readCompleteListener) {
		readCompleteListeners.add(readCompleteListener);
		
	}


	@Override
	public void addUpdateListener(UpdateListener updateListener) {
		updateListeners.add(updateListener);
		
	}
	
	class ReadFileThread extends Thread{
		private String fileName;
		
		public ReadFileThread(String fileName){
			this.fileName = fileName;
		}

		public void run(){
			load(fileName);
		}
	}

}
