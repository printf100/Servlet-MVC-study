package com.bike.DTO;

public class BikeDTO {

	private int CONTENT_ID;
	private String CONTENT_NAME;
	private String ADDR_GU;
	private String NEW_ADDR;
	private int CRADLE_COUNT;
	private double LONGITUDE;
	private double LATITUDE;
	
	public BikeDTO() {
		// TODO Auto-generated constructor stub
	}

	public BikeDTO(int cONTENT_ID, String cONTENT_NAME, String aDDR_GU, String nEW_ADDR, int cRADLE_COUNT,
			double lONGITUDE, double lATITUDE) {
		CONTENT_ID = cONTENT_ID;
		CONTENT_NAME = cONTENT_NAME;
		ADDR_GU = aDDR_GU;
		NEW_ADDR = nEW_ADDR;
		CRADLE_COUNT = cRADLE_COUNT;
		LONGITUDE = lONGITUDE;
		LATITUDE = lATITUDE;
	}

	public int getCONTENT_ID() {
		return CONTENT_ID;
	}

	public void setCONTENT_ID(int cONTENT_ID) {
		CONTENT_ID = cONTENT_ID;
	}

	public String getCONTENT_NAME() {
		return CONTENT_NAME;
	}

	public void setCONTENT_NAME(String cONTENT_NAME) {
		CONTENT_NAME = cONTENT_NAME;
	}

	public String getADDR_GU() {
		return ADDR_GU;
	}

	public void setADDR_GU(String aDDR_GU) {
		ADDR_GU = aDDR_GU;
	}

	public String getNEW_ADDR() {
		return NEW_ADDR;
	}

	public void setNEW_ADDR(String nEW_ADDR) {
		NEW_ADDR = nEW_ADDR;
	}

	public int getCRADLE_COUNT() {
		return CRADLE_COUNT;
	}

	public void setCRADLE_COUNT(int cRADLE_COUNT) {
		CRADLE_COUNT = cRADLE_COUNT;
	}

	public double getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(double lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

	public double getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(double lATITUDE) {
		LATITUDE = lATITUDE;
	}

	@Override
	public String toString() {
		return "BikeDTO [CONTENT_ID=" + CONTENT_ID + ", CONTENT_NAME=" + CONTENT_NAME + ", ADDR_GU=" + ADDR_GU
				+ ", NEW_ADDR=" + NEW_ADDR + ", CRADLE_COUNT=" + CRADLE_COUNT + ", LONGITUDE=" + LONGITUDE
				+ ", LATITUDE=" + LATITUDE + "]";
	}
}
