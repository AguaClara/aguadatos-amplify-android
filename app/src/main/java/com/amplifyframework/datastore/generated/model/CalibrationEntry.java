package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.annotations.HasOne;
import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the CalibrationEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "CalibrationEntries", type = Model.Type.USER, version = 1)
@Index(name = "CalibrationByPlant", fields = {"plantID","createdAt"})
@Index(name = "CalibrationByOperator", fields = {"operatorID","createdAt"})
public final class CalibrationEntry implements Model {
  public static final QueryField ID = field("CalibrationEntry", "id");
  public static final QueryField CREATED_AT = field("CalibrationEntry", "createdAt");
  public static final QueryField CHEMICAL_TYPE = field("CalibrationEntry", "chemicalType");
  public static final QueryField COAG_TYPE = field("CalibrationEntry", "coagType");
  public static final QueryField SLIDER_POSITION = field("CalibrationEntry", "sliderPosition");
  public static final QueryField INFLOW_RATE = field("CalibrationEntry", "inflowRate");
  public static final QueryField START_VOLUME = field("CalibrationEntry", "startVolume");
  public static final QueryField END_VOLUME = field("CalibrationEntry", "endVolume");
  public static final QueryField TIME_ELAPSED = field("CalibrationEntry", "timeElapsed");
  public static final QueryField DOSE = field("CalibrationEntry", "dose");
  public static final QueryField FLOW_RATE = field("CalibrationEntry", "flowRate");
  public static final QueryField ACTIVE_TANK = field("CalibrationEntry", "activeTank");
  public static final QueryField TANK_VOLUME = field("CalibrationEntry", "tankVolume");
  public static final QueryField PLANT = field("CalibrationEntry", "plantID");
  public static final QueryField OPERATOR = field("CalibrationEntry", "operatorID");
  public static final QueryField CALIBRATION_ENTRY_DOSE_ENTRY_ID = field("CalibrationEntry", "calibrationEntryDoseEntryId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="ChemicalType", isRequired = true) ChemicalType chemicalType;
  private final @ModelField(targetType="CoagType") CoagType coagType;
  private final @ModelField(targetType="Float", isRequired = true) Double sliderPosition;
  private final @ModelField(targetType="Float", isRequired = true) Double inflowRate;
  private final @ModelField(targetType="Float", isRequired = true) Double startVolume;
  private final @ModelField(targetType="Float", isRequired = true) Double endVolume;
  private final @ModelField(targetType="Int", isRequired = true) Integer timeElapsed;
  private final @ModelField(targetType="Float", isRequired = true) Double dose;
  private final @ModelField(targetType="Float", isRequired = true) Double flowRate;
  private final @ModelField(targetType="ActiveTank") ActiveTank activeTank;
  private final @ModelField(targetType="Float") Double tankVolume;
  private final @ModelField(targetType="DoseEntry") @HasOne(associatedWith = "calibrationEntry", type = DoseEntry.class) DoseEntry doseEntry = null;
  private final @ModelField(targetType="Plant") @BelongsTo(targetName = "plantID", targetNames = {"plantID"}, type = Plant.class) Plant plant;
  private final @ModelField(targetType="Operator") @BelongsTo(targetName = "operatorID", targetNames = {"operatorID"}, type = Operator.class) Operator operator;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String calibrationEntryDoseEntryId;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public ChemicalType getChemicalType() {
      return chemicalType;
  }
  
  public CoagType getCoagType() {
      return coagType;
  }
  
  public Double getSliderPosition() {
      return sliderPosition;
  }
  
  public Double getInflowRate() {
      return inflowRate;
  }
  
  public Double getStartVolume() {
      return startVolume;
  }
  
  public Double getEndVolume() {
      return endVolume;
  }
  
  public Integer getTimeElapsed() {
      return timeElapsed;
  }
  
  public Double getDose() {
      return dose;
  }
  
  public Double getFlowRate() {
      return flowRate;
  }
  
  public ActiveTank getActiveTank() {
      return activeTank;
  }
  
  public Double getTankVolume() {
      return tankVolume;
  }
  
  public DoseEntry getDoseEntry() {
      return doseEntry;
  }
  
  public Plant getPlant() {
      return plant;
  }
  
  public Operator getOperator() {
      return operator;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getCalibrationEntryDoseEntryId() {
      return calibrationEntryDoseEntryId;
  }
  
  private CalibrationEntry(String id, Temporal.DateTime createdAt, ChemicalType chemicalType, CoagType coagType, Double sliderPosition, Double inflowRate, Double startVolume, Double endVolume, Integer timeElapsed, Double dose, Double flowRate, ActiveTank activeTank, Double tankVolume, Plant plant, Operator operator, String calibrationEntryDoseEntryId) {
    this.id = id;
    this.createdAt = createdAt;
    this.chemicalType = chemicalType;
    this.coagType = coagType;
    this.sliderPosition = sliderPosition;
    this.inflowRate = inflowRate;
    this.startVolume = startVolume;
    this.endVolume = endVolume;
    this.timeElapsed = timeElapsed;
    this.dose = dose;
    this.flowRate = flowRate;
    this.activeTank = activeTank;
    this.tankVolume = tankVolume;
    this.plant = plant;
    this.operator = operator;
    this.calibrationEntryDoseEntryId = calibrationEntryDoseEntryId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      CalibrationEntry calibrationEntry = (CalibrationEntry) obj;
      return ObjectsCompat.equals(getId(), calibrationEntry.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), calibrationEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getChemicalType(), calibrationEntry.getChemicalType()) &&
              ObjectsCompat.equals(getCoagType(), calibrationEntry.getCoagType()) &&
              ObjectsCompat.equals(getSliderPosition(), calibrationEntry.getSliderPosition()) &&
              ObjectsCompat.equals(getInflowRate(), calibrationEntry.getInflowRate()) &&
              ObjectsCompat.equals(getStartVolume(), calibrationEntry.getStartVolume()) &&
              ObjectsCompat.equals(getEndVolume(), calibrationEntry.getEndVolume()) &&
              ObjectsCompat.equals(getTimeElapsed(), calibrationEntry.getTimeElapsed()) &&
              ObjectsCompat.equals(getDose(), calibrationEntry.getDose()) &&
              ObjectsCompat.equals(getFlowRate(), calibrationEntry.getFlowRate()) &&
              ObjectsCompat.equals(getActiveTank(), calibrationEntry.getActiveTank()) &&
              ObjectsCompat.equals(getTankVolume(), calibrationEntry.getTankVolume()) &&
              ObjectsCompat.equals(getPlant(), calibrationEntry.getPlant()) &&
              ObjectsCompat.equals(getOperator(), calibrationEntry.getOperator()) &&
              ObjectsCompat.equals(getUpdatedAt(), calibrationEntry.getUpdatedAt()) &&
              ObjectsCompat.equals(getCalibrationEntryDoseEntryId(), calibrationEntry.getCalibrationEntryDoseEntryId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getChemicalType())
      .append(getCoagType())
      .append(getSliderPosition())
      .append(getInflowRate())
      .append(getStartVolume())
      .append(getEndVolume())
      .append(getTimeElapsed())
      .append(getDose())
      .append(getFlowRate())
      .append(getActiveTank())
      .append(getTankVolume())
      .append(getPlant())
      .append(getOperator())
      .append(getUpdatedAt())
      .append(getCalibrationEntryDoseEntryId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("CalibrationEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("chemicalType=" + String.valueOf(getChemicalType()) + ", ")
      .append("coagType=" + String.valueOf(getCoagType()) + ", ")
      .append("sliderPosition=" + String.valueOf(getSliderPosition()) + ", ")
      .append("inflowRate=" + String.valueOf(getInflowRate()) + ", ")
      .append("startVolume=" + String.valueOf(getStartVolume()) + ", ")
      .append("endVolume=" + String.valueOf(getEndVolume()) + ", ")
      .append("timeElapsed=" + String.valueOf(getTimeElapsed()) + ", ")
      .append("dose=" + String.valueOf(getDose()) + ", ")
      .append("flowRate=" + String.valueOf(getFlowRate()) + ", ")
      .append("activeTank=" + String.valueOf(getActiveTank()) + ", ")
      .append("tankVolume=" + String.valueOf(getTankVolume()) + ", ")
      .append("plant=" + String.valueOf(getPlant()) + ", ")
      .append("operator=" + String.valueOf(getOperator()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("calibrationEntryDoseEntryId=" + String.valueOf(getCalibrationEntryDoseEntryId()))
      .append("}")
      .toString();
  }
  
  public static CreatedAtStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static CalibrationEntry justId(String id) {
    return new CalibrationEntry(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      createdAt,
      chemicalType,
      coagType,
      sliderPosition,
      inflowRate,
      startVolume,
      endVolume,
      timeElapsed,
      dose,
      flowRate,
      activeTank,
      tankVolume,
      plant,
      operator,
      calibrationEntryDoseEntryId);
  }
  public interface CreatedAtStep {
    ChemicalTypeStep createdAt(Temporal.DateTime createdAt);
  }
  

  public interface ChemicalTypeStep {
    SliderPositionStep chemicalType(ChemicalType chemicalType);
  }
  

  public interface SliderPositionStep {
    InflowRateStep sliderPosition(Double sliderPosition);
  }
  

  public interface InflowRateStep {
    StartVolumeStep inflowRate(Double inflowRate);
  }
  

  public interface StartVolumeStep {
    EndVolumeStep startVolume(Double startVolume);
  }
  

  public interface EndVolumeStep {
    TimeElapsedStep endVolume(Double endVolume);
  }
  

  public interface TimeElapsedStep {
    DoseStep timeElapsed(Integer timeElapsed);
  }
  

  public interface DoseStep {
    FlowRateStep dose(Double dose);
  }
  

  public interface FlowRateStep {
    BuildStep flowRate(Double flowRate);
  }
  

  public interface BuildStep {
    CalibrationEntry build();
    BuildStep id(String id);
    BuildStep coagType(CoagType coagType);
    BuildStep activeTank(ActiveTank activeTank);
    BuildStep tankVolume(Double tankVolume);
    BuildStep plant(Plant plant);
    BuildStep operator(Operator operator);
    BuildStep calibrationEntryDoseEntryId(String calibrationEntryDoseEntryId);
  }
  

  public static class Builder implements CreatedAtStep, ChemicalTypeStep, SliderPositionStep, InflowRateStep, StartVolumeStep, EndVolumeStep, TimeElapsedStep, DoseStep, FlowRateStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private ChemicalType chemicalType;
    private Double sliderPosition;
    private Double inflowRate;
    private Double startVolume;
    private Double endVolume;
    private Integer timeElapsed;
    private Double dose;
    private Double flowRate;
    private CoagType coagType;
    private ActiveTank activeTank;
    private Double tankVolume;
    private Plant plant;
    private Operator operator;
    private String calibrationEntryDoseEntryId;
    public Builder() {
      
    }
    
    private Builder(String id, Temporal.DateTime createdAt, ChemicalType chemicalType, CoagType coagType, Double sliderPosition, Double inflowRate, Double startVolume, Double endVolume, Integer timeElapsed, Double dose, Double flowRate, ActiveTank activeTank, Double tankVolume, Plant plant, Operator operator, String calibrationEntryDoseEntryId) {
      this.id = id;
      this.createdAt = createdAt;
      this.chemicalType = chemicalType;
      this.coagType = coagType;
      this.sliderPosition = sliderPosition;
      this.inflowRate = inflowRate;
      this.startVolume = startVolume;
      this.endVolume = endVolume;
      this.timeElapsed = timeElapsed;
      this.dose = dose;
      this.flowRate = flowRate;
      this.activeTank = activeTank;
      this.tankVolume = tankVolume;
      this.plant = plant;
      this.operator = operator;
      this.calibrationEntryDoseEntryId = calibrationEntryDoseEntryId;
    }
    
    @Override
     public CalibrationEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new CalibrationEntry(
          id,
          createdAt,
          chemicalType,
          coagType,
          sliderPosition,
          inflowRate,
          startVolume,
          endVolume,
          timeElapsed,
          dose,
          flowRate,
          activeTank,
          tankVolume,
          plant,
          operator,
          calibrationEntryDoseEntryId);
    }
    
    @Override
     public ChemicalTypeStep createdAt(Temporal.DateTime createdAt) {
        Objects.requireNonNull(createdAt);
        this.createdAt = createdAt;
        return this;
    }
    
    @Override
     public SliderPositionStep chemicalType(ChemicalType chemicalType) {
        Objects.requireNonNull(chemicalType);
        this.chemicalType = chemicalType;
        return this;
    }
    
    @Override
     public InflowRateStep sliderPosition(Double sliderPosition) {
        Objects.requireNonNull(sliderPosition);
        this.sliderPosition = sliderPosition;
        return this;
    }
    
    @Override
     public StartVolumeStep inflowRate(Double inflowRate) {
        Objects.requireNonNull(inflowRate);
        this.inflowRate = inflowRate;
        return this;
    }
    
    @Override
     public EndVolumeStep startVolume(Double startVolume) {
        Objects.requireNonNull(startVolume);
        this.startVolume = startVolume;
        return this;
    }
    
    @Override
     public TimeElapsedStep endVolume(Double endVolume) {
        Objects.requireNonNull(endVolume);
        this.endVolume = endVolume;
        return this;
    }
    
    @Override
     public DoseStep timeElapsed(Integer timeElapsed) {
        Objects.requireNonNull(timeElapsed);
        this.timeElapsed = timeElapsed;
        return this;
    }
    
    @Override
     public FlowRateStep dose(Double dose) {
        Objects.requireNonNull(dose);
        this.dose = dose;
        return this;
    }
    
    @Override
     public BuildStep flowRate(Double flowRate) {
        Objects.requireNonNull(flowRate);
        this.flowRate = flowRate;
        return this;
    }
    
    @Override
     public BuildStep coagType(CoagType coagType) {
        this.coagType = coagType;
        return this;
    }
    
    @Override
     public BuildStep activeTank(ActiveTank activeTank) {
        this.activeTank = activeTank;
        return this;
    }
    
    @Override
     public BuildStep tankVolume(Double tankVolume) {
        this.tankVolume = tankVolume;
        return this;
    }
    
    @Override
     public BuildStep plant(Plant plant) {
        this.plant = plant;
        return this;
    }
    
    @Override
     public BuildStep operator(Operator operator) {
        this.operator = operator;
        return this;
    }
    
    @Override
     public BuildStep calibrationEntryDoseEntryId(String calibrationEntryDoseEntryId) {
        this.calibrationEntryDoseEntryId = calibrationEntryDoseEntryId;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, ChemicalType chemicalType, CoagType coagType, Double sliderPosition, Double inflowRate, Double startVolume, Double endVolume, Integer timeElapsed, Double dose, Double flowRate, ActiveTank activeTank, Double tankVolume, Plant plant, Operator operator, String calibrationEntryDoseEntryId) {
      super(id, createdAt, chemicalType, coagType, sliderPosition, inflowRate, startVolume, endVolume, timeElapsed, dose, flowRate, activeTank, tankVolume, plant, operator, calibrationEntryDoseEntryId);
      Objects.requireNonNull(createdAt);
      Objects.requireNonNull(chemicalType);
      Objects.requireNonNull(sliderPosition);
      Objects.requireNonNull(inflowRate);
      Objects.requireNonNull(startVolume);
      Objects.requireNonNull(endVolume);
      Objects.requireNonNull(timeElapsed);
      Objects.requireNonNull(dose);
      Objects.requireNonNull(flowRate);
    }
    
    @Override
     public CopyOfBuilder createdAt(Temporal.DateTime createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
    }
    
    @Override
     public CopyOfBuilder chemicalType(ChemicalType chemicalType) {
      return (CopyOfBuilder) super.chemicalType(chemicalType);
    }
    
    @Override
     public CopyOfBuilder sliderPosition(Double sliderPosition) {
      return (CopyOfBuilder) super.sliderPosition(sliderPosition);
    }
    
    @Override
     public CopyOfBuilder inflowRate(Double inflowRate) {
      return (CopyOfBuilder) super.inflowRate(inflowRate);
    }
    
    @Override
     public CopyOfBuilder startVolume(Double startVolume) {
      return (CopyOfBuilder) super.startVolume(startVolume);
    }
    
    @Override
     public CopyOfBuilder endVolume(Double endVolume) {
      return (CopyOfBuilder) super.endVolume(endVolume);
    }
    
    @Override
     public CopyOfBuilder timeElapsed(Integer timeElapsed) {
      return (CopyOfBuilder) super.timeElapsed(timeElapsed);
    }
    
    @Override
     public CopyOfBuilder dose(Double dose) {
      return (CopyOfBuilder) super.dose(dose);
    }
    
    @Override
     public CopyOfBuilder flowRate(Double flowRate) {
      return (CopyOfBuilder) super.flowRate(flowRate);
    }
    
    @Override
     public CopyOfBuilder coagType(CoagType coagType) {
      return (CopyOfBuilder) super.coagType(coagType);
    }
    
    @Override
     public CopyOfBuilder activeTank(ActiveTank activeTank) {
      return (CopyOfBuilder) super.activeTank(activeTank);
    }
    
    @Override
     public CopyOfBuilder tankVolume(Double tankVolume) {
      return (CopyOfBuilder) super.tankVolume(tankVolume);
    }
    
    @Override
     public CopyOfBuilder plant(Plant plant) {
      return (CopyOfBuilder) super.plant(plant);
    }
    
    @Override
     public CopyOfBuilder operator(Operator operator) {
      return (CopyOfBuilder) super.operator(operator);
    }
    
    @Override
     public CopyOfBuilder calibrationEntryDoseEntryId(String calibrationEntryDoseEntryId) {
      return (CopyOfBuilder) super.calibrationEntryDoseEntryId(calibrationEntryDoseEntryId);
    }
  }
  

  public static class CalibrationEntryIdentifier extends ModelIdentifier<CalibrationEntry> {
    private static final long serialVersionUID = 1L;
    public CalibrationEntryIdentifier(String id) {
      super(id);
    }
  }
  
}
