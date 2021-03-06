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
import otis.lexical.InputSequence;
import otis.lexical.OrParser;
import otis.lexical.Parser;
import otis.lexical.SpaceDelimitedRowFactory;
import otis.lexical.TableParser;

public class LogicalStatementParser implements Parser {

	private Parser unaryOp;
	private Parser relationalOp;

	public LogicalStatementParser() {
		
		unaryOp = new TableParser("/doe2_res/UnaryBoolOperatorDictionary.txt", new SpaceDelimitedRowFactory());


		relationalOp = new RelationalOperatorParser();
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		LogicalExpressionParser logicExpression = new LogicalExpressionParser();
		AndParser unaryStatement = new AndParser(new Parser[] { unaryOp, logicExpression });

		ExpressionParser expression = new ExpressionParser();
		AndParser relationalStatement = new AndParser(new Parser[] { expression, relationalOp, expression });

		Parser parser = new OrParser(new Parser[] { unaryStatement, relationalStatement });
		return parser.parse(in);
	}

}
