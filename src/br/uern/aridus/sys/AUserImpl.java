package br.uern.aridus.sys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import br.uern.aridus.model.UserProfile;

@WebService
@SOAPBinding
public class AUserImpl implements AUser {

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet rs = null;

	public AUserImpl() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		loadDriverConnection();
	}
	
	private static void loadDriverConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		if(connection == null){
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/aridusdb",
					"root", "spy*32");
		}
	}

	public long create(UserProfile user) {
		
		long key = 0;

		if (!check(user.getEmail())) {
			try {
				statement = connection.createStatement();
				String query = "INSERT INTO aridusdb.users (username, psswd, email, uriprofile)"
						+ " VALUES('"
						+ user.getUsername()
						+ "', md5('"
						+ user.getPsswd()
						+ "'), '"
						+ user.getEmail() + "', '" + user.getUriprofile() + "')";
				statement.execute(query);
				query = "SELECT userid FROM aridusdb.users "
						+ "WHERE username='" + user.getUsername() + "'";
				rs = statement.executeQuery(query);
				if (rs.next()) {
					key = rs.getLong(1);
				}
				// System.out.println(key);
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return key;
	}

	public boolean delete(String username, String psswd) {

		boolean ret = false;
		if (check(username, psswd)>0) {
			try {
				statement = connection.createStatement();
				String delete = "DELETE FROM aridusdb.users "
				+"WHERE username = '" + username + "'";
//				System.out.println(delete);
				long l = statement.executeUpdate(delete);
				if (l > 0)
					ret = true;
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	public boolean check(String username) {
		boolean check = false;
		try {
			statement = connection.createStatement();
			String query = "SELECT COUNT(*) as login " + "FROM users "
					+ "WHERE username = '" + username + "'";
			rs = statement.executeQuery(query);
			if (rs.next()) {
				if (rs.getInt(1) > 0)
					check = true;
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public long check(String username, String psswd){
		return AUserImpl.checkCredentials(username, psswd);
	}
	
	public static long checkCredentials(String username, String psswd) {
		long check = 0;
		try {
			if(connection == null) loadDriverConnection();
			
			statement = connection.createStatement();
			String query = "SELECT userid as login " + "FROM aridusdb.users "
					+ "WHERE username = '" + username + "' AND "
					+ "psswd = md5('" + psswd + "')";
//			System.out.println(query);
			rs = statement.executeQuery(query);
			if (rs.next()) {
				check = rs.getLong(1);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public String getProp(String username, String psswd, String prop) {
		String value = "";
		if (check(username, psswd)>0 && !prop.equalsIgnoreCase("psswd")) {
			try {
				statement = connection.createStatement();
				rs = statement.executeQuery("SELECT " + prop
						+ " FROM aridusdb.users " + "WHERE username = '"
						+ username + "'");
				if (rs.next()) {
					value = rs.getString(1);
				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}

	public boolean updateProp(String username, String psswd, String prop,
			String newValue) {
		boolean ret = false;
		if (check(username, psswd)>0 && !prop.equalsIgnoreCase("userid") && !prop.equalsIgnoreCase("username")) {
			try {
				statement = connection.createStatement();
				if (prop.equalsIgnoreCase("psswd")) {
					newValue = "md5('" + newValue + "')";
				} else {
					newValue = "'" + newValue + "'";
				}
				String update = "UPDATE aridusdb.users " + "SET " + prop + "="
						+ newValue + " WHERE username = '" + username + "'";
				// System.out.println(update);
				long l = statement.executeUpdate(update);
				if (l > 0)
					ret = true;
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
}
