package com.thl.taengumall.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class SignInFields {
    @NotBlank(message = "Not nullable")
    private String email;

    @NotBlank(message = "Not Nullable")
    private String password;
}
