package dunn.doe2;

import booker.building_data.BookerObject;
import booker.building_data.BookerProject;
import booker.building_data.NamespaceFilter;
import booker.building_data.NamespaceList;

public class ParentSystemFilter implements NamespaceFilter<BookerObject> {
	
	private BookerObject zone;
	private BookerProject project;
	
	public ParentSystemFilter(BookerProject project, BookerObject zone){
		this.project = project;
		this.zone = zone;
	}

	@Override
	public boolean filter(BookerObject item) {
		if(!item.type().equals("SYSTEM")){
			return false;
		}
		NamespaceList<BookerObject> systems = project.getTypeList("SYSTEM");
		int itemIndex = project.indexOf(item);
		int zoneIndex = project.indexOf(zone);
		int nextSystemIndex =  9999999;
		
		
		
		for(int i = 0; i<systems.size();i++){
			if(systems.get(i).equals(item)){
				if(i == systems.size()-1){
					nextSystemIndex = 9999999;
				} else {
					nextSystemIndex = project.indexOf(systems.get(i+1));
				}
			}
		}
		
		if((zoneIndex > itemIndex) && (zoneIndex < nextSystemIndex)){
			return true;
		} else {
			return false;
		}
		
		
	}

}
