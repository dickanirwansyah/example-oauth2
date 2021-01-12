package id.dicka.oauth2.productservice.controller;

import id.dicka.oauth2.commonservice.controller.BaseController;
import id.dicka.oauth2.commonservice.controller.ResponseApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController extends BaseController {

    @GetMapping(value = "/test")
    public ResponseApi test(){
        String test = "test data";
        return ResponseApi.responseOk(test);
    }
}
