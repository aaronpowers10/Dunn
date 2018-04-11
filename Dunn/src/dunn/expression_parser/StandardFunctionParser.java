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
import otis.lexical.SpaceDelimitedRowFactory;
import otis.lexical.TableParser;

public class StandardFunctionParser implements Parser {

	private Parser parser;

	public StandardFunctionParser() {
		String err1 = "Expected '('.";
		String err2 = "Expected ')'.";

		
		TableParser stdFunc = new TableParser("/doe2_res/StdFuncDictionary.txt", new SpaceDelimitedRowFactory());

		Parser functionName = TerminalParser.wrap(stdFunc);
		Parser openParenthesis = RequiredParser.wrap(TerminalParser.wrap(new CharacterParser("(")), err1);
		Parser closedParenthesis = RequiredParser.wrap(TerminalParser.wrap(new CharacterParser(")")), err2);
		Parser args = new StandardFunctionArgsParser();
		parser = new AndParser(new Parser[] { functionName, openParenthesis, args, closedParenthesis });
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		return parser.parse(in);
	}

}
