package no.oslomet.springbootdemo.repository;

import no.oslomet.springbootdemo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
