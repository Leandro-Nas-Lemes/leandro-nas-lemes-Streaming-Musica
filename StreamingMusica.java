import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario("User");

    public static void main(String[] args) {

        int op;

        do {
            System.out.println("\n1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Playlist");
            System.out.println("0. Sair");

            op = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (op) {
                    case 1: cadastrar(); break;
                    case 2: listar(); break;
                    case 3: buscar(); break;
                    case 4: playlist(); break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (op != 0);
    }

    static void cadastrar() {
        System.out.print("Título: ");
        String t = scanner.nextLine();

        System.out.print("Artista: ");
        String a = scanner.nextLine();

        System.out.print("Duração: ");
        int d = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Gênero: ");
        String g = scanner.nextLine();

        musicas.add(new Musica(t, a, d, g));
    }

    static void listar() {
        for (Musica m : musicas) {
            m.exibir();
        }
    }

    static void buscar() {
        System.out.print("Buscar: ");
        String b = scanner.nextLine().toLowerCase();

        for (Musica m : musicas) {
            if (m.getTitulo().toLowerCase().contains(b) ||
                m.getArtista().toLowerCase().contains(b)) {
                m.exibir();
            }
        }
    }

    static void playlist() {
        System.out.println("1. Criar");
        System.out.println("2. Gerenciar");

        int op = scanner.nextInt();
        scanner.nextLine();

        if (op == 1) {
            System.out.print("Nome: ");
            usuario.criarPlaylist(scanner.nextLine());
        } else {
            usuario.listarPlaylists();
            int i = scanner.nextInt() - 1;
            scanner.nextLine();

            Playlist p = usuario.getPlaylist(i);
            if (p == null) return;

            p.listarMusicas();
        }
    }
}