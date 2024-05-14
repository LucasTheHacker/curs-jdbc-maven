package batista.lucas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;

import batista.lucas.db.DB;

public class Program2copy {

    public static void main(String[] args) {

    // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    try {
        conn = DB.getConnection();

        preparedStatement = conn.prepareStatement(
                "INSERT INTO department "
                + "(name) "
                + "VALUES"
                + "(?)"); //place holder
    
        preparedStatement.setString(1, "E-Books trash");
        
        int rowsAffected = preparedStatement.executeUpdate(); //retorna quantidade de linhas alteradas

        System.out.println("Tudo 2, cria: " + rowsAffected);


    } catch (SQLException e) {
        e.printStackTrace();   
    }
    // catch (ParseException e) {
    //     e.printStackTrace();
    // }
    finally {
        DB.closeConnection();
        DB.closeStatement(preparedStatement);
    }
    }

}
