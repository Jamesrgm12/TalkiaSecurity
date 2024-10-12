package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.ContentDTO;
import com.upc.talkia_proyect.dtos.queries.ShowContentByDayDTO;
import com.upc.talkia_proyect.dtos.queries.ShowContentByFilterDTO;
import com.upc.talkia_proyect.dtos.queries.UrlDTO;
import com.upc.talkia_proyect.entities.Content;
import com.upc.talkia_proyect.services.ContentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContentController {

    @Autowired
    private ContentService contentService;

    private final ModelMapper modelMapper = new ModelMapper();

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/content")
    public ResponseEntity<?> insertContent(@RequestBody ContentDTO contentDTO){
        try {
            Content content = modelMapper.map(contentDTO, Content.class);
            content = contentService.insertContent(content);
            contentDTO = modelMapper.map(content, ContentDTO.class);
            return new ResponseEntity<>(contentDTO, HttpStatus.CREATED);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_level/{level}")
    public ResponseEntity<?> listContentByLevels(@PathVariable String level) {
        try {
            List<ShowContentByFilterDTO> contentDTOs = contentService.listContentByLevels(level);
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_level_type/{level}/{type}")
    public ResponseEntity<?> listContentByLevelsAndTypes(@PathVariable String level, @PathVariable String type) {
        try {
            List<ShowContentByFilterDTO> contentDTOs = contentService.listContentByLevelsAndTypes(level, type);
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_type/{type}")
    public ResponseEntity<?> listContentByTypes(@PathVariable String type) {
        try {
            List<ShowContentByFilterDTO> contentDTOs = contentService.listContentByTypes(type);
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_theme_level/{level}/{theme}")
    public ResponseEntity<?> listContentByLevelsAndTheme(@PathVariable String level, @PathVariable String theme) {
        try {
            List<ShowContentByFilterDTO> contentDTOs = contentService.listContentByLevelsAndTheme(level, theme);
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_theme_level_type/{type}/{theme}/{level}")
    public ResponseEntity<?> listContentByAllFilters(@PathVariable String theme, @PathVariable String type, @PathVariable String level) {
        try {
            List<ShowContentByFilterDTO> contentDTOs = contentService.listContentByAllFilters(theme, type, level);
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_theme_type/{theme}/{type}")
    public ResponseEntity<?> listContentByThemeAndTypes( @PathVariable String theme, @PathVariable String type) {
        try {
            List<ShowContentByFilterDTO> contentDTOs = contentService.listContentByThemeAndTypes(theme, type);
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_theme/{theme}")
    public ResponseEntity<?> listContentByTheme(@PathVariable String theme) {
        try {
            List<ShowContentByFilterDTO> contentDTOs = contentService.listContentByTheme(theme);
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/contents")
    public ResponseEntity<?> listContent() {
        try {
            List<Content> contents = contentService.listAllContent();
            ModelMapper modelMapper = new ModelMapper();
            List<ContentDTO> contentDTOs = modelMapper.map(contents, List.class);
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content/title/{title}")
    public ResponseEntity<?> listContentByTitle(@PathVariable String title) {
        try {
            List<ShowContentByFilterDTO> contentDTOs = contentService.listContentByTitle(title);
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_fechaAsc")
    public ResponseEntity<?> listContentOrderByDateOfPublicationAsc() {
        try {
            List<ShowContentByDayDTO> contentDTOs = contentService.listContentOrderByDateOfPublicationAsc();
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_fechaDesc")
    public ResponseEntity<?> listContentOrderByDateOfPublicationDesc() {
        try {
            List<ShowContentByDayDTO> contentDTOs = contentService.listContentOrderByDateOfPublicationDesc();
            return new ResponseEntity<>(contentDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/content")
    public ResponseEntity<?> updateContent(@RequestBody ContentDTO contentDTO) {
        try {
            Content content = modelMapper.map(contentDTO, Content.class);
            content = contentService.updateContent(content);
            contentDTO = modelMapper.map(content, ContentDTO.class);
            return new ResponseEntity<>(contentDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/content_url/{title}")
    public ResponseEntity<?> listContentByUrl(@PathVariable String title) {
        try {
            List<UrlDTO> urlDTOs = contentService.listContentByLink(title);
            return new ResponseEntity<>(urlDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/content/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable int id) {
        try {
            contentService.deleteContent(id);
            return new ResponseEntity<>("Contenido eliminado correctamente", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}