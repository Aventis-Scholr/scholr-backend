Feature: US20 Vista de becas disponibles de la empresa
  Como administrador
  Quiero ver todas las becas activas de mi empresa
  Para gestionar oportunidades vigentes

  Scenario: Ver listado de becas activas por empresa
    Given el administrador accede a la sección "Becas"
    When ingresa el nombre de su empresa en el filtro
    And hace clic en "Buscar"
    Then el sistema muestra todas las becas activas asociadas a esa empresa
    And se utiliza el endpoint GET /api/v1/scholarships/company/{companyName}

  Scenario: Visualizar detalles clave de cada beca
    Given el sistema muestra el listado de becas activas
    Then cada beca debe mostrar nombre, tipo, estado, requisitos principales y fecha límite

  Scenario: No hay becas activas para la empresa
    Given el administrador busca becas de su empresa sin resultados activos
    Then el sistema muestra el mensaje "No hay becas activas actualmente para esta empresa"
