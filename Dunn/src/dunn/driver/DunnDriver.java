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
package dunn.driver;

import booker.building_data.BookerObject;
import booker.building_data.BookerProject;
import booker.building_data.NamespaceList;
import booker.io.ProjectReadCompleteListener;
import dunn.input.DOE2ProjectLoader;
import otis.lexical.ConsoleUpdateListener;

public class DunnDriver implements ProjectReadCompleteListener {

	public static void main(String[] args) {
		
		DunnDriver driver = new DunnDriver();
		
		DOE2ProjectLoader loader = new DOE2ProjectLoader(new ConsoleUpdateListener());
		
		loader.addProjectReadCompleteListener(driver);
		
		loader.load("C:\\doe22\\inp\\LG-OFF.inp");
		
	}


	@Override
	public void projectReadComplete(BookerProject project) {
		NamespaceList<BookerObject> spaces = project.getTypeList("SPACE");
		for(int i=0;i<spaces.size();i++) {
			BookerObject space = spaces.get(i);
			space.deleteField("EQUIP-SCHEDULE");
			space.deleteField("EQUIP-LATENT");
			space.deleteField("EQUIP-SENSIBLE");
			space.deleteField("EQUIPMENT-W/AREA");
			space.deleteField("C-SUB-SRC-BTUH");
			space.deleteField("C-SUB-SRC-KW");
			
		}
	}

}
