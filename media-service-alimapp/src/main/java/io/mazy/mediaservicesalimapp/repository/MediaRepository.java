package io.mazy.mediaservicesalimapp.repository;

import io.mazy.mediaservicesalimapp.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAllByAppUserId(Long id);


}
