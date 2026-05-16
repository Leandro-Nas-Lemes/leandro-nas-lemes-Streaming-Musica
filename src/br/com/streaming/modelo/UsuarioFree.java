package br.com.streaming.modelo;

public class UsuarioFree extends Usuario {

    private int anunciosExibidos;
    public static final int LIMITE_PLAYLISTS = 3;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.anunciosExibidos = 0;
    }

    public int getAnunciosExibidos() { return anunciosExibidos; }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= LIMITE_PLAYLISTS) {
            System.out.println("Usuário Free pode criar no máximo " + LIMITE_PLAYLISTS + " playlists.");
            return;
        }
        super.criarPlaylist(nome);
    }

    @Override
    public void criarPlaylistAutomatica(String nome, String criterio) {
        System.out.println("Playlist automática é recurso Premium.");
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        super.reproduzirMusica(musica);
        if (contadorReproducoes % 3 == 0) {
            anunciosExibidos++;
            System.out.println("📢 Anúncio exibido para usuário Free.");
        }
    }

    @Override
    public String getTipo() {
        return "Free";
    }

    @Override
    public void exibirResumo() {
        super.exibirResumo();
        System.out.println("   Anúncios exibidos: " + anunciosExibidos +
                " | Limite de playlists: " + LIMITE_PLAYLISTS);
    }
}
