package br.com.streaming.modelo;

import java.util.ArrayList;
import br.com.streaming.servico.Reproduzivel;

public class Playlist implements Reproduzivel {

    protected String nome;
    protected ArrayList<Musica> musicas;
    protected String descricao;
    protected boolean tocando;

    public Playlist(String nome) {
        setNome(nome);
        musicas = new ArrayList<>();
        descricao = "Playlist criada pelo usuário";
        tocando = false;
    }

    public String getNome() { return nome; }
    public ArrayList<Musica> getMusicas() { return musicas; }
    public String getDescricao() { return descricao; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome.trim();
    }

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            System.out.println("Música inválida!");
            return;
        }
        musicas.add(musica);
        System.out.println("Música adicionada à playlist.");
    }

    public void removerMusica(int indice) {
        if (indice < 0 || indice >= musicas.size()) {
            System.out.println("Índice inválido!");
            return;
        }
        musicas.remove(indice);
        System.out.println("Música removida.");
    }

    public void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    @Override
    public void reproduzir() {
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        tocando = true;
        System.out.println("🎵 Reproduzindo playlist: " + nome);
        for (Musica m : musicas) {
            System.out.println("▶ " + m.getTitulo());
            m.registrarReproducao();
        }
    }

    @Override
    public void pausar() {
        if (tocando) {
            tocando = false;
            System.out.println("⏸ Playlist pausada: " + nome);
        } else {
            System.out.println("A playlist não está tocando.");
        }
    }

    @Override
    public void parar() {
        tocando = false;
        System.out.println("⏹ Playlist parada: " + nome);
    }

    @Override
    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total += m.getDuracao();
        }
        return total;
    }

    public int getQuantidade() {
        return musicas.size();
    }
}
