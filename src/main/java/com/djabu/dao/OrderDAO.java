package com.djabu.dao;

import com.djabu.model.OrderItemModel;
import com.djabu.model.OrderModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.djabu.dao.Conexion.getConexion;

public class OrderDAO {

    public static int saveNewOrder(OrderModel order) throws SQLException {
        String sqlOrder = "INSERT INTO orders (total_price_order) VALUES (?)";
        String sqlDetail = "INSERT INTO order_details (id_order, name, quantity, price) VALUES (?, ?, ?, ?)";
        List<OrderItemModel> details = order.getDetails();
        int generatedId = -1;
        try (Connection conn = getConexion();
             PreparedStatement psVenta = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);) {
            psVenta.setDouble(1, order.getTotal());
            psVenta.executeUpdate();
            ResultSet generatedKeys = psVenta.getGeneratedKeys();

            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("could not obtain the order id");
            }
            ;
            for (OrderItemModel item:
                 details) {
                PreparedStatement psDetalle = conn.prepareStatement(sqlDetail);
                psDetalle.setInt(1, generatedId);
                psDetalle.setString(2, item.getName());
                psDetalle.setInt(3, item.getQuantity());
                psDetalle.setDouble(4, item.getPrice());
                psDetalle.executeUpdate();
            }
        }
        return generatedId;
    }

    public List<OrderModel> getAllOrders(){
        List<OrderModel> orders = new ArrayList<>();
        String sql = "SELECT * from orders";
        try(Connection conn = getConexion();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();){
            while (rs.next()){
                int id = rs.getInt("id_order");
                Timestamp date  = rs.getTimestamp("order_date");
                double total = rs.getDouble("total_price_order");
                OrderModel order = new OrderModel();
                order.setId(id);
                order.setDate(date);
                order.setTotal(total);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderItemModel> getDetailsById(int id){
        String sql = "SELECT * from order_details WHERE id_order = ?";
        List<OrderItemModel> details = new ArrayList<>();
        try(Connection conn = getConexion();
            PreparedStatement pstm = conn.prepareStatement(sql);){
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                Double price =rs.getDouble("price");
                details.add(new OrderItemModel(name,quantity,price));
            }
            return details;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderModel> getOrdersByDate(LocalDate startDate, LocalDate endDate) {
        List<OrderModel> orders = new ArrayList<>();
        // Usamos CAST para comparar solo la parte de la fecha de nuestro TIMESTAMP
        String sql = "SELECT * FROM orders WHERE CAST(order_date AS DATE) BETWEEN ? AND ?";

        try (Connection conn = getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Asignamos los parámetros de fecha a la consulta y transformamos el tipo localdate
            // a tipo sql.date que el jdbc si puede entender
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_order");
                Timestamp date  = rs.getTimestamp("order_date");
                double total = rs.getDouble("total_price_order");
                OrderModel order = new OrderModel();
                order.setId(id);
                order.setDate(date);
                order.setTotal(total);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;

}

}
