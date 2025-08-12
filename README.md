

# 💱 CONVERSOR DE MOEDAS

## 📝 Descrição
Este é um conversor de moedas desenvolvido em **Java** com o auxílio do **Maven** para gerenciamento de dependências e build.  
A aplicação permite ao usuário converter valores entre diferentes moedas, utilizando taxas de câmbio atualizadas fornecidas pela **[ExchangeRate-API](https://exchangerate-api.com)**.

O projeto foi criado como parte do desafio do programa **Oracle Next Education (ONE)** em parceria com a **Alura**, com o objetivo de aplicar e consolidar conhecimentos em **Java** e **orientação a objetos**.

---

## ✨ Funcionalidades
- **Conversão de Moedas**: Suporte para Dólar Americano (USD), Real Brasileiro (BRL), Euro (EUR), entre outras.
- **Menu Interativo**: Interface de linha de comando (CLI) que guia o usuário na escolha das moedas e valores.
- **Taxas de Câmbio em Tempo Real**: Integração com a *ExchangeRate-API* para obter dados de câmbio atualizados.
- **Gerenciamento com Maven**: Utiliza o `pom.xml` para gerenciar dependências e facilitar o build do projeto.

![Conversor_de_moedas](https://github.com/user-attachments/assets/32e4dd8b-320c-446e-b589-8a3631d9504b)

---

## 🚀 Tecnologias Utilizadas
- **Java 21**: Linguagem principal do projeto.
- **Maven**: Ferramenta para automação de build e gerenciamento de dependências.
- **Gson**: Biblioteca do Google para manipulação de JSON.
- **ExchangeRate-API**: API externa para cotações de moedas.

---

## ⚙️ Como Executar o Projeto

### Obter a chave da API

1. Acesse [ExchangeRate-Api.com](https://www.exchangerate-api.com).
2. Cadastre-se e copie sua chave de API (ex.: `123456abcdef`).

### Configure a variável de ambiente `API_KEY`
#### No Windows (PowerShell)

```powershell
$Env:API_KEY = "SUA_API_KEY_AQUI"
```

#### No Linux/macOS (bash/zsh)

```bash
export API_KEY="SUA_API_KEY_AQUI"
```

### Passos para execução
> É necessário ter o **Java (JDK 21 ou superior)** e o **Apache Maven** instalados.

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/lucastnsoares/conversor_de_moedas.git
   
2. **Acesse a pasta do projeto**:
    ```bash
   conversor_de_moedas
   
3. **Compile e execute com o Maven**
    ```bash
    mvn compile exec:java -Dexec.mainClass="ConversorDeMoedas"
