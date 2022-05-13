package com.company.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.company.bd.daos.*;
import com.company.bd.dbos.*;

public class Programa {
    public static void main(String[] args) throws IOException {
        boolean continuar = true;

        while (continuar) {
            try {
                // Teclado.limparConsole();

                System.out.println("""

                            .▄▄ · ▄▄▄ .▄▄▄  ▪   ▄▄▄· ▄▄▌      ▄ •▄ ▪  ▄▄▌  ▄▄▌  ▄▄▄ .▄▄▄       ▄▄· ▄▄▄  ▄• ▄▌·▄▄▄▄ \s
                            ▐█ ▀. ▀▄.▀·▀▄ █·██ ▐█ ▀█ ██•      █▌▄▌▪██ ██•  ██•  ▀▄.▀·▀▄ █·    ▐█ ▌▪▀▄ █·█▪██▌██▪ ██\s
                            ▄▀▀▀█▄▐▀▀▪▄▐▀▀▄ ▐█·▄█▀▀█ ██▪      ▐▀▀▄·▐█·██▪  ██▪  ▐▀▀▪▄▐▀▀▄     ██ ▄▄▐▀▀▄ █▌▐█▌▐█· ▐█▌
                            ▐█▄▪▐█▐█▄▄▌▐█•█▌▐█▌▐█ ▪▐▌▐█▌▐▌    ▐█.█▌▐█▌▐█▌▐▌▐█▌▐▌▐█▄▄▌▐█•█▌    ▐███▌▐█•█▌▐█▄█▌██. ██\s
                             ▀▀▀▀  ▀▀▀ .▀  ▀▀▀▀ ▀  ▀ .▀▀▀     ·▀  ▀▀▀▀.▀▀▀ .▀▀▀  ▀▀▀ .▀  ▀    ·▀▀▀ .▀  ▀ ▀▀▀ ▀▀▀▀▀•\s
                        """);

                System.out.println("0 - Sair");
                System.out.println("1 - Cadastrar um Serial Killer");
                System.out.println("2 - Consultar portfólios dos Serial Killers");
                System.out.println("3 - Atualizar portfólio de um Serial Killer");
                System.out.println("4 - Excluir um Serial Killer\n");
                System.out.print("Escolha uma opção: ");
                char opcao = Teclado.getUmChar();

                switch (opcao) {
                    case '0':
                        continuar = false;
                        break;

                    case '1':
                        try {
                            // Teclado.limparConsole();

                            System.out.println("""

                                     ▄▄· ▄▄▄  ▄▄▄ . ▄▄▄· ▄▄▄▄▄▄▄▄ .
                                    ▐█ ▌▪▀▄ █·▀▄.▀·▐█ ▀█ •██  ▀▄.▀·
                                    ██ ▄▄▐▀▀▄ ▐▀▀▪▄▄█▀▀█  ▐█.▪▐▀▀▪▄
                                    ▐███▌▐█•█▌▐█▄▄▌▐█ ▪▐▌ ▐█▌·▐█▄▄▌
                                    ·▀▀▀ .▀  ▀ ▀▀▀  ▀  ▀  ▀▀▀  ▀▀▀\s
                                    """);

                            System.out.print("Informe o nome do Serial Killer: ");
                            String nome = Teclado.getUmString();

                            if (!SerialKillers.cadastrado(nome)) {
                                System.out.print("Informe as armas do Serial Killer: ");
                                String armas = Teclado.getUmString();

                                System.out.print("Informe o número de vezes contratado: ");
                                int vezesContratado = Teclado.getUmInt();

                                System.out.print("Informe a quantidade de mortes confirmadas: ");
                                int mortesConfirmadas = Teclado.getUmInt();

                                System.out.print("Informe o preço por contrato: ");
                                float precoPorContrato = Teclado.getUmFloat();

                                System.out.print("Informe o C.E.P. do endereço (xxxxxxxx): ");
                                int cep = Teclado.getUmInt();

                                Logradouro logradouro = null;

                                while (logradouro == null) {
                                    try {
                                        logradouro = (Logradouro) ClienteWS.getObjeto(
                                                Logradouro.class,
                                                "https://api.postmon.com.br/v1/cep",
                                                Integer.toString(cep));
                                    } catch (Exception erro) {
                                    } // sei que o c.e.p. eh invalido

                                    if (logradouro == null) {
                                        System.err.println("C.E.P. inválido!");
                                        System.err.flush();
                                        System.out.print("Informe o C.E.P. do endereço: ");

                                        cep = Teclado.getUmInt();
                                    }
                                }

                                System.out.println(logradouro);

                                System.out.print("Informe o número do endereço: ");
                                int numero = Teclado.getUmInt();

                                System.out.print("Informe o complemento do endereço: ");
                                String complemento = Teclado.getUmString();

                                SerialKiller serialKiller = new SerialKiller(
                                        UUID.randomUUID().toString(),
                                        nome,
                                        armas,
                                        vezesContratado,
                                        mortesConfirmadas,
                                        precoPorContrato,
                                        cep,
                                        numero,
                                        complemento);

                                try {
                                    SerialKillers.incluir(serialKiller);
                                    System.out.println("\nSerial Killer incluído com sucesso!");
                                } catch (Exception erro) {
                                    System.err.println(erro.getMessage());
                                }
                            }

                            else {
                                System.err.println(
                                        "\nSerial Killer com nome '" + nome + "' já existe:\n");
                                System.err.flush();

                                SerialKiller serialKiller = null;
                                try {
                                    serialKiller = SerialKillers.getSerialKillerPorNome(nome);
                                } catch (Exception erro) {
                                    System.err.println(erro.getMessage());
                                }

                                if (serialKiller != null) {
                                    System.out.println(serialKiller);

                                    Logradouro logradouro = null;
                                    try {
                                        logradouro = (Logradouro) ClienteWS.getObjeto(
                                                Logradouro.class,
                                                "https://api.postmon.com.br/v1/cep",
                                                Integer.toString(serialKiller.getCep()));
                                    } catch (Exception erro) {
                                        System.err.println(erro.getMessage());
                                    }

                                    if (logradouro != null) {
                                        System.out.println(logradouro);
                                    }
                                }
                            }

                            System.out.print("\nDeseja retornar ao menu (S/N)? ");
                            char retornar = Character.toUpperCase(Teclado.getUmChar());

                            if (retornar == 'N') {
                                continuar = false;
                            }

                            else if (retornar != 'S') {
                                System.err.print(
                                        "\nOpção inválida. Você será redirecionado ao menu. Pressione [ENTER] para continuar.");
                                System.err.flush();
                                Teclado.getUmString();
                            }
                        } catch (Exception erro) {
                            System.err.println(erro.getMessage());
                        }
                        break;

                    case '2':
                        try {
                            // Teclado.limparConsole();

                            System.out.println("""

                                    ▄▄▄  ▄▄▄ . ▄▄▄· ·▄▄▄▄ \s
                                    ▀▄ █·▀▄.▀·▐█ ▀█ ██▪ ██\s
                                    ▐▀▀▄ ▐▀▀▪▄▄█▀▀█ ▐█· ▐█▌
                                    ▐█•█▌▐█▄▄▌▐█ ▪▐▌██. ██\s
                                    .▀  ▀ ▀▀▀  ▀  ▀ ▀▀▀▀▀•\s
                                    """);

                            System.out.println("1 - Consultar todos os Serial Killers");
                            System.out.println("2 - Consultar um Serial Killer");
                            System.out.print("\nEscolha uma consulta: ");
                            char consulta = Teclado.getUmChar();

                            if (consulta == '1') {
                                ArrayList<SerialKiller> serialKillers = null;
                                try {
                                    serialKillers = SerialKillers.getSerialKillers();
                                } catch (Exception erro) {
                                    System.err.println(erro.getMessage());
                                }

                                if (serialKillers != null) {
                                    for (SerialKiller serialKiller : serialKillers) {
                                        if (serialKiller != null) {
                                            System.out.println("\n" + serialKiller);

                                            Logradouro logradouro = null;
                                            try {
                                                logradouro = (Logradouro) ClienteWS.getObjeto(
                                                        Logradouro.class,
                                                        "https://api.postmon.com.br/v1/cep",
                                                        Integer.toString(serialKiller.getCep()));
                                            } catch (Exception erro) {
                                                System.err.println(erro.getMessage());
                                            }

                                            if (logradouro != null) {
                                                System.out.println(logradouro);
                                            }
                                        }
                                    }
                                }
                            }

                            else if (consulta == '2') {
                                System.out.print(
                                        "\nInforme o nome ou ID do Serial Killer que deseja consultar: ");
                                String input = Teclado.getUmString();

                                SerialKiller serialKiller = null;
                                try {
                                    UUID id = null;
                                    try {
                                        id = UUID.fromString(input);
                                    } catch (Exception erro) {
                                    } // sei que o input nao eh um UUID

                                    if (id != null) {
                                        serialKiller = SerialKillers.getSerialKillerPorId(id.toString());
                                    }

                                    else {
                                        serialKiller = SerialKillers.getSerialKillerPorNome(input);
                                    }
                                } catch (Exception erro) {
                                    System.err.println(erro.getMessage());
                                }

                                if (serialKiller != null) {
                                    System.out.println("\n" + serialKiller);

                                    Logradouro logradouro = null;
                                    try {
                                        logradouro = (Logradouro) ClienteWS.getObjeto(
                                                Logradouro.class,
                                                "https://api.postmon.com.br/v1/cep",
                                                Integer.toString(serialKiller.getCep()));
                                    } catch (Exception erro) {
                                        System.err.println(erro.getMessage());
                                    }

                                    if (logradouro != null) {
                                        System.out.println(logradouro);
                                    }
                                }

                                else {
                                    System.err
                                            .println("\nSerial Killer com identificador '" + input
                                                    + "' não encontrado!\n");
                                    System.err.flush();
                                }
                            }

                            System.out.print("\nDeseja retornar ao menu (S/N)? ");
                            char retornar = Character.toUpperCase(Teclado.getUmChar());

                            if (retornar == 'N') {
                                continuar = false;
                            }

                            else if (retornar != 'S') {
                                System.err.print(
                                        "\nOpção inválida. Você será redirecionado ao menu. Pressione [ENTER] para continuar.");
                                System.err.flush();
                                Teclado.getUmString();
                            }
                        } catch (Exception erro) {
                            System.err.println(erro.getMessage());
                        }
                        break;

                    case '3':
                        try {
                            // Teclado.limparConsole();

                            System.out.println("""

                                    ▄• ▄▌ ▄▄▄··▄▄▄▄   ▄▄▄· ▄▄▄▄▄▄▄▄ .
                                    █▪██▌▐█ ▄███▪ ██ ▐█ ▀█ •██  ▀▄.▀·
                                    █▌▐█▌ ██▀·▐█· ▐█▌▄█▀▀█  ▐█.▪▐▀▀▪▄
                                    ▐█▄█▌▐█▪·•██. ██ ▐█ ▪▐▌ ▐█▌·▐█▄▄▌
                                     ▀▀▀ .▀   ▀▀▀▀▀•  ▀  ▀  ▀▀▀  ▀▀▀\s
                                    """);

                            System.out.print("Informe o nome ou ID do Serial Killer que deseja alterar: ");
                            String input = Teclado.getUmString();

                            try {
                                UUID id = null;
                                try {
                                    id = UUID.fromString(input);
                                } catch (Exception erro) {
                                } // sei que o input nao eh um UUID

                                SerialKiller serialKiller = null;
                                try {
                                    if (id != null) {
                                        serialKiller = SerialKillers.getSerialKillerPorId(id.toString());
                                    }

                                    else {
                                        serialKiller = SerialKillers.getSerialKillerPorNome(input);
                                    }
                                } catch (Exception erro) {
                                    System.err.println(erro.getMessage());
                                }

                                if (serialKiller != null) {
                                    System.out.println("\n" + serialKiller);

                                    Logradouro logradouro = null;
                                    try {
                                        logradouro = (Logradouro) ClienteWS.getObjeto(
                                                Logradouro.class,
                                                "https://api.postmon.com.br/v1/cep",
                                                Integer.toString(serialKiller.getCep()));
                                        System.out.println(logradouro);
                                    } catch (Exception erro) {
                                        System.err.println(erro.getMessage());
                                    }

                                    if (logradouro != null) {
                                        System.out.println(logradouro);
                                    }

                                    System.out.println(
                                            "\nUso: Pressione [ENTER] nos campos que não devem ser alterados e");
                                    System.out.println(
                                            "informe os novos valores desejados para os campos a serem alterados.\n");

                                    System.out.print("Informe as armas do Serial Killer: ");
                                    String armas = Teclado.getUmString();

                                    System.out.print("Informe o número de vezes contratado: ");
                                    int vezesContratado = 0;
                                    try {
                                        vezesContratado = Teclado.getUmInt();
                                    } catch (Exception erro) {
                                    }

                                    System.out.print("Informe a quantidade de mortes confirmadas: ");
                                    int mortesConfirmadas = 0;
                                    try {
                                        mortesConfirmadas = Teclado.getUmInt();
                                    } catch (Exception erro) {
                                    }

                                    System.out.print("Informe o preço por contrato: ");
                                    float precoPorContrato = 0;
                                    try {
                                        precoPorContrato = Teclado.getUmFloat();
                                    } catch (Exception erro) {
                                    }

                                    System.out.print("Informe o C.E.P. do endereço: ");
                                    int cep = 0;
                                    try {
                                        cep = Teclado.getUmInt();
                                    } catch (Exception erro) {
                                    }

                                    if (cep != 0) {
                                        logradouro = null;
                                        while (logradouro == null) {
                                            try {
                                                logradouro = (Logradouro) ClienteWS.getObjeto(
                                                        Logradouro.class,
                                                        "https://api.postmon.com.br/v1/cep",
                                                        Integer.toString(cep));
                                            } catch (Exception erro) {
                                            }

                                            if (logradouro == null) {
                                                System.err.println("C.E.P. inválido!");
                                                System.err.flush();
                                                System.out.print("Informe o C.E.P. do endereço: ");
                                                cep = Teclado.getUmInt();
                                            }
                                        }

                                        System.out.println(logradouro);
                                    }

                                    System.out.print("Informe o número do endereço: ");
                                    int numero = 0;
                                    try {
                                        numero = Teclado.getUmInt();
                                    } catch (Exception erro) {
                                    }

                                    System.out.print("Informe o complemento do endereço: ");
                                    String complemento = Teclado.getUmString();

                                    serialKiller.setArmas(
                                            armas.equals("")
                                                    ? serialKiller.getArmas()
                                                    : armas);
                                    serialKiller.setVezesContratado(
                                            vezesContratado == 0
                                                    ? serialKiller.getVezesContratado()
                                                    : vezesContratado);
                                    serialKiller.setMortesConfirmadas(
                                            mortesConfirmadas == 0
                                                    ? serialKiller.getMortesConfirmadas()
                                                    : mortesConfirmadas);
                                    serialKiller.setPrecoPorContrato(
                                            precoPorContrato == 0
                                                    ? serialKiller.getPrecoPorContrato()
                                                    : precoPorContrato);
                                    serialKiller.setCep(
                                            cep == 0
                                                    ? serialKiller.getCep()
                                                    : cep);
                                    serialKiller.setNumero(
                                            numero == 0
                                                    ? serialKiller.getNumero()
                                                    : numero);
                                    serialKiller.setComplemento(
                                            complemento.equals("")
                                                    ? serialKiller.getComplemento()
                                                    : complemento);

                                    try {
                                        SerialKillers.alterar(serialKiller);
                                        System.out.println("\nSerial Killer atualizado com sucesso!");
                                    } catch (Exception erro) {
                                        System.err.println(erro.getMessage());
                                    }
                                }

                                else {
                                    System.err.println(
                                            "\nSerial Killer com identificador '" + input + "' não encontrado!\n");
                                    System.err.flush();
                                }
                            } catch (Exception erro) {
                                System.err.println(erro.getMessage());
                            }

                            System.out.print("\nDeseja retornar ao menu (S/N)? ");
                            char retornar = Character.toUpperCase(Teclado.getUmChar());

                            if (retornar == 'N') {
                                continuar = false;
                            }

                            else if (retornar != 'S') {
                                System.err.print(
                                        "\nOpção inválida. Você será redirecionado ao menu. Pressione [ENTER] para continuar.");
                                System.err.flush();
                                Teclado.getUmString();
                            }
                        } catch (Exception erro) {
                            System.err.println(erro.getMessage());
                        }
                        break;

                    case '4':
                        try {
                            // Teclado.limparConsole();

                            System.out.println("""

                                    ·▄▄▄▄  ▄▄▄ .▄▄▌  ▄▄▄ .▄▄▄▄▄▄▄▄ .
                                    ██▪ ██ ▀▄.▀·██•  ▀▄.▀·•██  ▀▄.▀·
                                    ▐█· ▐█▌▐▀▀▪▄██▪  ▐▀▀▪▄ ▐█.▪▐▀▀▪▄
                                    ██. ██ ▐█▄▄▌▐█▌▐▌▐█▄▄▌ ▐█▌·▐█▄▄▌
                                    ▀▀▀▀▀•  ▀▀▀ .▀▀▀  ▀▀▀  ▀▀▀  ▀▀▀\s
                                    """);

                            System.out.print("Informe o nome ou ID do Serial Killer que deseja excluir: ");
                            String input = Teclado.getUmString();

                            try {
                                UUID id = null;
                                try {
                                    id = UUID.fromString(input);
                                } catch (Exception erro) {
                                } // sei que o input nao eh um UUID

                                SerialKiller serialKiller = null;
                                try {
                                    if (id != null) {
                                        serialKiller = SerialKillers.getSerialKillerPorId(id.toString());
                                    }

                                    else {
                                        serialKiller = SerialKillers.getSerialKillerPorNome(input);
                                    }
                                } catch (Exception erro) {
                                    System.err.println(erro.getMessage());
                                }

                                if (serialKiller != null) {
                                    System.out.println("\n" + serialKiller);

                                    Logradouro logradouro = null;
                                    try {
                                        logradouro = (Logradouro) ClienteWS.getObjeto(
                                                Logradouro.class,
                                                "https://api.postmon.com.br/v1/cep",
                                                Integer.toString(serialKiller.getCep()));
                                    } catch (Exception erro) {
                                        System.err.println(erro.getMessage());
                                    }

                                    if (logradouro != null) {
                                        System.out.println(logradouro);
                                    }

                                    System.out.print(
                                            "\nDigite '" + serialKiller.getNome() + "' para confirmar a exclusão: ");
                                    String nome = Teclado.getUmString();

                                    if (nome.equals(serialKiller.getNome())) {
                                        try {
                                            SerialKillers.excluir(nome);
                                            System.out.println("\nSerial Killer excluído com sucesso!");
                                        } catch (Exception erro) {
                                            System.err.println(erro.getMessage());
                                        }
                                    }

                                    else {
                                        System.err.println("Exclusão cancelada!\n");
                                        System.err.flush();
                                    }
                                }

                                else {
                                    System.err.println(
                                            "\nSerial Killer com identificador '" + input + "' não encontrado!\n");
                                    System.err.flush();
                                }
                            } catch (Exception erro) {
                                System.err.println(erro.getMessage());
                            }

                            System.out.print("\nDeseja retornar ao menu (S/N)? ");
                            char retornar = Character.toUpperCase(Teclado.getUmChar());

                            if (retornar == 'N') {
                                continuar = false;
                            }

                            else if (retornar != 'S') {
                                System.err.print(
                                        "\nOpção inválida. Você será redirecionado ao menu. Pressione [ENTER] para continuar.");
                                System.err.flush();
                                Teclado.getUmString();
                            }
                        } catch (Exception erro) {
                            System.err.println(erro.getMessage() + "\n");
                        }
                        break;

                    default:
                        System.err.println("Opção inválida!\n");
                        System.err.flush();
                        break;
                }
            } catch (Exception erro) {
                System.err.println(erro.getMessage() + "\n");
            }
        }
    }
}
