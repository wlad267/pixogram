package com.bluementors.bluemedia;

import com.bluementors.user.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    //List<Media> findByUserId(Long userId);
}
