package project.ecom.se2_backup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ecom.se2_backup.common.ApiResponse;
import project.ecom.se2_backup.model.Category;
import project.ecom.se2_backup.service.CategoryService;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Create new category"), HttpStatus.CREATED );
    }

    @GetMapping("/list/")
    public ResponseEntity<List<Category>> listCategory(){
        List<Category> body = categoryService.listCategory();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody Category category,
                                   @PathVariable(value = "id") Long id) throws Exception {
        categoryService.updateCategory(category, id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated"), HttpStatus.OK);
    }

    @GetMapping("/list/{name}")
    public ResponseEntity<List<Category>> getAllCategoriesByName(@PathVariable(value = "name") String name) {
        List<Category> body = categoryService.getAllCategoriesByName(name);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(value = "id") Long id) throws Exception {
        categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "deleted"), HttpStatus.OK);
    }

}
