package com.company.adminservice.feign;

import com.company.adminservice.dto.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "level-up-service")
public interface LevelUpServiceClient {

    @RequestMapping(value = "/levelup", method = RequestMethod.POST)
    public LevelUp createLevelUp(@RequestBody LevelUp levelUp);

    @RequestMapping(value = "/levelup", method = RequestMethod.GET)
    public List<LevelUp> getAllLevelUps();

    @RequestMapping(value = "/levelup/id/{id}", method = RequestMethod.GET)
    public LevelUp getLevelUpById(@PathVariable int id);

    @RequestMapping(value = "/levelup/id/{id}", method = RequestMethod.PUT)
    public void updateLevelUp(@PathVariable int id, @RequestBody LevelUp levelUp);

    @RequestMapping(value = "/levelup/id/{id}", method = RequestMethod.DELETE)
    public void deleteLevelUp(@PathVariable int id);


    @RequestMapping(value = "/levelup/customerid/{customerid}", method = RequestMethod.GET)
    public LevelUp getLevelUpByCustomerId(@PathVariable int customerid);

}
