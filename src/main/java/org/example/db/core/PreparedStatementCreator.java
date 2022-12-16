package org.example.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 创建预处理的语句对象
 */
public interface PreparedStatementCreator {
    PreparedStatement createPreparedStatement(Connection conn) throws SQLException;
}
