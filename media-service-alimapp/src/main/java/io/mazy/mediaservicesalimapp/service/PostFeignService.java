package io.mazy.mediaservicesalimapp.service;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.Collection;

@FeignClient(name = "post-service-alimapp/Post")
public interface PostFeignService {

    Collection<Long> getMediaPostId();

}
