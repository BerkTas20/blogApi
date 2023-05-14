package com.berktas.blogApi;

import com.berktas.blogApi.model.Category;
import com.berktas.blogApi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            initCategories();
        }
    }

    private void initCategories(){
        categoryRepository.save(Category.create("Bilim"));
        categoryRepository.save(Category.create("Seyahat"));
        categoryRepository.save(Category.create("Yiyecek"));
        categoryRepository.save(Category.create("Siyaset"));
        categoryRepository.save(Category.create("Tarih"));
        categoryRepository.save(Category.create("Sanat"));
        categoryRepository.save(Category.create("Film ve Diziler"));
    }
}
