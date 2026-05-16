package br.com.streaming.app;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.streaming.modelo.Musica;
import br.com.streaming.modelo.Playlist;
import br.com.streaming.modelo.PlaylistAutomatica;
import br.com.streaming.modelo.Usuario;
import br.com.streaming.modelo.UsuarioFree;
import br.com.streaming.modelo.UsuarioPremium;

public class StreamingMusica {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        carregarDadosIniciais();

        int op;
        do {
            System.out.println("\n=== SISTEMA DE STREAMING ===");
            System.out.println("Usuário logado: " + (usuarioLogado == null ? "nenhum" : usuarioLogado.getNome() + " (" + usuarioLogado.getTipo() + ")"));
            System.out.println("1. Criar novo usuário");
            System.out.println("2. Login");
            System.out.println("3. Listar usuários");
            System.out.println("4. Cadastrar música");
            System.out.println("5. Listar músicas");
            System.out.println("6. Buscar música");
            System.out.println("7. Reproduzir música");
            System.out.println("8. Gerenciar playlists");
            System.out.println("9. Gerar playlist automática");
            System.out.println("10. Estatísticas do sistema");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            op = lerInteiro();

            try {
                switch (op) {
                    case 1: criarUsuario(); break;
                    case 2: login(); break;
                    case 3: listarUsuarios(); break;
                    case 4: cadastrarMusica(); break;
                    case 5: listarMusicas(); break;
                    case 6: buscarMusica(); break;
                    case 7: reproduzirMusica(); break;
                    case 8: gerenciarPlaylists(); break;
                    case 9: gerarPlaylistAutomatica(); break;
                    case 10: estatisticas(); break;
                    case 0: System.out.println("Encerrando..."); break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (op != 0);
    }

    static void carregarDadosIniciais() {
        musicas.add(new Musica("Horizonte", "Ana Beat", 210, "pop"));
        musicas.add(new Musica("Noite Rock", "Banda Java", 180, "rock"));
        musicas.add(new Musica("Jazz Azul", "Blue Code", 240, "jazz"));

        usuarios.add(new UsuarioFree("Ana", "ana@email.com"));
        usuarios.add(new UsuarioPremium("Carlos", "carlos@email.com", "Mensal"));
    }

    static int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.nextLine();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    static boolean verificarLogin() {
        if (usuarioLogado == null) {
            System.out.println("Faça login primeiro.");
            return false;
        }
        return true;
    }

    static void criarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("Tipo de conta:");
        System.out.println("1. Free");
        System.out.println("2. Premium");
        System.out.print("Escolha: ");
        int tipo = lerInteiro();

        if (tipo == 1) {
            usuarios.add(new UsuarioFree(nome, email));
        } else if (tipo == 2) {
            System.out.print("Plano (Mensal/Anual/Familiar): ");
            String plano = scanner.nextLine();
            usuarios.add(new UsuarioPremium(nome, email, plano));
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        System.out.println("✅ Usuário criado!");
    }

    static void login() {
        listarUsuarios();
        if (usuarios.isEmpty()) return;

        System.out.print("Escolha o usuário: ");
        int indice = lerInteiro() - 1;

        if (indice < 0 || indice >= usuarios.size()) {
            System.out.println("Usuário inválido.");
            return;
        }

        usuarioLogado = usuarios.get(indice);
        System.out.println("✅ Login realizado: " + usuarioLogado.getNome() + " (" + usuarioLogado.getTipo() + ")");
    }

    static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\nUsuários cadastrados:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.print((i + 1) + ". ");
            usuarios.get(i).exibirResumo();
        }
    }

    static void cadastrarMusica() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        System.out.print("Duração em segundos: ");
        int duracao = lerInteiro();

        System.out.print("Gênero (rock, pop, jazz, eletronica, hip-hop, classica): ");
        String genero = scanner.nextLine();

