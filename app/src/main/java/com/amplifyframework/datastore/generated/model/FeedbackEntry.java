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

/** This is an auto generated class representing the FeedbackEntry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FeedbackEntries", type = Model.Type.USER, version = 1)
@Index(name = "FeedbackByPlant", fields = {"plantID","createdAt"})
@Index(name = "FeedbackByOperator", fields = {"operatorID","createdAt"})
public final class FeedbackEntry implements Model {
  public static final QueryField ID = field("FeedbackEntry", "id");
  public static final QueryField CREATED_AT = field("FeedbackEntry", "createdAt");
  public static final QueryField FEEDBACK = field("FeedbackEntry", "feedback");
  public static final QueryField PLANT = field("FeedbackEntry", "plantID");
  public static final QueryField OPERATOR = field("FeedbackEntry", "operatorID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime createdAt;
  private final @ModelField(targetType="String", isRequired = true) String feedback;
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
  
  public String getFeedback() {
      return feedback;
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
  
  private FeedbackEntry(String id, Temporal.DateTime createdAt, String feedback, Plant plant, Operator operator) {
    this.id = id;
    this.createdAt = createdAt;
    this.feedback = feedback;
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
      FeedbackEntry feedbackEntry = (FeedbackEntry) obj;
      return ObjectsCompat.equals(getId(), feedbackEntry.getId()) &&
              ObjectsCompat.equals(getCreatedAt(), feedbackEntry.getCreatedAt()) &&
              ObjectsCompat.equals(getFeedback(), feedbackEntry.getFeedback()) &&
              ObjectsCompat.equals(getPlant(), feedbackEntry.getPlant()) &&
              ObjectsCompat.equals(getOperator(), feedbackEntry.getOperator()) &&
              ObjectsCompat.equals(getUpdatedAt(), feedbackEntry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCreatedAt())
      .append(getFeedback())
      .append(getPlant())
      .append(getOperator())
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
      .append("feedback=" + String.valueOf(getFeedback()) + ", ")
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
      feedback,
      plant,
      operator);
  }
  public interface CreatedAtStep {
    FeedbackStep createdAt(Temporal.DateTime createdAt);
  }
  

  public interface FeedbackStep {
    BuildStep feedback(String feedback);
  }
  

  public interface BuildStep {
    FeedbackEntry build();
    BuildStep id(String id);
    BuildStep plant(Plant plant);
    BuildStep operator(Operator operator);
  }
  

  public static class Builder implements CreatedAtStep, FeedbackStep, BuildStep {
    private String id;
    private Temporal.DateTime createdAt;
    private String feedback;
    private Plant plant;
    private Operator operator;
    @Override
     public FeedbackEntry build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new FeedbackEntry(
          id,
          createdAt,
          feedback,
          plant,
          operator);
    }
    
    @Override
     public FeedbackStep createdAt(Temporal.DateTime createdAt) {
        Objects.requireNonNull(createdAt);
        this.createdAt = createdAt;
        return this;
    }
    
    @Override
     public BuildStep feedback(String feedback) {
        Objects.requireNonNull(feedback);
        this.feedback = feedback;
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
    private CopyOfBuilder(String id, Temporal.DateTime createdAt, String feedback, Plant plant, Operator operator) {
      super.id(id);
      super.createdAt(createdAt)
        .feedback(feedback)
        .plant(plant)
        .operator(operator);
    }
    
    @Override
     public CopyOfBuilder createdAt(Temporal.DateTime createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
    }
    
    @Override
     public CopyOfBuilder feedback(String feedback) {
      return (CopyOfBuilder) super.feedback(feedback);
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