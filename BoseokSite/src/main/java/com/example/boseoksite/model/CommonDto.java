package com.example.boseoksite.model;

import com.example.boseoksite.paging.Criteria;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * packageName : com.example.boseoksite.model
 * fileName : CommonDto
 * author : Seok
 * date : 2022-05-18
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-18         Seok          최초 생성
 */

// lombok에서 제공하는 어노테이션
@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommonDto extends Criteria {
    // 삭제여부 (Y,N)
    private String deleteYn;
    // 등록일 - 항상 넣어주는게 좋음
    private LocalDateTime insertTime;
    // 수정일 - 항상 넣어주는게 좋음
    private LocalDateTime updateTime;
    // 삭제일 - 항상 넣어주는게 좋음
    private LocalDateTime deleteTime;
}