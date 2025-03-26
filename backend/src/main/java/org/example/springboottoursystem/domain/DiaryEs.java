package org.example.springboottoursystem.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "diary")
@Getter
@Setter
public class DiaryEs {
    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_analyzer")
    private String title;
    @Field(type = FieldType.Text, analyzer = "ik_analyzer")
    private String spot;
    @Field(type = FieldType.Text, analyzer = "ik_analyzer")
    private String author;
    @Field(type = FieldType.Date)
    private String date;
    @Field(type = FieldType.Integer)
    private int heat;
    @Field(type = FieldType.Double)
    private double grade;
    @Field(type = FieldType.Text, analyzer = "ik_analyzer")
    private String text;

    public DiaryEs() {
    }

    public DiaryEs(Long id, String title, String spot, String author, String date, int heat, double grade, String text) {
        this.id = id;
        this.title = title;
        this.spot = spot;
        this.author = author;
        this.date = date;
        this.heat = heat;
        this.grade = grade;
        this.text = text;
    }
}