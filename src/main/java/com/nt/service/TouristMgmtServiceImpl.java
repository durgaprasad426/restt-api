package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.repo.ITouistRepo;
import com.nt.repo.Tourist;

@Service
public class TouristMgmtServiceImpl implements ITouristService {

	@Autowired
	private ITouistRepo repo;
	
	@Override
	public String registerTourist(Tourist tourist) {
		//tourist=null;
	int id=	repo.save(tourist).getTid();
	return "register tourist successfully::"+id;
		
	}

	@Override
	public List<Tourist> fetchAllTouists() {
	List<Tourist> list=	repo.findAll();
	list.sort((i1,i2)->-i1.getTid().compareTo(i2.getTid()));
		return list;
	}

	@Override
	public Tourist fetchTouristById(Integer tid) throws TouristNotFoundException {
		
		return repo.findById(tid).orElseThrow(()->new TouristNotFoundException(tid+"::touristnotFound"));
	}

	@Override
	public String updateTouristDetails(Tourist tourist) throws TouristNotFoundException {
	  Optional<Tourist>optional=  repo.findById(tourist.getTid());
	  if(optional.isPresent()) {
		  repo.save(tourist);
		  return tourist.getTid()+ "tourist is updated";
	  }else {
		throw new TouristNotFoundException(tourist.getTid()+"tourist not updated");
	}

}

	@Override
	public List<Tourist> findAllTouristByName(String name) {
		return repo.getTouristByName(name);
	}

	@Override
	public String updateTouristBudgetById(int tid, double hikePercentage) throws TouristNotFoundException {
	Optional<Tourist>opt=	repo.findById(tid);
	if(opt.isPresent()) {
Tourist tourist=		opt.get();
  Double budget= tourist.getBudget();
  Double newbudget=budget+(budget*hikePercentage/100.0);
  tourist.setBudget(newbudget);
  repo.save(tourist);
  return "tourist budget is hiked..and return new budget::"+newbudget;
	}
	else {
	 throw new  TouristNotFoundException(tid+"not found for updation");
	}
		
	}

	@Override
	public String removeTouristById(Integer tid) throws TouristNotFoundException {
	Optional<Tourist>opt=	repo.findById(tid);
	if(opt.isPresent()) {
		repo.deleteById(tid);
	return tid+"tourist id deleted";
	}else {
		throw new TouristNotFoundException(tid+"touridt  not found");
	}
}

	@Override
	public String removeTouristBudgetRange(double start, double end) {
	     int count=  repo.removeTouristByBudgetRange(start, end);
		return count==0?"Tourist not found for deletion":count+"no,of Tourist found deletion";
	}
}
