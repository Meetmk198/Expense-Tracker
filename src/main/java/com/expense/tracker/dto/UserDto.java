package com.expense.tracker.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {

	@NotBlank(message = "Email is Mandtory")
	@Email
	private String email;
	@NotBlank(message = "Password is Mandtory")
	private String password;
	@NotNull(message = "Date of Birth is Mandatory")
	private Date dob;
	
	@NotNull(message = "Mobile no is Mandatory")
	private Long mobileNo;
	@Min(value = 10, message = "User Must be grater or equal to 10.")
	@NotNull(message = "age is Mandatory")
	private int age;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
