package org.cuatrovientos.tarea2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection =
				DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement statement = connection.createStatement();
		
		//Para que funcione editar el pom de dependencies	
		//String sql = "create table customer (id integer, name varchar(40))";
		//statement.executeUpdate(sql);
		
		String option = "";
		String line = "";
		Scanner reader = new Scanner(System.in);
		String id = "";
		String name = "";
		do {
			System.out.println("Please select: ");
			System.out.println("1) Show all the elements ");
			System.out.println("2) Insert new element ");
			System.out.println("3) Modify a element ");
			System.out.println("4) Eliminate an element ");
			System.out.println("5) Eliminate all the elements ");
			System.out.println("6) Exit ");
			
			option = reader.nextLine();
			
			switch (option) {
				case "1":
					System.out.println("You selected 1 ");
					String select = "select * from customer";
					ResultSet resultSet = statement.executeQuery(select);
					while (resultSet.next()) {
						System.out.print("ID: " + resultSet.getInt("id"));
						System.out.println(" Name: " + resultSet.getString("name"));
					}
					break;
				case "2":
					
					System.out.println("You selected 2");
					System.out.println("Introduce the id (integer): ");
					id = reader.nextLine();
					System.out.println("Introduce the name: ");
					name = reader.nextLine();
					PreparedStatement preparedStatement =
							connection.prepareStatement("insert into customer values (?,?)");
					preparedStatement.setInt(1,Integer.parseInt(id));
					preparedStatement.setString(2, name);
					preparedStatement.addBatch();
					break;
				case "3":
					System.out.println("You selected 3");
					System.out.println("Introduce the id (integer): ");
					id = reader.nextLine();
					System.out.println("Introduce the new name: ");
					name = reader.nextLine();
					String updateSql = "update customer set name="+name+" where id="+id+"";
					statement.executeUpdate(updateSql);
					break;
				case "4":
					System.out.println("You selected 4");
					System.out.println("Introduce the id (integer): ");
					id = reader.nextLine();
					String deleteSql = "delete from customer where id="+id+"";
					statement.executeUpdate(deleteSql);
					break;
				case "5":
					System.out.println("You selected 5");
					deleteSql = "delete from customer";
					statement.executeUpdate(deleteSql);
					break;
				case "6":
					System.out.println("You selected 6 ");
					System.out.println("Byeeeeeeeeeee ");
					break;
				default:
					System.out.println("Incorrect option");
					break;
			
			} 
		}while (!option.equals("6"));
	}
}