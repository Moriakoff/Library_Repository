package moriakoff.book.service;

import moriakoff.book.entity.Publisher;


public interface PublisherService {

    boolean add(Publisher publisher);

    boolean update(Publisher publisher);

    boolean delete(Publisher publisher);

    Publisher getPublisher(String name);


}
