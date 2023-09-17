package com.omerceyhan.discountservice.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;


@Document(collection = "users")
public class User extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    public User(Long id, String fullName, Collection<UserStatus> userStatuses) {
        super(id);
        this.fullName = fullName;
        this.userStatuses = userStatuses;
    }

    private String fullName;

    @DBRef
    private Collection<UserStatus> userStatuses;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Collection<UserStatus> getUserStatuses() {
        return userStatuses;
    }

    public void setUserStatuses(Collection<UserStatus> userStatuses) {
        this.userStatuses = userStatuses;
    }
}
