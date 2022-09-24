package br.dev.magliano.productjpa.security;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class PlainPassword {

    private String password;

    public PlainPassword(@NotBlank @Length(min = 6) String openPassword) {
        Assert.hasLength(openPassword, "Senha não pode ser em branco");
        Assert.isTrue(openPassword.length()>=6, "Senha precisa ter no mínimo 6 caraceteres");
        this.password = openPassword;
    }

    public String encode(){
        return new BCryptPasswordEncoder().encode(this.password);
    }
}

