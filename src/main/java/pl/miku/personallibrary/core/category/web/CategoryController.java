package pl.miku.personallibrary.core.category.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.miku.personallibrary.core.category.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CategoryView getCategory(@PathVariable Long id) {
        return service.getCategory(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CategoryView> getAllCategories(@PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        return service.findAllCategories(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CategoryView create(@RequestBody @Valid CategoryRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryView updateCategory(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid CategoryRequest request
    ) {
        var category = service.findOrThrow(id);
        return service.update(category, request);
    }
}
