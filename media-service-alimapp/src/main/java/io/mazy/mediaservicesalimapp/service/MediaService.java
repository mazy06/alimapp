package io.mazy.mediaservicesalimapp.service;

import io.mazy.mediaservicesalimapp.model.Media;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface MediaService {

    /**
     * create media uploaded
     * @param media media
     * @return media uploaded
     */
    Media saveMedia(MultipartFile media);

    /**
     * get specific media
     * @param mediaId id's media
     * @return get specific media
     */
    Optional<Media> getMedia(Long mediaId);

    /**
     * get all media's user
     * @param appUserId
     * @return
     */
    List<Media> getMedias(Long appUserId);

    /**
     * get all public medias
     * @return
     */
    List<Media> getMedias();

}
