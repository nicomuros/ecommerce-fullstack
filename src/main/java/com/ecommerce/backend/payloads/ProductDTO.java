package com.ecommerce.backend.payloads;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase "ProductDTO" es un objeto de transferencia de datos (DTO) que se utiliza
 * para representar datos relacionados con un producto en la aplicación. Esta clase
 * está diseñada específicamente para transferir información sobre productos entre
 * la capa de presentación y la capa de servicio de la aplicación.
 *
 * Los atributos de esta clase corresponden a las propiedades de un producto, como
 * su identificador, nombre, descripción, precio y stock. Al utilizar un DTO como este,
 * puedes controlar exactamente qué datos se transmiten entre las capas y evitar
 * transmitir datos innecesarios.
 *
 * La anotación "@Data" de Lombok se utiliza en esta clase para generar automáticamente
 * métodos estándar como getters, setters, equals, hashCode y toString, lo que simplifica
 * la creación y el mantenimiento de la clase. Esto reduce la cantidad de código repetitivo
 * que se necesita escribir para una clase simple de transferencia de datos.
 *
 * Las anotaciones "@NoArgsConstructor" y "@AllArgsConstructor" también son de Lombok y se
 * utilizan para generar automáticamente constructores sin argumentos y constructores que
 * aceptan todos los campos de la clase. Estos constructores son útiles al crear instancias
 * de la clase desde diferentes partes de la aplicación, como en la capa de controladores o
 * al recibir datos de una solicitud HTTP.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    Integer product_id;
    String name;
    String description;
    double price;
    Integer stock;
}
