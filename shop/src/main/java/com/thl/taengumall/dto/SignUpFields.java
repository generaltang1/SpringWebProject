package com.thl.taengumall.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SignUpFields {
    private String name;
    private String email;
    private String password;
    private String address;
}
