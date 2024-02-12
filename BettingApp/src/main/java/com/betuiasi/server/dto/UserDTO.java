package com.betuiasi.server.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class  UserDTO {

    @Id
    private String uid;
    private String username;
    private String password;

    private String cnp;

    private String role;
    private Double funds;

    public UserDTO(String username, String password, String CNP) {
        this.username = username;
        this.password = password;
        this.cnp = CNP;
    }
}
