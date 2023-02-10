package com.example.ApiTokenGen.ApiTokenGen;


import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class LoginService {
    private  RestTemplate restTemplate;
    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public LoginResponse getToken(LoginRequest loginRequest){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        HttpEntity<String> request=new HttpEntity<>(gson.toJson(loginRequest), headers);
        ResponseEntity<LoginResponse> s = restTemplate.postForEntity("https://coreuat-zwqcqy3qmq-el.a.run.app/getlogintoken",request,LoginResponse.class);
//        System.out.println(s.getBody());
        return s.getBody();
    }

    private final String url = "https://coreuat-zwqcqy3qmq-el.a.run.app/user/getuserprofile?id";
    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdHBsIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNjc2MDIzMDY1NTA3LCJleHAiOjE2NzYwMjQ4NjV9.ffjBMMiH-zAlB0KA3cMeOdC1suG1_bWBYFswCxxBDQ9g0E3hWnKxvKCo6YcGQfc6I6EA7KmX_Yr7YV0HbsAX5w";

    public ResponseEntity<UserDetails> getUserDetails() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","" + token);

        HttpEntity<UserDetails> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(url,HttpMethod.GET, entity, UserDetails.class);
    }

    public ResponseEntity<UserDetails> getUserDetailsByUsernameAndPassword(LoginRequest loginRequest) {
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        HttpEntity<String> request=new HttpEntity<>(gson.toJson(loginRequest), headers);
        ResponseEntity<LoginResponse> s = restTemplate.postForEntity("https://coreuat-zwqcqy3qmq-el.a.run.app/getlogintoken",request,LoginResponse.class);
        JSONObject jsonObject = new JSONObject(s.getBody());
        String generatedToken = jsonObject.getString("token");
        System.out.println(generatedToken);
        String url = "https://coreuat-zwqcqy3qmq-el.a.run.app/user/getuserprofile?id";
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.set("Authorization",""+generatedToken);

        HttpEntity<UserDetails> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(url,HttpMethod.GET, entity, UserDetails.class);
    }




}
