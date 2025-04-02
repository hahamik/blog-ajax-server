package shop.mtcoding.blog.board;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm(BoardRequest.WriteDTO requestDTO) {
        boardRepository.insert(requestDTO);
        return "board/saveForm";
    }


    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        // 1. ssr : template engine
        request.setAttribute("boardId", id);
        // 2. cookie
        Cookie cookie = new Cookie("boardId", id + "");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        // 응답의 헤더 -> Set-Cookie : boardId=5


        return "board/updateForm";
    }
}