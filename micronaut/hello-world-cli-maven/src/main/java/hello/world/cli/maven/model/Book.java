package hello.world.cli.maven.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "title field must be not null")
    private String title;

    @NotNull(message = "pages field must not be null")
    private int pages;

}
