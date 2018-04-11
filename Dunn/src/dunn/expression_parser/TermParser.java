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

package dunn.expression_parser;

import otis.lexical.AndParser;
import otis.lexical.CannotParseException;
import otis.lexical.CharacterParser;
import otis.lexical.InputSequence;
import otis.lexical.OrParser;
import otis.lexical.Parser;
import otis.lexical.RequiredParser;

public class TermParser implements Parser {

	private Parser openParen;
	private Parser closedParen;
	private StatementParser statement;

	public TermParser() {
		String err = "Expected ')'.";
		openParen = TerminalParser.wrap(new CharacterParser("("));
		closedParen = RequiredParser.wrap(TerminalParser.wrap(new CharacterParser(")")), err);
		statement = new StatementParser();
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		ExpressionParser expression = new ExpressionParser();
		AndParser parentheticTerm = new AndParser(new Parser[] { openParen, expression, closedParen });
		Parser parser = new OrParser(new Parser[] { parentheticTerm, statement });
		return parser.parse(in);
	}

}
