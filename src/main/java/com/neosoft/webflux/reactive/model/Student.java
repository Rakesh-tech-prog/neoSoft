package com.neosoft.webflux.reactive.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("student")
public class Student implements Persistable<Integer> {

	@Id
	@Column("stdId")
	private Integer stdId;
	@Column("stdName")
	private String stdName;
	@Column("university")
	private String university;
	@Column("gpa")
	private double gpa;
	@Column("result")
	private String result;

	public Student(Integer stdId, String stdName, String university, double gpa, String result) {
		this.stdId = stdId;
		this.stdName = stdName;
		this.university = university;
		this.gpa = gpa;
		this.result = result;
	}

	public Integer getStdId() {
		return stdId;
	}

	public void setStdId(Integer stdId) {
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Student [stdId=" + stdId + ", stdName=" + stdName + ", university=" + university + ", gpa=" + gpa
				+ ", result=" + result + "]";
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.stdId;
	}

	 @Transient 
	  private boolean newStd=false;
	
	  @Transient
	  @Override
	  public boolean isNew() { 
	  if(this.newStd==false || this.stdId==null) 
	  return true; 
	  return false; 
	 }
	  public Student setAsNew() { 
		  this.newStd = true; 
		  return this; 
		  }
}


