    package com.sachin.ecom_proj.model;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.math.BigDecimal;
    import java.util.Date;

    //to map to database
    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;//int to Integer
        private Integer stockQuantity; //Cannot map null into type int
        private String name;
        private String description;
        private String brand;
        private BigDecimal price;
        private String category;
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-dd-MM")
        private Date releaseDate;
        private boolean productAvailable;

        //image

        private String imageName;
        private String imageType;
        @Lob // we can store the image on aws but rn we are storing it in local db
        private byte[] imageDate;
    }
