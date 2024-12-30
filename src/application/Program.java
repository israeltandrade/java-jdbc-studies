package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;

		//------DELETE DATA:
		
				try {
					conn = DB.getConnection();
					
					st = conn.prepareStatement(
							"DELETE FROM department "
							+ "WHERE "
							+ "Id = ?");
					
					//	First parameter refers to the "?", the second one is the value:
					st.setInt(1, 2);
					
					int rowsAffected = st.executeUpdate();
					
					System.out.println("Done! Rows affected: " + rowsAffected);
				}
				catch (SQLException e) {
					throw new DbIntegrityException(e.getMessage());
				}
				finally {
					DB.closeStatement(st);
					DB.closeConnection();
				}		

//------UPDATE DATA:
		
//		try {
//			conn = DB.getConnection();
//			
//			st = conn.prepareStatement(
//					"UPDATE seller "
//					+ "SET BaseSalary = BaseSalary + ? "
//					+ "WHERE "
//					+ "(DepartmentId = ?)");
////			First parameter refers to the "?", the second one is the value:
//			st.setDouble(1,  200.0);
//			st.setInt(2, 2);
//			
//			int rowsAffected = st.executeUpdate();
//			
//			System.out.println("Done! Rows affected: " + rowsAffected);
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			DB.closeStatement(st);
//			DB.closeConnection();
//		}
		
//------INSERT DATA:

//		try {
//			conn = DB.getConnection();
//
//			st = conn.prepareStatement(
//					"INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES "
//							+ "(?, ?, ?, ?, ?)",
//					// Optional return of id:
//					Statement.RETURN_GENERATED_KEYS);
//			
////			Replace the interrogations ("?"s) on the previous PreparedStatement object:
//			st.setString(1, "Carl Purple");
//			st.setString(2, "carl@gmail.com");
//			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
//			st.setDouble(4, 3000.0);
//			st.setInt(5, 4);
//
//			// Second example with simple insertion (comment ParseException to work):
////			st = conn.prepareStatement(
////					"insert into department (Name) values ('D1'),('D2')",
////					Statement.RETURN_GENERATED_KEYS);
//			
//			int rowsAffected = st.executeUpdate();
//
////			Old simple msg return:
////			System.out.println("Done! Rows affected: " + rowsAffected);
//
//			if (rowsAffected > 0) {
//				ResultSet rs = st.getGeneratedKeys();
//				while (rs.next()) {
//					int id = rs.getInt(1);
//					System.out.println("Done! Id = " + id);
//				}
//			} else {
//				System.out.println("No rows affected!");
//			}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		catch (ParseException e) {
//			e.printStackTrace();
//		}
//		finally {
//			DB.closeStatement(st);
//			
//			DB.closeConnection();
//		}

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
