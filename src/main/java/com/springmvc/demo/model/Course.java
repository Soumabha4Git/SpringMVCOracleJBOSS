package com.springmvc.demo.model;




public class Course {	
	
    private String id;
    private String courseName;    
    private String rating;    
    private String price;  
    private String trainerName;
    private String numberOfDays;
        
       
	public Course() {
		super();		
	}


	public Course(String id, String courseName, String rating, String price, String trainerName, String numberOfDays) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.rating = rating;
		this.price = price;
		this.trainerName = trainerName;
		this.numberOfDays = numberOfDays;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getTrainerName() {
		return trainerName;
	}


	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}


	public String getNumberOfDays() {
		return numberOfDays;
	}


	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays = numberOfDays;
	}


	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", rating=" + rating + ", price=" + price
				+ ", trainerName=" + trainerName + ", numberOfDays=" + numberOfDays + "]";
	}
    
    
	
	
    
    
	
}
