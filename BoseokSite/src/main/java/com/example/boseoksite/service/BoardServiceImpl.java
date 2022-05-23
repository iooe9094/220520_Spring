package com.example.boseoksite.service;

import com.example.boseoksite.dao.BoardDao;
import com.example.boseoksite.model.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * packageName : com.example.boseoksite.service
 * fileName : BoardServiceImpl
 * author : Seok
 * date : 2022-05-18
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-18         Seok          최초 생성
 */

// @Service: 스프링에 객체로 생성하는 어노테이션
@Service
public class BoardServiceImpl implements BoardService {

    // 아래 멤버 변수에 스프링의 객체를 넣어준다
    // Autowired 없이 private BoardDao boardDao만 하면 객체 정의만 된 상태임
    // Autowired 걸면 자동으로 객체를 '생성'해준다
    @Autowired
    private BoardDao boardDao;

    // 게시판에 Idx(글번호)가 없으면 insert문 실행 (사용자가 새 글쓰기 버튼을 클릭)
    // 글 번호가 있으면 update문 실행 (사용자가 글 목록 버튼을 클릭 + 수정 버튼 클릭)
    @Override
    public boolean registerBoard(BoardDto params) {
        // insert 또는 update 결과를 저장하는 변수
        // 위의 sql문이 정상 실행되면 1, 아니면 다른 값(-1, ...)
        int queryResult = 0;

        if(params.getIdx() == null) {
            // 새 글쓰기 (insert문 실행)
            queryResult = boardDao.insertBoard(params);
        } else {
            // 상세 목록에서 글 수정 (update문 실행)
            queryResult = boardDao.updateBoard(params);

        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public BoardDto getBoardDetail(Long idx) {
        // 글번호(idx)에 해당하는 상세 목록 보기 (select문 실행 - 1건)
        return boardDao.selectBoardDetail(idx);
    }

    // 전체 게시물 select하는 서비스
    @Override
    public List<BoardDto> getBoardAllList() {
        List<BoardDto> boardList = Collections.emptyList();

        // select(전체 게시물)문 실행
        boardList = boardDao.selectBoardAllList();
        return boardList;
    }

    // 게시판 번호를 받아 게시물을 삭제하는 서비스
    @Override
    public boolean deleteBoard(Long idx) {
        int queryResult = 0;

        // 게시물이 있는지 확인 하는 절차 (Select-1건)
        // 게시물이 있으면 board != null
        BoardDto board = boardDao.selectBoardDetail(idx);

        // Delete_Yn : "N"일 경우만 삭제를 진행 + board가 null이 아닌 경우
        if(board!=null && "N".equals(board.getDeleteYn())) {
            // 게시물 삭제 서비스 (내부적으로 진짜 삭제는 안함)
            queryResult = boardDao.deleteBoard(idx);
        }
        return (queryResult == 1) ? true : false;
    }

    // 게시판 페이징 처리를 위한 서비스 구현
    @Override
    public List<BoardDto> getBoardList(BoardDto params) {
        List<BoardDto> boardDto = Collections.emptyList();

        // DB에 prams에 해당하는 데이터가 있는지 먼저 확인
        int boardTotalCount = boardDao.selectBoardTotalCount(params);

        if(boardTotalCount > 0) {
            // 페이징 처리 서비스 호출
            boardDto = boardDao.selectBoardList(params);
        }

        return boardDto;
    }
}