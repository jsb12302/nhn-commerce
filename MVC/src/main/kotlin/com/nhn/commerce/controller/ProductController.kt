package com.nhn.commerce.controller


import com.nhn.commerce.dto.ProductDTO
import com.nhn.commerce.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping("/")
    fun getProductList(model: Model): String {
        model.addAttribute("productList", productService.findProductList())
        return "product"
    }

    @GetMapping("/addProductPage")
    fun addProductPage(model: Model):String{
        model.addAttribute("productDTO",ProductDTO("",0))
        return "addProduct"
    }

    @GetMapping("/modifyProductPage")
    fun modifyProductPage(@RequestParam("productNo")productNo:Int,model: Model):String{
        model.addAttribute("productOne",productService.findProduct(productNo))
        return "modifyProduct"
    }

    // TODO (상품 상세 조회 기능 + Exception 처리)
    @GetMapping("/product/{productNo}")
    fun detailProduct(@PathVariable("productNo")productNo:Int, model: Model):String{
        model.addAttribute("detail",productService.detailProduct(productNo))
        return "detailProduct"
    }

    // TODO (상품 추가 기능)
    @PostMapping("/product")
    fun addProduct(productDTO: ProductDTO):String{
        productService.insertProduct(productDTO)
        return "redirect:/"
    }


    // TODO (상품 수정 기능 + Exception 처리)
    @PostMapping("/product/modify")
    fun modifyProduct(@RequestParam("productNo")productNo:Int,
                      @RequestParam("productName")productName:String,
                      @RequestParam("salePrice")salePrice:Int):String{
        productService.modifyProduct(productNo,productName,salePrice);
        return "redirect:/"
    }


    // TODO (상품 삭제 기능 + Exception 처리)
    @GetMapping("/product/delete")
    fun deleteProduct(@RequestParam("productNo")productNo:Int):String{
        productService.deleteProduct(productNo);
        return "redirect:/"
    }
}
