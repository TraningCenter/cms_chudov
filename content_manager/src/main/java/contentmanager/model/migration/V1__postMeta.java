package contentmanager.model.migration;


import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class V1__postMeta implements JdbcMigration{

    @Override
    public void migrate(Connection connection) throws Exception {
        final PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO post_meta (post_id, title, post_date)" +
                        "VALUES (1111, 'Flyway migration', CURRENT_DATE )");

        try{
            statement.execute();
        } finally {
            statement.close();
        }
    }
}
