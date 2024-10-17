package com.marcuslull.entities;

import com.marcuslull.entities.enumerations.Categories;
import com.marcuslull.entities.interfaces.NotedEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a category in the system.
 * This entity maps to the "categories" table in the database and includes details
 * such as the category name, associated products, and associated notes.
 */
@Entity
// TODO: Declare indexing in the @Table annotation
@Table(name = "categories")
public class Category extends BaseEntity implements NotedEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_gen")
    @SequenceGenerator(name = "categories_id_gen", sequenceName = "categories_category_id_seq", allocationSize = 1)
    @Column(name = "category_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @NotNull
    @Size(max = 50)
    @Column(name = "category_name")
    private Categories categoryName;

    @ManyToMany
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "categories_category_id", table = "categories"),
            inverseJoinColumns = @JoinColumn(name = "products_product_id", table = "products"))
    private Set<@NotNull Product> products = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private Set<@NotNull Note> notes = new LinkedHashSet<>();

    public Category() {
    }

    public Category(String categoryName) {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categories getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Categories categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Category category = (Category) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", number of products=" + products.size() +
                ", number of notes=" + notes.size() +
                "} " + super.toString();
    }
}