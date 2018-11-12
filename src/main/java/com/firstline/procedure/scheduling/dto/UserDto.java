package com.firstline.procedure.scheduling.dto;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.NotEmpty;

public class UserDto {

    private String name;

    @NotNull
    @NotEmpty
    private String password;
    private String password2;


    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public UserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
