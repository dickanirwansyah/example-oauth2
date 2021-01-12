package id.dicka.oauth2.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROLE")
@Entity
public class Role extends BaseEntityID {

    @Column(name = "NAME")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PERMISSION_ROLE", joinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID")
    })
    private List<Permission> permissions;
}
