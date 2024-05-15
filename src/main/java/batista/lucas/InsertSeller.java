package batista.lucas;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import batista.lucas.db.DB;

public class InsertSeller {

    public static void main(String[] args) {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    try {
        conn = DB.getConnection();

        preparedStatement = conn.prepareStatement(
                "INSERT INTO seller "
                + "(name, email, birthdate, basesalary, departmentid) "
                + "VALUES"
                + "(?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS); //place holder
    
        preparedStatement.setString(1, "Fulano das Couves");
        preparedStatement.setString(2, "fulanocouves@email.com");
        preparedStatement.setDate(3,new java.sql.Date(sdf.parse("25-06-2004 00:00:00").getTime()));
        preparedStatement.setDouble(4, 3700);
        preparedStatement.setInt(5, 4);

        int rowsAffected = preparedStatement.executeUpdate(); //retorna quantidade de linhas alteradas

        if (rowsAffected > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                System.out.println("Pronto, sua id eh: " + id);
            }
        }
        else {
            System.out.println("No rows affected!");
        }

        //System.out.println("Tudo 2, cria: " + rowsAffected);


    } catch (SQLException e) {
        e.printStackTrace();   
    }
    catch (ParseException e) {
        e.printStackTrace();
    }
    finally {
        DB.closeConnection();
        DB.closeStatement(preparedStatement);
    }
    }

}
