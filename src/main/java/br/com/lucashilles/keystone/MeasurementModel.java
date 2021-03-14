package br.com.lucashilles.keystone;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_measurement")
public class MeasurementModel extends PanacheEntity {
    public Double measure;
    @Column(name = "measure_date", nullable = false,
            columnDefinition = "timestamp with time zone not null")
    public Date measureDate;
    @ManyToOne
    public EnterpriseModel enterprise;
}
