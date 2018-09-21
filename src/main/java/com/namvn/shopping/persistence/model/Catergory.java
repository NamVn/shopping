package com.namvn.shopping.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "catergory")
public class Catergory {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private String image;
    private int stattus;

    public Catergory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStattus() {
        return stattus;
    }

    public void setStattus(int stattus) {
        this.stattus = stattus;
    }
}
