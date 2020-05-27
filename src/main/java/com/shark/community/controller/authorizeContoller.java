package com.shark.community.controller;

import com.shark.community.dto.accessTokenDto;
import com.shark.community.dto.githubUser;
import com.shark.community.provider.githubProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class authorizeContoller {

    @Autowired
    private githubProvide githubProvide;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectURL;



    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state) throws IOException {
        accessTokenDto accessTokenDto = new accessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url(redirectURL);

        String accessToken = githubProvide.getAccessToken(accessTokenDto);
        githubUser user=githubProvide.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }



}
