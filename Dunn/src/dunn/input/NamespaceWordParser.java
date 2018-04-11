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

package dunn.input;

import otis.lexical.BetweenParser;
import otis.lexical.CannotParseException;
import otis.lexical.CharacterParser;
import otis.lexical.InputSequence;
import otis.lexical.OrParser;
import otis.lexical.Parser;
import otis.lexical.StringParser;
import otis.lexical.UpToParser;

public class NamespaceWordParser implements Parser{

	private Parser quotedParser;
	private Parser unQuotedParser;

	public NamespaceWordParser() {
		CharacterParser quoteParser = new CharacterParser("\"");
		quotedParser = new BetweenParser(quoteParser, quoteParser);
		StringParser newLineParser = new StringParser("\r\n");
		Parser[] delimiters = new Parser[5];
		delimiters[0] = new CharacterParser(" ");
		delimiters[1] = new CharacterParser("=");
		delimiters[2] = new CharacterParser(",");
		delimiters[3] = new CharacterParser(")");
		delimiters[4] = newLineParser;
		unQuotedParser = new UpToParser(new OrParser(delimiters));
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		try {
			return quotedParser.parse(in);
		} catch (CannotParseException e) {
			return unQuotedParser.parse(in);
		}
	}

}
