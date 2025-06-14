Feature: US16 Visualización de información clave
  Como usuario
  Quiero ver información destacada en tarjetas
  Para entender los beneficios o estadísticas del sistema

  Scenario: Mostrar tarjetas con texto e íconos
    Given el usuario está en la pantalla principal
    Then se muestran 3 tarjetas interactivas con íconos y texto

  Scenario: Mostrar video resumen
    Given el usuario accede a la pantalla principal
    Then se muestra un video corto en autoplay sin sonido

  Scenario: Mostrar estadísticas destacadas
    Given el usuario está en la pantalla principal
    Then se muestran métricas como "95% de postulaciones procesadas en 72h"
