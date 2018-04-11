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
import otis.lexical.OneOrMoreParser;
import otis.lexical.Parser;
import otis.lexical.RequiredParser;
import otis.lexical.StringParser;

public class CaseListParser implements Parser {

	private Parser caseWord;
	private Parser constant;
	private Parser colon;

	public CaseListParser() {
		caseWord = TerminalParser.wrap(new StringParser("case"));
		String err1 = "Expected constant after 'case'.";
		constant = RequiredParser.wrap(new ConstantParser(), err1);
		String err2 = "Expected ':'";
		colon = RequiredParser.wrap(TerminalParser.wrap(new CharacterParser(":")), err2);
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		ExpressionParser expression = new ExpressionParser();
		AndParser caseStatement = new AndParser(new Parser[] { caseWord, constant, colon, expression });
		Parser parser = new OneOrMoreParser(caseStatement);
		return parser.parse(in);
	}

}
