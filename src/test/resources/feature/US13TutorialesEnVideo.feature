Feature: US13 Tutoriales en video
  Como usuario nuevo
  Quiero ver un tutorial en video
  Para entender cómo usar la plataforma

  Scenario: Ver tutorial en ventana emergente
    Given el usuario accede a la sección de ayuda
    When hace clic en "Ver tutorial"
    Then se muestra un video en una ventana emergente con controles

  Scenario: Verificar experiencia en móviles
    Given el usuario está en un dispositivo móvil
    Then el tutorial debe ser accesible y funcional