package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:8090/board/write

    public String boardWriteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    //public String boardWritePro(@RequestParam(name = "title") String title, @RequestParam(name = "content") String content) // 1. 데이터 넘어오는걸 먼저 확인
    public String boardWritePro(Board board, Model model) {

        //System.out.println(board.getTitle());
        boardService.write(board); //@Autowired쓰고 주입시켜주면 에러사라짐

        model.addAttribute("message", "글 작성이 완료되었습니다.");

        model.addAttribute("searchUrl", "/board/list");


        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {

        model.addAttribute("list", boardService.boardLIst());
                            //list라는 이름으로 보낼건데, 여기에 뭘 담아서 보낼거냐?
                            //boardService.boardLIst() 메서드를 실행하면 반환되는게 있는데 그걸 "list"에 담아서 넘기겠다!!

        return "boardlist";
    }

    @GetMapping("/board/view") // localhost:8090/board/view?id=1
    public String boardView(Model model, Integer id) {

        model.addAttribute("board", boardService.boardView(id));

        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}") //PathVariable이란? {id} 이부분이 인식이되서 인티저 형식의 id로 들어오는거임
    public String boardModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("board", boardService.boardView(id));

        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board) {

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);

        return "redirect:/board/list";
    }
}

