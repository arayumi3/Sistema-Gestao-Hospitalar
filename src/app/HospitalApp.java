package app;

import service.HospitalFacade;
import exceptions.*;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HospitalApp {

    private static HospitalFacade facade = new HospitalFacade();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema de Gestão Hospitalar!");

        inicializarDadosDeExemplo();

        int opcao = 0;

        do {
            exibirMenu();
            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarPaciente();
                        break;
                    case 2:
                        darEntradaPaciente();
                        break;
                    case 3:
                        internarPaciente();
                        break;
                    case 4:
                        cadastrarLeito();
                        break;
                    case 5:
                        cadastrarMedico();
                        break;
                    case 6:
                        efetuarConsulta();
                        break;
                    case 7:
                        emitirReceita();
                        break;
                    case 8:
                        emitirPedidoExame();
                        break;
                    case 9:
                        listarPacientes();
                        break;
                    case 10:
                        listarMedicos();
                        break;
                    case 11:
                        listarLeitos();
                        break;
                    case 12:
                        listarConsultas();
                        break;
                    case 13:
                        listarReceitas();
                        break;
                    case 14:
                        listarExames();
                        break;
                    case 15:
                        listarAtendimentos();
                        break;
                    case 16:
                        buscarProntuarioPaciente();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema. Obrigado!");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção entre 0 e 16.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, digite um número inteiro para a opção do menu.");
                scanner.nextLine();
                opcao = -1;
            } catch (RuntimeException e) {
                System.err.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Cadastrar Paciente");
        System.out.println("2. Dar Entrada em Paciente (para Consulta/Atendimento)");
        System.out.println("3. Internar Paciente");
        System.out.println("4. Cadastrar Leito");
        System.out.println("5. Cadastrar Médico");
        System.out.println("6. Efetuar Consulta");
        System.out.println("7. Emitir Receita");
        System.out.println("8. Emitir Pedido de Exame");
        System.out.println("--- Consultas ---");
        System.out.println("9. Listar Pacientes");
        System.out.println("10. Listar Médicos");
        System.out.println("11. Listar Leitos");
        System.out.println("12. Listar Consultas");
        System.out.println("13. Listar Receitas");
        System.out.println("14. Listar Exames");
        System.out.println("15. Listar Atendimentos");
        System.out.println("16. Buscar Prontuário do Paciente");
        System.out.println("0. Sair");
        System.out.println("--------------------");
    }

    private static void cadastrarPaciente() {
        System.out.println("\n--- Cadastrar Paciente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        System.out.print("Plano de Saúde: ");
        String planoSaude = scanner.nextLine();
        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        String dataNascimentoStr = scanner.nextLine();
        LocalDate dataNascimento = null;
        try {
            dataNascimento = LocalDate.parse(dataNascimentoStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data de nascimento inválido. Use AAAA-MM-DD.");
        }

        facade.cadastrarPaciente(nome, cpf, telefone, rua, numero, bairro, cidade, estado, cep, planoSaude, dataNascimento);
    }

    private static void darEntradaPaciente() {
        System.out.println("\n--- Dar Entrada em Paciente ---");
        System.out.print("CPF do Paciente: ");
        String cpfPaciente = scanner.nextLine();
        System.out.print("CRM do Médico para atendimento inicial: ");
        String crmMedico = scanner.nextLine();

        facade.darEntradaPaciente(cpfPaciente, crmMedico);
    }

    private static void internarPaciente() {
        System.out.println("\n--- Internar Paciente ---");
        System.out.print("CPF do Paciente: ");
        String cpfPaciente = scanner.nextLine();
        System.out.print("ID do Leito: ");
        String idLeito = scanner.nextLine();
        System.out.print("CRM do Médico responsável pela internação: ");
        String crmMedico = scanner.nextLine();

        facade.internarPaciente(cpfPaciente, idLeito, crmMedico);
    }

    private static void cadastrarLeito() {
        System.out.println("\n--- Cadastrar Leito ---");
        System.out.print("ID do Leito (ex: UTI-01, ENF-05): ");
        String id = scanner.nextLine();
        System.out.print("Tipo do Leito (ex: UTI, Enfermaria, Particular): ");
        String tipoDescricao = scanner.nextLine();

        facade.cadastrarLeito(id, tipoDescricao);
    }

    private static void cadastrarMedico() {
        System.out.println("\n--- Cadastrar Médico ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        System.out.print("CRM: ");
        String crm = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        facade.cadastrarMedico(nome, cpf, telefone, rua, numero, bairro, cidade, estado, cep, crm, especialidade);
    }

    private static void efetuarConsulta() {
        System.out.println("\n--- Efetuar Consulta ---");
        System.out.print("CPF do Paciente: ");
        String cpfPaciente = scanner.nextLine();
        System.out.print("CRM do Médico: ");
        String crmMedico = scanner.nextLine();
        System.out.print("Data e Hora da Consulta (AAAA-MM-DDTHH:MM, ex: 2025-07-10T14:30): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = null;
        try {
            dataHora = LocalDateTime.parse(dataHoraStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data e hora inválido. Use AAAA-MM-DDTHH:MM.");
        }
        System.out.print("Diagnóstico: ");
        String diagnostico = scanner.nextLine();

        facade.efetuarConsulta(cpfPaciente, crmMedico, dataHora, diagnostico);
    }

    private static void emitirReceita() {
        System.out.println("\n--- Emitir Receita ---");
        System.out.print("ID da Consulta associada: ");
        String idConsulta = scanner.nextLine();

        System.out.println("Digite os medicamentos (um por linha, digite 'fim' para terminar):");
        List<String> medicamentos = new ArrayList<>();
        String medicamento;
        while (!(medicamento = scanner.nextLine()).equalsIgnoreCase("fim")) {
            if (!medicamento.trim().isEmpty()) {
                medicamentos.add(medicamento);
            }
        }
        if (medicamentos.isEmpty()) {
            throw new CampoObrigatorioException("Pelo menos um medicamento deve ser informado para a receita.");
        }

        System.out.print("Observações (opcional): ");
        String observacoes = scanner.nextLine();

        facade.emitirReceita(idConsulta, medicamentos, observacoes.isEmpty() ? null : observacoes);
    }

    private static void emitirPedidoExame() {
        System.out.println("\n--- Emitir Pedido de Exame ---");
        System.out.print("CPF do Paciente: ");
        String cpfPaciente = scanner.nextLine();
        System.out.print("CRM do Médico Solicitante: ");
        String crmMedico = scanner.nextLine();
        System.out.print("Nome do Tipo de Exame (ex: Hemograma, Raio-X): ");
        String nomeTipoExame = scanner.nextLine();
        System.out.print("Data da Solicitação (AAAA-MM-DD): ");
        String dataSolicitacaoStr = scanner.nextLine();
        LocalDate dataSolicitacao = null;
        try {
            dataSolicitacao = LocalDate.parse(dataSolicitacaoStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data de solicitação inválido. Use AAAA-MM-DD.");
        }

        try {
            facade.cadastrarTipoExame(nomeTipoExame, "Descrição padrão para " + nomeTipoExame);
        } catch (IllegalArgumentException e) {
            // Ignorar se já existe
        }

        facade.emitirPedidoExame(cpfPaciente, crmMedico, nomeTipoExame, dataSolicitacao);
    }

    private static void listarPacientes() {
        System.out.println("\n--- Lista de Pacientes ---");
        List<Paciente> pacientes = facade.listarPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            pacientes.forEach(System.out::println);
        }
    }

    private static void listarMedicos() {
        System.out.println("\n--- Lista de Médicos ---");
        List<Medico> medicos = facade.listarMedicos();
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
        } else {
            medicos.forEach(System.out::println);
        }
    }

    private static void listarLeitos() {
        System.out.println("\n--- Lista de Leitos ---");
        List<Leito> leitos = facade.listarLeitos();
        if (leitos.isEmpty()) {
            System.out.println("Nenhum leito cadastrado.");
        } else {
            leitos.forEach(System.out::println);
        }
    }

    private static void listarConsultas() {
        System.out.println("\n--- Lista de Consultas ---");
        List<Consulta> consultas = facade.listarConsultas();
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta registrada.");
        } else {
            consultas.forEach(System.out::println);
        }
    }

    private static void listarReceitas() {
        System.out.println("\n--- Lista de Receitas ---");
        List<Receita> receitas = facade.listarReceitas();
        if (receitas.isEmpty()) {
            System.out.println("Nenhuma receita emitida.");
        } else {
            receitas.forEach(System.out::println);
        }
    }

    private static void listarExames() {
        System.out.println("\n--- Lista de Exames ---");
        List<Exame> exames = facade.listarExames();
        if (exames.isEmpty()) {
            System.out.println("Nenhum pedido de exame registrado.");
        } else {
            exames.forEach(System.out::println);
        }
    }

    private static void listarAtendimentos() {
        System.out.println("\n--- Lista de Atendimentos ---");
        List<Atendimento> atendimentos = facade.listarAtendimentos();
        if (atendimentos.isEmpty()) {
            System.out.println("Nenhum atendimento registrado.");
        } else {
            atendimentos.forEach(System.out::println);
        }
    }

    private static void buscarProntuarioPaciente() {
        System.out.println("\n--- Buscar Prontuário do Paciente ---");
        System.out.print("CPF do Paciente: ");
        String cpfPaciente = scanner.nextLine();
        Prontuario prontuario = facade.buscarProntuarioPaciente(cpfPaciente);
        System.out.println(prontuario);
    }

    private static void inicializarDadosDeExemplo() {
        try {
            facade.cadastrarTipoLeito("UTI");
            facade.cadastrarTipoLeito("Enfermaria");
            facade.cadastrarTipoLeito("Particular");

            facade.cadastrarLeito("L-UTI-01", "UTI");
            facade.cadastrarLeito("L-ENF-01", "Enfermaria");
            facade.cadastrarLeito("L-PART-01", "Particular");

            facade.cadastrarEspecialidade("Cardiologia");
            facade.cadastrarEspecialidade("Pediatria");
            facade.cadastrarEspecialidade("Clínico Geral");

            facade.cadastrarMedico("Dr. João Silva", "111.111.111-11", "98765-4321", "Rua A", "100", "Centro", "Recife", "PE", "50000-000", "CRM123", "Cardiologia");
            facade.cadastrarMedico("Dra. Maria Souza", "222.222.222-22", "12345-6789", "Av. B", "200", "Boa Viagem", "Recife", "PE", "51000-000", "CRM456", "Pediatria");

            facade.cadastrarPaciente("Ana Paula", "333.333.333-33", "99999-8888", "Rua C", "30", "Madalena", "Recife", "PE", "50700-000", "Unimed", LocalDate.of(1990, 5, 15));
            facade.cadastrarPaciente("Carlos Alberto", "444.444.444-44", "97777-6666", "Rua D", "40", "Pina", "Recife", "PE", "51100-000", "SUS", LocalDate.of(1985, 10, 20));

            facade.cadastrarEnfermeiro("Enf. Fernanda Costa", "555.555.555-55", "96666-5555", "Rua E", "50", "Graças", "Recife", "PE", "52000-000", "COREN789");

            facade.cadastrarAdministrativo("Adm. Ricardo Gomes", "666.666.666-66", "95555-4444", "Rua F", "60", "Casa Forte", "Recife", "PE", "52000-100", "Secretário");

            System.out.println("\nDados de exemplo inicializados.");
        } catch (RuntimeException e) {
            System.err.println("Erro ao inicializar dados de exemplo: " + e.getMessage());
        }
    }
}