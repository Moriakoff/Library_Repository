package moriakoff.book.service;

import moriakoff.book.dto.PublisherDto;


public interface PublisherService {

    boolean add(PublisherDto publisher);

    boolean update(PublisherDto publisher);

    boolean delete(PublisherDto publisher);

    PublisherDto getPublisher(String name);


}
