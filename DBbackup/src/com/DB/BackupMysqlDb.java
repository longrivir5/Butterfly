package com.DB;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class BackupMysqlDb {

	public static void backupMysqlDatabase(String hostName, String dataBase, String userName, String passwd,
			String outFilePath, String mysqlDumpPath) throws Exception {
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append(mysqlDumpPath + " -u").append(userName).append(" -p").append(passwd).append(" ").append(dataBase);
		if (hostName != null && !hostName.equals("")) {
			sqlStr.append(" ").append(" -h").append(hostName);
		}
		Runtime rt = Runtime.getRuntime();
		Process process = rt.exec(sqlStr.toString());
		InputStream in = process.getInputStream();// �D���x����X��T�@����J�y
		InputStreamReader isr = new InputStreamReader(in, "utf8");
		StringBuilder sb = new StringBuilder("");
		String inStr;
		BufferedReader br = new BufferedReader(isr);
		while ((inStr = br.readLine()) != null) {
			sb.append(inStr).append("\r\n");
		}
		File file = new File(outFilePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fout = new FileOutputStream(outFilePath);
		OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
		writer.write(sb.toString());
		writer.flush();
		in.close();
		isr.close();
		br.close();
		writer.close();
		fout.close();

	}

	public static void backupProject(String srFile, String dtFile, String userName, String passwd, String outFilePath)
			throws Exception {

		File f1 = new File(srFile);
		File f2 = new File(dtFile);
		if (!f2.exists()) {
			f2.createNewFile();
		}
		InputStream in = new FileInputStream(f1);
		// For Overwrite the file.
		OutputStream out = new FileOutputStream(f2);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
		System.out.println("File copied.");
	}

	public static void backupToZip(String outFilePath, String outZipPath) throws Exception {
		
		
		File f = new File(outFilePath);
		FileInputStream fis = new FileInputStream(f);

		// �}�_���Y���X���ɮ�
		ZipOutputStream zOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outZipPath)));
		/*
		* �b���Y�ɤ��إߤ@�Ӷ���(��ܤ@�����Y���ɮשΥؿ��A�i�H�ؿ����c���覡��ܡA �����Y��i�H�]�w���ؿ����c��m�ɮ�)
		*/ 
		zOut.putNextEntry(new ZipEntry(f.getName()));
		// �]�w���Y���{��0~9
		zOut.setLevel(0);

		// �Hbyte���覡Ū���ɮרüg�J���Y��
		int byteNo;
		byte[] b = new byte[64];
		while ((byteNo = fis.read(b)) > 0) {
			zOut.write(b, 0, byteNo);// �N�ɮ׼g�J���Y��
		}
		zOut.close();
		fis.close();
		f.delete();
		
		
	}
	
	public static void bakupLoadFtp(String outZipPath){
		
		  FTPClient ftpClient = new FTPClient(); 
	      FileInputStream fis = null; 
	      try { 
	            ftpClient.connect("10.112.3.10"); 
	            ftpClient.login("dbupload", "goldfish"); 
	            File srcFile = new File(outZipPath); 
	            fis = new FileInputStream(srcFile); 
	            //�]�m�W�ǥؿ� \homes\dbupload\Butterfly
	            ftpClient.changeWorkingDirectory("/Butterfly");
	            ftpClient.setBufferSize(1024); 
	            ftpClient.setControlEncoding("utf-8"); 
	            //�]�m�ɮ������]�G�i��^ 
	            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
	            ftpClient.storeFile("success.zip", fis);
	            fis.close();
	            srcFile.delete();
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	            throw new RuntimeException("FTP�Τ�ݥX���I", e); 
	        } finally { 
	            IOUtils.closeQuietly(fis); 
	            try { 
	                ftpClient.disconnect(); 
	            } catch (IOException e) { 
	                e.printStackTrace(); 
	                throw new RuntimeException("����FTP�s���o�Ͳ��`�I", e); 
	            } 
	        } 
		
		
	}

}