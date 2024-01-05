package com.college.blog.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.college.blog.Model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{
	
	//Query to search using keyword
	@Query(value="Select s.* from blogs b where "
			+ "b.title like %:keyword% or b.author like %:keyword% or b.category like %:keyword% or b.tags like %:keyword%"
			, nativeQuery=true)
	List<Blog> findByKeyword(Pageable pageable, @Param ("keyword") String keyword);
	
	//Query to search using title
	@Query(value="Select b.* from blogs b where "
			+ "b.title like %:title%"
			, nativeQuery=true)
	List<Blog> findByTitleLike(Pageable pageable, @Param ("title") String title);
	
	//Query to search using author
	@Query(value="Select b.* from blogs b where "
			+ "b.author like %:author%"
			, nativeQuery=true)
	List<Blog> findByAuthorLike(Pageable pageable, @Param ("author") String author);
	
	//Query to search using category
	@Query(value="Select b.* from blogs b where "
			+ "b.category like %:category%"
			, nativeQuery=true)
	List<Blog> findByCategoryLike(Pageable pageable, @Param ("category") String category);
	
	//Query to search using tags
	@Query(value="Select b.* from blogs b where "
			+ "b.tags like %:tags%"
			, nativeQuery=true)
	List<Blog> findByTagsLike(Pageable pageable, @Param ("tags") String tags);
		
	void deleteById(Integer id);
	
}
