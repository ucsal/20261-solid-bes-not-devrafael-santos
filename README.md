# Olimpíada de Questões (V1) 🏆

Este projeto é um sistema de gerenciamento e aplicação de provas (com foco em questões de xadrez) desenvolvido em Java. 

O objetivo principal deste repositório é demonstrar a evolução de um código monolítico para uma **Arquitetura Limpa (Clean Architecture)** através da aplicação rigorosa dos **Princípios SOLID** e Injeção de Dependência Pura (Pure DI).

## 📐 Arquitetura e Aplicação do SOLID

O sistema foi refatorado para separar claramente as responsabilidades em camadas: **Domínio**, **Serviços de Aplicação (Use Cases)**, **Portas/Repositórios (Infraestrutura)** e **Apresentação (View/UI)**.

Abaixo, detalhamos como cada princípio do SOLID foi aplicado no código:

### 1. SRP - Princípio da Responsabilidade Única (Single Responsibility Principle)
*Uma classe deve ter um, e somente um, motivo para mudar.*

* **Domínio (`Questao`, `Participante`, etc.):** Responsáveis apenas por manter o estado da entidade e garantir a integridade dos seus próprios dados (ex: o método `validate()` no construtor garante que a entidade não nasça em um estado inválido).
* **AppServices (`QuestaoAppService`, etc.):** Responsáveis exclusivamente por orquestrar os casos de uso. Eles recebem DTOs, convertem em entidades de domínio e coordenam a persistência, sem misturar lógica de tela ou regras de negócio intrínsecas da entidade.
* **Menus (`MenuOpcao` e subclasses):** Cuidam exclusivamente da interação com o usuário via terminal (I/O com `Scanner` e `System.out`).
* **Visualização Utilitária (`ConsoleBoardPrinter`):** Uma classe isolada apenas para a responsabilidade de desenhar o tabuleiro FEN na tela.
* **Root Configuration (`App.java`):** Atua unicamente como o *Composition Root* do sistema, focado apenas em instanciar dependências e fazer a "fiação" (wiring) da aplicação.

### 2. OCP - Princípio do Aberto/Fechado (Open/Closed Principle)
*Entidades de software devem estar abertas para extensão, mas fechadas para modificação.*

* **Sistema de Menus:** A classe base `Menu` está **fechada** para modificação. Ela possui a lógica de desenhar opções e capturar entradas de forma genérica. No entanto, o sistema está **aberto** para extensão: para adicionar uma nova funcionalidade (ex: "Excluir Prova"), basta criar uma nova classe que estenda `MenuOpcao` e adicioná-la à lista no `App.java`, sem alterar o motor principal do menu.

### 3. LSP - Princípio da Substituição de Liskov (Liskov Substitution Principle)
*Classes derivadas devem poder ser substituídas por suas classes base sem quebrar o comportamento do sistema.*

* **Polimorfismo nos Menus:** A classe `Menu` recebe uma `List<MenuOpcao>` e itera chamando o método `action()`. O sistema funciona perfeitamente seja o objeto um `CadastrarProvaMO`, um `AplicarProvaMO` ou um `ListarTentativasMO`. Todas as subclasses respeitam o contrato da classe abstrata base, garantindo um comportamento previsível.

### 4. ISP - Princípio da Segregação de Interfaces (Interface Segregation Principle)
*Módulos não devem ser forçados a depender de interfaces que não utilizam.*

* **Portas de Repositório (`ports`):** Foram criadas interfaces específicas e focadas para cada entidade (`ParticipanteRepository`, `ProvaRepository`, `QuestaoRepository`, `TentativaRepository`). Isso evita a criação de um "GigaRepository" genérico e garante que as implementações forneçam apenas os métodos que seus respectivos serviços de fato consomem.

### 5. DIP - Princípio da Inversão de Dependência (Dependency Inversion Principle)
*Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações.*

* **Injeção de Dependência (Pure DI):** Os serviços de aplicação (ex: `ParticipanteAppService`) representam o alto nível e não fazem ideia de como os dados são salvos. Eles dependem unicamente da abstração (interface `ParticipanteRepository`).
* A implementação concreta de baixo nível (`ParticipanteRepositoryImpl` com listas em memória) é instanciada apenas no `App.java` e **injetada via construtor** no serviço. 
* Se houver a necessidade de migrar o banco de dados para PostgreSQL ou MySQL, bastará criar uma nova implementação da interface e trocar a instância no arquivo `App.java`, sem modificar uma única linha de código nos serviços de aplicação.

---

## 🚀 Como Executar

1. Clone o repositório.
2. Compile o projeto via Maven ou na sua IDE de preferência.
3. Execute a classe `App.java` localizada em `src/main/java/br/com/ucsal/olimpiadas/App.java`.
4. O sistema iniciará no console com dados pré-carregados (Data Seeder) para facilitar os testes.
