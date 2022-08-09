package com.mightycoder.spring.data.jpa.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name")
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile")
        )
})
public class Guardian {

//    @Id
//    @SequenceGenerator(
//            name = "guardian_sequence",
//    sequenceName = "guardian_sequence",
//    allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guardian_sequence")
//    private Long guardianId;

    private String name;

    private String email;

    private String mobile;
}
