package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.dtos.queries.ShowContentByDayDTO;
import com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO;
import com.upc.talkia_proyect.dtos.queries.UrlDTO;
import com.upc.talkia_proyect.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO(c.title,c.year)" +
            "from Content c where c.title like %:title%")
    public List<ShowContentByFilterDTO> listContentByTitle(@Param("title")String title);

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO(c.title, c.year) from Content c " +
            "join c.levels l where l.level = :level")
    public List<ShowContentByFilterDTO> listContentByLevels(@Param("level") String level);

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO(c.title, c.year) from Content c " +
            "join c.levels l where l.level = :level and c.type =:type")
    public List<ShowContentByFilterDTO>  listContentByLevelsAndTypes(@Param("level") String level, @Param("type") String type);

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO(c.title, c.year) from Content c " +
            " where c.type =:type")
    public List<ShowContentByFilterDTO> listContentByTypes(@Param("type") String type);


    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO(c.title, c.year)from Content c " +
            " where c.theme =:theme")
    public List<ShowContentByFilterDTO> listContentByTheme(@Param("theme") String theme);

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO(c.title, c.year)from Content c " +
            " where c.theme =:theme and c.type=:type")
    public List<ShowContentByFilterDTO> listContentByThemeAndTypes(@Param("theme") String theme, @Param("type") String type);

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO(c.title, c.year) from Content c " +
            "join c.levels l where l.level = :level and c.theme =:theme and c.type=:type  ")
    public List<ShowContentByFilterDTO> listContentByAllFilters(@Param("theme") String theme, @Param("type") String type, @Param("level") String level);

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO(c.title, c.year) from Content c " +
            "join c.levels l where l.level = :level and c.theme =:theme")
    public List<ShowContentByFilterDTO>  listContentByLevelsAndTheme(@Param("level") String level, @Param("theme") String theme);

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByDayDTO(r.content.title, avg(r.score),r.content.year) " +
            "from Rating r group by r.content.title, r.content.year order by r.content.year asc")
    public List<ShowContentByDayDTO> listContentOrderByDateOfPublicationAsc();

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowContentByDayDTO(r.content.title, avg(r.score),r.content.year) " +
            "from Rating r group by r.content.title, r.content.year order by r.content.year desc")
    public List<ShowContentByDayDTO> listContentOrderByDateOfPublicationDesc();

    @Query("select new com.upc.talkia_proyect.dtos.queries.UrlDTO(c.link) from Content c where c.title=:title")
    public List<UrlDTO> listContentByLink(@Param("title") String title);


}
