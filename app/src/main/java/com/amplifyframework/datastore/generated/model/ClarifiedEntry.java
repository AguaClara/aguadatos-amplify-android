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

/** This is an auto generated class representing the ClarifiedEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ClarifiedEntries", type = Model.Type.USER, version = 1)
@Index(name = "ClarifiedByPlant", fields = {"plantID","createdAt"})
@Index(name = "ClarifiedByOperator", fields = {"operatorID","createdAt"})
public final class ClarifiedEntry implements Model {
  public static final QueryField ID = field("ClarifiedEntry", "id");
  public static final QueryField CREATED_AT = field("ClarifiedEntry", "createdAt");
  public static final QueryField TURBIDITY = field("ClarifiedEntry", "turbidity");
  public static final QueryField NOTES = field("ClarifiedEntry", "notes");
  public static final QueryField PLANT = field("ClarifiedEntry", "plantID");
  public static final QueryField OPERATOR = field("ClarifiedEntry", "operatorID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="Float", isRequired = true) Double turbidity;
  private final @ModelField(targetType="String") String notes;
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
  
  public Double getTurbidity() {
      return turbidity;
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
  
  private ClarifiedEntry(String id, Temporal.DateTime createdAt, Double turbidity, String notes, Plant plant, Operator operator) {
    this.id = id;
    this.createdAt = createdAt;
    this.turbidity = turbidity;
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
      ClarifiedEntry clarifiedEntry = (ClarifiedEntry) obj;
      return ObjectsCompat.equals(getId(), clarifiedEntry.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), clarifiedEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getTurbidity(), clarifiedEntry.getTurbidity()) &&
              ObjectsCompat.equals(getNotes(), clarifiedEntry.getNotes()) &&
              ObjectsCompat.equals(getPlant(), clarifiedEntry.getPlant()) &&
              ObjectsCompat.equals(getOperator(), clarifiedEntry.getOperator()) &&
              ObjectsCompat.equals(getUpdatedAt(), clarifiedEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getTurbidity())
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
      .append("ClarifiedEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("turbidity=" + String.valueOf(getTurbidity()) + ", ")
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
  public static ClarifiedEntry justId(String id) {
    return new ClarifiedEntry(
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
      turbidity,
      notes,
      plant,
      operator);
  }
  public interface CreatedAtStep {
    TurbidityStep createdAt(Temporal.DateTime createdAt);
  }
  

  public interface TurbidityStep {
    BuildStep turbidity(Double turbidity);
  }
  

  public interface BuildStep {
    ClarifiedEntry build();
    BuildStep id(String id);
    BuildStep notes(String notes);
    BuildStep plant(Plant plant);
    BuildStep operator(Operator operator);
  }
  

  public static class Builder implements CreatedAtStep, TurbidityStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private Double turbidity;
    private String notes;
    private Plant plant;
    private Operator operator;
    public Builder() {
      
    }
    
    private Builder(String id, Temporal.DateTime createdAt, Double turbidity, String notes, Plant plant, Operator operator) {
      this.id = id;
      this.createdAt = createdAt;
      this.turbidity = turbidity;
      this.notes = notes;
      this.plant = plant;
      this.operator = operator;
    }
    
    @Override
     public ClarifiedEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ClarifiedEntry(
          id,
          createdAt,
          turbidity,
          notes,
          plant,
          operator);
    }
    
    @Override
     public TurbidityStep createdAt(Temporal.DateTime createdAt) {
        Objects.requireNonNull(createdAt);
        this.createdAt = createdAt;
        return this;
    }
    
    @Override
     public BuildStep turbidity(Double turbidity) {
        Objects.requireNonNull(turbidity);
        this.turbidity = turbidity;
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
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, Double turbidity, String notes, Plant plant, Operator operator) {
      super(id, createdAt, turbidity, notes, plant, operator);
      Objects.requireNonNull(createdAt);
      Objects.requireNonNull(turbidity);
    }
    
    @Override
     public CopyOfBuilder createdAt(Temporal.DateTime createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
    }
    
    @Override
     public CopyOfBuilder turbidity(Double turbidity) {
      return (CopyOfBuilder) super.turbidity(turbidity);
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
  

  public static class ClarifiedEntryIdentifier extends ModelIdentifier<ClarifiedEntry> {
    private static final long serialVersionUID = 1L;
    public ClarifiedEntryIdentifier(String id) {
      super(id);
    }
  }
  
}
