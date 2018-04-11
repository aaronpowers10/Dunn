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

import booker.building_data.AlphaValue;
import booker.building_data.FieldValue;
import booker.building_data.RealValue;
import otis.lexical.BetweenParser;
import otis.lexical.CannotParseException;
import otis.lexical.CharacterParser;
import otis.lexical.InputSequence;
import otis.lexical.NumericParser;
import otis.lexical.Parser;

public class SingleValueParser implements Parser {

	private Parser numericParser;
	private Parser expressionParser;
	private Parser stringParser;
	private Parser namespaceParser;
	private FieldValue value;

	public SingleValueParser() {
		numericParser = new NumericParser();
		expressionParser = new KeywordExpressionParser();
		CharacterParser asterisk = new CharacterParser("*");
		stringParser = new BetweenParser(asterisk, asterisk);
		namespaceParser = new NamespaceWordParser();

	}

	public FieldValue parseValue(InputSequence in) throws CannotParseException {
		parse(in);
		return value;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		try {
			value = new RealValue(Double.parseDouble(numericParser.parse(in)));
		} catch (CannotParseException e1) {
			try {
				value = new AlphaValue(expressionParser.parse(in));
			} catch (CannotParseException e2) {
				try {
					value = new AlphaValue(stringParser.parse(in));
				} catch (CannotParseException e3) {
					value = new AlphaValue(namespaceParser.parse(in));
				}
			}

		}
		return "";
	}
}