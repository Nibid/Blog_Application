package com.college.blog.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.college.blog.Model.Blog;
import com.college.blog.Repository.BlogRepository;

@Service
@Transactional
public class BlogService {
	
	@Autowired
	private BlogRepository repository;
	
	public void save(MultipartFile file, String title, String content, String author, String category, String tags, String timestamps) {
		Blog b = new Blog();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			b.setThumbnail(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		b.setAuthor(author);
		b.setCategory(category);
		b.setContent(content);
		b.setTimestamps(timestamps);
		b.setTitle(title);
		b.setTags(tags);
		repository.save(b);
	}
	
	public void update(int id, MultipartFile file, String title, String content, String author, String category, String tags, String timestamps) {
		Optional<Blog> blog = repository.findById(id);
		Blog b= blog.get();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			b.setThumbnail(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		b.setAuthor(author);
		b.setCategory(category);
		b.setContent(content);
		b.setTimestamps(timestamps);
		b.setTitle(title);
		b.setTags(tags);
		repository.save(b);
	}
	
	public void saveFileList(MultipartFile[] files, Integer id) {
		Optional<Blog> b = repository.findById(id);
		Blog blog= b.get();
		for (MultipartFile file : files) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			try {
				blog.setImages(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		repository.save(blog);
	}

	public Blog get(Integer id) {
		return repository.findById(id).get();
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public List<Blog> listAll() {
		return repository.findAll();
	}

}
