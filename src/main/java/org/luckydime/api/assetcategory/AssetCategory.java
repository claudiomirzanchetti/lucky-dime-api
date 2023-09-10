package org.luckydime.api.assetcategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asset_category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetCategory {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;
}
