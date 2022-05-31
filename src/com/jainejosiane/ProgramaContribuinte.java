package com.jainejosiane;

import java.util.Locale;
import java.util.Scanner;

public class ProgramaContribuinte {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite os dados do contribuinte: ");

        System.out.print("Renda anual com salário: ");
        double rendaAnualSalario = sc.nextDouble();

        System.out.print("Renda anual com prestação de serviço: ");
        double rendaAnualPrestacaoServico = sc.nextDouble();

        System.out.print("Renda anual com ganho de capital: ");
        double rendaAnualGanhoCapital = sc.nextDouble();

        System.out.print("Gastos médicos: ");
        double gastosMedicosAnuais = sc.nextDouble();

        System.out.print("Gastos educacionais: ");
        double gastosEducacionaisAnuais = sc.nextDouble();

        System.out.println();
        System.out.println("RELATÓRIO");

        System.out.printf("Imposto sobre salário: R$ %.2f%n", calcularImpostoSalario(rendaAnualSalario));

        System.out.printf("Imposto sobre salário com prestação de serviços: R$ %.2f%n", calcularImpostoServicos(rendaAnualPrestacaoServico));

        System.out.printf("Imposto sobre salário com ganho de capital: R$ %.2f%n", calcularImpostoGanhoCapital(rendaAnualGanhoCapital));

        System.out.printf("Imposto bruto total: R$ %.2f%n", calcularImpostoMensalTotal(rendaAnualSalario, rendaAnualPrestacaoServico, rendaAnualGanhoCapital));

        System.out.printf("Abatimento: %.2f%n", calcularDescontoImposto(rendaAnualSalario, rendaAnualPrestacaoServico, rendaAnualGanhoCapital, gastosMedicosAnuais, gastosEducacionaisAnuais));

        System.out.printf("Imposto devido: %.2f%n", calcularImpostoDevido(rendaAnualSalario, rendaAnualPrestacaoServico, rendaAnualGanhoCapital, gastosMedicosAnuais, gastosEducacionaisAnuais));

        sc.close();
    }

    public static double calcularImpostoSalario(double salario) {
        double impostoSalario = 0;
        double salarioMensal = salario / 12;
        if (salarioMensal < 3000) {
            impostoSalario = 0;
        } else if (salarioMensal >= 3000 && salarioMensal < 5000) {
            impostoSalario = (salarioMensal * 0.10) * 12;
        } else if (salarioMensal >= 5000) {
            impostoSalario = (salarioMensal * 0.20) * 12;
        }
        return impostoSalario;
    }

    public static double calcularImpostoServicos(double salarioServicos) {
        double impostoServicos = 0;
        double salarioServicosMensal = salarioServicos / 12;
        if (salarioServicos != 0) {
            impostoServicos = (salarioServicosMensal * 0.15) * 12;
        } else {
            impostoServicos = 0;
        }
        return impostoServicos;
    }

    public static double calcularImpostoGanhoCapital(double salarioGanhoCapital) {
        double impostoGanhoCapital = 0;
        double salarioGanhoCapitalMensal = salarioGanhoCapital / 12;
        if (salarioGanhoCapital != 0) {
            impostoGanhoCapital = (salarioGanhoCapitalMensal * 0.20) * 12;
        } else {
            impostoGanhoCapital = 0;
        }
        return impostoGanhoCapital;
    }

    public static double calcularImpostoMensalTotal(double salario, double salarioServicos, double salarioGanhoCapital) {
        return calcularImpostoSalario(salario) + calcularImpostoServicos(salarioServicos) + calcularImpostoGanhoCapital(salarioGanhoCapital);
    }

    public static double calcularDescontoImposto(double salario, double salarioServicos, double salarioGanhoCapital, double gastosMedicos, double gastosEducacionais) {
        double descontos = gastosMedicos + gastosEducacionais;
        double descontoImposto = (calcularImpostoMensalTotal(salario, salarioServicos, salarioGanhoCapital)) * 0.3;
        return Math.min(descontos, descontoImposto);
    }

    public static double calcularImpostoDevido(double salario, double salarioServicos, double salarioGanhoCapital, double gastosMedicos, double gastosEducacionais) {
        return ((calcularImpostoMensalTotal(salario, salarioServicos, salarioGanhoCapital)) - calcularDescontoImposto(salario, salarioServicos, salarioGanhoCapital, gastosMedicos, gastosEducacionais));
    }
}