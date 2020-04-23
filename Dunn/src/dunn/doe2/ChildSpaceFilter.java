package dunn.doe2;

import booker.building_data.BookerObject;
import booker.building_data.BookerProject;
import booker.building_data.NamespaceFilter;
import booker.building_data.NamespaceList;

public class ChildSpaceFilter implements NamespaceFilter<BookerObject> {
	
	private BookerObject floor;
	private BookerProject project;
	
	public ChildSpaceFilter(BookerProject project, BookerObject floor){
		this.project = project;
		this.floor = floor;
	}

	@Override
	public boolean filter(BookerObject item) {
		if(!item.type().equals("SPACE")){
			return false;
		}
		NamespaceList<BookerObject> floors = project.getTypeList("FLOOR");
		int itemIndex = project.indexOf(item);
		int floorIndex = project.indexOf(floor);
		int nextFloorIndex =  9999999;
		
		
		
		for(int i = 0; i<floors.size();i++){
			if(floors.get(i).equals(floor)){
				if(i == floors.size()-1){
					nextFloorIndex = 9999999;
				} else {
					nextFloorIndex = project.indexOf(floors.get(i+1));
				}
			}
		}
		
		if((itemIndex > floorIndex) && (itemIndex < nextFloorIndex)){
			return true;
		} else {
			return false;
		}
		
		
	}

}
