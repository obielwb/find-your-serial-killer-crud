package com.company.bd.daos;

import java.sql.*;
import java.util.ArrayList;

import com.company.bd.*;
import com.company.bd.core.*;
import com.company.bd.dbos.*;

public class SerialKillers
{
    public static boolean cadastrado (String nome) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM \"SerialKillers\" " +
                    "WHERE nome=?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, nome);

            MeuResultSet resultado = (MeuResultSet) BDPostgreSQL.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar serial killer");
        }

        return retorno;
    }

    public static void incluir (SerialKiller serialKiller) throws Exception
    {
        if (serialKiller==null)
            throw new Exception ("Serial killer nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO \"SerialKillers\" " +
                    "(id, nome, armas, \"vezesContratado\", \"mortesConfirmadas\", \"precoPorContrato\", cep, numero, complemento) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString(1, serialKiller.getId());
            BDPostgreSQL.COMANDO.setString (2, serialKiller.getNome());
            BDPostgreSQL.COMANDO.setString(3, serialKiller.getArmas());
            BDPostgreSQL.COMANDO.setInt(4, serialKiller.getVezesContratado());
            BDPostgreSQL.COMANDO.setInt(5, serialKiller.getMortesConfirmadas());
            BDPostgreSQL.COMANDO.setFloat(6, serialKiller.getPrecoPorContrato());
            BDPostgreSQL.COMANDO.setInt(7, serialKiller.getCep());
            BDPostgreSQL.COMANDO.setInt(8, serialKiller.getNumero());
            BDPostgreSQL.COMANDO.setString(9, serialKiller.getComplemento());

            BDPostgreSQL.COMANDO.executeUpdate ();
            BDPostgreSQL.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDPostgreSQL.COMANDO.rollback();
            throw new Exception ("Erro ao inserir serial killer");
        }
    }

    public static void excluir (String nome) throws Exception
    {
        if (!cadastrado (nome))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM \"SerialKillers\" " +
                    "WHERE nome=?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, nome);

            BDPostgreSQL.COMANDO.executeUpdate ();
            BDPostgreSQL.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            BDPostgreSQL.COMANDO.rollback();
            throw new Exception ("Erro ao excluir serial killer");
        }
    }

    public static void alterar (SerialKiller serialKiller) throws Exception
    {
        if (serialKiller==null)
            throw new Exception ("Serial killer nao fornecido");

        if (!cadastrado (serialKiller.getNome()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE \"SerialKillers\" " +
                    "SET (nome, armas, \"vezesContratado\", \"mortesConfirmadas\", \"precoPorContrato\", cep, numero, complemento)=" +
                    "(?, ?, ?, ?, ?, ?, ?, ?)" +
                    "WHERE id=?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, serialKiller.getNome());
            BDPostgreSQL.COMANDO.setString(2, serialKiller.getArmas());
            BDPostgreSQL.COMANDO.setInt(3, serialKiller.getVezesContratado());
            BDPostgreSQL.COMANDO.setInt(4, serialKiller.getMortesConfirmadas());
            BDPostgreSQL.COMANDO.setFloat(5, serialKiller.getPrecoPorContrato());
            BDPostgreSQL.COMANDO.setInt(6, serialKiller.getCep());
            BDPostgreSQL.COMANDO.setInt(7, serialKiller.getNumero());
            BDPostgreSQL.COMANDO.setString(8, serialKiller.getComplemento());
            BDPostgreSQL.COMANDO.setString (9, serialKiller.getId());

            BDPostgreSQL.COMANDO.executeUpdate ();
            BDPostgreSQL.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDPostgreSQL.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de serial killer");
        }
    }

    public static SerialKiller getSerialKillerPorNome(String nome) throws Exception
    {
        SerialKiller serialKiller = null;

        try
        {
            serialKiller = getSerialKiller("nome", nome);
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro ao procurar serial killer");
        }

        return serialKiller;
    }

    public static SerialKiller getSerialKillerPorId(String id) throws Exception
    {
        SerialKiller serialKiller = null;

        try
        {
            serialKiller = getSerialKiller("id", id);
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro ao procurar serial killer");
        }

        return serialKiller;
    }

    public static SerialKiller getSerialKiller (String parameter, String value) throws Exception
    {
        SerialKiller serialKiller = null;

        try
        {
            String sql;

            sql = String.format("SELECT * " +
                    "FROM \"SerialKillers\" " +
                    "WHERE %s=?", parameter);

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, value);

            MeuResultSet resultado = (MeuResultSet) BDPostgreSQL.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            serialKiller = new SerialKiller (
                    resultado.getString("id"),
                    resultado.getString("nome"),
                    resultado.getString("armas"),
                    resultado.getInt("vezesContratado"),
                    resultado.getInt("mortesConfirmadas"),
                    resultado.getFloat("precoPorContrato"),
                    resultado.getInt("cep"),
                    resultado.getInt("numero"),
                    resultado.getString("complemento")
            );
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar serial killer");
        }

        return serialKiller;
    }


    public static ArrayList<SerialKiller> getSerialKillers () throws Exception
    {
        ArrayList<SerialKiller> serialKillers = new ArrayList<>();

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM \"SerialKillers\"";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet) BDPostgreSQL.COMANDO.executeQuery ();

            while (resultado.next())
            {
                SerialKiller serialKiller = new SerialKiller(
                        resultado.getString("id"),
                        resultado.getString("nome"),
                        resultado.getString("armas"),
                        resultado.getInt("vezesContratado"),
                        resultado.getInt("mortesConfirmadas"),
                        resultado.getFloat("precoPorContrato"),
                        resultado.getInt("cep"),
                        resultado.getInt("numero"),
                        resultado.getString("complemento")
                );

                serialKillers.add(serialKiller);
            }
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar serial killer");
        }

        return serialKillers;
    }
}