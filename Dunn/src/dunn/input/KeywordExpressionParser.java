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

import dunn.expression_parser.ExpressionParser;
import dunn.expression_parser.TerminalParser;
import otis.lexical.AndParser;
import otis.lexical.CannotParseException;
import otis.lexical.CharacterParser;
import otis.lexical.InputSequence;
import otis.lexical.Parser;
import otis.lexical.RequiredParser;

public class KeywordExpressionParser implements Parser {

	private Parser parser;

	public KeywordExpressionParser() {
		String err1 = "Expected '}'";
		String err2 = "Syntax error in expression.";
		CharacterParser openBrace = new CharacterParser("{");
		Parser closedBrace = RequiredParser.wrap(TerminalParser.wrap(new CharacterParser("}")), err1);
		Parser expression = RequiredParser.wrap(new ExpressionParser(), err2);
		parser = new AndParser(new Parser[] { openBrace, expression, closedBrace });
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		return parser.parse(in);
	}

}
