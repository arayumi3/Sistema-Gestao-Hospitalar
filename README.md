# Sistema de Gestão Hospitalar em Java

Projeto de Programação Orientada a Objetos desenvolvido no 2º período. O sistema funciona via terminal e simula a gestão completa das rotinas de um hospital, utilizando uma arquitetura baseada em camadas.

## Arquitetura do Projeto:
- **Models:** Classes de entidades que representam o domínio do hospital (Pacientes, Médicos, Leitos, Consultas, Receitas, etc.).
- **Repositories:** Estrutura de armazenamento em memória para gerir os dados de cada entidade.
- **Service (Facade):** A classe `HospitalFacade` centraliza as regras de negócio e a comunicação entre o sistema e os repositórios.
- **Exceptions:** Pacote dedicado ao tratamento de erros personalizados, como `LeitoOcupadoException` ou `PacienteJaInternadoException`.

## Funcionalidades principais:
- Registo de utentes (Pacientes), equipa médica (Médicos, Enfermeiros) e equipa administrativa.
- Gestão de atendimentos, internamentos em leitos específicos e consultas.
- Emissão de receitas médicas e pedidos de exames associados a um Prontuário.
- Menu interativo no terminal para navegação e operações no sistema (`HospitalApp.java`).
