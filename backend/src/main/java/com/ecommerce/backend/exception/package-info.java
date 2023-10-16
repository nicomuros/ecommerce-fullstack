/**
 * En este paquete diseño las excepciones "craft" o personales.
 *
 * <p>ResourceNotFoundException: Es una excepcion que expresa que el recurso al cual se intenta
 * acceder no existe. Es necesaria porque sin ella la respuesta contiene un código 5xx, que implica
 * errores en el servidor (cuando en realidad, el error está desde el lado del cliente, puesto
 * que solicitan por un producto inexistente). En base a eso, se declara una ResponseStatus.NOT_FOUND,
 * y se construye la declaración del mensaje. Extiende de RuntimeException porque el error no se da en
 * compilación, sino una vez que está corriendo el programa
 *
 * <p>DuplicateResourceException: Es una exepción "craft" que indica que el recurso que se está intentando
 * crear en la base de datos ya existe.
 *
 * <p>RequestValidationException: Se lanza cuando se recibe una solicitud de actualización, pero el estado nuevo
 * es igual al estado previo
 */
package com.ecommerce.backend.exception;