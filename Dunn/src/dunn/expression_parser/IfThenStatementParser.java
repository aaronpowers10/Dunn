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
import otis.lexical.Parser;
import otis.lexical.RequiredParser;
import otis.lexical.StringParser;

public class IfThenStatementParser implements Parser {

	private Parser ifWord;
	private Parser openParen;
	private Parser closedParen;
	private Parser thenWord;
	private Parser elseWord;
	private Parser endIfWord;
	private Parser logicalExpr;

	public IfThenStatementParser() {
		String err1 = "Expected '('.";
		String err2 = "Expected ')'.";
		String err3 = "Expected 'then'.";
		String err4 = "Expected 'else'.";
		String err5 = "Expected 'endif'.";

		ifWord = TerminalParser.wrap(new StringParser("if"));

		openParen = RequiredParser.wrap(TerminalParser.wrap(new CharacterParser("(")), err1);
		closedParen = RequiredParser.wrap(TerminalParser.wrap(new CharacterParser(")")), err2);
		thenWord = RequiredParser.wrap(TerminalParser.wrap(new StringParser("then")), err3);
		elseWord = RequiredParser.wrap(TerminalParser.wrap(new StringParser("else")), err4);
		endIfWord = RequiredParser.wrap(TerminalParser.wrap(new StringParser("endif")), err5);
		logicalExpr = new LogicalExpressionParser();

	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		ExpressionParser expression = new ExpressionParser();
		Parser parser = new AndParser(new Parser[] { ifWord, openParen, logicalExpr, closedParen, thenWord, expression,
				elseWord, expression, endIfWord });
		return parser.parse(in);
	}

}
