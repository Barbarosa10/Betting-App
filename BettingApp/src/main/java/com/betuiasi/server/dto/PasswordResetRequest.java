package com.betuiasi.server.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PasswordResetRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
