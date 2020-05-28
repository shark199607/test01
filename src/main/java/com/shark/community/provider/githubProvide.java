package com.shark.community.provider;

import com.alibaba.fastjson.JSON;
import com.shark.community.dto.accessTokenDto;
import com.shark.community.dto.githubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.io.IOException;

@Component
public class githubProvide {
    public String getAccessToken(accessTokenDto accessTokenDto) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public githubUser getUser(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        System.out.println("try前");
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            githubUser githubUser = JSON.parseObject(string, githubUser.class);
            return githubUser;
        } catch (IOException e) {
            System.out.println("错误");
        }
        return null;
    }
}
