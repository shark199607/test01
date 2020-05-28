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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
                           @RequestParam(name="state")String state,HttpServletRequest request) throws IOException {

        accessTokenDto accessTokenDto = new accessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url(redirectURL);
        accessTokenDto.setState(state);

        String accessToken = githubProvide.getAccessToken(accessTokenDto);

        githubUser user=githubProvide.getUser(accessToken);
        System.out.println(user.getName());
        if(user!=null){
            //登陆成功
            System.out.println("登陆成功");
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            //登陆失败
            return "redirect:/";
        }
    }



}
