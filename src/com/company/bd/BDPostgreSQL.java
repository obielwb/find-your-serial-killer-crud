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
            String host = "ec2-52-200-215-149.compute-1.amazonaws.com";
            String port = "5432";
            String database = "d8ocpdmflioatm";
            String user = "fsfkwstqceqnli";
            String password = "a6ae9b0fc0d19df9164501610161a1041ea1030eb88881abf0524e08a2464566";

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