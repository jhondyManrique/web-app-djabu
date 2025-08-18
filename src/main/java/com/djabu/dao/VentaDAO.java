package com.djabu.dao;

import com.djabu.model.VentaItemModel;
import com.djabu.model.VentaModel;

import java.sql.*;
import java.util.List;

import static com.djabu.dao.Conexion.getConexion;

public class VentaDAO {

    public boolean guardarVenta(VentaModel venta) throws SQLException {
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
            }
        }
        return true;
    }
}
