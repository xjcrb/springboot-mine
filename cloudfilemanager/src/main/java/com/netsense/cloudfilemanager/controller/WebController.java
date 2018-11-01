package com.netsense.cloudfilemanager.controller;

import com.netsense.cloudfilemanager.model.*;
import com.netsense.cloudfilemanager.service.WebService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@RestController
public class WebController {

    @Resource
    private WebService webService;

    @PostMapping(value = "/delete")
    public Result delete(@RequestBody Map<String,Object> params) throws Exception {
        return webService.delete(params);
    }

    @PostMapping("/search")
    public Result search(@RequestBody Map<String,Object> params) throws Exception {
        return webService.search(params);
    }
    @PostMapping("/getcheduletimer")
    public Result getcheduletimer(@RequestBody(required = false) Map<String,Object> params) throws Exception {
        return webService.getcheduletimer(params);
    }
    @PostMapping("/updatescheduletime")
    public Result getcheupdatescheduletimeduletimer(@RequestBody Power power) throws Exception {
        return webService.updatescheduletime(power);
    }
}
