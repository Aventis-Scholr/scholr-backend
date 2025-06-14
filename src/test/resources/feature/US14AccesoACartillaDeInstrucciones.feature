Feature: US14 Acceso a cartilla de instrucciones
  Como usuario
  Quiero descargar una cartilla en PDF
  Para tener instrucciones claras sobre el proceso

  Scenario: Descargar cartilla
    Given el usuario está en la sección de ayuda
    When hace clic en el botón "Descargar cartilla"
    Then el sistema guarda el archivo en la carpeta de descargas
    And muestra un mensaje de confirmación o visor PDF