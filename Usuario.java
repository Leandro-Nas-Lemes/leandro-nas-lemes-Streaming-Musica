import java.util.ArrayList;

public class Usuario {

    private String nome;
    private ArrayList<Playlist> playlists;

    public Usuario(String nome) {
        setNome(nome);
        playlists = new ArrayList<>();
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome.trim();
    }

    public void criarPlaylist(String nome) {
        playlists.add(new Playlist(nome));
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
            return null;
        }
        return playlists.get(i);
    }
}