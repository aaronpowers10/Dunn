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
package dunn.object_factories;

import java.util.ArrayList;

import booker.building_data.BookerField;
import booker.building_data.BookerObject;
import dunn.doe2.DOE2Factory;

public class ScheduleFactory {

	public static BookerObject fractionDaySchedule(String name, ArrayList<Double> values) {
		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "FRACTION"));

		BookerField valuesField = DOE2Factory.createListField("VALUES");

		for (Double value : values) {
			valuesField.add(DOE2Factory.createNumericValue(value));
		}

		schedule.addField(valuesField);

		return schedule;
	}

	public static BookerObject fractionDaySchedule(String name, int onHour, int offHour, double onVal, double offVal) {

		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "FRACTION"));

		BookerField values = DOE2Factory.createListField("VALUES");

		for (int hour = 0; hour <= Math.min(23, offHour + 1); hour++) {
			if (hour == 0) {
				values.add(DOE2Factory.createNumericValue(offVal));
			} else if (hour == onHour) {
				values.add(DOE2Factory.createNumericValue(onVal));
			} else if (hour == offHour) {
				values.add(DOE2Factory.createNumericValue(offVal));
			} else {
				values.add(DOE2Factory.createCodewordValue("&D"));
			}
		}

		schedule.addField(values);

		return schedule;

	}

	public static BookerObject fractionDaySchedule(String name, double value) {

		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "FRACTION"));

		BookerField values = DOE2Factory.createListField("VALUES");

		values.add(DOE2Factory.createNumericValue(value));

		// for (int hour = 0; hour < 24; hour++) {
		// values.add(DOE2Factory.createReal(value));
		// }

		schedule.addField(values);

		return schedule;

	}

	public static BookerObject temperatureDaySchedule(String name, int onHour, int offHour, double onVal,
			double offVal) {

		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "TEMPERATURE"));

		BookerField values = DOE2Factory.createListField("VALUES");

		for (int hour = 0; hour <= Math.min(23, offHour + 1); hour++) {

			if (hour == 0) {
				values.add(DOE2Factory.createNumericValue(offVal));
			} else if (hour == onHour) {
				values.add(DOE2Factory.createNumericValue(onVal));
			} else if (hour == offHour) {
				values.add(DOE2Factory.createNumericValue(offVal));
			} else {
				values.add(DOE2Factory.createCodewordValue("&D"));
			}
		}

		schedule.addField(values);

		return schedule;

	}

	public static BookerObject temperatureDaySchedule(String name, int onHour, int offHour, double onVal, double offVal,
			double setupVal, ArrayList<Integer> setupHours) {

		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "TEMPERATURE"));

		BookerField values = DOE2Factory.createListField("VALUES");

		for (int hour = 0; hour <= Math.min(23, offHour + 1); hour++) {
			boolean isSetup = false;
			for (Integer setupHour : setupHours) {
				if (hour == setupHour) {
					values.add(DOE2Factory.createNumericValue(setupVal));
					isSetup = true;
				}
			}

			if (!isSetup) {
				if (hour == 0) {
					values.add(DOE2Factory.createNumericValue(offVal));
				} else if (hour == onHour) {
					values.add(DOE2Factory.createNumericValue(onVal));
				} else if (hour == offHour) {
					values.add(DOE2Factory.createNumericValue(offVal));
				} else {
					values.add(DOE2Factory.createCodewordValue("&D"));
				}
			}
		}

		schedule.addField(values);

		return schedule;

	}

	public static BookerObject temperatureDaySchedule(String name, double value) {

		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "TEMPERATURE"));

		BookerField values = DOE2Factory.createListField("VALUES");

		values.add(DOE2Factory.createNumericValue(value));

		// for (int hour = 0; hour < 24; hour++) {
		// values.add(DOE2Factory.createReal(value));
		// }

		schedule.addField(values);

		return schedule;

	}

	public static BookerObject onOffDaySchedule(String name, int onHour, int offHour) {

		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "ON/OFF/FLAG"));

		BookerField values = DOE2Factory.createListField("VALUES");

		for (int hour = 0; hour <= Math.min(23, offHour + 1); hour++) {
			if (hour == 0) {
				values.add(DOE2Factory.createNumericValue(0));
			} else if (hour == onHour) {
				values.add(DOE2Factory.createNumericValue(1));
			} else if (hour == offHour) {
				values.add(DOE2Factory.createNumericValue(0));
			} else {
				values.add(DOE2Factory.createCodewordValue("&D"));
			}
		}

		schedule.addField(values);

		return schedule;

	}

	public static BookerObject onOffDaySchedule(String name, int value) {

		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "ON/OFF/FLAG"));

		BookerField values = DOE2Factory.createListField("VALUES");

		values.add(DOE2Factory.createNumericValue(value));

		schedule.addField(values);

		return schedule;

	}

	public static BookerObject onOffTempDaySchedule(String name, double value) {

		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "ON/OFF/TEMP"));

		BookerField values = DOE2Factory.createListField("VALUES");

		values.add(DOE2Factory.createNumericValue(value));

		schedule.addField(values);

		return schedule;

	}

	public static BookerObject onOffTempDaySchedule(String name, int onHour, int offHour) {

		BookerObject schedule = DOE2Factory.createObject(name, "DAY-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", "ON/OFF/TEMP"));

		BookerField values = DOE2Factory.createListField("VALUES");

		for (int hour = 0; hour <= Math.min(23, offHour + 1); hour++) {
			if (hour == 0) {
				values.add(DOE2Factory.createNumericValue(0));
			} else if (hour == onHour) {
				values.add(DOE2Factory.createNumericValue(1));
			} else if (hour == offHour) {
				values.add(DOE2Factory.createNumericValue(0));
			} else {
				values.add(DOE2Factory.createCodewordValue("&D"));
			}
		}

		schedule.addField(values);

		return schedule;

	}

	public static BookerObject weekSchedule(String name, String type, BookerObject weekdaySchedule,
			BookerObject weekendSchedule) {
		BookerObject schedule = DOE2Factory.createObject(name, "WEEK-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", type));
		BookerField values = DOE2Factory.createListField("DAY-SCHEDULES");
		values.add(DOE2Factory.createObjectValue(weekdaySchedule));
		for (int i = 0; i < 4; i++) {
			values.add(DOE2Factory.createCodewordValue("&D"));
		}
		values.add(DOE2Factory.createObjectValue(weekendSchedule));
		schedule.addField(values);
		return schedule;
	}

	public static BookerObject weekSchedule(String name, String type, BookerObject daySchedule) {
		BookerObject schedule = DOE2Factory.createObject(name, "WEEK-SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", type));
		BookerField values = DOE2Factory.createListField("DAY-SCHEDULES");
		values.add(DOE2Factory.createObjectValue(daySchedule));
		schedule.addField(values);
		return schedule;
	}

	public static BookerObject annualSchedule(String name, String type, BookerObject weekSchedule) {
		BookerObject schedule = DOE2Factory.createObject(name, "SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", type));
		BookerField month = DOE2Factory.createListField("MONTH");
		month.add(DOE2Factory.createNumericValue(12));
		BookerField day = DOE2Factory.createListField("DAY");
		day.add(DOE2Factory.createNumericValue(31));
		BookerField values = DOE2Factory.createListField("WEEK-SCHEDULES");
		values.add(DOE2Factory.createObjectValue(weekSchedule));
		schedule.addField(month);
		schedule.addField(day);
		schedule.addField(values);
		return schedule;
	}

	public static BookerObject annualSchedule(String name, String type, ArrayList<BookerObject> weekSchedules,
			ArrayList<Integer> months, ArrayList<Integer> days) {
		BookerObject schedule = DOE2Factory.createObject(name, "SCHEDULE-PD");
		schedule.addField(DOE2Factory.createCodewordField("TYPE", type));
		BookerField monthField = DOE2Factory.createListField("MONTH");
		for (Integer month : months) {
			monthField.add(DOE2Factory.createNumericValue(month));
		}

		BookerField dayField = DOE2Factory.createListField("DAY");
		for (Integer day : days) {
			dayField.add(DOE2Factory.createNumericValue(day));
		}
		BookerField values = DOE2Factory.createListField("WEEK-SCHEDULES");
		for (BookerObject weekSchedule : weekSchedules) {
			values.add(DOE2Factory.createObjectValue(weekSchedule));
		}
		schedule.addField(monthField);
		schedule.addField(dayField);
		schedule.addField(values);
		return schedule;
	}

}
