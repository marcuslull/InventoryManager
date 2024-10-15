package com.marcuslull.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Supplier represents a supplier entity with details such as
 * supplier name, website, representative, and phone number.
 * It extends BaseEntity and is mapped to the "suppliers" table in the public schema.
 */
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suppliers_id_gen")
    @SequenceGenerator(name = "suppliers_id_gen", sequenceName = "suppliers_supplier_id_seq", allocationSize = 1)
    @Column(name = "supplier_id")
    private Integer id;

    @NaturalId
    @Size(max = 50)
    @NotNull
    @Column(name = "supplier_name")
    private String supplierName;

    @Size(max = 200)
    @NotNull
    @Column(name = "website")
    private String website;

    @Size(max = 50)
    @Column(name = "representative")
    private String representative;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone")
    private String phone;

    @ManyToMany
    @JoinTable(name = "products_suppliers",
            joinColumns = @JoinColumn(name = "suppliers_supplier_id", table = "suppliers"),
            inverseJoinColumns = @JoinColumn(name = "products_product_id", table = "products"))
    private Set<Product> products = new LinkedHashSet<>();

    @OneToMany(mappedBy = "supplier", orphanRemoval = true)
    private Set<Price> prices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "supplier", orphanRemoval = true)
    private Set<Note> notes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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
        Supplier supplier = (Supplier) o;
        return getId() != null && Objects.equals(getId(), supplier.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", supplierName='" + supplierName + '\'' +
                ", website='" + website + '\'' +
                ", representative='" + representative + '\'' +
                ", phone='" + phone + '\'' +
                ", number of products=" + products.size() +
                ", number of prices=" + prices.size() +
                ", number of notes=" + notes.size() +
                "} " + super.toString();
    }
}