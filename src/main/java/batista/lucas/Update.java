package batista.lucas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import batista.lucas.db.DB;
import batista.lucas.db.DbException;

public class Update {

    public static void main(String[] args) {
        
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                "UPDATE seller "
                + "SET basesalary = basesalary + ? "
                + "WHERE "
                + "(name = ?)");

            st.setDouble(1, 20000);
            st.setString(2, "Fulano das Couves");

            int rowsAffected= st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
