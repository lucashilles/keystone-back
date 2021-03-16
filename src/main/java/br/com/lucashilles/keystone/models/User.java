package br.com.lucashilles.keystone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_user", indexes = @Index(columnList = "email", unique = true, name = "email_sys_user"))
@UserDefinition
public class User extends PanacheEntity {
    public String name;
    @Username
    public String email;
    @Password
    public String password;
    public String cpfcnpj;
    @Roles
    public String userType;
    public String semaRegister;
    public String professionRegister;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    public List<Enterprise> enterprises;


    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public static User add(String email, String password, String userType) {
        User user = new User();
        user.email = email;
        user.password = BcryptUtil.bcryptHash(password);
        user.userType = userType;
        user.persist();
        return user;
    }

}