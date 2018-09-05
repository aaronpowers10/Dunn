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

import booker.building_data.BookerField;
import booker.building_data.BookerObject;
import booker.building_data.BookerDataException;
import booker.building_data.BookerProject;
import booker.building_data.FieldValue;
import booker.building_data.ListValue;
import booker.building_data.ValueType;
import dunn.doe2.DOE2Factory;

public class FieldLoader {

	public static void loadFields(BookerObject firstPassObject, BookerObject secondPassObject,
			BookerProject project) {
		for (int i = 0; i < firstPassObject.numFields(); i++) {
			BookerField firstPassField = firstPassObject.getField(i);

			FieldValue secondPassValue = null;
			
			FieldValue firstPassValue = firstPassField.value();
			
			if(firstPassField.type() == ValueType.LIST){
				secondPassValue = loadListValue(firstPassValue.getAsList(),project);
			} else {
				secondPassValue = loadSingleValue(firstPassValue,project);
			}
			
			BookerField secondPassField = DOE2Factory.createField(firstPassField.name(), secondPassValue);
			

			secondPassObject.addField(secondPassField);

		}
	}
	
	private static FieldValue loadListValue(ListValue firstPassValue, BookerProject project){
		ListValue secondPassValue = DOE2Factory.createListValue();
		for(int i=0;i<firstPassValue.size();i++){
			secondPassValue.add(loadSingleValue(firstPassValue.get(i),project));
		}
		return secondPassValue;
	}
	
	private static FieldValue loadSingleValue(FieldValue firstPassValue, BookerProject project){
		try {
			return loadAsReal(firstPassValue);
		} catch (BookerDataException e1) {
			try {
				return loadAsObject(firstPassValue,project);
			} catch (BookerDataException e2) {
				return loadAlpha(firstPassValue);
		 	}
		}
	}
	
	//Create list, need from factory createField(name, value)
	
	private static FieldValue loadAsObject(FieldValue firstPassValue, BookerProject project) throws BookerDataException{
		BookerObject object = project.get(firstPassValue.getAsAlpha().value());
		return DOE2Factory.createObjectValue(object);
	}
	
	private static FieldValue loadAlpha(FieldValue firstPassValue){
		String value = firstPassValue.getAsAlpha().value();
		if(value.startsWith("{")){
			return DOE2Factory.createExpressionValue(value);
		} else if (value.startsWith("*")){
			return DOE2Factory.createStringValue( value);
		} else {
			return DOE2Factory.createCodewordValue( value);
		}
	}

	private static FieldValue loadAsReal(FieldValue firstPassValue) throws BookerDataException {
		return DOE2Factory.createNumericValue(firstPassValue.getAsReal().value());
	}

}
