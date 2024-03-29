package WatchWithMe.repository.movie;

import WatchWithMe.domain.Movie;
import WatchWithMe.dto.request.movie.MovieListRequestDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import java.util.List;
import static WatchWithMe.domain.QMovie.movie;

@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 영화 제목으로 검색한 경우
    @Override
    public List<Movie> search(MovieListRequestDto movieListRequestDto, Pageable pageable){
        return queryFactory.
                select(movie).from(movie).where(movieNameEq(movieListRequestDto.titleOrActorOrDirector()),
                        movieOpenYearEq(movieListRequestDto.openYear()),
                        movieGenreEq(movieListRequestDto.genre()),
                        movieUserRatingBetween(movieListRequestDto.userRatingHigh(), movieListRequestDto.userRatingLow()))
                        .orderBy(movie.createdAt.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();
    }

    private BooleanExpression movieNameEq(String title){
        if (title == null){
            return null;
        }
        return movie.title.eq(title);
    }

    private BooleanExpression movieOpenYearEq(String openYear){
        if (openYear == null){
            return null;
        }
        return movie.openYear.eq(openYear);
    }

    private BooleanExpression movieGenreEq(String genre){
        if (genre == null){
            return null;
        }
        return movie.genre.eq(genre);
    }

    private BooleanExpression movieUserRatingBetween(Integer userRatingLow, Integer userRatingHigh){
        if (userRatingLow == null || userRatingHigh == null){
            return null;
        }
        return movie.userRating.between(userRatingLow, userRatingHigh);
    }
}
