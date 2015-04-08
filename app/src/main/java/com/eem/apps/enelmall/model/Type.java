package com.eem.apps.enelmall.model;

public enum Type {
    TIEMPO_LIMITADO(0),
    DESCUENTO(1);

    private int id;

    Type(int id) {
        this.id = id;
    }

    public static Type getFromId(int id) {
        return values()[id];
    }
}