package com.netsense.cloudfilemanager.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
public class RestTempleController {

    @Autowired
    RestTemplate restTemplate;

    /***********HTTP GET method*************/
    @RequestMapping("get")
    public String hello() {
        String url = "http://localhost:8080/json";
        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        return json.toString();
    }

    @RequestMapping("/json")
    public Object genJson() {
        JSONObject json = new JSONObject();
        json.put("descp", "this is spring rest template sample");
        return json;
    }

    /**********HTTP POST method**************/
    @RequestMapping("/postApi")
    public Object iAmPostApi(@RequestBody JSONObject parm) {
        System.out.println(parm.toString());
        parm.put("result", "hello post");
        return parm;
    }

    @RequestMapping("/post")
    public Object testPost() {
        String url = "http://localhost:8080/postApi";
        JSONObject postData = new JSONObject();
        postData.put("descp", "request for post");
        JSONObject json = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
        return json.toString();
    }

    @RequestMapping("/async")
    public String asyncReq() {

        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        String url = "http://localhost:8080/json";
        ListenableFuture<ResponseEntity<JSONObject>> future = asyncRestTemplate.getForEntity(url, JSONObject.class);
        future.addCallback(new SuccessCallback<ResponseEntity<JSONObject>>() {
            @Override
            public void onSuccess(ResponseEntity<JSONObject> result) {
                System.out.println(result.getBody().toString());
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("onFailure:" + ex);
            }
        });
        return "this is async sample";
    }


    @RequestMapping("/headerApi")//模拟远程的restful API
    public JSONObject withHeader(@RequestBody JSONObject parm, HttpServletRequest req) {
        System.out.println("headerApi=====" + parm.toString());
        Enumeration<String> headers = req.getHeaderNames();
        JSONObject result = new JSONObject();
        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            System.out.println("[" + name + "]=" + req.getHeader(name));
            result.put(name, req.getHeader(name));
        }
        result.put("descp", "this is from header");
        return result;
    }

    @RequestMapping("/header")
    public Object postWithHeader() {
        //该方法通过restTemplate请求远程restfulAPI;
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth_token", "asdfgh");
        headers.set("Other-Header", "othervalue");
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject parm = new JSONObject();
        parm.put("parm", "1234");
        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(parm, headers);
        HttpEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/headerApi", HttpMethod.POST, entity, String.class);//这里放JSONObject, String 都可以。因为JSONObject返回的时候其实也就是string
        return response.getBody();
    }
}
