package sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SqlParameter {
    public int index;
    public Object value;
    public SqlParameterType parameterType;

    public SqlParameter(int index, Object value) {
        this(index, value, SqlParameterType.STRING);
    }

    public SqlParameter(int index, Object value, SqlParameterType parameterType) {
        this.index = index;
        this.value = value;
        this.parameterType = parameterType;
    }

    public void addToStatement(PreparedStatement statement) throws SQLException {
        switch (parameterType) {
            case STRING:
                statement.setString(index, (String) value);
                break;
            case INT:
                statement.setInt(index, (int) value);
                break;
            case NULL:
                statement.setNull(index, Types.NULL);
                break;
        }
    }
}
