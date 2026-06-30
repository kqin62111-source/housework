package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObjet {
    private Integer currentPage=1;
    private Integer pageSize=5;

    public Integer getStartPage() {
        return (this.currentPage-1)*this.pageSize;
    }
}
