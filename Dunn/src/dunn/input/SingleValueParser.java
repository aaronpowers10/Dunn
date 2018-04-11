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

import cropper.input.SingleValueInput;
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
	private SingleValueInput value;

	public SingleValueParser() {
		numericParser = new NumericParser();
		expressionParser = new KeywordExpressionParser();
		CharacterParser asterisk = new CharacterParser("*");
		stringParser = new BetweenParser(asterisk, asterisk);
		namespaceParser = new NamespaceWordParser();

	}

	public SingleValueInput parseValue(InputSequence in) throws CannotParseException {
		parse(in);
		return value;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		try {
			value = new SingleValueInput(numericParser.parse(in), "Numeric");
		} catch (CannotParseException e1) {
			try {
				value = new SingleValueInput(expressionParser.parse(in), "Expression");
			} catch (CannotParseException e2) {
				try {
					value = new SingleValueInput(stringParser.parse(in), "String");
				} catch (CannotParseException e3) {
					value = new SingleValueInput(namespaceParser.parse(in), "Namespace");
				}
			}

		}
		return "";
	}
}