package com.designPrinciples.L_LiskovSubstituitionPrinciple;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class L_LiskovSubstituitionProblem {

    @Getter
    @Setter
    private static class BasicAccount {
        protected double balance = 0.0;

        public void yield() {
            this.balance += this.balance *= 0.02;
        }

        public void deposit(double value){
            this.balance += value;
        }

        public double withdraw(double value){
            if(value <= this.balance)
                return this.balance;
            else throw new IllegalArgumentException("Insufficient funds");
        }
    }

    private static class SalaryAccount extends BasicAccount{
        public void receiveSalaryFromCampany(double value){
            this.balance += value;
        }

        @Override
        public void yield() {
            throw new UnsupportedOperationException("Salary account can't yield");
        }
    }

    public static void main(String[] args) {
        List<BasicAccount> accountList = new AccountDAO().getAllAccounts();

        for(BasicAccount account :accountList){
            System.out.println("old balance: " + account.getBalance());
            account.yield();
            System.out.println("new balance: " + account.getBalance());
        }
    }


    private static class AccountDAO{
        private List<BasicAccount> getAllAccounts(){
            BasicAccount basicAccount = new BasicAccount();
            basicAccount.setBalance(1000.0);

            SalaryAccount salaryAccount = new SalaryAccount();
            salaryAccount.setBalance(10);

            return Arrays.asList(basicAccount, salaryAccount);
        }
    }
}
