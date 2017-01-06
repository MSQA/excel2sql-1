package com.zhouzh3.excel2sql.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Schema extends Data implements Comparable<Schema> {

	private List<Table> tables;

	public Schema() {
	}

	public Schema(String code) {
		this.code = code;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	@Override
	public int compareTo(Schema o) {
		return 0;
	}

	@Override
	String createSqlClause() {
		return "";
	}

	public void writeExcel(String name) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		for (Table table : tables) {
			table.createSheet(workbook);
		}
		FileOutputStream stream = new FileOutputStream(name);
		workbook.write(stream);
		workbook.close();
		stream.close();
	}

}
