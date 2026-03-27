import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario();

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE STREAMING ===");
            System.out.println("1. Cadastrar música");
            System.out.println("2. Listar músicas");
            System.out.println("3. Buscar música");
            System.out.println("4. Criar playlist");
            System.out.println("5. Gerenciar playlists");
            System.out.println("6. Estatísticas");
            System.out.println("0. Sair");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarMusica();
                case 2 -> listarMusicas();
                case 3 -> buscarMusica();
                case 4 -> criarPlaylist();
                case 5 -> gerenciarPlaylists();
                case 6 -> exibirEstatisticas();
            }

        } while (opcao != 0);
    }

    static void cadastrarMusica() {
        Musica m = new Musica();

        System.out.print("Título: ");
        m.titulo = scanner.nextLine();

        System.out.print("Artista: ");
        m.artista = scanner.nextLine();

        System.out.print("Duração (segundos): ");
        m.duracaoSegundos = Integer.parseInt(scanner.nextLine());

        System.out.print("Gênero: ");
        m.genero = scanner.nextLine();

        musicas.add(m);
    }

    static void listarMusicas() {
        for (Musica m : musicas) {
            m.exibir();
        }
    }

    static void buscarMusica() {
        System.out.print("Digite algo para buscar: ");
        String busca = scanner.nextLine();

        for (Musica m : musicas) {
            if (m.contemTitulo(busca) || m.contemArtista(busca)) {
                m.exibir();
            }
        }
    }

    static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        usuario.criarPlaylist(nome);
    }

    static void gerenciarPlaylists() {
        usuario.listarPlaylists();

        System.out.print("Escolha uma playlist: ");
        int i = Integer.parseInt(scanner.nextLine()) - 1;

        Playlist p = usuario.getPlaylist(i);

        if (p == null) return;

        System.out.println("1. Adicionar música");
        System.out.println("2. Remover música");
        System.out.println("3. Listar músicas");

        int op = Integer.parseInt(scanner.nextLine());

        switch (op) {
            case 1 -> {
                listarMusicas();
                System.out.print("Escolha a música: ");
                int m = Integer.parseInt(scanner.nextLine()) - 1;
                p.adicionarMusica(musicas.get(m));
            }
            case 2 -> {
                p.listarMusicas();
                System.out.print("Remover índice: ");
                int r = Integer.parseInt(scanner.nextLine()) - 1;
                p.removerMusica(r);
            }
            case 3 -> p.listarMusicas();
        }
    }

    static void exibirEstatisticas() {
        int total = musicas.size();
        int soma = 0;

        for (Musica m : musicas) {
            soma += m.duracaoSegundos;
        }

        System.out.println("Total músicas: " + total);
        System.out.println("Duração total: " + soma);
    }
}
