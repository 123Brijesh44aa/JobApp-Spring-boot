package com.brijesh.firstjobapp.review;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody Review review, @PathVariable Long companyId){
        if (reviewService.createReview(review,companyId)) {
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Review Creation Unsuccessful", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviewsOfASpecificCompany(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewOfASpecificCompany(@PathVariable Long companyId, @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewOfASpecificCompany(companyId,reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> editReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review updatedReview){
        Boolean isReviewUpdated = reviewService.editReview(companyId,reviewId,updatedReview);
        if (isReviewUpdated){
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        Boolean isReviewDeleted = reviewService.isReviewDeleted(companyId,reviewId);
        if (isReviewDeleted){
            return new ResponseEntity<>("Review Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


/*

create a review about a company : /companies/{companyId}/reviews

get all reviews about a specific company : /companies/{companyId}/reviews

get single review about a specific company : /companies/{companyId}/reviews/{reviewId}


update review : /companies/{companyId}/reviews/{reviewId}

delete review : /companies/{companyId}/reviews/{reviewId}
 */
