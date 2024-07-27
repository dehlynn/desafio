package com.github.dehlynn.desafio.model;

public enum Role {
    ADMIN("Admin"),
    MEMBER("Member"),
    EXTERNAL("External");

    private final String nomeRoles;

    Role(String nomeRoles) {
        this.nomeRoles = nomeRoles;
    }

    public String getNomeRoles() {
        return nomeRoles;
    }


}