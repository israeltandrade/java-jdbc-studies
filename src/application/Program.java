package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;

//------INSERT DATA:

		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES "
							+ "(?, ?, ?, ?, ?)",
					// Optional return of id:
					Statement.RETURN_GENERATED_KEYS);
			
//			Replace the interrogations ("?"s) on the previous PreparedStatement object:
			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);

			// Second example with simple insertion (comment ParseException to work):
//			st = conn.prepareStatement(
//					"insert into department (Name) values ('D1'),('D2')",
//					Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate();

//			Old simple msg return:
//			System.out.println("Done! Rows affected: " + rowsAffected);

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No rows affected!");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			
			DB.closeConnection();
		}

//------RETRIEVE DATA:
//
//		Statement st = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = DB.getConnection();
//			
//			st = conn.createStatement();
//			
//			rs = st.executeQuery("select * from department");
//			
//			while (rs.next()) {
//				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			DB.closeResultSet(rs);
//			DB.closeStatement(st);;
//			DB.closeConnection();
//		}

	}

}
