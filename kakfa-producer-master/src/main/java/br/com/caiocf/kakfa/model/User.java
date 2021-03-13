package br.com.caiocf.kakfa.model;

public class User {
	
	private String name;
	private Integer year;
	public User(String name, Integer year) {
		super();
		this.name = name;
		this.year = year;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=");
		builder.append(name);
		builder.append(", year=");
		builder.append(year);
		builder.append("]");
		return builder.toString();
	}

	
	
	

}
