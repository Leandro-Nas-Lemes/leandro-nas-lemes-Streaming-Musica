import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario("Usuário");

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n--- SISTEMA DE STREAMING ---");
            System.out.println("1. Cadastrar música");
            System.out.println("2. Listar músicas");
            System.out.println("3. Buscar música");
            System.out.println("4. Criar playlist");
            System.out.println("5. Gerenciar playlists");
            System.out.println("6. Estatísticas");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: listar(); break;
                case 3: buscar(); break;
                case 4: criarPlaylist(); break;
                case 5: gerenciarPlaylist(); break;
                case 6: estatisticas(); break;
            }

        } while (opcao != 0);
    }

    static void cadastrar() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        System.out.print("Duração: ");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        musicas.add(new Musica(titulo, artista, duracao, genero));
    }

    static void listar() {
        for (Musica m : musicas) {
            m.exibir();
        }
    }

    static void buscar() {
        System.out.print("Buscar: ");
        String busca = scanner.nextLine().toLowerCase();

        for (Musica m : musicas) {
            if (m.titulo.toLowerCase().contains(busca) ||
                m.artista.toLowerCase().contains(busca)) {
                m.exibir();
            }
        }
    }

    static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        usuario.criarPlaylist(nome);
    }

    static void gerenciarPlaylist() {

        usuario.listarPlaylists();
        System.out.print("Escolha: ");
        int i = scanner.nextInt() - 1;
        scanner.nextLine();

        Playlist p = usuario.getPlaylist(i);

        if (p == null) {
            System.out.println("Playlist inválida!");
            return;
        }

        int op;

        do {
            System.out.println("\n1. Listar músicas");
            System.out.println("2. Adicionar música");
            System.out.println("3. Remover música");
            System.out.println("0. Voltar");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    p.listarMusicas();
                    break;

                case 2:
                    listar();
                    System.out.print("Escolha música: ");
                    int m = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (m >= 0 && m < musicas.size()) {
                        p.adicionarMusica(musicas.get(m));
                    }
                    break;

                case 3:
                    p.listarMusicas();
                    System.out.print("Remover: ");
                    int r = scanner.nextInt() - 1;
                    scanner.nextLine();
                    p.removerMusica(r);
                    break;
            }

        } while (op != 0);
    }

    static void estatisticas() {

        if (musicas.isEmpty()) {
            System.out.println("Sem músicas.");
            return;
        }

        int total = musicas.size();
        int soma = 0;

        for (Musica m : musicas) {
            soma += m.duracao;
        }

        System.out.println("Total: " + total);
        System.out.println("Duração total: " + soma + "s");
        System.out.println("Média: " + (soma / total) + "s");
    }
}