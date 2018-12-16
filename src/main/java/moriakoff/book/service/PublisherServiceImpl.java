package moriakoff.book.service;

import moriakoff.book.entity.Publisher;
import moriakoff.book.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository repository;

    @Override
    @Transactional
    public Publisher add(Publisher publisher) {
        if (repository.existsById(publisher.getName())) return null;
        return repository.save(publisher);
    }

    @Override
    @Transactional
    public Publisher update(Publisher publisher) {
        if (repository.existsById(publisher.getName())) {
            return repository.save(publisher);
        }
        return null;
    }

    @Override
    @Transactional
    public Publisher delete(Publisher publisher) {
        Publisher victim = repository.findById(publisher.getName()).orElse(null);
        if (victim == null) return null;
        repository.delete(publisher);
        return victim;
    }

    @Override
    @Transactional(readOnly = true)
    public Publisher getPublisher(String name) {
        return repository.findById(name).orElse(null);
    }
}
