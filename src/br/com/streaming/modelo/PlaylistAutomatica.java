package br.com.streaming.modelo;

import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {

    private String criterio; // "top", "recomendadas" ou "recentes"

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        setCriterio(criterio);
        this.descricao = "Playlist automática gerada pelo sistema";
    }

    public String getCriterio() { return criterio; }

    public void setCriterio(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            throw new IllegalArgumentException("Critério inválido");
        }

        String criterioFormatado = criterio.trim().toLowerCase();
        if (!criterioFormatado.equals("top")
                && !criterioFormatado.equals("recomendadas")
                && !criterioFormatado.equals("recentes")) {
            throw new IllegalArgumentException("Critério inválido. Use: top, recomendadas ou recentes.");
        }

        this.criterio = criterioFormatado;
    }

    public void atualizar(ArrayList<Musica> todasMusicas) {
        musicas.clear();

        if (todasMusicas == null || todasMusicas.isEmpty()) {
            return;
        }

        for (Musica m : todasMusicas) {
            if (criterio.equals("top") && m.getReproducoes() >= 2) {
                musicas.add(m);
            } else if (criterio.equals("recomendadas") &&
                    (m.getGenero().equals("pop") || m.getGenero().equals("rock"))) {
                musicas.add(m);
            } else if (criterio.equals("recentes") && musicas.size() < 5) {
                musicas.add(m);
            }
        }
    }

    @Override
    public void reproduzir() {
        System.out.println("🤖 Playlist Automática: " + nome);
        System.out.println("Critério: " + criterio);
        super.reproduzir();
    }
}
