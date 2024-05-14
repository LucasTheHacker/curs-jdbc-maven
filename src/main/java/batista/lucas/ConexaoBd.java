package batista.lucas;

import java.sql.Connection;
import batista.lucas.db.DB;
public class ConexaoBd {
    public static void main(String[] args) {
        
        Connection conn = DB.getConnection();

        DB.closeConnection();
    }
}