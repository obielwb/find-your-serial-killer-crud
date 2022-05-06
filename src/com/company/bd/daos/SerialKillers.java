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
            throw new Exception ("Erro ao procurar livro");
        }

        return retorno;
    }

    public static void incluir (SerialKiller serialKiller) throws Exception
    {
        if (serialKiller==null)
            throw new Exception ("Serial Killer nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO SerialKillers " +
                    "(NOME) " +
                    "VALUES " +
                    "(?)";

            BDMySQL.COMANDO.prepareStatement (sql);

            BDMySQL.COMANDO.setString (1, serialKiller.getNome());

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

            sql = "DELETE FROM LIVROS " +
                    "WHERE CODIGO=?";

            BDMySQL.COMANDO.prepareStatement (sql);

            BDMySQL.COMANDO.setInt (1, id);

            BDMySQL.COMANDO.executeUpdate ();
            BDMySQL.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            BDMySQL.COMANDO.rollback();
            throw new Exception ("Erro ao excluir livro");
        }
    }

    public static void alterar (SerialKiller serialKiller) throws Exception
    {
        if (serialKiller==null)
            throw new Exception ("Serial Killer nao fornecido");

        if (!cadastrado (serialKiller.getId()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE LIVROS " +
                    "SET NOME=? " +
                    "SET PRECO=? " +
                    "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, livro.getNome ());
            BDSQLServer.COMANDO.setFloat  (2, livro.getPreco ());
            BDSQLServer.COMANDO.setInt    (3, livro.getCodigo ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de livro");
        }
    }

    public static Livro getLivro (int codigo) throws Exception
    {
        Livro livro = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM LIVROS " +
                    "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            livro = new Livro (resultado.getInt   ("CODIGO"),
                    resultado.getString("NOME"),
                    resultado.getFloat ("PRECO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
        }

        return livro;
    }

    public static MeuResultSet getLivros () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM LIVROS";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar livros");
        }

        return resultado;
    }
}