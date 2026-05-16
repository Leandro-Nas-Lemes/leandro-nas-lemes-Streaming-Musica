package br.com.streaming.modelo;

import java.util.ArrayList;

public abstract class Usuario {

    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected int contadorReproducoes;

    public Usuario(String nome, String email) {
        setNome(nome);
        validarEmail(email);
        this.email = email.trim().toLowerCase();
        playlists = new ArrayList<>();
        contadorReproducoes = 0;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public int getContadorReproducoes() { return contadorReproducoes; }
    public ArrayList<Playlist> getPlaylists() { return playlists; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome.trim();
    }

    public final void validarEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    public void criarPlaylist(String nome) {
        playlists.add(new Playlist(nome));
    }

    public void criarPlaylistAutomatica(String nome, String criterio) {
        playlists.add(new PlaylistAutomatica(nome, criterio));
    }

    public void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist.");
            return;
        }

        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
        }
    }

    public Playlist getPlaylist(int i) {
        if (i < 0 || i >= playlists.size()) {
            System.out.println("Playlist inválida.");
            return null;
        }
        return playlists.get(i);
    }

    public void reproduzirMusica(Musica musica) {
        if (musica == null) {
            System.out.println("Música inválida.");
            return;
        }
        contadorReproducoes++;
        musica.registrarReproducao();
        System.out.println("▶ " + nome + " está ouvindo: " + musica.getTitulo());
    }

    public abstract String getTipo();

    public void exibirResumo() {
        System.out.println(nome + " (" + getTipo() + ") - " + email +
                " | Playlists: " + playlists.size() +
                " | Reproduções: " + contadorReproducoes);
    }
}
