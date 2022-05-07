package com.company.bd.daos;

import java.sql.*;
import com.company.bd.*;
import com.company.bd.core.*;
import com.company.bd.dbos.*;

public class SerialKillers
{
    public static boolean cadastrado (String id) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM \"SerialKillers\" " +
                    "WHERE id = ?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, id);

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

    public static void excluir (String id) throws Exception
    {
        if (!cadastrado (id))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM \"SerialKillers\" " +
                    "WHERE id=?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, id);

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

        if (!cadastrado (serialKiller.getId()))
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

    public static SerialKiller getSerialKiller (String id) throws Exception
    {
        SerialKiller serialKiller = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM \"SerialKillers\" " +
                    "WHERE id = ?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, id);

            MeuResultSet resultado = (MeuResultSet) BDPostgreSQL.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            serialKiller = new SerialKiller (
                    id,
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

    public static MeuResultSet getSerialKillers () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM \"SerialKillers\"";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet) BDPostgreSQL.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar serial killer");
        }

        return resultado;
    }
}