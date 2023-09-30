/**
 * El paquete "service" contiene las clases de servicio que implementan la lógica de
 * negocio de la aplicación. Esta capa se encarga de coordinar y gestionar las
 * operaciones y reglas de negocio de la aplicación.
 *
 * <p>En la capa de servicio, se definen clases que encapsulan la funcionalidad de la
 * aplicación, y estas clases interactúan con las entidades y los repositorios para
 * realizar operaciones más complejas que no se limitan a simples operaciones CRUD.
 * Especificamente, trabaja con la capa de acceso a datos, a traves de la interfaz ProductDao
 *
 * <p>Las clases de servicio proporcionan una abstracción de alto nivel que permite
 * orquestar múltiples operaciones y coordinar la lógica empresarial de manera coherente.
 * Esto significa que aquí es donde se aplican las reglas de negocio específicas de la
 * aplicación, la validación de datos y cualquier otra lógica que no pertenezca a la
 * capa de acceso a la base de datos.
 */

package com.ecommerce.backend.service;