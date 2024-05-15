package batista.lucas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import batista.lucas.db.DB;
import batista.lucas.db.DbIntegrityException;

public class Delete {

    public static void main(String[] args) {
        
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                "DELETE FROM department "
                + "WHERE "
                + "(id = ?)");

            st.setInt(1, 6);

            int rowsAffected= st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
