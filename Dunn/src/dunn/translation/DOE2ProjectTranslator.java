package dunn.translation;

import booker.building_data.BookerProject;
import booker.building_data.Namespace;
import booker.building_data.NamespaceList;
import dunn.object_factories.ProjectCreator;
import java.util.ArrayList;

public class DOE2ProjectTranslator <T extends Namespace> {
	private ArrayList<RunPeriodCreator> runPeriodCreators;
	private ArrayList<SiteDataCreator> siteDataCreators;
	private ArrayList<DayScheduleCreator> dayScheduleCreators;
	private ArrayList<WeekScheduleCreator> weekScheduleCreators;
	private ArrayList<AnnualScheduleCreator> annualScheduleCreators;
	private ArrayList<ConstructionCreator> constructionCreators;
	private ArrayList<PolygonCreator> polygonCreators;
	private ArrayList<WeightFactorCreator> weightFactorCreators;
	private ArrayList<ShadeCreator> shadeCreators;
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

	public DOE2ProjectTranslator() {
		initializeLists();
	}

	public DOE2ProjectTranslator(NamespaceList<T> objects) {
		initializeLists();
		appendObjects(objects);
	}

	public BookerProject createDOE2Project() {
		BookerProject project = ProjectCreator.initializeProject();

		for (RunPeriodCreator runPeriodCreator : this.runPeriodCreators) {
			runPeriodCreator.addRunPeriod(project);
		}

		for (SiteDataCreator siteDataCreator : this.siteDataCreators) {
			siteDataCreator.addSiteData(project);
		}

		for (DayScheduleCreator scheduleCreator : this.dayScheduleCreators) {
			scheduleCreator.addDaySchedules(project);
		}

		for (WeekScheduleCreator scheduleCreator : this.weekScheduleCreators) {
			scheduleCreator.addWeekSchedules(project);
		}

		for (AnnualScheduleCreator scheduleCreator : this.annualScheduleCreators) {
			scheduleCreator.addAnnualSchedules(project);
		}

		for (ConstructionCreator constructionCreator : this.constructionCreators) {
			constructionCreator.addConstructions(project);
		}

		for (CurveCreator curveCreator : this.curveCreators) {
			curveCreator.addCurve(project);
		}

		for (PolygonCreator polygonCreator : this.polygonCreators) {
			polygonCreator.addPolygon(project);
		}

		for (WeightFactorCreator weightFactorCreator : this.weightFactorCreators) {
			weightFactorCreator.addWeightFactors(project);
		}

		for (ShadeCreator shadeCreator : this.shadeCreators) {
			shadeCreator.addShade(project);
		}

		for (FloorCreator floorCreator : this.floorCreators) {
			floorCreator.addFloorsAndDescendants(project);
		}

		for (MeterCreator meterCreator : this.meterCreators) {
			meterCreator.addMeter(project);
		}

		for (PumpCreator pumpCreator : this.pumpCreators) {
			pumpCreator.addPump(project);
		}

		for (LoopCreator loopCreator : this.loopCreators) {
			loopCreator.addLoop(project);
		}

		for (ChillerCreator chillerCreator : this.chillerCreators) {
			chillerCreator.addChiller(project);
		}

		for (CoolingTowerCreator coolingTowerCreator : this.coolingTowerCreators) {
			coolingTowerCreator.addCoolingTower(project);
		}

		for (SystemCreator systemCreator : this.systemCreators) {
			systemCreator.addSystemAndDescendants(project);
		}

		for (EquipmentControlCreator equipmentControlCreator : this.equipmentControlCreators) {
			equipmentControlCreator.addEquipmentControl(project);
		}

		for (ReportBlockCreator reportBlockCreator : this.reportBlockCreators) {
			reportBlockCreator.addReportBlocks(project);
		}

		for (ReportCreator reportCreator : this.reportCreators) {
			reportCreator.addReports(project);
		}

		for (ObjectModifier objectModifier : this.objectModifiers) {
			objectModifier.modifyObjects(project);
		}

		ProjectCreator.completeProject(project);

		return project;
	}

