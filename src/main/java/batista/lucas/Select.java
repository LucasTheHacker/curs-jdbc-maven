package batista.lucas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import batista.lucas.db.DB;
import batista.lucas.db.DbException;

public class Select {

    public static void main(String[] args) {

        Connection conn = null;   //conexao
        Statement statement = null;   //consulta
        ResultSet resultSet = null;   //resultado da consulta

        try {
            conn = DB.getConnection();

            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT * from department");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
            DB.closeConnection();
        }

    }
}
