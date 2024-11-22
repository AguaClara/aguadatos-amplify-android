package com.amplifyframework.datastore.generated.model;

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
  public static final QueryField PLANT_ID = field("Operator", "plantID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="ID", isRequired = true) String plantID;
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
  
  public String getPlantId() {
      return plantID;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Operator(String id, String name, String plantID) {
    this.id = id;
    this.name = name;
    this.plantID = plantID;
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
              ObjectsCompat.equals(getPlantId(), operator.getPlantId()) &&
              ObjectsCompat.equals(getCreatedAt(), operator.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), operator.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getPlantId())
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
      .append("plantID=" + String.valueOf(getPlantId()) + ", ")
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
      plantID);
  }
  public interface NameStep {
    PlantIdStep name(String name);
  }
  

  public interface PlantIdStep {
    BuildStep plantId(String plantId);
  }
  

  public interface BuildStep {
    Operator build();
    BuildStep id(String id);
  }
  

  public static class Builder implements NameStep, PlantIdStep, BuildStep {
    private String id;
    private String name;
    private String plantID;
    public Builder() {
      
    }
    
    private Builder(String id, String name, String plantID) {
      this.id = id;
      this.name = name;
      this.plantID = plantID;
    }
    
    @Override
     public Operator build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Operator(
          id,
          name,
          plantID);
    }
    
    @Override
     public PlantIdStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep plantId(String plantId) {
        Objects.requireNonNull(plantId);
        this.plantID = plantId;
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
    private CopyOfBuilder(String id, String name, String plantId) {
      super(id, name, plantID);
      Objects.requireNonNull(name);
      Objects.requireNonNull(plantID);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder plantId(String plantId) {
      return (CopyOfBuilder) super.plantId(plantId);
    }
  }
  

  public static class OperatorIdentifier extends ModelIdentifier<Operator> {
    private static final long serialVersionUID = 1L;
    public OperatorIdentifier(String id) {
      super(id);
    }
  }
  
}
