package io.mazy.mediaservicesalimapp.service;

import io.mazy.mediaservicesalimapp.exception.MyFileNotFoundException;
import io.mazy.mediaservicesalimapp.model.Media;
import io.mazy.mediaservicesalimapp.repository.MediaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService{

    private AppUserFeignService appUserFeignService;
    private MediaRepository mediaRepository;

    @Override
    public Media saveMedia(MultipartFile mediaFile) {
        LocalDateTime dateTime = LocalDateTime.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        String name = StringUtils.cleanPath(Objects.requireNonNull(mediaFile.getOriginalFilename()));
        try {
            Media media = new Media(name, mediaFile.getContentType(),mediaFile.getBytes(), dateTime, appUserFeignService.getMediaAppUserId());
            return mediaRepository.save(media);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Media> getMedia(Long mediaId) {
        return Optional.ofNullable(mediaRepository.findById(mediaId).orElseThrow(() -> new MyFileNotFoundException("File not found with id " + mediaId)));
    }

    @Override
    public List<Media> getMedias(Long appUserId) {
        return mediaRepository.findAllByAppUserId(appUserId).stream().toList();
    }

    @Override
    public List<Media> getMedias() {
        return mediaRepository.findAll();
    }

}
