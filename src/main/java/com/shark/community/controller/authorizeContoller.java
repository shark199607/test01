package com.shark.community.controller;


import com.shark.community.mapper.userMapper;
import com.shark.community.dto.accessTokenDto;
import com.shark.community.dto.githubUser;
import com.shark.community.model.user;
import com.shark.community.provider.githubProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

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

 /*   @Autowired
    private JdbcTemplate jdbcTemplate;*/
    @Autowired
    private userMapper userMapper;



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


        githubUser githubUser=githubProvide.getUser(accessToken);

        if(githubUser!=null){
            //登陆成功
            System.out.println("登陆成功");
            user user1 = new user();

            user1.setToken(UUID.randomUUID().toString());
            user1.setName(githubUser.getName());
            user1.setAccountId(String.valueOf(githubUser.getId()));
            user1.setGmtCreate(System.currentTimeMillis());
            user1.setGmtModified(user1.getGmtCreate());
            System.out.println(user1.getName());

            String sql = "insert into user (name,account_id) value (?,?)";
            Object[] arg={user1.getName(),user1.getAccountId()};
            userMapper.insert(user1);

            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            //登陆失败
            return "redirect:/";
        }
    }



}
