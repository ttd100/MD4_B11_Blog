package rikkei.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Category;

public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Page<Blog> findAllByOrderByDateDesc(Pageable pageable);

    Page<Blog> findAllByCategory(Category category, Pageable pageable);

    Iterable<Blog> findAllByCategory(Category category);
}
