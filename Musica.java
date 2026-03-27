public class Musica {

    String titulo;
    String artista;
    int duracaoSegundos;
    String genero;

    void exibir() {
        System.out.printf("- %s | %s | %s | %s%n",
                titulo,
                artista,
                getDuracaoFormatada(),
                genero);
    }

    String getDuracaoFormatada() {
        int minutos = duracaoSegundos / 60;
        int segundos = duracaoSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    boolean contemTitulo(String busca) {
        return titulo.toLowerCase().contains(busca.toLowerCase());
    }

    boolean contemArtista(String busca) {
        return artista.toLowerCase().contains(busca.toLowerCase());
    }
}
