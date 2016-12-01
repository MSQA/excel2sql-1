package com.zhouzh3.excel2sql.model;

import com.zhouzh3.excel2sql.util.StringUtil;

public abstract class Data implements Validatable {

	protected String name;
	protected String code;
	protected String comment;

	public Data() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (StringUtil.isNotBlank(name)) {
			this.name = name.trim();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (StringUtil.isNotBlank(code)) {
			this.code = code.trim().toUpperCase();
		}
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		if (StringUtil.isNotBlank(comment)) {
			this.comment = comment.trim();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhouzh3.excel2sql.parse.Validatable#validate()
	 */
	@Override
	public boolean validate() {
		if (StringUtil.isBlank(code)) {
			System.err.println("code为空：" + toString());
			return false;
		}

		if (StringUtil.isBlank(name)) {
			System.err.println("name为空：" + toString());
			return false;
		}

		return true;
	}

	abstract String createSqlClause();

	@Override
	public String toString() {
		return "Data [name=" + name + ", code=" + code + ", comment=" + comment + "]";
	}

}