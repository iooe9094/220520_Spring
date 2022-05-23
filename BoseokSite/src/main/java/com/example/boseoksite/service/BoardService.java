package com.example.boseoksite.service;

import com.example.boseoksite.model.BoardDto;

import java.util.List;

/**
 * packageName : com.example.boseoksite.service
 * fileName : BoardService
 * author : Seok
 * date : 2022-05-18
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-18         Seok          최초 생성
 */

public interface BoardService {

    // 게시판에 insert 하는 서비스
    boolean registerBoard(BoardDto params);

    // 상세 목록을 확인하는 서비스 (Select)
    BoardDto getBoardDetail(Long idx);

    // 게시판 페이징 처리를 위한 서비스
    public List<BoardDto> getBoardList(BoardDto params);

    // 게시판의 글 목록을 가져오는 서비스 (Select:전체 글목록)
    public List<BoardDto> getBoardAllList();

    // 게시판 글을 삭제하는 서비스 (Update) : delete_YN = 'Y' 수정
    public boolean deleteBoard(Long idx);
}