	private void initializeLists() {
		this.runPeriodCreators = new ArrayList<RunPeriodCreator>();
		this.siteDataCreators = new ArrayList<SiteDataCreator>();
		this.dayScheduleCreators = new ArrayList<DayScheduleCreator>();
		this.weekScheduleCreators = new ArrayList<WeekScheduleCreator>();
		this.annualScheduleCreators = new ArrayList<AnnualScheduleCreator>();
		this.constructionCreators = new ArrayList<ConstructionCreator>();
		this.polygonCreators = new ArrayList<PolygonCreator>();
		this.shadeCreators = new ArrayList<ShadeCreator>();
		this.weightFactorCreators = new ArrayList<WeightFactorCreator>();
		this.floorCreators = new ArrayList<FloorCreator>();
		this.curveCreators = new ArrayList<CurveCreator>();
		this.pumpCreators = new ArrayList<PumpCreator>();
		this.loopCreators = new ArrayList<LoopCreator>();
		this.chillerCreators = new ArrayList<ChillerCreator>();
		this.coolingTowerCreators = new ArrayList<CoolingTowerCreator>();
		this.systemCreators = new ArrayList<SystemCreator>();
		this.equipmentControlCreators = new ArrayList<EquipmentControlCreator>();
		this.meterCreators = new ArrayList<MeterCreator>();
		this.reportBlockCreators = new ArrayList<ReportBlockCreator>();
		this.reportCreators = new ArrayList<ReportCreator>();
		this.objectModifiers = new ArrayList<ObjectModifier>();
	}

	public void appendObjects(NamespaceList<T> objects) {
		for (int i = 0; i < objects.size(); i++) {
			addObject(objects.get(i));
		}
	}

	public void addObject(T object) {
		if (object instanceof RunPeriodCreator) {
			this.runPeriodCreators.add((RunPeriodCreator) object);
		}

		if (object instanceof SiteDataCreator) {
			this.siteDataCreators.add((SiteDataCreator) object);
		}

		if (object instanceof DayScheduleCreator) {
			this.dayScheduleCreators.add((DayScheduleCreator) object);
		}

		if (object instanceof WeekScheduleCreator) {
			this.weekScheduleCreators.add((WeekScheduleCreator) object);
		}

		if (object instanceof AnnualScheduleCreator) {
			this.annualScheduleCreators.add((AnnualScheduleCreator) object);
		}

		if (object instanceof ConstructionCreator) {
			this.constructionCreators.add((ConstructionCreator) object);
		}

		if (object instanceof PolygonCreator) {
			this.polygonCreators.add((PolygonCreator) object);
		}

		if (object instanceof WeightFactorCreator) {
			this.weightFactorCreators.add((WeightFactorCreator) object);
		}

		if (object instanceof ShadeCreator) {
			this.shadeCreators.add((ShadeCreator) object);
		}

		if (object instanceof FloorCreator) {
			this.floorCreators.add((FloorCreator) object);
		}

		if (object instanceof CurveCreator) {
			this.curveCreators.add((CurveCreator) object);
		}

		if (object instanceof ChillerCreator) {
			this.chillerCreators.add((ChillerCreator) object);
		}

		if (object instanceof CoolingTowerCreator) {
			this.coolingTowerCreators.add((CoolingTowerCreator) object);
		}

		if (object instanceof PumpCreator) {
			this.pumpCreators.add((PumpCreator) object);
		}

		if (object instanceof LoopCreator) {
			this.loopCreators.add((LoopCreator) object);
		}

		if (object instanceof SystemCreator) {
			this.systemCreators.add((SystemCreator) object);
		}

		if (object instanceof EquipmentControlCreator) {
			this.equipmentControlCreators.add((EquipmentControlCreator) object);
		}

		if (object instanceof MeterCreator) {
			this.meterCreators.add((MeterCreator) object);
		}

		if (object instanceof ReportBlockCreator) {
			this.reportBlockCreators.add((ReportBlockCreator) object);
		}

		if (object instanceof ReportCreator) {
			this.reportCreators.add((ReportCreator) object);
		}

		if (object instanceof ObjectModifier)
			this.objectModifiers.add((ObjectModifier) object);
	}
}
