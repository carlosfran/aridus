package br.uern.aridus.sys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class ADocBaseImpl implements ADocBase {

	public static final String dirBase = "aridus-config/DocBase"; 
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet rs = null;

	public ADocBaseImpl() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/aridusdb",
				"root", "spy*32");
	}

	public long create(String title, String keywords, String abstractText, long userId) {

		long key = 0;
			try {
				
				String query = "INSERT INTO aridusdb.docs (title,abstract, userid)"
						+ " VALUES('"+ title+ "', '"+abstractText+"', "+userId+")";
				String select = "SELECT iddoc FROM aridusdb.docs" +
						" WHERE title='"+ title+ "' AND abstract='"+abstractText+"'";
				
				statement = connection.prepareStatement(select);
				ResultSet rs = statement.executeQuery(select);
				if(!rs.next()){
					statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
					ResultSet set = statement.getGeneratedKeys();
					if(set.next()){
						key = set.getInt(1);
						// System.out.println(key);
						// Insere keywords
						insertDocKeys(key, keywords);						
					}
				}
				statement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		return key;
	}
	
	private void insertkey(Long id, Long key){
		String insert = "INSERT INTO dockeys(idkey, iddoc) VALUES("+key+", "+id+")";
		try {
			statement = connection.prepareStatement(insert);
			statement.executeUpdate(insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void insertDocKeys(Long id, String keywords){
		StringTokenizer stoken = new StringTokenizer(keywords, ",");
		while(stoken.hasMoreElements()){
//			String keyword = (String) stoken.nextElement();
			String keyword = (String) stoken.nextToken();
			while(keyword.startsWith(" ")) 
				keyword = keyword.substring(1,keyword.length());
			while(keyword.endsWith(" "))
				keyword = keyword.substring(0, keyword.length()-1);
			System.out.println(keyword);
			String select = "SELECT idkey FROM keyword" +
					" WHERE lower(keyword)=lower('" + keyword + "')";
			try{
				statement = connection.prepareStatement(select);
				rs = statement.executeQuery(select);
				if(rs.next()){ // keywords j� existe na base
					Long k = rs.getLong(1);
					// insert key
					if(k > 0) insertkey(id, k);
					
				}else{ // keyword n�o existe na base
					// inserir keyword
					String insert = "INSERT INTO keyword(keyword) VALUES('"+keyword+"')";
					statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
					statement.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
					ResultSet set = statement.getGeneratedKeys();
					if(set.next()){
						long  k = set.getLong(1);
						// insert key
						if(k > 0) insertkey(id, k);
					}
				}
			}catch(SQLException sqle){
				sqle.getStackTrace();
			}
		}
	}

	@Override
	public boolean delete(long id, long userid) {

		boolean ret = false;
		try {
			statement = connection.createStatement();
			String delete = "DELETE FROM aridusdb.docs "
				+"WHERE iddoc = " + id + " AND userid="+userid ;
//				System.out.println(delete);
			long l = statement.executeUpdate(delete);
			if (l > 0)
				ret = true;
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String getProp(long id, String prop) {
		String value = "";
		if (!prop.equalsIgnoreCase("iddoc") && !prop.equalsIgnoreCase("userid") && !prop.equalsIgnoreCase("keywords")) {
			try {
				statement = connection.createStatement();
				rs = statement.executeQuery("SELECT " + prop
						+ " FROM aridusdb.docs " + "WHERE iddoc = "+id);
				if (rs.next()) {
					value = rs.getString(1);
				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(prop.equalsIgnoreCase("keywords")){
			try {
				statement = connection.createStatement();
				String select = "SELECT k.keyword "
						+ " FROM dockeys as p, keyword as k"
						+ " WHERE p.iddoc="+id+" AND p.idkey=k.idkey;";
				rs = statement.executeQuery(select);
				StringBuffer sb = new StringBuffer("");
				while (rs.next()) {
					sb.append(rs.getString(1) +", ");
				}
				value = sb.toString();
				value = value.substring(0, value.length()-2);
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see br.uern.aridus.aridusdb.ProjectIface#updateProp(long, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateProp(long id, long userid, String prop, String newValue) {
		boolean ret = false;
		try {
			statement = connection.createStatement();
			if (!prop.equalsIgnoreCase("iddoc") && !prop.equalsIgnoreCase("userid")
					&& !prop.equalsIgnoreCase("keywords")) {
				String update = "UPDATE aridusdb.docs " + "SET " + prop + "='"
					+ newValue + "' WHERE iddoc = " +id + " AND userid="+userid;
			// System.out.println(update);
				long l = statement.executeUpdate(update);
				if (l > 0)
					ret = true;
				rs.close();
				statement.close();
			}else if(prop.equalsIgnoreCase("keywords")){
					try {
						statement = connection.createStatement();
						statement.executeUpdate("DELETE FROM dockeys " +
								"WHERE iddoc="+ id +";");
						
						insertDocKeys(id, newValue);
						ret = true;
						rs.close();
						statement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public boolean documentExist(long id){
		boolean ret = false;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT iddoc" +
					" FROM aridusdb.docs" +
					" WHERE iddoc = "+id);
			if (rs.next()) {
				ret = true;
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
		
	// save uploaded file to new location
	public void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {

		try {
			
			int read = 0;
			byte[] bytes = new byte[1024];
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
 
			e.printStackTrace();
		}
 
	}
	
	public ResultSet searchDoc(String term, String field){
		ResultSet ret = null;
		if(field.equalsIgnoreCase("title")){
			String query = "SELECT iddoc as id, title " +
					"FROM aridusdb.docs WHERE title LIKE '%" + term +"%' GROUP BY title ASC";
			try {
				statement = connection.createStatement();
				ret = statement.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(field.equalsIgnoreCase("keyword")){
			String query = "SELECT p.iddoc as id, title " +
					"FROM keyword as k, dockeys as pk, docs as p " +
					"WHERE keyword LIKE '%"+ term+"%' AND " +
							"k.idkey=pk.idkey AND pk.iddoc=p.iddoc " +
							"GROUP BY title ASC";
			try {
				statement = connection.createStatement();
				ret = statement.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(field.equalsIgnoreCase("abstract")){
			String query = "SELECT iddoc as id, title " +
					"FROM aridusdb.docs WHERE abstract LIKE '%" + term +"%' GROUP BY title ASC";
			try {
				statement = connection.createStatement();
				ret = statement.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
}
