package com.shark.community.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class accessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;


}
