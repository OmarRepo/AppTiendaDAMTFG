package com.example.buyaskill.helpers;

public enum URLS {
    domain("el dominio donde tenemos el servidor php"),
    identificar("el archivo php correspondiente"),
    registrar("el archivo php correspondiete");
    private final String url;
    private URLS(String url) {
        this.url=url;
    }
    public String toString() {
        return url;
    }
}
