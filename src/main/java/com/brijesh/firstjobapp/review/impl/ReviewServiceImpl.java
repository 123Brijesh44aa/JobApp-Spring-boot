package com.brijesh.firstjobapp.review.impl;

import com.brijesh.firstjobapp.company.Company;
import com.brijesh.firstjobapp.company.CompanyService;
import com.brijesh.firstjobapp.review.Review;
import com.brijesh.firstjobapp.review.ReviewRepository;
import com.brijesh.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService){
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public void createReview(Review review, Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
        }
    }
}
