package org.grails.msgpack.demo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.msgpack.annotation.MessagePackMessage;
import org.msgpack.annotation.Optional;

@MessagePackMessage
public class Message {

    public List<Comment> comments;
    public Date dateCreated;
    public Date dateUpdated;
    public Double doubleval;
    public Float floatval;
    @Optional
    public Long id;
    public Integer intval;
    public Boolean isActive;
    public Long longval;
    @Optional
    public String optional;
    
    public Map<String, String> props;
    public String string;

    
    @Optional
    public Long version;
}
