package com.college.blog.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.college.blog.Model.Blog;
import com.college.blog.Repository.BlogRepository;
import com.college.blog.Service.BlogService;

@RestController
public class BlogController {
	
	@Autowired
	private BlogService service;
	
	@Autowired
	private BlogRepository repository;
	
	//Store post details
	@PostMapping("/blogs")
	public void add(
			@RequestParam("thumbnail") MultipartFile file,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("author") String author,
			@RequestParam("category") String category,
			@RequestParam("tags") String tags,
			@RequestParam("timestamps") String timestamps) {
		service.save(file, title, content, author, category, tags, timestamps);
	}
	
	//Update existing posts
	@PutMapping("/blogs/{id}")
	public ResponseEntity <?> update(
			@RequestParam("thumbnail") MultipartFile file,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("author") String author,
			@RequestParam("category") String category,
			@RequestParam("tags") String tags,
			@RequestParam("timestamps") String timestamps,
			@PathVariable Integer id) {
		try {
			Blog existBlog = service.get(id);
			service.update(id, file, title, content, author, category, tags, timestamps);
			return new ResponseEntity <> (HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity <> (HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete posts
	@DeleteMapping("/blogs/{id}")
	public ResponseEntity <?> delete(@PathVariable Integer id) {
		try {
			Blog existBlog = service.get(id);
			service.delete(id);
			return new ResponseEntity <> (HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity <> (HttpStatus.NOT_FOUND);
		}
	}
	
	
	//View all Post
	@GetMapping("/blogs")
	public ResponseEntity<List<Blog>> list() {
		return ResponseEntity.ok(service.listAll());
	}
	
	//Search based on keyword
			@GetMapping("/v1/blogs")
			public ResponseEntity<List<Blog>> listwithsearchkeyword(
					@RequestParam(required = false, defaultValue="0") int pageNo,
					@RequestParam(required = false, defaultValue="10") int pageSize,
					@RequestParam(required = false, defaultValue="id#asc") String[] sort,
					@RequestParam(required = false, defaultValue="") String search
					) {
				final List<Order> orders = Arrays.stream(sort).filter(s -> s.contains("#")).map(s -> s.split("#"))
						.map(arr -> new Order(Direction.fromString(arr[1]), arr[0])).collect(Collectors.toList());

				final Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));

				final var data = search.isBlank() ? service.listAll()
						: repository.findByKeyword(paging, search);

				return ResponseEntity.ok(data);
			}
	
	//Search based on title
	@GetMapping("/blogs/searchtitle")
	public ResponseEntity<List<Blog>> listwithsearchtitle(
			@RequestParam(required = false, defaultValue="0") int pageNo,
			@RequestParam(required = false, defaultValue="10") int pageSize,
			@RequestParam(required = false, defaultValue="id#asc") String[] sort,
			@RequestParam(required = false, defaultValue="") String search
			) {
		final List<Order> orders = Arrays.stream(sort).filter(s -> s.contains("#")).map(s -> s.split("#"))
				.map(arr -> new Order(Direction.fromString(arr[1]), arr[0])).collect(Collectors.toList());

		final Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));

		final var data = search.isBlank() ? service.listAll()
				: repository.findByTitleLike(paging, search);

		return ResponseEntity.ok(data);
	}

	//Search based on author
		@GetMapping("/blogs/searchauthor")
		public ResponseEntity<List<Blog>> listwithauthortitle(
				@RequestParam(required = false, defaultValue="0") int pageNo,
				@RequestParam(required = false, defaultValue="10") int pageSize,
				@RequestParam(required = false, defaultValue="id#asc") String[] sort,
				@RequestParam(required = false, defaultValue="") String search
				) {
			final List<Order> orders = Arrays.stream(sort).filter(s -> s.contains("#")).map(s -> s.split("#"))
					.map(arr -> new Order(Direction.fromString(arr[1]), arr[0])).collect(Collectors.toList());

			final Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));

			final var data = search.isBlank() ? service.listAll()
					: repository.findByAuthorLike(paging, search);

			return ResponseEntity.ok(data);
		}
		
	//Search based on category
		@GetMapping("/blogs/searchcategory")
		public ResponseEntity<List<Blog>> listwithsearchcategory(
				@RequestParam(required = false, defaultValue="0") int pageNo,
				@RequestParam(required = false, defaultValue="10") int pageSize,
				@RequestParam(required = false, defaultValue="id#asc") String[] sort,
				@RequestParam(required = false, defaultValue="") String search
				) {
			final List<Order> orders = Arrays.stream(sort).filter(s -> s.contains("#")).map(s -> s.split("#"))
					.map(arr -> new Order(Direction.fromString(arr[1]), arr[0])).collect(Collectors.toList());

			final Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));

			final var data = search.isBlank() ? service.listAll()
					: repository.findByCategoryLike(paging, search);

			return ResponseEntity.ok(data);
		}
		
		//Search based on tags
				@GetMapping("/blogs/searchtags")
				public ResponseEntity<List<Blog>> listwithtagscategory(
						@RequestParam(required = false, defaultValue="0") int pageNo,
						@RequestParam(required = false, defaultValue="10") int pageSize,
						@RequestParam(required = false, defaultValue="id#asc") String[] sort,
						@RequestParam(required = false, defaultValue="") String search
						) {
					final List<Order> orders = Arrays.stream(sort).filter(s -> s.contains("#")).map(s -> s.split("#"))
							.map(arr -> new Order(Direction.fromString(arr[1]), arr[0])).collect(Collectors.toList());

					final Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));

					final var data = search.isBlank() ? service.listAll()
							: repository.findByTagsLike(paging, search);

					return ResponseEntity.ok(data);
				}
}
