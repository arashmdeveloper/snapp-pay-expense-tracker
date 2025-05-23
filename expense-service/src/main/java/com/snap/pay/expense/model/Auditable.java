package com.snap.pay.expense.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public abstract class Auditable {
    
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private long createdDate; // Unix timestamp in milliseconds
    
    @LastModifiedDate
    @Column(name = "modified_date")
    private long modifiedDate;
    
    // Optional: If you need user auditing
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    
    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;
}