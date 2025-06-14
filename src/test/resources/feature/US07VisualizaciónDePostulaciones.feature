Feature: US07 Visualización de postulaciones
  Como postulante
  Quiero ver mis postulaciones enviadas o guardadas
  Para conocer su estado y detalles

  Scenario: Mostrar lista de postulaciones filtradas por estado
    Given el usuario accede a la sección "Mis postulaciones"
    When selecciona un estado de filtro (ej. "En revisión")
    Then se muestra una lista de postulaciones con ese estado

  Scenario: Ver detalles de una postulación
    Given el usuario hace clic en una postulación
    Then se muestra un modal con el historial, comentarios y documentos

  Scenario: Editar postulación existente
    Given el usuario accede a una postulación editable
    When modifica los campos necesarios
    And hace clic en "Guardar cambios"
    Then el sistema actualiza la postulación mediante un endpoint PUT