package com.example.navigationmenu.Models;

public class ListElementMain {
    public String titulo,subtitulo;

    public ListElementMain(String titulo, String subtitulo) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

}
