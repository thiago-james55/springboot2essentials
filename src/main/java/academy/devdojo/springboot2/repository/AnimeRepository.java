package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AnimeRepository extends JpaRepository<Anime, Long> {

    @Query(nativeQuery = true , value = "select * from anime.anime where name like %?% ")
    List<Anime> findByName(String name);

}
