package com.example.quizzApp.utility;

import com.example.quizzApp.responses.APISuccess;
import org.springframework.http.ResponseEntity;

public class CommonUtil {
    public static ResponseEntity<Object> buildResponse(APISuccess apiSuccess) {
        return new ResponseEntity<>(apiSuccess, apiSuccess.getStatus());
    }
}
