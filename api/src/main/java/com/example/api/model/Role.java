package com.example.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome")
    private EnumRole nomeRole;

    public EnumRole getNomeRole() {
        return nomeRole;
    }

    public void setNomeRole(EnumRole nomeRole) {
        this.nomeRole = nomeRole;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public Role(EnumRole nomeRole) {
        this.nomeRole = nomeRole;
    }

    public Role() {
    }

}
