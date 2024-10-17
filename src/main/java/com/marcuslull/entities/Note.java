package com.marcuslull.entities;

import com.marcuslull.entities.annotations.AtLeastOneNotNull;
import com.marcuslull.entities.interfaces.NotedEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Represents a note in the system.
 * This entity maps to the "notes" table in the database and is associated with a category, supplier, and product.
 * It extends BaseEntity to include common fields for tracking creation and update timestamps.
 */
@Entity
@Table(name = "notes")
@AtLeastOneNotNull
public class Note extends BaseEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notes_id_gen")
    @SequenceGenerator(name = "notes_id_gen", sequenceName = "notes_note_id_seq", allocationSize = 1)
    @Column(name = "note_id")
    private Integer id;

    @NotNull
    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public Note() {
    }

    public Note(String note) {}

    /**
     * Checks if the removal of a foreign key relationship related to a given entity class
     * will violate the entity's integrity constraints.
     *
     * @param entityClass the class object of the foreign key relationship to check against
     * @return true if removing the foreign key relationship will leave the entity in a state that
     *         violates its constraints, otherwise false
     */
    public boolean foreignKeyRemovalWillViolateConstraint(Class<? extends NotedEntity> entityClass) {
        return switch (entityClass.getSimpleName()) {
            case "Category" -> this.supplier == null && this.product == null;
            case "Supplier" -> this.category == null && this.product == null;
            case "Product" -> this.category == null && this.supplier == null;
            default -> false;
        };
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Note note = (Note) o;
        return getId() != null && Objects.equals(getId(), note.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", category=" + category +
                ", supplier=" + supplier +
                ", product=" + product +
                "} " + super.toString();
    }
}