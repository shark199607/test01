package com.shark.community.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shark on 2020/5/28 10:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}
