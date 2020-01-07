package sql;

import java.sql.ResultSet;

public class UpdateResult {
    public int updatedRows;
    public ResultSet generatedKeys;

    public UpdateResult(int updatedRows, ResultSet generatedKeys) {
        this.updatedRows = updatedRows;
        this.generatedKeys = generatedKeys;
    }
}
