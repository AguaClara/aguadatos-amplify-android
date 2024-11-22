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

/** This is an auto generated class representing the FeedbackEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FeedbackEntries", type = Model.Type.USER, version = 1)
@Index(name = "FeedbackByPlant", fields = {"plantID","createdAt"})
@Index(name = "FeedbackByOperator", fields = {"operatorID","createdAt"})
public final class FeedbackEntry implements Model {
  public static final QueryField ID = field("FeedbackEntry", "id");
  public static final QueryField CREATED_AT = field("FeedbackEntry", "createdAt");
  public static final QueryField PLANT_ID = field("FeedbackEntry", "plantID");
  public static final QueryField OPERATOR_ID = field("FeedbackEntry", "operatorID");
  public static final QueryField FEEDBACK = field("FeedbackEntry", "feedback");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="ID", isRequired = true) String plantID;
  private final @ModelField(targetType="ID", isRequired = true) String operatorID;
  private final @ModelField(targetType="String", isRequired = true) String feedback;
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
  
  public String getPlantId() {
      return plantID;
  }
  
  public String getOperatorId() {
      return operatorID;
  }
  
  public String getFeedback() {
      return feedback;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private FeedbackEntry(String id, Temporal.DateTime createdAt, String plantID, String operatorID, String feedback) {
    this.id = id;
    this.createdAt = createdAt;
    this.plantID = plantID;
    this.operatorID = operatorID;
    this.feedback = feedback;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      FeedbackEntry feedbackEntry = (FeedbackEntry) obj;
      return ObjectsCompat.equals(getId(), feedbackEntry.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), feedbackEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getPlantId(), feedbackEntry.getPlantId()) &&
              ObjectsCompat.equals(getOperatorId(), feedbackEntry.getOperatorId()) &&
              ObjectsCompat.equals(getFeedback(), feedbackEntry.getFeedback()) &&
              ObjectsCompat.equals(getUpdatedAt(), feedbackEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getPlantId())
      .append(getOperatorId())
      .append(getFeedback())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("FeedbackEntry {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("plantID=" + String.valueOf(getPlantId()) + ", ")
      .append("operatorID=" + String.valueOf(getOperatorId()) + ", ")
      .append("feedback=" + String.valueOf(getFeedback()) + ", ")
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
  public static FeedbackEntry justId(String id) {
    return new FeedbackEntry(
      id,
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
      feedback);
  }
  public interface CreatedAtStep {
    PlantIdStep createdAt(Temporal.DateTime createdAt);
  }
  

  public interface PlantIdStep {
    OperatorIdStep plantId(String plantId);
  }
  

  public interface OperatorIdStep {
    FeedbackStep operatorId(String operatorId);
  }
  

  public interface FeedbackStep {
    BuildStep feedback(String feedback);
  }
  

  public interface BuildStep {
    FeedbackEntry build();
    BuildStep id(String id);
  }
  

  public static class Builder implements CreatedAtStep, PlantIdStep, OperatorIdStep, FeedbackStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private String plantID;
    private String operatorID;
    private String feedback;
    public Builder() {
      
    }
    
    private Builder(String id, Temporal.DateTime createdAt, String plantID, String operatorID, String feedback) {
      this.id = id;
      this.createdAt = createdAt;
      this.plantID = plantID;
      this.operatorID = operatorID;
      this.feedback = feedback;
    }
    
    @Override
     public FeedbackEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new FeedbackEntry(
          id,
          createdAt,
          plantID,
          operatorID,
          feedback);
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
     public FeedbackStep operatorId(String operatorId) {
        Objects.requireNonNull(operatorId);
        this.operatorID = operatorId;
        return this;
    }
    
    @Override
     public BuildStep feedback(String feedback) {
        Objects.requireNonNull(feedback);
        this.feedback = feedback;
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
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, String plantId, String operatorId, String feedback) {
      super(id, createdAt, plantID, operatorID, feedback);
      Objects.requireNonNull(createdAt);
      Objects.requireNonNull(plantID);
      Objects.requireNonNull(operatorID);
      Objects.requireNonNull(feedback);
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
     public CopyOfBuilder feedback(String feedback) {
      return (CopyOfBuilder) super.feedback(feedback);
    }
  }
  

  public static class FeedbackEntryIdentifier extends ModelIdentifier<FeedbackEntry> {
    private static final long serialVersionUID = 1L;
    public FeedbackEntryIdentifier(String id) {
      super(id);
    }
  }
  
}