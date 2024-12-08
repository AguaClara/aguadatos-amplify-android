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

/** This is an auto generated class representing the FilteredEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FilteredEntries", type = Model.Type.USER, version = 1)
@Index(name = "FilteredByPlant", fields = {"plantID","createdAt"})
@Index(name = "FilteredByOperator", fields = {"operatorID","createdAt"})
public final class FilteredEntry implements Model {
  public static final QueryField ID = field("FilteredEntry", "id");
  public static final QueryField CREATED_AT = field("FilteredEntry", "createdAt");
  public static final QueryField TURBIDITIES = field("FilteredEntry", "turbidities");
  public static final QueryField NOTES = field("FilteredEntry", "notes");
  public static final QueryField PLANT = field("FilteredEntry", "plantID");
  public static final QueryField OPERATOR = field("FilteredEntry", "operatorID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="Float", isRequired = true) List<Double> turbidities;
  private final @ModelField(targetType="String") String notes;
  private final @ModelField(targetType="Plant") @BelongsTo(targetName = "plantID", targetNames = {"plantID"}, type = Plant.class) Plant plant;
  private final @ModelField(targetType="Operator") @BelongsTo(targetName = "operatorID", targetNames = {"operatorID"}, type = Operator.class) Operator operator;
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
  
  public List<Double> getTurbidities() {
      return turbidities;
  }
  
  public String getNotes() {
      return notes;
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
  
  private FilteredEntry(String id, Temporal.DateTime createdAt, List<Double> turbidities, String notes, Plant plant, Operator operator) {
    this.id = id;
    this.createdAt = createdAt;
    this.turbidities = turbidities;
    this.notes = notes;
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
      FilteredEntry filteredEntry = (FilteredEntry) obj;
      return ObjectsCompat.equals(getId(), filteredEntry.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), filteredEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getTurbidities(), filteredEntry.getTurbidities()) &&
              ObjectsCompat.equals(getNotes(), filteredEntry.getNotes()) &&
              ObjectsCompat.equals(getPlant(), filteredEntry.getPlant()) &&
              ObjectsCompat.equals(getOperator(), filteredEntry.getOperator()) &&
              ObjectsCompat.equals(getUpdatedAt(), filteredEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getTurbidities())
      .append(getNotes())
      .append(getPlant())
      .append(getOperator())
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
      .append("turbidities=" + String.valueOf(getTurbidities()) + ", ")
      .append("notes=" + String.valueOf(getNotes()) + ", ")
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
      turbidities,
      notes,
      plant,
      operator);
  }
  public interface CreatedAtStep {
    TurbiditiesStep createdAt(Temporal.DateTime createdAt);
  }
  

  public interface TurbiditiesStep {
    BuildStep turbidities(List<Double> turbidities);
  }
  

  public interface BuildStep {
    FilteredEntry build();
    BuildStep id(String id);
    BuildStep notes(String notes);
    BuildStep plant(Plant plant);
    BuildStep operator(Operator operator);
  }
  

  public static class Builder implements CreatedAtStep, TurbiditiesStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private List<Double> turbidities;
    private String notes;
    private Plant plant;
    private Operator operator;
    @Override
     public FilteredEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new FilteredEntry(
          id,
          createdAt,
          turbidities,
          notes,
          plant,
          operator);
    }
    
    @Override
     public TurbiditiesStep createdAt(Temporal.DateTime createdAt) {
        Objects.requireNonNull(createdAt);
        this.createdAt = createdAt;
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
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, List<Double> turbidities, String notes, Plant plant, Operator operator) {
      super.id(id);
      super.createdAt(createdAt)
        .turbidities(turbidities)
        .notes(notes)
        .plant(plant)
        .operator(operator);
    }
    
    @Override
     public CopyOfBuilder createdAt(Temporal.DateTime createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
    }
    
    @Override
     public CopyOfBuilder turbidities(List<Double> turbidities) {
      return (CopyOfBuilder) super.turbidities(turbidities);
    }
    
    @Override
     public CopyOfBuilder notes(String notes) {
      return (CopyOfBuilder) super.notes(notes);
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
  
}
