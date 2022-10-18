package com.osckorea.board.api;

import com.osckorea.board.dto.ArticleDto;
import com.osckorea.board.entity.Article;
import com.osckorea.board.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*") // 프론트앤드와 통신 CORS 문제 해결
public class BoardApiController {

    @Autowired // DI 생성 객체를 가져와 연결.
    private ArticleService articleService;

//    @Autowired
//    private ArticleRepository articleRepository;

    // FinAll Get
    @GetMapping(value = "/api/boards")
    public List<Article> index() {
        return articleService.index();
    }

    // Get by id
    @GetMapping(value = "/api/boards/{id}")
    public Article search(@PathVariable Long id) {

        return articleService.search(id);
    }

    // POST
    @PostMapping(value = "/api/boards/posts")
    public ResponseEntity<Article> post(@RequestBody ArticleDto dto) {
        Article posts = articleService.dbPosts(dto);
        return (posts != null) ?
                ResponseEntity.status(HttpStatus.OK).body(posts) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping(value = "/api/boardsR/{id}")
    public ResponseEntity<Article> update(
            @PathVariable Long id,
            @RequestBody ArticleDto dto) {

        // 서비스에게 update 메소드를 시킨다( id와 dto를 넘겨준다)
        // updated 라는 객체로 넘겨준다.
        Article updated = articleService.revision(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // DELETE
    @DeleteMapping(value = "/api/boardsD/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
    // 트랜잭션 -- 반드시 성공해야하는 일련의 과정
    // 실패하면 다시 실패한부분으로 돌아올것.
//    @PostMapping(value = "/api/transaction")
//    public ResponseEntity<List<Article>> transactionExample(@RequestBody List<ArticleDto> dtos) {
//        List<Article> creatList = articleService.createArticles(dtos);
//        return (creatList != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(creatList) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//}

//    // PATCH
//    @PatchMapping(value = "/api/board/{id}")
//    public ResponseEntity<Article> patch(@PathVariable Long id,
//                                         @RequestBody ArticleDto dto) {
//
//        // ResponsEntity<> 로 묶으면 상태코드를 확인하게 보내줄 수 있어.
//
//        // 1. 수정용 엔티티 생성
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        // 2. 대상 엔티티를 조회
//        Article targetEntity = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못 요청한 경우.
//        if ( targetEntity == null || id != article.getId()) {
//            // 400응답 반응 정의
//            log.info("잘못된 요청 id : {}, article {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        }
//
//        // 4. 업데이트 및 정상 응답(200반응)
//        targetEntity.patch(article);
//        Article updated = articleRepository.save(targetEntity);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
