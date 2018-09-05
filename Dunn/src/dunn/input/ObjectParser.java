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
import otis.lexical.CannotParseException;
import otis.lexical.InputSequence;
import otis.lexical.Parser;
import otis.lexical.SyntaxException;
import otis.lexical.UpdateListener;

public class ObjectParser implements Parser {
	
	private BookerObject object;
	private FieldListParser fieldListParser;
	private NamelessObjectParser namelessObjectParser;
	private NamedObjectParser namedObjectParser;
	
	public ObjectParser(ArrayList<UpdateListener> updateListeners){
		namelessObjectParser = new NamelessObjectParser(updateListeners);
		namedObjectParser = new NamedObjectParser(updateListeners);
		fieldListParser = new FieldListParser(updateListeners);
	}
	
	public BookerObject parseObject(InputSequence in) throws CannotParseException {
		parse(in);
		return object;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		try {
			object = namelessObjectParser.parseObject(in);
		} catch (CannotParseException e1) {
			try {
				object = namedObjectParser.parseObject(in);
			} catch (CannotParseException e2) {
				throw new SyntaxException("Could not parse object on line " + in.lineNumber() + ".");
			}
		}
		
		try {
			object.setFields(fieldListParser.parseFieldList(in));
		} catch (CannotParseException e) {
			throw new SyntaxException("Syntax error on line number " + in.lineNumber() + ".");
		}
		return "";
	}
	



}
