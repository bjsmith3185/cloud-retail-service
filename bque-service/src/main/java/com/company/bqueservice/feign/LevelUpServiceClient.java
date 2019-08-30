package com.company.bqueservice.feign;

import com.company.bqueservice.message.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "level-up-service")
public interface LevelUpServiceClient {


    @RequestMapping(value = "/levelup", method = RequestMethod.POST)
    public LevelUp createLevelUp(@RequestBody LevelUp levelUp);




}

