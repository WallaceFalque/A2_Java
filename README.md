# Sistema de Vendas

Sistema desktop em Java (Swing) para uma pequena loja registrar seus **clientes**, **produtos** e **vendas**.
Trabalho da A2 da disciplina de Programação Orientada a Objetos.

## Funcionalidades

- **Cadastro de Clientes**: inclusão, alteração, consulta e exclusão (nome, CPF e telefone).
  O CPF tem máscara (`000.000.000-00`) e a validação confere os dígitos verificadores.
- **Cadastro de Produtos**: inclusão, alteração, consulta e exclusão (descrição, preço e estoque).
- **Vendas (tela de negócio)**: relaciona um cliente a um produto e calcula o valor total.
  O estoque é baixado ao vender, devolvido ao excluir a venda, e não é possível vender mais do que há em estoque.

Os dados ficam gravados em arquivos na pasta `dados/` (um arquivo por tabela), simulando uma base de dados.

## Estrutura

```
SistemaVendas/src/main/java/sistemavendas/
├── Main.java            # ponto de entrada
├── model/               # classes de domínio (Entidade, Cliente, Produto, Venda)
├── dados/               # persistência (Tabela genérica e BancoDados)
└── view/                # telas Swing (.java + .form) e Estilo (tema visual)
```

## Como executar

Requisito: Java 17 ou superior.

**VS Code**: com a extensão *Extension Pack for Java*, abra `Main.java` e clique em **Run**.

**NetBeans**: abra a pasta `SistemaVendas` como projeto Maven e execute.

**Terminal (PowerShell)**, a partir da pasta raiz do repositório:

```powershell
javac -encoding UTF-8 -d SistemaVendas/target/classes (Get-ChildItem -Recurse SistemaVendas/src/main/java -Filter *.java).FullName
java -cp SistemaVendas/target/classes sistemavendas.Main
```

> Execute a partir da pasta raiz para que o sistema encontre a pasta `dados/`.
