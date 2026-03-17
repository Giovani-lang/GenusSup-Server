package com.genus.GENUS_SUP.Traces.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with")
@Entity
@Table(name = "Genus_traces")
public class GenusHttpTrace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @Column(name = "timestamp")
    private Date timestamp;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @Column(name = "principal")
    private String principal;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @Column(name = "apiPath")
    private String apiPath;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @Column(name = "session")
    private String session;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @Column(name = "request")
    private String request;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @Column(name = "remote-address")
    private String remoteAddress;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @Column(name = "response")
    private String response;
}
