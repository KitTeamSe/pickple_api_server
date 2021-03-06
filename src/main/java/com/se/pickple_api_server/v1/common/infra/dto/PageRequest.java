package com.se.pickple_api_server.v1.common.infra.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Min;

import lombok.*;
import org.springframework.data.domain.Sort;

@ApiModel("페이지 요청")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {

  @ApiModelProperty(example = "1", notes = "페이지, 1 이상이야 함")
  @Min(value = 1)
  private int page;
  @ApiModelProperty(example = "8", notes = "페이지의 사이즈, 8 이상이어야 함")
  @Min(value = 8)
  private int size;

  @ApiModelProperty(example = "ASC", notes = "정렬 방향, 기준(생성일)")
  private Sort.Direction direction;

  public void setPage(int page) {
    this.page = page <= 0 ? 1 : page;
  }

  public void setSize(int size) {
    int DEFAULT_SIZE = 8;
    int MAX_SIZE = 20;
    this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
  }

  public void setDirection(Sort.Direction direction) {
    this.direction = direction;
  }

  public org.springframework.data.domain.PageRequest of() {
    return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, "createdDate");
  }
}
