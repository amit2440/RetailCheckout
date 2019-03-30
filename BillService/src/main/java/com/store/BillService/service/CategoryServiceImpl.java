package com.store.BillService.service;

import com.store.BillService.dao.CategoryRepo;
import com.store.BillService.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public boolean createCategory(Category category) {
        categoryRepo.save(category);
            return true;
    }
}
