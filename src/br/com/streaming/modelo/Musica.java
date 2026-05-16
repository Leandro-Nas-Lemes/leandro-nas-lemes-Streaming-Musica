package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;

public class Musica implements Reproduzivel {

    private String titulo;
    private String artista;
    private int duracao;
    private String genero;
    private int reproducoes;
    private int downloads;
    private boolean tocando;

    private static final String[] GENEROS_VALIDOS = {
        "rock", "pop", "jazz", "eletronica", "hip-hop", "classica"
    };

    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
        this.reproducoes = 0;
        this.downloads = 0;
        this.tocando = false;
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracao() { return duracao; }
    public String getGenero() { return genero; }
    public int getReproducoes() { return reproducoes; }
    public int getDownloads() { return downloads; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido");
        }
        this.titulo = titulo.trim();
    }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido");
        }
        this.artista = artista.trim();
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0 || duracao >= 3600) {
            throw new IllegalArgumentException("Duração inválida");
        }
        this.duracao = duracao;
    }

    public void setGenero(String genero) {
        if (genero == null) {
            throw new IllegalArgumentException("Gênero inválido");
        }

        String g = genero.toLowerCase().trim();
        for (String val : GENEROS_VALIDOS) {
            if (val.equals(g)) {
                this.genero = g;
                return;
            }
        }
        throw new IllegalArgumentException("Gênero inválido");
    }

    public void registrarReproducao() {
        reproducoes++;
    }

    public void registrarDownload() {
        downloads++;
    }

    public String formatarDuracao() {
        int min = duracao / 60;
        int seg = duracao % 60;
        return String.format("%d:%02d", min, seg);
    }

    public void exibir() {
        System.out.println(titulo + " | " + artista + " | " + formatarDuracao() +
                " | " + genero + " | plays: " + reproducoes + " | downloads: " + downloads);
    }

    @Override
    public void reproduzir() {
        tocando = true;
        registrarReproducao();
        System.out.println("▶ Reproduzindo música: " + titulo + " - " + artista);
    }

    @Override
    public void pausar() {
        if (tocando) {
            tocando = false;
            System.out.println("⏸ Música pausada: " + titulo);
        } else {
            System.out.println("A música não está tocando.");
        }
    }

    @Override
    public void parar() {
        tocando = false;
        System.out.println("⏹ Música parada: " + titulo);
    }

    @Override
    public int getDuracaoTotal() {
        return duracao;
    }
}
