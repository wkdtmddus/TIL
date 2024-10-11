package com.ssafy.ios.lineup.backend.domain.dto.recruit;

import com.ssafy.ios.lineup.backend.common.constant.RecruitSort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class RecruitSearchFilter {

    private String keyword;

    private RecruitSort recruitSort;

//    private String[] district;
//
//    private String gender;
//
//    private Integer[] ages;


}
