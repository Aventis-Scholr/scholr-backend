Feature: US01 Selección de empresa durante el registro
  Como usuario que desea registrarse
  Quiero seleccionar una empresa y subir documentos
  Para completar correctamente mi proceso de registro

  Scenario Outline: Seleccionar empresa y subir documentos
    Given el usuario se encuentra en la pantalla de registro
    When selecciona una <empresa> válida
    And sube los documentos requeridos
    Then el botón "Continuar" se habilita
    And el usuario puede avanzar al siguiente paso

    Examples:
      | empresa      |
      | Acme Corp    |

  Scenario Outline: Validación visual del botón 'Continuar'
    Given el usuario está en la pantalla de registro
    When completa correctamente todos los campos requeridos
    Then el botón "Continuar" debe estar habilitado

    Examples:
      | empresa      |
      | Acme Corp    |
