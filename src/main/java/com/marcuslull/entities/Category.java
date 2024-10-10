package com.marcuslull.entities;

import jakarta.persistence.*;
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
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_gen")
    @SequenceGenerator(name = "categories_id_gen", sequenceName = "categories_category_id_seq", allocationSize = 1)
    // TODO: Remove all nullable = false in favor of @NotNull from Hibernate Validator
    // TODO: Define all @Column constraints
    @Column(name = "category_id", nullable = false)
    private Integer id;

    // TODO: Create enum for this and add @Enumerated(STRING)
    @NaturalId
    @Column(name = "category_name", nullable = false, length = 50)
    private String categoryName;

    @ManyToMany
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "categories_category_id"),
            inverseJoinColumns = @JoinColumn(name = "products_product_id"))
    private Set<Product> products = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private Set<Note> notes = new LinkedHashSet<>();

    public Category() {
    }

    public Category(String categoryName) {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
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