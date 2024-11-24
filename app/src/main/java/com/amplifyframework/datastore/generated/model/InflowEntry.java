package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

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

/** This is an auto generated class representing the InflowEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "InflowEntries", type = Model.Type.USER, version = 1)
@Index(name = "InflowByPlant", fields = {"plantID","createdAt"})
@Index(name = "InflowByOperator", fields = {"operatorID","createdAt"})
public final class InflowEntry implements Model {
  public static final QueryField ID = field("InflowEntry", "id");
  public static final QueryField CREATED_AT = field("InflowEntry", "createdAt");
  public static final QueryField PLANT_ID = field("InflowEntry", "plantID");
  public static final QueryField OPERATOR_ID = field("InflowEntry", "operatorID");
  public static final QueryField INFLOW_RATE = field("InflowEntry", "inflowRate");
  public static final QueryField NOTES = field("InflowEntry", "notes");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="ID", isRequired = true) String plantID;
  private final @ModelField(targetType="ID", isRequired = true) String operatorID;
  private final @ModelField(targetType="Float", isRequired = true) Double inflowRate;
  private final @ModelField(targetType="String") String notes;
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
  
  public Double getInflowRate() {
      return inflowRate;
  }
  
  public String getNotes() {
      return notes;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private InflowEntry(String id, Temporal.DateTime createdAt, String plantID, String operatorID, Double inflowRate, String notes) {
    this.id = id;
    this.createdAt = createdAt;
    this.plantID = plantID;
    this.operatorID = operatorID;
    this.inflowRate = inflowRate;
    this.notes = notes;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      InflowEntry inflowEntry = (InflowEntry) obj;
      return ObjectsCompat.equals(getId(), inflowEntry.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), inflowEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getPlantId(), inflowEntry.getPlantId()) &&
              ObjectsCompat.equals(getOperatorId(), inflowEntry.getOperatorId()) &&
              ObjectsCompat.equals(getInflowRate(), inflowEntry.getInflowRate()) &&
              ObjectsCompat.equals(getNotes(), inflowEntry.getNotes()) &&
              ObjectsCompat.equals(getUpdatedAt(), inflowEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getPlantId())
      .append(getOperatorId())
      .append(getInflowRate())
      .append(getNotes())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("InflowEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("plantID=" + String.valueOf(getPlantId()) + ", ")
      .append("operatorID=" + String.valueOf(getOperatorId()) + ", ")
      .append("inflowRate=" + String.valueOf(getInflowRate()) + ", ")
      .append("notes=" + String.valueOf(getNotes()) + ", ")
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
  public static InflowEntry justId(String id) {
    return new InflowEntry(
      id,
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
      inflowRate,
      notes);
  }
  public interface CreatedAtStep {
    PlantIdStep createdAt(Temporal.DateTime createdAt);
  }
  

  public interface PlantIdStep {
    OperatorIdStep plantId(String plantId);
  }
  

  public interface OperatorIdStep {
    InflowRateStep operatorId(String operatorId);
  }
  

  public interface InflowRateStep {
    BuildStep inflowRate(Double inflowRate);
  }
  

  public interface BuildStep {
    InflowEntry build();
    BuildStep id(String id);
    BuildStep notes(String notes);
  }
  

  public static class Builder implements CreatedAtStep, PlantIdStep, OperatorIdStep, InflowRateStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private String plantID;
    private String operatorID;
    private Double inflowRate;
    private String notes;
    @Override
     public InflowEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new InflowEntry(
          id,
          createdAt,
          plantID,
          operatorID,
          inflowRate,
          notes);
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
     public InflowRateStep operatorId(String operatorId) {
        Objects.requireNonNull(operatorId);
        this.operatorID = operatorId;
        return this;
    }
    
    @Override
     public BuildStep inflowRate(Double inflowRate) {
        Objects.requireNonNull(inflowRate);
        this.inflowRate = inflowRate;
        return this;
    }
    
    @Override
     public BuildStep notes(String notes) {
        this.notes = notes;
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
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, String plantId, String operatorId, Double inflowRate, String notes) {
      super.id(id);
      super.createdAt(createdAt)
        .plantId(plantId)
        .operatorId(operatorId)
        .inflowRate(inflowRate)
        .notes(notes);
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
     public CopyOfBuilder inflowRate(Double inflowRate) {
      return (CopyOfBuilder) super.inflowRate(inflowRate);
    }
    
    @Override
     public CopyOfBuilder notes(String notes) {
      return (CopyOfBuilder) super.notes(notes);
    }
  }
  
}
