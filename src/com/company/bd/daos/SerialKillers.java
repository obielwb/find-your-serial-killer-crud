package com.company.bd.daos;

import java.sql.*;
import com.company.bd.*;
import com.company.bd.core.*;
import com.company.bd.dbos.*;

public class SerialKillers
{
    public static boolean cadastrado (int id) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM SerialKillers " +
                    "WHERE id = ?";

            BDMySQL.COMANDO.prepareStatement (sql);

            BDMySQL.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BDMySQL.COMANDO.executeQuery ();

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

            sql = "INSERT INTO SerialKillers " +
                    "(nome, armas, vezesContratado, mortesConfirmadas, precoPorContrato, cep, numero, complemento) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?)";

            BDMySQL.COMANDO.prepareStatement (sql);

            BDMySQL.COMANDO.setString (1, serialKiller.getNome());
            BDMySQL.COMANDO.setString(2, serialKiller.getArmas());
            BDMySQL.COMANDO.setInt(3, serialKiller.getVezesContratado());
            BDMySQL.COMANDO.setInt(4, serialKiller.getMortesConfirmadas());
            BDMySQL.COMANDO.setFloat(5, serialKiller.getPrecoPorContrato());
            BDMySQL.COMANDO.setInt(6, serialKiller.getCep());
            BDMySQL.COMANDO.setInt(7, serialKiller.getNumero());
            BDMySQL.COMANDO.setString(8, serialKiller.getComplemento());

            BDMySQL.COMANDO.executeUpdate ();
            BDMySQL.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDMySQL.COMANDO.rollback();
            throw new Exception ("Erro ao inserir livro");
        }
    }

    public static void excluir (int id) throws Exception
    {
        if (!cadastrado (id))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM SerialKillers " +
                    "WHERE id=?";

            BDMySQL.COMANDO.prepareStatement (sql);

            BDMySQL.COMANDO.setInt (1, id);

            BDMySQL.COMANDO.executeUpdate ();
            BDMySQL.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            BDMySQL.COMANDO.rollback();
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

            sql = "UPDATE SerialKillers " +
                    "SET nome=? " +
                    "SET armas=? " +
                    "SET vezesContratado=? " +
                    "SET mortesConfirmadas=? " +
                    "SET precoPorContrato=? " +
                    "SET cep=? " +
                    "SET numero=? " +
                    "SET complemento=? " +
                    "WHERE id = ?";

            BDMySQL.COMANDO.prepareStatement (sql);

            BDMySQL.COMANDO.setString (1, serialKiller.getNome());
            BDMySQL.COMANDO.setString(2, serialKiller.getArmas());
            BDMySQL.COMANDO.setInt(3, serialKiller.getVezesContratado());
            BDMySQL.COMANDO.setInt(4, serialKiller.getMortesConfirmadas());
            BDMySQL.COMANDO.setFloat(5, serialKiller.getPrecoPorContrato());
            BDMySQL.COMANDO.setInt(6, serialKiller.getCep());
            BDMySQL.COMANDO.setInt(7, serialKiller.getNumero());
            BDMySQL.COMANDO.setString(8, serialKiller.getComplemento());
            BDMySQL.COMANDO.setInt (9, serialKiller.getId());

            BDMySQL.COMANDO.executeUpdate ();
            BDMySQL.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDMySQL.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de serial killer");
        }
    }

    public static SerialKiller getSerialKiller (int id) throws Exception
    {
        SerialKiller serialKiller = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM SerialKillers " +
                    "WHERE id = ?";

            BDMySQL.COMANDO.prepareStatement (sql);

            BDMySQL.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BDMySQL.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            serialKiller = new SerialKiller (
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
                    "FROM SerialKillers";

            BDMySQL.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDMySQL.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar serial killer");
        }

        return resultado;
    }
}