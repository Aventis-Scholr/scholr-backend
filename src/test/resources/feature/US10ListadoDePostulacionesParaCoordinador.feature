Feature: US10 Listado de postulaciones para coordinador
  Como coordinador de bienestar
  Quiero ver un listado de todas las postulaciones recibidas filtradas por tipo de beca
  Para priorizar revisiones según urgencia

  Scenario: Acceder al listado general de postulaciones
    Given el coordinador accede al panel de gestión
    When consulta el endpoint GET /api/v1/applications
    Then se muestran todas las postulaciones con su estado, tipo de beca y datos del postulante

  Scenario: Filtrar postulaciones por tipo de beca
    Given el coordinador selecciona el filtro "Beca por mérito"
    When aplica el filtro
    Then solo se muestran postulaciones con tipo de beca "MERITO"
