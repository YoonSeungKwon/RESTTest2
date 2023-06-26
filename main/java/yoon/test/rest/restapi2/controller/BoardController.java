package yoon.test.rest.restapi2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import yoon.test.rest.restapi2.domain.Post;
import yoon.test.rest.restapi2.message.Message;
import yoon.test.rest.restapi2.message.StatusEnum;
import yoon.test.rest.restapi2.service.BoardService;
import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<Message> read(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
        Message message = new Message();
        message.setStatusEnum(StatusEnum.OK);
        message.setData(boardService.read());
        message.setMessage("사용자 정보");
        return new ResponseEntity<>(message,httpHeaders,HttpStatus.OK);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Message> read(@PathVariable String id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
        Message message = new Message();
        message.setStatusEnum(StatusEnum.OK);
        message.setData(boardService.read(id));
        message.setMessage("사용자 정보");
        return new ResponseEntity<>(message,httpHeaders,HttpStatus.OK);
    }

    @PostMapping("/board")
    public ResponseEntity<Message> join(Post post){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
        Message message = new Message();
        message.setStatusEnum(StatusEnum.OK);
        message.setMessage("사용자 추가");
        message.setData(post);
        boardService.join(post);
        return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
    }

    @PutMapping("/board/{id}")
    public ResponseEntity<Message> put(@PathVariable String id, Post post){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
        Message message = new Message();
        if(boardService.put(id, post).equals("E")) {
            message.setStatusEnum(StatusEnum.BAD_REQUEST);
            message.setMessage("사용자 없음");
            message.setData("none");
        }
        else{
            message.setStatusEnum(StatusEnum.OK);
            message.setMessage(post.getTitle() + "수정 완료");
            message.setData(post);
        }
        return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK );
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Message> delete(@PathVariable String id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
        Message message = new Message();
        String result = boardService.unlink(id);
        if(!result.equals("")){
            message.setStatusEnum(StatusEnum.OK);
            message.setMessage("글 삭제 완료");
            message.setData(result);
        }
        else{
            message.setStatusEnum(StatusEnum.BAD_REQUEST);
            message.setMessage("잘못된 정보");
            message.setData("none");
        }
        return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
    }
}
