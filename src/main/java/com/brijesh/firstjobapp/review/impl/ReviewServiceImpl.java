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
    public Boolean createReview(Review review, Long companyId) {
        Optional<Company> companyOptional = Optional.ofNullable(companyService.getCompanyById(companyId));
        if (companyOptional.isPresent()){
            review.setCompany(companyOptional.get());
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReviewOfASpecificCompany(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews
                .stream()
                .filter((review -> review.getId().equals(reviewId)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean editReview(Long companyId, Long reviewId, Review updatedReview) {
        Optional<Company> companyOptional = Optional.ofNullable(companyService.getCompanyById(companyId));
        if (companyOptional.isPresent()){
            List<Review> reviews = companyOptional.get().getReviews();
            Review review = reviews
                    .stream()
                    .filter((r -> r.getId().equals(reviewId)))
                    .findFirst()
                    .orElse(null);
            if (review != null){
                review.setId(reviewId);
                review.setCompany(review.getCompany());
                review.setTitle(updatedReview.getTitle());
                review.setDescription(updatedReview.getDescription());
                review.setRating(updatedReview.getRating());
                reviewRepository.save(review);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Boolean isReviewDeleted(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            assert review != null;
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        } else {
            return false;
        }
    }
}
