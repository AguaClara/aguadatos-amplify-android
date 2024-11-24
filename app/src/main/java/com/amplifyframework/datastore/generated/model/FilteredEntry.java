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

/** This is an auto generated class representing the FilteredEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FilteredEntries", type = Model.Type.USER, version = 1)
@Index(name = "FilteredByPlant", fields = {"plantID","createdAt"})
@Index(name = "FilteredByOperator", fields = {"operatorID","createdAt"})
public final class FilteredEntry implements Model {
  public static final QueryField ID = field("FilteredEntry", "id");
  public static final QueryField CREATED_AT = field("FilteredEntry", "createdAt");
  public static final QueryField PLANT_ID = field("FilteredEntry", "plantID");
  public static final QueryField OPERATOR_ID = field("FilteredEntry", "operatorID");
  public static final QueryField TURBIDITIES = field("FilteredEntry", "turbidities");
  public static final QueryField NOTES = field("FilteredEntry", "notes");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="ID", isRequired = true) String plantID;
  private final @ModelField(targetType="ID", isRequired = true) String operatorID;
  private final @ModelField(targetType="Float", isRequired = true) List<Double> turbidities;
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
  
  public List<Double> getTurbidities() {
      return turbidities;
  }
  
  public String getNotes() {
      return notes;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private FilteredEntry(String id, Temporal.DateTime createdAt, String plantID, String operatorID, List<Double> turbidities, String notes) {
    this.id = id;
    this.createdAt = createdAt;
    this.plantID = plantID;
    this.operatorID = operatorID;
    this.turbidities = turbidities;
    this.notes = notes;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      FilteredEntry filteredEntry = (FilteredEntry) obj;
      return ObjectsCompat.equals(getId(), filteredEntry.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), filteredEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getPlantId(), filteredEntry.getPlantId()) &&
              ObjectsCompat.equals(getOperatorId(), filteredEntry.getOperatorId()) &&
              ObjectsCompat.equals(getTurbidities(), filteredEntry.getTurbidities()) &&
              ObjectsCompat.equals(getNotes(), filteredEntry.getNotes()) &&
              ObjectsCompat.equals(getUpdatedAt(), filteredEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getPlantId())
      .append(getOperatorId())
      .append(getTurbidities())
      .append(getNotes())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("FilteredEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("plantID=" + String.valueOf(getPlantId()) + ", ")
      .append("operatorID=" + String.valueOf(getOperatorId()) + ", ")
      .append("turbidities=" + String.valueOf(getTurbidities()) + ", ")
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
  public static FilteredEntry justId(String id) {
    return new FilteredEntry(
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
      turbidities,
      notes);
  }
  public interface CreatedAtStep {
    PlantIdStep createdAt(Temporal.DateTime createdAt);
  }
  

  public interface PlantIdStep {
    OperatorIdStep plantId(String plantId);
  }
  

  public interface OperatorIdStep {
    TurbiditiesStep operatorId(String operatorId);
  }
  

  public interface TurbiditiesStep {
    BuildStep turbidities(List<Double> turbidities);
  }
  

  public interface BuildStep {
    FilteredEntry build();
    BuildStep id(String id);
    BuildStep notes(String notes);
  }
  

  public static class Builder implements CreatedAtStep, PlantIdStep, OperatorIdStep, TurbiditiesStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private String plantID;
    private String operatorID;
    private List<Double> turbidities;
    private String notes;
    @Override
     public FilteredEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new FilteredEntry(
          id,
          createdAt,
          plantID,
          operatorID,
          turbidities,
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
     public TurbiditiesStep operatorId(String operatorId) {
        Objects.requireNonNull(operatorId);
        this.operatorID = operatorId;
        return this;
    }
    
    @Override
     public BuildStep turbidities(List<Double> turbidities) {
        Objects.requireNonNull(turbidities);
        this.turbidities = turbidities;
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
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, String plantId, String operatorId, List<Double> turbidities, String notes) {
      super.id(id);
      super.createdAt(createdAt)
        .plantId(plantId)
        .operatorId(operatorId)
        .turbidities(turbidities)
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
     public CopyOfBuilder turbidities(List<Double> turbidities) {
      return (CopyOfBuilder) super.turbidities(turbidities);
    }
    
    @Override
     public CopyOfBuilder notes(String notes) {
      return (CopyOfBuilder) super.notes(notes);
    }
  }
  
}
