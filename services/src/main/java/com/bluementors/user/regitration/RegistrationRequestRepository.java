package com.bluementors.user.regitration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, UUID> {
}
