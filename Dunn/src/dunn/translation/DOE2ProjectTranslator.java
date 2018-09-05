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
package dunn.translation;

import java.util.ArrayList;

import booker.building_data.BookerProject;
import booker.building_data.Namespace;
import booker.building_data.NamespaceList;
import dunn.object_factories.ProjectCreator;

public class DOE2ProjectTranslator<T extends Namespace> {

	private ArrayList<RunPeriodCreator> runPeriodCreators;
	private ArrayList<SiteDataCreator> siteDataCreators;
	private ArrayList<DayScheduleCreator> dayScheduleCreators;
	private ArrayList<WeekScheduleCreator> weekScheduleCreators;
	private ArrayList<AnnualScheduleCreator> annualScheduleCreators;
	private ArrayList<ConstructionCreator> constructionCreators;
	private ArrayList<PolygonCreator> polygonCreators;
	private ArrayList<WeightFactorCreator> weightFactorCreators;
	private ArrayList<FloorCreator> floorCreators;
	private ArrayList<CurveCreator> curveCreators;
	private ArrayList<PumpCreator> pumpCreators;
	private ArrayList<LoopCreator> loopCreators;
	private ArrayList<ChillerCreator> chillerCreators;
	private ArrayList<CoolingTowerCreator> coolingTowerCreators;
	private ArrayList<SystemCreator> systemCreators;
	private ArrayList<EquipmentControlCreator> equipmentControlCreators;
	private ArrayList<MeterCreator> meterCreators;
	private ArrayList<ReportBlockCreator> reportBlockCreators;
	private ArrayList<ReportCreator> reportCreators;
	private ArrayList<ObjectModifier> objectModifiers;

	public DOE2ProjectTranslator(NamespaceList<T> objects) {
		runPeriodCreators = new ArrayList<RunPeriodCreator>();
		siteDataCreators = new ArrayList<SiteDataCreator>();
		dayScheduleCreators = new ArrayList<DayScheduleCreator>();
		weekScheduleCreators = new ArrayList<WeekScheduleCreator>();
		annualScheduleCreators = new ArrayList<AnnualScheduleCreator>();
		constructionCreators = new ArrayList<ConstructionCreator>();
		polygonCreators = new ArrayList<PolygonCreator>();
		weightFactorCreators = new ArrayList<WeightFactorCreator>();
		floorCreators = new ArrayList<FloorCreator>();
		curveCreators = new ArrayList<CurveCreator>();
		pumpCreators = new ArrayList<PumpCreator>();
		loopCreators = new ArrayList<LoopCreator>();
		chillerCreators = new ArrayList<ChillerCreator>();
		coolingTowerCreators = new ArrayList<CoolingTowerCreator>();
		systemCreators = new ArrayList<SystemCreator>();
		equipmentControlCreators = new ArrayList<EquipmentControlCreator>();
		meterCreators = new ArrayList<MeterCreator>();
		reportBlockCreators = new ArrayList<ReportBlockCreator>();
		reportCreators = new ArrayList<ReportCreator>();
		objectModifiers = new ArrayList<ObjectModifier>();
		for (int i = 0; i < objects.size(); i++) {
			T object = objects.get(i);

			if (object instanceof RunPeriodCreator) {
				runPeriodCreators.add((RunPeriodCreator) object);
			}

			if (object instanceof SiteDataCreator) {
				siteDataCreators.add((SiteDataCreator) object);
			}

			if (object instanceof DayScheduleCreator) {
				dayScheduleCreators.add((DayScheduleCreator) object);
			}

			if (object instanceof WeekScheduleCreator) {
				weekScheduleCreators.add((WeekScheduleCreator) object);
			}

			if (object instanceof AnnualScheduleCreator) {
				annualScheduleCreators.add((AnnualScheduleCreator) object);
			}

			if (object instanceof ConstructionCreator) {
				constructionCreators.add((ConstructionCreator) object);
			}

			if (object instanceof PolygonCreator) {
				polygonCreators.add((PolygonCreator) object);
			}

			if (object instanceof WeightFactorCreator) {
				weightFactorCreators.add((WeightFactorCreator) object);
			}

			if (object instanceof FloorCreator) {
				floorCreators.add((FloorCreator) object);
			}

			if (object instanceof CurveCreator) {
				curveCreators.add((CurveCreator) object);
			}

			if (object instanceof ChillerCreator) {
				chillerCreators.add((ChillerCreator) object);
			}

			if (object instanceof CoolingTowerCreator) {
				coolingTowerCreators.add((CoolingTowerCreator) object);
			}

			if (object instanceof PumpCreator) {
				pumpCreators.add((PumpCreator) object);
			}

			if (object instanceof LoopCreator) {
				loopCreators.add((LoopCreator) object);
			}

			if (object instanceof SystemCreator) {
				systemCreators.add((SystemCreator) object);
			}

			if (object instanceof EquipmentControlCreator) {
				equipmentControlCreators.add((EquipmentControlCreator) object);
			}

			if (object instanceof MeterCreator) {
				meterCreators.add((MeterCreator) object);
			}

			if (object instanceof ReportBlockCreator) {
				reportBlockCreators.add((ReportBlockCreator) object);
			}
			
			if (object instanceof ReportCreator) {
				reportCreators.add((ReportCreator) object);
			}

			if (object instanceof ObjectModifier) {
				objectModifiers.add((ObjectModifier) object);
			}

		}
	}

