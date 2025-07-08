Feature: US03 Confirmación de verificación exitosa
  Como padre/trabajador
  Quiero recibir confirmación con un código único al enviar mi postulación
  Para tener un comprobante digital de mi solicitud

  Scenario: Postulación enviada correctamente muestra código único
    Given el padre completa todos los campos de la postulación
    When hace clic en "Enviar postulación"
    Then el sistema responde con estado 201
    And muestra un mensaje de éxito junto a un código único de confirmación

  Scenario: Postulación incompleta no genera código
    Given el padre omite campos obligatorios en la postulación
    When intenta enviarla
    Then el sistema responde con error 400
    And no se genera código de confirmación
