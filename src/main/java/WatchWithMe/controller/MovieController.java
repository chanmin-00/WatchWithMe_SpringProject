package WatchWithMe.controller;

import WatchWithMe.dto.request.movie.MovieListRequestDto;
import WatchWithMe.global.response.ApiResponse;
import WatchWithMe.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    // 영화 단건 조회
    @GetMapping("/findOne/{movieId}")
    @Operation(summary = "영화 단건 조회")
    public ApiResponse findMovie(@PathVariable Long movieId) {
        return ApiResponse.onSuccess("영화 조회에 성공했습니다.", movieService.getMovieById(movieId));
    }

    // 영화 전체 조회, 페이징
    @GetMapping("/findList")
    @Operation(summary = "영화 전체 조회", description = "조회 페이지 입력 필요")
    public ApiResponse findMovieList(@Valid @RequestParam(value="page", defaultValue="1") int page) {
        return ApiResponse.onSuccess("영화 목록 조회에 성공했습니다", movieService.getMovieList(page));
    }

    // 영화 전체 조회, 평점 높음 순 조회
    @GetMapping("/find/ratingDescList")
    @Operation(summary = "영화 전체 조회, 높은 평점순", description = "조회 페이지 입력  필요")
    public ApiResponse findRatingDescMovieList(@Valid @RequestParam(value="page", defaultValue="1") int page) {
        return ApiResponse.onSuccess("영화 목록 조회에 성공했습니다", movieService.getUserRatingDescMovieList(page));
    }

    // 영화 전체 조회, 평점 낮음 순 조회
    @GetMapping("/find/ratingAscList")
    @Operation(summary = "영화 전체 조회, 낮은 평점순", description = "조회 페이지 입력  필요")
    public ApiResponse findRatingAscMovieList(@Valid @RequestParam(value="page", defaultValue="1") int page) {
        return ApiResponse.onSuccess("영화 목록 조회에 성공했습니다", movieService.getUserRatingAscMovieList(page));
    }

    // 영화 전체 조회, 리뷰 많음 순 조회
    @GetMapping("/find/reviewMostList")
    @Operation(summary = "영화 전체 조회, 리뷰 많은순", description = "조회 페이지 입력  필요")
    public ApiResponse findReviewMostList(@Valid  @RequestParam(value = "page", defaultValue = "1") int page) {
        return ApiResponse.onSuccess("영화 목록 조회에 성공했습니다", movieService.getReviewMostMovieList(page));
    }

    // 영화 조건 검색
    @PostMapping("/search")
    @Operation(summary = "영화 조건 검색", description = "다양한 조건(영화명, 배우, 장르, 개봉 연도 등)으로 영화 검색")
    public ApiResponse searchByCondition(@Valid @RequestBody MovieListRequestDto movieListRequestDto, @RequestParam(value="page", defaultValue="1") int page) {
        return ApiResponse.onSuccess("영화 조건 검색에 성공했습니다", movieService.searchMovieList(movieListRequestDto, page));
    }

    // 영화 조건 검색, 평점 높음순
    @PostMapping("/search/ratingDesc")
    @Operation(summary = "영화 조건 검색, 평점 높음순", description = "다양한 조건(영화명, 배우, 장르, 개봉 연도 등)으로 영화 검색")
    public ApiResponse searchByConditionRatingDesc(@Valid @RequestBody MovieListRequestDto movieListRequestDto, @RequestParam(value="page", defaultValue="1") int page) {
        return ApiResponse.onSuccess("영화 조건 검색에 성공했습니다", movieService.searchMovieListRatingDesc(movieListRequestDto, page));
    }

    // 영화 조건 검색, 평점 낮음순
    @PostMapping("/search/ratingAsc")
    @Operation(summary = "영화 조건 검색, 평점 낮음순", description = "다양한 조건(영화명, 배우, 장르, 개봉 연도 등)으로 영화 검색")
    public ApiResponse searchByConditionRatingAsc(@Valid @RequestBody MovieListRequestDto movieListRequestDto, @RequestParam(value="page", defaultValue="1") int page) {
        return ApiResponse.onSuccess("영화 조건 검색에 성공했습니다", movieService.searchMovieListRatingAsc(movieListRequestDto, page));
    }
}
