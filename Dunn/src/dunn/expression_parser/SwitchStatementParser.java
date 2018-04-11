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

public class SwitchStatementParser implements Parser {

	private Parser switchWord;
	private Parser caseList;
	private Parser defaultWord;
	private Parser colon;
	private Parser endSwitch;

	public SwitchStatementParser() {
		String err1 = "Expected 'default'.";
		String err2 = "Expected ':'.";
		String err3 = "Expected 'endswitch'.";
		switchWord = TerminalParser.wrap(new StringParser("switch"));
		caseList = new CaseListParser();
		defaultWord = RequiredParser.wrap(TerminalParser.wrap(new StringParser("default")), err1);
		colon = RequiredParser.wrap(TerminalParser.wrap(new CharacterParser(":")), err2);
		endSwitch = RequiredParser.wrap(TerminalParser.wrap(new StringParser("endswitch")), err3);

	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {

		ExpressionParser expression = new ExpressionParser();
		Parser parser = new AndParser(
				new Parser[] { switchWord, expression, caseList, defaultWord, colon, expression, endSwitch });
		return parser.parse(in);
	}

}
