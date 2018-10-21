package com.workspace.examples.boot.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long>{
	
	@Query(value ="select * from Books where name like ?", nativeQuery = true)
	public List<Books> getBookNamesLike(String name);
	
}