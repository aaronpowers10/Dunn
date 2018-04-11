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

import booker.building_data.FieldValue;
import booker.building_data.ListValue;
import otis.lexical.CannotParseException;
import otis.lexical.InputSequence;
import otis.lexical.Parser;

public class FieldValueParser implements Parser {

	private ListValueParser listParser;
	private SingleValueParser singleValueParser;
	private FieldValue value;

	public FieldValueParser() {
		listParser = new ListValueParser();
		singleValueParser = new SingleValueParser();
	}
	
	public FieldValue parseValue(InputSequence in)throws CannotParseException{
		parse(in);
		return value;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		try{
			value = new ListValue(listParser.parseList(in));

		} catch (CannotParseException e1){
			value = singleValueParser.parseValue(in);
		}
		return "";
	}
	
}
