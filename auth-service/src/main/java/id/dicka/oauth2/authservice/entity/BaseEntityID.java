package id.dicka.oauth2.authservice.entity;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntityID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected int id;
}
