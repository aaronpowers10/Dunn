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
package dunn.output;

import booker.building_data.AlphaValue;
import booker.io.InputFileWriter;
import booker.io.OutputSequence;

public class CodewordWriter implements InputFileWriter {
	
	private AlphaValue value;
	
	public CodewordWriter(AlphaValue value){
		this.value = value;
	}

	@Override
	public void write(OutputSequence out) {
		if(value.value().contains(" ")){
			out.write("\"");
		}
		out.write(value.value());
		if(value.value().contains(" ")){
			out.write("\"");
		}
		
	}
	
	

}
