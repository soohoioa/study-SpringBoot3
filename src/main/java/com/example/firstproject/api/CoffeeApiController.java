package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    private CoffeeService coffeeService;

    // GET
    @GetMapping("/api/coffees")
    public Iterable<Coffee> index() {
        return coffeeService.index();
    }

    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee coffee = coffeeService.show(id);
        return (coffee != null) ?
                ResponseEntity.status(HttpStatus.OK).body(coffee) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto coffeeDto) {
        Coffee created = coffeeService.create(coffeeDto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id,
                                         @RequestBody CoffeeDto coffeeDto) {
        Coffee updated = coffeeService.update(id, coffeeDto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     @Autowired
     private CoffeeRepository coffeeRepository;

     // GET
     @GetMapping("/api/coffees")
     public Iterable<Coffee> index() {
     return coffeeRepository.findAll();
     }

     @GetMapping("/api/coffees/{id}")
     public Coffee show(@PathVariable Long id) {
     return coffeeRepository.findById(id).orElse(null);
     }

     // POST
     @PostMapping("/api/coffees")
     public Coffee create(@RequestBody CoffeeDto coffeeDto) {
     Coffee created = coffeeDto.toEntity();
     return coffeeRepository.save(created);
     }

     // PATCH
     @PatchMapping("/api/coffees/{id}")
     public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto coffeeDto) {
     // 1. DTO -> 엔티티 변환하기
     Coffee coffee = coffeeDto.toEntity();
     log.info("id: {}, coffee: {}", id, coffee.toString());

     // 2. 타깃 조회하기
     Coffee target = coffeeRepository.findById(id).orElse(null);

     // 3. 잘못된 요청 처리하기
     if (target == null || id != coffee.getId()) {
     // 400, 잘못된 요청 처리
     log.info("잘못된 요청! id: {}, coffee: {}", id, coffee.toString());
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
     }
     // 4. 업데이트 및 정상 응답(200)하기
     target.patch(coffee);
     Coffee updated = coffeeRepository.save(target);
     return ResponseEntity.ok(updated);
     }

     // DELETE
     @DeleteMapping("/api/coffees/{id}")
     public ResponseEntity<Coffee> delete(@PathVariable Long id) {
     // 1. 대상 찾기
     Coffee target = coffeeRepository.findById(id).orElse(null);

     // 2. 잘못된 요청 처리하기
     if (target == null) {
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
     }

     // 3. 대상 삭제하기
     coffeeRepository.delete(target);
     return ResponseEntity.status(HttpStatus.OK).build();
     }
     */
}