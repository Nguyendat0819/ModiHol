package com.example.modihol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.modihol.repository.*;
import com.example.modihol.entity.*;
import java.util.List;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;    
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }
}
