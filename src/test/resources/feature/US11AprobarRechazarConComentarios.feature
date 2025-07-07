Feature: US11 Aprobar/rechazar postulaciones con comentarios
  Como coordinador de bienestar
  Quiero aprobar/rechazar postulaciones con comentarios personalizados
  Para mantener informados a los padres/trabajadores

  Scenario: Aprobar una postulación con un comentario
    Given el coordinador abre una postulación pendiente
    When selecciona "Aprobar", agrega un comentario y hace clic en "Confirmar"
    Then el sistema cambia el estado de la postulación a "APROBADO"
    And almacena el comentario en el registro de la postulación

  Scenario: Rechazar una postulación con justificación
    Given el coordinador abre una postulación pendiente
    When selecciona "Rechazar", escribe el motivo y confirma
    Then el sistema cambia el estado a "RECHAZADO"
    And se notifica al usuario con el comentario personalizado
