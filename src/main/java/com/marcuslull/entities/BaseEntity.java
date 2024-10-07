package com.marcuslull.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

/**
 * BaseEntity serves as a base class for all entities in the application, providing common
 * fields and methods for tracking creation and update timestamps, as well as a logical
 * deletion flag.
 */
@MappedSuperclass()
public abstract class BaseEntity {
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    protected Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    protected Instant updatedAt;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    protected Boolean isDeleted;

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
