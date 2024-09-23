// Source code is decompiled from a .class file using FernFlower decompiler.
//[bibliotecas]
package dao;

import model.*;

import java.sql.*;

//[cabecalho]
/**
 *@author: matheusEduardoCamposSoares
 *@data: 21.09.2024 
**/

//[codigo]
public class comentarioDAO extends DAO 
{
    public comentarioDAO() 
    {
        this.Conectar();
    }
  
    public void finalize() 
    {
        this.close();
    }

    private int maxId = 0;

    //Retorna o id do ultimo comentario inserido no banco de dados
    public int getMaxId() 
    {
        try 
        {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MAX(id) as maxId FROM comentario"; // Alias para facilitar a leitura do valor
    
            ResultSet rs = st.executeQuery(sql); // Usa executeQuery para comandos SELECT
            if (rs.next()) 
            { // Verifica se o resultado contém ao menos uma linha
                maxId = rs.getInt("maxId"); // Obtem o valor usando o alias
            }

            rs.close(); // Fecha o ResultSet
            st.close(); // Fecha o Statement
        } 
        catch (SQLException u) 
        {
            u.printStackTrace();
        }
        
        return maxId; // Retorna o valor de maxId
    }    
     
    //inserir comentario sobre animal
    public boolean inserirComentario(Comentario comentario) 
    {
        boolean status = false;
        try 
        {
            Statement st = conexao.createStatement();
            
            // Alterando a consulta SQL para inserir o comentário na tabela 'comentario'
            String sql = "INSERT INTO comentario (id, conteudo, id_animal, id_pessoa) " +
                         "VALUES (" +
                         comentario.getId() + ", '" +
                         comentario.getConteudo() + "', '" +
                         comentario.getanimalId() + "', '" +
                         comentario.getpessoaId() + "', " + ")";
            
            st.executeUpdate(sql);
            st.close();
            status = true;
        } 
        catch (SQLException u) 
        {
            u.printStackTrace();
        }
        return status;
    }

    //atualiza comentario feito sobre animal
    public boolean atualizarComentario(Comentario comentario) 
    {
        boolean status = false;
        try 
        {
            Statement st = conexao.createStatement();
            String sql = "UPDATE comentario SET " +
                            "id = " + comentario.getId() + ", '" +
                            "conteudo = " + comentario.getConteudo() + "', '" +
                            "id_animal = " + comentario.getanimalId() + "', '" +
                            "id_pessoa = " + comentario.getpessoaId() + "', " + ")";
            
            st.executeUpdate(sql);
            st.close();
            status = true;
        } 
        catch (SQLException u) 
        {
            u.printStackTrace();
        }
        return status;
    }
    
    //Exclui o comentario pertencente ao id informado
    public boolean excluirComentario(int id)
    {
        boolean status = false;
        try 
        {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM comentario WHERE id = " + id);
            st.close();
            status = true;
        } 
        catch(SQLException u)
        {
            throw new RuntimeException(u);
        }
        return status;
    }    
}

//[comentarios]
/*
eu nao sei muito bem se esta certo, ainda fiquei com muitas duvidas
quanto a como eu faria principalmente com as chaves estrangeiras, acho que
nao e a forma ideal de ser feito.
*/
