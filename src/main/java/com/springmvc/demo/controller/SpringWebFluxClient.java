package com.springmvc.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


import com.springmvc.demo.model.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import org.apache.log4j.Logger;

// @RestController
@Controller

public class SpringWebFluxClient {

	static Logger logger = Logger.getLogger(SpringWebFluxClient.class);

	private WebClient webClient = WebClient.create("http://localhost:8083");

	@GetMapping("/get/viewAllCourses")
	public String getAllCourses(Model model) {
		logger.info("*********** inside getAllCourses ************");
		Flux < Course > courseFlux = webClient.get().uri("NodeJSRestApiOracle/get/all")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToFlux(Course.class)
			.log("retrieve allEmps :: ");
		logger.info("courseFlux is " + courseFlux.toString());
		List < Course > courseList = courseFlux.collectList().block();
		model.addAttribute("headerValue", "List of All Courses");
		model.addAttribute("allCourses", courseList);
		return "all-courses";
	}


	@GetMapping("/get/addCourses")
	public String addCourse(Model model) {
		logger.info("*********** inside addCourse ************");
		Course course = new Course();
		model.addAttribute("course", course);		
		return "add-courses";
	}


	@PostMapping("post/editCourses")
	public String editCourse(@ModelAttribute("course") Course course,Model model) {
		logger.info("*********** inside editCourses ************");
		logger.info("course edited for confirmation is " + course.toString());	
		model.addAttribute("course", course);
		return "edit-courses";
	}
	
	@PostMapping("post/removeCourses")
	public String removeCourse(@ModelAttribute("course") Course course,Model model) {
		logger.info("*********** inside removeCourses ************");
		logger.info("course removed for confirmation is " + course.toString());	
		model.addAttribute("course", course);
		return "remove-courses";
	}
	
	@PostMapping("/post/confirmFormForPost")
	public String confirmCourseForPost(@ModelAttribute("course") Course course,Model model) {
		logger.info("*********** inside confirmCourseForPost ************");
		logger.info("course added for confirmation is " + course.toString());
		model.addAttribute("titleValue","Confirm Courses that is going to be added");
		model.addAttribute("headerValue","Form to confirm courses before adding it");
		model.addAttribute("paragraphValue","Confirm the Courses that is going to be added");
		model.addAttribute("actionValue", "/post");
		return "confirm-courses";
	}
	
	
	@PostMapping("/post/confirmFormForPut")
	public String confirmCourseForPut(@ModelAttribute("course") Course course,Model model) {
		logger.info("*********** inside confirmCourseForPut ************");
		logger.info("course edited for confirmation is " + course.toString());
		model.addAttribute("titleValue","Confirm Courses that is going to be edited");
		model.addAttribute("headerValue","Form to confirm courses before editing it");
		model.addAttribute("paragraphValue","Confirm the Courses that is going to be edited");
		model.addAttribute("actionValue", "/put");
		return "confirm-courses";
	}
	
	@PostMapping("/post/confirmFormForDelete")
	public String confirmCourseForDelete(@ModelAttribute("course") Course course,Model model) {
		logger.info("*********** inside confirmCourseForDelete ************");
		logger.info("course deleted for confirmation is " + course.toString());
		model.addAttribute("titleValue","Confirm Courses that is going to be deleted");
		model.addAttribute("headerValue","Form to confirm courses before deleting it");
		model.addAttribute("paragraphValue","Confirm the Courses that is going to be deleted");
		model.addAttribute("actionValue", "/delete");
		return "confirm-courses";
	}
	
	
	


	@PostMapping("/post")
	public String addCourse(@ModelAttribute("course") Course course) {
		logger.info("*********** inside addCourse ************");	
		
		webClient.post().uri("NodeJSRestApiOracle/post")
		  .accept(MediaType.APPLICATION_FORM_URLENCODED)
		  .body(BodyInserters.fromObject(course)).retrieve()
		  .bodyToMono(Course.class)
		  .log("insert using body :: ");
		
		return "redirect:/get/viewAllCourses";
	}


	@PostMapping("/put")
	public String updateCourseById(@ModelAttribute("course") Course course) {		
		logger.info("*********** inside updateCourseById ************");
		String id = course.getId();
		
		webClient.put().uri("NodeJSRestApiOracle/put/{id}", id)
			.accept(MediaType.APPLICATION_JSON)
			.syncBody(course)
			.retrieve()
			.bodyToMono(Course.class)
			.log("update empById :: ");
		
		return "redirect:/get/viewAllCourses";
	}


	@PostMapping("/delete")
	public String removeCourseById(@ModelAttribute("course") Course course) {
		logger.info("*********** inside removeCourseById ************");
		String id = course.getId();
		
		webClient.delete().uri("NodeJSRestApiOracle/delete/{id}", id)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(Course.class)
			.log("remove empById :: ");
		
		return "redirect:/get/viewAllCourses";
	}

}