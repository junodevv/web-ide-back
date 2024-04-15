package goorm.webide.chat.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * packageName    : goorm.webide.chat.entity
 * fileName       : ChatApiResponse
 * author         : won
 * date           : 2024/04/16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/16        won       최초 생성
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"success", "message","data"})
public class ChatApiResponse<T> implements Serializable{

    private boolean success;
    private String message;
    private T data;

    // 성공 응답 생성
    public static <T> ChatApiResponse<T> success(T data, String message) {
        return new ChatApiResponse<>(true, message, data);
    }

    // 실패 응답 생성
    public static <T> ChatApiResponse<T> fail(String message) {
        return new ChatApiResponse<>(false, message, null);
    }
}
