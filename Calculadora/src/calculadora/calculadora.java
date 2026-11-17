
package calculadora;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class calculadora {

    private static final String NOME = "Kauan Felipe Souza Da Cruz";
    private static final String RA = "0023597";

    // Cor baseada na data de nascimento: 05/11/2006
    // RGB: 105, 111, 206
    private static final Color AZUL_NASCIMENTO =
            new Color(105, 111, 206);

    private static final Color FUNDO =
            new Color(230, 234, 255);

    private static final Color TEXTO =
            new Color(23, 30, 75);

    private static final Color OPERADOR =
            new Color(204, 211, 255);

    private JFrame frame;
    private JTextField JTFResultado;

    private double valor1 = 0;
    private String op = "";
    private boolean novoNumero = true;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    calculadora janela = new calculadora();
                    janela.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public calculadora() {
        initialize();
    }

    // Criacao e personalizacao dos botoes
    private JButton criarBotao(
            String texto, int x, int y,
            int largura, int altura) {

        JButton botao = new JButton(texto);

        botao.setBounds(x, y, largura, altura);
        botao.setFont(new Font("Arial", Font.BOLD, 19));
        botao.setMargin(new Insets(0, 0, 0, 0));
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setOpaque(true);

        // Cor do botao igual
        if ("=".equals(texto)) {

            botao.setBackground(AZUL_NASCIMENTO);
            botao.setForeground(Color.WHITE);

        // Cor do botao limpar
        } else if ("C".equals(texto)) {

            botao.setBackground(new Color(255, 219, 226));
            botao.setForeground(new Color(145, 24, 58));

        // Cores das operacoes
        } else if ("+".equals(texto)
                || "-".equals(texto)
                || "*".equals(texto)
                || "/".equals(texto)
                || "^".equals(texto)) {

            botao.setBackground(OPERADOR);
            botao.setForeground(AZUL_NASCIMENTO);

        // Cores dos numeros
        } else {

            botao.setBackground(Color.WHITE);
            botao.setForeground(TEXTO);
        }

        botao.setBorder(
                BorderFactory.createLineBorder(
                        new Color(190, 199, 236), 1));

        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String comando =
                        ((JButton) e.getSource()).getText();

                if (comando.length() == 1
                        && "0123456789".contains(comando)) {

                    digitar(comando);

                } else if (".".equals(comando)
                        || ",".equals(comando)) {

                    digitarPonto();

                } else if ("C".equals(comando)) {

                    limpar();

                } else if ("=".equals(comando)) {

                    calcular();

                } else if ("+".equals(comando)) {

                    escolherOperacao("Soma");

                } else if ("-".equals(comando)) {

                    escolherOperacao("Subtracao");

                } else if ("*".equals(comando)) {

                    escolherOperacao("Multiplicacao");

                } else if ("/".equals(comando)) {

                    escolherOperacao("Divisao");

                } else if ("^".equals(comando)) {

                    escolherOperacao("Potencia");
                }
            }
        });

        frame.getContentPane().add(botao);
        return botao;
    }

    // Digitacao dos numeros
    private void digitar(String digito) {

        if (novoNumero
                || "0".equals(JTFResultado.getText())
                || "Erro".equals(JTFResultado.getText())) {

            JTFResultado.setText(digito);
            novoNumero = false;

        } else {

            JTFResultado.setText(
                    JTFResultado.getText() + digito);
        }
    }

    // Ponto decimal
    private void digitarPonto() {

        if (novoNumero
                || "Erro".equals(JTFResultado.getText())) {

            JTFResultado.setText("0.");
            novoNumero = false;

        } else if (!JTFResultado.getText().contains(".")) {

            JTFResultado.setText(
                    JTFResultado.getText() + ".");
        }
    }

    // Seleciona a operacao
    private void escolherOperacao(String novaOp) {

        if ("Erro".equals(JTFResultado.getText())) {
            return;
        }

        if (!op.isEmpty() && !novoNumero) {

            calcular();

            if ("Erro".equals(JTFResultado.getText())) {
                return;
            }
        }

        valor1 = Double.parseDouble(
                JTFResultado.getText());

        op = novaOp;
        novoNumero = true;
    }

    // METODO 1 - ADICAO
    private double adicao(double a, double b) {

        return a + b;
    }

    // METODO 2 - SUBTRACAO
    private double subtracao(double a, double b) {

        return a - b;
    }

    // METODO 3 - MULTIPLICACAO
    private double multiplicacao(double a, double b) {

        return a * b;
    }

    // METODO 4 - DIVISAO
    private double divisao(double a, double b) {

        if (b == 0) {

            throw new ArithmeticException(
                    "Divisao por zero");
        }

        return a / b;
    }

    // METODO 5 - POTENCIA
    private double potencia(double a, double b) {

        return Math.pow(a, b);
    }

    // Realiza o calculo
    private void calcular() {

        if (op.isEmpty() || novoNumero) {
            return;
        }

        double valor2 = Double.parseDouble(
                JTFResultado.getText());

        double resultado;

        try {

            if ("Soma".equals(op)) {

                resultado = adicao(valor1, valor2);

            } else if ("Subtracao".equals(op)) {

                resultado = subtracao(valor1, valor2);

            } else if ("Multiplicacao".equals(op)) {

                resultado = multiplicacao(valor1, valor2);

            } else if ("Divisao".equals(op)) {

                resultado = divisao(valor1, valor2);

            } else if ("Potencia".equals(op)) {

                resultado = potencia(valor1, valor2);

            } else {

                return;
            }

            if (Double.isNaN(resultado)
                    || Double.isInfinite(resultado)) {

                throw new ArithmeticException(
                        "Resultado invalido");
            }

            // Remove o .0 quando o resultado for inteiro
            if (resultado == Math.rint(resultado)
                    && resultado >= Long.MIN_VALUE
                    && resultado < 0x1.0p63) {

                JTFResultado.setText(
                        String.valueOf((long) resultado));

            } else {

                JTFResultado.setText(
                        String.valueOf(resultado));
            }

            valor1 = resultado;

        } catch (ArithmeticException erro) {

            JTFResultado.setText("Erro");
            valor1 = 0;
        }

        op = "";
        novoNumero = true;
    }

    // Limpa a calculadora
    private void limpar() {

        JTFResultado.setText("0");
        valor1 = 0;
        op = "";
        novoNumero = true;
    }

    // Janela principal
    private void initialize() {

        frame = new JFrame();

        frame.setTitle(
                "Calculadora - " + NOME + " - RA: " + RA);

        frame.setBounds(100, 100, 380, 555);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(FUNDO);

        // Titulo
        JLabel titulo = new JLabel(
                "CALCULADORA | " + NOME);

        titulo.setFont(
                new Font("Arial", Font.BOLD, 13));

        titulo.setForeground(TEXTO);

        titulo.setHorizontalAlignment(
                SwingConstants.CENTER);

        titulo.setBounds(10, 13, 345, 23);

        frame.getContentPane().add(titulo);

        // Visor
        JTFResultado = new JTextField("0");

        JTFResultado.setBounds(31, 50, 302, 71);

        JTFResultado.setFont(
                new Font("Arial", Font.BOLD, 29));

        JTFResultado.setHorizontalAlignment(
                SwingConstants.RIGHT);

        JTFResultado.setBackground(
                new Color(22, 29, 79));

        JTFResultado.setForeground(Color.WHITE);

        JTFResultado.setBorder(
                BorderFactory.createEmptyBorder(
                        8, 10, 8, 12));

        JTFResultado.setEditable(false);

        frame.getContentPane().add(JTFResultado);

        // Tamanho e posicao dos botoes
        int w = 68;
        int h = 52;

        int x1 = 31;
        int x2 = 109;
        int x3 = 187;
        int x4 = 265;

        int y0 = 143;
        int y1 = 205;
        int y2 = 267;
        int y3 = 329;
        int y4 = 391;

        // Primeira linha
        criarBotao("C", x1, y0, 146, h);
        criarBotao("^", x3, y0, 146, h);

        // Segunda linha
        criarBotao("7", x1, y1, w, h);
        criarBotao("8", x2, y1, w, h);
        criarBotao("9", x3, y1, w, h);
        criarBotao("/", x4, y1, w, h);

        // Terceira linha
        criarBotao("4", x1, y2, w, h);
        criarBotao("5", x2, y2, w, h);
        criarBotao("6", x3, y2, w, h);
        criarBotao("*", x4, y2, w, h);

        // Quarta linha
        criarBotao("1", x1, y3, w, h);
        criarBotao("2", x2, y3, w, h);
        criarBotao("3", x3, y3, w, h);
        criarBotao("-", x4, y3, w, h);

        // Quinta linha
        criarBotao("0", x1, y4, w, h);
        criarBotao(".", x2, y4, w, h);
        criarBotao("=", x3, y4, w, h);
        criarBotao("+", x4, y4, w, h);

        // Identificacao do aluno
        JLabel rodape = new JLabel(
                "RA: " + RA + " | Tema: 05/11/2006");

        rodape.setFont(
                new Font("Arial", Font.PLAIN, 12));

        rodape.setForeground(TEXTO);

        rodape.setHorizontalAlignment(
                SwingConstants.CENTER);

        rodape.setBounds(10, 464, 345, 25);

        frame.getContentPane().add(rodape);
    }
}
