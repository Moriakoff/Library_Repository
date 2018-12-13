package moriakoff.book.controller;

import moriakoff.book.dto.AuthorDto;
import moriakoff.book.entity.Author;
import moriakoff.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public AuthorDto getAuthor(@RequestParam("fName") String fName,
                               @RequestParam("lName") String lName){
        return authorService.getAuthor(fName, lName);
    }

    @PostMapping
    public boolean add(@RequestBody AuthorDto author){
        return authorService.add(author);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("fName") String fName, @RequestParam String lName){
        return authorService.delete(fName,lName);
    }

    @DeleteMapping
    public boolean delete(@RequestBody AuthorDto author){
        return authorService.delete(author);
    }

    @PutMapping
    public boolean update(@RequestBody AuthorDto author){
        return authorService.update(author);
    }

    @GetMapping("/all")
    public List<Author> getAll(){
        return authorService.getAuthors();
    }

}
