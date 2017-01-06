package com.zhouzh3.excel2sql.model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zhouzh3.excel2sql.util.FileUtil;
import com.zhouzh3.excel2sql.util.JavaBeansUtil;
import com.zhouzh3.excel2sql.util.StringUtil;

public class Table extends Data implements Comparable<Table> {

	private Column primaryColumn;

	private List<Column> columns;

	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Table.class);

	public Table() {
		super();
		this.columns = new ArrayList<>();
	}

	public void addColumn(Column col) {
		columns.add(col);
	}

	public void addColumns(List<Column> columns) {
		this.columns.addAll(columns);
	}

	public boolean hasPrimary() {
		return primaryColumn != null;
	}

	public void setPrimaryColumn(Column primaryKey) {
		this.primaryColumn = primaryKey;
	}

	public Column getPrimaryColumn() {
		return primaryColumn;
	}

	/**
	 * <code>
	create table T_ADMIN_TREE
	(
	NODE_ID              varchar(16),
	SUPER_ID             varchar(16),
	NODE_NAME            varchar(32)   comment '这是注释',
	NODE_CODE            varchar(128),
	PRIMARY KEY (NODE_ID)
	);
	
	alter table T_ADMIN_TREE comment '注释';
	 * </code>
	 * 
	 * @return
	 */
	public String createSqlClause() {
		StringBuilder sb = new StringBuilder();

		sb.append("/*==============================================================*/").append(Constants.NEW_LINE);
		sb.append(String.format("/* Table: %-54s*/", code)).append(Constants.NEW_LINE);
		sb.append("/*==============================================================*/").append(Constants.NEW_LINE);
		sb.append(String.format("DROP TABLE IF EXISTS %s;", code)).append(Constants.NEW_LINE);
		sb.append(MessageFormat.format("create table {0}  ( ", code)).append(Constants.NEW_LINE);

		for (int i = 0; i < columns.size(); i++) {
			Data column = columns.get(i);
			if (i != 0) {
				appendSeparetor(sb);
			}
			sb.append(column.createSqlClause());
		}

		if (hasPrimary()) {// 添加主键信息
			appendSeparetor(sb);
			sb.append(String.format("   PRIMARY KEY (%s)", getPrimaryColumn().getCode()));
		}
		sb.append(Constants.NEW_LINE).append(");").append(Constants.NEW_LINE).append(Constants.NEW_LINE);

		//
		if (StringUtil.isNotBlank(FileUtil.fileName(name))) {
			sb.append(MessageFormat.format("alter table {0} comment ''{1}'';", code, FileUtil.fileName(name)))
					.append(Constants.NEW_LINE);
		}
		return sb.toString();
	}

	public void createSheet(XSSFWorkbook workbook) {
		String sheetName = getSheetName(workbook, name, code);
		logger.info("sheetName: " + sheetName);
		XSSFSheet sheet = workbook.createSheet(sheetName);
		int rownum = 0;
		initSheetHeader(sheet.createRow(rownum++));
		for (Column column : columns) {
			XSSFRow row = sheet.createRow(rownum++);
			if (rownum == 2) {// 第二行，增加表名
				row.createCell(0).setCellValue(code);
			}
			column.createRow(row);
		}
	}

	private String getSheetName(XSSFWorkbook workbook, String tableName, String tableCode) {
		String sheetName = FileUtil.fileName(name);
		if (StringUtil.isBlank(sheetName)) {
			return tableCode;
		}

		int i = 1;
		while (workbook.getSheet(sheetName) != null) {
			sheetName = sheetName + "(" + i + ")";
			i++;
		}
		return sheetName;
	}

	private void initSheetHeader(XSSFRow row) {
		for (int i = 0; i < Constants.COLUMN_NAMES.length; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellValue(Constants.COLUMN_NAMES[i]);
		}
	}

	private void appendSeparetor(StringBuilder sb) {
		appendComma(sb);
		appendNewLine(sb);
	}

	private void appendComma(StringBuilder sb) {
		sb.append(Constants.COMMA);
	}

	private void appendNewLine(StringBuilder sb) {
		sb.append(Constants.NEW_LINE);
	}

	@Override
	public boolean validate() {
		if (!super.validate()) {
			return false;
		}

		Set<String> cols = new HashSet<String>(columns.size());
		for (Iterator<Column> iterator = columns.iterator(); iterator.hasNext();) {
			Column column = (Column) iterator.next();
			if (!column.validate()) {//
				return false;
			}

			if (!cols.add(column.code)) {//
				System.err.println(String.format("表%s已经存在列%s", code, column.code));
				return false;
			}
		}
		return true;
	}

	public String getXmlElement() {
		return "<table tableName=\"" + getCode() + "\" domainObjectName=\""
				+ JavaBeansUtil.getCamelCaseString(getCode().replaceFirst("(?i)T", ""), true) + "\" />";
	}

	@Override
	public int compareTo(Table o) {
		return code.compareTo(o.code);
	}

	@Override
	public String toString() {
		return "Table [name=" + FileUtil.fileName(name) + ", code=" + code + ", comment=" + comment + ", columns="
				+ columns + "]";
	}

}
