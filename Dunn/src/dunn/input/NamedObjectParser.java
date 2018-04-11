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

import java.util.ArrayList;

import cropper.input.ObjectInput;
import otis.lexical.CannotParseException;
import otis.lexical.InputSequence;
import otis.lexical.Parser;
import otis.lexical.RequiredParser;
import otis.lexical.UpdateListener;

public class NamedObjectParser implements Parser{
	private NamespaceWordParser namespaceWordParser;
	private Parser delimiterParser;
	private ObjectInput object;
	private ArrayList<UpdateListener> updateListeners;

	public NamedObjectParser(ArrayList<UpdateListener> updateListeners) {
		this.updateListeners = updateListeners;
		String err = "Delimiter required between object name and type.";
		namespaceWordParser = new NamespaceWordParser();
		delimiterParser = RequiredParser.wrap(new DelimiterParser(), err);
	}
	
	public ObjectInput parseObject(InputSequence in) throws CannotParseException{
		parseWithMessage(in,updateListeners);
		return object;
	}

	public String parse(InputSequence in)
			throws CannotParseException {
		String name = namespaceWordParser.parse(in);
		delimiterParser.parse(in);
		String type = namespaceWordParser.parse(in);
		delimiterParser.parse(in);
		object = new ObjectInput(type,name);
		return name + " " + type;
	}
	
	@Override
	public String message(){
		return "Reading object '" + object.name() + "' of type '" + object.type() + "'.";
	}
}
