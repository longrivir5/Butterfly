package com.sabretn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAgentCustomer {

	static ArrayList<Integer> deleteAgent = new ArrayList<Integer>();
	static ArrayList<Integer> deleteCustomer = new ArrayList<Integer>();
	static Connection conDB;
	static String url = "jdbc:mysql://10.112.32.30:3306/butterfly_prod";
	static String password = "Abacus8Rd";
	static String user = "root";
	//static String url = "jdbc:mysql://localhost:3306/butterfly_prod";
	//static String password = "11111111";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		// �s�u�]�w
		Class.forName("com.mysql.jdbc.Driver");
		conDB = (Connection) DriverManager.getConnection(url, user, password);

		String agtno = "";
		int count = 0;
		// �w�R�����q agtno
		Scanner scanner = new Scanner(System.in);
		System.out.println("�п�J�w�R���b���s��(Agtno)�G");
		agtno = scanner.next();

		// Sql���O
		String sql = "select * from account where agtno= '" + agtno + "'";

		// select
		Statement stmtAccount = (Statement) conDB.createStatement();
		stmtAccount.executeQuery(sql);
		ResultSet rsAccount = (ResultSet) stmtAccount.getResultSet();

		// update delete
		// stmt.executeUpdate(sql);

		while (rsAccount.next()) {

			sql = "select * from agentcustomer where agtno ='" + agtno + "' AND account='"
					+ rsAccount.getString("loginaccount") + "'";

			Statement stmtAgent = (Statement) conDB.createStatement();
			stmtAgent.executeQuery(sql);
			ResultSet rsAgent = (ResultSet) stmtAgent.getResultSet();

			while (rsAgent.next()) {
				deleteAgent.add(Integer.parseInt(rsAgent.getString("agtcustnbr")));
				sql = "select * from agentcustdetail  where agtcustnbr ='" + rsAgent.getString("agtcustnbr") + "'";

				Statement stmtCust = (Statement) conDB.createStatement();
				stmtCust.executeQuery(sql);
				ResultSet rsCust = (ResultSet) stmtCust.getResultSet();

				while (rsCust.next()) {
					deleteCustomer.add(Integer.parseInt(rsCust.getString("agtcustdetailnbr")));
					System.out.println("�Ȥ�W�� :" + rsAccount.getString("loginaccount") + "-"
							+ rsAgent.getString("ccompanyname") + "(" + rsAgent.getString("agtcustnbr") + ")" + "-"
							+ rsCust.getString("ccustname"));
					count++;
				}

				rsCust.close();
				stmtCust.close();
			}
			rsAgent.close();
			stmtAgent.close();
		}
		System.out.println("�w�R���`���� :" + count);
		rsAccount.close();
		stmtAccount.close();

		// �R��
		deleteCustomer();
		deleteAgent();

	}

	public static void deleteCustomer() throws SQLException {

		Statement stmtDeleteCus = (Statement) conDB.createStatement();

		
		for (int agtcustdetailnbr : deleteCustomer) {
			String sql = "DELETE FROM agentcustdetail  where agtcustdetailnbr = '" + agtcustdetailnbr + "'";
			stmtDeleteCus.executeUpdate(sql);
			System.out.println("�Ȥ��Ʈw�s�� : " + agtcustdetailnbr);
		}
		

	}

	public static void deleteAgent() throws SQLException {

		Statement stmtDeleteAgt = (Statement) conDB.createStatement();
		
		
		for(int agtcustnbr : deleteAgent){
			String sql = "DELETE FROM agentcustomer  where agtcustnbr = '" + agtcustnbr + "'";
			stmtDeleteAgt.executeUpdate(sql);
			System.out.println("�t�Ӹ�Ʈw�s�� : " + agtcustnbr);
		}
		
		
	}

}
