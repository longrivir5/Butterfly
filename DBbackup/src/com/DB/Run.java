package com.DB;

public class Run {

	static String error;
	
	static String hostName = "localhost";
	static String dataBase = "butterfly_prod";// is
	static String userName = "root";
	static String passwd = "11111111";
	static String outFilePath = "C:\\Users\\vincentlin\\Desktop\\backupmysql.sql";
	static String outZipPath = "C:\\Users\\vincentlin\\Desktop\\backupmysql.zip";
    static String mysqlDumpPath = "C:/Program Files/MySQL/MySQL Server 5.5/bin/mysqldump";
	
    
	static String srFile = "C:\\Users\\vincentlin\\Desktop\\backupmysql.sql";
	static String dtFile = "C:\\Sabre\\backupmysql.sql";

	public static void main(String[] args) {
		DBbackup();
	}

	public static void DBbackup() {

		try {
			BackupMysqlDb.backupMysqlDatabase(hostName, dataBase, userName, passwd, outFilePath,mysqlDumpPath);
			System.out.println(" 備份MySQL 成功");
			BackupMysqlDb.backupToZip(outFilePath, outZipPath);
			System.out.println(" 解壓縮MySQL 成功");
			BackupMysqlDb.bakupLoadFtp(outZipPath);
			System.out.println(" 上傳FTP 成功");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
