package com.example.modihol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modihol.entity.ProductVariant;
import com.example.modihol.repository.ProductVariantRepository;
import java.util.*;
@Service
public class ProductVariantService {
    @Autowired
    private ProductVariantRepository productVariantRepo;

}
