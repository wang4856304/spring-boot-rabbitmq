package com.wj.queue;

import org.springframework.amqp.core.AbstractDeclarable;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author jun.wang
 * @title: RabbitMQQueue
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/30 15:19
 */
public class RabbitMQQueue extends AbstractDeclarable {

    private String name;
    private boolean durable;
    private boolean exclusive;
    private boolean autoDelete;
    private Map<String, Object> arguments;

    public RabbitMQQueue() {

    }

    public void init() {
        Assert.notNull(name, "'name' cannot be null");
        this.durable = true;
        this.exclusive = false;
        this.autoDelete = false;
        this.arguments = null;
    }

    public RabbitMQQueue(String name) {
        this(name, true, false, false);
    }

    public RabbitMQQueue(String name, boolean durable) {
        this(name, durable, false, false, (Map)null);
    }

    public RabbitMQQueue(String name, boolean durable, boolean exclusive, boolean autoDelete) {
        this(name, durable, exclusive, autoDelete, (Map)null);
    }

    public RabbitMQQueue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) {
        Assert.notNull(name, "'name' cannot be null");
        this.name = name;
        this.durable = durable;
        this.exclusive = exclusive;
        this.autoDelete = autoDelete;
        this.arguments = arguments;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public boolean isDurable() {
        return this.durable;
    }

    public boolean isExclusive() {
        return this.exclusive;
    }

    public boolean isAutoDelete() {
        return this.autoDelete;
    }

    public Map<String, Object> getArguments() {
        return this.arguments;
    }

    public String toString() {
        return "Queue [name=" + this.name + ", durable=" + this.durable + ", autoDelete=" + this.autoDelete + ", exclusive=" + this.exclusive + ", arguments=" + this.arguments + "]";
    }
}
