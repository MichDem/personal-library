package pl.miku.personallibrary.core.category;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.miku.personallibrary.core.category.converter.CategoryToCategoryViewConverter;
import pl.miku.personallibrary.core.category.web.CategoryRequest;
import pl.miku.personallibrary.core.category.web.CategoryView;
import pl.miku.personallibrary.error.EntityNotFoundException;
import pl.miku.personallibrary.util.MessageUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MessageUtil messageUtil;
    private final CategoryToCategoryViewConverter categoryToCategoryViewConverter;

    public CategoryService(CategoryRepository categoryRepository, MessageUtil messageUtil, CategoryToCategoryViewConverter categoryToCategoryViewConverter) {
        this.categoryRepository = categoryRepository;
        this.messageUtil = messageUtil;
        this.categoryToCategoryViewConverter = categoryToCategoryViewConverter;
    }

    public Category findOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("category.NotFound", id)));
    }

    public CategoryView getCategory(Long id) {
        var category = findOrThrow(id);
        return categoryToCategoryViewConverter.convert(category);
    }

    public CategoryView create(CategoryRequest request) {
        var category = new Category();
        prepare(category, request);
        var save = categoryRepository.save(category);
        return categoryToCategoryViewConverter.convert(save);
    }

    public Page<CategoryView> findAllCategories(Pageable pageable) {
        var categories = categoryRepository.findAll(pageable);
        var views = new ArrayList<CategoryView>();
        categories.forEach(category -> {
            var view = categoryToCategoryViewConverter.convert(category);
            views.add(view);
        });
        return new PageImpl<>(views, pageable, categories.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(messageUtil.getMessage("category.NotFound", id));
        }
    }

    public CategoryView update(Category category, CategoryRequest request) {
        var newCategory = prepare(category, request);
        var saved = categoryRepository.save(newCategory);
        return categoryToCategoryViewConverter.convert(saved);
    }

    private Category prepare(Category category, CategoryRequest request) {
        //TODO
        return category;
    }
}
