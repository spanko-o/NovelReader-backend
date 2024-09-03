package org.bupt.minisemester.dao.DTO;

import lombok.Getter;
import lombok.Setter;

public class NovelDTO {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String picture;
    @Getter
    @Setter
    private boolean status;
}
