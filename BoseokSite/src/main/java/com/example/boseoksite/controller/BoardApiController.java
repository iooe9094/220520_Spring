package com.example.boseoksite.controller;

import com.example.boseoksite.model.BoardDto;
import com.example.boseoksite.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName : com.example.boseoksite.controller
 * fileName : BoardApiController
 * author : Seok
 * date : 2022-05-18
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-18         Seok          최초 생성
 */

// 비교) @Controller: html, jsp, 타임리프 파일로 바로 출력
// 샘플)http://localhost:8000/api/board/
// @RequestMapping("/api") : 위의 api를 자동으로 설정
// @RestController : json/text로 프론트엔드에 전송
@RestController
@RequestMapping("/api")
public class BoardApiController {

    // Autowired 사용해야 스프링에서 객체를 받아 아래 멤버변수에 넣을 수 있음
    @Autowired
    BoardServiceImpl boardService; // Autowired 없으면 단순 객체 정의

    @GetMapping("/board/write/{idx}")
    // @PathVariable : @PathVariable("idx")의 idx가 위의 {idx}와 연결되게 해줌
    public BoardDto openBoardWrite(@PathVariable("idx") Long idx) {
        // 상세 목록보기 서비스를 호출(select - 1건)
        BoardDto detail = boardService.getBoardDetail(idx);
        return detail;
    }

    // @PostMapping : insert할 때 사용하는 어노테이션
    // @RequestBody : 입력 테스트 시 json 형태로 데이터를 전송할 수 있음
    @PostMapping("/board/register")
    public List<BoardDto> registerBoard(@RequestBody BoardDto boardDto) {

//        try {
//            // insert문 실행
//            boolean isRegistered = boardService.registerBoard(boardDto);
//        } catch(DataAccessException e) {
//            // DB 관련된 에러는 여기로 들어옴
//            // TODO => DB 처리 과정에 문제가 발생했다는 메세지를 출력
//        } catch(Exception e) {
//            // DB 외의 에러일 경우 여기로 들어옴
//            // TODO => 시스템에 문제가 발생했다는 메세지를 출력
//        }
        // insert 완료 후 데이터 확인을 위한 전체 조회 (select)
        return boardService.getBoardAllList();
    }

    // @PutMapping : update문 실행
    @PutMapping(value="/board/delete/{idx}")
    public List<BoardDto> deleteBoard(@PathVariable("idx") Long idx) {

        // 삭제 서비스 호출
        boolean isDelete = boardService.deleteBoard(idx);

        // 삭제 되었는지 전체 조회 실행 (select:전체조회)
        return boardService.getBoardAllList();
    }

    // 페이징 처리를 위한 게시물 검색 메뉴
    @GetMapping("/board/list/cur-page-no/{currentPageNo}/records-per-page/{recordsPerPage}")
    public List<BoardDto> openBoardList(BoardDto params) {

        return boardService.getBoardList(params);
    }

    // 페이징 처리를 위한 게시물 검색 메뉴 2
    @GetMapping("/board/list/cpage/{currentPageNo}/rpage/{recordsPerPage}/sword/{searchKeyword}/stype/{searchType}")
    public List<BoardDto> openBoardList2(BoardDto params) {

        return boardService.getBoardList(params);
    }
}