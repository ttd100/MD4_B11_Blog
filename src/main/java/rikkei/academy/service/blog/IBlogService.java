package rikkei.academy.service.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Category;
import rikkei.academy.service.IGenericService;

public interface IBlogService extends IGenericService<Blog> {
    Page<Blog> findAllByCategory(Category category, Pageable pageable);

    Iterable<Blog> findByCategory(Category category);
}
