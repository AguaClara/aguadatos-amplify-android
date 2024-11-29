package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the Plant type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Plants", type = Model.Type.USER, version = 1)
public final class Plant implements Model {
  public static final QueryField ID = field("Plant", "id");
  public static final QueryField NAME = field("Plant", "name");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Operator") @HasMany(associatedWith = "plant", type = Operator.class) List<Operator> operators = null;
  private final @ModelField(targetType="InflowEntry") @HasMany(associatedWith = "plant", type = InflowEntry.class) List<InflowEntry> inflowEntries = null;
  private final @ModelField(targetType="RawEntry") @HasMany(associatedWith = "plant", type = RawEntry.class) List<RawEntry> rawEntries = null;
  private final @ModelField(targetType="ClarifiedEntry") @HasMany(associatedWith = "plant", type = ClarifiedEntry.class) List<ClarifiedEntry> clarifiedEntries = null;
  private final @ModelField(targetType="FilteredEntry") @HasMany(associatedWith = "plant", type = FilteredEntry.class) List<FilteredEntry> filteredEntries = null;
  private final @ModelField(targetType="CalibrationEntry") @HasMany(associatedWith = "plant", type = CalibrationEntry.class) List<CalibrationEntry> calibrationEntries = null;
  private final @ModelField(targetType="DoseEntry") @HasMany(associatedWith = "plant", type = DoseEntry.class) List<DoseEntry> doseEntries = null;
  private final @ModelField(targetType="FeedbackEntry") @HasMany(associatedWith = "plant", type = FeedbackEntry.class) List<FeedbackEntry> feedbackEntries = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public List<Operator> getOperators() {
      return operators;
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
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Plant(String id, String name) {
    this.id = id;
    this.name = name;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Plant plant = (Plant) obj;
      return ObjectsCompat.equals(getId(), plant.getId()) &&
              ObjectsCompat.equals(getName(), plant.getName()) &&
              ObjectsCompat.equals(getCreatedAt(), plant.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), plant.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Plant {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
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
  public static Plant justId(String id) {
    return new Plant(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Plant build();
    BuildStep id(String id);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    @Override
     public Plant build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Plant(
          id,
          name);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
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
    private CopyOfBuilder(String id, String name) {
      super.id(id);
      super.name(name);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
  }
  
}
