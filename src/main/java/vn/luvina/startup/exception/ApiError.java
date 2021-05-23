package vn.luvina.startup.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

  private Integer status;

  private List<String> errors;

}
