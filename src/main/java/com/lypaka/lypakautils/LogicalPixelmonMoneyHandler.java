package com.lypaka.lypakautils;

import com.pixelmonmod.pixelmon.api.economy.BankAccount;
import com.pixelmonmod.pixelmon.api.economy.BankAccountProxy;

import java.util.Optional;
import java.util.UUID;

public class LogicalPixelmonMoneyHandler {

    public static void add (UUID uuid, double amount) {

        Optional<? extends BankAccount> account = BankAccountProxy.getBankAccount(uuid);
        if (account.isPresent()) {

            account.get().add(amount);
            account.get().updatePlayer(); // putting this here for safety I guess

        } else {

            LypakaUtils.logger.error("Could not get account for UUID: " + uuid + "!");

        }

    }

    public static void remove (UUID uuid, double amount) {

        Optional<? extends BankAccount> account = BankAccountProxy.getBankAccount(uuid);
        if (account.isPresent()) {

            account.get().take(amount);
            account.get().updatePlayer(); // putting this here for safety I guess

        } else {

            LypakaUtils.logger.error("Could not get account for UUID: " + uuid + "!");

        }

    }

    public static double getBalance (UUID uuid) {

        double balance = 0;
        Optional<? extends BankAccount> account = BankAccountProxy.getBankAccount(uuid);
        if (account.isPresent()) {

            balance = account.get().getBalance().doubleValue();

        }

        return balance;

    }

}
