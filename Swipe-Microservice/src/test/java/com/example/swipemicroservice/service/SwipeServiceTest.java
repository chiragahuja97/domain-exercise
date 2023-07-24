package com.example.swipemicroservice.service;

import com.example.swipemicroservice.dao.EmployeeRepository;
import com.example.swipemicroservice.dao.SwipeRepository;
import com.example.swipemicroservice.entity.Employee;
import com.example.swipemicroservice.entity.SwipeDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SwipeServiceTest {

    @Mock
    private SwipeRepository repo;

    @InjectMocks
    private SwipeService swipeService;

    @Test
    void swipe() {
        Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(new SwipeDetails());
       SwipeDetails details=swipeService.swipe(new SwipeDetails());
        assertEquals(SwipeDetails.class,details.getClass());
    }

    @Test
    void findByIdAndDate() {
        Mockito.when(repo.findByUserIdAndDate(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(new ArrayList<>());

        List<SwipeDetails> details=swipeService.findByUserIdAndDate("101","12/5");
        assertEquals(ArrayList.class,details.getClass());
    }
}