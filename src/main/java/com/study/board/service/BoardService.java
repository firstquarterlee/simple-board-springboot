package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired // 이거쓰면 스프링빈이 알아서 읽어들임
    private BoardRepository boardRepository;
    // 여기에 그대로 주입해줌(DI)

    // 글 작성 처리
    public void write(Board board) {
                     //Board라는 클래스에서
                     //board라는 변수를 파라미터(매개변수)로 받아줌
        boardRepository.save(board);

    }

    // 게시글 리스트 처리
    public List<Board> boardLIst() {

        return boardRepository.findAll();
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id) {

        return boardRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }
}
