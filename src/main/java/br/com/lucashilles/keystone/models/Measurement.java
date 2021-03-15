package br.com.lucashilles.keystone.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_measurement")
public class Measurement extends PanacheEntity {
    public Double measure;
    @Column(name = "measure_date", nullable = false,
            columnDefinition = "timestamp with time zone not null")
    public Date measureDate;
    @ManyToOne
    public Enterprise enterprise;
}
