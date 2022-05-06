package com.company.main;

import java.io.IOException;

public class Programa
{
    public static void main (String[] args) throws IOException {
        boolean continuar = true;

        System.out.println("""

                .▄▄ · ▄▄▄ .▄▄▄  ▪   ▄▄▄· ▄▄▌      ▄ •▄ ▪  ▄▄▌  ▄▄▌  ▄▄▄ .▄▄▄       ▄▄· ▄▄▄  ▄• ▄▌·▄▄▄▄ \s
                ▐█ ▀. ▀▄.▀·▀▄ █·██ ▐█ ▀█ ██•      █▌▄▌▪██ ██•  ██•  ▀▄.▀·▀▄ █·    ▐█ ▌▪▀▄ █·█▪██▌██▪ ██\s
                ▄▀▀▀█▄▐▀▀▪▄▐▀▀▄ ▐█·▄█▀▀█ ██▪      ▐▀▀▄·▐█·██▪  ██▪  ▐▀▀▪▄▐▀▀▄     ██ ▄▄▐▀▀▄ █▌▐█▌▐█· ▐█▌
                ▐█▄▪▐█▐█▄▄▌▐█•█▌▐█▌▐█ ▪▐▌▐█▌▐▌    ▐█.█▌▐█▌▐█▌▐▌▐█▌▐▌▐█▄▄▌▐█•█▌    ▐███▌▐█•█▌▐█▄█▌██. ██\s
                 ▀▀▀▀  ▀▀▀ .▀  ▀▀▀▀ ▀  ▀ .▀▀▀     ·▀  ▀▀▀▀.▀▀▀ .▀▀▀  ▀▀▀ .▀  ▀    ·▀▀▀ .▀  ▀ ▀▀▀ ▀▀▀▀▀•\s
                """);

        while (continuar)
        {
            System.out.println("0- Sair");
            System.out.println("1 - Cadastrar um novo Serial Killer");
            System.out.println("2 - Consultar portfólio do Serial Killer");
            System.out.println("3 - Atualizar portfólio do Serial Killer");
            System.out.println("4 - Excluir Serial Killer \n");
            System.out.println("Escolha qual opção deseja realizar: ");
            String opcao = Teclado.getUmString().substring(0, 1);


            switch (opcao) {
                case "1":
                    System.out.println("adsfasdf");
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
                    continuar = false;
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
            }

        }
    }
}
