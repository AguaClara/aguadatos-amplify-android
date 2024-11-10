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

/** This is an auto generated class representing the ClarifiedWaterEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ClarifiedWaterEntries", type = Model.Type.USER, version = 1)
@Index(name = "cwByPlantByOperatorByDate", fields = {"plantID","operatorID","creationDateTime"})
@Index(name = "byOperator", fields = {"operatorID"})
public final class ClarifiedWaterEntry implements Model {
  public static final QueryField ID = field("ClarifiedWaterEntry", "id");
  public static final QueryField PLANT_ID = field("ClarifiedWaterEntry", "plantID");
  public static final QueryField OPERATOR_ID = field("ClarifiedWaterEntry", "operatorID");
  public static final QueryField CREATION_DATE_TIME = field("ClarifiedWaterEntry", "creationDateTime");
  public static final QueryField ADDITIONAL_NOTES = field("ClarifiedWaterEntry", "additionalNotes");
  public static final QueryField TURBIDITY = field("ClarifiedWaterEntry", "turbidity");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String plantID;
  private final @ModelField(targetType="ID", isRequired = true) String operatorID;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime creationDateTime;
  private final @ModelField(targetType="String", isRequired = true) String additionalNotes;
  private final @ModelField(targetType="Float", isRequired = true) Double turbidity;
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
  
  public Double getTurbidity() {
      return turbidity;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private ClarifiedWaterEntry(String id, String plantID, String operatorID, Temporal.DateTime creationDateTime, String additionalNotes, Double turbidity) {
    this.id = id;
    this.plantID = plantID;
    this.operatorID = operatorID;
    this.creationDateTime = creationDateTime;
    this.additionalNotes = additionalNotes;
    this.turbidity = turbidity;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ClarifiedWaterEntry clarifiedWaterEntry = (ClarifiedWaterEntry) obj;
      return ObjectsCompat.equals(getId(), clarifiedWaterEntry.getId()) &&
              ObjectsCompat.equals(getPlantId(), clarifiedWaterEntry.getPlantId()) &&
              ObjectsCompat.equals(getOperatorId(), clarifiedWaterEntry.getOperatorId()) &&
              ObjectsCompat.equals(getCreationDateTime(), clarifiedWaterEntry.getCreationDateTime()) &&
              ObjectsCompat.equals(getAdditionalNotes(), clarifiedWaterEntry.getAdditionalNotes()) &&
              ObjectsCompat.equals(getTurbidity(), clarifiedWaterEntry.getTurbidity()) &&
              ObjectsCompat.equals(getCreatedAt(), clarifiedWaterEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), clarifiedWaterEntry.getUpdatedAt());
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
      .append(getTurbidity())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ClarifiedWaterEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("plantID=" + String.valueOf(getPlantId()) + ", ")
      .append("operatorID=" + String.valueOf(getOperatorId()) + ", ")
      .append("creationDateTime=" + String.valueOf(getCreationDateTime()) + ", ")
      .append("additionalNotes=" + String.valueOf(getAdditionalNotes()) + ", ")
      .append("turbidity=" + String.valueOf(getTurbidity()) + ", ")
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
  public static ClarifiedWaterEntry justId(String id) {
    return new ClarifiedWaterEntry(
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
      turbidity);
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
    TurbidityStep additionalNotes(String additionalNotes);
  }
  

  public interface TurbidityStep {
    BuildStep turbidity(Double turbidity);
  }
  

  public interface BuildStep {
    ClarifiedWaterEntry build();
    BuildStep id(String id);
  }
  

  public static class Builder implements PlantIdStep, OperatorIdStep, CreationDateTimeStep, AdditionalNotesStep, TurbidityStep, BuildStep {
    private String id;
    private String plantID;
    private String operatorID;
    private Temporal.DateTime creationDateTime;
    private String additionalNotes;
    private Double turbidity;
    public Builder() {
      
    }
    
    private Builder(String id, String plantID, String operatorID, Temporal.DateTime creationDateTime, String additionalNotes, Double turbidity) {
      this.id = id;
      this.plantID = plantID;
      this.operatorID = operatorID;
      this.creationDateTime = creationDateTime;
      this.additionalNotes = additionalNotes;
      this.turbidity = turbidity;
    }
    
    @Override
     public ClarifiedWaterEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ClarifiedWaterEntry(
          id,
          plantID,
          operatorID,
          creationDateTime,
          additionalNotes,
          turbidity);
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
     public TurbidityStep additionalNotes(String additionalNotes) {
        Objects.requireNonNull(additionalNotes);
        this.additionalNotes = additionalNotes;
        return this;
    }
    
    @Override
     public BuildStep turbidity(Double turbidity) {
        Objects.requireNonNull(turbidity);
        this.turbidity = turbidity;
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
    private CopyOfBuilder(String id, String plantId, String operatorId, Temporal.DateTime creationDateTime, String additionalNotes, Double turbidity) {
      super(id, plantID, operatorID, creationDateTime, additionalNotes, turbidity);
      Objects.requireNonNull(plantID);
      Objects.requireNonNull(operatorID);
      Objects.requireNonNull(creationDateTime);
      Objects.requireNonNull(additionalNotes);
      Objects.requireNonNull(turbidity);
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
     public CopyOfBuilder turbidity(Double turbidity) {
      return (CopyOfBuilder) super.turbidity(turbidity);
    }
  }
  

  public static class ClarifiedWaterEntryIdentifier extends ModelIdentifier<ClarifiedWaterEntry> {
    private static final long serialVersionUID = 1L;
    public ClarifiedWaterEntryIdentifier(String id) {
      super(id);
    }
  }
  
}
