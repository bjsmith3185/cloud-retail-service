package com.company.bqueservice.feign;

import com.company.bqueservice.message.LevelUp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "level-up-service")
public interface LevelUpServiceClient {


    @RequestMapping(value = "/levelup", method = RequestMethod.POST)
    public LevelUp createLevelUp(LevelUp levelUp);




}

