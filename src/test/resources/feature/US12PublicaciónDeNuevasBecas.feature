Feature: US12 Publicación de nuevas becas
  Como coordinador de bienestar
  Quiero publicar fechas límite y requisitos de nuevas becas en la app
  Para evitar consultas masivas al departamento de RRHH

  Scenario: Crear una nueva beca con todos los campos requeridos
    Given el coordinador accede al formulario de "Nueva beca"
    When completa los campos: nombre, empresa, tipo, estado, requisitos y coordinador asignado
    And hace clic en "Publicar"
    Then el sistema registra la beca mediante el endpoint POST /api/v1/scholarships
    And muestra un mensaje de confirmación "Beca publicada correctamente"

  Scenario: Validar campos obligatorios antes de publicar
    Given el coordinador intenta publicar una beca sin completar todos los campos requeridos
    When hace clic en "Publicar"
    Then el sistema muestra mensajes de error indicando los campos faltantes

  Scenario: Ver la nueva beca en la lista general
    Given el coordinador ha publicado una nueva beca exitosamente
    When accede al listado de becas
    Then la nueva beca aparece en la lista con su nombre, tipo, estado y empresa asociada
