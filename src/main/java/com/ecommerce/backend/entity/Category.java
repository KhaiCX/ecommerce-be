package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.UUID;

@Entity
@Table(name = "categories")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
//@SQLDelete(sql = "update categories set deleted = true where category_id = ?")
//@FilterDef(
//        name = "deletedCategory",
//        parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
//@Filter(
//        name = "deletedCategory",
//        condition = "deleted = :isDeleted")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID categoryId;
    private String name;
    private Boolean deleted = false;
}
