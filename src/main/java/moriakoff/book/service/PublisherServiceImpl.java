package moriakoff.book.service;

import moriakoff.book.dto.PublisherDto;
import moriakoff.book.entity.Country;
import moriakoff.book.entity.Publisher;
import moriakoff.book.repository.PublisherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository repository;

    @Override
    public boolean add(PublisherDto publisher) {
        if(repository.existsById(publisher.getName())) return false;
        repository.save(publisherDtoTopublisher(publisher));
        return true;
    }

    private Publisher publisherDtoTopublisher(PublisherDto publisher) {
        Publisher publisherEntity = new Publisher();
        //BeanUtils.copyProperties(publisher, publisher);
        publisherEntity.setName(publisher.getName());
        publisherEntity.setCountryName(new Country(publisher.getCountry()));
        return publisherEntity;
    }

    @Override
    public boolean update(PublisherDto publisher) {
        if (repository.existsById(publisher.getName())) {
            repository.save(publisherDtoTopublisher(publisher));
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(PublisherDto publisher) {
        if(repository.existsById(publisher.getName())){
            repository.delete(publisherDtoTopublisher(publisher));
            return true;
        }
        return false;
    }

    @Override
    public PublisherDto getPublisher(String name) {
        PublisherDto publisherDto = new PublisherDto();
        Publisher publisher = repository.findById(name).orElse(new Publisher());
        BeanUtils.copyProperties(publisher,publisherDto);
        return publisherDto;
    }
}
