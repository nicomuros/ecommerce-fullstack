/**
 * El paquete "repository" contiene las interfaces de repositorio que interactúan
 * con la base de datos de la aplicación. Esta capa desempeña un papel fundamental
 * en la persistencia de datos y la recuperación de información de la base de datos.
 *
 * <p>En esta capa, generalmente se definen interfaces que extienden JpaRepository
 * u otras interfaces proporcionadas por un framework de persistencia, como Spring
 * Data JPA. Estas interfaces permiten realizar operaciones de CRUD (Crear, Leer,
 * Actualizar y Eliminar) en las entidades de la aplicación de una manera sencilla.
 *
 * <p>Si por ejemplo quiero usar otro repositorio (como JDBC manual) puedo crear el servicio
 * de acceso, agregarle el qualifier("jdbc") y seleccionarlo en la capa de servicio que del cual
 * dependa
 *
 * <p>Además de las operaciones estándar de CRUD, en esta capa también se pueden agregar
 * métodos personalizados para acceder a la base de datos de acuerdo con los requisitos
 * específicos de la aplicación. Estos métodos personalizados pueden incluir consultas
 * personalizadas, búsquedas complejas o cualquier operación de base de datos que no
 * esté cubierta por las operaciones CRUD estándar.
 *
 * <p>La capa "repository" actúa como una interfaz entre la lógica de negocio de la
 * aplicación y la base de datos subyacente. Proporciona una abstracción que permite
 * a la lógica de negocio interactuar con los datos sin preocuparse por los detalles
 * de cómo se almacenan y recuperan en la base de datos.
 *
 *
 * <p>ProductDao
 * Es la interfaz del servicio de acceso a datos. Es decir, se va a acceder a los datos a partir de dicha interfaz
 *
 * <p>ProductsJPADataAccessService es un repositorio que utiliza JPA. Para poder usarlo, tenemos que crear la interfaz
 * ProductsRepository
 *
 */
package com.ecommerce.backend.repository;