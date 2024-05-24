package com.nt.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import jakarta.transaction.Transactional;

public interface ITouistRepo extends JpaRepository<Tourist, Integer> {

	@Query("from Tourist where tname=:tname")
	public List<Tourist>getTouristByName( String tname);
	
	@Query("delete from Tourist where budget>=:start and budget<=:end ")
	@Modifying
	@Transactional
	public int removeTouristByBudgetRange(double start,double end);
}
