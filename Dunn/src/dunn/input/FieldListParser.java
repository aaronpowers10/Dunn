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

import cropper.input.FieldInput;
import otis.lexical.AndParser;
import otis.lexical.CannotParseException;
import otis.lexical.InputSequence;
import otis.lexical.OptionalParser;
import otis.lexical.Parser;
import otis.lexical.RequiredParser;
import otis.lexical.StringParser;
import otis.lexical.UpdateListener;

public class FieldListParser implements Parser{
	private DOE2FieldParser fieldParser;
	private Parser optionalDelimiter;
	private Parser requiredDelimiter;
	private Parser fieldListEnd;
	private ArrayList<FieldInput> fields;

	public FieldListParser(ArrayList<UpdateListener> updateListeners) {
		fields = new ArrayList<FieldInput>();
		String err = "Delimiter required between field definitions.";
		fieldParser = new DOE2FieldParser(updateListeners);
		DelimiterParser delimiterParser = new DelimiterParser();
		optionalDelimiter = new OptionalParser(delimiterParser);
		requiredDelimiter = RequiredParser.wrap((delimiterParser), err);
		StringParser terminator = new StringParser("..");
		fieldListEnd = new AndParser(new Parser[] { optionalDelimiter, terminator });
	}
	
	public ArrayList<FieldInput> parseFieldList(InputSequence in) throws CannotParseException{
		parse(in);
		return fields;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		fields = new ArrayList<FieldInput>();
		boolean continueParsing = true;
		while (continueParsing) {
			try {
				fieldListEnd.parse(in);
				continueParsing = false;
			} catch (CannotParseException e) {
				FieldInput field = fieldParser.parseField(in);
				fields.add(field);				
				
				if (!field.type().equals("List")) {
					requiredDelimiter.parse(in);
				}
			}
		}
		return "";
	}

}
