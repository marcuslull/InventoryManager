package com.marcuslull.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.NaturalId;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Product class represents an entity in the application's domain model, designed to map to the
 * "products" table in the database. It holds various attributes related to a product, including
 * SKU, name, quantity, threshold, description, and weight. Additionally, it manages the relationships
 * with categories, suppliers, prices, and notes.
 */
@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(name = "products_sku_key", columnNames = {"sku"})})
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id_gen")
    @SequenceGenerator(name = "products_id_gen", sequenceName = "products_product_id_seq", allocationSize = 1)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @NaturalId
    @Column(name = "sku", nullable = false, length = 20)
    private String sku;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @ColumnDefault("0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "threshold")
    private Integer threshold;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "weight", precision = 6, scale = 2)
    private BigDecimal weight;

    @ManyToMany(mappedBy = "products")
    private Set<Category> categories = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Supplier> suppliers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private Set<Price> prices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private Set<Note> notes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
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
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", threshold=" + threshold +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", num of categories=" + categories.size() +
                ", num of suppliers=" + suppliers.size() +
                ", num of prices=" + prices.size() +
                ", num of notes=" + notes.size() +
                "} " + super.toString();
    }
}