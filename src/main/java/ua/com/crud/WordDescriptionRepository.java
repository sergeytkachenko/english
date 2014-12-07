package ua.com.crud;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.com.entity.Word;
import ua.com.entity.WordsDescription;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "content", path = "word-description")
public interface WordDescriptionRepository extends CrudRepository<WordsDescription, Integer> {
    public WordsDescription findByTitle (@Param("word") String title);
}