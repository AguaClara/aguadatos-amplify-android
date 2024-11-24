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

/** This is an auto generated class representing the FilteredWaterEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FilteredWaterEntries", type = Model.Type.USER, version = 1)
@Index(name = "fwByPlantByOperatorByDate", fields = {"plantID","operatorID","creationDateTime"})
@Index(name = "byOperator", fields = {"operatorID"})
public final class FilteredWaterEntry implements Model {
  public static final QueryField ID = field("FilteredWaterEntry", "id");
  public static final QueryField PLANT_ID = field("FilteredWaterEntry", "plantID");
  public static final QueryField OPERATOR_ID = field("FilteredWaterEntry", "operatorID");
  public static final QueryField CREATION_DATE_TIME = field("FilteredWaterEntry", "creationDateTime");
  public static final QueryField ADDITIONAL_NOTES = field("FilteredWaterEntry", "additionalNotes");
  public static final QueryField TURBIDITY_VALUES = field("FilteredWaterEntry", "turbidityValues");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String plantID;
  private final @ModelField(targetType="ID", isRequired = true) String operatorID;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime creationDateTime;
  private final @ModelField(targetType="String", isRequired = true) String additionalNotes;
  private final @ModelField(targetType="Float") List<Double> turbidityValues;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
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
  
  public String getAdditionalNotes() {
      return additionalNotes;
  }
  
  public List<Double> getTurbidityValues() {
      return turbidityValues;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private FilteredWaterEntry(String id, String plantID, String operatorID, Temporal.DateTime creationDateTime, String additionalNotes, List<Double> turbidityValues) {
    this.id = id;
    this.plantID = plantID;
    this.operatorID = operatorID;
    this.creationDateTime = creationDateTime;
    this.additionalNotes = additionalNotes;
    this.turbidityValues = turbidityValues;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      FilteredWaterEntry filteredWaterEntry = (FilteredWaterEntry) obj;
      return ObjectsCompat.equals(getId(), filteredWaterEntry.getId()) &&
              ObjectsCompat.equals(getPlantId(), filteredWaterEntry.getPlantId()) &&
              ObjectsCompat.equals(getOperatorId(), filteredWaterEntry.getOperatorId()) &&
              ObjectsCompat.equals(getCreationDateTime(), filteredWaterEntry.getCreationDateTime()) &&
              ObjectsCompat.equals(getAdditionalNotes(), filteredWaterEntry.getAdditionalNotes()) &&
              ObjectsCompat.equals(getTurbidityValues(), filteredWaterEntry.getTurbidityValues()) &&
              ObjectsCompat.equals(getCreatedAt(), filteredWaterEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), filteredWaterEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPlantId())
      .append(getOperatorId())
      .append(getCreationDateTime())
      .append(getAdditionalNotes())
      .append(getTurbidityValues())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("FilteredWaterEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("plantID=" + String.valueOf(getPlantId()) + ", ")
      .append("operatorID=" + String.valueOf(getOperatorId()) + ", ")
      .append("creationDateTime=" + String.valueOf(getCreationDateTime()) + ", ")
      .append("additionalNotes=" + String.valueOf(getAdditionalNotes()) + ", ")
      .append("turbidityValues=" + String.valueOf(getTurbidityValues()) + ", ")
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
  public static FilteredWaterEntry justId(String id) {
    return new FilteredWaterEntry(
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
      plantID,
      operatorID,
      creationDateTime,
      additionalNotes,
      turbidityValues);
  }
  public interface PlantIdStep {
    OperatorIdStep plantId(String plantId);
  }
  

  public interface OperatorIdStep {
    CreationDateTimeStep operatorId(String operatorId);
  }
  

  public interface CreationDateTimeStep {
    AdditionalNotesStep creationDateTime(Temporal.DateTime creationDateTime);
  }
  

  public interface AdditionalNotesStep {
    BuildStep additionalNotes(String additionalNotes);
  }
  

  public interface BuildStep {
    FilteredWaterEntry build();
    BuildStep id(String id);
    BuildStep turbidityValues(List<Double> turbidityValues);
  }
  

  public static class Builder implements PlantIdStep, OperatorIdStep, CreationDateTimeStep, AdditionalNotesStep, BuildStep {
    private String id;
    private String plantID;
    private String operatorID;
    private Temporal.DateTime creationDateTime;
    private String additionalNotes;
    private List<Double> turbidityValues;
    @Override
     public FilteredWaterEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new FilteredWaterEntry(
          id,
          plantID,
          operatorID,
          creationDateTime,
          additionalNotes,
          turbidityValues);
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
     public AdditionalNotesStep creationDateTime(Temporal.DateTime creationDateTime) {
        Objects.requireNonNull(creationDateTime);
        this.creationDateTime = creationDateTime;
        return this;
    }
    
    @Override
     public BuildStep additionalNotes(String additionalNotes) {
        Objects.requireNonNull(additionalNotes);
        this.additionalNotes = additionalNotes;
        return this;
    }
    
    @Override
     public BuildStep turbidityValues(List<Double> turbidityValues) {
        this.turbidityValues = turbidityValues;
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
    private CopyOfBuilder(String id, String plantId, String operatorId, Temporal.DateTime creationDateTime, String additionalNotes, List<Double> turbidityValues) {
      super.id(id);
      super.plantId(plantId)
        .operatorId(operatorId)
        .creationDateTime(creationDateTime)
        .additionalNotes(additionalNotes)
        .turbidityValues(turbidityValues);
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
     public CopyOfBuilder additionalNotes(String additionalNotes) {
      return (CopyOfBuilder) super.additionalNotes(additionalNotes);
    }
    
    @Override
     public CopyOfBuilder turbidityValues(List<Double> turbidityValues) {
      return (CopyOfBuilder) super.turbidityValues(turbidityValues);
    }
  }
  
}
