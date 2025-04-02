package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardRepository boardRepository;

    @PutMapping("/api/boards/{id}")
    public ApiUtil<?> update(@PathVariable("id") int id, @RequestBody BoardRequest.UpdateDTO requestDTO) {
        boardRepository.updateById(requestDTO, id);
        return new ApiUtil<>(null);
    }


    @PostMapping("/api/boards")
    public ApiUtil<?> write(@RequestBody BoardRequest.WriteDTO requestDTO) {
        boardRepository.insert(requestDTO);
        return new ApiUtil<>(null);
    }

    @DeleteMapping("/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id) {
        Board board = boardRepository.selectOne(id);
        boardRepository.deleteById(id);
        return new ApiUtil<>(null);
    }

    @GetMapping("/api/boards/{id}")
    public ApiUtil<?> findById(@PathVariable("id") int id) {
        Board board = boardRepository.selectOne(id);

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        return new ApiUtil<>(board); // MessageConverter
    }

    @GetMapping("/api/boards")
    public ApiUtil<?> findAll() {
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); // MessageConverter
    }
}








