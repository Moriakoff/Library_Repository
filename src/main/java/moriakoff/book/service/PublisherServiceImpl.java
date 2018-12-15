package moriakoff.book.service;

import moriakoff.book.entity.Publisher;
import moriakoff.book.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository repository;

    @Override
    public boolean add(Publisher publisher) {
        if(repository.existsById(publisher.getName())) return false;
        repository.save(publisher);
        return true;
    }

    @Override
    public boolean update(Publisher publisher) {
        if (repository.existsById(publisher.getName())) {
            repository.save(publisher);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Publisher publisher) {
        if(repository.existsById(publisher.getName())){
            repository.delete(publisher);
            return true;
        }
        return false;
    }

    @Override
    public Publisher getPublisher(String name) {
        return repository.findById(name).orElse(null);
    }
}
