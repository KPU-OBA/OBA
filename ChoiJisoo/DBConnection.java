package DB;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnection {
	
	public List<Map<String,Object>> DBConnection(String sql, String getString){
		
		List<Map<String,Object>> result = new ArrayList();
		java.sql.Connection connection = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miro_education", "root","passwd");
			st = connection.createStatement();
			if(connection != null){
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()){
					Map<String, Object> map = new HashMap();
					String sqlRecipeProcess = rs.getString(getString);
					map.put(getString, sqlRecipeProcess);
					result.add(map);
				}

				rs.close();
				st.close();
				connection.close();
			}
		} catch (SQLException se1) {
			se1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException se2) {

			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return result;
	}
}

	

