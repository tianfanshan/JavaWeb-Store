package org.example.db.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理结果集对象
 */
public interface RowCallbackHandler {
    void processRow(ResultSet rs) throws SQLException;
}
