Feature: US04 Subir documentos de postulación
  Como padre/trabajador
  Quiero subir documentos (credencial laboral, notas de mi hijo) directamente en la app
  Para evitar trámites presenciales y acelerar mi postulación

  Scenario: Subida exitosa de documentos a la postulación
    Given el padre tiene una postulación creada
    When sube los archivos DNI, Libreta, Constancia, DNI del Apoderado y Declaración Jurada
    Then el sistema guarda las URLs de los archivos en la aplicación
    And responde con estado 200 y las URLs en el cuerpo

  Scenario: Error al subir archivos no válidos
    Given el padre intenta subir archivos corruptos o en formato no permitido
    When hace clic en "Subir"
    Then el sistema muestra un mensaje de error indicando el problema
