package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.annotations.BelongsTo;

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

/** This is an auto generated class representing the DoseEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "DoseEntries", type = Model.Type.USER, version = 1)
@Index(name = "DoseByPlant", fields = {"plantID","createdAt"})
@Index(name = "DoseByOperator", fields = {"operatorID","createdAt"})
@Index(name = "undefined", fields = {"calibrationEntryID"})
public final class DoseEntry implements Model {
  public static final QueryField ID = field("DoseEntry", "id");
  public static final QueryField CREATED_AT = field("DoseEntry", "createdAt");
  public static final QueryField PLANT_ID = field("DoseEntry", "plantID");
  public static final QueryField OPERATOR_ID = field("DoseEntry", "operatorID");
  public static final QueryField CHEMICAL_TYPE = field("DoseEntry", "chemicalType");
  public static final QueryField TARGET_DOSE = field("DoseEntry", "targetDose");
  public static final QueryField UPDATED_SLIDER_POSITION = field("DoseEntry", "updatedSliderPosition");
  public static final QueryField UPDATED_FLOW_RATE = field("DoseEntry", "updatedFlowRate");
  public static final QueryField CALIBRATION_ENTRY = field("DoseEntry", "calibrationEntryID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="ID", isRequired = true) String plantID;
  private final @ModelField(targetType="ID", isRequired = true) String operatorID;
  private final @ModelField(targetType="ChemicalType", isRequired = true) ChemicalType chemicalType;
  private final @ModelField(targetType="Float", isRequired = true) Double targetDose;
  private final @ModelField(targetType="Float", isRequired = true) Double updatedSliderPosition;
  private final @ModelField(targetType="Float", isRequired = true) Double updatedFlowRate;
  private final @ModelField(targetType="CalibrationEntry") @BelongsTo(targetName = "calibrationEntryID", targetNames = {"calibrationEntryID"}, type = CalibrationEntry.class) CalibrationEntry calibrationEntry;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public String getPlantId() {
      return plantID;
  }
  
  public String getOperatorId() {
      return operatorID;
  }
  
  public ChemicalType getChemicalType() {
      return chemicalType;
  }
  
  public Double getTargetDose() {
      return targetDose;
  }
  
  public Double getUpdatedSliderPosition() {
      return updatedSliderPosition;
  }
  
  public Double getUpdatedFlowRate() {
      return updatedFlowRate;
  }
  
  public CalibrationEntry getCalibrationEntry() {
      return calibrationEntry;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private DoseEntry(String id, Temporal.DateTime createdAt, String plantID, String operatorID, ChemicalType chemicalType, Double targetDose, Double updatedSliderPosition, Double updatedFlowRate, CalibrationEntry calibrationEntry) {
    this.id = id;
    this.createdAt = createdAt;
    this.plantID = plantID;
    this.operatorID = operatorID;
    this.chemicalType = chemicalType;
    this.targetDose = targetDose;
    this.updatedSliderPosition = updatedSliderPosition;
    this.updatedFlowRate = updatedFlowRate;
    this.calibrationEntry = calibrationEntry;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      DoseEntry doseEntry = (DoseEntry) obj;
      return ObjectsCompat.equals(getId(), doseEntry.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), doseEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getPlantId(), doseEntry.getPlantId()) &&
              ObjectsCompat.equals(getOperatorId(), doseEntry.getOperatorId()) &&
              ObjectsCompat.equals(getChemicalType(), doseEntry.getChemicalType()) &&
              ObjectsCompat.equals(getTargetDose(), doseEntry.getTargetDose()) &&
              ObjectsCompat.equals(getUpdatedSliderPosition(), doseEntry.getUpdatedSliderPosition()) &&
              ObjectsCompat.equals(getUpdatedFlowRate(), doseEntry.getUpdatedFlowRate()) &&
              ObjectsCompat.equals(getCalibrationEntry(), doseEntry.getCalibrationEntry()) &&
              ObjectsCompat.equals(getUpdatedAt(), doseEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getPlantId())
      .append(getOperatorId())
      .append(getChemicalType())
      .append(getTargetDose())
      .append(getUpdatedSliderPosition())
      .append(getUpdatedFlowRate())
      .append(getCalibrationEntry())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("DoseEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("plantID=" + String.valueOf(getPlantId()) + ", ")
      .append("operatorID=" + String.valueOf(getOperatorId()) + ", ")
      .append("chemicalType=" + String.valueOf(getChemicalType()) + ", ")
      .append("targetDose=" + String.valueOf(getTargetDose()) + ", ")
      .append("updatedSliderPosition=" + String.valueOf(getUpdatedSliderPosition()) + ", ")
      .append("updatedFlowRate=" + String.valueOf(getUpdatedFlowRate()) + ", ")
      .append("calibrationEntry=" + String.valueOf(getCalibrationEntry()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
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
  public static DoseEntry justId(String id) {
    return new DoseEntry(
      id,
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
      plantID,
      operatorID,
      chemicalType,
      targetDose,
      updatedSliderPosition,
      updatedFlowRate,
      calibrationEntry);
  }
  public interface CreatedAtStep {
    PlantIdStep createdAt(Temporal.DateTime createdAt);
  }
  

  public interface PlantIdStep {
    OperatorIdStep plantId(String plantId);
  }
  

  public interface OperatorIdStep {
    ChemicalTypeStep operatorId(String operatorId);
  }
  

  public interface ChemicalTypeStep {
    TargetDoseStep chemicalType(ChemicalType chemicalType);
  }
  

  public interface TargetDoseStep {
    UpdatedSliderPositionStep targetDose(Double targetDose);
  }
  

  public interface UpdatedSliderPositionStep {
    UpdatedFlowRateStep updatedSliderPosition(Double updatedSliderPosition);
  }
  

  public interface UpdatedFlowRateStep {
    BuildStep updatedFlowRate(Double updatedFlowRate);
  }
  

  public interface BuildStep {
    DoseEntry build();
    BuildStep id(String id);
    BuildStep calibrationEntry(CalibrationEntry calibrationEntry);
  }
  

  public static class Builder implements CreatedAtStep, PlantIdStep, OperatorIdStep, ChemicalTypeStep, TargetDoseStep, UpdatedSliderPositionStep, UpdatedFlowRateStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private String plantID;
    private String operatorID;
    private ChemicalType chemicalType;
    private Double targetDose;
    private Double updatedSliderPosition;
    private Double updatedFlowRate;
    private CalibrationEntry calibrationEntry;
    @Override
     public DoseEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new DoseEntry(
          id,
          createdAt,
          plantID,
          operatorID,
          chemicalType,
          targetDose,
          updatedSliderPosition,
          updatedFlowRate,
          calibrationEntry);
    }
    
    @Override
     public PlantIdStep createdAt(Temporal.DateTime createdAt) {
        Objects.requireNonNull(createdAt);
        this.createdAt = createdAt;
        return this;
    }
    
    @Override
     public OperatorIdStep plantId(String plantId) {
        Objects.requireNonNull(plantId);
        this.plantID = plantId;
        return this;
    }
    
    @Override
     public ChemicalTypeStep operatorId(String operatorId) {
        Objects.requireNonNull(operatorId);
        this.operatorID = operatorId;
        return this;
    }
    
    @Override
     public TargetDoseStep chemicalType(ChemicalType chemicalType) {
        Objects.requireNonNull(chemicalType);
        this.chemicalType = chemicalType;
        return this;
    }
    
    @Override
     public UpdatedSliderPositionStep targetDose(Double targetDose) {
        Objects.requireNonNull(targetDose);
        this.targetDose = targetDose;
        return this;
    }
    
    @Override
     public UpdatedFlowRateStep updatedSliderPosition(Double updatedSliderPosition) {
        Objects.requireNonNull(updatedSliderPosition);
        this.updatedSliderPosition = updatedSliderPosition;
        return this;
    }
    
    @Override
     public BuildStep updatedFlowRate(Double updatedFlowRate) {
        Objects.requireNonNull(updatedFlowRate);
        this.updatedFlowRate = updatedFlowRate;
        return this;
    }
    
    @Override
     public BuildStep calibrationEntry(CalibrationEntry calibrationEntry) {
        this.calibrationEntry = calibrationEntry;
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
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, String plantId, String operatorId, ChemicalType chemicalType, Double targetDose, Double updatedSliderPosition, Double updatedFlowRate, CalibrationEntry calibrationEntry) {
      super.id(id);
      super.createdAt(createdAt)
        .plantId(plantId)
        .operatorId(operatorId)
        .chemicalType(chemicalType)
        .targetDose(targetDose)
        .updatedSliderPosition(updatedSliderPosition)
        .updatedFlowRate(updatedFlowRate)
        .calibrationEntry(calibrationEntry);
    }
    
    @Override
     public CopyOfBuilder createdAt(Temporal.DateTime createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
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
     public CopyOfBuilder chemicalType(ChemicalType chemicalType) {
      return (CopyOfBuilder) super.chemicalType(chemicalType);
    }
    
    @Override
     public CopyOfBuilder targetDose(Double targetDose) {
      return (CopyOfBuilder) super.targetDose(targetDose);
    }
    
    @Override
     public CopyOfBuilder updatedSliderPosition(Double updatedSliderPosition) {
      return (CopyOfBuilder) super.updatedSliderPosition(updatedSliderPosition);
    }
    
    @Override
     public CopyOfBuilder updatedFlowRate(Double updatedFlowRate) {
      return (CopyOfBuilder) super.updatedFlowRate(updatedFlowRate);
    }
    
    @Override
     public CopyOfBuilder calibrationEntry(CalibrationEntry calibrationEntry) {
      return (CopyOfBuilder) super.calibrationEntry(calibrationEntry);
    }
  }
  
}
