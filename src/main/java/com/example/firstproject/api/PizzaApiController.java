package com.example.firstproject.api;

import com.example.firstproject.entity.Pizza;
import com.example.firstproject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaApiController {

    @Autowired
    private PizzaService pizzaService;

    // 피자 목록 조회
    @GetMapping
    public List<Pizza> getPizzas() {
        return pizzaService.getPizzas();
    }

    // 피자 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizza(@PathVariable Long id) {
        Pizza pizza = pizzaService.getPizza(id);
        if (pizza != null) {
            return ResponseEntity.ok(pizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 피자 생성
    @PostMapping
    public Pizza createPizza(@RequestBody Pizza pizza) {
        return pizzaService.createPizza(pizza);
    }

    // 피자 수정
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Long id, @RequestBody Pizza pizza) {
        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        if (updatedPizza != null) {
            return ResponseEntity.ok(updatedPizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 피자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        boolean deleted = pizzaService.deletePizza(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}