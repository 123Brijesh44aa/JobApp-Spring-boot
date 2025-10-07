package com.brijesh.firstjobapp.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    void createReview(Review review, Long companyId);

}
