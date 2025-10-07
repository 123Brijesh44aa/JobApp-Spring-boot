package com.brijesh.firstjobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody Review review, @PathVariable Long companyId){
        reviewService.createReview(review,companyId);
        return new ResponseEntity<>("Review Added Successfully",HttpStatus.CREATED);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviewsOfASpecificCompany(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
}


/*

create a review about a company : /companies/{companyId}/reviews

get all reviews about a specific company : /companies/{companyId}/reviews

get single review about a specific company : /companies/{companyId}/reviews/{reviewId}


update review : /companies/{companyId}/reviews/{reviewId}

delete review : /companies/{companyId}/reviews/{reviewId}
 */
