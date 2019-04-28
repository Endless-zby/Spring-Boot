package com.zby.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("MavenProject-Friend")
public interface friendClient {


}
