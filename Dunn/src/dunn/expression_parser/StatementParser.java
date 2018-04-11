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

import otis.lexical.CannotParseException;
import otis.lexical.InputSequence;
import otis.lexical.NumericParser;
import otis.lexical.OrParser;
import otis.lexical.Parser;

public class StatementParser implements Parser {

	private Parser parser;

	public StatementParser() {
		Parser numeric = TerminalParser.wrap(new NumericParser());
		IfThenStatementParser ifThenStatement = new IfThenStatementParser();
		SwitchStatementParser switchStatement = new SwitchStatementParser();
		StandardFunctionParser standardFunction = new StandardFunctionParser();
		BDLFunctionParser bdlFunction = new BDLFunctionParser();
		ReservedWordParser reservedWord = new ReservedWordParser();
		parser = new OrParser(new Parser[] { numeric, ifThenStatement, switchStatement, standardFunction, bdlFunction,
				reservedWord });
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		return parser.parse(in);
	}

}
