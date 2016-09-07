package com.powere2e.reporttool.model;

public class ExcelModel {
	private String filename;
	private byte[] fileDate;
	public byte[] getFileDate() {
		return fileDate;
	}
	public void setFileDate(byte[] fileDate) {
		this.fileDate = fileDate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
