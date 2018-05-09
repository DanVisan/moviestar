package upb.acs.movietaste.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upb.acs.movietaste.models.RatingModel;
import upb.acs.movietaste.services.RateService;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("ratings")
public class RateController {

    private final RateService rateService;

    @Autowired
    public RateController(final RateService rateService) {
        this.rateService = Objects.requireNonNull(rateService, "rateService must not be null");
    }

    @GetMapping
    public Page<RatingModel> getRatedMovies(@RequestParam(value = "title_like", defaultValue = "") final String searchTerm,
                                            @RequestParam("page") final int page,
                                            @RequestParam("size") final int size) {
        return rateService.getRatings(searchTerm, page, size);
    }

    @PostMapping
    public ResponseEntity<RatingModel> saveRating(@RequestBody @Valid final RatingModel ratingModel) {
        rateService.saveRating(ratingModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RatingModel> updateRating(@RequestBody @Valid final RatingModel ratingModel) {
        rateService.updateRating(ratingModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<RatingModel> deleteRating(@PathVariable("ratingId") final Long ratingId) {
        rateService.deleteRating(ratingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
