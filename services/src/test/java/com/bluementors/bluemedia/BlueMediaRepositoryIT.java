package com.bluementors.bluemedia;

import com.bleumedia.MediaType;
import com.bluementors.IntegrationTest;
import com.bluementors.user.Media;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Transactional
public class BlueMediaRepositoryIT extends IntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MediaRepository mediaRepository;

    @Test
    public void test_blob_savings(){
        Media testMedia = new Media();
        testMedia.setId(1L);
        testMedia.setMediaType(MediaType.image);
        testMedia.setData("1234".getBytes());

        mediaRepository.save(testMedia);
        entityManager.flush();

        List<Media> media = mediaRepository.findAll();

        assertThat(media)
                .isNotNull()
                .isNotEmpty();

        Media savedMedia = media.get(0);

        assertThat(savedMedia)
                .extracting(Media::getData)
                .isEqualTo("1234".getBytes());


    }
}
