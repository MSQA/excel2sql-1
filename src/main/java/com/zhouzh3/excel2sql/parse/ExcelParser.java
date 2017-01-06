package com.zhouzh3.excel2sql.parse;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zhouzh3.excel2sql.model.Column;
import com.zhouzh3.excel2sql.model.Constants;
import com.zhouzh3.excel2sql.model.Context;
import com.zhouzh3.excel2sql.model.Table;
import com.zhouzh3.excel2sql.util.FileUtil;
import com.zhouzh3.excel2sql.util.ScriptRunnerUtil;
import com.zhouzh3.excel2sql.util.StringUtil;

public class ExcelParser {

	private Map<String, Table> xlsx2table(File xlsxPath, String... sheetNames)
			throws IOException, InvalidFormatException {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(xlsxPath);
			if (sheetNames.length > 0) {
				return xlsx2table(workbook, sheetNames);
			} else {
				return xlsx2table(workbook);
			}
		} catch (IOException | InvalidFormatException e) {
			throw e;
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					;// do nothing
				}
			}
		}

	}

	protected Map<String, Table> xlsx2table(XSSFWorkbook workbook, String... sheetNames) {
		Map<String, Table> retVal = new TreeMap<String, Table>();
		for (String sheetName : sheetNames) {
			System.out.println("#" + sheetName);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Table table = sheet2table(sheet);
			if (table != null && table.validate()) {
				retVal.put(table.getCode(), table);
			}
		}
		return retVal;
	}

	protected Map<String, Table> xlsx2table(XSSFWorkbook workbook) {
		Map<String, Table> retVal = new TreeMap<String, Table>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			XSSFSheet sheet = workbook.getSheetAt(i);

			if (sheet.getSheetName().startsWith(Constants.COMMENT_SHEET_PREFIX)) {
				continue;
			}

			Table table = sheet2table(sheet);
			if (table != null && table.validate()) {
				retVal.put(table.getCode(), table);
			}
		}
		return retVal;
	}

	private Table sheet2table(XSSFSheet sheet) {
		if (!validate(sheet)) {
			return null;
		}

		Table table = new Table();
		table.setName(sheet.getSheetName());

		for (int rownum = Constants.FIRST_ROW; rownum <= sheet.getLastRowNum(); rownum++) {
			XSSFRow row = sheet.getRow(rownum);

			if (row != null) {
				Column col = new Column();
				for (int colnum = 0; colnum < Constants.COLUMN_NAMES.length
						&& colnum <= row.getLastCellNum(); colnum++) {
					populate(table, col, Constants.COLUMN_NAMES[colnum], row.getCell(colnum));
				}

				// name,code,datatype is all not null, then add it
				if (col.isNotBlank()) {
					table.addColumn(col);
				}
			}
		}

		return table;
	}

	private boolean validate(XSSFSheet sheet) {
		if (sheet == null) {
			throw new NullPointerException("传入的sheet为空");
		}
		XSSFRow row = sheet.getRow(0);
		if (row != null) {
			int lastCellNum = row.getLastCellNum();
			if (lastCellNum < Constants.COLUMN_NAMES.length) {
				System.err.println("当前行的列数不够：" + row.toString());
				return false;
			}

			for (int i = 0; i < Constants.COLUMN_NAMES.length; i++) {
				XSSFCell cell = row.getCell(i);
				if (cell == null || !cell.getStringCellValue().equalsIgnoreCase(Constants.COLUMN_NAMES[i])) {
					System.err.println(
							"该列的标题【" + cell.getStringCellValue() + "】与期望值【" + Constants.COLUMN_NAMES[i] + "】不相同");
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private void populate(Table table, Column column, String property, XSSFCell cell) {
		if (cell != null) {
			String stringCellValue = cell.getStringCellValue();
			if (StringUtil.isNotBlank(stringCellValue)) {
				populate(table, column, property, stringCellValue);
			}
		}
	}

	private void populate(Table table, Column column, String property, String cellValue) {
		switch (property) {
		case "TABLE":
			if (StringUtil.isBlank(table.getCode())) {
				table.setCode(cellValue);
				column.setPrimary(true);
				column.setNotNull(true);
				table.setPrimaryColumn(column);
			}
			break;
		case "NAME":
			column.setName(cellValue);
			break;
		case "CODE":
			column.setCode(cellValue);
			break;
		case "DATATYPE":
			column.setDatatype(cellValue);
			break;
		case "NOTNULL":
			column.setNotNull("Y".equalsIgnoreCase(cellValue));
			break;
		case "COMMENT":
			column.setComment(cellValue);
			break;
		default:
			break;
		}
	}

	private String combineSql(Map<String, Table> tables) {
		StringBuilder sb = new StringBuilder();
		for (Table table : tables.values()) {
			String sql = table.createSqlClause();
			sb.append(sql).append(Constants.NEW_LINE);
		}

		return sb.toString();
	}

	private void saveScript(String fileName, String script, boolean saveFile) throws IOException {
		FileUtil.save2file(fileName, script);
	}

	private void executeSql(Context context, String content) {
		if (context.isExecuteSql()) {
			ScriptRunnerUtil.runScript(context.getDataSource(), new StringReader(content));
		}
	}

	public void execute(Context context) throws IOException, InvalidFormatException {
		File excelFile = context.getExcelFile();
		Logger.getLogger(ExcelParser.class).debug("ExcelFile :" + excelFile);
		Map<String, Table> tables = xlsx2table(excelFile, context.getSheetNames());

		String script = combineSql(tables);

		if (context.isScriptSave()) {
			saveScript(context.getScriptFile(), script, context.isScriptSave());
		}
		if (context.isExecuteSql()) {
			executeSql(context, script);
		}

		for (Table table : tables.values()) {
			System.out.println(table.getXmlElement());
		}
	}

}
