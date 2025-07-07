Feature: US22 Vista de postulaciones por colaborador
  Como administrador
  Quiero ver todas las postulaciones agrupadas por colaborador
  Para evaluar casos completos

  Scenario: Obtener postulaciones de un apoderado
    Given el administrador tiene el ID del colaborador
    When consulta el endpoint GET /api/v1/applications/apoderado/{apoderadoId}
    Then se listan todas las postulaciones asociadas a ese colaborador
    And cada una incluye su estado actual y tipo de beca
