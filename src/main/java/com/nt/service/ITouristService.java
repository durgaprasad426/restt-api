package com.nt.service;

import java.util.List;

import com.nt.repo.Tourist;

public interface ITouristService {
public String registerTourist(Tourist tourist);
public List<Tourist>fetchAllTouists();
public Tourist fetchTouristById(Integer tid)throws TouristNotFoundException;
public String updateTouristDetails(Tourist tourist)throws TouristNotFoundException;
public List<Tourist>findAllTouristByName(String name);
public String updateTouristBudgetById(int tid,double percentage)throws TouristNotFoundException;
public String removeTouristById(Integer tid)throws TouristNotFoundException;
	public String removeTouristBudgetRange(double start,double end);
}