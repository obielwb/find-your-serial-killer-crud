package com.company.bd;

import com.company.bd.core.*;

public class BDMySQL
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
        MeuPreparedStatement comando = null;

        try
        {
            comando = new MeuPreparedStatement (
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://" + System.getenv("DATABASE_HOST") +
                            ":" + System.getenv("DATABASE_PORT") +
                            "/" + System.getenv("DATABASE") + "?sslaccept=strict",
                    System.getenv("DATABASE_USER"), System.getenv("DATABASE_PASSWORD"));
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }

        COMANDO = comando;
    }
}