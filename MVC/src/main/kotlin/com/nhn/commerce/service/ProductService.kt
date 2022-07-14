package com.nhn.commerce.service

import com.nhn.commerce.dto.ProductDTO
import com.nhn.commerce.model.Product
import com.nhn.commerce.repository.ProductRepository
import org.apache.tomcat.jni.Local
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun findProductList(): List<Product> = productRepository.findProductList()

    fun insertProduct(productDTO: ProductDTO){
        val now = LocalDateTime.now()
        val salePrice=productDTO.salePrice
        if(salePrice.isNotZero(salePrice)){
            throw Exception("판매가격은 0보다 작을수 없습니다")
        }
        productRepository.insertProduct(productDTO.productName,now,now,salePrice)
    }

    fun Int.isNotZero(salePrice:Int):Boolean=salePrice<0

    fun deleteProduct(productNo:Int){
        productRepository.deleteProduct(productNo)
    }

    fun findProduct(productNo:Int):Product{
        return productRepository.findProduct(productNo)
    }

    fun modifyProduct(productNo:Int,productName:String,salePrice: Int){
        val now = LocalDateTime.now()
        productRepository.modifyProduct(productNo,productName,now,salePrice)
    }

    fun detailProduct(productNo:Int):Product{
        return productRepository.findProduct(productNo)
    }

}
