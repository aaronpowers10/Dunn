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
import otis.lexical.SpaceDelimitedRowFactory;
import otis.lexical.TableParser;
import otis.lexical.UpdateListener;

public class NamelessObjectParser implements Parser{
	
	private TableParser typeParser;
	private ObjectInput object;
	private ArrayList<UpdateListener> updateListeners;

	public NamelessObjectParser(ArrayList<UpdateListener> updateListeners) {
		this.updateListeners = updateListeners;
		typeParser = new TableParser("/doe2_res/Types.txt", new SpaceDelimitedRowFactory());
	}
	
	public ObjectInput parseObject(InputSequence in) throws CannotParseException {
		parseWithMessage(in,updateListeners);
		return object;
	}

	public String parse(InputSequence in)
			throws CannotParseException {
		String type = typeParser.parse(in);
		object = new ObjectInput(type,type);
		return type;
	}
	
	@Override
	public String message(){
		return "Reading object '" + object.name() + "'.";
	}

}
