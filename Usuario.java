import java.util.ArrayList;

public class Usuario {

    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();

    void criarPlaylist(String nome) {
        Playlist p = new Playlist();
        p.nome = nome;
        playlists.add(p);
        System.out.println("Playlist criada com sucesso!");
    }

    Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        }
        return null;
    }

    void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
            return;
        }

        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).nome);
        }
    }
}