        musicas.add(new Musica(titulo, artista, duracao, genero));
        System.out.println("✅ Música cadastrada!");
    }

    static void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    static void buscarMusica() {
        System.out.print("Buscar por título ou artista: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean encontrou = false;
        for (Musica m : musicas) {
            if (m.getTitulo().toLowerCase().contains(busca) ||
                    m.getArtista().toLowerCase().contains(busca)) {
                m.exibir();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada.");
        }
    }

    static Musica escolherMusica() {
        listarMusicas();
        if (musicas.isEmpty()) return null;

        System.out.print("Escolha a música: ");
        int indice = lerInteiro() - 1;

        if (indice < 0 || indice >= musicas.size()) {
            System.out.println("Música inválida.");
            return null;
        }

        return musicas.get(indice);
    }

    static void reproduzirMusica() {
        if (!verificarLogin()) return;

        Musica musica = escolherMusica();
        if (musica == null) return;

        usuarioLogado.reproduzirMusica(musica);

        if (usuarioLogado instanceof UsuarioPremium) {
            System.out.print("Deseja baixar a música? (s/n): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                UsuarioPremium premium = (UsuarioPremium) usuarioLogado;
                premium.baixarMusica(musica);
            }
        }
    }

    static void gerenciarPlaylists() {
        if (!verificarLogin()) return;

        System.out.println("1. Criar playlist comum");
        System.out.println("2. Listar minhas playlists");
        System.out.println("3. Adicionar música em playlist");
        System.out.println("4. Reproduzir playlist");
        System.out.print("Escolha: ");
        int op = lerInteiro();

        if (op == 1) {
            System.out.print("Nome da playlist: ");
            usuarioLogado.criarPlaylist(scanner.nextLine());
        } else if (op == 2) {
            usuarioLogado.listarPlaylists();
        } else if (op == 3) {
            usuarioLogado.listarPlaylists();
            System.out.print("Escolha a playlist: ");
            Playlist p = usuarioLogado.getPlaylist(lerInteiro() - 1);
            if (p == null) return;

            Musica musica = escolherMusica();
            if (musica != null) p.adicionarMusica(musica);
        } else if (op == 4) {
            usuarioLogado.listarPlaylists();
            System.out.print("Escolha a playlist: ");
            Playlist p = usuarioLogado.getPlaylist(lerInteiro() - 1);
            if (p != null) p.reproduzir();
        }
    }

    static void gerarPlaylistAutomatica() {
        if (!verificarLogin()) return;

        if (!(usuarioLogado instanceof UsuarioPremium)) {
            System.out.println("Apenas usuários Premium podem gerar playlists automáticas.");
            return;
        }

        System.out.println("=== PLAYLISTS AUTOMÁTICAS ===");
        System.out.println("1. Top músicas mais tocadas");
        System.out.println("2. Recomendadas para você");
        System.out.println("3. Adicionadas recentemente");
        System.out.print("Escolha: ");
        int op = lerInteiro();

        String criterio;
        if (op == 1) {
            criterio = "top";
        } else if (op == 2) {
            criterio = "recomendadas";
        } else if (op == 3) {
            criterio = "recentes";
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        PlaylistAutomatica playlist = new PlaylistAutomatica("Automática - " + criterio, criterio);
        playlist.atualizar(musicas);
        usuarioLogado.getPlaylists().add(playlist);

        System.out.println("✅ Playlist criada com " + playlist.getQuantidade() + " músicas!");
    }

    static void estatisticas() {
        int totalFree = 0;
        int totalPremium = 0;
        int reproducoesFree = 0;
        int reproducoesPremium = 0;
        int anuncios = 0;
        int downloads = 0;

        for (Usuario u : usuarios) {
            if (u instanceof UsuarioPremium) {
                totalPremium++;
                reproducoesPremium += u.getContadorReproducoes();
                UsuarioPremium premium = (UsuarioPremium) u;
                downloads += premium.getMusicasBaixadas();
            } else if (u instanceof UsuarioFree) {
                totalFree++;
                reproducoesFree += u.getContadorReproducoes();
                UsuarioFree free = (UsuarioFree) u;
                anuncios += free.getAnunciosExibidos();
            }
        }

        int totalUsuarios = usuarios.size();
        int totalReproducoes = reproducoesFree + reproducoesPremium;

        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        System.out.println("Total de usuários: " + totalUsuarios);
        System.out.println("- Free: " + totalFree + " usuários");
        System.out.println("- Premium: " + totalPremium + " usuários");
        System.out.println("Músicas cadastradas: " + musicas.size());
        System.out.println("Reproduções totais: " + totalReproducoes);
        System.out.println("- Free: " + reproducoesFree + " reproduções");
        System.out.println("- Premium: " + reproducoesPremium + " reproduções");
        System.out.println("Anúncios exibidos: " + anuncios);
        System.out.println("Downloads Premium: " + downloads);
    }
}