	public BookerProject createDOE2Project() {
		BookerProject project = ProjectCreator.initializeProject();

		for (RunPeriodCreator runPeriodCreator : runPeriodCreators) {
			runPeriodCreator.addRunPeriod(project);
		}

		for (SiteDataCreator siteDataCreator : siteDataCreators) {
			siteDataCreator.addSiteData(project);
		}

		for (DayScheduleCreator scheduleCreator : dayScheduleCreators) {
			scheduleCreator.addDaySchedules(project);
		}

		for (WeekScheduleCreator scheduleCreator : weekScheduleCreators) {
			scheduleCreator.addWeekSchedules(project);
		}

		for (AnnualScheduleCreator scheduleCreator : annualScheduleCreators) {
			scheduleCreator.addAnnualSchedules(project);
		}

		for (ConstructionCreator constructionCreator : constructionCreators) {
			constructionCreator.addConstructions(project);
		}

		for (CurveCreator curveCreator : curveCreators) {
			curveCreator.addCurve(project);
		}

		for (PolygonCreator polygonCreator : polygonCreators) {
			polygonCreator.addPolygon(project);
		}

		for (WeightFactorCreator weightFactorCreator : weightFactorCreators) {
			weightFactorCreator.addWeightFactors(project);
		}

		for (FloorCreator floorCreator : floorCreators) {
			floorCreator.addFloorsAndDescendants(project);
		}

		for (MeterCreator meterCreator : meterCreators) {
			meterCreator.addMeter(project);
		}

		for (PumpCreator pumpCreator : pumpCreators) {
			pumpCreator.addPump(project);
		}

		for (LoopCreator loopCreator : loopCreators) {
			loopCreator.addLoop(project);
		}

		for (ChillerCreator chillerCreator : chillerCreators) {
			chillerCreator.addChiller(project);
		}

		for (CoolingTowerCreator coolingTowerCreator : coolingTowerCreators) {
			coolingTowerCreator.addCoolingTower(project);
		}

		for (SystemCreator systemCreator : systemCreators) {
			systemCreator.addSystemAndDescendants(project);
		}

		for (EquipmentControlCreator equipmentControlCreator : equipmentControlCreators) {
			equipmentControlCreator.addEquipmentControl(project);
		}

		for (ReportBlockCreator reportBlockCreator : reportBlockCreators) {
			reportBlockCreator.addReportBlocks(project);
		}
		
		for (ReportCreator reportCreator : reportCreators) {
			reportCreator.addReports(project);
		}

		for (ObjectModifier objectModifier : objectModifiers) {
			objectModifier.modifyObjects(project);
		}

		ProjectCreator.completeProject(project);

		return project;
	}
}
