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

		// 連線設定
		Class.forName("com.mysql.jdbc.Driver");
		conDB = (Connection) DriverManager.getConnection(url, user, password);

		String agtno = "";
		int count = 0;
		// 預刪除公司 agtno
		Scanner scanner = new Scanner(System.in);
		System.out.println("請輸入預刪除帳號編號(Agtno)：");
		agtno = scanner.next();

		// Sql指令
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
					System.out.println("客戶名稱 :" + rsAccount.getString("loginaccount") + "-"
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
		System.out.println("預刪除總筆數 :" + count);
		rsAccount.close();
		stmtAccount.close();

		// 刪除
		deleteCustomer();
		deleteAgent();

	}

	public static void deleteCustomer() throws SQLException {

		Statement stmtDeleteCus = (Statement) conDB.createStatement();

		
		for (int agtcustdetailnbr : deleteCustomer) {
			String sql = "DELETE FROM agentcustdetail  where agtcustdetailnbr = '" + agtcustdetailnbr + "'";
			stmtDeleteCus.executeUpdate(sql);
			System.out.println("客戶資料庫編號 : " + agtcustdetailnbr);
		}
		

	}

	public static void deleteAgent() throws SQLException {

		Statement stmtDeleteAgt = (Statement) conDB.createStatement();
		
		
		for(int agtcustnbr : deleteAgent){
			String sql = "DELETE FROM agentcustomer  where agtcustnbr = '" + agtcustnbr + "'";
			stmtDeleteAgt.executeUpdate(sql);
			System.out.println("廠商資料庫編號 : " + agtcustnbr);
		}
		
		
	}

}
