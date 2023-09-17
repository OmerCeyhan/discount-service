package com.omerceyhan.discountservice.entity;

import org.springframework.data.annotation.Id;


public abstract class BaseEntity {
    protected BaseEntity(Long id) {
        this.id = id;
    }

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
