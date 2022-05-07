package com.company.main;

import java.io.IOException;
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
                        Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", "13033205");
                        System.out.println (logradouro);
                        continuar = false;
                    }
                    catch (Exception erro)
                    {
                        System.err.println (erro.getMessage());
                    }
                case "2":
                    try
                    {

                    }
                    catch (Exception erro)
                    {
                        System.err.println (erro.getMessage());
                    }
                case "3":
                    try
                    {

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
