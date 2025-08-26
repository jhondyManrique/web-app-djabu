package com.djabu.service;

import com.djabu.model.OrderItemModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CarritoServiceTest {
    private CartService cartService;
    @BeforeEach
    void setUp() {
        cartService = new CartService();
    }

    @Test
    @DisplayName("Debería calcular el total correctamente desde un Map (carrito)")
    void testCalcularTotal_ConMap() {
        // 1. ARRANGE (Preparar el escenario)
        // Creamos un carrito falso (un HashMap) para la prueba.
        Map<String, OrderItemModel> carritoDePrueba = new HashMap<>();
        carritoDePrueba.put("Manzanas", new OrderItemModel("Manzanas", 2, 1500.0)); // Subtotal: 3000
        carritoDePrueba.put("Peras", new OrderItemModel("Peras", 3, 2000.0));    // Subtotal: 6000

        double totalEsperado = 9000.0;

        // 2. ACT (Ejecutar la acción)
        // Llamamos al método que queremos probar con nuestros datos falsos.
        double totalCalculado = cartService.calcularTotal(carritoDePrueba);

        // 3. ASSERT (Verificar el resultado)
        // Comprobamos que el resultado sea el que esperábamos.
        assertEquals(totalEsperado, totalCalculado, "El total calculado desde el Map no es correcto.");
    }

    @Test
    @DisplayName("Debería calcular el total correctamente desde una Lista (detalles)")
    void testCalcularTotal_ConList() {
        // 1. ARRANGE
        // Creamos una lista falsa de detalles.
        List<OrderItemModel> detallesDePrueba = new ArrayList<>();
        detallesDePrueba.add(new OrderItemModel("Leche", 1, 4000.0)); // Subtotal: 4000
        detallesDePrueba.add(new OrderItemModel("Pan", 2, 500.0));   // Subtotal: 1000

        double totalEsperado = 5000.0;

        // 2. ACT
        // Llamamos a la otra versión del método.
        double totalCalculado = cartService.calcularTotal(detallesDePrueba);

        // 3. ASSERT
        assertEquals(totalEsperado, totalCalculado, "El total calculado desde la Lista no es correcto.");
    }

    @Test
    @DisplayName("Debería devolver 0 si el carrito (Map) está vacío")
    void testCalcularTotal_ConMapVacio() {
        // 1. ARRANGE
        Map<String, OrderItemModel> carritoVacio = new HashMap<>();

        // 2. ACT
        double totalCalculado = cartService.calcularTotal(carritoVacio);

        // 3. ASSERT
        assertEquals(0.0, totalCalculado, "El total de un carrito vacío debería ser 0.");
    }
}
