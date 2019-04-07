package com.bluementors.bluemedia;

import com.bluementors.user.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
