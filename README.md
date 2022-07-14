# MeliChallenge
Desarrollo de una aplicación Android que se conecte al api de mercadolibre y muestre el listado de productos y al darle clic el usuario pueda navegar al detalle del producto.

# Arquitectura
MVVM + Clean Arquitecture

##  Capas
* UI: Se encuentra todo lo relacionado con la interface del usuario, toda la UI fue desarrollada con Compose.
* Domain: Se encuentra la logica del negocio encontramos los casos de uso.
* Data: Se encuentra la conexión con el api de mercadolibre.

Para la comunicación entre las capas se utilizo Flow mediante una sealed class llamada Result que gestiona el Success y el Failure.

## Interface del usuario.
Para el desarrollo de las vistas se utilizo Jeckpack Compose se crearon diferentes componentes que son reutilizables así como Preview para visualizar la vista finalizada.

## Inyeccion de dependencias
Para la inyección de dependecias se utilizo Hilt es el recomendado por google y ademas tiene una buena integración con Compose.

## Conexión con el API
Se utilizo Retrofit para realizar los llamados a API con Gson para deserializar los datos.

## Pruebas unitarias
Se realizaron las pruebas de:
* Repositorios
* Mappers
* Casos de uso
* ViewModels

Estas pruebas fueron realizadas con Mockk para crear los mocks y Junit para los assert.

## Pruebas de UI
Se realizaron pruebas de UI para la lista de productos y el card del producto. 

# Herramientas utilizadas
* Kotlin + Coroutines + Flow
* Retrofit
* Hilt
* Gson
* Pruebas unitarias
  * Mockk
  * Junit
* Jetpack
  * Compose
  * Navigation
  * ViewModel
* Architecture
MVVM + Clean Arquitecture
