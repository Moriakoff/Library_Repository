package moriakoff.book.controller;

import moriakoff.book.entity.Publisher;
import moriakoff.book.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public Publisher add(@RequestBody Publisher publisher) {
        return publisherService.add(publisher);
    }

    @PutMapping
    public Publisher update(@RequestBody Publisher publisher) {
        return publisherService.update(publisher);
    }

    @DeleteMapping
    public Publisher delete(@RequestBody Publisher publisher) {
        return publisherService.delete(publisher);
    }

    @GetMapping
    public Publisher get(@RequestParam("name") String name) {
        return publisherService.getPublisher(name);
    }


}
