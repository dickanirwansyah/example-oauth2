package id.dicka.oauth2.commonservice.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi {

    private int code;
    private Date dateTime;
    private String message;
    private Object data;

    public static ResponseApi responseOk(Object data){
        ResponseApi responseApi = new ResponseApi();
        responseApi.setCode(200);
        responseApi.setData(data);
        responseApi.setMessage("success");
        responseApi.setDateTime(new Date());
        return responseApi;
    }

    public static ResponseApi responseNotOk(){
        ResponseApi responseApi = new ResponseApi();
        responseApi.setCode(400);
        responseApi.setData(null);
        responseApi.setMessage("success");
        responseApi.setDateTime(new Date());
        return responseApi;
    }

    public static ResponseApi responseConflict(){
        ResponseApi responseApi = new ResponseApi();
        responseApi.setCode(409);
        responseApi.setData(null);
        responseApi.setMessage("conflict");
        responseApi.setDateTime(new Date());
        return responseApi;
    }
}
