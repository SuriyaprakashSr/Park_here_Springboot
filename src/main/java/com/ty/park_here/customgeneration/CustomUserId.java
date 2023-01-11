package com.ty.park_here.customgeneration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomUserId implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = "user_";
		String suffix = "";
		try {
			Connection connection = session.connection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM user ORDER BY user_id DESC LIMIT 1";
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				String res= resultSet.getString(1);
				String [] resultid= res.trim().split("_");
				int userId= Integer.parseInt(resultid[1]);
				int id = userId + 1;
				if (id <= 9) {
					suffix += "0" + id;
				} 
			else {
					suffix += id;
				}
			}else {
				suffix="0"+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prefix + suffix;
	}

}
