package com.marcuslull.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;


/**
 * BaseEntity is an abstract base class that provides common properties for
 * entities such as creation time, update time, deletion status, and version control.
 * It is designed to be extended by other entity classes to inherit these properties.
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

    @Version
    @Column(name = "version")
    @ColumnDefault("0")
    protected Integer version;

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isDeleted=" + isDeleted +
                ", version=" + version +
                '}';
    }
}
