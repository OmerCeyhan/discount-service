package com.omerceyhan.discountservice.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_status")
public class UserStatus extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "user_status_sequence";

    public UserStatus(Long id, StatusType statusType) {
        super(id);
        this.statusType = statusType;
    }

    private StatusType statusType;

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }
}
