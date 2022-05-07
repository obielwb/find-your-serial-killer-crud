package com.company.bd;

import com.company.bd.core.*;

public class BDPostgreSQL
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
        MeuPreparedStatement comando = null;

        try
        {
            String host = "";
            String port = "";
            String database = "";
            String user = "";
            String password = "";

            String uri = "postgresql://" + host + ":" + port + "/" + database;

            comando =
                    new MeuPreparedStatement (
                            "org.postgresql.Driver",
                            "jdbc:" + uri,
                            user, password);
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }

        COMANDO = comando;
    }
}
