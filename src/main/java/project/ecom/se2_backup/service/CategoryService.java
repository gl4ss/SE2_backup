package project.ecom.se2_backup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ecom.se2_backup.model.Category;
import project.ecom.se2_backup.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategory(){
        return categoryRepository.findAll();
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    public Category getCategoryByID(Long id) throws Exception {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found"));
    }

    public List<Category> getAllCategoriesByName(String name) {
        return categoryRepository.findAllByNameContaining(name);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }



    public Category updateCategory(Category category, Long id) throws Exception {
        Category preCategory = getCategoryByID(id);
        if (preCategory != null) {
            preCategory.setId(category.getId());
            preCategory.setName(category.getName());
        }
        return saveCategory(preCategory);
    }

    public String deleteCategory(Long id) throws Exception {
        Category category = getCategoryByID(id);
        if (category != null) {
            categoryRepository.delete(category);
            return id + " deleted successfully.";
        } else {
            return id + " not found. Deleted failed";
        }
    }




}
