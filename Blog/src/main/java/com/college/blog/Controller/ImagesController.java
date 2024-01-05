package com.college.blog.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.college.blog.Model.Blog;
import com.college.blog.Service.BlogService;

@RestController
public class ImagesController {
	
	@Autowired
	private BlogService service;
	
	//Store images to a particular blog
	@PutMapping("/blogs/storeimage/{id}")
	public ResponseEntity <?> storeImage(@RequestParam("images") MultipartFile[] files, @PathVariable Integer id){
		try {
			Blog existBlog = service.get(id);
			service.saveFileList(files, id);
			return new ResponseEntity <> (HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity <> (HttpStatus.NOT_FOUND);
		}
	}

}
