package com.example.swipemicroservice.service;

import com.example.swipemicroservice.dao.SwipeRepository;
import com.example.swipemicroservice.entity.Employee;
import com.example.swipemicroservice.entity.SwipeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwipeService {


    @Autowired
    private SwipeRepository swipeRepo;


    public SwipeDetails swipe(SwipeDetails details)
    {
        return swipeRepo.save(details);
    }

    public List<SwipeDetails> findByUserIdAndDate(String userId,String date)
    {
        return swipeRepo.findByUserIdAndDate(userId,date);
    }

    public SwipeDetails getLastRecord()
    {
        return swipeRepo.getLastRecord();
    }


}
