package ar.com.semillero.semillatronalfa.models.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    private String name;
    private String type;
    private Long size;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] bytes;

    @OneToOne
    @JoinColumn(name = "event_id")
    @Getter(value= AccessLevel.NONE)
    @JsonIgnore
    private Event event;

    public Image() {
    }

    public Image(String name,String type, Long size, byte[] bytes, Event event) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.bytes = bytes;
        this.event = event;
    }

    public Image(String id, String name,String type, Long size, byte[] bytes, Event event) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.bytes = bytes;
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
