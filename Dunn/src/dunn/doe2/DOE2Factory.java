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

import java.util.ArrayList;

import booker.building_data.AlphaValue;
import booker.building_data.BookerField;
import booker.building_data.BookerObject;
import booker.building_data.FieldValue;
import booker.building_data.ListValue;
import booker.building_data.ObjectValue;
import booker.building_data.RealValue;
import dunn.output.CodewordWriter;
import dunn.output.FieldWriter;
import dunn.output.KeywordExpressionWriter;
import dunn.output.ListValueWriter;
import dunn.output.NumericWriter;
import dunn.output.ObjectValueWriter;
import dunn.output.ObjectWriter;
import dunn.output.StringValueWriter;

public class DOE2Factory {
	
	public static BookerObject createObject(String name, String type){
		BookerObject object = new BookerObject(type,name);
		object.setWriter(new ObjectWriter(object));
		return object;
	}
	
	public static BookerObject createObject(String type){
		BookerObject object = new BookerObject(type,type);
		object.setWriter(new ObjectWriter(object));
		return object;
	}
	
	public static BookerObject createDefault(String type){
		BookerObject object = createObject("SET-DEFAULT");
		object.addField(createCodewordField("FOR",type));
		return object;
	}
	
	public static BookerField createField(String name, FieldValue value){
		BookerField field = new BookerField(name,value);
		field.setWriter(new FieldWriter(field));
		return field;
	}
	
	public static BookerField createNumericField(String name, double value){
		BookerField field = new BookerField(name, createNumericValue(value));
		field.setWriter(new FieldWriter(field));
		return field;
	}
	
	public static BookerField createStringField(String name, String value){
		BookerField field = new BookerField(name, createStringValue(value));
		field.setWriter(new FieldWriter(field));
		return field;
	}
	
	public static BookerField createExpressionField(String name, String value){
		BookerField field = new BookerField(name, createExpressionValue(value));
		field.setWriter(new FieldWriter(field));
		return field;
	}
	
	public static BookerField createCodewordField(String name, String value){
		BookerField field = new BookerField(name, createCodewordValue(value));
		field.setWriter(new FieldWriter(field));
		return field;
	}
	
	public static BookerField createObjectField(String name, BookerObject value){
		BookerField field = new BookerField(name, createObjectValue(value));
		field.setWriter(new FieldWriter(field));
		return field;
	}
	
	public static BookerField createListField(String name, ArrayList<FieldValue> values){
		BookerField field = new BookerField(name, createListValue(values));
		field.setWriter(new FieldWriter(field));
		return field;
	}
	
	public static BookerField createListField(String name){
		BookerField field = new BookerField(name, createListValue());
		field.setWriter(new FieldWriter(field));
		return field;
	}
	
	public static BookerField createListField(String name, BookerObject firstElement){
		BookerField field = createListField(name);
		field.add(createObjectValue(firstElement));
		return field;
	}
	
	public static BookerField createListField(String name, double firstElement){
		BookerField field = createListField(name);
		field.add(createNumericValue(firstElement));
		return field;
	}
	
	public static BookerField createListField(String name, double x1, double x2, double x3, double x4, double x5, double x6){
		BookerField field = createListField(name);
		field.add(createNumericValue(x1));
		field.add(createNumericValue(x2));
		field.add(createNumericValue(x3));
		field.add(createNumericValue(x4));
		field.add(createNumericValue(x5));
		field.add(createNumericValue(x6));
		return field;
	}
	
	public static BookerField createListField(String name, String firstElement){
		BookerField field = createListField(name);
		field.add(createCodewordValue(firstElement));
		return field;
	}
	
	public static RealValue createNumericValue(double value){
		RealValue realValue = new RealValue(value);
		realValue.setWriter(new NumericWriter(realValue));
		return realValue;
	}
	
	public static AlphaValue createStringValue(String value){
		AlphaValue stringValue = new AlphaValue(value);
		stringValue.setWriter(new StringValueWriter(stringValue));
		return stringValue;
	}
	
	public static AlphaValue createExpressionValue(String value){
		AlphaValue expressionValue = new AlphaValue(value);
		expressionValue.setWriter(new KeywordExpressionWriter(expressionValue));
		return expressionValue;
	}
	
	public static AlphaValue createCodewordValue(String value){
		AlphaValue codewordValue = new AlphaValue(value);
		codewordValue.setWriter(new CodewordWriter(codewordValue));
		return codewordValue;
	}
	
	public static ObjectValue createObjectValue(BookerObject object){
		ObjectValue objectValue = new ObjectValue(object);
		objectValue.setWriter(new ObjectValueWriter(objectValue));
		return objectValue;
	}
	
	public static ListValue createListValue(ArrayList<FieldValue> values){
		ListValue listValue = new ListValue(values);
		listValue.setWriter(new ListValueWriter(listValue));
		return listValue;
	}
	
	public static ListValue createListValue(){
		ListValue listValue = new ListValue();
		listValue.setWriter(new ListValueWriter(listValue));
		return listValue;
	}

}
