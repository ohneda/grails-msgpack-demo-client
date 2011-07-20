package org.grails.msgpack.demo;

import org.msgpack.annotation.MessagePackMessage;
import org.msgpack.annotation.Optional;

@MessagePackMessage
public class Comment {
    @Optional
    public Long id;

    public String name;
    @Optional
    public String title; 
    @Optional
    public Long version;

}
