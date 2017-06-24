package com.myproject.cloud.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by user on 13/06/17.
 */
@Slf4j
@Data
public class User {
    private String name;
    private String email;
}
