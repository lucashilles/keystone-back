package br.com.lucashilles.keystone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.*;

@Entity
@Table(name = "sys_user")
@UserDefinition
public class UserModel extends PanacheEntity {
    public String name;
    @Username
    public String email;
    @Password
    @JsonIgnore
    public String password;
    public String cpfcnpj;
    @Roles
    public String userType;
    public String semaRegister;
    public String professionRegister;


    /**
     * Adds a new user in the database
     * @param username the user name
     * @param password the unencrypted password (it will be encrypted with bcrypt)
     * @param role the comma-separated roles
     */
    public static void add(String email, String password, String userType) {
        UserModel user = new UserModel();
        user.email = email;
        user.password = BcryptUtil.bcryptHash(password);
        user.userType = userType;
        user.persist();
    }

}