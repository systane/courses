package com.designPrinciples.L_LiskovSubstituitionPrinciple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class L_LiskovSubstituitionSolution {

    @Getter @Setter
    private static class AccountManager {
        private double balance;

        private void yield(double percent) {
            this.balance += this.balance *= percent;
        }

        private void deposit(double value){
            this.balance += value;
        }
    }

    @Getter @Setter @AllArgsConstructor
    private static class BasicAccount {
        private AccountManager accountManager;

        private void yield(double percent) {
            accountManager.yield(0.02);
        }

        private void deposit(double value){
            accountManager.deposit(value);
        }

        private double getCurrentBalance(){
            return accountManager.getBalance();
        }
    }

    private static class SalaryAccount {
        private AccountManager accountManager;

        public void receiveSalaryFromCampany(double value){
            accountManager.deposit(value);
        }
    }

    public static void main(String[] args) {
        List<BasicAccount> accountList = new AccountDAO().getAllBasicAccounts();

        for(BasicAccount account :accountList){
            System.out.println("old balance: " + account.getCurrentBalance());
            account.yield(0.02);
            System.out.println("new balance: " + account.getCurrentBalance());
        }
    }

    private static class AccountDAO{
        private List<BasicAccount> getAllBasicAccounts(){
            BasicAccount basicAccount = new BasicAccount(new AccountManager());
            basicAccount.deposit(1000.0);

            return Arrays.asList(basicAccount);
        }
    }
}
