package pl.miku.personallibrary.core.category.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.miku.personallibrary.core.category.Category;
import pl.miku.personallibrary.core.category.web.CategoryView;

@Component
public class CategoryToCategoryViewConverter implements Converter<Category, CategoryView> {

    @Override
    public CategoryView convert(Category category) {
        return new CategoryView()
                .setId(category.getId())
                .setName(category.getName());
    }
}
