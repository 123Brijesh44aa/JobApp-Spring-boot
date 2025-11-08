package com.brijesh.firstjobapp.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    Boolean createReview(Review review, Long companyId);

    Review getReviewOfASpecificCompany(Long companyId, Long reviewId);

    Boolean editReview(Long companyId, Long reviewId, Review updatedReview);

}
