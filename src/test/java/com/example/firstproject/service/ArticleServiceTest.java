package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    @Transactional
    void index() {
        // 1. 테스트 데이터 삽입
        articleService.create(new ArticleForm(null, "가가가가", "1111"));
        articleService.create(new ArticleForm(null, "나나나나", "2222"));
        articleService.create(new ArticleForm(null, "다다다다", "3333"));

        // 2. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));

        // 3. 실제 데이터
        List<Article> articles = articleService.index();

        // 4. 비교 및 검증
        assertEquals(expected.size(), articles.size());
//        // 1. 예상 데이터
//        Article a = new Article(1L, "가가가가", "1111");
//        Article b = new Article(2L, "나나나나", "2222");
//        Article c = new Article(3L, "다다다다", "3333");
//        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
//
//        // 2. 실제 데이터
//        List<Article> articles = articleService.index();
//
//        // 3. 비교 및 검증
//        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
//        // 1. 예상 데이터
//        Long id = 1L;
//        Article expected = new Article(id, "가가가가", "1111");
//        // 2. 실제 데이터
//        Article article = articleService.show(id);
//        // 3. 비교 및 검증
//        assertEquals(expected.toString(), article.toString());
        // 테스트 데이터를 먼저 삽입
        ArticleForm dto = new ArticleForm(null, "가가가가", "1111");
        Article created = articleService.create(dto);

        // 실제로 생성된 id 사용
        Long id = created.getId();

        // 1. 예상 데이터
        Article expected = new Article(id, "가가가가", "1111");

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
//        // 1. 예상 데이터
//        String title = "라라라라";
//        String content = "4444";
//        ArticleForm dto = new ArticleForm(null, title, content);
//        Article expected = new Article(4L, title, content);
//        // 2. 실제 데이터
//        Article article = articleService.create(dto);
//        // 3. 비교 및 검증
//        assertEquals(expected.toString(), article.toString());
        // 1. 예상 데이터 (id는 동적으로 처리)
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 예상 데이터와 실제 데이터 비교 (id는 실제로 생성된 값 사용)
        assertNotNull(article);  // article이 null이 아닌지 확인
        assertEquals(title, article.getTitle());  // title 확인
        assertEquals(content, article.getContent());  // content 확인
    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    // 13장 셀프체크 1. update()를 성공한 경우 1
    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        // 1. 테스트 데이터를 먼저 삽입
        ArticleForm createDto = new ArticleForm(null, "가가가가", "1111");
        Article created = articleService.create(createDto);

        // 2. 예상 데이터 (업데이트할 값)
        Long id = created.getId();
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);

        // 3. 실제 데이터
        Article article = articleService.update(id, dto);

        // 4. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
//        // 예상 데이터
//        Long id = 1L;
//        String title = "가나다라";
//        String content = "1234";
//        ArticleForm dto = new ArticleForm(id, title, content);
//        Article expected = new Article(id, title,content);
//        // 실제 데이터
//        Article article = articleService.update(id, dto);
//        // 비교 및 검증
//        assertEquals(expected.toString(), article.toString());
    }

    // 13장 셀프체크 2. update()를 성공한 경우 2
    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        // 1. 테스트 데이터를 먼저 삽입
        ArticleForm createDto = new ArticleForm(null, "가가가가", "1111");
        Article created = articleService.create(createDto);

        // 2. 예상 데이터 (content는 null로 입력됨)
        Long id = created.getId();
        String title = "AAAA";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);

        // 3. 예상 결과는 title만 업데이트되고 content는 기존 값 유지
        Article expected = new Article(id, "AAAA", "1111");

        // 4. 실제 데이터
        Article article = articleService.update(id, dto);

        // 5. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
//        // 예상 데이터
//        Long id = 1L;
//        String title = "AAAA";
//        String content = null;
//        ArticleForm dto = new ArticleForm(id, title, content);
//        Article expected = new Article(1L, "AAAA" , "1111");
//        // 실제 데이터
//        Article article = articleService.update(id, dto);
//        // 비교 및 검증
//        assertEquals(expected.toString(), article.toString());
    }

    // 13장 셀프체크 3. update()를 실패한 경우 1
    @Test
    @Transactional
    void update_실패_존재하지_않는_id의_dto_입력() {
        // 1. 예상 데이터
        Long id = -1L; // 존재하지 않는 id
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        // 2. 실제 데이터
        Article article = articleService.update(id, dto);

        // 3. 비교 및 검증
        assertEquals(expected, article);
//        // 예상 데이터
//        Long id = -1L;
//        String title = "가나다라";
//        String content = "1234";
//        ArticleForm dto = new ArticleForm(id, title, content);
//        Article expected = null;
//        // 실제 데이터
//        Article article = articleService.update(id, dto);
//        // 비교 및 검증
//        assertEquals(expected, article);
    }

    // 13장 셀프체크 4. delete()를 성공한 경우
    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        // 1. 테스트 데이터를 먼저 삽입
        ArticleForm createDto = new ArticleForm(null, "가가가가", "1111");
        Article created = articleService.create(createDto);

        // 2. 예상 데이터
        Long id = created.getId();
        Article expected = new Article(id, "가가가가", "1111");

        // 3. 실제 데이터
        Article article = articleService.delete(id);

        // 4. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
//        // 예상 데이터
//        Long id = 1L;
//        Article expected = new Article(id, "가가가가", "1111");
//        // 실제 데이터
//        Article article = articleService.delete(id);
//        // 비교 및 검증
//        assertEquals(expected.toString(), article.toString());
    }

    // 13장 셀프체크 5. delete()를 실패한 경우
    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력() {
        // 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 실제 데이터
        Article article = articleService.delete(id);
        // 비교 및 검증
        assertEquals(expected, article);
    }
}