/*
 *
 *  Copyright (C) 2017 Aaron Powers
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
package dunn.object_factories;

import booker.building_data.BookerField;
import dunn.doe2.DOE2Factory;

public class VertexFactory {
	
	public static BookerField create(String name, double x, double y){
		BookerField vertex = DOE2Factory.createListField(name);
		vertex.add(DOE2Factory.createNumericValue(x));
		vertex.add(DOE2Factory.createNumericValue(y));
		return vertex;
	}
	
	public static BookerField create(String name, String x, double y){
		BookerField vertex = DOE2Factory.createListField(name);
		vertex.add(DOE2Factory.createExpressionValue(x));
		vertex.add(DOE2Factory.createNumericValue(y));
		return vertex;
	}
	
	public static BookerField create(String name, double x, String y){
		BookerField vertex = DOE2Factory.createListField(name);
		vertex.add(DOE2Factory.createNumericValue(x));
		vertex.add(DOE2Factory.createExpressionValue(y));
		return vertex;
	}
	
	
	public static BookerField create(String name, String x, String y){
		BookerField vertex = DOE2Factory.createListField(name);
		vertex.add(DOE2Factory.createExpressionValue(x));
		vertex.add(DOE2Factory.createExpressionValue(y));
		return vertex;
	}

}
