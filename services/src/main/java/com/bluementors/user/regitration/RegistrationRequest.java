package com.bluementors.user.regitration;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name= "REGISTRATION_REQUESTS")
public class RegistrationRequest {
    @Id
    private UUID uuid;
    @CreationTimestamp
    private LocalDateTime creattionData;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RegistrationStatus status = RegistrationStatus.SENT;

    @Embedded
    private RegistrationForm registrationForm;

    protected RegistrationRequest(){
    }

    public RegistrationRequest(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreattionData() {
        return creattionData;
    }

    public void setCreattionData(LocalDateTime creattionData) {
        this.creattionData = creattionData;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }

    public void setRegistrationForm(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }
}
