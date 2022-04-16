package io.mazy.postsservicesalimapp.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service-alimapp/AppUser")
public interface AppUserFeignService {
    @GetMapping("/exist/{id}")
    Boolean appUserExist(@PathVariable Long id);
}
