package ua.com.crud;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.com.entity.Word;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "content", path = "words")

public interface WordsRepository extends CrudRepository<Word, Integer> {
    public List<Word>  findByTitleStartingWith (@Param("word") String title, Pageable pageable);
}