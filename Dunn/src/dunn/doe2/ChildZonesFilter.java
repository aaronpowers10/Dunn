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
package dunn.doe2;

import booker.building_data.BookerObject;
import booker.building_data.BookerProject;
import booker.building_data.NamespaceFilter;
import booker.building_data.NamespaceList;

public class ChildZonesFilter implements NamespaceFilter<BookerObject> {
	
	private BookerObject system;
	private BookerProject project;
	
	public ChildZonesFilter(BookerProject project, BookerObject system){
		this.project = project;
		this.system = system;
	}

	@Override
	public boolean filter(BookerObject item) {
		if(!item.type().equals("ZONE")){
			return false;
		}
		NamespaceList<BookerObject> systems = project.getTypeList("SYSTEM");
		int itemIndex = project.indexOf(item);
		int systemIndex = project.indexOf(system);
		int nextSystemIndex =  9999999;
		
		
		
		for(int i = 0; i<systems.size();i++){
			if(systems.get(i).equals(system)){
				if(i == systems.size()-1){
					nextSystemIndex = 9999999;
				} else {
					nextSystemIndex = project.indexOf(systems.get(i+1));
				}
			}
		}
		
		if((itemIndex > systemIndex) && (itemIndex < nextSystemIndex)){
			return true;
		} else {
			return false;
		}
		
		
	}

}
