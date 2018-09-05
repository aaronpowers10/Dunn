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

import booker.building_data.BookerField;
import booker.building_data.FieldValue;
import otis.lexical.CannotParseException;
import otis.lexical.InputSequence;
import otis.lexical.OptionalParser;
import otis.lexical.Parser;
import otis.lexical.RequiredParser;
import otis.lexical.UpdateListener;

public class FieldParser implements Parser{

	private ArrayList<UpdateListener> updateListeners;
	private NamespaceWordParser namespaceWordParser;
	private FieldValueParser fieldValueParser;
	private Parser delimiterParser;
	private Parser optionalDelimiter;
	private BookerField field;

	public FieldParser(ArrayList<UpdateListener> updateListeners) {
		this.updateListeners = updateListeners;
		String err = "Delimiter required between field name and field value.";
		namespaceWordParser = new NamespaceWordParser();
		fieldValueParser = new FieldValueParser();
		delimiterParser = RequiredParser.wrap(new DelimiterParser(), err);
		optionalDelimiter = OptionalParser.wrap(new DelimiterParser());
	}
	
	public BookerField parseField(InputSequence in) throws CannotParseException{
		parseWithMessage(in,updateListeners);
		return field;
	}

	@Override
	public String parse(InputSequence in)
			throws CannotParseException {
		optionalDelimiter.parse(in);
		String name = namespaceWordParser.parse(in);	
		delimiterParser.parse(in);
		FieldValue value = fieldValueParser.parseValue(in);
		field = new BookerField(name,value);
		return "";
	}

	@Override
	public String message() {
		return "Reading field '" + field.name() + "'.";
	}

}