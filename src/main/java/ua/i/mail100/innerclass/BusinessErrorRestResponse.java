package ua.i.mail100.innerclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessErrorRestResponse {

    private String title;
    private String status;
    private String detail;
    private String path;
    private Error error;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Error {
        private String errorUserCode;
        private String errorUserMessage;
    }

    public void setError(String errorUserCode, String errorUserMessage) {
        this.error = new Error(errorUserCode, errorUserMessage);
    }

    public String getErrorUserCode() {
        return error.getErrorUserCode();
    }

    public String getErrorUserMessage() {
        return error.getErrorUserMessage();
    }
}
