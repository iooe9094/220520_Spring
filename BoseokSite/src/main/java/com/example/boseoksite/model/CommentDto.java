package com.example.boseoksite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

/**
 * packageName : com.example.boseoksite.model
 * fileName : CommentDto
 * author : Seok
 * date : 2022-05-19
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-19         Seok          최초 생성
 */
@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class CommentDto {
    private int idx; // 댓글번호
    private int boardIdx; // 게시판번호
    private String content; // 댓글내용
    private String writer; // 댓글작성자
    private String deleteYn; // 삭제여부(Y,N)
    private LocalDateTime insertDate; // 댓글등록일시
    private LocalDateTime updateDate; // 댓글수정일시
    private LocalDateTime deleteDate; // 댓글삭제일시
}