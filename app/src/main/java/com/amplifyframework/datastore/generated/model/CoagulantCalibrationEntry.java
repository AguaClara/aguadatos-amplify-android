package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
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

/** This is an auto generated class representing the CoagulantCalibrationEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "CoagulantCalibrationEntries", type = Model.Type.USER, version = 1)
@Index(name = "coCalByPlantByOperatorByDate", fields = {"plantID","operatorID","creationDateTime"})
@Index(name = "byOperator", fields = {"operatorID"})
public final class CoagulantCalibrationEntry implements Model {
  public static final QueryField ID = field("CoagulantCalibrationEntry", "id");
  public static final QueryField PLANT_ID = field("CoagulantCalibrationEntry", "plantID");
  public static final QueryField OPERATOR_ID = field("CoagulantCalibrationEntry", "operatorID");
  public static final QueryField CREATION_DATE_TIME = field("CoagulantCalibrationEntry", "creationDateTime");
  public static final QueryField SLIDER_POSITION = field("CoagulantCalibrationEntry", "sliderPosition");
  public static final QueryField INFLOW_RATE = field("CoagulantCalibrationEntry", "inflowRate");
  public static final QueryField START_VOLUME = field("CoagulantCalibrationEntry", "startVolume");
  public static final QueryField END_VOLUME = field("CoagulantCalibrationEntry", "endVolume");
  public static final QueryField TIME_ELAPSED = field("CoagulantCalibrationEntry", "timeElapsed");
  public static final QueryField CHEMICAL_DOSE = field("CoagulantCalibrationEntry", "chemicalDose");
  public static final QueryField CHEMICAL_FLOW_RATE = field("CoagulantCalibrationEntry", "chemicalFlowRate");
  public static final QueryField ACTIVE_TANK_VOLUME = field("CoagulantCalibrationEntry", "activeTankVolume");
  public static final QueryField CHEMICAL_TYPE = field("CoagulantCalibrationEntry", "chemicalType");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String plantID;
  private final @ModelField(targetType="ID", isRequired = true) String operatorID;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime creationDateTime;
  private final @ModelField(targetType="Float", isRequired = true) Double sliderPosition;
  private final @ModelField(targetType="Float", isRequired = true) Double inflowRate;
  private final @ModelField(targetType="Float", isRequired = true) Double startVolume;
  private final @ModelField(targetType="Float", isRequired = true) Double endVolume;
  private final @ModelField(targetType="Int", isRequired = true) Integer timeElapsed;
  private final @ModelField(targetType="Float", isRequired = true) Double chemicalDose;
  private final @ModelField(targetType="Float", isRequired = true) Double chemicalFlowRate;
  private final @ModelField(targetType="Float", isRequired = true) Double activeTankVolume;
  private final @ModelField(targetType="Float", isRequired = true) Double chemicalType;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getPlantId() {
      return plantID;
  }
  
  public String getOperatorId() {
      return operatorID;
  }
  
  public Temporal.DateTime getCreationDateTime() {
      return creationDateTime;
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
  
  public Double getChemicalDose() {
      return chemicalDose;
  }
  
  public Double getChemicalFlowRate() {
      return chemicalFlowRate;
  }
  
  public Double getActiveTankVolume() {
      return activeTankVolume;
  }
  
  public Double getChemicalType() {
      return chemicalType;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private CoagulantCalibrationEntry(String id, String plantID, String operatorID, Temporal.DateTime creationDateTime, Double sliderPosition, Double inflowRate, Double startVolume, Double endVolume, Integer timeElapsed, Double chemicalDose, Double chemicalFlowRate, Double activeTankVolume, Double chemicalType) {
    this.id = id;
    this.plantID = plantID;
    this.operatorID = operatorID;
    this.creationDateTime = creationDateTime;
    this.sliderPosition = sliderPosition;
    this.inflowRate = inflowRate;
    this.startVolume = startVolume;
    this.endVolume = endVolume;
    this.timeElapsed = timeElapsed;
    this.chemicalDose = chemicalDose;
    this.chemicalFlowRate = chemicalFlowRate;
    this.activeTankVolume = activeTankVolume;
    this.chemicalType = chemicalType;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      CoagulantCalibrationEntry coagulantCalibrationEntry = (CoagulantCalibrationEntry) obj;
      return ObjectsCompat.equals(getId(), coagulantCalibrationEntry.getId()) &&
              ObjectsCompat.equals(getPlantId(), coagulantCalibrationEntry.getPlantId()) &&
              ObjectsCompat.equals(getOperatorId(), coagulantCalibrationEntry.getOperatorId()) &&
              ObjectsCompat.equals(getCreationDateTime(), coagulantCalibrationEntry.getCreationDateTime()) &&
              ObjectsCompat.equals(getSliderPosition(), coagulantCalibrationEntry.getSliderPosition()) &&
              ObjectsCompat.equals(getInflowRate(), coagulantCalibrationEntry.getInflowRate()) &&
              ObjectsCompat.equals(getStartVolume(), coagulantCalibrationEntry.getStartVolume()) &&
              ObjectsCompat.equals(getEndVolume(), coagulantCalibrationEntry.getEndVolume()) &&
              ObjectsCompat.equals(getTimeElapsed(), coagulantCalibrationEntry.getTimeElapsed()) &&
              ObjectsCompat.equals(getChemicalDose(), coagulantCalibrationEntry.getChemicalDose()) &&
              ObjectsCompat.equals(getChemicalFlowRate(), coagulantCalibrationEntry.getChemicalFlowRate()) &&
              ObjectsCompat.equals(getActiveTankVolume(), coagulantCalibrationEntry.getActiveTankVolume()) &&
              ObjectsCompat.equals(getChemicalType(), coagulantCalibrationEntry.getChemicalType()) &&
              ObjectsCompat.equals(getCreatedAt(), coagulantCalibrationEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), coagulantCalibrationEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPlantId())
      .append(getOperatorId())
      .append(getCreationDateTime())
      .append(getSliderPosition())
      .append(getInflowRate())
      .append(getStartVolume())
      .append(getEndVolume())
      .append(getTimeElapsed())
      .append(getChemicalDose())
      .append(getChemicalFlowRate())
      .append(getActiveTankVolume())
      .append(getChemicalType())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("CoagulantCalibrationEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("plantID=" + String.valueOf(getPlantId()) + ", ")
      .append("operatorID=" + String.valueOf(getOperatorId()) + ", ")
      .append("creationDateTime=" + String.valueOf(getCreationDateTime()) + ", ")
      .append("sliderPosition=" + String.valueOf(getSliderPosition()) + ", ")
      .append("inflowRate=" + String.valueOf(getInflowRate()) + ", ")
      .append("startVolume=" + String.valueOf(getStartVolume()) + ", ")
      .append("endVolume=" + String.valueOf(getEndVolume()) + ", ")
      .append("timeElapsed=" + String.valueOf(getTimeElapsed()) + ", ")
      .append("chemicalDose=" + String.valueOf(getChemicalDose()) + ", ")
      .append("chemicalFlowRate=" + String.valueOf(getChemicalFlowRate()) + ", ")
      .append("activeTankVolume=" + String.valueOf(getActiveTankVolume()) + ", ")
      .append("chemicalType=" + String.valueOf(getChemicalType()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static PlantIdStep builder() {
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
  public static CoagulantCalibrationEntry justId(String id) {
    return new CoagulantCalibrationEntry(
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      plantID,
      operatorID,
      creationDateTime,
      sliderPosition,
      inflowRate,
      startVolume,
      endVolume,
      timeElapsed,
      chemicalDose,
      chemicalFlowRate,
      activeTankVolume,
      chemicalType);
  }
  public interface PlantIdStep {
    OperatorIdStep plantId(String plantId);
  }
  

  public interface OperatorIdStep {
    CreationDateTimeStep operatorId(String operatorId);
  }
  

  public interface CreationDateTimeStep {
    SliderPositionStep creationDateTime(Temporal.DateTime creationDateTime);
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
    ChemicalDoseStep timeElapsed(Integer timeElapsed);
  }
  

  public interface ChemicalDoseStep {
    ChemicalFlowRateStep chemicalDose(Double chemicalDose);
  }
  

  public interface ChemicalFlowRateStep {
    ActiveTankVolumeStep chemicalFlowRate(Double chemicalFlowRate);
  }
  

  public interface ActiveTankVolumeStep {
    ChemicalTypeStep activeTankVolume(Double activeTankVolume);
  }
  

  public interface ChemicalTypeStep {
    BuildStep chemicalType(Double chemicalType);
  }
  

  public interface BuildStep {
    CoagulantCalibrationEntry build();
    BuildStep id(String id);
  }
  

  public static class Builder implements PlantIdStep, OperatorIdStep, CreationDateTimeStep, SliderPositionStep, InflowRateStep, StartVolumeStep, EndVolumeStep, TimeElapsedStep, ChemicalDoseStep, ChemicalFlowRateStep, ActiveTankVolumeStep, ChemicalTypeStep, BuildStep {
    private String id;
    private String plantID;
    private String operatorID;
    private Temporal.DateTime creationDateTime;
    private Double sliderPosition;
    private Double inflowRate;
    private Double startVolume;
    private Double endVolume;
    private Integer timeElapsed;
    private Double chemicalDose;
    private Double chemicalFlowRate;
    private Double activeTankVolume;
    private Double chemicalType;
    public Builder() {
      
    }
    
    private Builder(String id, String plantID, String operatorID, Temporal.DateTime creationDateTime, Double sliderPosition, Double inflowRate, Double startVolume, Double endVolume, Integer timeElapsed, Double chemicalDose, Double chemicalFlowRate, Double activeTankVolume, Double chemicalType) {
      this.id = id;
      this.plantID = plantID;
      this.operatorID = operatorID;
      this.creationDateTime = creationDateTime;
      this.sliderPosition = sliderPosition;
      this.inflowRate = inflowRate;
      this.startVolume = startVolume;
      this.endVolume = endVolume;
      this.timeElapsed = timeElapsed;
      this.chemicalDose = chemicalDose;
      this.chemicalFlowRate = chemicalFlowRate;
      this.activeTankVolume = activeTankVolume;
      this.chemicalType = chemicalType;
    }
    
    @Override
     public CoagulantCalibrationEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new CoagulantCalibrationEntry(
          id,
          plantID,
          operatorID,
          creationDateTime,
          sliderPosition,
          inflowRate,
          startVolume,
          endVolume,
          timeElapsed,
          chemicalDose,
          chemicalFlowRate,
          activeTankVolume,
          chemicalType);
    }
    
    @Override
     public OperatorIdStep plantId(String plantId) {
        Objects.requireNonNull(plantId);
        this.plantID = plantId;
        return this;
    }
    
    @Override
     public CreationDateTimeStep operatorId(String operatorId) {
        Objects.requireNonNull(operatorId);
        this.operatorID = operatorId;
        return this;
    }
    
    @Override
     public SliderPositionStep creationDateTime(Temporal.DateTime creationDateTime) {
        Objects.requireNonNull(creationDateTime);
        this.creationDateTime = creationDateTime;
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
     public ChemicalDoseStep timeElapsed(Integer timeElapsed) {
        Objects.requireNonNull(timeElapsed);
        this.timeElapsed = timeElapsed;
        return this;
    }
    
    @Override
     public ChemicalFlowRateStep chemicalDose(Double chemicalDose) {
        Objects.requireNonNull(chemicalDose);
        this.chemicalDose = chemicalDose;
        return this;
    }
    
    @Override
     public ActiveTankVolumeStep chemicalFlowRate(Double chemicalFlowRate) {
        Objects.requireNonNull(chemicalFlowRate);
        this.chemicalFlowRate = chemicalFlowRate;
        return this;
    }
    
    @Override
     public ChemicalTypeStep activeTankVolume(Double activeTankVolume) {
        Objects.requireNonNull(activeTankVolume);
        this.activeTankVolume = activeTankVolume;
        return this;
    }
    
    @Override
     public BuildStep chemicalType(Double chemicalType) {
        Objects.requireNonNull(chemicalType);
        this.chemicalType = chemicalType;
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
    private CopyOfBuilder(String id, String plantId, String operatorId, Temporal.DateTime creationDateTime, Double sliderPosition, Double inflowRate, Double startVolume, Double endVolume, Integer timeElapsed, Double chemicalDose, Double chemicalFlowRate, Double activeTankVolume, Double chemicalType) {
      super(id, plantID, operatorID, creationDateTime, sliderPosition, inflowRate, startVolume, endVolume, timeElapsed, chemicalDose, chemicalFlowRate, activeTankVolume, chemicalType);
      Objects.requireNonNull(plantID);
      Objects.requireNonNull(operatorID);
      Objects.requireNonNull(creationDateTime);
      Objects.requireNonNull(sliderPosition);
      Objects.requireNonNull(inflowRate);
      Objects.requireNonNull(startVolume);
      Objects.requireNonNull(endVolume);
      Objects.requireNonNull(timeElapsed);
      Objects.requireNonNull(chemicalDose);
      Objects.requireNonNull(chemicalFlowRate);
      Objects.requireNonNull(activeTankVolume);
      Objects.requireNonNull(chemicalType);
    }
    
    @Override
     public CopyOfBuilder plantId(String plantId) {
      return (CopyOfBuilder) super.plantId(plantId);
    }
    
    @Override
     public CopyOfBuilder operatorId(String operatorId) {
      return (CopyOfBuilder) super.operatorId(operatorId);
    }
    
    @Override
     public CopyOfBuilder creationDateTime(Temporal.DateTime creationDateTime) {
      return (CopyOfBuilder) super.creationDateTime(creationDateTime);
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
     public CopyOfBuilder chemicalDose(Double chemicalDose) {
      return (CopyOfBuilder) super.chemicalDose(chemicalDose);
    }
    
    @Override
     public CopyOfBuilder chemicalFlowRate(Double chemicalFlowRate) {
      return (CopyOfBuilder) super.chemicalFlowRate(chemicalFlowRate);
    }
    
    @Override
     public CopyOfBuilder activeTankVolume(Double activeTankVolume) {
      return (CopyOfBuilder) super.activeTankVolume(activeTankVolume);
    }
    
    @Override
     public CopyOfBuilder chemicalType(Double chemicalType) {
      return (CopyOfBuilder) super.chemicalType(chemicalType);
    }
  }
  

  public static class CoagulantCalibrationEntryIdentifier extends ModelIdentifier<CoagulantCalibrationEntry> {
    private static final long serialVersionUID = 1L;
    public CoagulantCalibrationEntryIdentifier(String id) {
      super(id);
    }
  }
  
}
