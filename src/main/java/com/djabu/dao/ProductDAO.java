package com.djabu.dao;

import com.djabu.model.ProductModel;
import com.djabu.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public ProductDAO() {
    }

    public List<ProductModel> getProducts(){
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT * from products";
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();){

            while (rs.next()){
                String name = rs.getString("product_name");
                Double price = rs.getDouble("unit_price");
                products.add(new ProductModel(name,price));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public ProductModel getProductByName(String name){
        String sql = "SELECT * from products WHERE product_name = ?";
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);){

            pstm.setString(1 , name);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                String productName = rs.getString("product_name");
                Double unitPrice = rs.getDouble("unit_price");
                return new ProductModel(productName,unitPrice);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public int addProduct(ProductModel productModel) throws SQLException {
        int afectedRows = 0;
        String sql = "INSERT INTO products (product_name,unit_price) VALUES (?,?)";
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement  pstm = conn.prepareStatement(sql)){
            pstm.setString(1,productModel.getName());
            pstm.setDouble(2,productModel.getPrice());
            afectedRows = pstm.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return afectedRows;
    }
}
