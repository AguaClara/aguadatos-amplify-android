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

/** This is an auto generated class representing the ChlorineChangeDoseEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ChlorineChangeDoseEntries", type = Model.Type.USER, version = 1)
@Index(name = "clChByPlantByOperatorByDate", fields = {"plantID","operatorID","creationDateTime"})
@Index(name = "byOperator", fields = {"operatorID"})
public final class ChlorineChangeDoseEntry implements Model {
  public static final QueryField ID = field("ChlorineChangeDoseEntry", "id");
  public static final QueryField PLANT_ID = field("ChlorineChangeDoseEntry", "plantID");
  public static final QueryField OPERATOR_ID = field("ChlorineChangeDoseEntry", "operatorID");
  public static final QueryField CREATION_DATE_TIME = field("ChlorineChangeDoseEntry", "creationDateTime");
  public static final QueryField CHEMICAL_FLOW_RATE = field("ChlorineChangeDoseEntry", "chemicalFlowRate");
  public static final QueryField CHEMICAL_DOSE = field("ChlorineChangeDoseEntry", "chemicalDose");
  public static final QueryField SLIDER_POSITION = field("ChlorineChangeDoseEntry", "sliderPosition");
  public static final QueryField TARGET_CHEMICAL_DOSE = field("ChlorineChangeDoseEntry", "targetChemicalDose");
  public static final QueryField UPDATED_SLIDER_POSITION = field("ChlorineChangeDoseEntry", "updatedSliderPosition");
  public static final QueryField UPDATED_CHEMICAL_FLOW_RATE = field("ChlorineChangeDoseEntry", "updatedChemicalFlowRate");
  public static final QueryField CHEMICAL_TYPE = field("ChlorineChangeDoseEntry", "chemicalType");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String plantID;
  private final @ModelField(targetType="ID", isRequired = true) String operatorID;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime creationDateTime;
  private final @ModelField(targetType="Float", isRequired = true) Double chemicalFlowRate;
  private final @ModelField(targetType="Float", isRequired = true) Double chemicalDose;
  private final @ModelField(targetType="Float", isRequired = true) Double sliderPosition;
  private final @ModelField(targetType="Float", isRequired = true) Double targetChemicalDose;
  private final @ModelField(targetType="Float", isRequired = true) Double updatedSliderPosition;
  private final @ModelField(targetType="Float", isRequired = true) Double updatedChemicalFlowRate;
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
  
  public Double getChemicalFlowRate() {
      return chemicalFlowRate;
  }
  
  public Double getChemicalDose() {
      return chemicalDose;
  }
  
  public Double getSliderPosition() {
      return sliderPosition;
  }
  
  public Double getTargetChemicalDose() {
      return targetChemicalDose;
  }
  
  public Double getUpdatedSliderPosition() {
      return updatedSliderPosition;
  }
  
  public Double getUpdatedChemicalFlowRate() {
      return updatedChemicalFlowRate;
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
  
  private ChlorineChangeDoseEntry(String id, String plantID, String operatorID, Temporal.DateTime creationDateTime, Double chemicalFlowRate, Double chemicalDose, Double sliderPosition, Double targetChemicalDose, Double updatedSliderPosition, Double updatedChemicalFlowRate, Double chemicalType) {
    this.id = id;
    this.plantID = plantID;
    this.operatorID = operatorID;
    this.creationDateTime = creationDateTime;
    this.chemicalFlowRate = chemicalFlowRate;
    this.chemicalDose = chemicalDose;
    this.sliderPosition = sliderPosition;
    this.targetChemicalDose = targetChemicalDose;
    this.updatedSliderPosition = updatedSliderPosition;
    this.updatedChemicalFlowRate = updatedChemicalFlowRate;
    this.chemicalType = chemicalType;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ChlorineChangeDoseEntry chlorineChangeDoseEntry = (ChlorineChangeDoseEntry) obj;
      return ObjectsCompat.equals(getId(), chlorineChangeDoseEntry.getId()) &&
              ObjectsCompat.equals(getPlantId(), chlorineChangeDoseEntry.getPlantId()) &&
              ObjectsCompat.equals(getOperatorId(), chlorineChangeDoseEntry.getOperatorId()) &&
              ObjectsCompat.equals(getCreationDateTime(), chlorineChangeDoseEntry.getCreationDateTime()) &&
              ObjectsCompat.equals(getChemicalFlowRate(), chlorineChangeDoseEntry.getChemicalFlowRate()) &&
              ObjectsCompat.equals(getChemicalDose(), chlorineChangeDoseEntry.getChemicalDose()) &&
              ObjectsCompat.equals(getSliderPosition(), chlorineChangeDoseEntry.getSliderPosition()) &&
              ObjectsCompat.equals(getTargetChemicalDose(), chlorineChangeDoseEntry.getTargetChemicalDose()) &&
              ObjectsCompat.equals(getUpdatedSliderPosition(), chlorineChangeDoseEntry.getUpdatedSliderPosition()) &&
              ObjectsCompat.equals(getUpdatedChemicalFlowRate(), chlorineChangeDoseEntry.getUpdatedChemicalFlowRate()) &&
              ObjectsCompat.equals(getChemicalType(), chlorineChangeDoseEntry.getChemicalType()) &&
              ObjectsCompat.equals(getCreatedAt(), chlorineChangeDoseEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), chlorineChangeDoseEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPlantId())
      .append(getOperatorId())
      .append(getCreationDateTime())
      .append(getChemicalFlowRate())
      .append(getChemicalDose())
      .append(getSliderPosition())
      .append(getTargetChemicalDose())
      .append(getUpdatedSliderPosition())
      .append(getUpdatedChemicalFlowRate())
      .append(getChemicalType())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ChlorineChangeDoseEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("plantID=" + String.valueOf(getPlantId()) + ", ")
      .append("operatorID=" + String.valueOf(getOperatorId()) + ", ")
      .append("creationDateTime=" + String.valueOf(getCreationDateTime()) + ", ")
      .append("chemicalFlowRate=" + String.valueOf(getChemicalFlowRate()) + ", ")
      .append("chemicalDose=" + String.valueOf(getChemicalDose()) + ", ")
      .append("sliderPosition=" + String.valueOf(getSliderPosition()) + ", ")
      .append("targetChemicalDose=" + String.valueOf(getTargetChemicalDose()) + ", ")
      .append("updatedSliderPosition=" + String.valueOf(getUpdatedSliderPosition()) + ", ")
      .append("updatedChemicalFlowRate=" + String.valueOf(getUpdatedChemicalFlowRate()) + ", ")
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
  public static ChlorineChangeDoseEntry justId(String id) {
    return new ChlorineChangeDoseEntry(
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      plantID,
      operatorID,
      creationDateTime,
      chemicalFlowRate,
      chemicalDose,
      sliderPosition,
      targetChemicalDose,
      updatedSliderPosition,
      updatedChemicalFlowRate,
      chemicalType);
  }
  public interface PlantIdStep {
    OperatorIdStep plantId(String plantId);
  }
  

  public interface OperatorIdStep {
    CreationDateTimeStep operatorId(String operatorId);
  }
  

  public interface CreationDateTimeStep {
    ChemicalFlowRateStep creationDateTime(Temporal.DateTime creationDateTime);
  }
  

  public interface ChemicalFlowRateStep {
    ChemicalDoseStep chemicalFlowRate(Double chemicalFlowRate);
  }
  

  public interface ChemicalDoseStep {
    SliderPositionStep chemicalDose(Double chemicalDose);
  }
  

  public interface SliderPositionStep {
    TargetChemicalDoseStep sliderPosition(Double sliderPosition);
  }
  

  public interface TargetChemicalDoseStep {
    UpdatedSliderPositionStep targetChemicalDose(Double targetChemicalDose);
  }
  

  public interface UpdatedSliderPositionStep {
    UpdatedChemicalFlowRateStep updatedSliderPosition(Double updatedSliderPosition);
  }
  

  public interface UpdatedChemicalFlowRateStep {
    ChemicalTypeStep updatedChemicalFlowRate(Double updatedChemicalFlowRate);
  }
  

  public interface ChemicalTypeStep {
    BuildStep chemicalType(Double chemicalType);
  }
  

  public interface BuildStep {
    ChlorineChangeDoseEntry build();
    BuildStep id(String id);
  }
  

  public static class Builder implements PlantIdStep, OperatorIdStep, CreationDateTimeStep, ChemicalFlowRateStep, ChemicalDoseStep, SliderPositionStep, TargetChemicalDoseStep, UpdatedSliderPositionStep, UpdatedChemicalFlowRateStep, ChemicalTypeStep, BuildStep {
    private String id;
    private String plantID;
    private String operatorID;
    private Temporal.DateTime creationDateTime;
    private Double chemicalFlowRate;
    private Double chemicalDose;
    private Double sliderPosition;
    private Double targetChemicalDose;
    private Double updatedSliderPosition;
    private Double updatedChemicalFlowRate;
    private Double chemicalType;
    public Builder() {
      
    }
    
    private Builder(String id, String plantID, String operatorID, Temporal.DateTime creationDateTime, Double chemicalFlowRate, Double chemicalDose, Double sliderPosition, Double targetChemicalDose, Double updatedSliderPosition, Double updatedChemicalFlowRate, Double chemicalType) {
      this.id = id;
      this.plantID = plantID;
      this.operatorID = operatorID;
      this.creationDateTime = creationDateTime;
      this.chemicalFlowRate = chemicalFlowRate;
      this.chemicalDose = chemicalDose;
      this.sliderPosition = sliderPosition;
      this.targetChemicalDose = targetChemicalDose;
      this.updatedSliderPosition = updatedSliderPosition;
      this.updatedChemicalFlowRate = updatedChemicalFlowRate;
      this.chemicalType = chemicalType;
    }
    
    @Override
     public ChlorineChangeDoseEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ChlorineChangeDoseEntry(
          id,
          plantID,
          operatorID,
          creationDateTime,
          chemicalFlowRate,
          chemicalDose,
          sliderPosition,
          targetChemicalDose,
          updatedSliderPosition,
          updatedChemicalFlowRate,
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
     public ChemicalFlowRateStep creationDateTime(Temporal.DateTime creationDateTime) {
        Objects.requireNonNull(creationDateTime);
        this.creationDateTime = creationDateTime;
        return this;
    }
    
    @Override
     public ChemicalDoseStep chemicalFlowRate(Double chemicalFlowRate) {
        Objects.requireNonNull(chemicalFlowRate);
        this.chemicalFlowRate = chemicalFlowRate;
        return this;
    }
    
    @Override
     public SliderPositionStep chemicalDose(Double chemicalDose) {
        Objects.requireNonNull(chemicalDose);
        this.chemicalDose = chemicalDose;
        return this;
    }
    
    @Override
     public TargetChemicalDoseStep sliderPosition(Double sliderPosition) {
        Objects.requireNonNull(sliderPosition);
        this.sliderPosition = sliderPosition;
        return this;
    }
    
    @Override
     public UpdatedSliderPositionStep targetChemicalDose(Double targetChemicalDose) {
        Objects.requireNonNull(targetChemicalDose);
        this.targetChemicalDose = targetChemicalDose;
        return this;
    }
    
    @Override
     public UpdatedChemicalFlowRateStep updatedSliderPosition(Double updatedSliderPosition) {
        Objects.requireNonNull(updatedSliderPosition);
        this.updatedSliderPosition = updatedSliderPosition;
        return this;
    }
    
    @Override
     public ChemicalTypeStep updatedChemicalFlowRate(Double updatedChemicalFlowRate) {
        Objects.requireNonNull(updatedChemicalFlowRate);
        this.updatedChemicalFlowRate = updatedChemicalFlowRate;
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
    private CopyOfBuilder(String id, String plantId, String operatorId, Temporal.DateTime creationDateTime, Double chemicalFlowRate, Double chemicalDose, Double sliderPosition, Double targetChemicalDose, Double updatedSliderPosition, Double updatedChemicalFlowRate, Double chemicalType) {
      super(id, plantID, operatorID, creationDateTime, chemicalFlowRate, chemicalDose, sliderPosition, targetChemicalDose, updatedSliderPosition, updatedChemicalFlowRate, chemicalType);
      Objects.requireNonNull(plantID);
      Objects.requireNonNull(operatorID);
      Objects.requireNonNull(creationDateTime);
      Objects.requireNonNull(chemicalFlowRate);
      Objects.requireNonNull(chemicalDose);
      Objects.requireNonNull(sliderPosition);
      Objects.requireNonNull(targetChemicalDose);
      Objects.requireNonNull(updatedSliderPosition);
      Objects.requireNonNull(updatedChemicalFlowRate);
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
     public CopyOfBuilder chemicalFlowRate(Double chemicalFlowRate) {
      return (CopyOfBuilder) super.chemicalFlowRate(chemicalFlowRate);
    }
    
    @Override
     public CopyOfBuilder chemicalDose(Double chemicalDose) {
      return (CopyOfBuilder) super.chemicalDose(chemicalDose);
    }
    
    @Override
     public CopyOfBuilder sliderPosition(Double sliderPosition) {
      return (CopyOfBuilder) super.sliderPosition(sliderPosition);
    }
    
    @Override
     public CopyOfBuilder targetChemicalDose(Double targetChemicalDose) {
      return (CopyOfBuilder) super.targetChemicalDose(targetChemicalDose);
    }
    
    @Override
     public CopyOfBuilder updatedSliderPosition(Double updatedSliderPosition) {
      return (CopyOfBuilder) super.updatedSliderPosition(updatedSliderPosition);
    }
    
    @Override
     public CopyOfBuilder updatedChemicalFlowRate(Double updatedChemicalFlowRate) {
      return (CopyOfBuilder) super.updatedChemicalFlowRate(updatedChemicalFlowRate);
    }
    
    @Override
     public CopyOfBuilder chemicalType(Double chemicalType) {
      return (CopyOfBuilder) super.chemicalType(chemicalType);
    }
  }
  

  public static class ChlorineChangeDoseEntryIdentifier extends ModelIdentifier<ChlorineChangeDoseEntry> {
    private static final long serialVersionUID = 1L;
    public ChlorineChangeDoseEntryIdentifier(String id) {
      super(id);
    }
  }
  
}
