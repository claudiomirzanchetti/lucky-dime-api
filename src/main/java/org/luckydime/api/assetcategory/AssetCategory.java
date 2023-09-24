package org.luckydime.api.assetcategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asset_category")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_category_seq")
    @SequenceGenerator(name="asset_category_seq", sequenceName = "asset_category_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "description")
    private String description;
}
