package moriakoff.book.service;

import moriakoff.book.entity.Publisher;


public interface PublisherService {

    Publisher add(Publisher publisher);

    Publisher update(Publisher publisher);

    Publisher delete(Publisher publisher);

    Publisher getPublisher(String name);


}
