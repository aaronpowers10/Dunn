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

import cropper.input.SingleValueInput;
import otis.lexical.AndParser;
import otis.lexical.CannotParseException;
import otis.lexical.CharacterParser;
import otis.lexical.InputSequence;
import otis.lexical.OptionalParser;
import otis.lexical.Parser;
import otis.lexical.RequiredParser;

public class ListValueParser implements Parser {
	private Parser openParenthesis;
	private Parser arrayTerminator;
	private Parser optionalDelimiter;
	private Parser requiredDelimiter;
	private ArrayList<SingleValueInput> values;

	public ListValueParser() {
		values = new ArrayList<SingleValueInput>();
		String err1 = "Delimiter required between array elements.";
		openParenthesis = new CharacterParser("(");
		DelimiterParser delimiterParser = new DelimiterParser();
		optionalDelimiter = new OptionalParser(delimiterParser);
		CharacterParser closedParenthesis = new CharacterParser(")");
		arrayTerminator = new AndParser(new Parser[] { optionalDelimiter, closedParenthesis });
		requiredDelimiter = RequiredParser.wrap((delimiterParser), err1);
	}
	
	public ArrayList<SingleValueInput> parseList(InputSequence in) throws CannotParseException{
		parse(in);
		return values;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		values = new ArrayList<SingleValueInput>();
		openParenthesis.parse(in);
		optionalDelimiter.parse(in);
		SingleValueParser fieldValueParser = new SingleValueParser();
		values.add(fieldValueParser.parseValue(in));
		boolean continueParsing = true;
		while (continueParsing) {
			try {
				arrayTerminator.parse(in);
				continueParsing = false;
			} catch (CannotParseException e1) {
				requiredDelimiter.parse(in);
				values.add(fieldValueParser.parseValue(in));
			}
		}
		return "";
	}

}