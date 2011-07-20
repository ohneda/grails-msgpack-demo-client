package org.grails.msgpack.demo;

public interface MessageService {
    Message get(Long id);
    Message save(Message message);
    Message update(Message message);
    Long delete(Long id);
}
