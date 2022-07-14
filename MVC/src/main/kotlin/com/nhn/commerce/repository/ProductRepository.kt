package com.nhn.commerce.repository

import com.nhn.commerce.model.Product
import org.apache.ibatis.annotations.*
import java.time.LocalDateTime

@Mapper
interface ProductRepository {
    @Select("SELECT * FROM product")
    fun findProductList(): List<Product>


    @Insert("INSERT INTO product(productName,registerYmdt,updateYmdt,salePrice) Values(#{productName},#{registerYmdt},#{updateYmdt},#{salePrice})")
    fun insertProduct(@Param("productName")productName:String,@Param("registerYmdt")registerYmdt:LocalDateTime,
                      @Param("updateYmdt")updateYmdt:LocalDateTime,@Param("salePrice")salePrice:Int)

    @Delete("DELETE FROM product WHERE productNo = #{productNo}")
    fun deleteProduct(@Param("productNo")productNo:Int)

    @Update("UPDATE product SET productName=#{productName}, salePrice=#{salePrice}" +
            ", updateYmdt=#{updateYmdt} WHERE productNo = #{productNo}")
    fun modifyProduct(@Param("productNo")productNo:Int,@Param("productName")productName:String,
                      @Param("updateYmdt")updateYmdt:LocalDateTime,@Param("salePrice")salePrice:Int)

    @Select("SELECT * FROM product WHERE productNo = #{productNo}")
    fun findProduct(@Param("productNo")productNo:Int):Product

}
