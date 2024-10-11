package com.ssafy.ios.lineup.backend.domain.repository.recruit;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ios.lineup.backend.common.constant.RecruitSort;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitDetailResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitSearchFilter;
import com.ssafy.ios.lineup.backend.domain.entity.QRecruit;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static com.ssafy.ios.lineup.backend.common.constant.RecruitStatus.*;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.repository.recruit fileName       :
 * RecruitCustomRepositoryImpl author         : moongi date           : 9/16/24 description    :
 */
@Repository
@RequiredArgsConstructor
public class RecruitCustomRepositoryImpl implements RecruitCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final QRecruit r = QRecruit.recruit;

    @Override
    public RecruitDetailResponse findRecruitDetailById(Long recruitId) {

        return queryFactory
                .select(Projections.fields(
                        RecruitDetailResponse.class,
                        r.id,
                        r.writer.id.as("writerId"),
                        r.title,
                        r.content,
                        r.recruitImg,
                        r.placeType,
//                        new CaseBuilder()
//                                .when(r.placeType.eq(PlaceType.POP_UP)).then(PlaceType.POP_UP.getValue()) // "팝업스토어"
//                                .when(r.placeType.eq(PlaceType.DEPARTMENT)).then(PlaceType.DEPARTMENT.getValue()) // "백화점"
//                                .when(r.placeType.eq(PlaceType.RESTAURANT)).then(PlaceType.RESTAURANT.getValue()) // "음식점"
//                                .when(r.placeType.eq(PlaceType.CAFE)).then(PlaceType.CAFE.getValue()) // "카페"
//                                .when(r.placeType.eq(PlaceType.ETC)).then(PlaceType.ETC.getValue()) // "기타"
//                                .otherwise("알 수 없음").as("placeType"),  // 기본값 설정
//                        Expressions.stringTemplate("cast({0} as string)", r.placeType).as("placeType"),
                        // Enum 타입 필드를 문자열로 변환하여 매핑

//                        Expressions.stringTemplate("{0}", r.placeType.stringValue())
//                                .as("placeType"),
//                        Expressions.stringTemplate("{0}", r.recruitStatus.toString())
//                                .as("recruitStatus"),
//                        Expressions.stringTemplate("{0}", r.serviceType.toString())
//                                .as("serviceType"),
////                        Expressions.stringTemplate("{0}", r.serviceType).as("serviceType"),
//                        // LocalDateTime을 문자열로 변환하여 매핑
//                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y.%m.%d')", r.startAt)
//                                .as("startDate"),
//                        Expressions.stringTemplate("DATE_FORMAT({0}, '%H:%i')", r.startAt)
//                                .as("startAt"),
//                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y.%m.%d')", r.endAt)
//                                .as("endDate"),
//                        Expressions.stringTemplate("DATE_FORMAT({0}, '%H:%i')", r.endAt)
//                                .as("endAt"),
//                        r.placeType.,
//                        r.recruitStatus.value,
//                        r.serviceType.value,
//                        formatDate(r.startAt),
                        r.endAt,
                        r.location,
                        r.successSalary,
                        r.failSalary,
                        r.createdAt,
                        r.modifiedAt
                ))
                .from(r)
                .where(r.id.eq(recruitId))
                .fetchOne();
    }

    private String formatDate(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        String dayOfWeek = date.getDayOfWeek()
                .getDisplayName(TextStyle.SHORT, Locale.KOREAN);  // 요일 변환
        return date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) + " (" + dayOfWeek + ")";
    }

    private String formatTime(LocalDateTime time) {
        if (time == null) {
            return "";
        }
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public List<Recruit> findExpiredRecruits(LocalDateTime now) {
        return queryFactory
                .selectFrom(r)
                .where(
                        r.endAt.before(now)
                                .and(r.recruitStatus.in(RECRUITING, COMPLETED))
                )
                .fetch();
    }

    @Override
    public List<RecruitCardResponse> getRecruits() {
        return queryFactory
                .select(Projections.fields(
                        RecruitCardResponse.class,
                        r.id,
                        r.title,
                        r.recruitImg,
                        r.successSalary,
                        r.location.streetAddress,
                        new CaseBuilder()
                                .when(r.recruitStatus.eq(RECRUITING))
                                .then(RECRUITING.getValue()) // "구인 중"
                                .when(r.recruitStatus.eq(COMPLETED))
                                .then(COMPLETED.getValue()) // "구인 완료"
                                .when(r.recruitStatus.eq(EXPIRED))
                                .then(EXPIRED.getValue()) // "기간 만료"
                                .otherwise("알 수 없음").as("recruitStatus"),  // 기본값 설정
                        // 요일을 한글로 변환하는 CASE 문
                        Expressions.stringTemplate(
                                "CASE DAYOFWEEK({0}) " +
                                        "WHEN 1 THEN '일' " +
                                        "WHEN 2 THEN '월' " +
                                        "WHEN 3 THEN '화' " +
                                        "WHEN 4 THEN '수' " +
                                        "WHEN 5 THEN '목' " +
                                        "WHEN 6 THEN '금' " +
                                        "WHEN 7 THEN '토' " +
                                        "END", r.startAt
                        ).as("dayOfWeek"),
                        // LocalDateTime을 'yyyy.MM.dd (요일)' 형식으로 변환
                        Expressions.stringTemplate(
                                "CONCAT(DATE_FORMAT({0}, '%Y. %m. %d'), ' (', {1}, ')')", r.startAt,
                                Expressions.stringTemplate(
                                        "CASE DAYOFWEEK({0}) " +
                                                "WHEN 1 THEN '일' " +
                                                "WHEN 2 THEN '월' " +
                                                "WHEN 3 THEN '화' " +
                                                "WHEN 4 THEN '수' " +
                                                "WHEN 5 THEN '목' " +
                                                "WHEN 6 THEN '금' " +
                                                "WHEN 7 THEN '토' " +
                                                "END", r.startAt
                                )).as("startDate"),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%H:%i')", r.startAt)
                                .as("startAt"),
                        Expressions.stringTemplate(
                                "CONCAT(DATE_FORMAT({0}, '%Y. %m. %d'), ' (', {1}, ')')", r.endAt,
                                Expressions.stringTemplate(
                                        "CASE DAYOFWEEK({0}) " +
                                                "WHEN 1 THEN '일' " +
                                                "WHEN 2 THEN '월' " +
                                                "WHEN 3 THEN '화' " +
                                                "WHEN 4 THEN '수' " +
                                                "WHEN 5 THEN '목' " +
                                                "WHEN 6 THEN '금' " +
                                                "WHEN 7 THEN '토' " +
                                                "END", r.endAt
                                )).as("endDate"),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%H:%i')", r.endAt)
                                .as("endAt"),
                        r.viewCount,
                        r.likeCount
                ))
                .from(r)
                .where(r.deletedAt.isNull())
                .fetch();
    }

    @Override
    public List<RecruitCardResponse> searchRecruitByFilter(
            RecruitSearchFilter recruitSearchFilter) {
        return queryFactory
                .select(Projections.fields(
                        RecruitCardResponse.class,
                        r.id,
                        r.title,
                        r.recruitImg,
                        r.successSalary,
                        r.location.streetAddress,
                        new CaseBuilder()
                                .when(r.recruitStatus.eq(RECRUITING))
                                .then(RECRUITING.getValue()) // "구인 중"
                                .when(r.recruitStatus.eq(COMPLETED))
                                .then(COMPLETED.getValue()) // "구인 완료"
                                .when(r.recruitStatus.eq(EXPIRED))
                                .then(EXPIRED.getValue()) // "기간 만료"
                                .otherwise("알 수 없음").as("recruitStatus"),  // 기본값 설정
                        // 요일을 한글로 변환하는 CASE 문
                        Expressions.stringTemplate(
                                "CASE DAYOFWEEK({0}) " +
                                        "WHEN 1 THEN '일' " +
                                        "WHEN 2 THEN '월' " +
                                        "WHEN 3 THEN '화' " +
                                        "WHEN 4 THEN '수' " +
                                        "WHEN 5 THEN '목' " +
                                        "WHEN 6 THEN '금' " +
                                        "WHEN 7 THEN '토' " +
                                        "END", r.startAt
                        ).as("dayOfWeek"),
                        // LocalDateTime을 'yyyy.MM.dd (요일)' 형식으로 변환
                        Expressions.stringTemplate(
                                "CONCAT(DATE_FORMAT({0}, '%Y.%m.%d'), ' (', {1}, ')')", r.startAt,
                                Expressions.stringTemplate(
                                        "CASE DAYOFWEEK({0}) " +
                                                "WHEN 1 THEN '일' " +
                                                "WHEN 2 THEN '월' " +
                                                "WHEN 3 THEN '화' " +
                                                "WHEN 4 THEN '수' " +
                                                "WHEN 5 THEN '목' " +
                                                "WHEN 6 THEN '금' " +
                                                "WHEN 7 THEN '토' " +
                                                "END", r.startAt
                                )).as("startDate"),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%H:%i')", r.startAt)
                                .as("startAt"),
                        Expressions.stringTemplate(
                                "CONCAT(DATE_FORMAT({0}, '%Y.%m.%d'), ' (', {1}, ')')", r.endAt,
                                Expressions.stringTemplate(
                                        "CASE DAYOFWEEK({0}) " +
                                                "WHEN 1 THEN '일' " +
                                                "WHEN 2 THEN '월' " +
                                                "WHEN 3 THEN '화' " +
                                                "WHEN 4 THEN '수' " +
                                                "WHEN 5 THEN '목' " +
                                                "WHEN 6 THEN '금' " +
                                                "WHEN 7 THEN '토' " +
                                                "END", r.endAt
                                )).as("endDate"),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%H:%i')", r.endAt)
                                .as("endAt"),
                        r.viewCount,
                        r.likeCount
                ))
                .from(r)
                .where(createWhereClause(recruitSearchFilter).and(r.deletedAt.isNull()))
                .orderBy(createOrderSpecifier(recruitSearchFilter.getRecruitSort()))
                .fetch();
    }

    /* TODO: 공고 상태(구인 중, 구인 완료, 기간 만료) 에 따른 필터 */

    /* TODO: 공고 날짜에 다른 필터 */

    /* 공고 정렬(최신 등록순, 마감 임박순, 급여 높은 순, 조회수 높은 순, 좋아요 높은 순) 에 따른 필터*/
    private OrderSpecifier<?> createOrderSpecifier(RecruitSort sort) {
        if (sort == null) {
            return new OrderSpecifier<>(Order.ASC,
                    Expressions.numberTemplate(Double.class, "function('RAND')"));
        }
        return switch (sort) {
            case LATEST -> new OrderSpecifier<>(Order.DESC, r.createdAt);
            case DEADLINE_SOON -> new OrderSpecifier<>(Order.ASC, r.endAt);
            case HIGHEST_SALARY -> new OrderSpecifier<>(Order.DESC, r.successSalary);
            case MOST_VIEWED -> new OrderSpecifier<>(Order.DESC, r.viewCount);
            case MOST_LIKED -> new OrderSpecifier<>(Order.DESC, r.likeCount);
        };

    }

    private BooleanExpression createWhereClause(RecruitSearchFilter recruitSearchFilter) {
        return r.deletedAt.isNull()
                .and(r.createdAt.isNotNull())
                .and(containKeyword(recruitSearchFilter.getKeyword()));
//                .and(inLevels(recruitSearchFilter.getLevels()))
//                .and(inGenre(recruitSearchFilter.getGenres()))
//                .and(inPrice(recruitSearchFilter.getPrices()));
    }

    private BooleanExpression containKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return r.title.contains(keyword);
    }

//    private BooleanExpression inRegion(String[] regions) {
//        if (regions == null || regions.length == 0) {
//            return null;
//        }
//        return r.location.district.in(regions);
//    }

//    private BooleanExpression inGender(String keyword) {
//        if (keyword == null || keyword.isEmpty()) {
//            return null;
//        }
//        return r.title.contains(keyword);
//    }

//    private BooleanExpression inAge(String keyword) {
//        if (keyword == null || keyword.isEmpty()) {
//            return null;
//        }
//        return r.title.contains(keyword);
//    }

//    private BooleanExpression inCategory(String keyword) {
//        if (keyword == null || keyword.isEmpty()) {
//            return null;
//        }
//        return r.title.contains(keyword);
//    }
}
