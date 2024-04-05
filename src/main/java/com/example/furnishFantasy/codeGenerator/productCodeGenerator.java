package com.example.furnishFantasy.codeGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class productCodeGenerator implements IdentifierGenerator{
    @Autowired
    private DataSource dataSource;

    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {

        String prefix = "PROD";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT MAX(id) as max_id FROM product");
            rs = ps.executeQuery();
            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                int nextId = maxId + 1;
                String code = prefix + StringUtils.leftPad("_" + nextId, 3, '0');
                return code;
            }
        } catch (SQLException e) {
            throw new HibernateException("Error generating ID for User entity", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // Log the exception or handle it as required
            }
        }
        return null;
    }
}
