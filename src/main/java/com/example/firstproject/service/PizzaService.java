package com.example.firstproject.service;

import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    // 피자 목록 조회
    public List<Pizza> getPizzas() {
        return pizzaRepository.findAll();
    }

    // 피자 단건 조회
    public Pizza getPizza(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    // 피자 생성
    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    // 피자 수정
    public Pizza updatePizza(Long id, Pizza newPizza) {
        return pizzaRepository.findById(id).map(pizza -> {
            pizza.setName(newPizza.getName());
            pizza.setPrice(newPizza.getPrice());
            return pizzaRepository.save(pizza);
        }).orElse(null);
    }

    // 피자 삭제
    public boolean deletePizza(Long id) {
        return pizzaRepository.findById(id).map(pizza -> {
            pizzaRepository.delete(pizza);
            return true;
        }).orElse(false);
    }
}