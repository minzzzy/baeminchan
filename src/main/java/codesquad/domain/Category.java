package codesquad.domain;

import codesquad.support.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Entity
public class Category extends AbstractEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;

    @NotBlank
    private String name;

    public Category() {

    }

    public Category(String name, Category parent) {
        this();
        this.name = name;
        this.parent = parent;
    }

    public boolean isNotSameId(Long rootId) {
        return this.getId() != rootId;
    }


    public void removeChildren() {
        this.children = null;
    }
}
