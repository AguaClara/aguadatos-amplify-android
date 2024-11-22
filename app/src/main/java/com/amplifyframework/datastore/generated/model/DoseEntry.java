package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
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

/** This is an auto generated class representing the DoseEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "DoseEntries", type = Model.Type.USER, version = 1)
@Index(name = "DoseByPlant", fields = {"plantID","createdAt"})
@Index(name = "DoseByOperator", fields = {"operatorID","createdAt"})
@Index(name = "DoseByCalibration", fields = {"calibrationEntryID"})
public final class DoseEntry implements Model {
  public static final QueryField ID = field("DoseEntry", "id");
  public static final QueryField CREATED_AT = field("DoseEntry", "createdAt");
  public static final QueryField TARGET_DOSE = field("DoseEntry", "targetDose");
  public static final QueryField UPDATED_SLIDER_POSITION = field("DoseEntry", "updatedSliderPosition");
  public static final QueryField UPDATED_FLOW_RATE = field("DoseEntry", "updatedFlowRate");
  public static final QueryField CALIBRATION_ENTRY = field("DoseEntry", "calibrationEntryID");
  public static final QueryField PLANT = field("DoseEntry", "plantID");
  public static final QueryField OPERATOR = field("DoseEntry", "operatorID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="Float", isRequired = true) Double targetDose;
  private final @ModelField(targetType="Float", isRequired = true) Double updatedSliderPosition;
  private final @ModelField(targetType="Float", isRequired = true) Double updatedFlowRate;
  private final @ModelField(targetType="CalibrationEntry") @BelongsTo(targetName = "calibrationEntryID", targetNames = {"calibrationEntryID"}, type = CalibrationEntry.class) CalibrationEntry calibrationEntry;
  private final @ModelField(targetType="Plant") @BelongsTo(targetName = "plantID", targetNames = {"plantID"}, type = Plant.class) Plant plant;
  private final @ModelField(targetType="Operator") @BelongsTo(targetName = "operatorID", targetNames = {"operatorID"}, type = Operator.class) Operator operator;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
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
  
  public Plant getPlant() {
      return plant;
  }
  
  public Operator getOperator() {
      return operator;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private DoseEntry(String id, Temporal.DateTime createdAt, Double targetDose, Double updatedSliderPosition, Double updatedFlowRate, CalibrationEntry calibrationEntry, Plant plant, Operator operator) {
    this.id = id;
    this.createdAt = createdAt;
    this.targetDose = targetDose;
    this.updatedSliderPosition = updatedSliderPosition;
    this.updatedFlowRate = updatedFlowRate;
    this.calibrationEntry = calibrationEntry;
    this.plant = plant;
    this.operator = operator;
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
              ObjectsCompat.equals(getTargetDose(), doseEntry.getTargetDose()) &&
              ObjectsCompat.equals(getUpdatedSliderPosition(), doseEntry.getUpdatedSliderPosition()) &&
              ObjectsCompat.equals(getUpdatedFlowRate(), doseEntry.getUpdatedFlowRate()) &&
              ObjectsCompat.equals(getCalibrationEntry(), doseEntry.getCalibrationEntry()) &&
              ObjectsCompat.equals(getPlant(), doseEntry.getPlant()) &&
              ObjectsCompat.equals(getOperator(), doseEntry.getOperator()) &&
              ObjectsCompat.equals(getUpdatedAt(), doseEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getTargetDose())
      .append(getUpdatedSliderPosition())
      .append(getUpdatedFlowRate())
      .append(getCalibrationEntry())
      .append(getPlant())
      .append(getOperator())
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
      .append("targetDose=" + String.valueOf(getTargetDose()) + ", ")
      .append("updatedSliderPosition=" + String.valueOf(getUpdatedSliderPosition()) + ", ")
      .append("updatedFlowRate=" + String.valueOf(getUpdatedFlowRate()) + ", ")
      .append("calibrationEntry=" + String.valueOf(getCalibrationEntry()) + ", ")
      .append("plant=" + String.valueOf(getPlant()) + ", ")
      .append("operator=" + String.valueOf(getOperator()) + ", ")
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      createdAt,
      targetDose,
      updatedSliderPosition,
      updatedFlowRate,
      calibrationEntry,
      plant,
      operator);
  }
  public interface CreatedAtStep {
    TargetDoseStep createdAt(Temporal.DateTime createdAt);
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
    BuildStep plant(Plant plant);
    BuildStep operator(Operator operator);
  }
  

  public static class Builder implements CreatedAtStep, TargetDoseStep, UpdatedSliderPositionStep, UpdatedFlowRateStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private Double targetDose;
    private Double updatedSliderPosition;
    private Double updatedFlowRate;
    private CalibrationEntry calibrationEntry;
    private Plant plant;
    private Operator operator;
    public Builder() {
      
    }
    
    private Builder(String id, Temporal.DateTime createdAt, Double targetDose, Double updatedSliderPosition, Double updatedFlowRate, CalibrationEntry calibrationEntry, Plant plant, Operator operator) {
      this.id = id;
      this.createdAt = createdAt;
      this.targetDose = targetDose;
      this.updatedSliderPosition = updatedSliderPosition;
      this.updatedFlowRate = updatedFlowRate;
      this.calibrationEntry = calibrationEntry;
      this.plant = plant;
      this.operator = operator;
    }
    
    @Override
     public DoseEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new DoseEntry(
          id,
          createdAt,
          targetDose,
          updatedSliderPosition,
          updatedFlowRate,
          calibrationEntry,
          plant,
          operator);
    }
    
    @Override
     public TargetDoseStep createdAt(Temporal.DateTime createdAt) {
        Objects.requireNonNull(createdAt);
        this.createdAt = createdAt;
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
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, Double targetDose, Double updatedSliderPosition, Double updatedFlowRate, CalibrationEntry calibrationEntry, Plant plant, Operator operator) {
      super(id, createdAt, targetDose, updatedSliderPosition, updatedFlowRate, calibrationEntry, plant, operator);
      Objects.requireNonNull(createdAt);
      Objects.requireNonNull(targetDose);
      Objects.requireNonNull(updatedSliderPosition);
      Objects.requireNonNull(updatedFlowRate);
    }
    
    @Override
     public CopyOfBuilder createdAt(Temporal.DateTime createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
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
    
    @Override
     public CopyOfBuilder plant(Plant plant) {
      return (CopyOfBuilder) super.plant(plant);
    }
    
    @Override
     public CopyOfBuilder operator(Operator operator) {
      return (CopyOfBuilder) super.operator(operator);
    }
  }
  

  public static class DoseEntryIdentifier extends ModelIdentifier<DoseEntry> {
    private static final long serialVersionUID = 1L;
    public DoseEntryIdentifier(String id) {
      super(id);
    }
  }
  
}
