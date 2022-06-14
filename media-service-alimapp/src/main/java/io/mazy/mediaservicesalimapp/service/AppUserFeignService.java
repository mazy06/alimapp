package io.mazy.mediaservicesalimapp.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service-alimapp/AppUser")
public interface AppUserFeignService {

    Long getMediaAppUserId();

}
