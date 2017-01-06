package com.zhouzh3.excel2sql.model;

import java.text.MessageFormat;

import org.apache.poi.xssf.usermodel.XSSFRow;

import com.zhouzh3.excel2sql.util.StringUtil;

public class Column extends Data {

	private String datatype;

	private boolean primary;

	private boolean notNull;

	public Column() {
		super();
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		if (StringUtil.isNotBlank(datatype)) {
			this.datatype = datatype.trim().toUpperCase();
		}
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primarKey) {
		this.primary = primarKey;
	}

	public boolean isNotBlank() {
		return StringUtil.isNotBlank(code) || StringUtil.isNotBlank(name) || StringUtil.isNotBlank(datatype);
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setIsNotNull(boolean notNull) {
		setNotNull(notNull);
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	@Override
	public boolean validate() {
		if (!super.validate()) {
			return false;
		}

		if (StringUtil.isBlank(datatype)) {
			System.err.println("数据类型为空：" + toString());
			return false;
		}
		return true;
	}

	@Override
	public String createSqlClause() {
		return String.format("   %-24s %-14s %s %s", getCode(), getDatatype(), getNotNullClause(), getCommentClause());
	}

	public String getNotNullClause() {
		return isNotNull() ? "  NOT NULL" : "";
	}

	private String getCommentClause() {
		String coalesce = StringUtil.coalesce(getComment(), getName());
		if (!StringUtil.isNotBlank(coalesce)) {
			return "";
		}

		return MessageFormat.format(" comment ''{0}''", coalesce);
	}

	@Override
	public String toString() {
		return "Column [datatype=" + datatype + ", primary=" + primary + ", notNull=" + notNull + ", name=" + name
				+ ", code=" + code + ", comment=" + comment + "]";
	}

	public void createRow(XSSFRow row) {
		// 分组编号 APPGRP_ID varchar(32) Y
		int columnIndex = 1;
		row.createCell(columnIndex++).setCellValue(name);
		row.createCell(columnIndex++).setCellValue(code);
		row.createCell(columnIndex++).setCellValue(datatype);
		row.createCell(columnIndex++);
		row.createCell(columnIndex++).setCellValue(comment);
	}

}
