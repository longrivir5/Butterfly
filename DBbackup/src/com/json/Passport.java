package com.json;

public class Passport {

	private String UserID;
	private String ClientID;
	private String DocumentType;
	private String Nationality;
	private String Idno;
	private String DocNumber;
	private String Surname;
	private String Forenames;
	private String Sex;
	private String BirDate;
	private String ExpDate;
	private String CDate;
	private String MDate;
	private String MachineModel;
	
	public Passport(){}
	
	public Passport(String userID, String clientID, String documentType, String nationality, String idno,
			String docNumber, String surname, String forenames, String sex, String birDate, String expDate,
			String cDate, String mDate, String machineModel) {
		super();
		UserID = userID;
		ClientID = clientID;
		DocumentType = documentType;
		Nationality = nationality;
		Idno = idno;
		DocNumber = docNumber;
		Surname = surname;
		Forenames = forenames;
		Sex = sex;
		BirDate = birDate;
		ExpDate = expDate;
		CDate = cDate;
		MDate = mDate;
		MachineModel = machineModel;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String clientID) {
		ClientID = clientID;
	}

	public String getDocumentType() {
		return DocumentType;
	}

	public void setDocumentType(String documentType) {
		DocumentType = documentType;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public String getIdno() {
		return Idno;
	}

	public void setIdno(String idno) {
		Idno = idno;
	}

	public String getDocNumber() {
		return DocNumber;
	}

	public void setDocNumber(String docNumber) {
		DocNumber = docNumber;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getForenames() {
		return Forenames;
	}

	public void setForenames(String forenames) {
		Forenames = forenames;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getBirDate() {
		return BirDate;
	}

	public void setBirDate(String birDate) {
		BirDate = birDate;
	}

	public String getExpDate() {
		return ExpDate;
	}

	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}

	public String getCDate() {
		return CDate;
	}

	public void setCDate(String cDate) {
		CDate = cDate;
	}

	public String getMDate() {
		return MDate;
	}

	public void setMDate(String mDate) {
		MDate = mDate;
	}

	public String getMachineModel() {
		return MachineModel;
	}

	public void setMachineModel(String machineModel) {
		MachineModel = machineModel;
	}
	
	
	
}
