Feature: US15 Visualizar información de contacto de soporte
  Como usuario
  Quiero ver los datos de contacto de soporte
  Para pedir ayuda cuando lo necesite

  Scenario: Ver bloque de contacto
    Given el usuario accede a la sección de ayuda
    Then se muestra un recuadro con número telefónico y correo de contacto

  Scenario: Teléfono clicable en móviles
    Given el usuario está en un móvil
    Then el número de teléfono debe ser clicable

  Scenario: Accesibilidad del cuadro de contacto
    Given el usuario visualiza el recuadro de contacto
    Then debe ser claramente visible y legible
