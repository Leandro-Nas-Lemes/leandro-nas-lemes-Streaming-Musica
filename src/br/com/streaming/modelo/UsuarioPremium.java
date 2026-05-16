package br.com.streaming.modelo;

import java.util.ArrayList;
import br.com.streaming.servico.Baixavel;

public class UsuarioPremium extends Usuario implements Baixavel {

    private String plano;
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        setPlano(plano);
        this.musicasBaixadas = new ArrayList<>();
    }

    public String getPlano() { return plano; }

    public int getMusicasBaixadas() {
        return musicasBaixadas.size();
    }

    public void setPlano(String plano) {
        if (plano == null || plano.trim().isEmpty()) {
            throw new IllegalArgumentException("Plano inválido");
        }
        this.plano = plano.trim();
    }

    public void baixarMusica(Musica musica) {
        baixar(musica);
    }

    @Override
    public void baixar(Musica musica) {
        if (musica == null) {
            System.out.println("Música inválida.");
            return;
        }

        if (estaBaixada(musica)) {
            System.out.println("Esta música já foi baixada.");
            return;
        }

        musicasBaixadas.add(musica);
        musica.registrarDownload();
        System.out.println("⬇ Música baixada: " + musica.getTitulo());
    }

    @Override
    public void removerDownload(Musica musica) {
        if (musica == null) {
            System.out.println("Música inválida.");
            return;
        }

        if (musicasBaixadas.remove(musica)) {
            System.out.println("Download removido: " + musica.getTitulo());
        } else {
            System.out.println("Música não estava baixada.");
        }
    }

    @Override
    public boolean estaBaixada(Musica musica) {
        return musicasBaixadas.contains(musica);
    }

    @Override
    public int getTamanhoBaixado() {
        int total = 0;
        for (Musica m : musicasBaixadas) {
            total += m.getDuracao();
        }
        return total;
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        super.reproduzirMusica(musica);
        System.out.println("⭐ Reprodução sem anúncios para usuário Premium.");
    }

    @Override
    public String getTipo() {
        return "Premium";
    }

    @Override
    public void exibirResumo() {
        super.exibirResumo();
        System.out.println("   Plano: " + plano + " | Downloads: " + getMusicasBaixadas());
    }
}
