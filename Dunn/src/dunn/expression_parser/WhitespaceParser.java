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
import otis.lexical.CharacterParser;
import otis.lexical.InputSequence;
import otis.lexical.OrParser;
import otis.lexical.Parser;

public class WhitespaceParser implements Parser {

	private Parser parser;

	public WhitespaceParser() {
		CharacterParser space = new CharacterParser(" ");
		CharacterParser carriageReturn = new CharacterParser("\r");
		CharacterParser lineFeed = new CharacterParser("\n");
		parser = new OrParser(new Parser[] { space, carriageReturn, lineFeed });
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		return parser.parse(in);
	}

}
