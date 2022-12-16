package org.example.db.core;

import java.sql.*;

public class JdbcTemplate {

    /**
     * 执行查询操作
     * @param pscreator 创建语句对象
     * @param callbackHandler 结果集处理对象
     * @throws DataAccessException
     */
    public void query(PreparedStatementCreator pscreator,
                      RowCallbackHandler callbackHandler) throws DataAccessException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBHelp.getConnection();
            preparedStatement = pscreator.createPreparedStatement(connection);
            resultSet = preparedStatement.executeQuery();

            //遍历结果集
            while (resultSet.next()) {
                callbackHandler.processRow(resultSet);
            }

        } catch (SQLException e) {
            throw new DataAccessException("JdbcTemplate中的SQLException", e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("JdbcTemplate中的ClassNotFoundException", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate中不能关闭数据库连接", e);
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate中不能释放语句对象", e);
                }
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate中不能关闭结果集对象", e);
                }
            }
        }

    }

    /**
     * 数据修改操作
     * @param pscreator 创建语句对象器
     * @throws DataAccessException
     */
    public void update(PreparedStatementCreator pscreator)
            throws DataAccessException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBHelp.getConnection();
            preparedStatement = pscreator.createPreparedStatement(connection);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("JdbcTemplate中的SQLException", e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("JdbcTemplate中的ClassNotFoundException", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate中不能关闭数据库连接", e);
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate中不能释放语句对象", e);
                }
            }
        }
    }
}
