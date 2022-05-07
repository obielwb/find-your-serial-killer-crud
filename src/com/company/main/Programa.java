package com.company.main;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

import com.company.bd.core.MeuResultSet;
import com.company.bd.daos.SerialKillers;
import com.company.bd.dbos.SerialKiller;

public class Programa
{
    public static void main (String[] args) throws IOException {
        boolean continuar = true;

        /* try {
            SerialKiller zodiac = new SerialKiller(
                    UUID.randomUUID().toString(),
                    "Zodiac",
                    "knife:gun",
                    32,
                    30,
                    0,
                    13033205,
                    1050,
                    "Block 4"
            );

            SerialKillers.incluir(zodiac);

            zodiac.setArmas("gun");
            SerialKillers.alterar(zodiac);

            System.out.println(SerialKillers.getSerialKiller(zodiac.getId()));

            Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(
                    Logradouro.class,
                    "https://api.postmon.com.br/v1/cep",
                    Integer.toString(zodiac.getCep())
            );

            System.out.println (logradouro);

            SerialKillers.excluir(zodiac.getId());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } */

        System.out.println("""

                .▄▄ · ▄▄▄ .▄▄▄  ▪   ▄▄▄· ▄▄▌      ▄ •▄ ▪  ▄▄▌  ▄▄▌  ▄▄▄ .▄▄▄       ▄▄· ▄▄▄  ▄• ▄▌·▄▄▄▄ \s
                ▐█ ▀. ▀▄.▀·▀▄ █·██ ▐█ ▀█ ██•      █▌▄▌▪██ ██•  ██•  ▀▄.▀·▀▄ █·    ▐█ ▌▪▀▄ █·█▪██▌██▪ ██\s
                ▄▀▀▀█▄▐▀▀▪▄▐▀▀▄ ▐█·▄█▀▀█ ██▪      ▐▀▀▄·▐█·██▪  ██▪  ▐▀▀▪▄▐▀▀▄     ██ ▄▄▐▀▀▄ █▌▐█▌▐█· ▐█▌
                ▐█▄▪▐█▐█▄▄▌▐█•█▌▐█▌▐█ ▪▐▌▐█▌▐▌    ▐█.█▌▐█▌▐█▌▐▌▐█▌▐▌▐█▄▄▌▐█•█▌    ▐███▌▐█•█▌▐█▄█▌██. ██\s
                 ▀▀▀▀  ▀▀▀ .▀  ▀▀▀▀ ▀  ▀ .▀▀▀     ·▀  ▀▀▀▀.▀▀▀ .▀▀▀  ▀▀▀ .▀  ▀    ·▀▀▀ .▀  ▀ ▀▀▀ ▀▀▀▀▀•\s
                """);

        while (continuar)
        {
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar um novo Serial Killer");
            System.out.println("2 - Consultar portfólio do Serial Killer");
            System.out.println("3 - Atualizar portfólio do Serial Killer");
            System.out.println("4 - Excluir Serial Killer \n");
            System.out.print("Escolha qual opção deseja realizar: ");
            String opcao = Teclado.getUmString().substring(0, 1);

            switch (opcao) {
                case "1":
                    try
                    {
                        Teclado.limparConsole();

                        System.out.println("Informe o nome do Serial Killer: ");
                        String nome = Teclado.getUmString().toLowerCase().trim();

                        System.out.println("Informe as armas do Serial Killer: ");
                        String armas = Teclado.getUmString().toLowerCase().trim();

                        System.out.println("Informe o número de vezes contratado: ");
                        int vezesContratado = Teclado.getUmInt();

                        System.out.println("Informe a quantidade de mortes confirmadas: ");
                        int mortes = Teclado.getUmInt();
                        
                        System.out.println("Informe o preço por contrato: ");
                        double precoPorContrato = Teclado.getUmDouble();
                        
                        System.out.println("Informe o CEP: ");
                        int cep = Teclado.getUmInt();
                        
                        System.out.println("Informe o número da casa: ");
                        int numero = Teclado.getUmInt();
                        
                        System.out.println("Informe o logradouro: ");
                        String logradouro = Teclado.getUmString().toLowerCase().trim();
                        

                        SerialKiller novoSerialKiller = new SerialKiller(
                            UUID.randomUUID().toString(),
                            nome,
                            armas,
                            vezesContratato,
                            mortesConfirmadas,
                            precoPorContrato,
                            cep,
                            numero,
                            logradouro
                        );
                        
                        SerialKiller.incluir(novoSerialKiller);
                        
                        System.out.println("Serial Killer incluído com sucesso");

                        System.out.print("\nDeseja retornar ao menu (S/N)? ");
                        String retornar = Teclado.getUmString().toUpperCase().substring(0, 1);

                        if (retornar == "N")
                        {
                            continuar = false;
                            System.exit(0);
                        }

                        else if (retornar != "S")
                        {
                            System.out.println("Opção inválida. Você será direcionado ao menu inicial.");
                        }

                        System.out.println("[Aperte qualquer tecla para continuar]");
                        Scanner in = new Scanner(System.in);
                        in.next();
                        
                        Teclado.limparConsole();
                    }
                    catch (Exception erro)
                    {
                        System.err.println (erro.getMessage());
                    }
                case "2":
                    try
                    {
                        Teclado.limparConsole();
                        System.out.println("Informe o nome do Serial Killer que deseja consultar: ");
                        String serialKiller = Teclado.getUmString().toLowerCase().trim();

                        System.out.println(SerialKillers.getSerialKiller(serialKiller));

                        Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(
                                Logradouro.class,
                                "https://api.postmon.com.br/v1/cep",
                                "13033205");
                        System.out.println (logradouro);

                        System.out.print("\nDeseja retornar ao menu (S/N)? ");
                        String retornar = Teclado.getUmString().toUpperCase().substring(0, 1);

                        if (retornar == "N")
                        {
                            continuar = false;
                            System.exit(0);
                        }

                        else if (retornar != "S")
                        {
                            System.out.println("Opção inválida. Você será direcionado ao menu inicial.");
                        }

                        System.out.println("[Aperte qualquer tecla para continuar]");
                        Scanner in = new Scanner(System.in);
                        in.next();
                        
                        Teclado.limparConsole();
                    }
                    catch (Exception erro)
                    {
                        System.err.println (erro.getMessage());
                    }
                case "3":
                    try
                    {
                        Teclado.limparConsole();
                        
                        System.out.println("Digite o nome do Serial Killer o qual deseja alterar as informações: ");
                        String nomeDoSerialKiller = Teclado.getUmString().toLowerCase().trim();
                        
                        SerialKiller serialKillerInfo = SerialKillers.getSerialKiller(nomeDoSerialKiller);
                        
                        System.out.println("Escolha o campo que deseja mudar: ");
                        System.out.println("1 - Nome....................");
                        System.out.println("2 - Armas..................."); 
                        System.out.println("3 - Vezes contratado........");
                        System.out.println("4 - Mortes confirmadas......");
                        System.out.println("5 - Preço por contrato......");
                        System.out.println("6 - CEP.....................");
                        System.out.println("7 - Número..................");
                        System.out.println("8 - Logradouro..............");
                        
                        
                    }
                    catch (Exception erro)
                    {
                        System.err.println (erro.getMessage());
                    }
                case "4":
                    try
                    {

                    }
                    catch (Exception erro)
                    {
                        System.err.println (erro.getMessage());
                    }
                case "0":
                    continuar = false;
            }

        }
    }
}
