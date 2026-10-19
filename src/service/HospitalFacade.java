package service;

import model.*;
import repository.*;
import exceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class HospitalFacade {

    private PacienteRepository pacienteRepository;
    private MedicoRepository medicoRepository;
    private LeitoRepository leitoRepository;
    private ConsultaRepository consultaRepository;
    private ReceitaRepository receitaRepository;
    private ExameRepository exameRepository;
    private AtendimentoRepository atendimentoRepository;
    private ProntuarioRepository prontuarioRepository;
    private TipoExameRepository tipoExameRepository;
    private TipoLeitoRepository tipoLeitoRepository;
    private EspecialidadeRepository especialidadeRepository;
    private EnfermeiroRepository enfermeiroRepository;
    private AdministrativoRepository administrativoRepository;
    private HospitalRepository hospitalRepository;


    public HospitalFacade() {
        this.pacienteRepository = new PacienteRepository();
        this.medicoRepository = new MedicoRepository();
        this.leitoRepository = new LeitoRepository();
        this.consultaRepository = new ConsultaRepository();
        this.receitaRepository = new ReceitaRepository();
        this.exameRepository = new ExameRepository();
        this.atendimentoRepository = new AtendimentoRepository();
        this.prontuarioRepository = new ProntuarioRepository();
        this.tipoExameRepository = new TipoExameRepository();
        this.tipoLeitoRepository = new TipoLeitoRepository();
        this.especialidadeRepository = new EspecialidadeRepository();
        this.enfermeiroRepository = new EnfermeiroRepository();
        this.administrativoRepository = new AdministrativoRepository();
        this.hospitalRepository = new HospitalRepository();
    }

    public Paciente cadastrarPaciente(String nome, String cpf, String telefone,
                                      String rua, String numero, String bairro, String cidade, String estado, String cep,
                                      String planoSaude, LocalDate dataNascimento) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoObrigatorioException("O nome do paciente é obrigatório.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new CampoObrigatorioException("O CPF do paciente é obrigatório.");
        }

        if (pacienteRepository.findByCpf(cpf).isPresent()) {
            throw new IllegalArgumentException("Paciente com CPF " + cpf + " já cadastrado.");
        }

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        Paciente paciente = new Paciente(nome, cpf, telefone, endereco, planoSaude, dataNascimento);
        pacienteRepository.add(paciente);
        Prontuario prontuario = new Prontuario(UUID.randomUUID().toString(), paciente);
        prontuarioRepository.add(prontuario);

        System.out.println("Paciente cadastrado com sucesso: " + paciente);
        return paciente;
    }

    public Medico cadastrarMedico(String nome, String cpf, String telefone,
                                  String rua, String numero, String bairro, String cidade, String estado, String cep,
                                  String crm, String nomeEspecialidade) {
        if (nome == null || nome.trim().isEmpty() ||
                cpf == null || cpf.trim().isEmpty() ||
                crm == null || crm.trim().isEmpty() ||
                nomeEspecialidade == null || nomeEspecialidade.trim().isEmpty()) {
            throw new CampoObrigatorioException("Todos os campos de cadastro de médico são obrigatórios.");
        }

        if (medicoRepository.findByCrm(crm).isPresent()) {
            throw new IllegalArgumentException("Médico com CRM " + crm + " já cadastrado.");
        }
        if (pacienteRepository.findByCpf(cpf).isPresent() || medicoRepository.findByCrm(cpf).isPresent()
                || enfermeiroRepository.findByCpf(cpf).isPresent() || administrativoRepository.findByCpf(cpf).isPresent()){
            throw new IllegalArgumentException("Já existe uma pessoa cadastrada com o CPF " + cpf + ".");
        }

        Especialidade especialidade = especialidadeRepository.findByNome(nomeEspecialidade)
                .orElseGet(() -> {
                    Especialidade novaEspecialidade = new Especialidade(nomeEspecialidade);
                    especialidadeRepository.add(novaEspecialidade);
                    return novaEspecialidade;
                });

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        Medico medico = new Medico(nome, cpf, telefone, endereco, crm, especialidade);
        medicoRepository.add(medico);
        System.out.println("Médico cadastrado com sucesso: " + medico);
        return medico;
    }

    public Leito cadastrarLeito(String id, String descricaoTipo) {
        if (id == null || id.trim().isEmpty() || descricaoTipo == null || descricaoTipo.trim().isEmpty()) {
            throw new CampoObrigatorioException("ID do leito e descrição do tipo são obrigatórios.");
        }

        if (leitoRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Leito com ID " + id + " já cadastrado.");
        }

        TipoLeito tipoLeito = tipoLeitoRepository.findByDescricao(descricaoTipo)
                .orElseGet(() -> {
                    TipoLeito novoTipo = new TipoLeito(descricaoTipo);
                    tipoLeitoRepository.add(novoTipo);
                    return novoTipo;
                });

        Leito leito = new Leito(id, tipoLeito);
        leitoRepository.add(leito);
        System.out.println("Leito cadastrado com sucesso: " + leito);
        return leito;
    }

    public Atendimento darEntradaPaciente(String cpfPaciente, String crmMedico) {
        if (cpfPaciente == null || cpfPaciente.trim().isEmpty() || crmMedico == null || crmMedico.trim().isEmpty()) {
            throw new CampoObrigatorioException("CPF do paciente e CRM do médico são obrigatórios para dar entrada.");
        }

        Paciente paciente = pacienteRepository.findByCpf(cpfPaciente)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente com CPF " + cpfPaciente + " não encontrado."));
        Medico medico = medicoRepository.findByCrm(crmMedico)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico com CRM " + crmMedico + " não encontrado."));

        String atendimentoId = UUID.randomUUID().toString();
        Atendimento atendimento = new Atendimento(atendimentoId, paciente, LocalDateTime.now(), "Consulta", medico);
        atendimentoRepository.add(atendimento);

        prontuarioRepository.findByPacienteCpf(cpfPaciente)
                .ifPresent(p -> p.adicionarAtendimento(atendimento));

        System.out.println("Entrada do paciente " + paciente.getNome() + " registrada para consulta com " + medico.getNome());
        return atendimento;
    }

    public Atendimento internarPaciente(String cpfPaciente, String idLeito, String crmMedico) {
        if (cpfPaciente == null || cpfPaciente.trim().isEmpty() ||
                idLeito == null || idLeito.trim().isEmpty() ||
                crmMedico == null || crmMedico.trim().isEmpty()) {
            throw new CampoObrigatorioException("CPF do paciente, ID do leito e CRM do médico são obrigatórios para internação.");
        }

        Paciente paciente = pacienteRepository.findByCpf(cpfPaciente)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente com CPF " + cpfPaciente + " não encontrado."));
        Leito leito = leitoRepository.findById(idLeito)
                .orElseThrow(() -> new LeitoInexistenteException("Leito com ID " + idLeito + " não encontrado."));
        Medico medico = medicoRepository.findByCrm(crmMedico)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico com CRM " + crmMedico + " não encontrado."));

        if (leito.isOcupado()) {
            throw new LeitoOcupadoException("Leito " + idLeito + " já está ocupado.");
        }

        boolean pacienteAtualmenteInternado = atendimentoRepository.findAll().stream()
                .filter(at -> at.getPaciente().equals(paciente) && at.getLeito() != null && at.getDataSaida() == null)
                .findAny()
                .isPresent();

        if (pacienteAtualmenteInternado) {
            throw new PacienteJaInternadoException("Paciente " + paciente.getNome() + " já está internado.");
        }

        leito.setOcupado(true);
        String atendimentoId = UUID.randomUUID().toString();
        Atendimento atendimento = new Atendimento(atendimentoId, paciente, LocalDateTime.now(), "Internação", medico);
        atendimento.setLeito(leito);
        atendimentoRepository.add(atendimento);

        prontuarioRepository.findByPacienteCpf(cpfPaciente)
                .ifPresent(p -> p.adicionarAtendimento(atendimento));

        System.out.println("Paciente " + paciente.getNome() + " internado com sucesso no leito " + leito.getId());
        return atendimento;
    }

    public Consulta efetuarConsulta(String cpfPaciente, String crmMedico, LocalDateTime dataHora, String diagnostico) {
        if (cpfPaciente == null || cpfPaciente.trim().isEmpty() ||
                crmMedico == null || crmMedico.trim().isEmpty() ||
                dataHora == null || diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new CampoObrigatorioException("CPF do paciente, CRM do médico, data/hora e diagnóstico são obrigatórios para a consulta.");
        }

        Paciente paciente = pacienteRepository.findByCpf(cpfPaciente)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente com CPF " + cpfPaciente + " não encontrado."));
        Medico medico = medicoRepository.findByCrm(crmMedico)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico com CRM " + crmMedico + " não encontrado."));

        String consultaId = UUID.randomUUID().toString();
        Consulta consulta = new Consulta(consultaId, paciente, medico, dataHora);
        consulta.setDiagnostico(diagnostico);
        consulta.setRealizada(true);
        consultaRepository.add(consulta);

        prontuarioRepository.findByPacienteCpf(cpfPaciente)
                .ifPresent(p -> p.adicionarConsulta(consulta));

        System.out.println("Consulta de " + paciente.getNome() + " com Dr. " + medico.getNome() + " efetuada com sucesso.");
        return consulta;
    }

    public Receita emitirReceita(String idConsulta, List<String> medicamentos, String observacoes) {
        if (idConsulta == null || idConsulta.trim().isEmpty()) {
            throw new CampoObrigatorioException("ID da consulta é obrigatório para emitir receita.");
        }
        if (medicamentos == null || medicamentos.isEmpty()) {
            throw new CampoObrigatorioException("A receita deve conter pelo menos um medicamento.");
        }

        Consulta consulta = consultaRepository.findById(idConsulta)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Consulta com ID " + idConsulta + " não encontrada. Não é possível emitir receita sem uma consulta associada."));

        if (!consulta.isRealizada()) {
            throw new IllegalStateException("A receita só pode ser emitida para consultas já realizadas.");
        }

        String receitaId = UUID.randomUUID().toString();
        Receita receita = new Receita(receitaId, consulta.getPaciente(), consulta.getMedico(), LocalDate.now());
        medicamentos.forEach(receita::adicionarMedicamento);
        receita.setObservacoes(observacoes);
        receitaRepository.add(receita);

        prontuarioRepository.findByPacienteCpf(consulta.getPaciente().getCpf())
                .ifPresent(p -> p.adicionarReceita(receita));

        System.out.println("Receita emitida com sucesso para o paciente " + consulta.getPaciente().getNome() + " da consulta " + consulta.getId());
        return receita;
    }

    public Exame emitirPedidoExame(String cpfPaciente, String crmMedico, String nomeTipoExame, LocalDate dataSolicitacao) {
        if (cpfPaciente == null || cpfPaciente.trim().isEmpty() ||
                crmMedico == null || crmMedico.trim().isEmpty() ||
                nomeTipoExame == null || nomeTipoExame.trim().isEmpty() ||
                dataSolicitacao == null) {
            throw new CampoObrigatorioException("CPF do paciente, CRM do médico, tipo de exame e data de solicitação são obrigatórios para emitir pedido de exame.");
        }

        Paciente paciente = pacienteRepository.findByCpf(cpfPaciente)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente com CPF " + cpfPaciente + " não encontrado."));
        Medico medico = medicoRepository.findByCrm(crmMedico)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico com CRM " + crmMedico + " não encontrado."));

        TipoExame tipoExame = tipoExameRepository.findByNome(nomeTipoExame)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tipo de exame '" + nomeTipoExame + "' não encontrado. Cadastre o tipo de exame primeiro."));

        String exameId = UUID.randomUUID().toString();
        Exame exame = new Exame(exameId, paciente, medico, tipoExame, dataSolicitacao);
        exameRepository.add(exame);

        prontuarioRepository.findByPacienteCpf(cpfPaciente)
                .ifPresent(p -> p.adicionarExame(exame));

        System.out.println("Pedido de exame (" + tipoExame.getNome() + ") emitido com sucesso para o paciente " + paciente.getNome());
        return exame;
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Paciente com CPF " + cpf + " não encontrado."));
    }

    public Medico buscarMedicoPorCrm(String crm) {
        return medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Médico com CRM " + crm + " não encontrado."));
    }

    public Leito buscarLeitoPorId(String id) {
        return leitoRepository.findById(id)
                .orElseThrow(() -> new LeitoInexistenteException("Leito com ID " + id + " não encontrado."));
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public List<Leito> listarLeitos() {
        return leitoRepository.findAll();
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    public List<Exame> listarExames() {
        return exameRepository.findAll();
    }

    public List<Atendimento> listarAtendimentos() {
        return atendimentoRepository.findAll();
    }

    public Prontuario buscarProntuarioPaciente(String cpfPaciente) {
        return prontuarioRepository.findByPacienteCpf(cpfPaciente)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Prontuário para o paciente com CPF " + cpfPaciente + " não encontrado."));
    }

    public TipoExame cadastrarTipoExame(String nome, String descricao) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoObrigatorioException("Nome do tipo de exame é obrigatório.");
        }
        if (tipoExameRepository.findByNome(nome).isPresent()) {
            throw new IllegalArgumentException("Tipo de exame com nome '" + nome + "' já cadastrado.");
        }
        TipoExame tipo = new TipoExame(nome, descricao);
        tipoExameRepository.add(tipo);
        return tipo;
    }

    public TipoLeito cadastrarTipoLeito(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new CampoObrigatorioException("Descrição do tipo de leito é obrigatória.");
        }
        if (tipoLeitoRepository.findByDescricao(descricao).isPresent()) {
            throw new IllegalArgumentException("Tipo de leito com descrição '" + descricao + "' já cadastrado.");
        }
        TipoLeito tipo = new TipoLeito(descricao);
        tipoLeitoRepository.add(tipo);
        return tipo;
    }

    public Especialidade cadastrarEspecialidade(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoObrigatorioException("Nome da especialidade é obrigatório.");
        }
        if (especialidadeRepository.findByNome(nome).isPresent()) {
            throw new IllegalArgumentException("Especialidade com nome '" + nome + "' já cadastrada.");
        }
        Especialidade especialidade = new Especialidade(nome);
        especialidadeRepository.add(especialidade);
        return especialidade;
    }

    public Enfermeiro cadastrarEnfermeiro(String nome, String cpf, String telefone,
                                          String rua, String numero, String bairro, String cidade, String estado, String cep,
                                          String coren) {
        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty() || coren == null || coren.trim().isEmpty()) {
            throw new CampoObrigatorioException("Nome, CPF e COREN do enfermeiro são obrigatórios.");
        }
        if (enfermeiroRepository.findByCoren(coren).isPresent()) {
            throw new IllegalArgumentException("Enfermeiro com COREN " + coren + " já cadastrado.");
        }
        if (pacienteRepository.findByCpf(cpf).isPresent() || medicoRepository.findByCrm(cpf).isPresent()
                || enfermeiroRepository.findByCpf(cpf).isPresent() || administrativoRepository.findByCpf(cpf).isPresent()){
            throw new IllegalArgumentException("Já existe uma pessoa cadastrada com o CPF " + cpf + ".");
        }

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        Enfermeiro enfermeiro = new Enfermeiro(nome, cpf, telefone, endereco, coren);
        enfermeiroRepository.add(enfermeiro);
        System.out.println("Enfermeiro cadastrado com sucesso: " + enfermeiro);
        return enfermeiro;
    }

    public Administrativo cadastrarAdministrativo(String nome, String cpf, String telefone,
                                                  String rua, String numero, String bairro, String cidade, String estado, String cep,
                                                  String cargo) {
        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty() || cargo == null || cargo.trim().isEmpty()) {
            throw new CampoObrigatorioException("Nome, CPF e Cargo do funcionário administrativo são obrigatórios.");
        }
        if (pacienteRepository.findByCpf(cpf).isPresent() || medicoRepository.findByCrm(cpf).isPresent()
                || enfermeiroRepository.findByCpf(cpf).isPresent() || administrativoRepository.findByCpf(cpf).isPresent()){
            throw new IllegalArgumentException("Já existe uma pessoa cadastrada com o CPF " + cpf + ".");
        }

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        Administrativo administrativo = new Administrativo(nome, cpf, telefone, endereco, cargo);
        administrativoRepository.add(administrativo);
        System.out.println("Funcionário administrativo cadastrado com sucesso: " + administrativo);
        return administrativo;
    }
}