package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    static String sqlFilePath = "sql/populate_db.sql";
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Database.getInstance().getConnection();
        String sql = new String(Files.readAllBytes(Path.of(sqlFilePath)));
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }
}
