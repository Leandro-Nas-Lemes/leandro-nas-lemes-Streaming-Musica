# 🎵 Sistema de Streaming de Música

Projeto em Java para gerenciamento de um sistema de streaming musical, desenvolvido como atividade/checkpoint de Programação Orientada a Objetos.

## 📋 Funcionalidades

- Cadastro de músicas com título, artista, duração e gênero.
- Listagem de músicas cadastradas.
- Busca de músicas por título ou artista.
- Cadastro e login de usuários.
- Tipos de usuários:
  - Usuário Free
  - Usuário Premium
- Reprodução de músicas.
- Criação e gerenciamento de playlists.
- Geração de playlists automáticas para usuários Premium.
- Sistema de downloads para usuários Premium.
- Exibição de anúncios para usuários Free.
- Estatísticas gerais do sistema.

## 🏗️ Arquitetura

O projeto foi organizado em pacotes:

```text
src/
└── br/
    └── com/
        └── streaming/
            ├── app/
            │   └── StreamingMusica.java
            ├── modelo/
            │   ├── Musica.java
            │   ├── Playlist.java
            │   ├── PlaylistAutomatica.java
            │   ├── Usuario.java
            │   ├── UsuarioFree.java
            │   └── UsuarioPremium.java
            └── servico/
                ├── Baixavel.java
                └── Reproduzivel.java
```

## 🔌 Interfaces

### Reproduzivel

Implementada por `Musica` e `Playlist`.

Métodos principais:

- `reproduzir()`
- `pausar()`
- `parar()`
- `getDuracaoTotal()`

### Baixavel

Implementada por `UsuarioPremium`.

Métodos principais:

- `baixar(Musica musica)`
- `removerDownload(Musica musica)`
- `estaBaixada(Musica musica)`
- `getTamanhoBaixado()`

## 🧱 Classe Abstrata

A classe `Usuario` foi definida como abstrata, pois serve como base para os tipos específicos de usuário:

- `UsuarioFree`
- `UsuarioPremium`

Ela centraliza atributos e comportamentos comuns, como nome, e-mail, playlists e contador de reproduções.

## 🚀 Como executar

No terminal, dentro da pasta do projeto, compile com:

```bash
javac -d bin src/br/com/streaming/servico/*.java src/br/com/streaming/modelo/*.java src/br/com/streaming/app/*.java
```

Depois execute com:

```bash
java -cp bin br.com.streaming.app.StreamingMusica
```

## 👤 Autor

- Nome: [Seu Nome]
- RA: [Seu RA]

## 📅 Histórico dos 6 Checkpoints

1. Checkpoint 1: Estrutura inicial das classes.
2. Checkpoint 2: Cadastro de músicas e usuários.
3. Checkpoint 3: Criação de playlists.
4. Checkpoint 4: Herança com usuários Free e Premium.
5. Checkpoint 5: Polimorfismo, estatísticas e downloads.
6. Checkpoint 6: Pacotes, interfaces, classe abstrata e README.md.
