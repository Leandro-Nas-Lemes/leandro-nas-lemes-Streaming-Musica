public class Musica {

    private String titulo;
    private String artista;
    private int duracao;
    private String genero;

    private static final String[] GENEROS_VALIDOS = {
        "rock", "pop", "jazz", "eletronica", "hip-hop", "classica"
    };

    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracao() { return duracao; }
    public String getGenero() { return genero; }

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

    public String formatarDuracao() {
        int min = duracao / 60;
        int seg = duracao % 60;
        return String.format("%d:%02d", min, seg);
    }

    public void exibir() {
        System.out.println(titulo + " | " + artista + " | " + formatarDuracao() + " | " + genero);
    }
}