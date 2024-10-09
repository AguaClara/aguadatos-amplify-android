package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the PlantFlowEntry type in your schema. */
public final class PlantFlowEntry {
  private final String id;
  private final String name;
  private final String entryType;
  private final Double inflowRate;
  private final String notes;
  private final String createTime;
  private final String createDate;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getEntryType() {
      return entryType;
  }
  
  public Double getInflowRate() {
      return inflowRate;
  }
  
  public String getNotes() {
      return notes;
  }
  
  public String getCreateTime() {
      return createTime;
  }
  
  public String getCreateDate() {
      return createDate;
  }
  
  private PlantFlowEntry(String id, String name, String entryType, Double inflowRate, String notes, String createTime, String createDate) {
    this.id = id;
    this.name = name;
    this.entryType = entryType;
    this.inflowRate = inflowRate;
    this.notes = notes;
    this.createTime = createTime;
    this.createDate = createDate;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      PlantFlowEntry plantFlowEntry = (PlantFlowEntry) obj;
      return ObjectsCompat.equals(getId(), plantFlowEntry.getId()) &&
              ObjectsCompat.equals(getName(), plantFlowEntry.getName()) &&
              ObjectsCompat.equals(getEntryType(), plantFlowEntry.getEntryType()) &&
              ObjectsCompat.equals(getInflowRate(), plantFlowEntry.getInflowRate()) &&
              ObjectsCompat.equals(getNotes(), plantFlowEntry.getNotes()) &&
              ObjectsCompat.equals(getCreateTime(), plantFlowEntry.getCreateTime()) &&
              ObjectsCompat.equals(getCreateDate(), plantFlowEntry.getCreateDate());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getEntryType())
      .append(getInflowRate())
      .append(getNotes())
      .append(getCreateTime())
      .append(getCreateDate())
      .toString()
      .hashCode();
  }
  
  public static IdStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      entryType,
      inflowRate,
      notes,
      createTime,
      createDate);
  }
  public interface IdStep {
    NameStep id(String id);
  }
  

  public interface NameStep {
    EntryTypeStep name(String name);
  }
  

  public interface EntryTypeStep {
    InflowRateStep entryType(String entryType);
  }
  

  public interface InflowRateStep {
    CreateTimeStep inflowRate(Double inflowRate);
  }
  

  public interface CreateTimeStep {
    CreateDateStep createTime(String createTime);
  }
  

  public interface CreateDateStep {
    BuildStep createDate(String createDate);
  }
  

  public interface BuildStep {
    PlantFlowEntry build();
    BuildStep notes(String notes);
  }
  

  public static class Builder implements IdStep, NameStep, EntryTypeStep, InflowRateStep, CreateTimeStep, CreateDateStep, BuildStep {
    private String id;
    private String name;
    private String entryType;
    private Double inflowRate;
    private String createTime;
    private String createDate;
    private String notes;
    public Builder() {
      
    }
    
    private Builder(String id, String name, String entryType, Double inflowRate, String notes, String createTime, String createDate) {
      this.id = id;
      this.name = name;
      this.entryType = entryType;
      this.inflowRate = inflowRate;
      this.notes = notes;
      this.createTime = createTime;
      this.createDate = createDate;
    }
    
    @Override
     public PlantFlowEntry build() {
        
        return new PlantFlowEntry(
          id,
          name,
          entryType,
          inflowRate,
          notes,
          createTime,
          createDate);
    }
    
    @Override
     public NameStep id(String id) {
        Objects.requireNonNull(id);
        this.id = id;
        return this;
    }
    
    @Override
     public EntryTypeStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public InflowRateStep entryType(String entryType) {
        Objects.requireNonNull(entryType);
        this.entryType = entryType;
        return this;
    }
    
    @Override
     public CreateTimeStep inflowRate(Double inflowRate) {
        Objects.requireNonNull(inflowRate);
        this.inflowRate = inflowRate;
        return this;
    }
    
    @Override
     public CreateDateStep createTime(String createTime) {
        Objects.requireNonNull(createTime);
        this.createTime = createTime;
        return this;
    }
    
    @Override
     public BuildStep createDate(String createDate) {
        Objects.requireNonNull(createDate);
        this.createDate = createDate;
        return this;
    }
    
    @Override
     public BuildStep notes(String notes) {
        this.notes = notes;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String entryType, Double inflowRate, String notes, String createTime, String createDate) {
      super(id, name, entryType, inflowRate, notes, createTime, createDate);
      Objects.requireNonNull(id);
      Objects.requireNonNull(name);
      Objects.requireNonNull(entryType);
      Objects.requireNonNull(inflowRate);
      Objects.requireNonNull(createTime);
      Objects.requireNonNull(createDate);
    }
    
    @Override
     public CopyOfBuilder id(String id) {
      return (CopyOfBuilder) super.id(id);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder entryType(String entryType) {
      return (CopyOfBuilder) super.entryType(entryType);
    }
    
    @Override
     public CopyOfBuilder inflowRate(Double inflowRate) {
      return (CopyOfBuilder) super.inflowRate(inflowRate);
    }
    
    @Override
     public CopyOfBuilder createTime(String createTime) {
      return (CopyOfBuilder) super.createTime(createTime);
    }
    
    @Override
     public CopyOfBuilder createDate(String createDate) {
      return (CopyOfBuilder) super.createDate(createDate);
    }
    
    @Override
     public CopyOfBuilder notes(String notes) {
      return (CopyOfBuilder) super.notes(notes);
    }
  }
  
}
