package com.example.newproject.Controllers;

import com.example.newproject.data.Category;
import com.example.newproject.data.Post;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/category", produces = "application/json")

public class CategoriesController {
//    @GetMapping("")
//    private List<Category> getPostByCategory() {
//        List m = new ArrayList<>();
//        Category category = new Category(1L,"bunnies");
//        Category newOne = new Category(2L, "gym");
//        m.add(category);
//        m.add(newOne);
//        m.add(new Category(3L,"clothes"));
//        return m;
//    }
}
