package com.shark.community.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class githubUser {
    private String name;
    private Long id;
    private String bio;
}
