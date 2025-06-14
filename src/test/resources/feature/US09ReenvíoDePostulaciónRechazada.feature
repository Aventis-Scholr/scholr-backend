Feature: US09 Reenvío de postulación rechazada
  Como apoderado
  Quiero corregir y reenviar una postulación rechazada
  Para que vuelva a ser evaluada

  Scenario: Reenviar postulación dentro del plazo
    Given la postulación fue rechazada y está dentro del plazo de reenvío
    When el apoderado corrige los errores y hace clic en "Reenviar"
    Then el sistema envía la postulación para una nueva evaluación

  Scenario: Reenviar fuera del plazo
    Given la postulación fue rechazada y ha pasado el plazo de reenvío
    When el apoderado intenta reenviar
    Then el sistema muestra un mensaje de error: "Plazo vencido para reenvío"
