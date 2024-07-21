package com.example.javafxdemo.metiers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoArticle implements CRUD<Article,String>{
    Connection MyCon = DaoFactory.getConnection();
    @Override
    public boolean Create(Article art) {
        String Requete;
        Requete = "insert into Article(Code,Designation,Prix) values(?,?,?)";
        PreparedStatement Pst;
        try {
            Pst = MyCon.prepareStatement(Requete);
            Pst.setString(1, art.getCode());
            Pst.setString(2, art.getDesignation());
            Pst.setFloat(3, art.getPrix());
            int Lig = Pst.executeUpdate();
            if (Lig == 1)
                System.out.println(" JDBC.Article added with success");
            else
                System.out.println(" JDBC.Article added Failed !!!");
            return true ;
        } catch (SQLException ex) {
            System.out.println(" Error in Query Create : " + ex.getMessage());
            return false ;
        }
    }
    @Override
    public List<Article> All() {
        List<Article> articles = new ArrayList<Article>();
        try {
            Statement st = MyCon.createStatement();
            ResultSet rs = st.executeQuery("select * from article;");
            while(rs.next())
            {
                articles.add(new Article(rs.getString(1),
                        rs.getString(2),rs.getFloat(3)));
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return articles;
    }
    @Override
    public Optional<Article> Read(String Id) {
        String sql = "SELECT * FROM Article WHERE code = ?";
        ResultSet rs;
        try {
            PreparedStatement Pst= MyCon.prepareStatement(sql);
            Pst.setString(1, Id);
            rs = Pst.executeQuery();
            while(rs.next()){
                System.out.println(" article found ");
             /*   Article article=Article.builder()
                        .code(rs.getString(1))
                        .designation(rs.getString(2))
                        .prix(rs.getFloat(3))
                        .build();*/
                //return Optional.of(article);
            }
        }
        catch (SQLException e) {
            System.err.println("Error in the request getarticle"+e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public boolean Update(Article article, String Id) {
        String query = "update article set designation = ?, prix = ? where code = ?" ;
        try {
            PreparedStatement pst = MyCon.prepareStatement(query);
            pst.setString(1, article.getDesignation());
            pst.setFloat(2, article.getPrix());
            pst.setString(3, Id);
            pst.executeUpdate();
            return true ;
        }
        catch(SQLException ex)
        {
            System.err.println("Error in the request UPDATE"+ex.getMessage());
        }
        return false ;
    }
    @Override
    public boolean Delete(String Id) {
        String sql = "DELETE FROM Article WHERE code = ?";
        PreparedStatement Pst;
        try {
            Pst = MyCon.prepareStatement(sql);
            Pst.setString(1, Id);
            int Lig= Pst.executeUpdate();
            if (Lig != 0)
                System.out.println(Lig + " article delete with success");
            else
                System.out.println("No article delete");
            return true ;
        } catch (SQLException e) {
            System.err.println("Error in the request delete article " + e.getMessage());
        }return false ;
    }
    @Override
    public Long Count() {
        String sql = "select count(*) as taille from Article ;";
        Statement St ;
        ResultSet resultSet;
        try
        {
            St = MyCon.createStatement();
            resultSet = St.executeQuery(sql);
            if(resultSet.next()) return resultSet.getLong(1);
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0L ;
    }
}
