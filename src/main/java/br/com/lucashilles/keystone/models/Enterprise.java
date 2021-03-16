package br.com.lucashilles.keystone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_enterprise")
public class Enterprise extends PanacheEntity {
    public String name;
    public String city;
    public String state;
    public String latitude;
    public String longitude;
    public String equation;
    public String fantasyName;
    public String cpfcnpj;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "sys_user_sys_enterprise",
            joinColumns = {@JoinColumn(name = "enterprise_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public List<User> users;

    @OneToMany(mappedBy = "enterprise", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Measurement> measures;
}
