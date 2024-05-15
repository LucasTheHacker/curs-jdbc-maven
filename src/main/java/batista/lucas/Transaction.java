package batista.lucas;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import batista.lucas.db.DB;
import batista.lucas.db.DbException;

public class Transaction {

    public static void main(String[] args) {
        
        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false); //não confirme as operações automaticamente

            st = conn.createStatement();

            int rows = st.executeUpdate("UPDATE seller set basesalary = 2090 Where departmentid = 1");

            // int x = 1;
            // if (x < 2) {
            //     throw new SQLException("FAKE ERROR TO STUDY ATOMIC TRANSACTIONS");
            // }

            int rows2 = st.executeUpdate("UPDATE seller set basesalary = 9020 Where departmentid = 2");

            conn.commit(); //confirme as operações

            System.out.println("rows1 +" + rows);
            System.out.println("rows2 +" + rows2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Error trying o rollback! Caudsed by: " + e1.getMessage());
            }
        }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
