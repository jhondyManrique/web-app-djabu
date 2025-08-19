package com.djabu.dao;

import com.djabu.model.VentaItemModel;
import com.djabu.model.VentaModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.djabu.dao.Conexion;

import static com.djabu.dao.Conexion.getConexion;

public class VentaDAO {

    public static boolean guardarVenta(VentaModel venta) throws SQLException {
        String sqlVenta = "INSERT INTO ventas (total_venta) VALUES (?)";
        String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, nombre, cantidad, precio) VALUES (?, ?, ?, ?)";
        List<VentaItemModel> detalles = venta.getDetalles();
        try (Connection conn = getConexion();
             PreparedStatement psVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);) {
            psVenta.setDouble(1, venta.getTotal());
            psVenta.executeUpdate();
            ResultSet generatedKeys = psVenta.getGeneratedKeys();
            int idVentaGenerado;
            if (generatedKeys.next()) {
                idVentaGenerado = generatedKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la venta.");
            }
            ;
            for (VentaItemModel item:
                 detalles) {
                PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
                psDetalle.setInt(1, idVentaGenerado);
                psDetalle.setString(2, item.getNombre());
                psDetalle.setInt(3, item.getCantidad());
                psDetalle.setDouble(4, item.getPrecio());
                psDetalle.executeUpdate();
            }
        }
        return true;
    }

    public List<VentaModel> obtenerVentas(){
        List<VentaModel> ventas = new ArrayList<>();
        String sql = "SELECT * from ventas";
        try(Connection conn = getConexion();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();){
            while (rs.next()){
                int id = rs.getInt("id_venta");
                Timestamp fecha = rs.getTimestamp("fecha_venta");
                Double total = rs.getDouble("total_venta");
                VentaModel venta = new VentaModel();
                venta.setId(id);
                venta.setFecha(fecha);
                venta.setTotal(total);
                ventas.add(venta);
            }
            return ventas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<VentaItemModel> obtenerDetallesPorId(int id){
        String sql = "SELECT * from detalle_ventas WHERE id_venta = ?";
        List<VentaItemModel> detalles = new ArrayList<>();
        try(Connection conn = getConexion();
            PreparedStatement pstm = conn.prepareStatement(sql);){
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                Double precio =rs.getDouble("precio");
                detalles.add(new VentaItemModel(nombre,cantidad,precio));
            }
            return detalles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
