package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.annotations.BelongsTo;
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

/** This is an auto generated class representing the Operator type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Operators", type = Model.Type.USER, version = 1)
@Index(name = "OperatorByPlant", fields = {"plantID","id"})
public final class Operator implements Model {
  public static final QueryField ID = field("Operator", "id");
  public static final QueryField NAME = field("Operator", "name");
  public static final QueryField PLANT = field("Operator", "plantID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="InflowEntry") @HasMany(associatedWith = "operator", type = InflowEntry.class) List<InflowEntry> inflowEntries = null;
  private final @ModelField(targetType="RawEntry") @HasMany(associatedWith = "operator", type = RawEntry.class) List<RawEntry> rawEntries = null;
  private final @ModelField(targetType="ClarifiedEntry") @HasMany(associatedWith = "operator", type = ClarifiedEntry.class) List<ClarifiedEntry> clarifiedEntries = null;
  private final @ModelField(targetType="FilteredEntry") @HasMany(associatedWith = "operator", type = FilteredEntry.class) List<FilteredEntry> filteredEntries = null;
  private final @ModelField(targetType="CalibrationEntry") @HasMany(associatedWith = "operator", type = CalibrationEntry.class) List<CalibrationEntry> calibrationEntries = null;
  private final @ModelField(targetType="DoseEntry") @HasMany(associatedWith = "operator", type = DoseEntry.class) List<DoseEntry> doseEntries = null;
  private final @ModelField(targetType="FeedbackEntry") @HasMany(associatedWith = "operator", type = FeedbackEntry.class) List<FeedbackEntry> feedbackEntries = null;
  private final @ModelField(targetType="Plant") @BelongsTo(targetName = "plantID", targetNames = {"plantID"}, type = Plant.class) Plant plant;
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
  
  public String getName() {
      return name;
  }
  
  public List<InflowEntry> getInflowEntries() {
      return inflowEntries;
  }
  
  public List<RawEntry> getRawEntries() {
      return rawEntries;
  }
  
  public List<ClarifiedEntry> getClarifiedEntries() {
      return clarifiedEntries;
  }
  
  public List<FilteredEntry> getFilteredEntries() {
      return filteredEntries;
  }
  
  public List<CalibrationEntry> getCalibrationEntries() {
      return calibrationEntries;
  }
  
  public List<DoseEntry> getDoseEntries() {
      return doseEntries;
  }
  
  public List<FeedbackEntry> getFeedbackEntries() {
      return feedbackEntries;
  }
  
  public Plant getPlant() {
      return plant;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Operator(String id, String name, Plant plant) {
    this.id = id;
    this.name = name;
    this.plant = plant;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Operator operator = (Operator) obj;
      return ObjectsCompat.equals(getId(), operator.getId()) &&
              ObjectsCompat.equals(getName(), operator.getName()) &&
              ObjectsCompat.equals(getPlant(), operator.getPlant()) &&
              ObjectsCompat.equals(getCreatedAt(), operator.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), operator.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getPlant())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Operator {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("plant=" + String.valueOf(getPlant()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
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
  public static Operator justId(String id) {
    return new Operator(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      plant);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Operator build();
    BuildStep id(String id);
    BuildStep plant(Plant plant);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private Plant plant;
    public Builder() {
      
    }
    
    private Builder(String id, String name, Plant plant) {
      this.id = id;
      this.name = name;
      this.plant = plant;
    }
    
    @Override
     public Operator build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Operator(
          id,
          name,
          plant);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep plant(Plant plant) {
        this.plant = plant;
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
    private CopyOfBuilder(String id, String name, Plant plant) {
      super(id, name, plant);
      Objects.requireNonNull(name);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder plant(Plant plant) {
      return (CopyOfBuilder) super.plant(plant);
    }
  }
  

  public static class OperatorIdentifier extends ModelIdentifier<Operator> {
    private static final long serialVersionUID = 1L;
    public OperatorIdentifier(String id) {
      super(id);
    }
  }
  
}
