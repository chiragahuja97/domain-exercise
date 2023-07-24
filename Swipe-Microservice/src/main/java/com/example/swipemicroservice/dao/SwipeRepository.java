package com.example.swipemicroservice.dao;

import com.example.swipemicroservice.entity.Employee;
import com.example.swipemicroservice.entity.SwipeDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwipeRepository extends MongoRepository<SwipeDetails,String> {
     public List<SwipeDetails> findByUserIdAndDate(String userId, String date);

     @Query(value = "db.swipe_details.find({}).sort({_id:-1}).limit(1)")
     public SwipeDetails getLastRecord();
}
