package com.ty.ims.inventory_prject_boot.util;

import java.beans.Statement;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class Customer_IdGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = "CUS_", suffix = "";
		try {
			Connection connection = session.connection();
			java.sql.Statement statement = connection.createStatement();
			String sql = "select * from Customer order by customer_Id desc limit 1";
			ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(sql);
			if (resultSet.next()) {
				String res = resultSet.getString(1);
				String[] resultid = res.trim().split("_");
				int cartId = Integer.parseInt(resultid[1]);
				int id = cartId + 1;
				if (id <= 9) {
					suffix += "0" + id;
				} else {
					suffix += id;
				}
			} else {
				suffix = "0" + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prefix + suffix;
	}
}
