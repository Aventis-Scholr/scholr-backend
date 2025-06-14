Feature: US05 Guardar postulación como borrador
  Como postulante
  Quiero guardar mi postulación como borrador
  Para poder completarla más tarde

  Scenario: Guardar postulación con campos esenciales
    Given el usuario ha llenado los campos esenciales del formulario
    When hace clic en el botón "Guardar como borrador"
    Then la información se guarda localmente en el dispositivo

  Scenario: Recuperación automática del borrador
    Given el usuario vuelve a abrir la aplicación
    Then el sistema carga automáticamente los datos guardados
