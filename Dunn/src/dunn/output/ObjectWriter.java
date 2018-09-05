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

package dunn.output;

import booker.building_data.BookerObject;
import booker.io.InputFileWriter;
import booker.io.OutputSequence;

public class ObjectWriter implements InputFileWriter {

	private BookerObject object;

	public ObjectWriter(BookerObject object) {
		this.object = object;
	}

	@Override
	public void write(OutputSequence out) {
		if (!object.name().equals(object.type())) {
			NamespaceWriter.write(object.name(), out);
			out.write(" = ");
		}

		out.write(object.type());
		out.write(System.lineSeparator());
		for (int i = 0; i < object.numFields(); i++) {
			object.getField(i).write(out);
			out.write(System.lineSeparator());
		}
		out.write(".." + System.lineSeparator() + System.lineSeparator());

	}

}
