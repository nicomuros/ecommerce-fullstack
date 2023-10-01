/**
 * En el contexto de las API, un payload es la información que se envía en el cuerpo de una
 * solicitud HTTP o en la respuesta HTTP. Es el contenido real de la comunicación entre el
 * cliente y el servidor.
 *
 * <p>El paquete "payloads" contiene clases que definen objetos de transferencia de datos (DTOs)
 * y objetos de respuesta (Response) utilizados para transmitir datos entre la capa de
 * presentación y la capa de servicio de la aplicación.
 *
 * <p>Los DTOs son clases que se utilizan para encapsular datos y transferirlos entre las
 * capas de la aplicación. Estos objetos son especialmente útiles cuando deseas transmitir
 * solo un subconjunto de los datos de una entidad de modelo o cuando necesitas combinar
 * datos de múltiples fuentes en un solo objeto. Es una buena práctica tener un DTO por
 * modelo cuando la estructura del DTO es diferente de la estructura de la entidad de
 * modelo correspondiente, o cuando necesitas ocultar ciertos detalles internos de la entidad.
 *
 * <p>Los objetos de respuesta (Response) se utilizan para representar las respuestas de la
 * API de la aplicación. A menudo, los Responses contienen datos adicionales relacionados
 * con el resultado de una operación, como un código de estado HTTP y un mensaje. A diferencia
 * de los DTOs, los Responses están diseñados específicamente para la comunicación entre
 * la capa de controladores y la capa de presentación y se utilizan para proporcionar
 * información detallada sobre el resultado de una solicitud.
 *
 * <p>En este paquete, se definen tanto los DTOs como los Responses necesarios para transmitir
 * datos de manera efectiva entre las diferentes capas de la aplicación, siguiendo convenciones
 * y buenas prácticas de diseño de API.
 */

package com.ecommerce.backend.payloads;