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

/** This is an auto generated class representing the Entry type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Entries", type = Model.Type.USER, version = 1)
@Index(name = "undefined", fields = {"plantName","entryType","creationDate"})
@Index(name = "byOperator", fields = {"operatorName","creationDate"})
public final class Entry implements Model {
  public static final QueryField PLANT_NAME = field("Entry", "plantName");
  public static final QueryField ENTRY_TYPE = field("Entry", "entryType");
  public static final QueryField CREATION_DATE = field("Entry", "creationDate");
  public static final QueryField OPERATOR_NAME = field("Entry", "operatorName");
  public static final QueryField ADDITIONAL_NOTES = field("Entry", "additionalNotes");
  public static final QueryField INFLOW_RATE = field("Entry", "inflowRate");
  public static final QueryField TURBIDITY_READINGS = field("Entry", "turbidityReadings");
  public static final QueryField CHEMICAL_TYPE = field("Entry", "chemicalType");
  public static final QueryField SLIDER_POSITION = field("Entry", "sliderPosition");
  public static final QueryField START_VOLUME = field("Entry", "startVolume");
  public static final QueryField END_VOLUME = field("Entry", "endVolume");
  public static final QueryField TIME_ELAPSED = field("Entry", "timeElapsed");
  public static final QueryField CHEMICAL_DOSE = field("Entry", "chemicalDose");
  public static final QueryField CHEMICAL_FLOW_RATE = field("Entry", "chemicalFlowRate");
  public static final QueryField ACTIVE_TANK_VOLUME = field("Entry", "activeTankVolume");
  public static final QueryField TARGET_CHEMICAL_DOSE = field("Entry", "targetChemicalDose");
  public static final QueryField UPDATED_SLIDER_POSITION = field("Entry", "updatedSliderPosition");
  public static final QueryField UPDATED_CHEMICAL_FLOW_RATE = field("Entry", "updatedChemicalFlowRate");
  public static final QueryField OPERATOR_FEEDBACK = field("Entry", "operatorFeedback");
  private final @ModelField(targetType="String", isRequired = true) String plantName;
  private final @ModelField(targetType="EntryType", isRequired = true) EntryType entryType;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime creationDate;
  private final @ModelField(targetType="String", isRequired = true) String operatorName;
  private final @ModelField(targetType="String") String additionalNotes;
  private final @ModelField(targetType="Float") Double inflowRate;
  private final @ModelField(targetType="Float") List<Double> turbidityReadings;
  private final @ModelField(targetType="ChemType") ChemType chemicalType;
  private final @ModelField(targetType="Float") Double sliderPosition;
  private final @ModelField(targetType="Float") Double startVolume;
  private final @ModelField(targetType="Float") Double endVolume;
  private final @ModelField(targetType="Int") Integer timeElapsed;
  private final @ModelField(targetType="Float") Double chemicalDose;
  private final @ModelField(targetType="Float") Double chemicalFlowRate;
  private final @ModelField(targetType="Float") Double activeTankVolume;
  private final @ModelField(targetType="Float") Double targetChemicalDose;
  private final @ModelField(targetType="Float") Double updatedSliderPosition;
  private final @ModelField(targetType="Float") Double updatedChemicalFlowRate;
  private final @ModelField(targetType="String") String operatorFeedback;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private EntryIdentifier entryIdentifier;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public EntryIdentifier resolveIdentifier() {
    if (entryIdentifier == null) {
      this.entryIdentifier = new EntryIdentifier(plantName, entryType, creationDate);
    }
    return entryIdentifier;
  }
  
  public String getPlantName() {
      return plantName;
  }
  
  public EntryType getEntryType() {
      return entryType;
  }
  
  public Temporal.DateTime getCreationDate() {
      return creationDate;
  }
  
  public String getOperatorName() {
      return operatorName;
  }
  
  public String getAdditionalNotes() {
      return additionalNotes;
  }
  
  public Double getInflowRate() {
      return inflowRate;
  }
  
  public List<Double> getTurbidityReadings() {
      return turbidityReadings;
  }
  
  public ChemType getChemicalType() {
      return chemicalType;
  }
  
  public Double getSliderPosition() {
      return sliderPosition;
  }
  
  public Double getStartVolume() {
      return startVolume;
  }
  
  public Double getEndVolume() {
      return endVolume;
  }
  
  public Integer getTimeElapsed() {
      return timeElapsed;
  }
  
  public Double getChemicalDose() {
      return chemicalDose;
  }
  
  public Double getChemicalFlowRate() {
      return chemicalFlowRate;
  }
  
  public Double getActiveTankVolume() {
      return activeTankVolume;
  }
  
  public Double getTargetChemicalDose() {
      return targetChemicalDose;
  }
  
  public Double getUpdatedSliderPosition() {
      return updatedSliderPosition;
  }
  
  public Double getUpdatedChemicalFlowRate() {
      return updatedChemicalFlowRate;
  }
  
  public String getOperatorFeedback() {
      return operatorFeedback;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Entry(String plantName, EntryType entryType, Temporal.DateTime creationDate, String operatorName, String additionalNotes, Double inflowRate, List<Double> turbidityReadings, ChemType chemicalType, Double sliderPosition, Double startVolume, Double endVolume, Integer timeElapsed, Double chemicalDose, Double chemicalFlowRate, Double activeTankVolume, Double targetChemicalDose, Double updatedSliderPosition, Double updatedChemicalFlowRate, String operatorFeedback) {
    this.plantName = plantName;
    this.entryType = entryType;
    this.creationDate = creationDate;
    this.operatorName = operatorName;
    this.additionalNotes = additionalNotes;
    this.inflowRate = inflowRate;
    this.turbidityReadings = turbidityReadings;
    this.chemicalType = chemicalType;
    this.sliderPosition = sliderPosition;
    this.startVolume = startVolume;
    this.endVolume = endVolume;
    this.timeElapsed = timeElapsed;
    this.chemicalDose = chemicalDose;
    this.chemicalFlowRate = chemicalFlowRate;
    this.activeTankVolume = activeTankVolume;
    this.targetChemicalDose = targetChemicalDose;
    this.updatedSliderPosition = updatedSliderPosition;
    this.updatedChemicalFlowRate = updatedChemicalFlowRate;
    this.operatorFeedback = operatorFeedback;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Entry entry = (Entry) obj;
      return ObjectsCompat.equals(getPlantName(), entry.getPlantName()) &&
              ObjectsCompat.equals(getEntryType(), entry.getEntryType()) &&
              ObjectsCompat.equals(getCreationDate(), entry.getCreationDate()) &&
              ObjectsCompat.equals(getOperatorName(), entry.getOperatorName()) &&
              ObjectsCompat.equals(getAdditionalNotes(), entry.getAdditionalNotes()) &&
              ObjectsCompat.equals(getInflowRate(), entry.getInflowRate()) &&
              ObjectsCompat.equals(getTurbidityReadings(), entry.getTurbidityReadings()) &&
              ObjectsCompat.equals(getChemicalType(), entry.getChemicalType()) &&
              ObjectsCompat.equals(getSliderPosition(), entry.getSliderPosition()) &&
              ObjectsCompat.equals(getStartVolume(), entry.getStartVolume()) &&
              ObjectsCompat.equals(getEndVolume(), entry.getEndVolume()) &&
              ObjectsCompat.equals(getTimeElapsed(), entry.getTimeElapsed()) &&
              ObjectsCompat.equals(getChemicalDose(), entry.getChemicalDose()) &&
              ObjectsCompat.equals(getChemicalFlowRate(), entry.getChemicalFlowRate()) &&
              ObjectsCompat.equals(getActiveTankVolume(), entry.getActiveTankVolume()) &&
              ObjectsCompat.equals(getTargetChemicalDose(), entry.getTargetChemicalDose()) &&
              ObjectsCompat.equals(getUpdatedSliderPosition(), entry.getUpdatedSliderPosition()) &&
              ObjectsCompat.equals(getUpdatedChemicalFlowRate(), entry.getUpdatedChemicalFlowRate()) &&
              ObjectsCompat.equals(getOperatorFeedback(), entry.getOperatorFeedback()) &&
              ObjectsCompat.equals(getCreatedAt(), entry.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), entry.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getPlantName())
      .append(getEntryType())
      .append(getCreationDate())
      .append(getOperatorName())
      .append(getAdditionalNotes())
      .append(getInflowRate())
      .append(getTurbidityReadings())
      .append(getChemicalType())
      .append(getSliderPosition())
      .append(getStartVolume())
      .append(getEndVolume())
      .append(getTimeElapsed())
      .append(getChemicalDose())
      .append(getChemicalFlowRate())
      .append(getActiveTankVolume())
      .append(getTargetChemicalDose())
      .append(getUpdatedSliderPosition())
      .append(getUpdatedChemicalFlowRate())
      .append(getOperatorFeedback())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Entry {")
      .append("plantName=" + String.valueOf(getPlantName()) + ", ")
      .append("entryType=" + String.valueOf(getEntryType()) + ", ")
      .append("creationDate=" + String.valueOf(getCreationDate()) + ", ")
      .append("operatorName=" + String.valueOf(getOperatorName()) + ", ")
      .append("additionalNotes=" + String.valueOf(getAdditionalNotes()) + ", ")
      .append("inflowRate=" + String.valueOf(getInflowRate()) + ", ")
      .append("turbidityReadings=" + String.valueOf(getTurbidityReadings()) + ", ")
      .append("chemicalType=" + String.valueOf(getChemicalType()) + ", ")
      .append("sliderPosition=" + String.valueOf(getSliderPosition()) + ", ")
      .append("startVolume=" + String.valueOf(getStartVolume()) + ", ")
      .append("endVolume=" + String.valueOf(getEndVolume()) + ", ")
      .append("timeElapsed=" + String.valueOf(getTimeElapsed()) + ", ")
      .append("chemicalDose=" + String.valueOf(getChemicalDose()) + ", ")
      .append("chemicalFlowRate=" + String.valueOf(getChemicalFlowRate()) + ", ")
      .append("activeTankVolume=" + String.valueOf(getActiveTankVolume()) + ", ")
      .append("targetChemicalDose=" + String.valueOf(getTargetChemicalDose()) + ", ")
      .append("updatedSliderPosition=" + String.valueOf(getUpdatedSliderPosition()) + ", ")
      .append("updatedChemicalFlowRate=" + String.valueOf(getUpdatedChemicalFlowRate()) + ", ")
      .append("operatorFeedback=" + String.valueOf(getOperatorFeedback()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static PlantNameStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(plantName,
      entryType,
      creationDate,
      operatorName,
      additionalNotes,
      inflowRate,
      turbidityReadings,
      chemicalType,
      sliderPosition,
      startVolume,
      endVolume,
      timeElapsed,
      chemicalDose,
      chemicalFlowRate,
      activeTankVolume,
      targetChemicalDose,
      updatedSliderPosition,
      updatedChemicalFlowRate,
      operatorFeedback);
  }
  public interface PlantNameStep {
    EntryTypeStep plantName(String plantName);
  }
  

  public interface EntryTypeStep {
    CreationDateStep entryType(EntryType entryType);
  }
  

  public interface CreationDateStep {
    OperatorNameStep creationDate(Temporal.DateTime creationDate);
  }
  

  public interface OperatorNameStep {
    BuildStep operatorName(String operatorName);
  }
  

  public interface BuildStep {
    Entry build();
    BuildStep additionalNotes(String additionalNotes);
    BuildStep inflowRate(Double inflowRate);
    BuildStep turbidityReadings(List<Double> turbidityReadings);
    BuildStep chemicalType(ChemType chemicalType);
    BuildStep sliderPosition(Double sliderPosition);
    BuildStep startVolume(Double startVolume);
    BuildStep endVolume(Double endVolume);
    BuildStep timeElapsed(Integer timeElapsed);
    BuildStep chemicalDose(Double chemicalDose);
    BuildStep chemicalFlowRate(Double chemicalFlowRate);
    BuildStep activeTankVolume(Double activeTankVolume);
    BuildStep targetChemicalDose(Double targetChemicalDose);
    BuildStep updatedSliderPosition(Double updatedSliderPosition);
    BuildStep updatedChemicalFlowRate(Double updatedChemicalFlowRate);
    BuildStep operatorFeedback(String operatorFeedback);
  }
  

  public static class Builder implements PlantNameStep, EntryTypeStep, CreationDateStep, OperatorNameStep, BuildStep {
    private String plantName;
    private EntryType entryType;
    private Temporal.DateTime creationDate;
    private String operatorName;
    private String additionalNotes;
    private Double inflowRate;
    private List<Double> turbidityReadings;
    private ChemType chemicalType;
    private Double sliderPosition;
    private Double startVolume;
    private Double endVolume;
    private Integer timeElapsed;
    private Double chemicalDose;
    private Double chemicalFlowRate;
    private Double activeTankVolume;
    private Double targetChemicalDose;
    private Double updatedSliderPosition;
    private Double updatedChemicalFlowRate;
    private String operatorFeedback;
    public Builder() {
      
    }
    
    private Builder(String plantName, EntryType entryType, Temporal.DateTime creationDate, String operatorName, String additionalNotes, Double inflowRate, List<Double> turbidityReadings, ChemType chemicalType, Double sliderPosition, Double startVolume, Double endVolume, Integer timeElapsed, Double chemicalDose, Double chemicalFlowRate, Double activeTankVolume, Double targetChemicalDose, Double updatedSliderPosition, Double updatedChemicalFlowRate, String operatorFeedback) {
      this.plantName = plantName;
      this.entryType = entryType;
      this.creationDate = creationDate;
      this.operatorName = operatorName;
      this.additionalNotes = additionalNotes;
      this.inflowRate = inflowRate;
      this.turbidityReadings = turbidityReadings;
      this.chemicalType = chemicalType;
      this.sliderPosition = sliderPosition;
      this.startVolume = startVolume;
      this.endVolume = endVolume;
      this.timeElapsed = timeElapsed;
      this.chemicalDose = chemicalDose;
      this.chemicalFlowRate = chemicalFlowRate;
      this.activeTankVolume = activeTankVolume;
      this.targetChemicalDose = targetChemicalDose;
      this.updatedSliderPosition = updatedSliderPosition;
      this.updatedChemicalFlowRate = updatedChemicalFlowRate;
      this.operatorFeedback = operatorFeedback;
    }
    
    @Override
     public Entry build() {
        
        return new Entry(
          plantName,
          entryType,
          creationDate,
          operatorName,
          additionalNotes,
          inflowRate,
          turbidityReadings,
          chemicalType,
          sliderPosition,
          startVolume,
          endVolume,
          timeElapsed,
          chemicalDose,
          chemicalFlowRate,
          activeTankVolume,
          targetChemicalDose,
          updatedSliderPosition,
          updatedChemicalFlowRate,
          operatorFeedback);
    }
    
    @Override
     public EntryTypeStep plantName(String plantName) {
        Objects.requireNonNull(plantName);
        this.plantName = plantName;
        return this;
    }
    
    @Override
     public CreationDateStep entryType(EntryType entryType) {
        Objects.requireNonNull(entryType);
        this.entryType = entryType;
        return this;
    }
    
    @Override
     public OperatorNameStep creationDate(Temporal.DateTime creationDate) {
        Objects.requireNonNull(creationDate);
        this.creationDate = creationDate;
        return this;
    }
    
    @Override
     public BuildStep operatorName(String operatorName) {
        Objects.requireNonNull(operatorName);
        this.operatorName = operatorName;
        return this;
    }
    
    @Override
     public BuildStep additionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
        return this;
    }
    
    @Override
     public BuildStep inflowRate(Double inflowRate) {
        this.inflowRate = inflowRate;
        return this;
    }
    
    @Override
     public BuildStep turbidityReadings(List<Double> turbidityReadings) {
        this.turbidityReadings = turbidityReadings;
        return this;
    }
    
    @Override
     public BuildStep chemicalType(ChemType chemicalType) {
        this.chemicalType = chemicalType;
        return this;
    }
    
    @Override
     public BuildStep sliderPosition(Double sliderPosition) {
        this.sliderPosition = sliderPosition;
        return this;
    }
    
    @Override
     public BuildStep startVolume(Double startVolume) {
        this.startVolume = startVolume;
        return this;
    }
    
    @Override
     public BuildStep endVolume(Double endVolume) {
        this.endVolume = endVolume;
        return this;
    }
    
    @Override
     public BuildStep timeElapsed(Integer timeElapsed) {
        this.timeElapsed = timeElapsed;
        return this;
    }
    
    @Override
     public BuildStep chemicalDose(Double chemicalDose) {
        this.chemicalDose = chemicalDose;
        return this;
    }
    
    @Override
     public BuildStep chemicalFlowRate(Double chemicalFlowRate) {
        this.chemicalFlowRate = chemicalFlowRate;
        return this;
    }
    
    @Override
     public BuildStep activeTankVolume(Double activeTankVolume) {
        this.activeTankVolume = activeTankVolume;
        return this;
    }
    
    @Override
     public BuildStep targetChemicalDose(Double targetChemicalDose) {
        this.targetChemicalDose = targetChemicalDose;
        return this;
    }
    
    @Override
     public BuildStep updatedSliderPosition(Double updatedSliderPosition) {
        this.updatedSliderPosition = updatedSliderPosition;
        return this;
    }
    
    @Override
     public BuildStep updatedChemicalFlowRate(Double updatedChemicalFlowRate) {
        this.updatedChemicalFlowRate = updatedChemicalFlowRate;
        return this;
    }
    
    @Override
     public BuildStep operatorFeedback(String operatorFeedback) {
        this.operatorFeedback = operatorFeedback;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String plantName, EntryType entryType, Temporal.DateTime creationDate, String operatorName, String additionalNotes, Double inflowRate, List<Double> turbidityReadings, ChemType chemicalType, Double sliderPosition, Double startVolume, Double endVolume, Integer timeElapsed, Double chemicalDose, Double chemicalFlowRate, Double activeTankVolume, Double targetChemicalDose, Double updatedSliderPosition, Double updatedChemicalFlowRate, String operatorFeedback) {
      super(plantName, entryType, creationDate, operatorName, additionalNotes, inflowRate, turbidityReadings, chemicalType, sliderPosition, startVolume, endVolume, timeElapsed, chemicalDose, chemicalFlowRate, activeTankVolume, targetChemicalDose, updatedSliderPosition, updatedChemicalFlowRate, operatorFeedback);
      Objects.requireNonNull(plantName);
      Objects.requireNonNull(entryType);
      Objects.requireNonNull(creationDate);
      Objects.requireNonNull(operatorName);
    }
    
    @Override
     public CopyOfBuilder plantName(String plantName) {
      return (CopyOfBuilder) super.plantName(plantName);
    }
    
    @Override
     public CopyOfBuilder entryType(EntryType entryType) {
      return (CopyOfBuilder) super.entryType(entryType);
    }
    
    @Override
     public CopyOfBuilder creationDate(Temporal.DateTime creationDate) {
      return (CopyOfBuilder) super.creationDate(creationDate);
    }
    
    @Override
     public CopyOfBuilder operatorName(String operatorName) {
      return (CopyOfBuilder) super.operatorName(operatorName);
    }
    
    @Override
     public CopyOfBuilder additionalNotes(String additionalNotes) {
      return (CopyOfBuilder) super.additionalNotes(additionalNotes);
    }
    
    @Override
     public CopyOfBuilder inflowRate(Double inflowRate) {
      return (CopyOfBuilder) super.inflowRate(inflowRate);
    }
    
    @Override
     public CopyOfBuilder turbidityReadings(List<Double> turbidityReadings) {
      return (CopyOfBuilder) super.turbidityReadings(turbidityReadings);
    }
    
    @Override
     public CopyOfBuilder chemicalType(ChemType chemicalType) {
      return (CopyOfBuilder) super.chemicalType(chemicalType);
    }
    
    @Override
     public CopyOfBuilder sliderPosition(Double sliderPosition) {
      return (CopyOfBuilder) super.sliderPosition(sliderPosition);
    }
    
    @Override
     public CopyOfBuilder startVolume(Double startVolume) {
      return (CopyOfBuilder) super.startVolume(startVolume);
    }
    
    @Override
     public CopyOfBuilder endVolume(Double endVolume) {
      return (CopyOfBuilder) super.endVolume(endVolume);
    }
    
    @Override
     public CopyOfBuilder timeElapsed(Integer timeElapsed) {
      return (CopyOfBuilder) super.timeElapsed(timeElapsed);
    }
    
    @Override
     public CopyOfBuilder chemicalDose(Double chemicalDose) {
      return (CopyOfBuilder) super.chemicalDose(chemicalDose);
    }
    
    @Override
     public CopyOfBuilder chemicalFlowRate(Double chemicalFlowRate) {
      return (CopyOfBuilder) super.chemicalFlowRate(chemicalFlowRate);
    }
    
    @Override
     public CopyOfBuilder activeTankVolume(Double activeTankVolume) {
      return (CopyOfBuilder) super.activeTankVolume(activeTankVolume);
    }
    
    @Override
     public CopyOfBuilder targetChemicalDose(Double targetChemicalDose) {
      return (CopyOfBuilder) super.targetChemicalDose(targetChemicalDose);
    }
    
    @Override
     public CopyOfBuilder updatedSliderPosition(Double updatedSliderPosition) {
      return (CopyOfBuilder) super.updatedSliderPosition(updatedSliderPosition);
    }
    
    @Override
     public CopyOfBuilder updatedChemicalFlowRate(Double updatedChemicalFlowRate) {
      return (CopyOfBuilder) super.updatedChemicalFlowRate(updatedChemicalFlowRate);
    }
    
    @Override
     public CopyOfBuilder operatorFeedback(String operatorFeedback) {
      return (CopyOfBuilder) super.operatorFeedback(operatorFeedback);
    }
  }
  

  public static class EntryIdentifier extends ModelIdentifier<Entry> {
    private static final long serialVersionUID = 1L;
    public EntryIdentifier(String plantName, EntryType entryType, Temporal.DateTime creationDate) {
      super(plantName, entryType, creationDate);
    }
  }
  
}
