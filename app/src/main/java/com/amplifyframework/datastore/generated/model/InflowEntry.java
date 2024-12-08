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

/** This is an auto generated class representing the InflowEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "InflowEntries", type = Model.Type.USER, version = 1)
@Index(name = "InflowByPlant", fields = {"plantID","createdAt"})
@Index(name = "InflowByOperator", fields = {"operatorID","createdAt"})
public final class InflowEntry implements Model {
  public static final QueryField ID = field("InflowEntry", "id");
  public static final QueryField CREATED_AT = field("InflowEntry", "createdAt");
  public static final QueryField INFLOW_RATE = field("InflowEntry", "inflowRate");
  public static final QueryField NOTES = field("InflowEntry", "notes");
  public static final QueryField PLANT = field("InflowEntry", "plantID");
  public static final QueryField OPERATOR = field("InflowEntry", "operatorID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="Float", isRequired = true) Double inflowRate;
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
  
  public Double getInflowRate() {
      return inflowRate;
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
  
  private InflowEntry(String id, Temporal.DateTime createdAt, Double inflowRate, String notes, Plant plant, Operator operator) {
    this.id = id;
    this.createdAt = createdAt;
    this.inflowRate = inflowRate;
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
      InflowEntry inflowEntry = (InflowEntry) obj;
      return ObjectsCompat.equals(getId(), inflowEntry.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), inflowEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getInflowRate(), inflowEntry.getInflowRate()) &&
              ObjectsCompat.equals(getNotes(), inflowEntry.getNotes()) &&
              ObjectsCompat.equals(getPlant(), inflowEntry.getPlant()) &&
              ObjectsCompat.equals(getOperator(), inflowEntry.getOperator()) &&
              ObjectsCompat.equals(getUpdatedAt(), inflowEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getInflowRate())
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
      .append("InflowEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("inflowRate=" + String.valueOf(getInflowRate()) + ", ")
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
      inflowRate,
      notes,
      plant,
      operator);
  }
  public interface CreatedAtStep {
    InflowRateStep createdAt(Temporal.DateTime createdAt);
  }
  

  public interface InflowRateStep {
    BuildStep inflowRate(Double inflowRate);
  }
  

  public interface BuildStep {
    InflowEntry build();
    BuildStep id(String id);
    BuildStep notes(String notes);
    BuildStep plant(Plant plant);
    BuildStep operator(Operator operator);
  }
  

  public static class Builder implements CreatedAtStep, InflowRateStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private Double inflowRate;
    private String notes;
    private Plant plant;
    private Operator operator;
    @Override
     public InflowEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new InflowEntry(
          id,
          createdAt,
          inflowRate,
          notes,
          plant,
          operator);
    }
    
    @Override
     public InflowRateStep createdAt(Temporal.DateTime createdAt) {
        Objects.requireNonNull(createdAt);
        this.createdAt = createdAt;
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
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, Double inflowRate, String notes, Plant plant, Operator operator) {
      super.id(id);
      super.createdAt(createdAt)
        .inflowRate(inflowRate)
        .notes(notes)
        .plant(plant)
        .operator(operator);
    }
    
    @Override
     public CopyOfBuilder createdAt(Temporal.DateTime createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
    }
    
    @Override
     public CopyOfBuilder inflowRate(Double inflowRate) {
      return (CopyOfBuilder) super.inflowRate(inflowRate);
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
