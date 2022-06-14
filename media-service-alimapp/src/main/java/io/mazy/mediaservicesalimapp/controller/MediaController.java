package io.mazy.mediaservicesalimapp.controller;

import io.mazy.mediaservicesalimapp.model.Media;
import io.mazy.mediaservicesalimapp.service.AppUserFeignService;
import io.mazy.mediaservicesalimapp.service.MediaService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {

    private MediaService mediaService;
    private AppUserFeignService appUserFeignService;

    @GetMapping("/{id}")
    public @ResponseBody Optional<Media> getMedia(@PathVariable Long id) {
        return mediaService.getMedia(id);
    }

    @GetMapping("/medias")
    public ResponseEntity<List<Media>> getListMedias() {
        List<Media> media = mediaService.getMedias(appUserFeignService.getMediaAppUserId());
        return ResponseEntity.ok().body(media);
    }

    @PostMapping("/uploadMedias")
    public String uploadMedia(@RequestParam("medias")MultipartFile[] medias) {
        for (MultipartFile media : medias) {
            mediaService.saveMedia(media);
        }
        return "redirect:/";
    }

    @GetMapping("/downloadMedia/{mediaId}")
    public ResponseEntity<ByteArrayResource> downloadMedia(@PathVariable Long mediaId){
        Media media = mediaService.getMedia(mediaId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(media.getMediaType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment=filename=\"" + media.getName() + "\"")
                .body(new ByteArrayResource(media.getData()));
    }

}
