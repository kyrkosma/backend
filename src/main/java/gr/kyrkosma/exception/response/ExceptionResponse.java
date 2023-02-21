package gr.kyrkosma.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    private Integer status;
    private String message;
    private Long timeStamp;

}
